package com.comtrade.so.grupa;

import com.comtrade.broker.Broker;
import com.comtrade.domen.ClanGrupe;
import com.comtrade.so.OpstaSo;

public class DodajClanaGrupe extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		ClanGrupe cg=(ClanGrupe) obj;
		
		Broker.vratiInstancu().dodajClanaGrupe(cg);

	}

}
