package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Kontakti implements OpstiDomen,Serializable{
	
	private int idProfilPrvi, idProfilDrugi, statusPrijateljstva, idProfilAkcija;

	public Kontakti(int idProfilPrvi, int idProfilDrugi, int statusPrijateljstva, int idProfilAkcija) {
		super();
		this.idProfilPrvi = idProfilPrvi;
		this.idProfilDrugi = idProfilDrugi;
		this.statusPrijateljstva = statusPrijateljstva;
		this.idProfilAkcija = idProfilAkcija;
	}
	
	public Kontakti() {
	}
	

	public int getIdProfilPrvi() {
		return idProfilPrvi;
	}


	public void setIdProfilPrvi(int idProfilPrvi) {
		this.idProfilPrvi = idProfilPrvi;
	}


	public int getIdProfilDrugi() {
		return idProfilDrugi;
	}


	public void setIdProfilDrugi(int idProfilDrugi) {
		this.idProfilDrugi = idProfilDrugi;
	}


	public int getStatusPrijateljstva() {
		return statusPrijateljstva;
	}

	public void setStatusPrijateljstva(int statusPrijateljstva) {
		this.statusPrijateljstva = statusPrijateljstva;
	}

	public int getIdProfilAkcija() {
		return idProfilAkcija;
	}

	public void setIdProfilAkcija(int idProfilAkcija) {
		this.idProfilAkcija = idProfilAkcija;
	}
	
	/////////////////////////////////////////////////////////////////

	@Override
	public String vratiNazivTabele() {
		return "kontakti";
	}

	@Override
	public String vratiZaInsert() {
		
		return "VALUES ("+getIdProfilPrvi()+","+getIdProfilDrugi()+","+getStatusPrijateljstva()+","+getIdProfilAkcija()+")";
	}

	@Override
	public String vratiZaUpdate() {
		
		return "statusPrijateljstva="+getStatusPrijateljstva()+", idProfilAkcija="+getIdProfilAkcija()+"";
	}

	@Override
	public String vratiZaDelete() {
		
		return null;
	}

	@Override
	public List<OpstiDomen> izvrsiSelect(ResultSet rs) {
		List<OpstiDomen> kontakti=new ArrayList<>();
		try {
			while (rs.next()) {
				int idProfilPrvi=rs.getInt("idProfilPrvi");
				int idProfilDrugi=rs.getInt("idProfilDrugi");
				int statusPrijateljstva=rs.getInt("statusPrijateljstva");
				int idProfilAkcija=rs.getInt("idProfilAkcija");
				Kontakti k=new Kontakti(idProfilPrvi, idProfilDrugi, statusPrijateljstva, idProfilAkcija);
				kontakti.add(k);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return kontakti;
	}
	public Kontakti vratiKontakt(ResultSet rs) {
		Kontakti k=new Kontakti();
		try {
			int idProfilPrvi=rs.getInt("idProfilPrvi");
			int idProfilDrugi=rs.getInt("idProfilDrugi");
			int statusPrijateljstva=rs.getInt("statusPrijateljstva");
			int idProfilAkcija=rs.getInt("idProfilAkcija");
			k=new Kontakti(idProfilPrvi, idProfilDrugi, statusPrijateljstva, idProfilAkcija);

		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return k;
	}
	

}
