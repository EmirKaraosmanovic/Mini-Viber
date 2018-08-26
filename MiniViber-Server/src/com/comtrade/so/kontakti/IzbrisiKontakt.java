package com.comtrade.so.kontakti;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Kontakti;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.so.OpstaSo;

public class IzbrisiKontakt extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) obj;
		Kontakti k=new Kontakti();
		int idPrvi=zzp.getPosiljalac().getIdProfila();
		int idDrugi=zzp.getPrimalac().getIdProfila();
		if(idPrvi<idDrugi) {
			k.setIdProfilPrvi(idPrvi);
			k.setIdProfilDrugi(idDrugi);
		}else {
			k.setIdProfilPrvi(idDrugi);
			k.setIdProfilDrugi(idPrvi);
		}
		Broker.vratiInstancu().izbrisiKontakt(k);

	}

}
