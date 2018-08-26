package com.comtrade.so.kontakti;

import com.comtrade.broker.Broker;
import com.comtrade.domen.*;
import com.comtrade.konstante.Konstante;
import com.comtrade.so.OpstaSo;

public class UpisiZahtevZaPrijateljstvo extends OpstaSo implements Konstante{

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) obj;
		int idProfilPrvi=zzp.getPosiljalac().getIdProfila();
		int idProfilDrugi=zzp.getPrimalac().getIdProfila();
		Kontakti k=null;
		if(idProfilPrvi<idProfilDrugi) {
			k=new Kontakti(idProfilPrvi,idProfilDrugi , POSLAT_ZAHTEV, idProfilPrvi);
		}else {
			k=new Kontakti(idProfilDrugi ,idProfilPrvi, POSLAT_ZAHTEV, idProfilPrvi);
		}
		Broker.vratiInstancu().upisiKontakt(k);
	}

}
