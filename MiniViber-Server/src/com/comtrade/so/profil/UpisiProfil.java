package com.comtrade.so.profil;

import com.comtrade.broker.Broker;

import com.comtrade.so.OpstaSo;

public class UpisiProfil extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		Broker.vratiInstancu().upisiProfil(obj);

	}
}
