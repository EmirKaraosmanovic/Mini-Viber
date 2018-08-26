package com.comtrade.niti;


import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.comtrade.kontrolerFajl.*;
import com.comtrade.kontrolerServer.KontrolerServer;

@SuppressWarnings("resource")
public class NitServer extends Thread{
	
	
	public void run() {
		
		try {
			pokreniServer();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server nije pokrenut ("+e.getMessage()+")");
		}	
		
	}


	private void pokreniServer() {
		String niz[]=KontrolerFajl.vratiInstancu().vratiPodatkeIzFajla();
		try {
			ServerSocket serverSocket=new ServerSocket(Integer.parseInt(niz[1]));
			KontrolerServer.vratiObjekat().prikaziObavestenje("Server je pokrenut i spreman da prihvata klijente"+"\n");
			while(true) {
				Socket klijentSocket=serverSocket.accept();
				NitObradaServer noz=new NitObradaServer();
				noz.setSocket(klijentSocket);
				noz.start();
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	
	

}
