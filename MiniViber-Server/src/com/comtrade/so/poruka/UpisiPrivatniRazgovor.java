package com.comtrade.so.poruka;

import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.so.OpstaSo;
@SuppressWarnings("unchecked")
public class UpisiPrivatniRazgovor extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		List<PrivatnaPoruka> poruke=(List<PrivatnaPoruka>) obj;
		Broker.vratiInstancu().upisiPrivatniRazgovor(poruke);

	}

}
