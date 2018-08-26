package com.comtrade.domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.konstante.Konstante;

@SuppressWarnings({"serial","unused"})
public abstract class Poruka implements Serializable,OpstiDomen,Konstante{
	
	private int idPoruke;
	private int idProfil;
	private Profil posiljalac;
	private String tekstPoruke;
	private Date datumSlanja;
	private Time vremeSlanja;

	public Poruka(int idPoruke, Profil posiljalac, String tekstPoruke, Date datumSlanja,
			Time vremeSlanja) {
		super();
		this.idPoruke = idPoruke;
		this.posiljalac = posiljalac;
		this.tekstPoruke = tekstPoruke;
		this.datumSlanja = datumSlanja;
		this.vremeSlanja = vremeSlanja;
	}
	
	public Poruka() {
		
	}
	

	public int getIdPoruke() {
		return idPoruke;
	}

	public void setIdPoruke(int idPoruke) {
		this.idPoruke = idPoruke;
	}

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

	public Profil getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Profil posiljalac) {
		this.posiljalac = posiljalac;
	}

	public String getTekstPoruke() {
		return tekstPoruke;
	}

	public void setTekstPoruke(String tekstPoruke) {
		this.tekstPoruke = tekstPoruke;
	}

	public Date getDatumSlanja() {
		return datumSlanja;
	}

	public void setDatumSlanja(Date datumSlanja) {
		this.datumSlanja = datumSlanja;
	}

	public Time getVremeSlanja() {
		return vremeSlanja;
	}

	public void setVremeSlanja(Time vremeSlanja) {
		this.vremeSlanja = vremeSlanja;
	}


	//////////////////////////////////////////////////////////////////////////////
	
	
	

}
