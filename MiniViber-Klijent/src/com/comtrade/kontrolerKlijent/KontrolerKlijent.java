package com.comtrade.kontrolerKlijent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import com.comtrade.domen.ClanGrupe;
import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.SlanjeFajla;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.forma.admin.AdminForma;
import com.comtrade.forma.profil.ProfilForma;
import com.comtrade.konstante.Konstante;

public class KontrolerKlijent implements Konstante{
	
	private static KontrolerKlijent objekat;
	private ProfilForma profilForma;
	private AdminForma adminForma;
	
	private KontrolerKlijent() {
		
		
	}
	public static KontrolerKlijent vratiObjekat() {
		if(objekat==null)
			objekat=new KontrolerKlijent();
		return objekat;
		
	}
	
	public void postaviProfilFormu(ProfilForma profilForma) {// uzimam referencu na profil formu i postavljam je u globalnu promenljivu u kontroleru
		
		this.profilForma=profilForma;
		
	}
	
	public void postaviAdminFormu(AdminForma adminForma) {// na isti nacin postavljam referencu admin forme
		
		this.adminForma=adminForma;
		
	}
	
	public void osveziPretraguProfila(List<Profil> listaSvihProfila) {
		
		if(profilForma!=null) {
			profilForma.postaviListuSvihProfila(listaSvihProfila);
		}
		if(adminForma!=null) {
			adminForma.postaviListuSvihProfila(listaSvihProfila);
		}
		
	}
	
	public void prikaziPrimljeniZahtevZaPrijateljstvo(ZahtevZaPrijateljstvo zzp) {
		
		profilForma.prikaziPrimljeniZahtevZaPrijateljstvo(zzp);
		
	}
	public void prikaziNovogPrijatelja(Profil noviPrijatelj) {
		
		profilForma.prikaziNovogPrijatelja(noviPrijatelj);
		
	}
	public void ubaciUListuAktivnih(Profil aktivanPrijatelj) {

		profilForma.ubaciUListuAktivnih(aktivanPrijatelj);
		
	}
	public void izbaciIzListeAktivnih(Profil aktivanPrijatelj) {
		
		profilForma.izbaciIzListeAktivnih(aktivanPrijatelj);
		
	}
	public void izbrisiZahtevIzListePrimljenihZahteva(ZahtevZaPrijateljstvo zzp) {
		profilForma.izbrisiZahtevIzListePrimljenihZahteva(zzp);
		
	}
	public void izbrisiZahtevIzListePoslatihZahteva(ZahtevZaPrijateljstvo zzp) {
		
		profilForma.izbrisiZahtevIzListePoslatihZahteva(zzp);
		
	}
	
	public void izbrisiPrijateljaIzListePrijatelja(Profil prijatelj) {
		profilForma.izbrisiPrijateljaIzListePrijatelja(prijatelj);		
	}
	public void izbrisiBlokirajuciKontakt(Profil profil) {
		
		profilForma.izbrisiBlokirajuciKontakt(profil);
		
	}
	public void dodajBlokirajuciKontakt(Profil profil) {
		profilForma.dodajBlokirajuciKontakt(profil);
		
	}
	public void prikaziNovuPoruku(PrivatnaPoruka pp) {
		profilForma.prikaziNovuPoruku(pp);
		
	}

	public void postaviPoruke(Map<String, Object> poruke) {
		profilForma.postaviPoruke(poruke);
		
	}
	public void promeniStatusPoslatePoruke(PrivatnaPoruka pp) {
		profilForma.promeniStatusPoslatePoruke(pp);
		
	}
	public void kreiranjeGrupe(Grupa grupa) {
		
		profilForma.kreiranjeGrupe(grupa);
		
	}
	public void dodajNovuGrupuUListuSvih(Grupa grupa) {
		
		if(profilForma!=null) {
			profilForma.dodajNovuGrupuUListuSvih(grupa);
		}
		if(adminForma!=null) {
			adminForma.dodajNovuGrupuUListuSvih(grupa);
		}
		
		
	}
	public void izbrisiGrupuIzMojihGrupa(Grupa grupa) {
		
		profilForma.izbrisiGrupuIzMojihGrupa(grupa);
		
	}
	public void postaviSveGrupe(Map<String, Object> sveGrupe) {
		
		if(profilForma!=null) {
			profilForma.postaviSveGrupe(sveGrupe);
		}
		if(adminForma!=null) {
			adminForma.postaviSveGrupe(sveGrupe);
		}
		
	}
	public void dodajGrupuUMojeGrupe(Map<String, Object> clanGrupe) {
		profilForma.dodajGrupuUMojeGrupe(clanGrupe);
		
	}
	public void izbrisiClanaIzGrupe(ClanGrupe clanZaBrisanje) {
		profilForma.izbrisiClanaIzGrupe(clanZaBrisanje);
		
	}
	public void dodajClanaUPrimljeneZahteve(ClanGrupe primljenZahtev) {
		profilForma.dodajClanaUPrimljeneZahteve(primljenZahtev);
		
	}
	public void izbrisiClanaIzPrimljenihZahteva(ClanGrupe primljenZahtev) {
		profilForma.izbrisiClanaIzPrimljenihZahteva(primljenZahtev);
	
	}
	public void izbrisiClanaIzPoslatihZahteva(ClanGrupe poslatZahtev) {
		profilForma.izbrisiClanaIzPoslatihZahteva(poslatZahtev);
		
	}
	public void prikaziNovuGrupnuPoruku(GrupnaPoruka grupnaPoruka) {
		profilForma.prikaziNovuGrupnuPoruku(grupnaPoruka);
		
	}
	public void izmenaProfila(Profil izmenaProfil) {
		profilForma.izmenaProfila(izmenaProfil);
		
	}
	public void dodajIzmenjenProfil(Profil izmenaProfil) {
		profilForma.dodajIzmenjenProfila(izmenaProfil);
		
	}
	public void dodajIzmenjenuGrupu(Grupa izmenjenaGrupa) {
		profilForma.dodajIzmenjenuGrupu(izmenjenaGrupa);
		
	}
	public void sacuvajSlikuPrijatelja(SlanjeFajla sf) {
		
		sacuvajSliku(sf,FOLDER_SLIKE_PROFILA);
		
		
	}
	
	public void sacuvajSlikuGrupe(SlanjeFajla sf) {
		
		sacuvajSliku(sf,FOLDER_SLIKE_GRUPA);
		
	}
	
	public void sacuvajSliku(SlanjeFajla sf,String pozicijaZaCuvanje) {
		byte[] podatak=sf.getPodatak();
		
		File lokacijaSlike=new File(pozicijaZaCuvanje+sf.getNazivFajla()+".jpg");
		try {
			Files.write(lokacijaSlike.toPath(), podatak, StandardOpenOption.CREATE );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void prikaziPredloge(List<Profil> predlogPrijatelja) {
		
		if(profilForma!=null) {// ovo moramo da uslovimo jer ako je u pitanju prijava admin forme onda da ne izbaci null pointer exeption
			
			profilForma.prikaziPredloge(predlogPrijatelja);
		}
		
	}
	
	
}
