package com.comtrade.so.kontakti;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Kontakti;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.konstante.Konstante;
import com.comtrade.so.OpstaSo;

public class OdblokirajKontakt extends OpstaSo implements Konstante {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		ZahtevZaPrijateljstvo zahtev=(ZahtevZaPrijateljstvo) obj;
		Kontakti k=new Kontakti();
		int idPrvi=zahtev.getPosiljalac().getIdProfila();
		int idDrugi=zahtev.getPrimalac().getIdProfila();
		if(idPrvi<idDrugi) {
			k.setIdProfilPrvi(idPrvi);
			k.setIdProfilDrugi(idDrugi);
		}else {
			k.setIdProfilPrvi(idDrugi);
			k.setIdProfilDrugi(idPrvi);
		}
		k.setStatusPrijateljstva(PRIJATELJI);
		k.setIdProfilAkcija(idPrvi);
		Broker.vratiInstancu().odblokirajKontakt(k);
		
	}

}
