package com.comtrade.domen;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZahtevZaPrijateljstvo implements Serializable{
	
	private Profil posiljalac;
	private Profil primalac;
	
	public ZahtevZaPrijateljstvo(Profil posiljalac, Profil primalac) {
		super();
		this.posiljalac = posiljalac;
		this.primalac = primalac;
	}
	
	public ZahtevZaPrijateljstvo() {
		
	}
	

	public Profil getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Profil posiljalac) {
		this.posiljalac = posiljalac;
	}

	public Profil getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Profil primalac) {
		this.primalac = primalac;
	}

	@Override
	public String toString() {
		return getPosiljalac() + " je poslao zahtev " + getPrimalac();
	}
	
	

}
