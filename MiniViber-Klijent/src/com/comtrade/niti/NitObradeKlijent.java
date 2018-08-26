package com.comtrade.niti;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.comtrade.domen.ClanGrupe;
import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.SlanjeFajla;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.transfer.TransferKlasa;
@SuppressWarnings({"unused","unchecked"})
public class NitObradeKlijent extends Thread implements Konstante{

	private Socket socketKlijent;
	
	public NitObradeKlijent(Socket socket) {
		this.socketKlijent=socket;
	}
	
	
	private Socket vratiSocketKlijent() {
		
		return socketKlijent;
		
	}
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois=new ObjectInputStream(socketKlijent.getInputStream());
				TransferKlasa tk=(TransferKlasa) ois.readObject();
				obradiZahtevOdServera(tk);
			
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void obradiZahtevOdServera(TransferKlasa tk) {
		ZahtevZaPrijateljstvo zzp=null;
		Profil profil=null;
		PrivatnaPoruka pp=null;
		Grupa grupa=null;
		ClanGrupe primljenZahtev=null;
		ClanGrupe poslatZahtev=null;
		SlanjeFajla sf=null;
		
		switch (tk.getOperacija()) {
		
		case OSVEZI_PRETRAGU_PROFILA:
			
			List<Profil> listaSvihProfila=(List<Profil>) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().osveziPretraguProfila(listaSvihProfila);
			
			break;
		case VRATI_PORUKE:
			
			Map<String, Object> poruke=(Map<String, Object>) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().postaviPoruke(poruke);
					
			break;
		case ZAHTEV_ZA_PRIJATELJSTVO:
			
			zzp=(ZahtevZaPrijateljstvo) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().prikaziPrimljeniZahtevZaPrijateljstvo(zzp);
			
			break;
		case ZAHTEV_PRIHVACEN:
			
			Profil noviPrijatelj= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().prikaziNovogPrijatelja(noviPrijatelj);
			
			break;
		case BRISANJE_PRIMLJENOG_ZAHTEVA:
			
			zzp=(ZahtevZaPrijateljstvo) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiZahtevIzListePrimljenihZahteva(zzp);
			
			break;
		case BRISANJE_POSLATOG_ZAHTEVA:
			
			zzp=(ZahtevZaPrijateljstvo) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiZahtevIzListePoslatihZahteva(zzp);
			
			break;
		case IZBRISI_PRIJATELJA:
			
			zzp=(ZahtevZaPrijateljstvo) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiPrijateljaIzListePrijatelja(zzp.getPosiljalac());
			
			break;
		case PRIJAVA:
			
			Profil aktivanPrijatelj= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().ubaciUListuAktivnih(aktivanPrijatelj);
			
			break;
		case ODJAVA:
			
			Profil odjavljenPrijatelj= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbaciIzListeAktivnih(odjavljenPrijatelj);
			
			break;
		case ZAHTEV_ZA_BLOKIRANJE:
			
			profil= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiBlokirajuciKontakt(profil);
			
			break;
		case ODBLOKIRAJ_KONTAKT:
			
			profil= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().dodajBlokirajuciKontakt(profil);
			
			break;
		case PRIVATNA_PORUKA:
			
			pp=(PrivatnaPoruka) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().prikaziNovuPoruku(pp);
			
			break;
		case PROMENI_STATUS_PRIVATNIH_PORUKA_KOD_KORISNIKA:
			
			pp=(PrivatnaPoruka) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().promeniStatusPoslatePoruke(pp);
			
			break;
		case KREIRANJE_GRUPE:
			
			grupa= (Grupa) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().kreiranjeGrupe(grupa);
			
			break;
		case OSVEZI_LISTU_SVIH_GRUPA:
			
			grupa= (Grupa) tk.getServerObjekat_odgovor();
			if(grupa!=null) {
				KontrolerKlijent.vratiObjekat().dodajNovuGrupuUListuSvih(grupa);
			}else {
				Map<String, Object> sveGrupe=(Map<String, Object>) tk.getKlijentObjekat();
				
				KontrolerKlijent.vratiObjekat().postaviSveGrupe(sveGrupe);
			}
			
			break;
		case IZBRISI_GRUPU:
			
			grupa= (Grupa) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiGrupuIzMojihGrupa(grupa);
			
			break;
		case DODAJ_NOVU_GRUPU:
			
			Map<String, Object>  clanGrupe=(Map<String, Object>) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().dodajGrupuUMojeGrupe(clanGrupe);
			
			break;
		case IZBRISI_CLANA_GRUPE:
			
			ClanGrupe clanZaBrisanje= (ClanGrupe) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiClanaIzGrupe(clanZaBrisanje);
			
			break;
		case ZAHTEV_ZA_GRUPU:
			
			primljenZahtev= (ClanGrupe) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().dodajClanaUPrimljeneZahteve(primljenZahtev);
			
			break;
		case IZBRISI_PRIMLJEN_ZAHTEV_ZA_GRUPU:
			
			primljenZahtev= (ClanGrupe) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiClanaIzPrimljenihZahteva(primljenZahtev);
			
			break;
		case IZBRISI_POSLAT_ZAHTEV_ZA_GRUPU:
			
			poslatZahtev= (ClanGrupe) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izbrisiClanaIzPoslatihZahteva(poslatZahtev);
			
			break;
			
		case GRUPNA_PORUKA:
					
			GrupnaPoruka grupnaPoruka=  (GrupnaPoruka) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().prikaziNovuGrupnuPoruku(grupnaPoruka);
					
			break;
		case IZMENA_PROFILA:
			
			profil=  (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().izmenaProfila(profil);
			
			break;
		case DODAJ_IZMENJEN_PROFIL:
			
			profil= (Profil) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().dodajIzmenjenProfil(profil);
			
			break;
		case DODAJ_IZMENJENU_GRUPU:
			
			Grupa izmenjenaGrupa=  (Grupa) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().dodajIzmenjenuGrupu(izmenjenaGrupa);
			
			break;
		case SLANJE_SLIKE_PRIJATELJU:
			
			sf=(SlanjeFajla) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().sacuvajSlikuPrijatelja(sf);
			
			break;
		case SLANJE_SLIKE_CLANU_GRUPE:
			
			sf=(SlanjeFajla) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().sacuvajSlikuGrupe(sf);
			
			break;
		case PREDLOG_ZA_PRIJATELJE:
			
			List<Profil> predlogPrijatelja= (List<Profil>) tk.getServerObjekat_odgovor();
			KontrolerKlijent.vratiObjekat().prikaziPredloge(predlogPrijatelja);
			
			break;
		
		default:
			break;
			
		}
	}
}
