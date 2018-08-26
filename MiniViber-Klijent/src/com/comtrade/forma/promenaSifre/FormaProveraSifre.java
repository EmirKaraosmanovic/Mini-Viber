package com.comtrade.forma.promenaSifre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

import com.comtrade.domen.Profil;
import com.comtrade.domen.SlanjeMaila;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.transfer.TransferKlasa;
import com.toedter.calendar.JDateChooser;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class FormaProveraSifre extends JFrame implements Konstante {

	private JPanel contentPane;
	private JTextField tfUnosEMaila;
	private JTextField tfUnosGrada;
	private JDateChooser dcDatumRodjenja;

	
	public FormaProveraSifre() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 600, 500);
		setResizable(false);
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		setTitle("Provera sifre-miniViber");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		JLabel pozadina=null;
		try {
			pozadina = new JLabel( new ImageIcon(ImageIO.read(new File(POZADINA_SIFRA))));
			pozadina.setLocation(0, 0);
			pozadina.setSize(600, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.add(pozadina);
		
		JLabel lblEMail = new JLabel("Unesite vas E-mail:");
		lblEMail.setFont(new Font("Arial", Font.BOLD, 13));
		lblEMail.setBounds(95, 135, 180, 30);
		pozadina.add(lblEMail);
		
		JLabel lblDatumRodjenja = new JLabel("Oznacite datum rodjenja:");
		lblDatumRodjenja.setFont(new Font("Arial", Font.BOLD, 13));
		lblDatumRodjenja.setBounds(95, 211, 180, 30);
		pozadina.add(lblDatumRodjenja);
		
		JLabel lblUnesiteGradStanovanja = new JLabel("Unesite grad stanovanja:");
		lblUnesiteGradStanovanja.setFont(new Font("Arial", Font.BOLD, 13));
		lblUnesiteGradStanovanja.setBounds(95, 286, 180, 30);
		pozadina.add(lblUnesiteGradStanovanja);
		
		tfUnosEMaila = new JTextField();
		tfUnosEMaila.setBounds(285, 136, 150, 30);
		pozadina.add(tfUnosEMaila);
		tfUnosEMaila.setColumns(10);
		
		tfUnosGrada = new JTextField();
		tfUnosGrada.setColumns(10);
		tfUnosGrada.setBounds(285, 287, 150, 30);
		pozadina.add(tfUnosGrada);
		
		dcDatumRodjenja = new JDateChooser();
		dcDatumRodjenja.setDateFormatString("yyyy-MM-dd");
		dcDatumRodjenja.setBounds(285, 211, 150, 30);
		pozadina.add(dcDatumRodjenja);
		
		JButton btnPosaljiPodatke = new JButton("Posalji podatke na proveru");
		btnPosaljiPodatke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String eMail=tfUnosEMaila.getText();
				String gradStanovanja=tfUnosGrada.getText();
				Date izabranDatum=dcDatumRodjenja.getDate();
				java.sql.Date datumRodjenja=null;
				try {// ako nista ne unesem da mi ne izbacuje null poenter exeption
					datumRodjenja=new java.sql.Date(izabranDatum.getTime());
				} catch (Exception e) {
					
				}
				
				Profil proveraProfil=new Profil();
				proveraProfil.setEmail(eMail);
				proveraProfil.setGradStanovanja(gradStanovanja);
				proveraProfil.setDatumRodjenja(datumRodjenja);
				
				TransferKlasa tk=new TransferKlasa();
				tk.setOperacija(PROVERA_PODATAKA);
				tk.setKlijentObjekat(proveraProfil);
				Komunikacija.vratiInstancu().posalji(tk);
				
				tk=Komunikacija.vratiInstancu().procitaj();
				proveraProfil=(Profil) tk.getServerObjekat_odgovor();
				if(proveraProfil.getIdProfila()!=0) {
					JOptionPane.showMessageDialog(null, tk.getServerPoruka_odgovor());
					SlanjeMaila sm=new SlanjeMaila();
					sm.posaljiMail(eMail, "Lozinka na miniViber drustvenoj mrezi", "Vasa lozinka je: "+proveraProfil.getSifra());
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, tk.getServerPoruka_odgovor());
				}
				
			}
		});
		btnPosaljiPodatke.setBackground(new Color(230, 230, 250));
		btnPosaljiPodatke.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPosaljiPodatke.setBounds(159, 371, 200, 30);
		pozadina.add(btnPosaljiPodatke);
		
		JLabel lblPromenaSifre = new JLabel("ZABORAVILI STE LOZINKU?");
		pozadina.add(lblPromenaSifre);
		lblPromenaSifre.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		lblPromenaSifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromenaSifre.setBounds(64, 66, 400, 40);
	}
}
