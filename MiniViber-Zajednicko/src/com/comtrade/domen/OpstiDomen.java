package com.comtrade.domen;

import java.sql.ResultSet;
import java.util.List;

public interface OpstiDomen {
	
	String vratiNazivTabele();
	String vratiZaInsert();
	String vratiZaUpdate();
	String vratiZaDelete();
	List<OpstiDomen> izvrsiSelect(ResultSet rs);
	

}
