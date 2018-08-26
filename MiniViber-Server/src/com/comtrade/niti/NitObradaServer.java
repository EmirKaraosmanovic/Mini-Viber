package com.comtrade.niti;


import java.io.*;



import java.net.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import com.comtrade.domen.Grupa;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerServer.KontrolerServer;
import com.comtrade.transfer.TransferKlasa;

@SuppressWarnings({ "unused", "unchecked" })
public class NitObradaServer extends Thread implements Konstante{
	
	private Socket socket;
	private Profil aktivniProfil=new Profil();

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket s) {
		this.socket = s;
	}
	
	
	public Profil getAktivniProfil() {
		return aktivniProfil;
	}

	public void setAktivniProfil(Profil aktivniProfil) {
		this.aktivniProfil = aktivniProfil;
	}

	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = null;
				try {
					ois=new ObjectInputStream(socket.getInputStream());
					
				} catch (Exception e) {
					KontrolerServer.vratiObjekat().ukloniIzMapeAktivnih(aktivniProfil);
					try {
						KontrolerServer.vratiObjekat().prikaziObavestenje("Korisnik: ("+aktivniProfil.getKorisnickoIme()+socket.getInetAddress()+") se odjavio sa servera!!!\n");
					} catch (Exception e2) {
						KontrolerServer.vratiObjekat().prikaziObavestenje("Neuspesno logovanje sa adrese: "+socket.getInetAddress()+"\n");

					}
					break;
				}
				
				TransferKlasa tk=(TransferKlasa) ois.readObject();
				obradiZahtevOdKlijenta(tk);

			} catch (Exception e) {
				e.printStackTrace();
			break;
			}
		}
	}

	
	private void obradiZahtevOdKlijenta(TransferKlasa tk) {
		TransferKlasa tks=new TransferKlasa();
		List<Profil> listaProfila=new ArrayList<>();
		Profil p=null;
		
		switch (tk.getOperacija()) {
		
		case PRIJAVA:
			
			Map<String, Object> mapaPodaciProfila=new HashMap<>();
			p=(Profil) tk.getKlijentObjekat();
			mapaPodaciProfila.put("prijavaProfil", p);
			KontrolerServer.vratiObjekat().prijavaProfila(mapaPodaciProfila);
			aktivniProfil=(Profil) mapaPodaciProfila.get("upisanProfil");
			
			if(aktivniProfil!=null) { 
				List<Profil> aktivniPrijatelji=KontrolerServer.vratiObjekat().uzmiAktivnePrijatelje(mapaPodaciProfila.get("kontakti"),aktivniProfil);
				mapaPodaciProfila.put("aktivniPrijatelji", aktivniPrijatelji);
				tks.setOperacija(USPESNA_OPERACIJA);
				tks.setServerObjekat_odgovor(mapaPodaciProfila);
				posaljiOdgovor(tks);
				KontrolerServer.vratiObjekat().upisiUMapuAktivnih(this);// upisujem u mapu aktivnih prijavljeni profil
				KontrolerServer.vratiObjekat().prikaziObavestenje("Korisnik: ("+aktivniProfil.getKorisnickoIme()+socket.getInetAddress()+") se povezao na server!!!\n");
				Map<String, Object> grupe=(Map<String, Object>) mapaPodaciProfila.get("grupe");
				Map<String, Object> sveGrupe=(Map<String, Object>) grupe.get("sveGrupe");
				KontrolerServer.vratiObjekat().postaviSveGrupe(sveGrupe);
				KontrolerServer.vratiObjekat().uzmiSlikeIzFajla(aktivniProfil.getKorisnickoIme());
				List<Profil> predlogPrijatelja=KontrolerServer.vratiObjekat().posaljiPredlogPrijatelja(aktivniProfil,(Map<String, Object>) mapaPodaciProfila.get("kontakti"),(List<Profil>) mapaPodaciProfila.get("sviProfili"));
				tk.setOperacija(PREDLOG_ZA_PRIJATELJE);
				tk.setServerObjekat_odgovor(predlogPrijatelja);
				posaljiOdgovor(tk);
				
			}else {
				tks.setServerPoruka_odgovor("Korisnicko ime i lozinka ne odgovaraju ni jednom profilu!!!");
				posaljiOdgovor(tks);
			}
			
			break;
			
		case REGISTRACIJA_PROVERA:
			
			p=(Profil) tk.getKlijentObjekat();
			KontrolerServer.vratiObjekat().proveraPodataka(p);
			
			if(p.getIdProfila()!=0) {
				tks.setOperacija(MOGUCA_REGISTRACIJA);
				tks.setServerObjekat_odgovor(p);
				posaljiOdgovor(tks);
			}else {
				tks.setServerPoruka_odgovor("Korisnicko ime ili email već postoje.");
				posaljiOdgovor(tks);
			}
					
			break;
				
		case REGISTRACIJA_UPIS:
			
			p=(Profil) tk.getKlijentObjekat();
			Map<String, Object> mapRegistracija=new HashMap<>();
			mapRegistracija.put("profil", p);
			KontrolerServer.vratiObjekat().upisiProfil(mapRegistracija);
			aktivniProfil=(Profil) mapRegistracija.get("upisanProfil");
			if(aktivniProfil!=null) {
				tks.setOperacija(USPESNA_OPERACIJA);
				tks.setServerPoruka_odgovor("Profil je kreiran i upisan u bazu.");
				tks.setServerObjekat_odgovor(mapRegistracija);
				posaljiOdgovor(tks);
				
				KontrolerServer.vratiObjekat().upisiUMapuAktivnih(this);// upisujem u mapu aktivnih prijavljeni profil
				tks.setOperacija(OSVEZI_PRETRAGU_PROFILA);
				tks.setServerObjekat_odgovor((List<Profil>)mapRegistracija.get("sviProfili"));
				KontrolerServer.vratiObjekat().obavestiSveAktivne(tks,aktivniProfil);
				
				Map<String, Object> grupe=(Map<String, Object>) mapRegistracija.get("grupe");
				Map<String, Object> sveGrupe=(Map<String, Object>) grupe.get("sveGrupe");
				KontrolerServer.vratiObjekat().postaviSveGrupe(sveGrupe);
				List<Profil> predlogPrijatelja=KontrolerServer.vratiObjekat().posaljiPredlogPrijatelja(aktivniProfil,(Map<String, Object>) mapRegistracija.get("kontakti"),(List<Profil>) mapRegistracija.get("sviProfili"));
				tk.setOperacija(PREDLOG_ZA_PRIJATELJE);
				tk.setServerObjekat_odgovor(predlogPrijatelja);
				posaljiOdgovor(tk);
				
			}else {
				tks.setServerPoruka_odgovor("Profil nije upisan, pokušajte ponovo.");
				posaljiOdgovor(tks);
			}
			
			break;
		
		case PROVERA_PODATAKA:
			
			Profil provera=(Profil) tk.getKlijentObjekat();
			KontrolerServer.vratiObjekat().provriUnetePodatke(provera);
			if(provera.getIdProfila()!=0) {
				tks.setOperacija(USPESNA_OPERACIJA);
				tks.setServerPoruka_odgovor("Uneti podaci su tacni, lozinka je poslata na e-mail: "+provera.getEmail());
			}else {
				tks.setServerPoruka_odgovor("Uneti podaci nisu tacni!!!");
			}
			tks.setServerObjekat_odgovor(provera);
			posaljiOdgovor(tks);
			
			break;
		
		case VRATI_PORUKE:
			
			KontrolerServer.vratiObjekat().vratiPrivatnePoruke(tk);
			
			break;
		
		case ZAHTEV_ZA_PRIJATELJSTVO:
			
			KontrolerServer.vratiObjekat().upisiZahtev(tk);
			
			break;
		
		case ZAHTEV_PRIHVACEN:
			
			KontrolerServer.vratiObjekat().prihvatiZahtev(tk);
			
			break;
		
		case BRISANJE_POSLATOG_ZAHTEVA:
			
			KontrolerServer.vratiObjekat().izbrisiPoslatZahtev(tk);
			
			break;
		
		case BRISANJE_PRIMLJENOG_ZAHTEVA:
			
			KontrolerServer.vratiObjekat().izbrisiPrimljenZahtev(tk);
			
			break;
		
		case IZBRISI_PRIJATELJA:
			
			KontrolerServer.vratiObjekat().izbrisiPrijatelja(tk);
			
			break;
		
		case ZAHTEV_ZA_BLOKIRANJE:
			
			KontrolerServer.vratiObjekat().blokirajKontakt(tk);
			
			break;
		
		case ODBLOKIRAJ_KONTAKT:
			
			KontrolerServer.vratiObjekat().odblokirajKontakt(tk);
			
			break;
		
		case PRIVATNA_PORUKA:
			
			KontrolerServer.vratiObjekat().proslediPoruku(tk);
			
			break;
		
		case PROMENI_STATUS_PRIVATNIH_PORUKA:
			
			KontrolerServer.vratiObjekat().promeniStatusPrivatnihPoruka(tk);
			
			break;
		
		case PROMENI_STATUS_PRIVATNIH_PORUKA_KOD_KORISNIKA:
			
			KontrolerServer.vratiObjekat().promeniStatusKodKorisnika(tk);
			
			break;
		
		case UPISI_RAZGOVORE:
			
			KontrolerServer.vratiObjekat().upisiRazgovore(tk);
			
			break;
		
		case KREIRANJE_GRUPE:
			
			KontrolerServer.vratiObjekat().kreirajGrupu(tk);
			
			break;
		
		case IZBRISI_CLANA_GRUPE:
			
			KontrolerServer.vratiObjekat().izbrisiClanaGrupe(tk);
			
			break;
		
		case OSVEZI_LISTU_SVIH_GRUPA:
			
			Map<String, Object> sveGrupe=(Map<String, Object>) tk.getKlijentObjekat();
			KontrolerServer.vratiObjekat().obavestiSveAktivne(tk, aktivniProfil);
			KontrolerServer.vratiObjekat().postaviSveGrupe(sveGrupe);
			
			break;
		
		case DODAJ_NOVOG_CLANA:
			
			KontrolerServer.vratiObjekat().dodajClanaGrupe(tk);
			
			break;
		
		case ZAHTEV_ZA_GRUPU_PRIHVACEN:
			
			KontrolerServer.vratiObjekat().dodajClanaGrupe(tk);
			
			break;
		
		case NAPUSTI_GRUPU:
			
			KontrolerServer.vratiObjekat().napustiGrupu(tk);
			
			break;
		
		case ZAHTEV_ZA_GRUPU:
			
			KontrolerServer.vratiObjekat().upisiZahtevZaGrupu(tk);
			
			break;
		
		case IZBRISI_POSLAT_ZAHTEV_ZA_GRUPU:
			
			KontrolerServer.vratiObjekat().izbrisiPoslatiZahtevZaGrupu(tk);
			
			break;
		
		case IZBRISI_PRIMLJEN_ZAHTEV_ZA_GRUPU:
			
			KontrolerServer.vratiObjekat().izbrisiPrimljenZahtevZaGrupu(tk);
			
			break;
		
		case GRUPNA_PORUKA:
			
			KontrolerServer.vratiObjekat().proslediGrupnuPoruku(tk);
			
			break;
		
		case IZMENA_PROFILA:
			
			KontrolerServer.vratiObjekat().izmeniPodatkeProfila(tk);
			
			break;
			
		case IZMENI_PODATKE_GRUPE:
			
			KontrolerServer.vratiObjekat().izmeniPodatkeGrupe(tk);
					
			break;
					
		case SLANJE_SLIKE_PRIJATELJU:
			
			KontrolerServer.vratiObjekat().proslediSliku(tk);
			
			break;
		
		case SLANJE_SLIKE_CLANU_GRUPE:
			
			KontrolerServer.vratiObjekat().proslediSliku(tk);
				
			break;


		default:
			break;
		}
		
	}
	
	public void posaljiOdgovor(TransferKlasa tks) {
		ObjectOutputStream oos = null;
		try {
			oos=new ObjectOutputStream(getSocket().getOutputStream());
			oos.writeObject(tks);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
