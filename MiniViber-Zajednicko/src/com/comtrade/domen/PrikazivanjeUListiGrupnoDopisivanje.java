package com.comtrade.domen;

import java.awt.Color;

import java.awt.Component;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class PrikazivanjeUListiGrupnoDopisivanje  extends DefaultListCellRenderer implements ListCellRenderer<Object> {

	private static int indexPromene=-1;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		
		Grupa g=(Grupa) value;
		Image profilSlika = null;
		try {
			Image slika = ImageIO.read(new File(g.getSlikaGrupe()));
			profilSlika=slika.getScaledInstance(37, 50, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setText(g.toString());
		setIcon(new ImageIcon(profilSlika));
		
		if(isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			
		}else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
			
		}
		if(index==indexPromene) {
			setBackground(Color.GREEN);
		}
		
		setEnabled(true);
		setFont(list.getFont());
		
		return this;
	}

	public static void oznaciUListiAktivnih(int i) {
		
		indexPromene=i;
	}




}
