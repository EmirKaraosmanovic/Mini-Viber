package com.comtrade.domen;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GrupnaPoruka extends Poruka{

	private int idGrupe;
	private Grupa grupa;
	
	
	public GrupnaPoruka(int idPoruke, Profil posiljalac, Grupa grupa, String tekstPoruke, Date datumSlanja, Time vremeSlanja) {
		super(idPoruke, posiljalac, tekstPoruke, datumSlanja, vremeSlanja);
		this.grupa = grupa;
	}
	

	public GrupnaPoruka() {
	}


	public int getIdGrupe() {
		return idGrupe;
	}

	public void setIdGrupe(int idGrupe) {
		this.idGrupe = idGrupe;
	}

	public Grupa getGrupa() {
		return grupa;
	}

	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

	@Override
	public String toString() {
		return getPosiljalac().getKorisnickoIme()+" ("+getDatumSlanja()+"/"+getVremeSlanja()+"): \n >>"+getTekstPoruke()+"\n\n";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public String vratiNazivTabele() {
		return "grupna_poruka";
	}

	@Override
	public String vratiZaInsert() {
		return "(idProfila,tekstPoruke,idGrupa,datumSlanja,vremeSlanja) VALUES"
				+ " ('"+getPosiljalac().getIdProfila()+"','"+getTekstPoruke()+"','"+getGrupa().getIdGrupa()+"',"
				+ "'"+getDatumSlanja()+"','"+getVremeSlanja()+"')";
	}

	@Override
	public String vratiZaUpdate() {
		return null;
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
				int idPoruka=rs.getInt("idGrupnaPoruka");
				String tekstPoruke=rs.getString("tekstPoruke");
				Date datumSlanja=rs.getDate("datumSlanja");
				Time vremeSlanja=rs.getTime("vremeSlanja");
				GrupnaPoruka gp=new GrupnaPoruka(idPoruka, super.getPosiljalac(), grupa, tekstPoruke, datumSlanja, vremeSlanja);
				gp.setIdProfil(rs.getInt("idProfila"));
				gp.setIdGrupe(rs.getInt("idGrupa"));
				list.add(gp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public GrupnaPoruka vratiGrupnuPoruku(ResultSet rs) {
		GrupnaPoruka gp=null;
		try {
			int idPoruka=rs.getInt("idGrupnaPoruka");
			String tekstPoruke=rs.getString("tekstPoruke");
			Date datumSlanja=rs.getDate("datumSlanja");
			Time vremeSlanja=rs.getTime("vremeSlanja");
			gp=new GrupnaPoruka(idPoruka, super.getPosiljalac(), grupa, tekstPoruke, datumSlanja, vremeSlanja);
			gp.setIdProfil(rs.getInt("idProfila"));
			gp.setIdGrupe(rs.getInt("idGrupa"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return gp;
	}
	
}
