package com.comtrade.so.poruka;

import com.comtrade.broker.Broker;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.so.OpstaSo;

public class UpisiPrivatnuPoruku extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		PrivatnaPoruka pp=(PrivatnaPoruka) obj;
		Broker.vratiInstancu().upisiPoruku(pp);
		
	}

}
