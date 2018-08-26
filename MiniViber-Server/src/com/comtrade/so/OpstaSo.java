package com.comtrade.so;

import com.comtrade.broker.Broker;

public abstract class OpstaSo {
	
	public void izvrsiSo(Object obj) {
		
		try {
			
			pokreniTransakciju();
			
			izvrsikonkrektnuOperaciju(obj);
			
			potvrdiTransakciju();
			
		} catch (Exception e) {
			ponistiTransakciju();
			// TODO: handle exception
		}finally {
			zatvoriTransakciju();
		}
		
	}

	public abstract void izvrsikonkrektnuOperaciju(Object obj);
	
	private void zatvoriTransakciju() {
		Broker.vratiInstancu().zatvoriKonekciju();
		
	}

	private void ponistiTransakciju() {
		Broker.vratiInstancu().ponistiKonekciju();
		
	}

	private void potvrdiTransakciju() {
		Broker.vratiInstancu().potvrdiTransakciju();
		
	}

	private void pokreniTransakciju() {
		Broker.vratiInstancu().pokreniTransakciju();
		
	}

	

}
