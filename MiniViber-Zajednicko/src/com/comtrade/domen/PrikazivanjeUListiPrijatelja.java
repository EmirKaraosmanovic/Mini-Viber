package com.comtrade.domen;


import java.awt.Component;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.comtrade.konstante.Konstante;

@SuppressWarnings( "serial")
public class PrikazivanjeUListiPrijatelja extends DefaultListCellRenderer implements ListCellRenderer<Object>,Konstante {
	

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		
		Profil p=(Profil) value;
		
		if(p.getVidljivost()==PROFIL_NEVIDLJIV) {
			p.setSlikaProfila(SLIKA_PROFIL_NEVIDLJIV);
		}
			
		Image profilSlika = null;
		try {
			Image slika = ImageIO.read(new File(p.getSlikaProfila()));
			profilSlika=slika.getScaledInstance(37, 50, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setText(p.toString());
		setIcon(new ImageIcon(profilSlika));
		
		if(isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			
		}else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
			
		}
		
		setEnabled(true);
		setFont(list.getFont());
		
		return this;
	}


}
