package com.comtrade.domen;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({ "serial", "unused" })
public class ClanGrupe implements OpstiDomen, Serializable {
	
	private Profil clan;
	private Grupa grupa;
	private int statusClana;
	private int adminStatus;
	private int brojProcitanihPoruka;
	
	public ClanGrupe(Profil clan, Grupa grupa, int statusClana, int adminStatus, int brojProcitanihPoruka) {
		super();
		this.clan = clan;
		this.grupa = grupa;
		this.statusClana = statusClana;
		this.adminStatus=adminStatus;
		this.brojProcitanihPoruka = brojProcitanihPoruka;
	}
	public ClanGrupe() {
	}
	
	public int getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(int adminStatus) {
		this.adminStatus = adminStatus;
	}
	public Profil getClan() {
		return clan;
	}
	public void setClan(Profil clan) {
		this.clan = clan;
	}
	public Grupa getGrupa() {
		return grupa;
	}
	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}
	public int getStatusClana() {
		return statusClana;
	}
	public void setStatusClana(int statusClana) {
		this.statusClana = statusClana;
	}
	public int getBrojProcitanihPoruka() {
		return brojProcitanihPoruka;
	}
	public void setBrojProcitanihPoruka(int brojProcitanihPoruka) {
		this.brojProcitanihPoruka = brojProcitanihPoruka;
	}
	
	@Override
	public String toString() {
		return getClan() + " je poslao zahtev za " + getGrupa() + "";
	}
	
	
	///////////////////////////////////////////////////////////////////////
	

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "clanovi_grupe";
	}
	@Override
	public String vratiZaInsert() {
		// TODO Auto-generated method stub
		return "VALUES ("+getClan().getIdProfila()+","+getGrupa().getIdGrupa()+","+getStatusClana()+","+getAdminStatus()+","+getBrojProcitanihPoruka()+")";
	}
	@Override
	public String vratiZaUpdate() {
		// TODO Auto-generated method stub
		return "brojProcitanihPoruka="+getBrojProcitanihPoruka()+"";
	}
	@Override
	public String vratiZaDelete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OpstiDomen> izvrsiSelect(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ClanGrupe vratiClanaGrupe(ResultSet rs) {
		ClanGrupe cg=new ClanGrupe();
		
		try {
			cg.setStatusClana(rs.getInt("statusClana"));
			cg.setAdminStatus(rs.getInt("adminStatus"));
			cg.setBrojProcitanihPoruka(rs.getInt("brojProcitanihPoruka"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cg;
		
	}

}
