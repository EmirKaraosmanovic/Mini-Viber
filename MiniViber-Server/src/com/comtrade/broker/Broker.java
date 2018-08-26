package com.comtrade.broker;


import java.sql.*;


import java.util.*;
import java.util.Map.Entry;

import com.comtrade.domen.*;
import com.comtrade.konstante.Konstante;
import com.comtrade.so.*;

@SuppressWarnings({"unused","unchecked"})
public class Broker implements Konstante{
	
	private static Broker instanca;
	private Connection kon;
	
	private Broker() {
		
		ucitajDrajver();
		
	}
	
	public static Broker vratiInstancu() {
		
		if(instanca==null)
			instanca=new Broker();
		return instanca;
		
	}
	
	private void ucitajDrajver() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void pokreniTransakciju() {
		
		try {
			kon=DriverManager.getConnection("jdbc:mysql://localhost/miniviber", "root", "");
			kon.setAutoCommit(false);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public void potvrdiTransakciju() {
		
		try {
			kon.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void ponistiKonekciju() {
		
		try {
			kon.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void zatvoriKonekciju() {
		
		try {
			kon.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<OpstiDomen> vratiSveProfile(OpstiDomen od) {
		
		List<OpstiDomen> lista=new ArrayList<>();
		String upitSelect = "SELECT * FROM "+od.vratiNazivTabele();
		Statement s;
		ResultSet rs;
		
		try {
			s=kon.createStatement();
			rs=s.executeQuery(upitSelect);
			lista=od.izvrsiSelect(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public void upisiProfil(Object obj) {
		Map<String, Object> map=(Map<String, Object>) obj;
		
		OpstiDomen od = (Profil) map.get("profil");
		Profil profil = (Profil) map.get("profil");
		Grupa gr=new Grupa();
		ClanGrupe clanGrupe=new ClanGrupe();
		String upisiPodatke="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiZaUpdate()+" WHERE "+od.vratiNazivTabele()+".korisnickoIme='"+profil.getKorisnickoIme()+"'";
		String vratiProfil="SELECT * FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".korisnickoIme='"+profil.getKorisnickoIme()+"'";
		try {
			PreparedStatement ps=kon.prepareStatement(upisiPodatke);
			ps.execute();
			
			Statement st;
			ResultSet rs;
			st=kon.createStatement();
			rs=st.executeQuery(vratiProfil);
			if(rs.next()) {
				Map<String, Object> kontakti=new HashMap<>();
				kontakti.put("prijatelji", new ArrayList<>());
				kontakti.put("poslatiZahtevi", new ArrayList<>());
				kontakti.put("primljeniZahtevi", new ArrayList<>());
				kontakti.put("blokiraniKontakti", new ArrayList<>());
				map.put("upisanProfil", (Profil) profil.vratiProfil(rs));
				map.put("kontakti", kontakti);
				map.put("sviProfili", vratiSveProfile(od));
				
				// uzimanje svih grupa
				
				Map<String, Object> grupe=new HashMap<>();
				Map<String, Object> sveGrupe=new HashMap<>();
				
				String upitZaGrupe="SELECT * FROM grupa INNER JOIN clanovi_grupe on grupa.idGrupa=clanovi_grupe.idGrupe "
						+ "INNER JOIN profil ON profil.idProfil=clanovi_grupe.idProfila";
				Statement stGrupe=kon.createStatement();
				ResultSet rsGrupe=stGrupe.executeQuery(upitZaGrupe);
				
				while(rsGrupe.next()) {
					Profil pr=(Profil) profil.vratiProfil(rsGrupe);
					Grupa grupa=gr.vratiGrupu(rsGrupe);
					ClanGrupe cg=clanGrupe.vratiClanaGrupe(rsGrupe);
					cg.setClan(pr);
					cg.setGrupa(grupa);
					Map<String, Object> podaciZaGrupu=(Map<String, Object>) sveGrupe.get(grupa.getNazivGrupe());
					if(podaciZaGrupu==null) {
						sveGrupe.put(grupa.getNazivGrupe(), new HashMap<>());
						podaciZaGrupu=(Map<String, Object>) sveGrupe.get(grupa.getNazivGrupe());
						podaciZaGrupu.put("grupa", grupa);
						podaciZaGrupu.put("clanoviGrupe", new ArrayList<>());
						grupa=(Grupa) podaciZaGrupu.get("grupa");
						if(cg.getAdminStatus()==ADMIN_GRUPE) {
							grupa.setKreatorGrupe(pr);
						}
						List<ClanGrupe> clanovi=(List<ClanGrupe>) podaciZaGrupu.get("clanoviGrupe");
						clanovi.add(cg);
						
					}else {
						grupa=(Grupa) podaciZaGrupu.get("grupa");
						if(cg.getAdminStatus()==ADMIN_GRUPE) {
							grupa.setKreatorGrupe(pr);
						}
						List<ClanGrupe> clanovi=(List<ClanGrupe>) podaciZaGrupu.get("clanoviGrupe");
						clanovi.add(cg);
					}
					
				}
				grupe.put("sveGrupe", sveGrupe);
				map.put("grupe", grupe);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void prijavaProfila(Object obj) {// ovu metodu moram da izdelim!!!
		OpstiDomen odProfil=new Profil();
		Kontakti k=new Kontakti();
		Grupa gr=new Grupa();
		ClanGrupe clanGrupe=new ClanGrupe();
		Map<String, Object> mapaPodaciProfila=(Map<String, Object>) obj;
		Profil prijavaProfil=(Profil) mapaPodaciProfila.get("prijavaProfil");
		String vratiProfil="SELECT * FROM "+odProfil.vratiNazivTabele()+" WHERE "+odProfil.vratiNazivTabele()+".korisnickoIme='"+prijavaProfil.getKorisnickoIme()+"' AND "+odProfil.vratiNazivTabele()+".sifra='"+prijavaProfil.getSifra()+"'";
		
		try {
			PreparedStatement ps=kon.prepareStatement(vratiProfil);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				prijavaProfil=(Profil) prijavaProfil.vratiProfil(rs);
			}
			if(prijavaProfil.getIdProfila()!=0) {
				mapaPodaciProfila.put("upisanProfil", prijavaProfil);
				List<OpstiDomen> sviProfili=vratiSveProfile(odProfil);
				
				// odavde ide uzimanje kontakata
				
				Map<String, Object> kontakti=new HashMap<>();
				List<Profil> listaPrijatelja=new ArrayList<>();
				List<ZahtevZaPrijateljstvo> poslatiZatevi=new ArrayList<>();;
				List<ZahtevZaPrijateljstvo> primljniZahtevi=new ArrayList<>();;
				List<Profil> blokiraniKontakti=new ArrayList<>();
				String vratiPodatkeProfila="SELECT * FROM profil INNER JOIN kontakti "
						+ "ON profil.idProfil=kontakti.idProfilPrvi or profil.idProfil=kontakti.idProfilDrugi "
						+ "WHERE (kontakti.idProfilPrvi='"+prijavaProfil.getIdProfila()+"' or kontakti.idProfilDrugi='"+prijavaProfil.getIdProfila()+"') "
								+ "and profil.idProfil!='"+prijavaProfil.getIdProfila()+"'";
				ps=kon.prepareStatement(vratiPodatkeProfila);
				rs=ps.executeQuery();
				while(rs.next()) {
					Kontakti kontakt=k.vratiKontakt(rs);
					if(kontakt.getStatusPrijateljstva()==PRIJATELJI) {
						listaPrijatelja.add((Profil) prijavaProfil.vratiProfil(rs));
					}else if(kontakt.getStatusPrijateljstva()==POSLAT_ZAHTEV) {
						if(kontakt.getIdProfilAkcija()==prijavaProfil.getIdProfila()) {
							poslatiZatevi.add(new ZahtevZaPrijateljstvo(prijavaProfil, (Profil) prijavaProfil.vratiProfil(rs)));
						}else {
							primljniZahtevi.add(new ZahtevZaPrijateljstvo((Profil) prijavaProfil.vratiProfil(rs), prijavaProfil));
						}
					}else if(kontakt.getStatusPrijateljstva()==BLOKIRAN) {
						if(kontakt.getIdProfilAkcija()==prijavaProfil.getIdProfila()) {
							blokiraniKontakti.add((Profil) prijavaProfil.vratiProfil(rs));
						}else {
							ukloniIzListeSvih((Profil)prijavaProfil.vratiProfil(rs),sviProfili);
							
						}
					}
				}
				kontakti.put("prijatelji", listaPrijatelja);
				kontakti.put("poslatiZahtevi", poslatiZatevi);
				kontakti.put("primljeniZahtevi", primljniZahtevi);
				kontakti.put("blokiraniKontakti", blokiraniKontakti);
				mapaPodaciProfila.put("kontakti", kontakti);
				mapaPodaciProfila.put("sviProfili", sviProfili);
				
				// odavde ide uzimanje grupa i njihovoh clanova
				
				Map<String, Object> grupe=new HashMap<>();
				Map<String, Object> sveGrupe=new HashMap<>();
				List<Grupa> mojeGrupe=new ArrayList<>();
				List<ClanGrupe> poslatiZahteviZaGrupu=new ArrayList<>();
				List<ClanGrupe> primljeniZahteviZaGrupu=new ArrayList<>();
				
				String upitZaGrupe="SELECT * FROM grupa INNER JOIN clanovi_grupe on grupa.idGrupa=clanovi_grupe.idGrupe "
						+ "INNER JOIN profil ON profil.idProfil=clanovi_grupe.idProfila";
				
				Statement stGrupe=kon.createStatement();
				ResultSet rsGrupe=stGrupe.executeQuery(upitZaGrupe);
				
				while(rsGrupe.next()) {
					Profil pr=(Profil) prijavaProfil.vratiProfil(rsGrupe);
					Grupa grupa=gr.vratiGrupu(rsGrupe);
					ClanGrupe cg=clanGrupe.vratiClanaGrupe(rsGrupe);
					cg.setClan(pr);
					cg.setGrupa(grupa);
					Map<String, Object> podaciZaGrupu=(Map<String, Object>) sveGrupe.get(grupa.getNazivGrupe());
					if(podaciZaGrupu==null) {
						sveGrupe.put(grupa.getNazivGrupe(), new HashMap<>());
						podaciZaGrupu=(Map<String, Object>) sveGrupe.get(grupa.getNazivGrupe());
						podaciZaGrupu.put("grupa", grupa);
						podaciZaGrupu.put("clanoviGrupe", new ArrayList<>());
						grupa=(Grupa) podaciZaGrupu.get("grupa");
						if(cg.getAdminStatus()==ADMIN_GRUPE) {
							grupa.setKreatorGrupe(pr);
						}
						List<ClanGrupe> clanovi=(List<ClanGrupe>) podaciZaGrupu.get("clanoviGrupe");
						clanovi.add(cg);
						
					}else {
						grupa=(Grupa) podaciZaGrupu.get("grupa");
						if(cg.getAdminStatus()==ADMIN_GRUPE) {
							grupa.setKreatorGrupe(pr);
						}
						List<ClanGrupe> clanovi=(List<ClanGrupe>) podaciZaGrupu.get("clanoviGrupe");
						clanovi.add(cg);
					}
					if(pr.getKorisnickoIme().equals(prijavaProfil.getKorisnickoIme())) {
						Grupa grup=(Grupa) podaciZaGrupu.get("grupa");
						if(cg.getStatusClana()==CLAN_GRUPE) {
							mojeGrupe.add(grup);
						}else if(cg.getStatusClana()==ZAHTEV_ZA_GRUPU) {
							poslatiZahteviZaGrupu.add(cg);
						}
						
					}
					
					// uzeli smo sve grupe
					
				}
				
				grupe.put("sveGrupe", sveGrupe);
				grupe.put("mojeGrupe", mojeGrupe);
				grupe.put("poslatiZahtevi", poslatiZahteviZaGrupu);
				mapaPodaciProfila.put("grupe", grupe);
				
				
			}else {
				mapaPodaciProfila.put("upisanProfil", null);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ukloniIzListeSvih(Profil profil, List<OpstiDomen> lisataOd) {
		List<Profil> zaBrisanje=new ArrayList<>();
		for(OpstiDomen od:lisataOd) {
			Profil pr=(Profil) od;
			if(pr.getIdProfila()==profil.getIdProfila()) {
				zaBrisanje.add(pr);
				break;
			}
		}
		lisataOd.removeAll(zaBrisanje);
	}

	public void proveraPodataka(OpstiDomen od) {
		ResultSet rs = null;
		Statement st = null;
		Profil profil= (Profil) od;
		String upitProvera="SELECT * FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".korisnickoIme=? OR "+od.vratiNazivTabele()+".email=?";
		String upisiKontakt="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		String vratiProfil="SELECT * FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".korisnickoIme='"+profil.getKorisnickoIme()+"'";
		PreparedStatement psProvera;
		ResultSet rsProvera;
		try {
			psProvera = kon.prepareStatement(upitProvera);
			psProvera.setString(1, profil.getKorisnickoIme());
			psProvera.setString(2, profil.getEmail());
			rsProvera=psProvera.executeQuery();
			
			if(!rsProvera.next()) {
				PreparedStatement ps=kon.prepareStatement(upisiKontakt);
				ps.execute();
				
				 st=kon.createStatement();
				 rs=st.executeQuery(vratiProfil);
				if(rs.next()) {
					Profil p=(Profil) profil.vratiProfil(rs);
					profil.setIdProfila(p.getIdProfila());
				}
				
			}
			
				
		} catch (Exception e1) {
				e1.printStackTrace();
		}
			
	}

	public void upisiKontakt(OpstiDomen od) {
		Kontakti k=(Kontakti) od;
		String upisiKontakt="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		try {
			PreparedStatement ps=kon.prepareStatement(upisiKontakt);
			ps.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void prihvatiPrijateljstvo(OpstiDomen od) {
		
		Kontakti k=(Kontakti) od;
		String upisiKontakt="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiZaUpdate()+" WHERE "+od.vratiNazivTabele()+".idProfilPrvi=? AND "+od.vratiNazivTabele()+".idProfilDrugi=?";
		try {
			PreparedStatement ps=kon.prepareStatement(upisiKontakt);
			ps.setInt(1, k.getIdProfilPrvi());
			ps.setInt(2, k.getIdProfilDrugi());
			ps.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void izbrisiKontakt(OpstiDomen od) {
		Kontakti kontakt=(Kontakti) od;
		String izbrisiKontak="DELETE FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".idProfilPrvi='"+kontakt.getIdProfilPrvi()+"'"
				+ " AND "+od.vratiNazivTabele()+".idProfildrugi='"+kontakt.getIdProfilDrugi()+"'";
		
		try {
			PreparedStatement ps=kon.prepareStatement(izbrisiKontak);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void blokirajKontakt(OpstiDomen od) {
		Kontakti k=(Kontakti) od;
		String upisiKontakt="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiZaUpdate()+" WHERE "+od.vratiNazivTabele()+".idProfilPrvi=? AND "+od.vratiNazivTabele()+".idProfilDrugi=?";
		try {
			PreparedStatement ps=kon.prepareStatement(upisiKontakt);
			ps.setInt(1, k.getIdProfilPrvi());
			ps.setInt(2, k.getIdProfilDrugi());
			ps.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void odblokirajKontakt(OpstiDomen od) {
		Kontakti k=(Kontakti) od;
		String upisiKontakt="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiZaUpdate()+" WHERE "+od.vratiNazivTabele()+".idProfilPrvi=? AND "+od.vratiNazivTabele()+".idProfilDrugi=?";
		try {
			PreparedStatement ps=kon.prepareStatement(upisiKontakt);
			ps.setInt(1, k.getIdProfilPrvi());
			ps.setInt(2, k.getIdProfilDrugi());
			ps.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void upisiPoruku(OpstiDomen od) {
		String upisiPoruku="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		
		try {
			PreparedStatement ps=kon.prepareStatement(upisiPoruku);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void uzmiPoruke(OpstiDomen odPrivatnaPoruka, OpstiDomen odGrupnaPoruka, Map<String, Object> mojiPodaci) {
		Profil mojProfil=(Profil) mojiPodaci.get("mojProfil");
		GrupnaPoruka gp=(GrupnaPoruka) odGrupnaPoruka;
		PrivatnaPoruka poruka=new PrivatnaPoruka();
		
		Map<String, Object> poruke=new HashMap<>();
		Map<String, Object> privatnePoruke=new HashMap<>();
		String uzmiPoruke="SELECT * FROM "+odPrivatnaPoruka.vratiNazivTabele()+" INNER JOIN profil ON "
				+ "profil.idProfil=privatna_poruka.idProfila or profil.idProfil=privatna_poruka.idPrijatelja" + 
				" WHERE (privatna_poruka.idProfila="+mojProfil.getIdProfila()+" or privatna_poruka.idPrijatelja="+mojProfil.getIdProfila()+")"
				+ " AND profil.korisnickoIme!='"+mojProfil.getKorisnickoIme()+"'";
		try {
			Statement st=kon.createStatement();
			ResultSet rs=st.executeQuery(uzmiPoruke);
			
			while(rs.next()) {
				Profil prijatelj=(Profil) mojProfil.vratiProfil(rs);
				PrivatnaPoruka pp=poruka.vratiPoruku(rs);
				if(pp.getIdProfil()==mojProfil.getIdProfila()) {// postavljanje profila posiljaoca i primaoca
					pp.setPosiljalac(mojProfil);
					pp.setPrimalac(prijatelj);
				}else {
					pp.setPosiljalac(prijatelj);
					pp.setPrimalac(mojProfil);
				}
				Map<String, List<PrivatnaPoruka>> dopisivanje=(Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(prijatelj.getKorisnickoIme());
				if(dopisivanje==null){
					privatnePoruke.put(prijatelj.getKorisnickoIme(), new HashMap<>());
					dopisivanje=(Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(prijatelj.getKorisnickoIme());
				}
				if(pp.getStatusPoruke()==PORUKA_JE_PROCITANA) {
					try {
						dopisivanje.get("procitanePoruke").add(pp);
					} catch (Exception e) {
						dopisivanje.put("procitanePoruke", new ArrayList<>());
						dopisivanje.get("procitanePoruke").add(pp);
					}
				}else if(pp.getStatusPoruke()==PORUKA_NIJE_PROCITANA && pp.getIdProfil()==mojProfil.getIdProfila()) {
					try {
						dopisivanje.get("procitanePoruke").add(pp);
					} catch (Exception e) {
						dopisivanje.put("procitanePoruke", new ArrayList<>());
						dopisivanje.get("procitanePoruke").add(pp);
					}
					
				}else if(pp.getStatusPoruke()==PORUKA_NIJE_PROCITANA && pp.getIdProfil()!=mojProfil.getIdProfila()) {
					try {
						dopisivanje.get("neprocitanePoruke").add(pp);
					} catch (Exception e) {
						dopisivanje.put("neprocitanePoruke", new ArrayList<>());
						dopisivanje.get("neprocitanePoruke").add(pp);
					}
					
				}
				
			}
			poruke.put("privatnePoruke", privatnePoruke);
			
			
			// sad uzimam grupne poruke za sve moje grupe
			
			Map<String, List<GrupnaPoruka>> grupnePoruke=new  HashMap<>();
			Map<Integer, Integer> brojProcitanihGrupnihPoruka=new  HashMap<>();
			List<Grupa> mojeGrupe=(List<Grupa>) mojiPodaci.get("mojeGrupe");
			if(mojeGrupe!=null) {
				for(Grupa grupa:mojeGrupe) {
					grupnePoruke.put(grupa.getNazivGrupe(), new ArrayList<>());
					String upit="SELECT * FROM "+odGrupnaPoruka.vratiNazivTabele()+" INNER JOIN profil "
							+ "ON profil.idProfil=grupna_poruka.idProfila WHERE grupna_poruka.idGrupa="+grupa.getIdGrupa()+"";
					PreparedStatement ps=kon.prepareStatement(upit);
					ResultSet result=ps.executeQuery();
					while (result.next()) {
						Profil p=(Profil) mojProfil.vratiProfil(result);
						GrupnaPoruka grupnaPoruka=gp.vratiGrupnuPoruku(result);
						grupnaPoruka.setGrupa(grupa);
						grupnaPoruka.setPosiljalac(p);
						grupnePoruke.get(grupa.getNazivGrupe()).add(grupnaPoruka);
					}
					
				}
				String upit="SELECT clanovi_grupe.brojProcitanihPoruka,grupa.idGrupa FROM clanovi_grupe "
						+ "INNER JOIN grupa ON grupa.idGrupa=clanovi_grupe.idGrupe WHERE clanovi_grupe.idProfila="+mojProfil.getIdProfila()+"";
				
				PreparedStatement ps=kon.prepareStatement(upit);
				ResultSet result=ps.executeQuery();
				while (result.next()) {
					int idGrupe=result.getInt("idGrupa");
					int brojProcitanihPoruka=result.getInt("brojProcitanihPoruka");
					brojProcitanihGrupnihPoruka.put(idGrupe, brojProcitanihPoruka);
				}
				
				poruke.put("grupnePoruke", grupnePoruke);
				poruke.put("brojProcitanihPoruka", brojProcitanihGrupnihPoruka);
				mojiPodaci.put("poruke", poruke);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void promeniStatusPrivatnihPoruka(OpstiDomen od, List<PrivatnaPoruka> poruke) {
		
		for(PrivatnaPoruka pp:poruke) {
			String promenaStatusa="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiZaUpdate()+
					" WHERE "+od.vratiNazivTabele()+".idPrivatnaPoruka="+pp.getIdPoruke()+"";
			try {
				PreparedStatement ps=kon.prepareStatement(promenaStatusa);
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void upisiPrivatniRazgovor(List<PrivatnaPoruka> poruke) {
		
		for(PrivatnaPoruka pp:poruke) {
			upisiPoruku(pp);
		}
		
	}

	public void upisiGrupu(OpstiDomen od) {
		
		Statement st=null;
		ResultSet rs=null;
		Grupa novaGrupa=(Grupa) od;
		String upitProvera="SELECT * FROM "+od.vratiNazivTabele()+" WHERE grupa.nazivGrupe='"+novaGrupa.getNazivGrupe()+"'";
		String upitUpisGrupe="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		
		try {
			Statement stProvera=kon.createStatement();
			ResultSet rsProvera=stProvera.executeQuery(upitProvera);
			if(!rsProvera.next()) {
				
				PreparedStatement ps=kon.prepareStatement(upitUpisGrupe);
				ps.execute();
				
				st=kon.createStatement();
				rs=st.executeQuery(upitProvera);
				
				if(rs.next()) {
					int idGrupa=rs.getInt("idGrupa");
					novaGrupa.setIdGrupa(idGrupa);
					OpstiDomen odClan=new ClanGrupe(novaGrupa.getKreatorGrupe(), novaGrupa,CLAN_GRUPE, ADMIN_GRUPE, 0);
					String upisiClanaGrupe="INSERT INTO "+odClan.vratiNazivTabele()+" "+odClan.vratiZaInsert();
					PreparedStatement psClan=kon.prepareStatement(upisiClanaGrupe);
					psClan.execute();
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void izbrisiClanaGrupe(OpstiDomen od) {
		ClanGrupe cg=(ClanGrupe) od;
		String izbrisiClana="DELETE FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".idProfila="+cg.getClan().getIdProfila()+""
				+ " AND "+od.vratiNazivTabele()+".idGrupe="+cg.getGrupa().getIdGrupa()+"";
		
		try {
			PreparedStatement ps = kon.prepareStatement(izbrisiClana);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void dodajClanaGrupe(OpstiDomen od) {
		ClanGrupe cg=(ClanGrupe) od;
		String upisiClanaGrupe="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();

		try {
			PreparedStatement ps = kon.prepareStatement(upisiClanaGrupe);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void promeniStatusClanaGrupe(OpstiDomen od) {
		ClanGrupe cg=(ClanGrupe) od;
		String upisiClanaGrupe="UPDATE "+od.vratiNazivTabele()+" SET "+od.vratiNazivTabele()+".statusClana="+cg.getStatusClana()+" WHERE "
				+ ""+od.vratiNazivTabele()+".idProfila='"+cg.getClan().getIdProfila()+"' AND "
						+ ""+od.vratiNazivTabele()+".idGrupe='"+cg.getGrupa().getIdGrupa()+"'";

		try {
			PreparedStatement ps = kon.prepareStatement(upisiClanaGrupe);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void upisiGrupnuPoruku(OpstiDomen od) {
		GrupnaPoruka gp=(GrupnaPoruka) od;
		
		String upisPoruke= "INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		try {
			PreparedStatement ps = kon.prepareStatement(upisPoruke);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void upisiBrojGrupnihPoruka(Map<String, Object> mapa) {
		
		Map<Integer, Integer> brojGrupnihPoruka=(Map<Integer, Integer>) mapa.get("brojGrupnihPoruka");
		Profil mojProfil=(Profil) mapa.get("profil");
		
		for(Entry<Integer, Integer> map:brojGrupnihPoruka.entrySet()) {
			OpstiDomen od=new ClanGrupe();
			
			String upit="UPDATE "+od.vratiNazivTabele()+" SET "
					+ "brojProcitanihPoruka="+map.getValue()+" WHERE "+od.vratiNazivTabele()+".idGrupe="+map.getKey()+""
							+ " AND "+od.vratiNazivTabele()+".idProfila="+mojProfil.getIdProfila()+" ";
			try {
				PreparedStatement ps=kon.prepareStatement(upit);
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void izmenaProfila(OpstiDomen od) {
		
		Profil izmenaProfila=(Profil) od;
		
		String upitIzmena="UPDATE "+od.vratiNazivTabele()+"  SET "+od.vratiZaUpdate()+" WHERE"
				+ " "+od.vratiNazivTabele()+".idProfil="+izmenaProfila.getIdProfila()+"";
		
		try {
			PreparedStatement psIzmena=kon.prepareStatement(upitIzmena);
			psIzmena.execute();
		
		} catch (Exception e) {
			izmenaProfila.setIdProfila(0);
			e.printStackTrace();
		}
		
	}

	public void izmeniGrupu(OpstiDomen od) {
		Grupa izmenaGrupe=(Grupa) od;
		
		String upitIzmena="UPDATE "+od.vratiNazivTabele()+"  SET "+od.vratiZaUpdate()+" WHERE"
				+ " "+od.vratiNazivTabele()+".idGrupa="+izmenaGrupe.getIdGrupa()+"";
		
		try {
			PreparedStatement psIzmena=kon.prepareStatement(upitIzmena);
			psIzmena.execute();
		
		} catch (Exception e) {
			izmenaGrupe.setIdGrupa(0);
		}
	}

	public void proveraUnetihPodataka(OpstiDomen od) {
		Profil provera=(Profil) od;
		
		String upitProvera="SELECT * FROM "+od.vratiNazivTabele()+" WHERE "+od.vratiNazivTabele()+".email='"+provera.getEmail()+"' AND "
				+od.vratiNazivTabele()+".datumRodjenja='"+provera.getDatumRodjenja()+"' AND "+od.vratiNazivTabele()+".gradStanovanja='"+provera.getGradStanovanja()+"'";
		
		Statement st=null;
		ResultSet rs=null;
		try {
			st=kon.createStatement();
			rs=st.executeQuery(upitProvera);
			if(rs.next()) {
				provera.setIdProfila(rs.getInt("idProfil"));
				provera.setKorisnickoIme(rs.getString("korisnickoIme"));
				provera.setSifra(rs.getString("sifra"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}		
}