package com.comtrade.forma.registracija;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import com.comtrade.domen.*;
import com.comtrade.forma.profil.ProfilForma;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.*;
import com.comtrade.transfer.TransferKlasa;
import com.toedter.calendar.*;
import com.toedter.components.*;

import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;

@SuppressWarnings({ "serial", "unused" })
public class FormaRegistracija extends JFrame implements Konstante{

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfZanimanje;
	private JTextField tfGradStanovanja;
	private JComboBox<String> cbPol;
	private JComboBox<String> cbDrzava;
	private JDateChooser dcDatumRodjenja;
	private String pol;
	private String drzava;
	private Profil profil=new Profil();
	private Date datumRodjenja;
	private java.util.Date datum;
	private JButton btnKreirajProfil;


	public FormaRegistracija(Profil prof) {
		
		this.profil=prof;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 600, 600);
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		setResizable(false);
		setTitle("Registracija-miniViber");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNaslov = new JLabel("UNESI SVOJE PODATKE");
		lblNaslov.setBounds(163, 36, 286, 35);
		lblNaslov.setFont(new Font("Castellar", Font.PLAIN, 22));
		contentPane.add(lblNaslov);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIme.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIme.setBounds(62, 104, 105, 25);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrezime.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPrezime.setBounds(62, 157, 105, 26);
		contentPane.add(lblPrezime);
		
		JLabel lblZanimanje = new JLabel("Zanimanje:");
		lblZanimanje.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZanimanje.setFont(new Font("Arial", Font.PLAIN, 13));
		lblZanimanje.setBounds(62, 207, 105, 24);
		contentPane.add(lblZanimanje);
		
		JLabel lblPol = new JLabel("Pol:");
		lblPol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPol.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPol.setBounds(62, 315, 105, 25);
		contentPane.add(lblPol);
		
		JLabel lblDatumRodjenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRodjenja.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatumRodjenja.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDatumRodjenja.setBounds(62, 259, 105, 28);
		contentPane.add(lblDatumRodjenja);
		
		JLabel lblGradStanovanja = new JLabel("Grad stanovanja:");
		lblGradStanovanja.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGradStanovanja.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGradStanovanja.setBounds(62, 413, 105, 26);
		contentPane.add(lblGradStanovanja);
		
		JLabel lblDrzava = new JLabel("Država:");
		lblDrzava.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDrzava.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDrzava.setBounds(62, 361, 105, 26);
		contentPane.add(lblDrzava);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(76, 82, 443, 4);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(204, 96, 4, 360);
		contentPane.add(panel_1);
		
		tfIme = new JTextField();
		tfIme.setForeground(new Color(0, 0, 205));
		tfIme.setBounds(252, 107, 173, 22);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setForeground(new Color(0, 0, 205));
		tfPrezime.setColumns(10);
		tfPrezime.setBounds(252, 157, 173, 22);
		contentPane.add(tfPrezime);
		
		tfZanimanje = new JTextField();
		tfZanimanje.setForeground(new Color(0, 0, 205));
		tfZanimanje.setColumns(10);
		tfZanimanje.setBounds(252, 210, 173, 22);
		contentPane.add(tfZanimanje);
		
		dcDatumRodjenja = new JDateChooser();
		dcDatumRodjenja.setBackground(new Color(100, 149, 237));
		dcDatumRodjenja.setForeground(new Color(0, 0, 205));
		dcDatumRodjenja.setDateFormatString("yyyy-MM-dd");
		dcDatumRodjenja.setBounds(252, 259, 173, 25);
		contentPane.add(dcDatumRodjenja);
		
		cbPol = new JComboBox<String>();
		cbPol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pol=(String) cbPol.getSelectedItem();
			}
		});
		cbPol.setForeground(new Color(0, 0, 205));
		cbPol.setBounds(252, 318, 173, 25);
		contentPane.add(cbPol);
		
		cbDrzava = new JComboBox<String>();
		cbDrzava.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drzava=(String) cbDrzava.getSelectedItem();
			}
		});
		cbDrzava.setForeground(new Color(0, 0, 205));
		cbDrzava.setBounds(250, 361, 175, 25);
		contentPane.add(cbDrzava);
		
		tfGradStanovanja = new JTextField();
		tfGradStanovanja.setForeground(new Color(0, 0, 205));
		tfGradStanovanja.setBounds(252, 417, 173, 25);
		contentPane.add(tfGradStanovanja);
		tfGradStanovanja.setColumns(10);
		
		btnKreirajProfil = new JButton("KREIRAJ PROFIL");
		btnKreirajProfil.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				
				String ime=tfIme.getText();
				String prezime=tfPrezime.getText();
				String zanimanje=tfZanimanje.getText();
				datum= dcDatumRodjenja.getDate();// uzimanje datuma iz kalendara
				try {
					datumRodjenja=new Date(datum.getTime());
				} catch (Exception e) {
					
				}
				Date datumPrijave=new Date(new java.util.Date().getTime());// uzimanje trenutnog datuma
				String gradStanovanja=tfGradStanovanja.getText();
				if(!ime.equals("") && !prezime.equals("") && !zanimanje.equals("") && !pol.equals("") &&
						datumRodjenja!=null && !drzava.equals("") && !gradStanovanja.equals("") ) {
				
					profil.setIme(ime);
					profil.setPrezime(prezime);
					profil.setDatumRodjenja(datumRodjenja);
					profil.setDatumPrijave(datumPrijave);
					profil.setZanimanje(zanimanje);
					profil.setPol(pol);
					profil.setDrzava(drzava);
					profil.setGradStanovanja(gradStanovanja);
					profil.setoMeni("Opiši sebe u nekoliko recenica...");
					profil.setStatus(STATUS_KORISNIKA);
					profil.setVidljivost(PROFIL_VIDLJIV);
					if(profil.getPol().equals(MUSKI_POL)) {
						profil.setSlikaProfila("SlikeProfila/muskiProfil.jpg");
					}else {
						profil.setSlikaProfila("SlikeProfila/zenskiProfil.jpg");
					}
					TransferKlasa transfer=new TransferKlasa();
					transfer.setOperacija(REGISTRACIJA_UPIS);
					transfer.setKlijentObjekat(profil);
					Komunikacija.vratiInstancu().posalji(transfer);
					
					TransferKlasa tk=Komunikacija.vratiInstancu().procitaj();
					if(tk.getOperacija()==USPESNA_OPERACIJA) {
						JOptionPane.showMessageDialog(null, tk.getServerPoruka_odgovor());
						ProfilForma pf=new ProfilForma(tk.getServerObjekat_odgovor());
						pf.setVisible(true);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, tk.getServerPoruka_odgovor());
						urediPolja();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
					oznaciPraznaPolja();
				}
			}
		});
		btnKreirajProfil.setFont(new Font("Arial", Font.PLAIN, 13));
		btnKreirajProfil.setBounds(252, 469, 173, 25);
		contentPane.add(btnKreirajProfil);
		
		postaviPol();
		postaviDrzave();
	}

	private void postaviPol() {
		cbPol.addItem("");
		cbPol.addItem("Muški");
		cbPol.addItem("Ženski");
	}

	private void postaviDrzave() {
		cbDrzava.addItem("");
		String [] nizDrzava= Locale.getISOCountries();// Preko Klase Locale i statik metode uzmimamo u niz stringova sve drzave
		for(String d:nizDrzava) {// prolazimo kroz niz
			Locale drzava=new Locale("sr", d);
			cbDrzava.addItem(drzava.getDisplayCountry());// ovom metodom uzimamo naziv drzave i postavljamo je u ComboBox
		}
	}
	private void oznaciPraznaPolja() {
		if(tfIme.getText().equals("")) {
			tfIme.setBackground(Color.RED);
		}else {
			tfIme.setBackground(Color.WHITE);
		}
		if(tfPrezime.getText().equals("")) {
			tfPrezime.setBackground(Color.RED);
		}else {
			tfPrezime.setBackground(Color.WHITE);
		}
		if(tfZanimanje.getText().equals("")) {
			tfZanimanje.setBackground(Color.RED);	
		}else {
			tfZanimanje.setBackground(Color.WHITE);
		}
		if(pol.equals("")) {
			cbPol.setBackground(Color.RED);	
		}else {
			cbPol.setBackground(Color.WHITE);
		}
		if(datumRodjenja==null) {
			dcDatumRodjenja.getDateEditor().getUiComponent().setBackground(Color.RED);
		}else {
			dcDatumRodjenja.getDateEditor().getUiComponent().setBackground(Color.WHITE);	
		}
		if(drzava.equals("")) {
			cbDrzava.setBackground(Color.RED);	
		}else {
			cbDrzava.setBackground(Color.WHITE);
		}
		if(tfGradStanovanja.getText().equals("")) {
			tfGradStanovanja.setBackground(Color.RED);	
		}else {
			tfGradStanovanja.setBackground(Color.WHITE);
		}
	}
	private void urediPolja() {
		tfIme.setBackground(Color.WHITE);
		tfIme.setText("");
		tfPrezime.setBackground(Color.WHITE);
		tfPrezime.setText("");
		cbPol.setSelectedIndex(0);
		cbPol.setBackground(Color.WHITE);
		cbDrzava.setSelectedIndex(0);
		cbDrzava.setBackground(Color.WHITE);
		dcDatumRodjenja.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		dcDatumRodjenja.getDateEditor().setDate(null);
		tfGradStanovanja.setText("");
		tfGradStanovanja.setBackground(Color.WHITE);
		tfZanimanje.setText("");
		tfZanimanje.setBackground(Color.WHITE);
	}
	
}
