package com.comtrade.so.grupa;

import com.comtrade.broker.Broker;
import com.comtrade.domen.ClanGrupe;
import com.comtrade.so.OpstaSo;

public class PromeniStatusClanaGrupe extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {

		ClanGrupe cg=(ClanGrupe) obj;
		Broker.vratiInstancu().promeniStatusClanaGrupe(cg);

	}

}
