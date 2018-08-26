package com.comtrade.kontrolerFajl;

import java.io.*;

import java.util.*;

import com.comtrade.konstante.Konstante;


public class KontrolerFajl implements Konstante{
	
	private static KontrolerFajl instanca;
	
	private KontrolerFajl() {
		
	}
	
	public static KontrolerFajl vratiInstancu() {
		
		if (instanca==null) {
			instanca=new KontrolerFajl();
		}
		return instanca;
	}
	
	public String [] vratiPodatkeIzFajla() {
		String [] niz = new String[2];
		Scanner sc=null;
		
		try {
			sc = new Scanner(new File(NAZIV_FAJLA));
			while(sc.hasNextLine()) {
				String linija=null;
				try {
					linija = sc.next();
				}catch (Exception e) {
					break;
				}
				niz = linija.split("/");
				
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return niz;
		
	}

}
