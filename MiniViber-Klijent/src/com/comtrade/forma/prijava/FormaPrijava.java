package com.comtrade.forma.prijava;


import java.awt.*;


import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.*;
import javax.swing.*;

import javax.swing.border.*;

import com.comtrade.domen.*;
import com.comtrade.forma.admin.AdminForma;
import com.comtrade.forma.profil.*;
import com.comtrade.forma.promenaSifre.FormaProveraSifre;
import com.comtrade.forma.registracija.FormaRegistracija;
import com.comtrade.komunikacija.*;
import com.comtrade.konstante.*;
import com.comtrade.transfer.*;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings({ "unused", "serial","unchecked" })
public class FormaPrijava extends JFrame implements Konstante{

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfKorisnickoIme;
	private JTextField tfEmail;
	private JPasswordField pfNovaSifra;
	private JPasswordField pfPotvrdaSifre;
	private JPasswordField pfSifra;
	private JButton btnPrijava;
	private JButton btnKreiraj;
	private JLabel lblNaslov;
	private JLabel lblObavestenje;
	private JLabel lblZaboraviliSteLozinku;
	private Map<String, Object> podaciProfila=new HashMap<>();
	private JLabel pozadina_1;
	private JPanel panelPrijava;
	private JPanel panelRegistracija;
	private JLabel lblKorisnikoIme;
	private JLabel lblEmail;
	private JLabel lblSifra;
	private JLabel lblPotvrdiSifru;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaPrijava frame = new FormaPrijava();
					
					frame.setResizable(false);
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormaPrijava() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 900, 600);
		setTitle("Prijava-miniViber");
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPrijava = new JPanel();
		panelPrijava.setBounds(0, 0, 900, 130);
		contentPane.add(panelPrijava);
		panelPrijava.setLayout(null);
		JLabel pozadina1=null;
		try {
			pozadina1 = new JLabel( new ImageIcon(ImageIO.read(new File(POZADINA2))));
			pozadina1.setLocation(0, 0);
			pozadina1.setSize(900, 130);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelPrijava.add(pozadina1);
		
		
		
		panelRegistracija = new JPanel();
		panelRegistracija.setBounds(0, 131, 900, 450);
		contentPane.add(panelRegistracija);
		panelRegistracija.setLayout(null);
		
		JLabel pozadina=null;
		try {
			pozadina = new JLabel( new ImageIcon(ImageIO.read(new File(POZADINA1))));
			pozadina.setLocation(0, 0);
			pozadina.setSize(900, 450);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelRegistracija.add(pozadina);
		
		pfSifra = new JPasswordField();
		pfSifra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				pfSifra.setText("");
				
			}
		});
		pfSifra.setFont(new Font("Arial", Font.ITALIC, 13));
		pfSifra.setBounds(520, 71, 138, 30);
		pfSifra.setText("Šifra");
		pozadina1.add(pfSifra);
		
		tfIme = new JTextField();
		tfIme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfIme.setText("");
			}
		});
		tfIme.setFont(new Font("Arial", Font.ITALIC, 13));
		tfIme.setBounds(520, 17, 138, 30);
		tfIme.setText("Korisnicko ime");
		pozadina1.add(tfIme);
		
		btnPrijava = new JButton("Prijava");
		btnPrijava.setBackground(new Color(230, 230, 250));
		btnPrijava.setBounds(724, 40, 128, 30);
		btnPrijava.setFont(new Font("Arial", Font.PLAIN, 13));
		pozadina1.add(btnPrijava);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(489, 58, 200, 2);
		pozadina1.add(panel);
		
		lblZaboraviliSteLozinku = new JLabel("Zaboravili ste sifru?");
		lblZaboraviliSteLozinku.setFont(new Font("Arial", Font.BOLD, 12));
		pozadina1.add(lblZaboraviliSteLozinku);
		lblZaboraviliSteLozinku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				FormaProveraSifre fps=new FormaProveraSifre();
				fps.setVisible(true);
				lblZaboraviliSteLozinku.setVisible(false);
				
			}
		});
		lblZaboraviliSteLozinku.setHorizontalAlignment(SwingConstants.CENTER);
		lblZaboraviliSteLozinku.setBounds(723, 72, 128, 19);
		
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				TransferKlasa tk=new TransferKlasa();
				String korIme=tfIme.getText();
				String sifra=String.valueOf(pfSifra.getPassword());
				Profil prof=new Profil();
				prof.setKorisnickoIme(korIme);
				prof.setSifra(sifra);
				tk.setOperacija(PRIJAVA);
				tk.setKlijentObjekat(prof);
				Komunikacija.vratiInstancu().posalji(tk);
				
				TransferKlasa tkk=Komunikacija.vratiInstancu().procitaj();
				
				if(tkk.getOperacija()==USPESNA_OPERACIJA) {
					podaciProfila= (Map<String, Object>) tkk.getServerObjekat_odgovor();
					Profil profil=(Profil) podaciProfila.get("upisanProfil");
					if(profil.getStatus()==ADMIN_STATUS) {
						AdminForma af=new AdminForma(podaciProfila);
						af.setVisible(true);
						setVisible(false);
					}else {
						ProfilForma pf=new ProfilForma(podaciProfila);
						pf.setVisible(true);
						setVisible(false);
					}
				}else {
					JOptionPane.showMessageDialog(null, tkk.getServerPoruka_odgovor());
					lblZaboraviliSteLozinku.setVisible(true);
				}
				tfIme.setText("");
				pfSifra.setText("");
				
			}
		});
			
		btnKreiraj = new JButton("Kreiraj profil");
		btnKreiraj.setBackground(new Color(230, 230, 250));
		btnKreiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String korIme=tfKorisnickoIme.getText();
				String email=tfEmail.getText();
				String novaSifra=String.valueOf(pfNovaSifra.getPassword());
				String potvrdaSifre=String.valueOf(pfPotvrdaSifre.getPassword());
				if(!korIme.equals("") && !email.equals("") && !novaSifra.equals("") && !potvrdaSifre.equals("") ) {
					if(novaSifra.equals(potvrdaSifre)) {
						Profil profil=new Profil();
						profil.setKorisnickoIme(korIme);
						profil.setEmail(email);
						profil.setSifra(novaSifra);
						profil.setIme(" ");
						profil.setPrezime(" ");
						profil.setDatumRodjenja(new Date(new java.util.Date().getTime()));
						profil.setDatumPrijave(new Date(new java.util.Date().getTime()));
						profil.setZanimanje(" ");
						profil.setPol(" ");
						profil.setDrzava(" ");
						profil.setGradStanovanja(" ");
						profil.setoMeni("Opiši sebe u nekoliko recenica...");
						profil.setStatus(STATUS_KORISNIKA);
						profil.setVidljivost(PROFIL_VIDLJIV);
						profil.setSlikaProfila(SLIKA_PROFIL_NEVIDLJIV);
						TransferKlasa tkk=new TransferKlasa();
						tkk.setOperacija(REGISTRACIJA_PROVERA);
						tkk.setKlijentObjekat(profil);
						Komunikacija.vratiInstancu().posalji(tkk);
						
						TransferKlasa tks=Komunikacija.vratiInstancu().procitaj();
						if(tks.getOperacija()==MOGUCA_REGISTRACIJA) {
							FormaRegistracija fr=new FormaRegistracija((Profil) tks.getServerObjekat_odgovor());
							fr.setVisible(true);
							setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, tks.getServerPoruka_odgovor());
							srediFormu();
						}
					
					}else {
						obavestenjePogresneSifre();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Niste uneli sva polja.");
					
					srediFormu();
				}
			}
		});
		btnKreiraj.setBounds(396, 373, 139, 30);
		pozadina.add(btnKreiraj);
		btnKreiraj.setFont(new Font("Arial", Font.PLAIN, 13));
		
		lblNaslov = new JLabel("KREIRAJ NOVI PROFIL");
		lblNaslov.setFont(new Font("Bookman Old Style", Font.PLAIN, 22));
		lblNaslov.setBounds(396, 21, 280, 30);
		pozadina.add(lblNaslov);
		
		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.setBounds(396, 89, 267, 30);
		pozadina.add(tfKorisnickoIme);
		tfKorisnickoIme.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(396, 153, 267, 30);
		pozadina.add(tfEmail);
		
		pfNovaSifra = new JPasswordField();
		pfNovaSifra.setBounds(396, 217, 267, 30);
		pozadina.add(pfNovaSifra);
		
		pfPotvrdaSifre = new JPasswordField();
		pfPotvrdaSifre.setBounds(396, 280, 267, 30);
		pozadina.add(pfPotvrdaSifre);
		
		lblKorisnikoIme = new JLabel("Korisnicko ime:");
		lblKorisnikoIme.setFont(new Font("Arial", Font.BOLD, 13));
		lblKorisnikoIme.setBounds(396, 62, 194, 23);
		pozadina.add(lblKorisnikoIme);
		
		lblEmail = new JLabel("Email adresa:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		lblEmail.setBounds(396, 130, 194, 23);
		pozadina.add(lblEmail);
		
		lblSifra = new JLabel("Sifra:");
		lblSifra.setFont(new Font("Arial", Font.BOLD, 13));
		lblSifra.setBounds(396, 194, 194, 23);
		pozadina.add(lblSifra);
		
		lblPotvrdiSifru = new JLabel("Potvrdi šifru:");
		lblPotvrdiSifru.setFont(new Font("Arial", Font.BOLD, 13));
		lblPotvrdiSifru.setBounds(396, 258, 194, 23);
		pozadina.add(lblPotvrdiSifru);
		
		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(396, 332, 267, 30);
		pozadina.add(lblObavestenje);
		
	}
	
	private void srediFormu() {
		
		tfKorisnickoIme.setText("");
		tfEmail.setText("");
		pfNovaSifra.setText("");
		pfPotvrdaSifre.setText("");
		pfNovaSifra.setBackground(Color.WHITE);
		pfPotvrdaSifre.setBackground(Color.WHITE);
		lblObavestenje.setText("");
		
		
	}
	
	private void obavestenjePogresneSifre() {
		pfNovaSifra.setBackground(Color.RED);
		pfPotvrdaSifre.setBackground(Color.RED);
		pfNovaSifra.setText("");
		pfPotvrdaSifre.setText("");
		lblObavestenje.setText("Unete šifre nisu identicne.");
		
	}
}
