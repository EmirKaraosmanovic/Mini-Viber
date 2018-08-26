package com.comtrade.komunikacija;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import javax.swing.JOptionPane;

import com.comtrade.kontrolerFajl.*;
import com.comtrade.transfer.TransferKlasa;

public class Komunikacija {
	
	private Socket klijentSocket;
	private static Komunikacija instanca;
	
	public Socket getKlijentSocket() {
		return klijentSocket;
	}

	public void setKlijentSocket(Socket klijentSocket) {
		this.klijentSocket = klijentSocket;
	}

	private Komunikacija() {
		String niz[]=KontrolerFajl.vratiInstancu().vratiPodatkeIzFajla();
		try {
			klijentSocket=new Socket(niz[0], Integer.parseInt(niz[1]));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server nije u funkciji!!!");
		} 
		
	}
	
	public static Komunikacija vratiInstancu() {
		
		if(instanca==null)
			instanca=new Komunikacija();
		return instanca;
		
	}
	
	public void posalji(TransferKlasa tk) {
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(klijentSocket.getOutputStream());
			oos.writeObject(tk);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	public TransferKlasa procitaj() {
		
		try {
			ObjectInputStream ois=new ObjectInputStream(klijentSocket.getInputStream());
			return (TransferKlasa) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
