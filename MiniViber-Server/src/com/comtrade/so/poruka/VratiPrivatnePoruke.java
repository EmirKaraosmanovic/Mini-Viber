package com.comtrade.so.poruka;

import java.util.Map;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.so.OpstaSo;
@SuppressWarnings("unchecked")
public class VratiPrivatnePoruke extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		Map<String, Object> mojiPodaci=(Map<String, Object>) obj;
		
		Broker.vratiInstancu().uzmiPoruke(new PrivatnaPoruka(),new GrupnaPoruka(),mojiPodaci);

	}

}
