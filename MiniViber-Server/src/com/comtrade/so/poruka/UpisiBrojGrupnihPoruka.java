package com.comtrade.so.poruka;

import java.util.Map;

import com.comtrade.broker.Broker;
import com.comtrade.so.OpstaSo;
@SuppressWarnings("unchecked")
public class UpisiBrojGrupnihPoruka extends OpstaSo {

	
	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		Map<String, Object> brojPrivatnihPoruka=(Map<String, Object>) obj;
		
		Broker.vratiInstancu().upisiBrojGrupnihPoruka(brojPrivatnihPoruka);

	}

}
