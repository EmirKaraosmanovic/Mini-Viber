package com.comtrade.so.profil;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Profil;
import com.comtrade.so.OpstaSo;

public class ProveraUnetihPodataka extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		Profil provera=(Profil) obj;
		
		Broker.vratiInstancu().proveraUnetihPodataka(provera);

	}

}
