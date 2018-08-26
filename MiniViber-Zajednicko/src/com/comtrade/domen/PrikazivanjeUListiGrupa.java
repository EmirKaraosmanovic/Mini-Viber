package com.comtrade.domen;

import java.awt.Component;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class PrikazivanjeUListiGrupa extends DefaultListCellRenderer implements ListCellRenderer<Object>{
	

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,boolean cellHasFocus) {

		Grupa g=(Grupa) value;
		setText(g.toString());
		Image slikaGrupa = null;
		try {
			Image slika = ImageIO.read(new File(g.getSlikaGrupe()));
			slikaGrupa=slika.getScaledInstance(37, 50, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setIcon(new ImageIcon(slikaGrupa));
		
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
