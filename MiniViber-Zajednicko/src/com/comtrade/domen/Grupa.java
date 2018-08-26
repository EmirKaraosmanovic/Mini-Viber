package com.comtrade.domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.konstante.Konstante;

@SuppressWarnings("serial")
public class Grupa implements Serializable, OpstiDomen, Konstante{
	
	private int idGrupa;
	private String nazivGrupe;
	private Date datumKreiranjaGrupe;
	private String opisGrupe;
	private Profil kreatorGrupe;
	private String slikaGrupe;
	public Grupa(int idGrupa, String nazivGrupe, Date datumKreiranjaGrupe, String opisGrupe, Profil kreatorGrupe,String slikaGrupe) {
		super();
		this.idGrupa = idGrupa;
		this.nazivGrupe = nazivGrupe;
		this.datumKreiranjaGrupe = datumKreiranjaGrupe;
		this.opisGrupe = opisGrupe;
		this.kreatorGrupe = kreatorGrupe;
		this.slikaGrupe=slikaGrupe;
	}
	

	public Grupa() {
	}


	public String getSlikaGrupe() {
		return slikaGrupe;
	}

	public void setSlikaGrupe(String slikaGrupe) {
		this.slikaGrupe = slikaGrupe;
	}

	public int getIdGrupa() {
		return idGrupa;
	}
	public void setIdGrupa(int idGrupa) {
		this.idGrupa = idGrupa;
	}
	public String getNazivGrupe() {
		return nazivGrupe;
	}
	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}
	public Date getDatumKreiranjaGrupe() {
		return datumKreiranjaGrupe;
	}
	public void setDatumKreiranjaGrupe(Date datumKreiranjaGrupe) {
		this.datumKreiranjaGrupe = datumKreiranjaGrupe;
	}
	public String getOpisGrupe() {
		return opisGrupe;
	}
	public void setOpisGrupe(String opisGrupe) {
		this.opisGrupe = opisGrupe;
	}
	public Profil getKreatorGrupe() {
		return kreatorGrupe;
	}
	public void setKreatorGrupe(Profil kreatorGrupe) {
		this.kreatorGrupe = kreatorGrupe;
	}

	
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public String toString() {
		return "Grupa '"+ getNazivGrupe()+"'";
	}


	@Override
	public String vratiNazivTabele() {
		
		return "grupa";
	}
	@Override
	public String vratiZaInsert() {
		
		return "(nazivGrupe,datumKreiranjaGrupe,opisGrupe,slikaGrupe) VALUES "
				+ "('"+getNazivGrupe()+"','"+getDatumKreiranjaGrupe()+"','"+getOpisGrupe()+"','"+getSlikaGrupe()+"')";
	}
	@Override
	public String vratiZaUpdate() {
		
		return " opisGrupe='"+getOpisGrupe()+"', slikaGrupe='"+getSlikaGrupe()+"'";
	}
	@Override
	public String vratiZaDelete() {
		
		return null;
	}
	@Override
	public List<OpstiDomen> izvrsiSelect(ResultSet rs) {
		List<OpstiDomen> listaGrupa=new ArrayList<>();
		try {
			while(rs.next()){
				int idGrupa= rs.getInt("idGrupa");
				String nazivGrupe=rs.getString("nazivGrupe");
				Date datumKreiranja=rs.getDate("datumKreiranjaGrupe");
				String opisGrupe=rs.getString("opisGrupe");
				String slikaGrupe=rs.getString("slikaGrupe");
				
				Grupa g=new Grupa(idGrupa, nazivGrupe, datumKreiranja, opisGrupe, kreatorGrupe,slikaGrupe);
				listaGrupa.add(g);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaGrupa;
	}
	
	public Grupa vratiGrupu(ResultSet rs) {
		Grupa gr = null;

		try {
			int idGrupa = rs.getInt("idGrupa");
			String nazivGrupe=rs.getString("nazivGrupe");
			Date datumKreiranja=rs.getDate("datumKreiranjaGrupe");
			String opisGrupe=rs.getString("opisGrupe");
			String slikaGrupe=rs.getString("slikaGrupe");
					
			gr=new Grupa(idGrupa, nazivGrupe, datumKreiranja, opisGrupe, kreatorGrupe,slikaGrupe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return gr;
	}
	

}
