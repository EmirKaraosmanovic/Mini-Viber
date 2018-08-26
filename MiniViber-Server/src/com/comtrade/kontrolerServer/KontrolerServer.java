package com.comtrade.kontrolerServer;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import com.comtrade.broker.*;
import com.comtrade.domen.*;
import com.comtrade.forma.*;
import com.comtrade.konstante.Konstante;
import com.comtrade.niti.*;
import com.comtrade.so.*;
import com.comtrade.so.grupa.DodajClanaGrupe;
import com.comtrade.so.grupa.IzbrisiClanaGrupe;
import com.comtrade.so.grupa.IzmenaGrupe;
import com.comtrade.so.grupa.KreirajGrupu;
import com.comtrade.so.grupa.PromeniStatusClanaGrupe;
import com.comtrade.so.kontakti.*;
import com.comtrade.so.poruka.*;
import com.comtrade.so.profil.*;
import com.comtrade.transfer.TransferKlasa;

@SuppressWarnings({"unused","unchecked"})
public class KontrolerServer implements Konstante{
	
	private static KontrolerServer objekat;
	private ServerForma sf;
	private Map<String, NitObradaServer> mapaAktivnih=new HashMap<>();
	private Map<String, Object> sveGrupe=new HashMap<>();
	private Map<String, List<TransferKlasa>> mapaTransferKlasa=new HashMap<>();
	
	private KontrolerServer(){
		
		
	}
	
	public static KontrolerServer vratiObjekat() {
		
		if(objekat==null)
			objekat=new KontrolerServer();
		return objekat;
		
	}
	
	
	public void postaviFormu(ServerForma serverForma) {
		this.sf=serverForma;
	}
	
	public void postaviSveGrupe(Map<String, Object> sveGrupe) {
		this.sveGrupe=sveGrupe;
		
	}

	public List<Profil> vratiProfile() {
		List<Profil> listaProfila=new ArrayList<>();
		OpstaSo os=new VratiProfile();
		os.izvrsiSo(listaProfila);
		
		return listaProfila;
	}
	
	public void prikaziObavestenje(String poruka) {
		sf.prikaziObavestenje(poruka);
	}

	public void upisiProfil(Object obj) {
		
		OpstaSo os=new UpisiProfil();
		os.izvrsiSo(obj);
		
	}

	public void prijavaProfila(Map<String, Object> podaciProfila) {
		
		OpstaSo os=new PrijavaProfila();
		os.izvrsiSo(podaciProfila);
		
	}

	public void proveraPodataka(Profil p) {
		
		OpstaSo os=new ProveraPodataka();
		os.izvrsiSo(p);
		
	}
	
	public void upisiUMapuAktivnih(NitObradaServer nos) {
		
		mapaAktivnih.put(nos.getAktivniProfil().getKorisnickoIme(), nos);
		
	}
	
	public void ukloniIzMapeAktivnih(Profil aktivniProfil) {
		try {
			mapaAktivnih.remove(aktivniProfil.getKorisnickoIme());
		} catch (Exception e) {
			return;// ako ovde izbaci exaption to znaci da se nije prijavio vec je otvoren socket zbog prijave, samo izadji iz metode
		}
		
		TransferKlasa tk=new TransferKlasa();
		tk.setOperacija(ODJAVA);
		tk.setServerObjekat_odgovor(aktivniProfil);
		obavestiSveAktivne(tk, aktivniProfil);
		
	}
	public void obavestiSveAktivne(TransferKlasa tks, Profil aktivniProfil) {
		for(Entry<String, NitObradaServer> aktivni:mapaAktivnih.entrySet()) {
			if(!aktivni.getKey().equals(aktivniProfil.getKorisnickoIme())) {
				aktivni.getValue().posaljiOdgovor(tks);
			}
		}	
	}
	
	public void vratiPrivatnePoruke(TransferKlasa tk) {
		Map<String, Object> mojiPodaci=(Map<String, Object>) tk.getKlijentObjekat();
		Profil mojProfil=(Profil) mojiPodaci.get("mojProfil");
		OpstaSo os=new VratiPrivatnePoruke();
		os.izvrsiSo(mojiPodaci);
		Map<String, Object> poruke=(Map<String, Object>) mojiPodaci.get("poruke");
		if(mapaAktivnih.get(mojProfil.getKorisnickoIme())!=null) {
			tk.setOperacija(VRATI_PORUKE);
			tk.setServerObjekat_odgovor(poruke);
			mapaAktivnih.get(mojProfil.getKorisnickoIme()).posaljiOdgovor(tk);
			
		}
		
	}

	public void upisiZahtev(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil primalac=zzp.getPrimalac();
		NitObradaServer nitPrimaoca=mapaAktivnih.get(primalac.getKorisnickoIme());
		if(nitPrimaoca!=null){
			tk.setServerObjekat_odgovor(zzp);
			nitPrimaoca.posaljiOdgovor(tk);
		}
		OpstaSo os=new UpisiZahtevZaPrijateljstvo();
		os.izvrsiSo(zzp);
		
	}

	public void prihvatiZahtev(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil posiljalac=zzp.getPosiljalac();
		NitObradaServer nitPosiljaoca=mapaAktivnih.get(posiljalac.getKorisnickoIme());
		if(nitPosiljaoca!=null){
			tk.setServerObjekat_odgovor(zzp.getPrimalac());
			nitPosiljaoca.posaljiOdgovor(tk);
			tk.setOperacija(PRIJAVA);
			tk.setServerObjekat_odgovor(posiljalac);
			mapaAktivnih.get(zzp.getPrimalac().getKorisnickoIme()).posaljiOdgovor(tk);// vracam da je dodat prijatelj online
		}
		OpstaSo os=new PotvrdiZahtevZaPrijateljstvo();
		os.izvrsiSo(zzp);
		
	}

	public List<Profil> uzmiAktivnePrijatelje(Object object, Profil aktivniProfil) {
		Map<String, Object> kontakti=(Map<String, Object>) object;
		List<Profil> mojiPrijatelji=(List<Profil>) kontakti.get("prijatelji");
		List<Profil> aktivniPrijatelji=new ArrayList<>();
		for(Profil prof:mojiPrijatelji) {
			if(mapaAktivnih.get(prof.getKorisnickoIme())!=null) {
				aktivniPrijatelji.add(prof);
				obavestiPrijateljaOPrijavi(mapaAktivnih.get(prof.getKorisnickoIme()),aktivniProfil );
			}
		}
		return aktivniPrijatelji;
	}

	private void obavestiPrijateljaOPrijavi(NitObradaServer nitPrijatelja, Profil mojProfil) {
		TransferKlasa transfer=new TransferKlasa();
		transfer.setOperacija(PRIJAVA);
		transfer.setServerObjekat_odgovor(mojProfil);
		nitPrijatelja.posaljiOdgovor(transfer);
		
	}

	

	public void blokirajKontakt(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil blokiranProfil=zzp.getPrimalac();
		NitObradaServer nitPrimaoca=mapaAktivnih.get(blokiranProfil.getKorisnickoIme());
		if(nitPrimaoca!=null){
			tk.setServerObjekat_odgovor(zzp.getPosiljalac());
			nitPrimaoca.posaljiOdgovor(tk);
		}
		OpstaSo os=new BlokirajKontakt();
		os.izvrsiSo(zzp);
		
	}

	public void odblokirajKontakt(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil odblokiranProfil=zzp.getPrimalac();
		NitObradaServer nitPrimaoca=mapaAktivnih.get(odblokiranProfil.getKorisnickoIme());
		if(nitPrimaoca!=null){
			tk.setServerObjekat_odgovor(zzp.getPosiljalac());
			nitPrimaoca.posaljiOdgovor(tk);
			tk.setOperacija(PRIJAVA);
			tk.setServerObjekat_odgovor(odblokiranProfil);
			mapaAktivnih.get(zzp.getPosiljalac().getKorisnickoIme()).posaljiOdgovor(tk);// vracam da je odblokiraniProfilOnline
		}
		OpstaSo os=new OdblokirajKontakt();
		os.izvrsiSo(zzp);
	}

	public void proslediPoruku(TransferKlasa tk) {
		PrivatnaPoruka pp=(PrivatnaPoruka) tk.getKlijentObjekat();
		NitObradaServer nitPrimaoca=mapaAktivnih.get(pp.getPrimalac().getKorisnickoIme());
		if(nitPrimaoca!=null) {
			tk.setServerObjekat_odgovor(pp);
			nitPrimaoca.posaljiOdgovor(tk);
		}else {
			OpstaSo os=new UpisiPrivatnuPoruku();
			os.izvrsiSo(pp);
		}
		
		
	}

	public void promeniStatusPrivatnihPoruka(TransferKlasa tk) {
		List<PrivatnaPoruka> poruke=(List<PrivatnaPoruka>) tk.getKlijentObjekat();
		
		OpstaSo os=new PromeniStatusPrivatnihPoruka();
		os.izvrsiSo(poruke);
		
	}

	public void upisiRazgovore(TransferKlasa tk) {
		Map<String, Object> map=(Map<String, Object>) tk.getKlijentObjekat();
		Map<String, List<PrivatnaPoruka>> razgovori=(Map<String, List<PrivatnaPoruka>>) map.get("trenutniRazgovori");
		
		for(Entry<String, List<PrivatnaPoruka>> dopisivanje:razgovori.entrySet()) {
			if(mapaAktivnih.get(dopisivanje.getKey())!=null) {
				OpstaSo os=new UpisiPrivatniRazgovor();
				os.izvrsiSo(dopisivanje.getValue());
			}
		}	
		OpstaSo os=new UpisiBrojGrupnihPoruka();
		os.izvrsiSo(map);	
	}

	public void promeniStatusKodKorisnika(TransferKlasa tk) {
		PrivatnaPoruka pp=(PrivatnaPoruka) tk.getKlijentObjekat();
		if(mapaAktivnih.get(pp.getPosiljalac().getKorisnickoIme())!=null) {
			tk.setServerObjekat_odgovor(pp);
			mapaAktivnih.get(pp.getPosiljalac().getKorisnickoIme()).posaljiOdgovor(tk);
		}
	}

	public void izbrisiPoslatZahtev(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil primalac=zzp.getPrimalac();
		NitObradaServer nitPrimaoca=null;
		try {
			nitPrimaoca=mapaAktivnih.get(primalac.getKorisnickoIme());
		} catch (Exception e) {
			
		}
		
		if(nitPrimaoca!=null){
			tk.setOperacija(BRISANJE_PRIMLJENOG_ZAHTEVA);
			tk.setServerObjekat_odgovor(zzp);
			nitPrimaoca.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiKontakt();
		os.izvrsiSo(zzp);
		
	}
	public void izbrisiPrimljenZahtev(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil posljalac=zzp.getPosiljalac();
		NitObradaServer nitPosiljaoca=mapaAktivnih.get(posljalac.getKorisnickoIme());
		if(nitPosiljaoca!=null){
			tk.setOperacija(BRISANJE_POSLATOG_ZAHTEVA);
			tk.setServerObjekat_odgovor(zzp);
			nitPosiljaoca.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiKontakt();
		os.izvrsiSo(zzp);
		
	}
	
	public void izbrisiPrijatelja(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) tk.getKlijentObjekat();
		Profil primalac=zzp.getPrimalac();
		NitObradaServer nitPrimaoca=mapaAktivnih.get(primalac.getKorisnickoIme());
		if(nitPrimaoca!=null){
			tk.setServerObjekat_odgovor(zzp);
			nitPrimaoca.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiKontakt();
		os.izvrsiSo(zzp);
		
	}

	public void kreirajGrupu(TransferKlasa tk) {
		Grupa novaGrupa=(Grupa) tk.getKlijentObjekat();
		OpstaSo os=new KreirajGrupu();
		os.izvrsiSo(novaGrupa);
		tk.setServerObjekat_odgovor(novaGrupa);
		if(novaGrupa.getIdGrupa()!=0) {
			if(mapaAktivnih.get(novaGrupa.getKreatorGrupe().getKorisnickoIme())!=null) {
				mapaAktivnih.get(novaGrupa.getKreatorGrupe().getKorisnickoIme()).posaljiOdgovor(tk);
			}
			tk.setOperacija(OSVEZI_LISTU_SVIH_GRUPA);
			obavestiSveAktivne(tk, novaGrupa.getKreatorGrupe());
		}		
		
	}

	public void izbrisiClanaGrupe(TransferKlasa tk) {
		ClanGrupe clan=(ClanGrupe) tk.getKlijentObjekat();
		NitObradaServer nitClana=mapaAktivnih.get(clan.getClan().getKorisnickoIme());
		
		if(nitClana!=null) {
			tk.setOperacija(IZBRISI_GRUPU);
			tk.setServerObjekat_odgovor(clan.getGrupa());
			nitClana.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiClanaGrupe();
		os.izvrsiSo(clan);
	}

	public void dodajClanaGrupe(TransferKlasa tk) {
		Map<String, Object>  clanGrupe=(Map<String, Object>) tk.getKlijentObjekat();
		ClanGrupe clan=(ClanGrupe) clanGrupe.get("noviClan");
		if(tk.getOperacija()==DODAJ_NOVOG_CLANA) {
			OpstaSo os=new DodajClanaGrupe();
			os.izvrsiSo(clan);
		}else {
			OpstaSo os=new PromeniStatusClanaGrupe();
			os.izvrsiSo(clan);
		}
			
		NitObradaServer nitClana=mapaAktivnih.get(clan.getClan().getKorisnickoIme());
		if(nitClana!=null) {
			tk.setOperacija(DODAJ_NOVU_GRUPU);
			tk.setServerObjekat_odgovor(clanGrupe);
			nitClana.posaljiOdgovor(tk);
		}
		
	
	}

	public void napustiGrupu(TransferKlasa tk) {
		ClanGrupe clan=(ClanGrupe) tk.getKlijentObjekat();
		NitObradaServer nitClana=mapaAktivnih.get(clan.getGrupa().getKreatorGrupe().getKorisnickoIme());
		
		if(nitClana!=null) {
			tk.setOperacija(IZBRISI_CLANA_GRUPE);
			tk.setServerObjekat_odgovor(clan);
			nitClana.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiClanaGrupe();
		os.izvrsiSo(clan);
		
		
	}

	public void upisiZahtevZaGrupu(TransferKlasa tk) {
		ClanGrupe clan=(ClanGrupe) tk.getKlijentObjekat();
		Profil kreator=clan.getGrupa().getKreatorGrupe();
		NitObradaServer nitKreatora=mapaAktivnih.get(kreator.getKorisnickoIme());
		if(nitKreatora!=null) {
			tk.setServerObjekat_odgovor(clan);
			nitKreatora.posaljiOdgovor(tk);
		}
		OpstaSo os=new DodajClanaGrupe();
		os.izvrsiSo(clan);
		
	}

	public void izbrisiPoslatiZahtevZaGrupu(TransferKlasa tk) {
		ClanGrupe poslatZahtev=(ClanGrupe) tk.getKlijentObjekat();
		Profil kreator=poslatZahtev.getGrupa().getKreatorGrupe();
		NitObradaServer nitKreatora=mapaAktivnih.get(kreator.getKorisnickoIme());
		if(nitKreatora!=null) {
			tk.setOperacija(IZBRISI_PRIMLJEN_ZAHTEV_ZA_GRUPU);
			tk.setServerObjekat_odgovor(poslatZahtev);
			nitKreatora.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiClanaGrupe();
		os.izvrsiSo(poslatZahtev);
		
	}

	public void izbrisiPrimljenZahtevZaGrupu(TransferKlasa tk) {
		ClanGrupe primljenZahtev=(ClanGrupe) tk.getKlijentObjekat();
		Profil clan=primljenZahtev.getClan();
		NitObradaServer nitClana=mapaAktivnih.get(clan.getKorisnickoIme());
		if(nitClana!=null) {
			tk.setOperacija(IZBRISI_POSLAT_ZAHTEV_ZA_GRUPU);
			tk.setServerObjekat_odgovor(primljenZahtev);
			nitClana.posaljiOdgovor(tk);
		}
		OpstaSo os=new IzbrisiClanaGrupe();
		os.izvrsiSo(primljenZahtev);
		
	}

	public void proslediGrupnuPoruku(TransferKlasa tk) {
		GrupnaPoruka gp=(GrupnaPoruka) tk.getKlijentObjekat();
		tk.setServerObjekat_odgovor(gp);
		Map<String, Object> podaciGrupe=(Map<String, Object>) sveGrupe.get(gp.getGrupa().getNazivGrupe());
		List<ClanGrupe> clanoviGrupe=(List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		for(ClanGrupe clan:clanoviGrupe) {
			if(clan.getClan().getIdProfila()!=gp.getPosiljalac().getIdProfila()) {
				NitObradaServer nitClana=mapaAktivnih.get(clan.getClan().getKorisnickoIme());
				if(nitClana!=null) {
					nitClana.posaljiOdgovor(tk);
				}
			}
		}
		OpstaSo os=new UpisiGrupnuPoruku();
		os.izvrsiSo(gp);
		
	}

	public void izmeniPodatkeProfila(TransferKlasa tk) {
		Profil izmenaProfila=(Profil) tk.getKlijentObjekat();
		OpstaSo os=new IzmenaProfila();
		os.izvrsiSo(izmenaProfila);
		if(izmenaProfila.getIdProfila()!=0) {
			NitObradaServer nitProfila = mapaAktivnih.get(izmenaProfila.getKorisnickoIme());
			tk.setServerObjekat_odgovor(izmenaProfila);
			if(nitProfila!=null) {
				nitProfila.posaljiOdgovor(tk);
			}
			tk.setOperacija(DODAJ_IZMENJEN_PROFIL);
			obavestiSveAktivne(tk, izmenaProfila);
		}
		
	}

	public void izmeniPodatkeGrupe(TransferKlasa tk) {
		Grupa izmenaGrupe=(Grupa) tk.getKlijentObjekat();
		OpstaSo os=new IzmenaGrupe();
		os.izvrsiSo(izmenaGrupe);
		tk.setOperacija(DODAJ_IZMENJENU_GRUPU);
		tk.setServerObjekat_odgovor(izmenaGrupe);
		if(izmenaGrupe.getIdGrupa()!=0) {
		NitObradaServer nitProfila = mapaAktivnih.get(izmenaGrupe.getKreatorGrupe().getKorisnickoIme());
		if(nitProfila!=null) {
			nitProfila.posaljiOdgovor(tk);		
		}
		obavestiSveAktivne(tk, izmenaGrupe.getKreatorGrupe());
		}
	}

	public void provriUnetePodatke(Profil provera) {

		OpstaSo os=new ProveraUnetihPodataka();
		os.izvrsiSo(provera);
	}

	public void proslediSliku(TransferKlasa tk) {
		SlanjeFajla sf=(SlanjeFajla) tk.getKlijentObjekat();
		tk.setServerObjekat_odgovor(sf);
		NitObradaServer nitPrijatelja=mapaAktivnih.get(sf.getPrimalac());
		if(nitPrijatelja!=null) {
			nitPrijatelja.posaljiOdgovor(tk);
		}else {

			upisiUFajlKorisnika(sf.getPrimalac(),tk);
		}
	}

	private void upisiUFajlKorisnika(String nazivFajla, TransferKlasa zaUpis) {
		try {
			mapaTransferKlasa.get(nazivFajla).add(zaUpis);
			
		} catch (Exception e) {
			mapaTransferKlasa.put(nazivFajla, new ArrayList<>());
			mapaTransferKlasa.get(nazivFajla).add(zaUpis);
		}
		
		ObjectOutputStream oos=null;
		try {
			FileOutputStream fos=new FileOutputStream(FOLDER_ZA_UPIS_SLIKE_NA_SERVER+nazivFajla+".txt");
			oos=new ObjectOutputStream(fos);
			oos.writeObject(mapaTransferKlasa.get(nazivFajla)); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	public void uzmiSlikeIzFajla(String nazivFajla) {
		ObjectInputStream ois=null;
		try {
			FileInputStream fis=new FileInputStream(FOLDER_ZA_UPIS_SLIKE_NA_SERVER+nazivFajla+".txt");
			ois=new ObjectInputStream(fis);
			List<TransferKlasa> list=(List<TransferKlasa>) ois.readObject();
			for(TransferKlasa tk:list) {
				proslediSliku(tk);
				
			}
			mapaTransferKlasa.remove(nazivFajla);
			
		} catch (Exception e) {
			return;
		}finally {
			try {
				if(ois!=null)
					ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	public  List<Profil> posaljiPredlogPrijatelja(Profil mojProfil, Map<String, Object> kontakti,List<Profil> sviProfili) {
		List<Profil> prijatelji=(List<Profil>) kontakti.get("prijatelji");
		List<ZahtevZaPrijateljstvo> poslatiZatevi=(List<ZahtevZaPrijateljstvo>) kontakti.get("poslatiZahtevi");
		List<ZahtevZaPrijateljstvo> primljeniZahtevi=(List<ZahtevZaPrijateljstvo>) kontakti.get("primljeniZahtevi");
		List<Profil> blokiraniKontakti=(List<Profil>) kontakti.get("blokiraniKontakti");
		
		List<Profil> predlogPrijatelja=new ArrayList<>();
		int i=0;
		for(Profil pr:sviProfili) {
			if(pr.getIdProfila()!=mojProfil.getIdProfila() && pr.getStatus()!=ADMIN_STATUS) {
				if(mojProfil.getGradStanovanja().contains(pr.getGradStanovanja()) || mojProfil.getZanimanje().contains(pr.getZanimanje())){
					
					if (prijatelji != null && profilIzPrijatelja(pr,prijatelji) == true) {
						continue;
						
					}else if (primljeniZahtevi != null && profilIzPrimljenihZahteva(pr,primljeniZahtevi) == true) {
						continue;

					}else if (poslatiZatevi != null && profilIzPoslatihZahteva(pr,poslatiZatevi) == true) {
						
						continue;
					}else if (blokiraniKontakti != null && profilIzListeBlokiranih(pr,blokiraniKontakti) == true) {
						
						continue;

					}else {
						predlogPrijatelja.add(pr);
						i++;
					}
					if(i==5) {
						break;
					}
					
				}
			}
			
	
		}
		return predlogPrijatelja;
		
	}
	
	private static boolean profilIzListeBlokiranih(Profil profil,List<Profil> blokiraniKontakti ) {
		for (Profil p : blokiraniKontakti) {
			if (p.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private static boolean profilIzPrijatelja(Profil profil,List<Profil> listaPrijatelja) {
		for (Profil p : listaPrijatelja) {
			if (p.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private static boolean profilIzPoslatihZahteva(Profil profil,List<ZahtevZaPrijateljstvo> poslatiZahtevi) {
		for (ZahtevZaPrijateljstvo z : poslatiZahtevi) {
			if (z.getPrimalac().getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private static boolean profilIzPrimljenihZahteva(Profil profil,List<ZahtevZaPrijateljstvo> primljeniZahtevi) {
		for (ZahtevZaPrijateljstvo z : primljeniZahtevi) {
			if (z.getPosiljalac().getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	
		
}
