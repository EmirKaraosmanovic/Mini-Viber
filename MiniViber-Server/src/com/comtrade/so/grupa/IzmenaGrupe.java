package com.comtrade.so.grupa;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Grupa;
import com.comtrade.so.OpstaSo;

public class IzmenaGrupe extends OpstaSo {

	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		Grupa izmenaGrupe=(Grupa)obj;
		
		Broker.vratiInstancu().izmeniGrupu(izmenaGrupe);

	}

}
