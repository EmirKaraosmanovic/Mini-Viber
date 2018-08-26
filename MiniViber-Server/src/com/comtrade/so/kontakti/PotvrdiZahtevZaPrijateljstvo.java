package com.comtrade.so.kontakti;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Kontakti;
import com.comtrade.domen.ZahtevZaPrijateljstvo;
import com.comtrade.konstante.Konstante;
import com.comtrade.so.OpstaSo;

public class PotvrdiZahtevZaPrijateljstvo extends OpstaSo implements Konstante{

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		ZahtevZaPrijateljstvo zzp=(ZahtevZaPrijateljstvo) obj;
		int idPosiljalac=zzp.getPosiljalac().getIdProfila();
		int idPrimalac=zzp.getPrimalac().getIdProfila();
		Kontakti k=null;
		if(idPosiljalac<idPrimalac) {
			k=new Kontakti(idPosiljalac,idPrimalac , PRIJATELJI, idPrimalac);
		}else {
			k=new Kontakti(idPrimalac ,idPosiljalac, PRIJATELJI, idPrimalac);
		}
		Broker.vratiInstancu().prihvatiPrijateljstvo(k);
	}

}

