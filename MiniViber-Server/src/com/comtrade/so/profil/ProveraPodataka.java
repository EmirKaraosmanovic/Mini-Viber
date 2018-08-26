package com.comtrade.so.profil;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Profil;
import com.comtrade.so.OpstaSo;

public class ProveraPodataka extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		Profil p=(Profil) obj;
		Broker.vratiInstancu().proveraPodataka(p);
	}

}
