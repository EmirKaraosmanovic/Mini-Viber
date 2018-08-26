package com.comtrade.domen;

import java.io.Serializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Profil implements OpstiDomen, Serializable{
	
	private int idProfila, status, vidljivost;
	private String korisnickoIme, email, sifra, ime, prezime, pol, zanimanje, gradStanovanja, drzava,oMeni, slikaProfila;
	private Date datumRodjenja, datumPrijave;
	
	public Profil( int idProfila,int status, int vidljivost, String korisnickoIme, String email, String sifra,
			String ime, String prezime, String pol, String zanimanje, String gradStanovanja, String drzava, String oMeni,
			String slikaProfila, Date datumRodjenja, Date datumPrijave) {
		super();
		this.idProfila=idProfila;
		this.status = status;
		this.vidljivost = vidljivost;
		this.korisnickoIme = korisnickoIme;
		this.email = email;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.zanimanje = zanimanje;
		this.gradStanovanja = gradStanovanja;
		this.drzava = drzava;
		this.oMeni = oMeni;
		this.slikaProfila = slikaProfila;
		this.datumRodjenja = datumRodjenja;
		this.datumPrijave = datumPrijave;
	}

	public Profil() {};
	

	public int getVidljivost() {
		return vidljivost;
	}

	public void setVidljivost(int vidljivost) {
		this.vidljivost = vidljivost;
	}

	public String getGradStanovanja() {
		return gradStanovanja;
	}

	public void setGradStanovanja(String grad) {
		this.gradStanovanja = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getoMeni() {
		return oMeni;
	}

	public void setoMeni(String oMeni) {
		this.oMeni = oMeni;
	}

	public String getSlikaProfila() {
		return slikaProfila;
	}

	public void setSlikaProfila(String slikaProfila) {
		this.slikaProfila = slikaProfila;
	}

	public int getIdProfila() {
		return idProfila;
	}
	public void setIdProfila(int idProfila) {
		this.idProfila = idProfila;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getZanimanje() {
		return zanimanje;
	}
	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Date getDatumPrijave() {
		return datumPrijave;
	}
	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}
	@Override
	public String toString() {
		return "(" + getKorisnickoIme() + ") " + getIme() + " " + getPrezime();
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public String vratiNazivTabele() {
		
		return "profil";
	}

	@Override
	public String vratiZaInsert() {
		
		return " (korisnickoIme, email, sifra, ime, prezime, pol, zanimanje, datumRodjenja, datumPrijave, gradStanovanja, drzava, oMeni, statusProfila,"
				+ " vidljivost, slikaProfila) VALUES ('"+getKorisnickoIme()+"','"+getEmail()+"','"+getSifra()+"','"+getIme()+"','"+getPrezime()+"'"
				+ ",'"+getPol()+"','"+getZanimanje()+"','"+getDatumRodjenja()+"','"+getDatumPrijave()+"','"+getGradStanovanja()+"','"+getDrzava()+"'"
				+ ",'"+getoMeni()+"',"+getStatus()+","+getVidljivost()+",'"+getSlikaProfila()+"')";
	}
	@Override
	public String vratiZaUpdate() {
		
		return " sifra='"+getSifra()+"',ime='"+getIme()+"',prezime='"+getPrezime()+"',pol='"+getPol()+"',zanimanje='"+getZanimanje()+"',"
				+ "datumRodjenja='"+getDatumRodjenja()+"' ,gradStanovanja='"+getGradStanovanja()+"', drzava='"+getDrzava()+"',"
						+ "oMeni='"+getoMeni()+"',statusProfila="+getStatus()+",vidljivost="+getVidljivost()+", slikaProfila='"+getSlikaProfila()+"'";
	}
	@Override
	public String vratiZaDelete() {
		
		return null;
	}
	@Override
	public List<OpstiDomen> izvrsiSelect(ResultSet rs) {
		List<OpstiDomen> lista=new ArrayList<>();
		try {
			while(rs.next()) {
				int idProfil=rs.getInt("idProfil");
				String korIme=rs.getString("korisnickoIme");
				String email=rs.getString("email");
				String sifra=rs.getString("sifra");
				String ime=rs.getString("ime");
				String prezime=rs.getString("prezime");
				String pol=rs.getString("pol");
				String zanimanje=rs.getString("zanimanje");
				Date datumRodjenja=rs.getDate("datumRodjenja");
				Date datumPrijave=rs.getDate("datumPrijave");
				String gradStanovanja=rs.getString("gradStanovanja");
				String drzava=rs.getString("drzava");
				String oMeni=rs.getString("oMeni");
				int status=rs.getInt("statusProfila");
				int vidljivost=rs.getInt("vidljivost");
				String slikaProfila=rs.getString("slikaProfila");
				Profil p=new Profil(idProfil,status, vidljivost, korIme, email, sifra, ime, prezime, pol, zanimanje, gradStanovanja, drzava, oMeni, slikaProfila, datumRodjenja, datumPrijave);
				lista.add(p);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public OpstiDomen vratiProfil(ResultSet rs) {
		Profil profil=new Profil();
		try {
				int idProfil=rs.getInt("idProfil");
				String korIme=rs.getString("korisnickoIme");
				String email=rs.getString("email");
				String sifra=rs.getString("sifra");
				String ime=rs.getString("ime");
				String prezime=rs.getString("prezime");
				String pol=rs.getString("pol");
				String zanimanje=rs.getString("zanimanje");
				Date datumRodjenja=rs.getDate("datumRodjenja");
				Date datumPrijave=rs.getDate("datumPrijave");
				String gradStanovanja=rs.getString("gradStanovanja");
				String drzava=rs.getString("drzava");
				String oMeni=rs.getString("oMeni");
				int status=rs.getInt("statusProfila");
				int vidljivost=rs.getInt("vidljivost");
				String slikaProfila=rs.getString("slikaProfila");
				profil=new Profil(idProfil,status, vidljivost, korIme, email, sifra, ime, prezime, pol, zanimanje, gradStanovanja, drzava, oMeni, slikaProfila, datumRodjenja, datumPrijave);
				return profil;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		
}
