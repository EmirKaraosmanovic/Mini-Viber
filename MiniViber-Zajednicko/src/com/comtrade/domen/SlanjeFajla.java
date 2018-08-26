package com.comtrade.domen;


import java.io.Serializable;

@SuppressWarnings("serial")
public class SlanjeFajla implements Serializable{
	
	private byte[] podatak;
	private String primalac;
	private String nazivFajla;
	
	public SlanjeFajla(byte[] podatak, String primalac,String nazivFajla) {
		super();
		this.podatak = podatak;
		this.primalac = primalac;
		this.nazivFajla=nazivFajla;
	}
	
	public SlanjeFajla() {
		
	}
	
	

	public String getNazivFajla() {
		return nazivFajla;
	}

	public void setNazivFajla(String nazivFajla) {
		this.nazivFajla = nazivFajla;
	}

	public byte[] getPodatak() {
		return podatak;
	}

	public void setPodatak(byte[] podatak) {
		this.podatak = podatak;
	}

	public String getPrimalac() {
		return primalac;
	}

	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}
	
}
