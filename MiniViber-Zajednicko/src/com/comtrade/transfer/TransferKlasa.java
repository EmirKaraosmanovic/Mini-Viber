package com.comtrade.transfer;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransferKlasa implements Serializable{
	
	private int operacija;
	private Object klijentObjekat;
	private Object serverObjekat_odgovor;
	private Object serverPoruka_odgovor;
	
	
	public int getOperacija() {
		return operacija;
	}
	public void setOperacija(int operacija) {
		this.operacija = operacija;
	}
	public Object getKlijentObjekat() {
		return klijentObjekat;
	}
	public void setKlijentObjekat(Object klijentObjekat) {
		this.klijentObjekat = klijentObjekat;
	}
	public Object getServerObjekat_odgovor() {
		return serverObjekat_odgovor;
	}
	public void setServerObjekat_odgovor(Object serverObjekat_odgovor) {
		this.serverObjekat_odgovor = serverObjekat_odgovor;
	}
	public Object getServerPoruka_odgovor() {
		return serverPoruka_odgovor;
	}
	public void setServerPoruka_odgovor(Object serverPoruka_odgovor) {
		this.serverPoruka_odgovor = serverPoruka_odgovor;
	}
	@Override
	public String toString() {
		return "TransferKlasa [operacija=" + operacija + ", klijentObjekat=" + klijentObjekat
				+ ", serverObjekat_odgovor=" + serverObjekat_odgovor + ", serverPoruka_odgovor=" + serverPoruka_odgovor
				+ "]";
	}
	
	

}
