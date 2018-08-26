package com.comtrade.so.profil;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Profil;
import com.comtrade.so.OpstaSo;

public class IzmenaProfila extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		Profil izmenaProfila=(Profil) obj;
		
		Broker.vratiInstancu().izmenaProfila(izmenaProfila);

	}

}
