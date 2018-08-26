package com.comtrade.so.profil;

import java.util.List;


import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Profil;
import com.comtrade.so.OpstaSo;
@SuppressWarnings("unchecked")
public class VratiProfile extends OpstaSo{


	@Override
	public void izvrsikonkrektnuOperaciju(Object obj) {
		List<OpstiDomen> listaProfila=(List<OpstiDomen>) obj;
		Profil p=new Profil();
		for(OpstiDomen pr:Broker.vratiInstancu().vratiSveProfile(p)) {
			listaProfila.add(pr);
		}
		
	}



}
