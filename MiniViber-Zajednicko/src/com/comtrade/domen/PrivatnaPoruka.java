package com.comtrade.domen;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PrivatnaPoruka extends Poruka{

	private int idPrijatelj;
	private Profil primalac;
	private int statusPoruke;

	public PrivatnaPoruka(int idPoruke ,Profil posiljalac,Profil primalac, String tekstPoruke,
			Date datumSlanja, Time vremeSlanja,int statusPoruke) {
		super(idPoruke, posiljalac, tekstPoruke, datumSlanja, vremeSlanja);
		this.primalac=primalac;
		this.statusPoruke=statusPoruke;
		
	}
	
	public PrivatnaPoruka() {
		
	}

	
	public int getIdPrijatelj() {
		return idPrijatelj;
	}


	public void setIdPrijatelj(int idPrijatelj) {
		this.idPrijatelj = idPrijatelj;
	}


	public Profil getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Profil primalac) {
		this.primalac = primalac;
	}

	public int getStatusPoruke() {
		return statusPoruke;
	}

	public void setStatusPoruke(int statusPoruke) {
		this.statusPoruke = statusPoruke;
	}
	
	@Override
	public String toString() {
		return getPosiljalac().getKorisnickoIme()+" ("+getDatumSlanja()+"/"+getVremeSlanja()+"): \n >>"+getTekstPoruke()+"\n\n";
	}


////////////////////////////////////////////////////////////////////////////////////////


	

	@Override
	public String vratiNazivTabele() {
		return "privatna_poruka";
	}

	@Override
	public String vratiZaInsert() {
		return "(idProfila,tekstPoruke,idPrijatelja,datumSlanja,vremeSlanja,statusPoruke) VALUES"
				+ " ('"+getPosiljalac().getIdProfila()+"','"+getTekstPoruke()+"','"+getPrimalac().getIdProfila()+"',"
				+ "'"+getDatumSlanja()+"','"+getVremeSlanja()+"',"+getStatusPoruke()+")";
	}

	@Override
	public String vratiZaUpdate() {
		return "statusPoruke="+PORUKA_JE_PROCITANA+"";
	}

	@Override
	public String vratiZaDelete() {
		return null;
	}

	@Override
	public List<OpstiDomen> izvrsiSelect(ResultSet rs) {
		List<OpstiDomen> list=new ArrayList<>();
		try {
			while(rs.next()) {
				int idPoruka=rs.getInt("idPrivatnaPoruka");
				String tekstPoruke=rs.getString("tekstPoruke");
				Date datumSlanja=rs.getDate("datumSlanja");
				Time vremeSlanja=rs.getTime("vremeSlanja");
				int statusPoruke=rs.getInt("statusPoruke");
				PrivatnaPoruka pp=new PrivatnaPoruka(idPoruka, super.getPosiljalac(), primalac, tekstPoruke, datumSlanja, vremeSlanja, statusPoruke);
				pp.setIdProfil(rs.getInt("idProfila"));
				pp.setIdPrijatelj(rs.getInt("idPrijatelja"));
				list.add(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public PrivatnaPoruka vratiPoruku(ResultSet rs) {
		PrivatnaPoruka pp=null;
		try {
				int idPoruka=rs.getInt("idPrivatnaPoruka");
				String tekstPoruke=rs.getString("tekstPoruke");
				Date datumSlanja=rs.getDate("datumSlanja");
				Time vremeSlanja=rs.getTime("vremeSlanja");
				int statusPoruke=rs.getInt("statusPoruke");
				pp=new PrivatnaPoruka(idPoruka, super.getPosiljalac(), primalac, tekstPoruke, datumSlanja, vremeSlanja, statusPoruke);
				pp.setIdProfil(rs.getInt("idProfila"));
				pp.setIdPrijatelj(rs.getInt("idPrijatelja"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pp;
	}
	

}
