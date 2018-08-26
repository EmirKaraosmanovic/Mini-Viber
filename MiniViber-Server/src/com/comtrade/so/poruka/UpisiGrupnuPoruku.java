package com.comtrade.so.poruka;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.so.OpstaSo;

public class UpisiGrupnuPoruku extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		GrupnaPoruka gp=(GrupnaPoruka) obj;
		Broker.vratiInstancu().upisiGrupnuPoruku(gp);

	}

}
