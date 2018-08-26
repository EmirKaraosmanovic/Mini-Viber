package com.comtrade.so.grupa;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Grupa;
import com.comtrade.so.OpstaSo;

public class KreirajGrupu extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		
		Grupa novaGrupa=(Grupa) obj;
		
		Broker.vratiInstancu().upisiGrupu(novaGrupa);

	}

}
