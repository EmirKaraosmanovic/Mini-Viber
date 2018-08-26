package com.comtrade.forma.profil;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.management.ImmutableDescriptor;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.text.IconView;
import javax.swing.text.StyledDocument;

import com.comtrade.domen.*;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.niti.NitObradeKlijent;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.Spring;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings({ "serial", "unused", "unchecked"})
public class ProfilForma extends JFrame implements Konstante {

	private JPanel contentPane, panelPredlozi, panelPretragaLogo, panelMeni, panelPocetna, panelProfil, panelPrijatelji,
			panelPoruke, panelGrupa, panelKreiranjeGrupa, panelPrikazGrupe,panelIzmenaProfila;
	private JScrollPane spPretraga;
	private JScrollPane spOMeni;
	private JScrollPane spListaPrijatelja;
	private JScrollPane spPrimljeniZahtevi;
	private JScrollPane spPoslatiZahtevi;
	private JScrollPane spDopisivanja;
	private JScrollPane spPoruke;
	private JScrollPane spMojeGrupe;
	private JScrollPane spPrimljeniZahteviZaGrupe;
	private JScrollPane spPoslatiZahteviZaGrupe;
	private JScrollPane spClanoviGrupe;
	private JScrollPane spDodajNoveClanove;
	private JScrollPane spOpisGrupe;
	private JScrollPane spIzmenaOMeni;
	private JScrollPane spPredlogPrijatelja;
	private JTextArea taPrikazPoruka;
	private JTextPane tpMogucnosti;
	private JTextPane tpLicniPodaci;
	private JTextPane textPane;
	private JTextPane tpOMeni;
	private JTextPane tpOpisGrupe;
	private JTextPane tpIzmenaOMeni;
	private JList<Object> listPretraga;
	private JList<Object> listPrijatelji;
	private JList<Object> listGrupeZaDopisivanje;
	private JList<Object> listPrimljeniZahtevi;
	private JList<Object> listPoslatiZahtevi;
	private JList<Object> listAkrivniPrijatelji;
	private JList<Object> listMojeGrupe;
	private JList<Object> listPrimljeniZahteviZaGrupe;
	private JList<Object> listPoslatiZahteviZaGrupe;
	private JList<Object> listClanoviZaDodavanje;
	private JList<Object> listClanoviGrupe;
	private JList<Object> listPredlogPrijatelja;
	private JTextField tfPretraga;
	private JTextField tfUnosPoruke;
	private JTextField tfUnesiImeGrupe;
	private JTextField tfUnesiOpisGrupe;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfZanimanje;
	private JTextField tfGradStanovanja;
	private JTextField tfSifra;
	private JButton btnPocetna;
	private JButton btnProfil;
	private JButton btnPrijatelji;
	private JButton btnPoruke;
	private JButton btnGrupe;
	private JButton btnIzbrisi;
	private JButton btnBlokirajPrihvati;
	private JButton btnPrikaziProfil;
	private JButton btnPosaljiPoruku;
	private JButton btnIzmeniProfil;
	private JButton btnPosalji;
	private JButton btnPrikaziGrupu;
	private JButton btnPrihvatiZahtev;
	private JButton btnKreirajIzmeniGrupu;
	private JButton btnIzaberiSlikuGrupe;
	private JButton btnKreirajGrupu;
	private JButton btnIzmeniGrupu;
	private JButton btnAkcijaZaGrupu;
	private JButton btnIzbrisiClana;
	private JButton btnIzbrisiZahtevZaGrupu;
	private JButton btnGrupnePoruke;
	private JButton btnOdjava;
	private JButton btnIzaberiSlikuProfila;
	private JButton btnPosaljiPodatke;
	private JButton btnVidljivostProfila;
	private JButton btnPrikaziPredlog;
	private JRadioButton rbGrupe;
	private JRadioButton rbProfil;
	private JLabel lblObavestenje;
	private JLabel lblDobrodoslica;
	private JLabel lblBrojPrijatelja;
	private JLabel lblBrojGrupa;
	private JLabel lblOMeni;
	private JLabel lblSlikaProfila;
	private JLabel lblSlikaProfilaPoc;
	private JLabel lblPrimljeniZahtevi;
	private JLabel lblPorukeOd;
	private JLabel lblPogledajProfilGrupu;
	private JLabel lblPosaljiPoruku;
	private JLabel lblMojeGrupe;
	private JLabel lblPrimljeniZahteviZaGrupe;
	private JLabel lblPoslatiZahteviZaGrupe;
	private JLabel lblKreirajIzmeniGrupu;
	private JLabel lblSlikaGrupe;
	private JLabel lblUnesiImeGrupe;
	private JLabel lblUnesiOpisGrupe;
	private JLabel lblIzaberiNovogClana;
	private JLabel lblOGrupi;
	private JLabel lblPrikazGrupe;
	private JLabel lblNazivGrupe;
	private JLabel lblSlikaGrupePrikaz;
	private JLabel lblClanoviGrupe;
	private JLabel lblPrikazDopisivanja;
	private JLabel lblIzmenaSlike;
	private JLabel lblLogo;
	private JLabel lblKreator;
	private JLabel lblKorImeKreatora;
	private JLabel lblPredlogPrijatelja;
	private JComboBox<String> cbNovePoruke;
	private JComboBox<String> cbPromenaDrzave;
	private JComboBox<String> cbIzmenaPol;
	private JDateChooser dcIzmenaDatumaRodjenja;
	
	private int selektAktivni = -1;
	private int selektPrijatelj = -1;
	private int brojPrivatnePoruke;
	private int brojGrupnePoruke;
	private int indeksGrupnogDopisivanja = -1;
	private int indeksGrupe = -1;
	private int vidljivostProfila=1;
	private int indeksClana = -1;
	private String obavestenjeNeprocitanePoruke = " ----------------------Neprocitane poruke----------------------";
	private String korisnikNijeAktivan = " ------------------Korisnik nije aktivan------------------";
	private String korisnikIzComboBox;
	private String izabranKljuc;
	private String lokacijaSlike;
	private String drzava;
	private String pol;
	private String lokacijaSlikeProfilaPreIzmene;
	private String lokacijaSlikeGrupePreIzmene;
	private Profil profil;
	private Profil pretrgaProfil;
	private Profil izabranPrijatelj;
	private Profil aktivniPrijatelj;
	private Grupa izabranaGrupa;
	private ZahtevZaPrijateljstvo izabranZahtev;
	private ClanGrupe izabranClan;
	private Socket socket;
	private Date datumIzmena;
	private List<Profil> listaPrijatelja = new ArrayList<>();
	private List<Profil> listaAktivniPrijatelji = new ArrayList<>();
	private List<ZahtevZaPrijateljstvo> primljeniZahtevi = new ArrayList<>();
	private List<ZahtevZaPrijateljstvo> poslatiZahtevi = new ArrayList<>();
	private List<Profil> blokiraniKontakti = new ArrayList<>();
	private List<Grupa> mojeGrupe = new ArrayList<>();
	private List<ClanGrupe> primljeniZahteviZaGrupe = new ArrayList<>();
	private List<ClanGrupe> mojiPoslatiZahteviZaGrupe = new ArrayList<>();
	private List<Profil> sviProfili = new ArrayList<>();
	private List<Profil> listaPredlogPrijatelja=new ArrayList<>();
	private Map<String, Object> podaciProfila = new HashMap<>();
	private Map<String, Object> kontakti = new HashMap<>();
	private Map<String, Object> privatnePoruke = new HashMap<>();
	private Map<String, List<GrupnaPoruka>> grupnePoruke = new HashMap<>();
	private Map<String, List<GrupnaPoruka>> noveGrupnePoruke = new HashMap<>();
	private Map<Integer, Integer> brojProcitanihGrupnihPoruka = new HashMap<>();
	private Map<String, List<PrivatnaPoruka>> trenutniRazgovori = new HashMap<>();// u ovu mapu hocu da upisem sve razgovore i onda ako se desi da se odjavim da ih upisem u bazu
	private Map<String, Object> grupe = new HashMap<>();
	private Map<String, Object> sveGrupe = new HashMap<>();
	private Map<String, Object> pretragaGrupa;
	private Map<String, Object> podaciGrupe;
	private DefaultListModel<Object> dlm = new DefaultListModel<Object>();
	private DefaultListModel<Object> dlmPrijatelji = new DefaultListModel<Object>();
	private DefaultListModel<Object> dlmPrimljeniZahtevi = new DefaultListModel<Object>();
	private DefaultListModel<Object> dlmPoslatiZahtevi = new DefaultListModel<Object>();
	private DefaultListModel<Object> dlmAktivniPrijatelji = new DefaultListModel<>();
	private DefaultListModel<Object> dlmGrupe = new DefaultListModel<>();
	private DefaultListModel<Object> dlmPrimljeniZahteviGrupe = new DefaultListModel<>();
	private DefaultListModel<Object> dlmPoslatiZahteviGrupe = new DefaultListModel<>();
	private DefaultListModel<Object> dlmClanoviGrupe = new DefaultListModel<>();
	private DefaultListModel<Object> dlmPredlogPrijatelja= new DefaultListModel<>();
	private ButtonGroup bg = new ButtonGroup();

	
	public ProfilForma(Object object) {

		podaciProfila = (Map<String, Object>) object;
		profil = (Profil) podaciProfila.get("upisanProfil");
		sviProfili = (List<Profil>) podaciProfila.get("sviProfili");

		kontakti = (Map<String, Object>) podaciProfila.get("kontakti");
		listaPrijatelja = (List<Profil>) kontakti.get("prijatelji");
		listaAktivniPrijatelji = (List<Profil>) podaciProfila.get("aktivniPrijatelji");
		poslatiZahtevi = (List<ZahtevZaPrijateljstvo>) kontakti.get("poslatiZahtevi");
		primljeniZahtevi = (List<ZahtevZaPrijateljstvo>) kontakti.get("primljeniZahtevi");
		blokiraniKontakti = (List<Profil>) kontakti.get("blokiraniKontakti");

		grupe = (Map<String, Object>) podaciProfila.get("grupe");
		sveGrupe = (Map<String, Object>) grupe.get("sveGrupe");
		mojeGrupe = (List<Grupa>) grupe.get("mojeGrupe");
		mojiPoslatiZahteviZaGrupe = (List<ClanGrupe>) grupe.get("poslatiZahtevi");

		postaviPrimljeneZahteve(mojeGrupe);

		socket = Komunikacija.vratiInstancu().getKlijentSocket();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("miniViber");
		setBounds(100, 50, 1200, 700);
		setMinimumSize(new Dimension(1000, 600));
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int odjava=JOptionPane.showConfirmDialog(null, "Da li želite da se odjavite?","Odjava",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(odjava==JOptionPane.YES_OPTION) {
					odjavaKorisnika();
				}
				
			}

		});

		panelPretragaLogo = new JPanel();
		panelPretragaLogo.setBackground(Color.WHITE);
		panelPretragaLogo.setBorder(null);

		panelMeni = new JPanel();
		panelMeni.setBackground(Color.WHITE);
		panelMeni.setBorder(null);

		panelPocetna = new JPanel();
		panelPocetna.setBackground(Color.WHITE);
		panelPocetna.setBorder(null);

		panelProfil = new JPanel();
		panelProfil.setBackground(Color.WHITE);
		panelProfil.setBorder(null);

		panelPrijatelji = new JPanel();
		panelPrijatelji.setBackground(Color.WHITE);
		panelPrijatelji.setBorder(null);

		panelPoruke = new JPanel();
		panelPoruke.setBackground(Color.WHITE);
		panelPoruke.setBorder(null);

		panelGrupa = new JPanel();
		panelGrupa.setBackground(Color.WHITE);
		panelGrupa.setBorder(null);

		panelPrikazGrupe = new JPanel();
		panelPrikazGrupe.setBackground(Color.WHITE);
		panelPrikazGrupe.setBorder(null);

		panelKreiranjeGrupa = new JPanel();
		panelKreiranjeGrupa.setBackground(Color.WHITE);
		panelKreiranjeGrupa.setBorder(null);

		panelPredlozi = new JPanel();
		panelPredlozi.setBackground(new Color(230, 230, 250));
		panelPredlozi.setBorder(null);
		
		panelIzmenaProfila = new JPanel();
		panelIzmenaProfila.setBackground(Color.WHITE);
		panelIzmenaProfila.setBorder(null);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panelMeni, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addGap(4)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelPoruke, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)
								.addComponent(panelGrupa, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)	
								.addComponent(panelIzmenaProfila, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)
								.addComponent(panelPrijatelji, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)			
								.addComponent(panelPrikazGrupe, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)
								.addComponent(panelProfil, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)
								.addComponent(panelPocetna, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)
								.addComponent(panelKreiranjeGrupa, GroupLayout.PREFERRED_SIZE, 776, Short.MAX_VALUE)																
								)
						.addGap(4)
						.addComponent(panelPredlozi, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelPretragaLogo, GroupLayout.PREFERRED_SIZE, 1184, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panelPretragaLogo, GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE).addGap(4)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelPoruke, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
								.addComponent(panelGrupa, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)	
								.addComponent(panelIzmenaProfila, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
								.addComponent(panelPrijatelji, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)								
								.addComponent(panelPrikazGrupe, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)								
								.addComponent(panelProfil, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
								.addComponent(panelPocetna, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
								.addComponent(panelKreiranjeGrupa, GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)											
								.addComponent(panelPredlozi, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 558,Short.MAX_VALUE)
								.addComponent(panelMeni, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 558,Short.MAX_VALUE))));
		
		lblPredlogPrijatelja = new JLabel("PREDLOG PRIJATELJA");
		lblPredlogPrijatelja.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		lblPredlogPrijatelja.setHorizontalAlignment(SwingConstants.CENTER);
		
		spPredlogPrijatelja = new JScrollPane();
		
		btnPrikaziPredlog = new JButton("Prikazi profil");
		btnPrikaziPredlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(pretrgaProfil!=null) {
					prikaziProfil(pretrgaProfil, PROFIL_IZ_PRETRAGE);
				}
				
			}
		});
		btnPrikaziPredlog.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrikaziPredlog.setBackground(new Color(255, 255, 255));
		GroupLayout gl_panelPredlozi = new GroupLayout(panelPredlozi);
		gl_panelPredlozi.setHorizontalGroup(
			gl_panelPredlozi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPredlozi.createSequentialGroup()
					.addGap(5)
					.addComponent(lblPredlogPrijatelja, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(gl_panelPredlozi.createSequentialGroup()
					.addGap(5)
					.addComponent(spPredlogPrijatelja, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(gl_panelPredlozi.createSequentialGroup()
					.addGap(15)
					.addComponent(btnPrikaziPredlog, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(15))
		);
		gl_panelPredlozi.setVerticalGroup(
			gl_panelPredlozi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPredlozi.createSequentialGroup()
					.addGap(100)
					.addComponent(lblPredlogPrijatelja, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spPredlogPrijatelja, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPrikaziPredlog, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		
		listPredlogPrijatelja = new JList<Object>(dlmPredlogPrijatelja);
		listPredlogPrijatelja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				pretrgaProfil=(Profil) listPredlogPrijatelja.getSelectedValue();
			}
		});
		listPredlogPrijatelja.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		spPredlogPrijatelja.setViewportView(listPredlogPrijatelja);
		panelPredlozi.setLayout(gl_panelPredlozi);
		
		JLabel lblIzmenaProfila = new JLabel("IZMENA PROFILA: ");
		lblIzmenaProfila.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		lblIzmenaProfila.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblIzmenaSlike = new JLabel("");
		
		btnIzaberiSlikuProfila = new JButton("Izaberi sliku");
		btnIzaberiSlikuProfila.setBackground(new Color(230, 230, 250));
		btnIzaberiSlikuProfila.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				lokacijaSlike=null;
				JFileChooser biracFajla=new JFileChooser();
				biracFajla.showOpenDialog(null);
				File izabranFajl=biracFajla.getSelectedFile();
				File noviFajl=new File(FOLDER_SLIKE_PROFILA+profil.getKorisnickoIme()+".jpg");
				try {// kopitanje slike u folder klijenta
					Files.copy(izabranFajl.toPath(), noviFajl.toPath(),StandardCopyOption.REPLACE_EXISTING);
					lokacijaSlike=FOLDER_SLIKE_PROFILA+profil.getKorisnickoIme()+".jpg";
				} catch (Exception e) {
					ImageIcon novaSlika=new ImageIcon(new ImageIcon(profil.getSlikaProfila()).getImage().getScaledInstance(lblIzmenaSlike.getWidth(), lblIzmenaSlike.getHeight(), Image.SCALE_SMOOTH));
					return;
				}
				
				ImageIcon novaSlika=new ImageIcon(new ImageIcon(lokacijaSlike).getImage().getScaledInstance(lblIzmenaSlike.getWidth(), lblIzmenaSlike.getHeight(), Image.SCALE_SMOOTH));
				lblIzmenaSlike.setIcon(novaSlika); 
				
			}
		});
		btnIzaberiSlikuProfila.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblLblime = new JLabel("Ime:");
		lblLblime.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblZanimanje = new JLabel("Zanimanje");
		lblZanimanje.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblDrzava = new JLabel("Drzava:");
		lblDrzava.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblGradStanovanja = new JLabel("Grad stanovanja:");
		lblGradStanovanja.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja:");
		lblDatumRodjenja.setFont(new Font("Arial", Font.PLAIN, 13));
		
		tfIme = new JTextField();
		tfIme.setFont(new Font("Arial", Font.PLAIN, 13));
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setFont(new Font("Arial", Font.PLAIN, 13));
		tfPrezime.setColumns(10);
		
		tfZanimanje = new JTextField();
		tfZanimanje.setFont(new Font("Arial", Font.PLAIN, 13));
		tfZanimanje.setColumns(10);
		
		tfGradStanovanja = new JTextField();
		tfGradStanovanja.setFont(new Font("Arial", Font.PLAIN, 13));
		tfGradStanovanja.setColumns(10);
		
		cbPromenaDrzave = new JComboBox<String>();
		cbPromenaDrzave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drzava=(String) cbPromenaDrzave.getSelectedItem();
			}
		});
		cbPromenaDrzave.setFont(new Font("Arial", Font.PLAIN, 13));
		
		dcIzmenaDatumaRodjenja = new JDateChooser();
		dcIzmenaDatumaRodjenja.setDateFormatString("yyyy-MM-dd ");
		
		spIzmenaOMeni = new JScrollPane();
		
		btnPosaljiPodatke = new JButton("Posalji podatke");
		btnPosaljiPodatke.setBackground(new Color(230, 230, 250));
		btnPosaljiPodatke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sifra=tfSifra.getText();
				String ime=tfIme.getText();
				String prezime=tfPrezime.getText();
				String zanimanje=tfZanimanje.getText();
				String grad=tfGradStanovanja.getText();
				java.util.Date izabranDatum=dcIzmenaDatumaRodjenja.getDate();
				try {// ako nista ne unesem u kalendar javlja null poenter exaption
					datumIzmena=new Date(izabranDatum.getTime());
				} catch (Exception e) {
				}
				
				String oMeni=tpIzmenaOMeni.getText();
				
				if(!sifra.equals("") && !ime.equals("") && !prezime.equals("") && !zanimanje.equals("")  && datumIzmena!=null
						&& !drzava.equals("") && !grad.equals("") ) {
					
					Profil izmenaProfila=new Profil();
					
					izmenaProfila.setIdProfila(profil.getIdProfila());
					izmenaProfila.setKorisnickoIme(profil.getKorisnickoIme());
					izmenaProfila.setEmail(profil.getEmail());
					izmenaProfila.setSifra(sifra);
					izmenaProfila.setIme(ime);
					izmenaProfila.setPrezime(prezime);
					izmenaProfila.setDatumRodjenja(datumIzmena);
					izmenaProfila.setDatumPrijave(profil.getDatumPrijave());
					izmenaProfila.setZanimanje(zanimanje);
					izmenaProfila.setPol(pol);
					izmenaProfila.setDrzava(drzava);
					izmenaProfila.setGradStanovanja(grad);
					izmenaProfila.setoMeni(oMeni);
					izmenaProfila.setStatus(STATUS_KORISNIKA);
					izmenaProfila.setVidljivost(vidljivostProfila);

					if(lokacijaSlike!=null) {
						izmenaProfila.setSlikaProfila(lokacijaSlike);
					}else {
						izmenaProfila.setSlikaProfila(profil.getSlikaProfila());
					}
					
					TransferKlasa transfer=new TransferKlasa();
					transfer.setOperacija(IZMENA_PROFILA);
					transfer.setKlijentObjekat(izmenaProfila);
					Komunikacija.vratiInstancu().posalji(transfer);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
					oznaciPraznaPolja();
				}
				
			}
		});
		btnPosaljiPodatke.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblSifra = new JLabel("Lozinka:");
		lblSifra.setFont(new Font("Arial", Font.PLAIN, 13));
		tfSifra = new JTextField();
		tfSifra.setFont(new Font("Arial", Font.PLAIN, 13));
		tfSifra.setColumns(10);
		
		btnVidljivostProfila = new JButton("Profil vidljiv");
		btnVidljivostProfila.setBackground(new Color(230, 230, 250));
		btnVidljivostProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnVidljivostProfila.getText().equals("Profil vidljiv")) {
					btnVidljivostProfila.setText("Profil nije vidljiv");
					vidljivostProfila=0;
				}else if(btnVidljivostProfila.getText().equals("Profil nije vidljiv")) {
					btnVidljivostProfila.setText("Profil vidljiv");
					vidljivostProfila=1;
				}
				
			}
		});
		btnVidljivostProfila.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel lblPol = new JLabel("Izabeti pol:");
		lblPol.setFont(new Font("Arial", Font.PLAIN, 13));
		
		cbIzmenaPol = new JComboBox<String>();
		cbIzmenaPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pol=(String) cbIzmenaPol.getSelectedItem();
				
			}
		});
		cbIzmenaPol.addItem("");
		cbIzmenaPol.addItem("Muški");
		cbIzmenaPol.addItem("Ženski");
		
		
		JLabel lblIzmenaOMeni = new JLabel("Opisi sebe:");
		lblIzmenaOMeni.setFont(new Font("Arial", Font.PLAIN, 13));
		GroupLayout gl_panelIzmenaProfila = new GroupLayout(panelIzmenaProfila);
		gl_panelIzmenaProfila.setHorizontalGroup(
			gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
					.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addGap(280)
							.addComponent(lblIzmenaProfila, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
							.addGap(127)
							.addComponent(btnVidljivostProfila, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addGap(120)
							.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
										.addComponent(btnIzaberiSlikuProfila, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIzmenaSlike, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIzmenaOMeni, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
									.addGap(69)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblGradStanovanja, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfGradStanovanja, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addComponent(lblDrzava, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(cbPromenaDrzave, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addComponent(lblZanimanje, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfZanimanje, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addComponent(lblPrezime, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfPrezime, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addComponent(lblSifra, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfSifra, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addComponent(lblLblime, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfIme, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
													.addComponent(lblDatumRodjenja, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(dcIzmenaDatumaRodjenja, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
													.addComponent(lblPol, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(cbIzmenaPol, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))))
								.addComponent(spIzmenaOMeni, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
							.addGap(136))
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addGap(310)
							.addComponent(btnPosaljiPodatke, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelIzmenaProfila.setVerticalGroup(
			gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addGap(9)
							.addComponent(lblIzmenaProfila, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
									.addGap(10)
									.addComponent(lblIzmenaSlike, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnIzaberiSlikuProfila, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSifra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfSifra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLblime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfIme, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrezime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfPrezime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblZanimanje, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfZanimanje, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(cbPromenaDrzave, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDrzava, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblGradStanovanja, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfGradStanovanja, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDatumRodjenja, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(dcIzmenaDatumaRodjenja, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnVidljivostProfila, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelIzmenaProfila.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addComponent(lblPol, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addComponent(lblIzmenaOMeni, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(1))
						.addGroup(gl_panelIzmenaProfila.createSequentialGroup()
							.addComponent(cbIzmenaPol, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(spIzmenaOMeni, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPosaljiPodatke, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		tpIzmenaOMeni = new JTextPane();
		tpIzmenaOMeni.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		tpIzmenaOMeni.setSize(510,100);
		tpIzmenaOMeni.setMaximumSize(getSize());
		spIzmenaOMeni.setViewportView(tpIzmenaOMeni);
		panelIzmenaProfila.setLayout(gl_panelIzmenaProfila);

		lblPrikazGrupe = new JLabel("Prikaz grupe:");
		lblPrikazGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		lblNazivGrupe = new JLabel("");
		lblNazivGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		lblSlikaGrupePrikaz = new JLabel("");

		spOpisGrupe = new JScrollPane();

		spClanoviGrupe = new JScrollPane();

		spDodajNoveClanove = new JScrollPane();

		btnAkcijaZaGrupu = new JButton("");
		btnAkcijaZaGrupu.setBackground(new Color(230, 230, 250));
		btnAkcijaZaGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClanGrupe clan = new ClanGrupe();
				clan.setClan(profil);
				clan.setGrupa(izabranaGrupa);
				TransferKlasa tk = new TransferKlasa();
				switch (btnAkcijaZaGrupu.getText()) {
				case "Posalji zahtev":// odradjeno
					clan.setStatusClana(ZAHTEV_ZA_GRUPU);
					clan.setAdminStatus(OBICAN_CLAN_GRUPE);
					posaljiZahtevZaGrupu(clan, tk);
					btnAkcijaZaGrupu.setText("Izbrisi zahtev");
					btnAkcijaZaGrupu.setBackground(Color.RED);
					break;
				case "Izbrisi zahtev":
					izbrisiPoslatZahtevZaGrupu(clan, tk);
					btnAkcijaZaGrupu.setText("Posalji zahtev");
					btnAkcijaZaGrupu.setBackground(Color.green);
					break;
				case "Napusti grupu":// odradjeno
					napustiGrupu(clan, tk);
					btnAkcijaZaGrupu.setText("Posalji zahtev");
					btnAkcijaZaGrupu.setBackground(Color.green);
					break;
				case "Dodaj clana":// odradjeno
					lblIzaberiNovogClana.setVisible(true);
					spDodajNoveClanove.setVisible(true);


					break;

				default:
					break;
				}

			}

		});
		btnAkcijaZaGrupu.setFont(new Font("Arial", Font.PLAIN, 13));

		btnIzmeniGrupu = new JButton("Izmeni grupu");
		btnIzmeniGrupu.setBackground(new Color(230, 230, 250));
		btnIzmeniGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nazivGrupe = lblNazivGrupe.getText();
				podaciGrupe=(Map<String, Object>) sveGrupe.get(nazivGrupe);
				izabranaGrupa=(Grupa) podaciGrupe.get("grupa");
				prikaziPanelKreirajGrupu(IZMENA_GRUPE,podaciGrupe);
			}

			
		});
		btnIzmeniGrupu.setFont(new Font("Arial", Font.PLAIN, 13));

		lblClanoviGrupe = new JLabel("Clanovi grupe");
		lblClanoviGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblClanoviGrupe.setHorizontalAlignment(SwingConstants.CENTER);

		lblIzaberiNovogClana = new JLabel("Izaberi clana");
		lblIzaberiNovogClana.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzaberiNovogClana.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		lblOGrupi = new JLabel("Opis grupe:");
		lblOGrupi.setHorizontalAlignment(SwingConstants.CENTER);
		lblOGrupi.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

		btnIzbrisiClana = new JButton("Izbrisi clana");
		btnIzbrisiClana.setBackground(new Color(230, 230, 250));
		btnIzbrisiClana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (indeksClana > 0) {

					izbrisiClanaGrupe(lblNazivGrupe.getText(), indeksClana);

				} else {
					JOptionPane.showMessageDialog(null, "Ne mozete sebe da izbrisete");
				}
				btnIzbrisiClana.setVisible(false);
			}

		});
		btnIzbrisiClana.setFont(new Font("Arial", Font.PLAIN, 13));
		
		lblKreator = new JLabel("Kreator grupe:");
		lblKreator.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		
		lblKorImeKreatora = new JLabel("");
		lblKorImeKreatora.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		GroupLayout gl_panelPrikazGrupe = new GroupLayout(panelPrikazGrupe);
		gl_panelPrikazGrupe.setHorizontalGroup(
			gl_panelPrikazGrupe.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
					.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
									.addGap(50)
									.addComponent(spOpisGrupe, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
								.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
									.addGap(150)
									.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAkcijaZaGrupu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSlikaGrupePrikaz, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOGrupi, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
									.addGap(95)))
							.addGap(62))
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGap(155)
							.addComponent(btnIzmeniGrupu, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE))
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGap(100)
							.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
									.addComponent(lblKreator, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(lblKorImeKreatora, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
									.addComponent(lblPrikazGrupe, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNazivGrupe, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))))
					.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGap(57)
							.addComponent(btnIzbrisiClana, GroupLayout.PREFERRED_SIZE, 129, Short.MAX_VALUE)
							.addGap(118))
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblIzaberiNovogClana, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
								.addComponent(spClanoviGrupe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
								.addComponent(spDodajNoveClanove, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
								.addComponent(lblClanoviGrupe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
							.addGap(54))))
		);
		gl_panelPrikazGrupe.setVerticalGroup(
			gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addComponent(btnIzmeniGrupu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNazivGrupe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrikazGrupe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addComponent(btnIzbrisiClana, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblClanoviGrupe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addComponent(spClanoviGrupe, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblIzaberiNovogClana, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spDodajNoveClanove, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPrikazGrupe.createSequentialGroup()
							.addGroup(gl_panelPrikazGrupe.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKreator, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKorImeKreatora, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addComponent(lblSlikaGrupePrikaz, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnAkcijaZaGrupu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addComponent(lblOGrupi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(spOpisGrupe, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGap(32))
		);

		listClanoviZaDodavanje = new JList<Object>(dlmPrijatelji);
		listClanoviZaDodavanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				selektPrijatelj = listClanoviZaDodavanje.getSelectedIndex();
				Profil p = listaPrijatelja.get(selektPrijatelj);
				dodajClanaGrupe(lblNazivGrupe.getText(), p);
				lblIzaberiNovogClana.setVisible(false);
				spDodajNoveClanove.setVisible(false);

			}

		});
		listClanoviZaDodavanje.setFont(new Font("Arial", Font.PLAIN, 13));
		listClanoviZaDodavanje.setFixedCellHeight(50);
		listClanoviZaDodavanje.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		spDodajNoveClanove.setViewportView(listClanoviZaDodavanje);

		listClanoviGrupe = new JList<Object>(dlmClanoviGrupe);
		listClanoviGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (listClanoviGrupe.isEnabled()) {
					btnIzbrisiClana.setVisible(true);
					indeksClana = listClanoviGrupe.getSelectedIndex();
				}

			}
		});
		listClanoviGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		listClanoviGrupe.setFixedCellHeight(50);
		listClanoviGrupe.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		spClanoviGrupe.setViewportView(listClanoviGrupe);

		tpOpisGrupe = new JTextPane();
		tpOpisGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		tpOpisGrupe.setEditable(false);
		spOpisGrupe.setViewportView(tpOpisGrupe);
		panelPrikazGrupe.setLayout(gl_panelPrikazGrupe);

		lblKreirajIzmeniGrupu = new JLabel("");
		lblKreirajIzmeniGrupu.setHorizontalAlignment(SwingConstants.CENTER);
		lblKreirajIzmeniGrupu.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		lblSlikaGrupe = new JLabel("");

		lblUnesiImeGrupe = new JLabel("Unesi ime grupe:");
		lblUnesiImeGrupe.setFont(new Font("Arial", Font.PLAIN, 13));

		lblUnesiOpisGrupe = new JLabel("Unesi opis grupe:");
		lblUnesiOpisGrupe.setFont(new Font("Arial", Font.PLAIN, 13));

		tfUnesiImeGrupe = new JTextField();
		tfUnesiImeGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		tfUnesiImeGrupe.setColumns(10);

		tfUnesiOpisGrupe = new JTextField();
		tfUnesiOpisGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		tfUnesiOpisGrupe.setColumns(10);

		btnKreirajIzmeniGrupu = new JButton("");
		btnKreirajIzmeniGrupu.setBackground(new Color(230, 230, 250));
		btnKreirajIzmeniGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransferKlasa tk = new TransferKlasa();
				String nazivGrupe = tfUnesiImeGrupe.getText();
				String opisGrupe = tfUnesiOpisGrupe.getText();
				if(btnKreirajIzmeniGrupu.getText().equals("Kreiraj grupu")) {
					
					if (!nazivGrupe.isEmpty() && !opisGrupe.isEmpty()) {
						Date datumKreiranja = new Date(new java.util.Date().getTime());
						Grupa g = new Grupa(0, nazivGrupe, datumKreiranja, opisGrupe, profil, SLIKA_GRUPE);
	
						tk.setOperacija(KREIRANJE_GRUPE);
						tk.setKlijentObjekat(g);
						Komunikacija.vratiInstancu().posalji(tk);
	
					}
				}else {
					
					if(lokacijaSlike!=null || opisGrupe.equals(izabranaGrupa.getOpisGrupe())) {
						Grupa izmenaGrupe=new Grupa(izabranaGrupa.getIdGrupa(), nazivGrupe, izabranaGrupa.getDatumKreiranjaGrupe(),
								opisGrupe, izabranaGrupa.getKreatorGrupe(), lokacijaSlike);		
						
						tk.setOperacija(IZMENI_PODATKE_GRUPE);
						tk.setKlijentObjekat(izmenaGrupe);
						Komunikacija.vratiInstancu().posalji(tk);
						
					}else {
						JOptionPane.showMessageDialog(null, "Niste izmenili vrednosti!!!");
					}
					
					
				}
			}
		});
		btnKreirajIzmeniGrupu.setFont(new Font("Arial", Font.PLAIN, 13));

		btnIzaberiSlikuGrupe = new JButton("Izaberi sliku");
		btnIzaberiSlikuGrupe.setBackground(new Color(230, 230, 250));
		btnIzaberiSlikuGrupe.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				lokacijaSlike=null;
				JFileChooser biracFajla=new JFileChooser();
				biracFajla.showOpenDialog(null);
				File izabranFajl=biracFajla.getSelectedFile();
				
				File noviFajl=new File(FOLDER_SLIKE_GRUPA+izabranaGrupa.getNazivGrupe()+".jpg");
				
				try {// kopitanje slike u folder klijenta
					Files.copy(izabranFajl.toPath(), noviFajl.toPath(),StandardCopyOption.REPLACE_EXISTING);
					lokacijaSlike=FOLDER_SLIKE_GRUPA+izabranaGrupa.getNazivGrupe()+".jpg";
				} catch (Exception e) {
					ImageIcon novaSlika=new ImageIcon(new ImageIcon(izabranaGrupa.getSlikaGrupe()).getImage().getScaledInstance(lblIzmenaSlike.getWidth(), lblIzmenaSlike.getHeight(), Image.SCALE_SMOOTH));
					return;
				}
				ImageIcon novaSlika=new ImageIcon(new ImageIcon(lokacijaSlike).getImage().getScaledInstance(lblSlikaGrupe.getWidth(), lblSlikaGrupe.getHeight(), Image.SCALE_SMOOTH));
				lblSlikaGrupe.setIcon(novaSlika); 
				
				
			}
		});
		btnIzaberiSlikuGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		GroupLayout gl_panelKreiranjeGrupa = new GroupLayout(panelKreiranjeGrupa);
		gl_panelKreiranjeGrupa.setHorizontalGroup(
			gl_panelKreiranjeGrupa.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
					.addGap(200)
					.addComponent(lblKreirajIzmeniGrupu, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addGap(200))
				.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
					.addGap(120)
					.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnIzaberiSlikuGrupe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSlikaGrupe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
					.addGap(62)
					.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
							.addComponent(lblUnesiImeGrupe, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfUnesiImeGrupe, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
							.addComponent(lblUnesiOpisGrupe, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(btnKreirajIzmeniGrupu, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfUnesiOpisGrupe, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_panelKreiranjeGrupa.setVerticalGroup(
			gl_panelKreiranjeGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
					.addGap(101)
					.addComponent(lblKreirajIzmeniGrupu, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSlikaGrupe, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelKreiranjeGrupa.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnesiImeGrupe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfUnesiImeGrupe, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(gl_panelKreiranjeGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(tfUnesiOpisGrupe, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnesiOpisGrupe, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(btnKreirajIzmeniGrupu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnIzaberiSlikuGrupe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		panelKreiranjeGrupa.setLayout(gl_panelKreiranjeGrupa);

		spMojeGrupe = new JScrollPane();

		lblMojeGrupe = new JLabel("MOJE GRUPE");
		lblMojeGrupe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMojeGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));

		spPrimljeniZahteviZaGrupe = new JScrollPane();

		lblPrimljeniZahteviZaGrupe = new JLabel("PRIMLJENI ZAHTEVI");
		lblPrimljeniZahteviZaGrupe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimljeniZahteviZaGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));

		spPoslatiZahteviZaGrupe = new JScrollPane();

		lblPoslatiZahteviZaGrupe = new JLabel("POSLATI ZAHTEVI");
		lblPoslatiZahteviZaGrupe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoslatiZahteviZaGrupe.setFont(new Font("Bookman Old Style", Font.PLAIN, 17));

		listPoslatiZahteviZaGrupe = new JList<Object>(dlmPoslatiZahteviGrupe);
		listPoslatiZahteviZaGrupe.setFixedCellWidth(30);
		listPoslatiZahteviZaGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnPrihvatiZahtev.setEnabled(false);
				btnIzbrisiZahtevZaGrupu.setEnabled(true);
				indeksClana = listPoslatiZahteviZaGrupe.getSelectedIndex();

			}
		});
		listPoslatiZahteviZaGrupe.setFont(new Font("Arial", Font.PLAIN, 14));
		spPoslatiZahteviZaGrupe.setViewportView(listPoslatiZahteviZaGrupe);

		listPrimljeniZahteviZaGrupe = new JList<Object>(dlmPrimljeniZahteviGrupe);
		listPrimljeniZahteviZaGrupe.setFixedCellWidth(30);
		listPrimljeniZahteviZaGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnPrihvatiZahtev.setEnabled(true);
				btnIzbrisiZahtevZaGrupu.setEnabled(true);
				indeksClana = listPrimljeniZahteviZaGrupe.getSelectedIndex();
			}
		});
		listPrimljeniZahteviZaGrupe.setFont(new Font("Arial", Font.PLAIN, 14));
		spPrimljeniZahteviZaGrupe.setViewportView(listPrimljeniZahteviZaGrupe);

		listMojeGrupe = new JList<Object>(dlmGrupe);
		listMojeGrupe.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnPrihvatiZahtev.setEnabled(false);
				btnIzbrisiZahtevZaGrupu.setEnabled(false);
				indeksGrupe = listMojeGrupe.getSelectedIndex();
				izabranaGrupa = mojeGrupe.get(indeksGrupe);

			}
		});
		listMojeGrupe.setFont(new Font("Arial", Font.PLAIN, 14));
		listMojeGrupe.setFixedCellHeight(50);
		listMojeGrupe.setCellRenderer(new PrikazivanjeUListiGrupa());
		spMojeGrupe.setViewportView(listMojeGrupe);

		btnPrikaziGrupu = new JButton("Prikazi grupu");
		btnPrikaziGrupu.setBackground(new Color(230, 230, 250));
		btnPrikaziGrupu.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrikaziGrupu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (indeksGrupe != -1) {
					prikaziGrupu((Map<String, Object>) sveGrupe.get(izabranaGrupa.getNazivGrupe()));
				} else {
					JOptionPane.showMessageDialog(null, "Niste izabrali grupu!!!");
				}
				indeksGrupe = -1;
			}
		});

		btnPrihvatiZahtev = new JButton("Prihvati zahtev");
		btnPrihvatiZahtev.setBackground(new Color(230, 230, 250));
		btnPrihvatiZahtev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!listPrimljeniZahteviZaGrupe.isSelectionEmpty() && indeksClana != -1) {
					indeksClana = listPrimljeniZahteviZaGrupe.getSelectedIndex();
					izabranClan = primljeniZahteviZaGrupe.get(indeksClana);
					TransferKlasa tk = new TransferKlasa();
					prihvatiZahtevZaGrupu(izabranClan, tk);
				}else {
					
					JOptionPane.showMessageDialog(null, "Niste izabrali zahtev!!!");
				}

			}

		});
		btnPrihvatiZahtev.setFont(new Font("Arial", Font.PLAIN, 13));

		btnKreirajGrupu = new JButton("Kreiraj grupu");
		btnKreirajGrupu.setBackground(new Color(230, 230, 250));
		btnKreirajGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prikaziPanelKreirajGrupu(NOVA_GRUPA,null);
			}

		});
		btnKreirajGrupu.setFont(new Font("Arial", Font.PLAIN, 13));

		btnIzbrisiZahtevZaGrupu = new JButton("Izbrisi zahtev");
		btnIzbrisiZahtevZaGrupu.setBackground(new Color(230, 230, 250));
		btnIzbrisiZahtevZaGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (indeksClana != -1) {
					if (btnPrihvatiZahtev.isEnabled()) {
						izabranClan = primljeniZahteviZaGrupe.get(indeksClana);
						TransferKlasa tk = new TransferKlasa();
						izbrisiPrimljenZahtevZaGrupu(izabranClan, tk);

					} else {
						izabranClan = mojiPoslatiZahteviZaGrupe.get(indeksClana);
						TransferKlasa tk = new TransferKlasa();
						izbrisiPoslatZahtevZaGrupu(izabranClan, tk);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Niste izabrali zahtev za brisanje!!!");
				}
			}

		});
		btnIzbrisiZahtevZaGrupu.setFont(new Font("Arial", Font.PLAIN, 13));

		GroupLayout gl_panelGrupa = new GroupLayout(panelGrupa);
		gl_panelGrupa.setHorizontalGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING).addGroup(gl_panelGrupa
				.createSequentialGroup().addGap(50)
				.addGroup(gl_panelGrupa.createParallelGroup(Alignment.TRAILING).addGroup(gl_panelGrupa
						.createSequentialGroup()
						.addGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(spMojeGrupe, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE).addComponent(
										lblMojeGrupe, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGap(150)
						.addGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(spPrimljeniZahteviZaGrupe, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
								.addComponent(lblPrimljeniZahteviZaGrupe, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPoslatiZahteviZaGrupe, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										250, GroupLayout.PREFERRED_SIZE)
								.addComponent(spPoslatiZahteviZaGrupe, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										250, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelGrupa.createSequentialGroup()
								.addComponent(btnPrikaziGrupu, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addGap(96)
								.addGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING)
										.addComponent(btnKreirajGrupu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnPrihvatiZahtev, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												150, Short.MAX_VALUE))
								.addGap(104)
								.addComponent(btnIzbrisiZahtevZaGrupu, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
				.addGap(76)));
		gl_panelGrupa.setVerticalGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGrupa.createSequentialGroup().addGap(50)
						.addGroup(gl_panelGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMojeGrupe, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrimljeniZahteviZaGrupe, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addGroup(
								gl_panelGrupa.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelGrupa.createSequentialGroup()
												.addComponent(spPrimljeniZahteviZaGrupe, GroupLayout.PREFERRED_SIZE,
														150, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblPoslatiZahteviZaGrupe, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addGap(1).addComponent(spPoslatiZahteviZaGrupe,
														GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addComponent(spMojeGrupe, GroupLayout.PREFERRED_SIZE, 350,
												GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(gl_panelGrupa.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPrikaziGrupu, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIzbrisiZahtevZaGrupu, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPrihvatiZahtev, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(btnKreirajGrupu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		panelGrupa.setLayout(gl_panelGrupa);

		spDopisivanja = new JScrollPane(listAkrivniPrijatelji);

		spPoruke = new JScrollPane(taPrikazPoruka);
		spPoruke.setSize(350, 360);
		spPoruke.setMaximumSize(spPoruke.getSize());
		tfUnosPoruke = new JTextField();
		tfUnosPoruke.setEditable(false);
		tfUnosPoruke.setFont(new Font("Cambria", Font.PLAIN, 14));
		tfUnosPoruke.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (listAkrivniPrijatelji.isShowing()) {
						if (!listaAktivniPrijatelji.isEmpty() && !listAkrivniPrijatelji.isSelectionEmpty()) {
							posaljiPrivatnuPoruku(aktivniPrijatelj);
						} else {
							posaljiPrivatnuPoruku(izabranPrijatelj);
						}
					} else if (listGrupeZaDopisivanje.isShowing()) {
						posaljiGrupnuPoruku(izabranaGrupa);
					}
				}
			}

		});

		tfUnosPoruke.setColumns(10);

		lblPosaljiPoruku = new JLabel("");
		lblPosaljiPoruku.setSize(45, 35);
		lblPosaljiPoruku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listAkrivniPrijatelji.isShowing()) {
					if (!listaAktivniPrijatelji.isEmpty() && !listAkrivniPrijatelji.isSelectionEmpty()) {
						posaljiPrivatnuPoruku(aktivniPrijatelj);
					} else {
						posaljiPrivatnuPoruku(izabranPrijatelj);
					}
				} else if (listGrupeZaDopisivanje.isShowing()) {
					posaljiGrupnuPoruku(izabranaGrupa);
				}
			}
		});
		lblPosaljiPoruku.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			Image icon = ImageIO.read(new File(ICON_POSALJI));
			Image sendIcon = icon.getScaledInstance(lblPosaljiPoruku.getWidth(), lblPosaljiPoruku.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon slika = new ImageIcon(sendIcon);
			lblPosaljiPoruku.setIcon(slika);
		} catch (Exception e) {

			e.printStackTrace();
		}

		lblPogledajProfilGrupu = new JLabel("");
		lblPogledajProfilGrupu.setBackground(new Color(230, 230, 250));
		lblPogledajProfilGrupu.setOpaque(true);
		lblPogledajProfilGrupu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lblPogledajProfilGrupu.getText().equals("Pogledaj profil")) {
					if (aktivniPrijatelj != null) {
						pretrgaProfil = aktivniPrijatelj;
						prikaziProfil(pretrgaProfil, PROFIL_IZ_PRETRAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Niste izabrali prijatelja!!!");
					}
				} else {
					if (izabranaGrupa != null) {
						Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe
								.get(izabranaGrupa.getNazivGrupe());
						prikaziGrupu(podaciGrupe);

					} else {
						JOptionPane.showMessageDialog(null, "Niste izabrali grupu!!!");
					}
				}

			}
		});
		lblPogledajProfilGrupu.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPogledajProfilGrupu.setHorizontalAlignment(SwingConstants.CENTER);

		lblPrikazDopisivanja = new JLabel("");
		lblPrikazDopisivanja.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblPrikazDopisivanja.setHorizontalAlignment(SwingConstants.CENTER);

		lblPorukeOd = new JLabel("");
		lblPorukeOd.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblPorukeOd.setHorizontalAlignment(SwingConstants.CENTER);

		cbNovePoruke = new JComboBox<String>();
		cbNovePoruke.setVisible(false);

		cbNovePoruke.addItem("Nove poruke od:");
		cbNovePoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = cbNovePoruke.getSelectedIndex();
				korisnikIzComboBox = (String) cbNovePoruke.getItemAt(index);
				for (Profil pr : listaPrijatelja) {
					if (pr.getKorisnickoIme().equals(korisnikIzComboBox)) {
						prikaziDopisivanje(pr, false);
						cbNovePoruke.removeItemAt(index);
						return;
					}
				}
			}
		});
		cbNovePoruke.setBackground(new Color(230, 230, 250));
		GroupLayout gl_panelPoruka = new GroupLayout(panelPoruke);
		gl_panelPoruka.setHorizontalGroup(
			gl_panelPoruka.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPoruka.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panelPoruka.createParallelGroup(Alignment.LEADING)
						.addComponent(spDopisivanja, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(lblPogledajProfilGrupu, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(cbNovePoruke, 0, 250, Short.MAX_VALUE)
						.addComponent(lblPrikazDopisivanja, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
					.addGap(76)
					.addGroup(gl_panelPoruka.createParallelGroup(Alignment.TRAILING)
						.addComponent(spPoruke, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
						.addGroup(gl_panelPoruka.createSequentialGroup()
							.addComponent(tfUnosPoruke, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(1)
							.addComponent(lblPosaljiPoruku, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(1))
						.addComponent(lblPorukeOd, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
					.addGap(50))
		);
		gl_panelPoruka.setVerticalGroup(
			gl_panelPoruka.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPoruka.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panelPoruka.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrikazDopisivanja, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPorukeOd, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelPoruka.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(spPoruke, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPoruka.createSequentialGroup()
							.addComponent(spDopisivanja, 0, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPogledajProfilGrupu, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(5)
					.addGroup(gl_panelPoruka.createParallelGroup(Alignment.LEADING)
						.addComponent(tfUnosPoruke, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addGroup(gl_panelPoruka.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblPosaljiPoruku, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addComponent(cbNovePoruke, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(62))
		);

		taPrikazPoruka = new JTextArea();
		taPrikazPoruka.setEditable(false);
		taPrikazPoruka.setFont(new Font("Cambria", Font.PLAIN, 14));
		spPoruke.setViewportView(taPrikazPoruka);
		

		listAkrivniPrijatelji = new JList<Object>(dlmAktivniPrijatelji);
		listAkrivniPrijatelji.setFont(new Font("Arial", Font.PLAIN, 14));
		listAkrivniPrijatelji.setCellRenderer(new PrikazivanjeUListiAktivnih());
		listAkrivniPrijatelji.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selektAktivni = listAkrivniPrijatelji.getSelectedIndex();
				if (selektAktivni != -1) {
					aktivniPrijatelj = listaAktivniPrijatelji.get(selektAktivni);
					prikaziDopisivanje(aktivniPrijatelj, true);
				} else {
					JOptionPane.showMessageDialog(null, "Lista aktivnih prijatelja je prazna!!!");
				}

			}

		});

		listGrupeZaDopisivanje = new JList<Object>(dlmGrupe);
		listGrupeZaDopisivanje.setFont(new Font("Arial", Font.PLAIN, 14));
		listGrupeZaDopisivanje.setCellRenderer(new PrikazivanjeUListiGrupnoDopisivanje());
		listGrupeZaDopisivanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				indeksGrupnogDopisivanja = listGrupeZaDopisivanje.getSelectedIndex();
				izabranaGrupa = mojeGrupe.get(indeksGrupnogDopisivanja);

				prikaziGrupnoDopisivanje(izabranaGrupa);

			}

		});

		panelPoruke.setLayout(gl_panelPoruka);

		spListaPrijatelja = new JScrollPane();
		listPrijatelji = new JList<Object>(dlmPrijatelji);
		listPrijatelji.setFont(new Font("Arial", Font.PLAIN, 14));
		listPrijatelji.setFixedCellHeight(50);
		listPrijatelji.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		listPrijatelji.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!listPrijatelji.isSelectionEmpty()) {
					selektPrijatelj = listPrijatelji.getSelectedIndex();
					izabranPrijatelj = (Profil) listaPrijatelja.get(selektPrijatelj);
				} else {
					JOptionPane.showMessageDialog(null, "Niste izabrali prijatelja");
					return;
				}
				obrisiTekstDugeta();
				btnPosaljiPoruku.setVisible(true);
				btnBlokirajPrihvati.setVisible(true);
				btnIzbrisi.setVisible(true);
				btnPrikaziProfil.setVisible(true);
				btnBlokirajPrihvati.setText("Blokiraj");
				btnIzbrisi.setText("Izbrisi prijatelja");

			}
		});

		spListaPrijatelja.setViewportView(listPrijatelji);

		spPrimljeniZahtevi = new JScrollPane();
		listPrimljeniZahtevi = new JList<Object>(dlmPrimljeniZahtevi);
		listPrimljeniZahtevi.setFont(new Font("Arial", Font.PLAIN, 14));
		listPrimljeniZahtevi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				izabranZahtev = (ZahtevZaPrijateljstvo) listPrimljeniZahtevi.getSelectedValue();
				try {
					izabranPrijatelj = izabranZahtev.getPosiljalac();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Niste izabrali primljen zahtev");
					return;
				}
				obrisiTekstDugeta();
				btnPosaljiPoruku.setVisible(false);
				btnBlokirajPrihvati.setVisible(true);
				btnIzbrisi.setVisible(true);
				btnPrikaziProfil.setVisible(true);
				btnBlokirajPrihvati.setText("Prihvati zahtev");
				btnIzbrisi.setText("Izbrisi zahtev");

			}

		});
		listPrimljeniZahtevi.setFixedCellHeight(30);
		spPrimljeniZahtevi.setViewportView(listPrimljeniZahtevi);

		JLabel lblLbllista = new JLabel("LISTA PRIJATELJA");
		lblLbllista.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblLbllista.setHorizontalAlignment(SwingConstants.CENTER);

		lblPrimljeniZahtevi = new JLabel("PRIMLJENI ZAHTEVI");
		lblPrimljeniZahtevi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimljeniZahtevi.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		JLabel lblPoslatiZahtevi = new JLabel("POSLATI ZAHTEVI");
		lblPoslatiZahtevi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoslatiZahtevi.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		spPoslatiZahtevi = new JScrollPane();

		btnPrikaziProfil = new JButton("Prikaži profil");
		btnPrikaziProfil.setBackground(new Color(230, 230, 250));
		btnPrikaziProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prikaziProfil(izabranPrijatelj, PROFIL_IZ_PRETRAGE);
			}
		});
		btnPrikaziProfil.setFont(new Font("Arial", Font.PLAIN, 13));

		btnIzbrisi = new JButton("");
		btnIzbrisi.setBackground(new Color(230, 230, 250));
		btnIzbrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferKlasa tk = new TransferKlasa();
				ZahtevZaPrijateljstvo zzp = new ZahtevZaPrijateljstvo(profil, izabranPrijatelj);
				if (btnIzbrisi.getText().equals("Izbrisi zahtev")) {
					if (!listPoslatiZahtevi.isSelectionEmpty()) {
						izbrisiPoslatZahtev(izabranZahtev, tk);
						izbrisiZahtevIzListePoslatihZahteva(izabranZahtev);
					} else if (!listPrimljeniZahtevi.isSelectionEmpty()) {
						izbrisiPrimljenZahtev(izabranZahtev, tk);
						izbrisiZahtevIzListePrimljenihZahteva(izabranZahtev);

					}

				} else if (btnIzbrisi.getText().equals("Izbrisi prijatelja")) {
					izbrisiPrijatelja(zzp, tk);
					btnPosalji.setText("POSALJI ZAHTEV");
					btnPosalji.setBackground(Color.GREEN);
				}

			}
		});
		btnIzbrisi.setFont(new Font("Arial", Font.PLAIN, 13));

		btnBlokirajPrihvati = new JButton("");
		btnBlokirajPrihvati.setBackground(new Color(230, 230, 250));
		btnBlokirajPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransferKlasa tk = new TransferKlasa();
				if (btnBlokirajPrihvati.getText().equals("Prihvati zahtev")) {
					prihvatiZahtev(izabranZahtev, tk);

				} else if (btnBlokirajPrihvati.getText().equals("Blokiraj")) {
					ZahtevZaPrijateljstvo zahtev = new ZahtevZaPrijateljstvo(profil, izabranPrijatelj);
					blokirajKontakt(zahtev, tk);
					izbrisiBlokiranogPrijatelja(izabranPrijatelj);
				}
			}

		});
		btnBlokirajPrihvati.setFont(new Font("Arial", Font.PLAIN, 13));

		btnPosaljiPoruku = new JButton("Pošalji poruku");
		btnPosaljiPoruku.setBackground(new Color(230, 230, 250));
		btnPosaljiPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = 0;
				for (Profil profil : listaAktivniPrijatelji) {
					if (profil.getIdProfila() == izabranPrijatelj.getIdProfila()) {
						prikaziPanelPrivatnePoruke();
						listAkrivniPrijatelji.setSelectedIndex(index);
						selektAktivni = listAkrivniPrijatelji.getSelectedIndex();
						aktivniPrijatelj = listaAktivniPrijatelji.get(selektAktivni);
						prikaziDopisivanje(aktivniPrijatelj, true);
						return;
					}
				}
				prikaziPanelPrivatnePoruke();
				prikaziDopisivanje(izabranPrijatelj, false);

			}
		});
		btnPosaljiPoruku.setFont(new Font("Arial", Font.PLAIN, 13));

		GroupLayout gl_panelPrijatelji = new GroupLayout(panelPrijatelji);
		gl_panelPrijatelji.setHorizontalGroup(gl_panelPrijatelji.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrijatelji.createSequentialGroup().addGap(80).addGroup(gl_panelPrijatelji
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrijatelji.createSequentialGroup()
								.addGroup(gl_panelPrijatelji.createParallelGroup(Alignment.LEADING)
										.addComponent(spListaPrijatelja, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
										.addComponent(lblLbllista, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
								.addGap(116)
								.addGroup(gl_panelPrijatelji.createParallelGroup(Alignment.TRAILING)
										.addComponent(spPrimljeniZahtevi, GroupLayout.DEFAULT_SIZE, 250,
												Short.MAX_VALUE)
										.addComponent(spPoslatiZahtevi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												250, Short.MAX_VALUE)
										.addComponent(lblPoslatiZahtevi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												250, Short.MAX_VALUE)
										.addComponent(lblPrimljeniZahtevi, GroupLayout.DEFAULT_SIZE, 250,
												Short.MAX_VALUE)))
						.addGroup(gl_panelPrijatelji.createSequentialGroup()
								.addComponent(btnPrikaziProfil, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addGap(114)
								.addGroup(gl_panelPrijatelji.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnPosaljiPoruku, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBlokirajPrihvati, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												145, Short.MAX_VALUE))
								.addGap(117).addComponent(btnIzbrisi, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
						.addGap(80)));
		gl_panelPrijatelji.setVerticalGroup(gl_panelPrijatelji.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrijatelji.createSequentialGroup().addGroup(gl_panelPrijatelji
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelPrijatelji.createSequentialGroup().addGap(62)
								.addGroup(gl_panelPrijatelji.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLbllista, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrimljeniZahtevi, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
								.addGap(1).addComponent(spListaPrijatelja, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panelPrijatelji.createSequentialGroup().addGap(93)
								.addComponent(spPrimljeniZahtevi, GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblPoslatiZahtevi, GroupLayout.PREFERRED_SIZE, 29,
										GroupLayout.PREFERRED_SIZE)
								.addGap(14).addComponent(spPoslatiZahtevi, GroupLayout.PREFERRED_SIZE, 148,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(41)
						.addGroup(gl_panelPrijatelji.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPrikaziProfil, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIzbrisi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBlokirajPrihvati, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addComponent(btnPosaljiPoruku, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(84)));

		listPoslatiZahtevi = new JList<Object>(dlmPoslatiZahtevi);
		listPoslatiZahtevi.setFont(new Font("Arial", Font.PLAIN, 14));
		listPoslatiZahtevi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				izabranZahtev = (ZahtevZaPrijateljstvo) listPoslatiZahtevi.getSelectedValue();
				try {
					izabranPrijatelj = izabranZahtev.getPrimalac();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Niste izabrali poslat zahtev");
					return;
				}

				obrisiTekstDugeta();
				btnBlokirajPrihvati.setVisible(false);
				btnPosaljiPoruku.setVisible(false);
				btnIzbrisi.setVisible(true);
				btnPrikaziProfil.setVisible(true);
				btnIzbrisi.setText("Izbrisi zahtev");

			}
		});
		listPoslatiZahtevi.setFixedCellHeight(30);
		spPoslatiZahtevi.setViewportView(listPoslatiZahtevi);

		panelPrijatelji.setLayout(gl_panelPrijatelji);

		tpLicniPodaci = new JTextPane();
		tpLicniPodaci.setEditable(false);
		tpLicniPodaci.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		textPane = new JTextPane();
		textPane.setText("");

		lblOMeni = new JLabel("O meni...");
		lblOMeni.setFont(new Font("Arial", Font.ITALIC, 15));

		lblSlikaProfila = new JLabel("");
		lblSlikaProfila.setSize(150,200);
		btnIzmeniProfil = new JButton("Izmeni profil");
		btnIzmeniProfil.setBackground(new Color(230, 230, 250));
		btnIzmeniProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				prikaziPanelIzmenaProfila();
				
			}
		});
		btnIzmeniProfil.setFont(new Font("Arial", Font.PLAIN, 13));

		spOMeni = new JScrollPane();

		btnPosalji = new JButton("");
		btnPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZahtevZaPrijateljstvo poslatZahtev = new ZahtevZaPrijateljstvo(profil, pretrgaProfil);
				TransferKlasa tk = new TransferKlasa();
				switch (btnPosalji.getText()) {
				case "POSALJI ZAHTEV":
					posaljiZahtev(poslatZahtev, tk);
					btnPosalji.setText("IZBRISI ZAHTEV");
					btnPosalji.setBackground(Color.RED);
					break;
				case "IZBRISI ZAHTEV":
					btnPosalji.setText("POSALJI ZAHTEV");
					btnPosalji.setBackground(Color.green);
					izbrisiPoslatZahtev(poslatZahtev, tk);
					izbrisiZahtevIzListePoslatihZahteva(poslatZahtev);
					break;
				case "PRIHVATI ZAHTEV":
					prihvatiZahtev(izabranZahtev, tk);
					btnPosalji.setText("IZBRISI PRIJATELJA");
					btnPosalji.setBackground(Color.RED);
					break;
				case "IZBRISI PRIJATELJA":
					izbrisiPrijatelja(poslatZahtev, tk);
					btnPosalji.setText("POSALJI ZAHTEV");
					btnPosalji.setBackground(Color.green);
					break;
				case "ODBLOKIRAJ":
					odblokirajKontakt(poslatZahtev, tk);
					postaviUListuPrijatelja(pretrgaProfil);
					btnPosalji.setText("IZBRISI PRIJATELJA");
					btnPosalji.setBackground(Color.RED);
					break;

				default:
					break;
				}
			}
		});
		btnPosalji.setFont(new Font("Arial", Font.PLAIN, 12));

		GroupLayout gl_panelProfil = new GroupLayout(panelProfil);
		gl_panelProfil.setHorizontalGroup(
			gl_panelProfil.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfil.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_panelProfil.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfil.createSequentialGroup()
							.addComponent(btnPosalji, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panelProfil.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelProfil.createSequentialGroup()
								.addComponent(lblOMeni, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panelProfil.createSequentialGroup()
								.addComponent(lblSlikaProfila, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addComponent(tpLicniPodaci, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(btnIzmeniProfil, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addGap(5))
							.addGroup(gl_panelProfil.createSequentialGroup()
								.addComponent(spOMeni, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
								.addGap(76)))))
		);
		gl_panelProfil.setVerticalGroup(
			gl_panelProfil.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfil.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panelProfil.createParallelGroup(Alignment.LEADING)
						.addComponent(tpLicniPodaci, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSlikaProfila, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIzmeniProfil, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnPosalji, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(lblOMeni, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(spOMeni, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);

		tpOMeni = new JTextPane();
		tpOMeni.setEditable(false);
		tpOMeni.setFont(new Font("Bookman Old Style", Font.ITALIC, 16));
		spOMeni.setViewportView(tpOMeni);
		panelProfil.setLayout(gl_panelProfil);

		lblSlikaProfilaPoc = new JLabel("");
		lblSlikaProfilaPoc.setSize(150,200);

		lblDobrodoslica = new JLabel("");
		lblDobrodoslica.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		lblBrojPrijatelja = new JLabel("");
		lblBrojPrijatelja.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		lblBrojGrupa = new JLabel("");
		lblBrojGrupa.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));

		tpMogucnosti = new JTextPane();
		tpMogucnosti.setEditable(false);
		tpMogucnosti.setBackground(new Color(230, 230, 250));
		tpMogucnosti.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		GroupLayout gl_panelPocetna = new GroupLayout(panelPocetna);
		gl_panelPocetna.setHorizontalGroup(
			gl_panelPocetna.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPocetna.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panelPocetna.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPocetna.createSequentialGroup()
							.addComponent(tpMogucnosti, GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
							.addGap(20))
						.addGroup(gl_panelPocetna.createSequentialGroup()
							.addComponent(lblSlikaProfilaPoc, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(80)
							.addGroup(gl_panelPocetna.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDobrodoslica, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(lblBrojGrupa, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
								.addComponent(lblBrojPrijatelja, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
							.addGap(86)))
					.addGap(60))
		);
		gl_panelPocetna.setVerticalGroup(
			gl_panelPocetna.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPocetna.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panelPocetna.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSlikaProfilaPoc, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPocetna.createSequentialGroup()
							.addComponent(lblDobrodoslica, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(lblBrojPrijatelja, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(lblBrojGrupa, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(80)
					.addComponent(tpMogucnosti, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(112))
		);
		panelPocetna.setLayout(gl_panelPocetna);

		btnPocetna = new JButton("Pocetna");
		btnPocetna.setHorizontalAlignment(SwingConstants.LEADING);
		btnPocetna.setIcon(new ImageIcon(ICON_POCETNA));
		btnPocetna.setBackground(Color.WHITE);
		btnPocetna.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPocetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				prikaziPocetnuStranu();
			}
		});

		btnProfil = new JButton("Moj profil");
		btnProfil.setHorizontalAlignment(SwingConstants.LEADING);
		btnProfil.setIcon(new ImageIcon(ICON_PROFIL_FORMA));
		btnProfil.setBackground(Color.WHITE);
		btnProfil.setFont(new Font("Arial", Font.PLAIN, 13));
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProfil.setBackground(Color.WHITE);
				prikaziProfil(profil, MOJ_PROFIL);
			}

		});

		btnPrijatelji = new JButton("Prijatelji");
		btnPrijatelji.setHorizontalAlignment(SwingConstants.LEADING);
		btnPrijatelji.setBackground(Color.WHITE);
		btnPrijatelji.setIcon(new ImageIcon(ICON_PRIJATELJI));
		btnPrijatelji.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrijatelji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPrijatelji.setBackground(Color.WHITE);
				btnPrijatelji.setText("Prijatelji");
				prikaziPanelPrijatelji();
			}

		});

		btnPoruke = new JButton("Privatne poruke");
		btnPoruke.setHorizontalAlignment(SwingConstants.LEADING);
		btnPoruke.setBackground(Color.WHITE);
		btnPoruke.setIcon(new ImageIcon(ICON_PRIVATNE_PORUKE));
		btnPoruke.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPoruke.setBackground(Color.WHITE);
				btnPoruke.setText("Privatne poruke");
				indeksGrupnogDopisivanja = -1;
				listGrupeZaDopisivanje.setSelectedIndex(indeksGrupnogDopisivanja);
				
				prikaziPanelPrivatnePoruke();

			}

		});

		btnGrupe = new JButton("Grupe");
		btnGrupe.setHorizontalAlignment(SwingConstants.LEADING);
		btnGrupe.setBackground(Color.WHITE);
		btnGrupe.setIcon(new ImageIcon(ICON_GRUPE));
		btnGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		btnGrupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indeksGrupe = -1;
				listMojeGrupe.setSelectedIndex(indeksGrupe);
				btnGrupe.setBackground(Color.WHITE);
				btnGrupe.setText("Grupe");

				prikaziPanelGrupe();

			}
		});

		btnGrupnePoruke = new JButton("Grupne poruke");
		btnGrupnePoruke.setHorizontalAlignment(SwingConstants.LEADING);
		btnGrupnePoruke.setIcon(new ImageIcon(ICON_GRUPNE_PORUKE));
		btnGrupnePoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGrupnePoruke.setText("Grupne poruke");
				btnGrupnePoruke.setBackground(Color.WHITE);
				selektAktivni = -1;
				listAkrivniPrijatelji.setSelectedIndex(selektAktivni);
				prikaziPanelGrupnePoruke();
			}
		});
		btnGrupnePoruke.setFont(new Font("Arial", Font.PLAIN, 13));
		btnGrupnePoruke.setBackground(Color.WHITE);

		btnOdjava = new JButton("ODJAVI SE");
		btnOdjava.setHorizontalAlignment(SwingConstants.LEADING);
		btnOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int odjava=JOptionPane.showConfirmDialog(null, "Da li želite da se odjavite?","Odjava",JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(odjava==JOptionPane.YES_OPTION) {
					odjavaKorisnika();
				}
				
			}
		});
		btnOdjava.setFont(new Font("Arial", Font.PLAIN, 13));
		btnOdjava.setBackground(Color.WHITE);
		btnOdjava.setIcon(new ImageIcon(ICON_ODJAVA));

		GroupLayout gl_panelMeni = new GroupLayout(panelMeni);
		gl_panelMeni
				.setHorizontalGroup(gl_panelMeni.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMeni.createSequentialGroup().addGap(30)
								.addGroup(gl_panelMeni.createParallelGroup(Alignment.LEADING)
										.addComponent(btnGrupnePoruke, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
										.addGroup(gl_panelMeni.createSequentialGroup()
												.addComponent(btnPocetna, GroupLayout.PREFERRED_SIZE, 150,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(btnProfil, GroupLayout.PREFERRED_SIZE, 152, Short.MAX_VALUE)
										.addComponent(btnPrijatelji, GroupLayout.PREFERRED_SIZE, 152, Short.MAX_VALUE)
										.addComponent(btnGrupe, GroupLayout.PREFERRED_SIZE, 152, Short.MAX_VALUE)
										.addComponent(btnPoruke, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
										.addComponent(btnOdjava, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
								.addGap(36)));
		gl_panelMeni.setVerticalGroup(gl_panelMeni.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMeni.createSequentialGroup().addGap(40)
						.addComponent(btnPocetna, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE).addGap(30)
						.addComponent(btnProfil, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE).addGap(30)
						.addComponent(btnPrijatelji, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE).addGap(30)
						.addComponent(btnPoruke, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE).addGap(30)
						.addComponent(btnGrupe, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE).addGap(30)
						.addComponent(btnGrupnePoruke, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE).addGap(147)
						.addComponent(btnOdjava, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE).addContainerGap()));
		panelMeni.setLayout(gl_panelMeni);

		lblLogo = new JLabel("");
		lblLogo.setSize(200,100);
		lblLogo.setIcon(new ImageIcon(LOGO));

		tfPretraga = new JTextField();
		tfPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfPretraga.setText("");
			}
		});

		tfPretraga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				dlm.removeAllElements();
				spPretraga.setVisible(true);
				getContentPane().validate();
				String pretraga = tfPretraga.getText();
				int provera = 0;
				if (rbProfil.isSelected()) {
					for (Profil prof : sviProfili) {
						if (prof.getStatus() != ADMIN_STATUS  && !prof.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
							if (prof.getKorisnickoIme().toLowerCase().contains(pretraga.toLowerCase())
									|| prof.getIme().toLowerCase().contains(pretraga.toLowerCase())
									|| prof.getPrezime().toLowerCase().contains(pretraga.toLowerCase())) {
								dlm.addElement(prof);
								provera = 1;
							}
						}

					}
					if (provera == 0) {
						dlm.addElement("Nema rezultata pretrage...");
					}
				} else if (rbGrupe.isSelected()) {
					for (Entry<String, Object> grupa : sveGrupe.entrySet()) {
						String kljuc = grupa.getKey();
						if (kljuc.toLowerCase().contains(pretraga.toLowerCase())) {
							dlm.addElement(kljuc);
							provera = 1;
						}
					}
					if (provera == 0) {
						dlm.addElement("Nema rezultata pretrage...");
					}

				}
			}
		});

		tfPretraga.setText("Pretra\u017Ei...");
		tfPretraga.setFont(new Font("Arial", Font.ITALIC, 13));
		tfPretraga.setColumns(10);

		rbProfil = new JRadioButton("Profili", true);
		rbProfil.setFont(new Font("Arial", Font.PLAIN, 13));
		rbProfil.setBackground(Color.WHITE);
		bg.add(rbProfil);

		rbGrupe = new JRadioButton("Grupe", false);
		rbGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		rbGrupe.setBackground(Color.WHITE);
		bg.add(rbGrupe);

		lblObavestenje = new JLabel(
				"Prijavljeni ste kao " + profil.getKorisnickoIme() + ", sa email-om " + profil.getEmail() + "!");
		lblObavestenje.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));

		spPretraga = new JScrollPane();
		listPretraga = new JList<Object>(dlm);
		listPretraga.setFont(new Font("Arial", Font.PLAIN, 13));
		listPretraga.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (rbProfil.isSelected()) {
					pretrgaProfil = (Profil) listPretraga.getSelectedValue();
					prikaziProfil(pretrgaProfil, PROFIL_IZ_PRETRAGE);
					
				} else if (rbGrupe.isSelected()) {
					izabranKljuc = (String) listPretraga.getSelectedValue();
					pretragaGrupa = (Map<String, Object>) sveGrupe.get(izabranKljuc);
					prikaziGrupu(pretragaGrupa);
				}

				tfPretraga.setText("Pretra\u017Ei...");
				spPretraga.setVisible(false);
			}

		});
		spPretraga.setViewportView(listPretraga);
		spPretraga.setVisible(false);

		GroupLayout gl_panelPretragaLogo = new GroupLayout(panelPretragaLogo);
		gl_panelPretragaLogo.setHorizontalGroup(
			gl_panelPretragaLogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPretragaLogo.createSequentialGroup()
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(gl_panelPretragaLogo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPretragaLogo.createSequentialGroup()
							.addComponent(tfPretraga, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(lblObavestenje, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panelPretragaLogo.createSequentialGroup()
							.addGap(95)
							.addComponent(rbProfil, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(rbGrupe, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPretragaLogo.createSequentialGroup()
							.addComponent(spPretraga, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 487, Short.MAX_VALUE)))
					.addGap(28))
		);
		gl_panelPretragaLogo.setVerticalGroup(
			gl_panelPretragaLogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPretragaLogo.createSequentialGroup()
					.addGroup(gl_panelPretragaLogo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addGroup(gl_panelPretragaLogo.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelPretragaLogo.createParallelGroup(Alignment.BASELINE)
								.addComponent(rbProfil)
								.addComponent(rbGrupe))
							.addGap(2)
							.addGroup(gl_panelPretragaLogo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPretragaLogo.createSequentialGroup()
									.addComponent(lblObavestenje, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
									.addGap(33))
								.addGroup(gl_panelPretragaLogo.createSequentialGroup()
									.addComponent(tfPretraga, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(0)
									.addComponent(spPretraga, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))))
					.addGap(0))
		);
		panelPretragaLogo.setLayout(gl_panelPretragaLogo);

		contentPane.setLayout(gl_contentPane);

		NitObradeKlijent nok = new NitObradeKlijent(socket);
		nok.start();
		KontrolerKlijent.vratiObjekat().postaviProfilFormu(this);
		if (listaPrijatelja!=null || mojeGrupe!=null) {
			
			uzmiPorukeIzBaze(profil, listaPrijatelja,mojeGrupe);
		}
		
		prikaziUListiPrijatelja();
		prikaziPrimljeneZahteve();
		prikaziPoslateZahteve();
		prikaziUListiAktivnih();
		prikaziListuGrupa();
		prikazuListuPoslatihZahtevaZaGrupe();
		prikazuListuPrimljenihZahtevaZaGrupe();
		
		prikaziPocetnuStranu();

	}

	// --------------------------------------KRAJ KONSTRUKTORAFORME---------------------------------------------------------
	
	// --------------------------------------METODE KOJE SE KORISTE U KONSTRUKTORU------------------------------------------

	
	private void odjavaKorisnika() {
		Map<String,Object> map=new HashMap<>();
		map.put("profil", profil);
		map.put("trenutniRazgovori", trenutniRazgovori);
		map.put("brojGrupnihPoruka", brojProcitanihGrupnihPoruka);
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(UPISI_RAZGOVORE);
		tk.setKlijentObjekat(map);
		Komunikacija.vratiInstancu().posalji(tk);
		System.exit(0);
		
	}

	public void postaviListuSvihProfila(List<Profil> lista) {
		this.sviProfili = lista;
	}

	private void uzmiPorukeIzBaze(Profil mojProfil, List<Profil> mojiPrijatelji, List<Grupa> mojeGrupe) {
		Map<String, Object> vratiPoruke = new HashMap<>();
		vratiPoruke.put("mojProfil", mojProfil);
		vratiPoruke.put("mojiPrijatelji", mojiPrijatelji);
		vratiPoruke.put("mojeGrupe", mojeGrupe);
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(VRATI_PORUKE);
		tk.setKlijentObjekat(vratiPoruke);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void postaviPrimljeneZahteve(List<Grupa> mojeGrupe) {
		if (mojeGrupe != null) {
			for (Grupa gr : mojeGrupe) {
				if (gr.getKreatorGrupe().getKorisnickoIme().equals(profil.getKorisnickoIme())) {
					Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(gr.getNazivGrupe());
					List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
					for (ClanGrupe cg : clanoviGrupe) {
						if (cg.getStatusClana() == ZAHTEV_ZA_GRUPU) {
							primljeniZahteviZaGrupe.add(cg);
							dlmPrimljeniZahteviGrupe.addElement(cg);
						}
					}
				}
			}
		}
	}

	private void obrisiTekstDugeta() {
		btnBlokirajPrihvati.setText("");
		btnIzbrisi.setText("");
	}
	
	
	private void oznaciPraznaPolja() {
		if(tfIme.getText().equals("")) {
			tfIme.setBackground(Color.RED);
		}else {
			tfIme.setBackground(Color.WHITE);
		}
		if(tfSifra.getText().equals("")) {
			tfSifra.setBackground(Color.RED);
		}else {
			tfSifra.setBackground(Color.WHITE);
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
			cbIzmenaPol.setBackground(Color.RED);	
		}else {
			cbIzmenaPol.setBackground(Color.WHITE);
		}
		
		if(datumIzmena==null) {
			dcIzmenaDatumaRodjenja.getDateEditor().getUiComponent().setBackground(Color.RED);
		}else {
			dcIzmenaDatumaRodjenja.getDateEditor().getUiComponent().setBackground(Color.WHITE);	
		}
		if(drzava.equals("")) {
			cbPromenaDrzave.setBackground(Color.RED);	
		}else {
			cbPromenaDrzave.setBackground(Color.WHITE);
		}
		if(tfGradStanovanja.getText().equals("")) {
			tfGradStanovanja.setBackground(Color.RED);	
		}else {
			tfGradStanovanja.setBackground(Color.WHITE);
		}
	}

	// ----------------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU POCETNU STRANU---------------------------------------------------------

	private void prikaziPocetnuStranu() {
		
		panelPocetna.setVisible(true);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		if (profil.getPol().equals(MUSKI_POL)) {
				lblDobrodoslica.setText("Dobrodošao " + profil.getKorisnickoIme() + "!");	
				
		} else {
				lblDobrodoslica.setText("Dobrodošla " + profil.getKorisnickoIme() + "!");	
		}
		lblBrojPrijatelja.setText("VAŠ BROJ PRIJATELJA JE " + listaPrijatelja.size() + ".");
		try {
			lblBrojGrupa.setText("VAŠ BROJ GRUPA JE " + mojeGrupe.size() + ".");
		} catch (Exception e) {
			lblBrojGrupa.setText("VAŠ BROJ GRUPA JE 0.");
		}
		tpMogucnosti.setText("Poštovani korisnice, dobrodošli na društvenu mrežu miniViber by Comtrade." + "\n\n"
				+ "Uživajte na našoj društvenoj mreži koja vam momogućava:" + "\n\n"
				+ "-pretragu korisnika, slanje i prihvatanje zahteva za prijateljstvo, dopisivanje sa prijateljima, blokiranje prijatelja,pregled profila,"
				+ "\n\n"
				+ "-kreiranje grupe, pretragu drugih grupa, slanje zahteva za pristupanje i blokiranje grupa, grupno dopisivanje, i mnoge druge radnje...");
		
		Image slikaProfila = null;
		try {
			Image slika = ImageIO.read(new File(profil.getSlikaProfila()));
			slikaProfila=slika.getScaledInstance(lblSlikaProfilaPoc.getWidth(), lblSlikaProfilaPoc.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblSlikaProfilaPoc.setIcon(new ImageIcon(slikaProfila));
	}

	// --------------------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE PRIKAZUJU PROFIL---------------------------------------------------------

	
	private void prikaziProfil(Profil profil, int status) {// status se odnosi na to da li je u pitanju moj profil ili profil iz pretrage
		if (status == MOJ_PROFIL) {
			postaviPodatkeUPanelProfil(profil);
			btnPosalji.setVisible(false);
			btnIzmeniProfil.setVisible(true);

		} else {
			if (profil.getVidljivost() == PROFIL_VIDLJIV) {
				btnPosalji.setVisible(true);
				btnIzmeniProfil.setVisible(false);
				postaviPodatkeUPanelProfil(profil);
				postaviFunkcionalnostDugmetaPosalji(profil);
			} else {
				panelProfil.setVisible(true);
				panelPocetna.setVisible(false);
				panelPrijatelji.setVisible(false);
				panelPoruke.setVisible(false);
				panelGrupa.setVisible(false);
				panelKreiranjeGrupa.setVisible(false);
				panelPrikazGrupe.setVisible(false);
				panelIzmenaProfila.setVisible(false);
				btnIzmeniProfil.setVisible(false);
				
				tpLicniPodaci.setText("Profil korisnika " + profil.getKorisnickoIme() + " nije vidljiv!!!");
				Image slikaProfila = null;
				try {
					Image slika = ImageIO.read(new File(SLIKA_PROFIL_NEVIDLJIV));
					slikaProfila=slika.getScaledInstance(lblSlikaProfila.getWidth(), lblSlikaProfila.getHeight(), Image.SCALE_SMOOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
				lblSlikaProfila.setIcon(new ImageIcon(slikaProfila));
				postaviFunkcionalnostDugmetaPosalji(profil);
			}

		}

	}

	private void postaviPodatkeUPanelProfil(Profil profil) {
		
		panelProfil.setVisible(true);
		panelPocetna.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);
		
		StringBuffer sbPodaci = new StringBuffer("LIČNI PODACI:\n\n");
		sbPodaci.append("Ime i prezime: " + profil.getIme() + " " + profil.getPrezime() + "\n");
		sbPodaci.append("Zanimanje: " + profil.getZanimanje() + "\n");
		sbPodaci.append("Pol: " + profil.getPol() + "\n");
		sbPodaci.append("Datum rođenja: " + profil.getDatumRodjenja() + "\n");
		sbPodaci.append("Datum registracije: " + profil.getDatumPrijave() + "\n");
		sbPodaci.append("Država: " + profil.getDrzava() + "\n");
		sbPodaci.append("Grad stanovanja: " + profil.getGradStanovanja() + "\n");
		tpLicniPodaci.setText(sbPodaci.toString());
		tpOMeni.setText(profil.getoMeni());
		Image slikaProfila = null;
		try {
			Image slika = ImageIO.read(new File(profil.getSlikaProfila()));
			slikaProfila=slika.getScaledInstance(lblSlikaProfila.getWidth(), lblSlikaProfila.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblSlikaProfila.setIcon(new ImageIcon(slikaProfila));

	}

	private void postaviFunkcionalnostDugmetaPosalji(Profil profil) {

		if (blokiraniKontakti != null && profilIzListeBlokiranih(profil) == true) {
			btnPosalji.setText("ODBLOKIRAJ");
			btnPosalji.setBackground(Color.BLUE);

		} else if (primljeniZahtevi != null && profilIzPrimljenihZahteva(profil) == true) {
			btnPosalji.setText("PRIHVATI ZAHTEV");
			btnPosalji.setBackground(Color.GREEN);

		} else if (poslatiZahtevi != null && profilIzPoslatihZahteva(profil) == true) {
			btnPosalji.setText("IZBRISI ZAHTEV");
			btnPosalji.setBackground(Color.RED);

		} else if (listaPrijatelja != null && profilIzPrijatelja(profil) == true) {
			btnPosalji.setText("IZBRISI PRIJATELJA");
			btnPosalji.setBackground(Color.RED);
		} else {
			btnPosalji.setText("POSALJI ZAHTEV");
			btnPosalji.setBackground(Color.green);
		}

	}

	private boolean profilIzListeBlokiranih(Profil profil) {
		for (Profil p : blokiraniKontakti) {
			if (p.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private boolean profilIzPrijatelja(Profil profil) {
		for (Profil p : listaPrijatelja) {
			if (p.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private boolean profilIzPoslatihZahteva(Profil profil) {
		for (ZahtevZaPrijateljstvo z : poslatiZahtevi) {
			if (z.getPrimalac().getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	private boolean profilIzPrimljenihZahteva(Profil profil) {
		for (ZahtevZaPrijateljstvo z : primljeniZahtevi) {
			if (z.getPosiljalac().getKorisnickoIme().equals(profil.getKorisnickoIme())) {
				return true;
			}
		}
		return false;
	}

	// -----------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU IZMENU PROFILA---------------------------------------------------------

	
	
	private void prikaziPanelIzmenaProfila() {
		
		panelIzmenaProfila.setVisible(true);
		panelPrikazGrupe.setVisible(false);
		panelProfil.setVisible(false);
		panelPocetna.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		
		tfSifra.setText(profil.getSifra());
		tfIme.setText(profil.getIme());
		tfPrezime.setText(profil.getPrezime());
		tfZanimanje.setText(profil.getZanimanje());
		tfGradStanovanja.setText(profil.getGradStanovanja());
		postaviDrzavu();
		dcIzmenaDatumaRodjenja.getDateEditor().setDate(profil.getDatumRodjenja());
		tpIzmenaOMeni.setText(profil.getoMeni());
		cbIzmenaPol.setSelectedItem(profil.getPol());
		Image slikaProfila = null;
		try {
			Image slika = ImageIO.read(new File(profil.getSlikaProfila()));
			slikaProfila=slika.getScaledInstance(lblIzmenaSlike.getWidth(), lblIzmenaSlike.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblIzmenaSlike.setIcon(new ImageIcon(slikaProfila));
		if(profil.getVidljivost()==PROFIL_VIDLJIV) {
			btnVidljivostProfila.setText("Profil vidljiv");
		}else {
			btnVidljivostProfila.setText("Profil nije vidljiv");
		}
		
	}
	
	private void postaviDrzavu() {
		cbPromenaDrzave.addItem("");
		String [] nizDrzava= Locale.getISOCountries();// Preko Klase Locale i statik metode uzmimamo u niz stringova sve drzave
		for(String d:nizDrzava) {// prolazimo kroz niz
			Locale drzava=new Locale("sr", d);
			cbPromenaDrzave.addItem(drzava.getDisplayCountry());// ovom metodom uzimamo naziv drzave i postavljamo je u ComboBox
			if(drzava.getDisplayCountry().equals(profil.getDrzava())) {
				cbPromenaDrzave.setSelectedItem(drzava.getDisplayCountry());
			}
		}
	}
	
	
	// -----------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE PRIKAZUJU GRUPU---------------------------------------------------------


	private void prikaziGrupu(Map<String, Object> podaciGrupe) {
		
		panelPrikazGrupe.setVisible(true);
		panelProfil.setVisible(false);
		panelPocetna.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		postaviPodatkeUPanelPrikazGrupe(podaciGrupe);

	}

	private void postaviPodatkeUPanelPrikazGrupe(Map<String, Object> podaciGrupe) {
		
		izabranaGrupa = (Grupa) podaciGrupe.get("grupa");
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		lblNazivGrupe.setText(izabranaGrupa.getNazivGrupe());
		lblKorImeKreatora.setText(izabranaGrupa.getKreatorGrupe().getKorisnickoIme());
		tpOpisGrupe.setText(izabranaGrupa.getOpisGrupe());
		btnIzbrisiClana.setVisible(false);
		listClanoviGrupe.setEnabled(false);
		lblIzaberiNovogClana.setVisible(false);
		spDodajNoveClanove.setVisible(false);
		Image slikaGrupe = null;
		try {
			Image slika = ImageIO.read(new File(izabranaGrupa.getSlikaGrupe()));
			slikaGrupe=slika.getScaledInstance(lblSlikaGrupePrikaz.getWidth(), lblSlikaGrupePrikaz.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblSlikaGrupePrikaz.setIcon(new ImageIcon(slikaGrupe));

		postaviClanoveGrupeUListuClanova(clanoviGrupe);

		if (mojiPoslatiZahteviZaGrupe != null && grupaIzPoslatihZahteva(izabranaGrupa.getNazivGrupe())) {
			btnAkcijaZaGrupu.setText("Izbrisi zahtev");
			btnAkcijaZaGrupu.setBackground(Color.RED);
			btnIzmeniGrupu.setVisible(false);

		} else if (mojeGrupe != null && kreatorGrupe(izabranaGrupa.getNazivGrupe()) == true) {
			btnAkcijaZaGrupu.setText("Dodaj clana");
			btnAkcijaZaGrupu.setBackground(Color.WHITE);
			btnIzmeniGrupu.setVisible(true);
			listClanoviGrupe.setEnabled(true);

		} else if (mojeGrupe != null && clanGrupe(izabranaGrupa.getNazivGrupe()) == true) {
			btnAkcijaZaGrupu.setText("Napusti grupu");
			btnAkcijaZaGrupu.setBackground(Color.RED);
			btnIzmeniGrupu.setVisible(false);

		} else {
			btnAkcijaZaGrupu.setText("Posalji zahtev");
			btnAkcijaZaGrupu.setBackground(Color.GREEN);
			btnIzmeniGrupu.setVisible(false);
		}

	}

	public void postaviClanoveGrupeUListuClanova(List<ClanGrupe> clanoviGrupe) {
		dlmClanoviGrupe.clear();
		for (ClanGrupe cg : clanoviGrupe) {
			if (cg.getStatusClana() == CLAN_GRUPE) {
				dlmClanoviGrupe.addElement(cg.getClan());

			}
		}

	}

	private boolean grupaIzPoslatihZahteva(String nazivGrupe) {
		for (ClanGrupe cg : mojiPoslatiZahteviZaGrupe) {
			if (cg.getGrupa().getNazivGrupe().equals(nazivGrupe)) {
				return true;
			}
		}
		return false;
	}

	private boolean clanGrupe(String nazivGrupe) {
		for (Grupa gr : mojeGrupe) {
			if (gr.getNazivGrupe().equals(nazivGrupe)
					&& !gr.getKreatorGrupe().getKorisnickoIme().equals(profil.getKorisnickoIme()))
				return true;
		}
		return false;
	}

	private boolean kreatorGrupe(String nazivGrupe) {
		for (Grupa gr : mojeGrupe) {
			if (gr.getNazivGrupe().equals(nazivGrupe)
					&& gr.getKreatorGrupe().getKorisnickoIme().equals(profil.getKorisnickoIme()))
				return true;
		}
		return false;
	}

	private void napustiGrupu(ClanGrupe clan, TransferKlasa tk) {
		Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(clan.getGrupa().getNazivGrupe());
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		Grupa grupa = (Grupa) podaciGrupe.get("grupa");
		izbrisiGrupuIzMojihGrupa(grupa);
		int zaBrisanje = 0;
		for (ClanGrupe cg : clanoviGrupe) {
			if (cg.getClan().getIdProfila() == clan.getClan().getIdProfila()) {

				break;
			}
			zaBrisanje++;
		}
		clanoviGrupe.remove(zaBrisanje);
		dlmClanoviGrupe.removeElementAt(zaBrisanje);

		tk.setOperacija(NAPUSTI_GRUPU);
		tk.setKlijentObjekat(clan);
		Komunikacija.vratiInstancu().posalji(tk);
		tk.setOperacija(OSVEZI_LISTU_SVIH_GRUPA);
		tk.setKlijentObjekat(sveGrupe);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void posaljiZahtevZaGrupu(ClanGrupe clan, TransferKlasa tk) {
		try {
			mojiPoslatiZahteviZaGrupe.add(clan);
		} catch (Exception e) {
			mojiPoslatiZahteviZaGrupe = new ArrayList<>();
			mojiPoslatiZahteviZaGrupe.add(clan);
		}

		dlmPoslatiZahteviGrupe.addElement(clan);
		tk.setOperacija(ZAHTEV_ZA_GRUPU);
		tk.setKlijentObjekat(clan);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void izbrisiClanaGrupe(String nazivGrupe, int indeksClana) {
		
		Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(nazivGrupe);
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		Grupa grupa = (Grupa) podaciGrupe.get("grupa");
		ClanGrupe zaBrisanje = clanoviGrupe.get(indeksClana);
		clanoviGrupe.remove(indeksClana);
		dlmClanoviGrupe.removeElementAt(indeksClana);
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(IZBRISI_CLANA_GRUPE);
		tk.setKlijentObjekat(zaBrisanje);
		Komunikacija.vratiInstancu().posalji(tk);
		tk.setOperacija(OSVEZI_LISTU_SVIH_GRUPA);
		tk.setKlijentObjekat(sveGrupe);
		Komunikacija.vratiInstancu().posalji(tk);
	}

	private void dodajClanaGrupe(String nazivGrupe, Profil p) {
		Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(nazivGrupe);
		Grupa grupa = (Grupa) podaciGrupe.get("grupa");
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		ClanGrupe noviClan = new ClanGrupe(p, grupa, CLAN_GRUPE, OBICAN_CLAN_GRUPE, 0);
		int provera = 0;
		for (ClanGrupe cg : clanoviGrupe) {
			if (cg.getClan().getIdProfila() == noviClan.getClan().getIdProfila()) {
				provera = 1;
				break;
			}
		}
		if (provera == 1) {
			JOptionPane.showMessageDialog(null, "Vec postoji takav clan!!!");
			return;
		}
		clanoviGrupe.add(noviClan);
		dlmClanoviGrupe.addElement(p);
		Map<String, Object> clanGrupe=new HashMap<>();
		clanGrupe.put("noviClan", noviClan);
		clanGrupe.put("dopisivanjeGrupe", grupnePoruke.get(noviClan.getGrupa().getNazivGrupe()));
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(DODAJ_NOVOG_CLANA);
		tk.setKlijentObjekat(clanGrupe);
		Komunikacija.vratiInstancu().posalji(tk);
		tk.setOperacija(OSVEZI_LISTU_SVIH_GRUPA);
		tk.setKlijentObjekat(sveGrupe);
		Komunikacija.vratiInstancu().posalji(tk);
		
		posaljiSlikuGrupeClanu(noviClan, tk);		

	}

	// ------------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU PANEL PRIJATELJI---------------------------------------------------------


	private void prikaziPanelPrijatelji() {
		
		btnPrikaziProfil.setVisible(false);
		btnBlokirajPrihvati.setVisible(false);
		btnIzbrisi.setVisible(false);
		btnPosaljiPoruku.setVisible(false);
		panelPocetna.setVisible(false);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(true);
		panelPoruke.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		
		

	}

	private void prikaziUListiPrijatelja() {
		dlmPrijatelji.clear();
		if (listaPrijatelja != null || !listaPrijatelja.isEmpty()) {
			for (Profil p : listaPrijatelja) {
				dlmPrijatelji.addElement(p);
			}
		}

	}

	private void prikaziPoslateZahteve() {
		dlmPoslatiZahtevi.clear();
		if (!poslatiZahtevi.isEmpty()) {
			for (ZahtevZaPrijateljstvo p : poslatiZahtevi) {
				dlmPoslatiZahtevi.addElement(p);
			}
		}
	}

	private void prikaziPrimljeneZahteve() {
		dlmPrimljeniZahtevi.clear();
		if (!primljeniZahtevi.isEmpty()) {
			for (ZahtevZaPrijateljstvo p : primljeniZahtevi) {
				dlmPrimljeniZahtevi.addElement(p);
			}
			btnPrijatelji.setBackground(Color.GREEN);
			lblPrimljeniZahtevi.setBackground(Color.green);
			lblPrimljeniZahtevi.setOpaque(true);
		}
	}

	private void posaljiZahtev(ZahtevZaPrijateljstvo poslatZahtev, TransferKlasa tk) {
		poslatiZahtevi.add(poslatZahtev);
		dlmPoslatiZahtevi.addElement(poslatZahtev);
		tk.setOperacija(ZAHTEV_ZA_PRIJATELJSTVO);
		tk.setKlijentObjekat(poslatZahtev);
		Komunikacija.vratiInstancu().posalji(tk);
		Profil zaBrisanje=null;
		for(Profil p:listaPredlogPrijatelja) {
			if(p.getIdProfila()==poslatZahtev.getPrimalac().getIdProfila()) {
				zaBrisanje=p;
			}
		}
		listaPredlogPrijatelja.remove(zaBrisanje);
		dlmPredlogPrijatelja.removeElement(zaBrisanje);

	}

	private void prihvatiZahtev(ZahtevZaPrijateljstvo izabranZahtev, TransferKlasa tk) {
		tk.setOperacija(ZAHTEV_PRIHVACEN);
		tk.setKlijentObjekat(izabranZahtev);
		Komunikacija.vratiInstancu().posalji(tk);
		posaljiPrijateljuSlikuProfila(izabranZahtev.getPosiljalac().getKorisnickoIme(),tk);
		postaviUListuPrijatelja(izabranZahtev.getPosiljalac());
		izbrisiZahtevIzListePrimljenihZahteva(izabranZahtev);

	}

	private void posaljiPrijateljuSlikuProfila(String korisnickoImePrimaoca, TransferKlasa tk) {
		tk.setOperacija(SLANJE_SLIKE_PRIJATELJU);
		File f=new File(profil.getSlikaProfila());
		byte[] podatakFajl=null;
		try {
			podatakFajl = Files.readAllBytes(f.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SlanjeFajla sf=new SlanjeFajla(podatakFajl, korisnickoImePrimaoca ,profil.getKorisnickoIme());
		tk.setKlijentObjekat(sf);
		Komunikacija.vratiInstancu().posalji(tk);
	}

	private void izbrisiPoslatZahtev(ZahtevZaPrijateljstvo poslatZahtev, TransferKlasa tk) {
		tk.setOperacija(BRISANJE_POSLATOG_ZAHTEVA);
		tk.setKlijentObjekat(poslatZahtev);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void izbrisiPrimljenZahtev(ZahtevZaPrijateljstvo primljenZahtev, TransferKlasa tk) {
		tk.setOperacija(BRISANJE_PRIMLJENOG_ZAHTEVA);
		tk.setKlijentObjekat(primljenZahtev);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void izbrisiPrijatelja(ZahtevZaPrijateljstvo poslatZahtev, TransferKlasa tk) {
		tk.setOperacija(IZBRISI_PRIJATELJA);
		tk.setKlijentObjekat(poslatZahtev);
		Komunikacija.vratiInstancu().posalji(tk);
		izbrisiPrijateljaIzListePrijatelja(poslatZahtev.getPrimalac());

	}

	private void blokirajKontakt(ZahtevZaPrijateljstvo zahtev, TransferKlasa tk) {
		tk.setOperacija(ZAHTEV_ZA_BLOKIRANJE);
		tk.setKlijentObjekat(zahtev);
		Komunikacija.vratiInstancu().posalji(tk);
	}

	private void izbrisiBlokiranogPrijatelja(Profil izabranPrijatelj) {
		try {
			blokiraniKontakti.add(izabranPrijatelj);
		} catch (Exception e) {
			blokiraniKontakti = new ArrayList<>();
			blokiraniKontakti.add(izabranPrijatelj);
		}
		listaPrijatelja.remove(selektPrijatelj);
		dlmPrijatelji.removeElementAt(selektPrijatelj);
		try {
			izbaciIzListeAktivnih(izabranPrijatelj);
		} catch (Exception e) {
		}
		
	}

	private void odblokirajKontakt(ZahtevZaPrijateljstvo poslatZahtev, TransferKlasa tk) {
		
		Profil odblokiraj = null;
		for (Profil p : blokiraniKontakti) {
			if (p.getIdProfila() == poslatZahtev.getPrimalac().getIdProfila()) {
				odblokiraj = p;
				break;
			}
		}
		blokiraniKontakti.remove(odblokiraj);
		tk.setOperacija(ODBLOKIRAJ_KONTAKT);
		tk.setKlijentObjekat(poslatZahtev);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	// ---------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU PANEL ZA SLANJE PRIVATNE PORUKE----------------------------------------------


	private void prikaziPanelPrivatnePoruke() {
		
		panelPoruke.setVisible(true);
		panelPocetna.setVisible(false);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelGrupa.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		lblPogledajProfilGrupu.setIcon(new ImageIcon(ICON_PROFIL));
		lblPogledajProfilGrupu.setText("Pogledaj profil");
		lblPrikazDopisivanja.setText("AKTIVNI PRIJATELJI");
		spDopisivanja.setViewportView(listAkrivniPrijatelji);
		listAkrivniPrijatelji.setFixedCellHeight(50);
		lblPorukeOd.setText("");
		taPrikazPoruka.setText("");
		tfUnosPoruke.setText("");
		

		int velicina = cbNovePoruke.getModel().getSize();
		if (velicina > 1) {
			cbNovePoruke.setVisible(true);
		} else {
			cbNovePoruke.setVisible(false);
		}

		

	}

	private void prikaziDopisivanje(Profil primalac, boolean statusAktivnosti) {
		
		taPrikazPoruka.setText("");
		tfUnosPoruke.setEditable(true);
		
		PrikazivanjeUListiAktivnih.promenaPozadine(VRATI_BELU_POZADINU);
		
		lblPorukeOd.setText("DOPISIVANJE SA " + primalac.getKorisnickoIme());
		Map<String, List<PrivatnaPoruka>> dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(primalac.getKorisnickoIme());
		if (dopisivanje == null) {
			privatnePoruke.put(primalac.getKorisnickoIme(), new HashMap<>());
			dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(primalac.getKorisnickoIme());
		}

		List<PrivatnaPoruka> porukePrijatelja = dopisivanje.get("procitanePoruke");
		if (porukePrijatelja == null) {
			dopisivanje.put("procitanePoruke", new ArrayList<>());
			porukePrijatelja = dopisivanje.get("procitanePoruke");
		}
		List<PrivatnaPoruka> neprocitanePorukeIzBaze = dopisivanje.get("neprocitanePoruke");
		if (neprocitanePorukeIzBaze == null) {
			dopisivanje.put("neprocitanePoruke", new ArrayList<>());
			neprocitanePorukeIzBaze = dopisivanje.get("neprocitanePoruke");
		}
		List<PrivatnaPoruka> noveNeprocitane = dopisivanje.get("noveNeprocitane");
		if (noveNeprocitane == null) {
			dopisivanje.put("noveNeprocitane", new ArrayList<>());
			noveNeprocitane = dopisivanje.get("noveNeprocitane");
		}

		for (PrivatnaPoruka por : porukePrijatelja) {
			if (profil.getIdProfila() == por.getPosiljalac().getIdProfila()) {
				taPrikazPoruka.append("Ja (" + por.getDatumSlanja() + "/" + por.getVremeSlanja() + "): "
						+ por.getTekstPoruke() + "\n\n");

			} else {
				taPrikazPoruka.append(por.toString());
			}
		}
		if (!neprocitanePorukeIzBaze.isEmpty()) {
			taPrikazPoruka.append(obavestenjeNeprocitanePoruke + "\n\n");
			for (PrivatnaPoruka por : neprocitanePorukeIzBaze) {
				por.setStatusPoruke(PORUKA_JE_PROCITANA);
				taPrikazPoruka.append(por.toString());
			}
			porukePrijatelja.addAll(neprocitanePorukeIzBaze);
			promeniStatusPorukaUBazi(neprocitanePorukeIzBaze);
			neprocitanePorukeIzBaze.clear();
		}
		if (!noveNeprocitane.isEmpty()) {
			taPrikazPoruka.append(obavestenjeNeprocitanePoruke + "\n\n");
			for (PrivatnaPoruka por : noveNeprocitane) {
				promeniStatusPrivatnePoruke(por);
				por.setStatusPoruke(PORUKA_JE_PROCITANA);
				taPrikazPoruka.append(por.toString());
			}
			porukePrijatelja.addAll(noveNeprocitane);
			try {
				trenutniRazgovori.get(primalac.getKorisnickoIme()).addAll(noveNeprocitane);
			} catch (Exception e) {
				trenutniRazgovori.put(primalac.getKorisnickoIme(), new ArrayList<>());
				trenutniRazgovori.get(primalac.getKorisnickoIme()).addAll(noveNeprocitane);
			}
			noveNeprocitane.clear();
		}
		if (statusAktivnosti == false) {
			taPrikazPoruka.append(korisnikNijeAktivan + "\n\n");
		}

	}

	private void promeniStatusPorukaUBazi(List<PrivatnaPoruka> neprocitanePorukeIzBaze) {
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(PROMENI_STATUS_PRIVATNIH_PORUKA);
		tk.setKlijentObjekat(neprocitanePorukeIzBaze);
		Komunikacija.vratiInstancu().posalji(tk);
	}

	private void prikaziUListiAktivnih() {
		dlmAktivniPrijatelji.clear();
		try {
			for (Profil aktivni : listaAktivniPrijatelji) {
				dlmAktivniPrijatelji.addElement(aktivni);
			}

		} catch (Exception e) {
			listaAktivniPrijatelji = new ArrayList<>();
		}

	}

	private void posaljiPrivatnuPoruku(Profil primalac) {
		String tekstPoruke = tfUnosPoruke.getText();
		
		if (tekstPoruke != null && !tfUnosPoruke.getText().equals("") && primalac != null) {
			TransferKlasa tk = new TransferKlasa();
			PrivatnaPoruka porukaZaSlanje = new PrivatnaPoruka();
			porukaZaSlanje.setIdPoruke(++brojPrivatnePoruke);
			porukaZaSlanje.setPosiljalac(profil);
			porukaZaSlanje.setPrimalac(primalac);
			porukaZaSlanje.setTekstPoruke(tekstPoruke);
			porukaZaSlanje.setDatumSlanja(new Date(new java.util.Date().getTime()));
			porukaZaSlanje.setVremeSlanja(new Time(new java.util.Date().getTime()));
			porukaZaSlanje.setStatusPoruke(PORUKA_NIJE_PROCITANA);
			// ovo je dopisivanje sa porukama iz baze
			Map<String, List<PrivatnaPoruka>> dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke
					.get(primalac.getKorisnickoIme());
			if (dopisivanje == null) {
				privatnePoruke.put(primalac.getKorisnickoIme(), new HashMap<>());
				dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(primalac.getKorisnickoIme());
			}
			try {
				dopisivanje.get("procitanePoruke").add(porukaZaSlanje);
			} catch (Exception e) {
				dopisivanje.put("procitanePoruke", new ArrayList<>());
				dopisivanje.get("procitanePoruke").add(porukaZaSlanje);
			}
			try {
				trenutniRazgovori.get(primalac.getKorisnickoIme()).add(porukaZaSlanje);
			} catch (Exception e) {
				trenutniRazgovori.put(primalac.getKorisnickoIme(), new ArrayList<>());
				trenutniRazgovori.get(primalac.getKorisnickoIme()).add(porukaZaSlanje);
			}
			taPrikazPoruka.append("Ja (" + porukaZaSlanje.getDatumSlanja() + "/" + porukaZaSlanje.getVremeSlanja()
					+ "): \n" + tekstPoruke + "\n\n");
			tk.setOperacija(PRIVATNA_PORUKA);
			tk.setKlijentObjekat(porukaZaSlanje);
			Komunikacija.vratiInstancu().posalji(tk);
			tfUnosPoruke.setText("");
		}

	}

	private void postaviKomponenteZaPrivatnePoruke() {
		lblPorukeOd.setText("");
		taPrikazPoruka.setText("");
		tfUnosPoruke.setText("");

	}

	// ------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU PANEL GRUPE---------------------------------------------------------


	private void prikaziPanelGrupe() {
		
		panelGrupa.setVisible(true);
		panelPocetna.setVisible(false);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

	}

	private void prikazuListuPrimljenihZahtevaZaGrupe() {
		dlmPrimljeniZahteviGrupe.clear();
		if (!primljeniZahteviZaGrupe.isEmpty()) {
			for (ClanGrupe cg : primljeniZahteviZaGrupe) {
				dlmPrimljeniZahteviGrupe.addElement(cg);
			}
			lblPrimljeniZahteviZaGrupe.setText("Novi zahtevi");
			lblPrimljeniZahteviZaGrupe.setBackground(Color.GREEN);
			lblPrimljeniZahteviZaGrupe.setOpaque(true);
		} else {
			lblPrimljeniZahteviZaGrupe.setText("PRIMLJENI ZAHTEVI");
			lblPrimljeniZahteviZaGrupe.setBackground(Color.WHITE);
			lblPrimljeniZahteviZaGrupe.setOpaque(true);
		}
	}

	private void prikazuListuPoslatihZahtevaZaGrupe() {
		dlmPoslatiZahteviGrupe.clear();
		if (mojiPoslatiZahteviZaGrupe != null) {
			for (ClanGrupe cg : mojiPoslatiZahteviZaGrupe) {
				dlmPoslatiZahteviGrupe.addElement(cg);
			}
		}
	}

	public void prikaziListuGrupa() {
		dlmGrupe.clear();
		if (mojeGrupe != null) {
			for (Grupa pg : mojeGrupe) {
				dlmGrupe.addElement(pg);
			}
		}

	}

	private void izbrisiPoslatZahtevZaGrupu(ClanGrupe clan, TransferKlasa tk) {
		ClanGrupe zaBrisanje = null;
		for (ClanGrupe cg : mojiPoslatiZahteviZaGrupe) {
			if (cg.getClan().getIdProfila() == clan.getClan().getIdProfila()) {
				zaBrisanje = cg;
				break;
			}

		}
		mojiPoslatiZahteviZaGrupe.remove(zaBrisanje);
		dlmPoslatiZahteviGrupe.removeElement(zaBrisanje);
		tk.setOperacija(IZBRISI_POSLAT_ZAHTEV_ZA_GRUPU);
		tk.setKlijentObjekat(clan);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	private void izbrisiPrimljenZahtevZaGrupu(ClanGrupe izabranClan, TransferKlasa tk) {
		primljeniZahteviZaGrupe.remove(izabranClan);
		dlmPrimljeniZahteviGrupe.removeElement(izabranClan);
		tk.setOperacija(IZBRISI_PRIMLJEN_ZAHTEV_ZA_GRUPU);
		tk.setKlijentObjekat(izabranClan);
		Komunikacija.vratiInstancu().posalji(tk);
		if (panelGrupa.isVisible()) {
			prikaziPanelGrupe();
		}

	}

	private void prihvatiZahtevZaGrupu(ClanGrupe izabranClan, TransferKlasa tk) {
		primljeniZahteviZaGrupe.remove(izabranClan);
		dlmPrimljeniZahteviGrupe.removeElement(izabranClan);
		Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(izabranClan.getGrupa().getNazivGrupe());
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		izabranClan.setStatusClana(CLAN_GRUPE);
		clanoviGrupe.add(izabranClan);
		dlmClanoviGrupe.addElement(izabranClan.getClan());
		
		Map<String, Object> clanGrupe=new HashMap<>();
		clanGrupe.put("noviClan", izabranClan);
		clanGrupe.put("dopisivanjeGrupe", grupnePoruke.get(izabranClan.getGrupa().getNazivGrupe()));
		tk.setOperacija(ZAHTEV_ZA_GRUPU_PRIHVACEN);
		tk.setKlijentObjekat(clanGrupe);
		Komunikacija.vratiInstancu().posalji(tk);
		tk.setOperacija(OSVEZI_LISTU_SVIH_GRUPA);
		tk.setKlijentObjekat(sveGrupe);
		Komunikacija.vratiInstancu().posalji(tk);
		posaljiSlikuGrupeClanu(izabranClan,tk);

	}

	private void posaljiSlikuGrupeClanu(ClanGrupe clanGrupe, TransferKlasa tk) {
		tk.setOperacija(SLANJE_SLIKE_CLANU_GRUPE);
		File f=new File(clanGrupe.getGrupa().getSlikaGrupe());
		byte[] podatakFajl=null;
		try {
			podatakFajl = Files.readAllBytes(f.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SlanjeFajla sf=new SlanjeFajla(podatakFajl, clanGrupe.getClan().getKorisnickoIme(),clanGrupe.getGrupa().getNazivGrupe());
		tk.setKlijentObjekat(sf);
		Komunikacija.vratiInstancu().posalji(tk);
		
	}

	// ----------------------------------------------------------------------------------

	// --------------------------------------METODE KOJE DEFINISU PANEL ZA KREIRANJE GRUPE-------------------------------------------------


	private void prikaziPanelKreirajGrupu(String status, Map<String, Object> podaciGrupe) {
		
		panelKreiranjeGrupa.setVisible(true);
		panelGrupa.setVisible(false);
		panelPocetna.setVisible(false);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelPoruke.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		tfUnesiImeGrupe.setBackground(Color.WHITE);
		tfUnesiImeGrupe.setText("");
		tfUnesiOpisGrupe.setText("");

		if (status == NOVA_GRUPA) {
			lblKreirajIzmeniGrupu.setText("-KREIRANJE NOVE GRUPE-");
			btnKreirajIzmeniGrupu.setText("Kreiraj grupu");
			lblSlikaGrupe.setIcon(new ImageIcon(SLIKA_GRUPE));
			tfUnesiImeGrupe.setEnabled(true);
			btnIzaberiSlikuGrupe.setVisible(false);
		} else {
			Grupa mojaGrupa=(Grupa) podaciGrupe.get("grupa");
			lblKreirajIzmeniGrupu.setText("-IZMENA GRUPE-");
			btnKreirajIzmeniGrupu.setText("Izmeni grupu");
			btnIzaberiSlikuGrupe.setVisible(true);
			tfUnesiImeGrupe.setText(mojaGrupa.getNazivGrupe());
			tfUnesiImeGrupe.setEnabled(false);
			tfUnesiOpisGrupe.setText(mojaGrupa.getOpisGrupe());
			Image slikaGrupe = null;
			try {
				Image slika = ImageIO.read(new File(mojaGrupa.getSlikaGrupe()));
				slikaGrupe=slika.getScaledInstance(lblSlikaGrupe.getWidth(), lblSlikaGrupe.getHeight(), Image.SCALE_SMOOTH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lblSlikaGrupe.setIcon(new ImageIcon(slikaGrupe));
		}

	}

	// ----------------------------------------------------------------------------------
	
	// --------------------------------------METODE KOJE DEFINISU PANEL ZA GRUPNO DOPISIVANJE-------------------------------------------------
	

	private void prikaziPanelGrupnePoruke() {
		
		panelPoruke.setVisible(true);
		panelGrupa.setVisible(false);
		panelPocetna.setVisible(false);
		panelProfil.setVisible(false);
		panelPrijatelji.setVisible(false);
		panelKreiranjeGrupa.setVisible(false);
		panelPrikazGrupe.setVisible(false);
		panelIzmenaProfila.setVisible(false);

		cbNovePoruke.setVisible(false);
		lblPogledajProfilGrupu.setIcon(new ImageIcon(ICON_GRUPA));
		lblPogledajProfilGrupu.setText("Pogledaj grupu");
		lblPrikazDopisivanja.setText("GRUPE ZA DOPISIVANJE");
		spDopisivanja.setViewportView(listGrupeZaDopisivanje);
		listGrupeZaDopisivanje.setFixedCellHeight(50);
		lblPorukeOd.setText("");
		taPrikazPoruka.setText("");
		tfUnosPoruke.setText("");


	}

	private void prikaziGrupnoDopisivanje(Grupa izabranaGrupa) {
		
		taPrikazPoruka.setText("");
		tfUnosPoruke.setEditable(true);
		
		PrikazivanjeUListiGrupnoDopisivanje.oznaciUListiAktivnih(VRATI_BELU_POZADINU);
		
		lblPorukeOd.setText("DOPISIVANJE GRUPE " + izabranaGrupa.getNazivGrupe());

		List<GrupnaPoruka> porukeGrupe = grupnePoruke.get(izabranaGrupa.getNazivGrupe());
		if (porukeGrupe == null) {
			grupnePoruke.put(izabranaGrupa.getNazivGrupe(), new ArrayList<>());
			porukeGrupe = grupnePoruke.get(izabranaGrupa.getNazivGrupe());
		}

		List<GrupnaPoruka> noveNeprocitane = noveGrupnePoruke.get(izabranaGrupa.getNazivGrupe());
		if (noveNeprocitane == null) {
			noveGrupnePoruke.put(izabranaGrupa.getNazivGrupe(), new ArrayList<>());
			noveNeprocitane = noveGrupnePoruke.get(izabranaGrupa.getNazivGrupe());
		}
		Integer brojTrenutnihPoruka=brojProcitanihGrupnihPoruka.get(izabranaGrupa.getIdGrupa());
		if(brojTrenutnihPoruka!=null) {
			if(brojTrenutnihPoruka<porukeGrupe.size()) {
				brojTrenutnihPoruka=porukeGrupe.size();
				 brojProcitanihGrupnihPoruka.put(izabranaGrupa.getIdGrupa(),brojTrenutnihPoruka);
			}
		}
		// ovde ako hocu da uradim dokle sam procitao poruke!!!
		for (GrupnaPoruka por : porukeGrupe) {
			if (profil.getIdProfila() == por.getPosiljalac().getIdProfila()) {
				taPrikazPoruka.append("Ja (" + por.getDatumSlanja() + "/" + por.getVremeSlanja() + "): "
						+ por.getTekstPoruke() + "\n\n");

			} else {
				taPrikazPoruka.append(por.toString());
			}
		}
		if (!noveNeprocitane.isEmpty()) {
			taPrikazPoruka.append(obavestenjeNeprocitanePoruke + "\n\n");
			for (GrupnaPoruka por : noveNeprocitane) {
				taPrikazPoruka.append(por.toString());
			}
			brojTrenutnihPoruka+=noveNeprocitane.size();
			brojProcitanihGrupnihPoruka.put(izabranaGrupa.getIdGrupa(),brojTrenutnihPoruka);
			porukeGrupe.addAll(noveNeprocitane);
			noveNeprocitane.clear();
		}

	}

	private void posaljiGrupnuPoruku(Grupa izabranaGrupa) {
		
		String tekstPoruke = tfUnosPoruke.getText();
		if (tekstPoruke != null && !tfUnosPoruke.getText().equals("") && izabranaGrupa != null) {
			TransferKlasa tk = new TransferKlasa();
			GrupnaPoruka porukaZaSlanje = new GrupnaPoruka();
			porukaZaSlanje.setPosiljalac(profil);
			porukaZaSlanje.setGrupa(izabranaGrupa);
			porukaZaSlanje.setTekstPoruke(tekstPoruke);
			porukaZaSlanje.setDatumSlanja(new Date(new java.util.Date().getTime()));
			porukaZaSlanje.setVremeSlanja(new Time(new java.util.Date().getTime()));

			List<GrupnaPoruka> dopisivanje = grupnePoruke.get(izabranaGrupa.getNazivGrupe());
			if (dopisivanje == null) {
				grupnePoruke.put(izabranaGrupa.getNazivGrupe(), new ArrayList<>());
				dopisivanje = grupnePoruke.get(izabranaGrupa.getNazivGrupe());
			}
			dopisivanje.add(porukaZaSlanje);
			int brojTrenutnihPoruka=brojProcitanihGrupnihPoruka.get(izabranaGrupa.getIdGrupa()).intValue();
			brojTrenutnihPoruka++;
			brojProcitanihGrupnihPoruka.put(izabranaGrupa.getIdGrupa(),brojTrenutnihPoruka);
			taPrikazPoruka.append("Ja (" + porukaZaSlanje.getDatumSlanja() + "/" + porukaZaSlanje.getVremeSlanja()
					+ "): \n >>" + tekstPoruke + "\n\n");
			tk.setOperacija(GRUPNA_PORUKA);
			tk.setKlijentObjekat(porukaZaSlanje);
			Komunikacija.vratiInstancu().posalji(tk);
			tfUnosPoruke.setText("");
		}

	}

	// ---------------------------METODE KOJE UREDJUJU PROFIL FORMU NA AKCIJU DRUGOG KORISNIKA-------------------------------------------------
	
	// ---------------------------metode vezane za prijatelje----------------------------------------

	public void prikaziPrimljeniZahtevZaPrijateljstvo(ZahtevZaPrijateljstvo zzp) {
		primljeniZahtevi.add(zzp);
		dlmPrimljeniZahtevi.addElement(zzp);
		btnPrijatelji.setBackground(Color.GREEN);
		btnPrijatelji.setText("Novi zahtev");
		Profil zaBrisanje=null;
		for(Profil p:listaPredlogPrijatelja) {
			if(p.getIdProfila()==zzp.getPosiljalac().getIdProfila()) {
				zaBrisanje=p;
			}
		}
		listaPredlogPrijatelja.remove(zaBrisanje);
		dlmPredlogPrijatelja.removeElement(zaBrisanje);
	}
	
	public void izbrisiZahtevIzListePrimljenihZahteva(ZahtevZaPrijateljstvo zzp) {
		btnPosalji.setText("POSALJI ZAHTEV");
		btnPosalji.setBackground(Color.GREEN);
		ZahtevZaPrijateljstvo zaBrisanje = null;
		for (ZahtevZaPrijateljstvo z : primljeniZahtevi) {
			if (z.getPosiljalac().getIdProfila() == zzp.getPosiljalac().getIdProfila()) {
				zaBrisanje = z;
				break;
			}
		}
		primljeniZahtevi.remove(zaBrisanje);
		dlmPrimljeniZahtevi.removeElement(zaBrisanje);
		btnPrijatelji.setBackground(Color.WHITE);
		if(primljeniZahtevi.isEmpty()) {
			lblPrimljeniZahtevi.setBackground(Color.white);
			lblPrimljeniZahtevi.setOpaque(true);
		}

	}

	public void izbrisiZahtevIzListePoslatihZahteva(ZahtevZaPrijateljstvo poslatZahtev) {
		btnPosalji.setText("POSALJI ZAHTEV");
		btnPosalji.setBackground(Color.GREEN);
		ZahtevZaPrijateljstvo zaBrisanje = null;
		for (ZahtevZaPrijateljstvo z : poslatiZahtevi) {
			if (z.getPrimalac().getIdProfila() == poslatZahtev.getPrimalac().getIdProfila()) {
				zaBrisanje = z;
				break;
			}
		}
		poslatiZahtevi.remove(zaBrisanje);
		dlmPoslatiZahtevi.removeElement(zaBrisanje);
	}

	public void postaviUListuPrijatelja(Profil prijatelj) {

		listaPrijatelja.add(prijatelj);
		dlmPrijatelji.addElement(prijatelj);
		

	}

	public void prikaziNovogPrijatelja(Profil noviPrijatelj) {
		listaPrijatelja.add(noviPrijatelj);
		dlmPrijatelji.addElement(noviPrijatelj);

		btnPrijatelji.setBackground(Color.GREEN);
		btnPrijatelji.setText("Novi prijatelj");
		btnPosalji.setText("IZBRISI PRIJATELJA");
		btnPosalji.setBackground(Color.red);
		ZahtevZaPrijateljstvo zaBrisanje = null;
		for (ZahtevZaPrijateljstvo z : poslatiZahtevi) {
			if (z.getPrimalac().getIdProfila() == noviPrijatelj.getIdProfila()) {
				zaBrisanje = z;
			}
		}
		poslatiZahtevi.remove(zaBrisanje);
		dlmPoslatiZahtevi.removeElement(zaBrisanje);
		ubaciUListuAktivnih(noviPrijatelj);
		posaljiPrijateljuSlikuProfila(noviPrijatelj.getKorisnickoIme(), new TransferKlasa());
	}

	public void ubaciUListuAktivnih(Profil aktivanPrijatelj) {

		listaAktivniPrijatelji.add(aktivanPrijatelj);
		dlmAktivniPrijatelji.addElement(aktivanPrijatelj);
		if (panelPoruke.isVisible()) {
			prikaziPanelPrivatnePoruke();
		}

	}

	public void izbaciIzListeAktivnih(Profil profil) {
		Profil zaBrisanje = null;
		for (Profil pr : listaAktivniPrijatelji) {
			if (pr.getIdProfila() == profil.getIdProfila()) {
				zaBrisanje=pr;
				break;
			}
			
		}
		listaAktivniPrijatelji.remove(zaBrisanje);
		dlmAktivniPrijatelji.removeElement(zaBrisanje);

		if (panelPoruke.isVisible()) {
			prikaziPanelPrivatnePoruke();
		}
		if (aktivniPrijatelj != null && aktivniPrijatelj.getIdProfila() == profil.getIdProfila()) {
			postaviKomponenteZaPrivatnePoruke();
			aktivniPrijatelj = null;
		}

	}

	public void izbrisiPrijateljaIzListePrijatelja(Profil prijatelj) {
		Profil zaBrisanje = null;
		int index = 0;
		for (Profil p : listaPrijatelja) {
			if (p.getIdProfila() == prijatelj.getIdProfila()) {
				zaBrisanje = p;
				try {
					izbaciIzListeAktivnih(prijatelj);
				} catch (Exception e) {
				}
				break;
			}
			index++;

		}
		listaPrijatelja.remove(zaBrisanje);
		dlmPrijatelji.removeElementAt(index);
		if(panelProfil.isVisible()) {
			btnPosalji.setText("POSALJI ZAHTEV");
		}

	}

	public void izbrisiBlokirajuciKontakt(Profil pr) {
		Profil zaBrisanje = null;
		for (Profil profil : sviProfili) {
			if (pr.getIdProfila() == profil.getIdProfila()) {
				zaBrisanje = profil;
				break;
			}
		}
		sviProfili.remove(zaBrisanje);
		try {
			izbaciIzListeAktivnih(pr);
		} catch (Exception e) {
		}
		izbrisiPrijateljaIzListePrijatelja(pr);

	}

	public void dodajBlokirajuciKontakt(Profil profil) {

		listaPrijatelja.add(profil);
		dlmPrijatelji.addElement(profil);
		sviProfili.add(profil);
		ubaciUListuAktivnih(profil);

	}
	
	// ---------------------------metode vezane za privatne poruke----------------------------------------


	public void prikaziNovuPoruku(PrivatnaPoruka por) {
		// ovo je dopisivanje uzeto iz baze
		Map<String, List<PrivatnaPoruka>> dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke
				.get(por.getPosiljalac().getKorisnickoIme());
		if (dopisivanje == null) {
			privatnePoruke.put(por.getPosiljalac().getKorisnickoIme(), new HashMap<>());
			dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(por.getPosiljalac().getKorisnickoIme());
		}

		List<PrivatnaPoruka> procitane = dopisivanje.get("procitanePoruke");
		if (procitane == null) {
			dopisivanje.put("procitanePoruke", new ArrayList<>());
			procitane = dopisivanje.get("procitanePoruke");
		}
		List<PrivatnaPoruka> noveNeprocitane = dopisivanje.get("noveNeprocitane");
		if (noveNeprocitane == null) {
			dopisivanje.put("noveNeprocitane", new ArrayList<>());
			noveNeprocitane = dopisivanje.get("noveNeprocitane");
		}

		// ostale funkcionalnosti
		if (!panelPoruke.isVisible() || !listAkrivniPrijatelji.isShowing()) {
			btnPoruke.setBackground(Color.GREEN);
			btnPoruke.setText("Nova poruka");
		}

		for (int i = 0; i < listaAktivniPrijatelji.size(); i++) {
			if (listaAktivniPrijatelji.get(i).getKorisnickoIme().equals(por.getPosiljalac().getKorisnickoIme())) {
				if (listAkrivniPrijatelji.isSelectedIndex(i) && listAkrivniPrijatelji.isShowing()) {
					promeniStatusPrivatnePoruke(por);
					por.setStatusPoruke(PORUKA_JE_PROCITANA);
					procitane.add(por);
					try {
						trenutniRazgovori.get(por.getPosiljalac().getKorisnickoIme()).add(por);// dodajem da je poruka
																								// vidljiva
					} catch (Exception e) {
						trenutniRazgovori.put(por.getPosiljalac().getKorisnickoIme(), new ArrayList<>());
						trenutniRazgovori.get(por.getPosiljalac().getKorisnickoIme()).add(por);
					}
					taPrikazPoruka.append(por.toString());
					break;
				} else {
					noveNeprocitane.add(por);
					PrikazivanjeUListiAktivnih.promenaPozadine(i);
					if (panelPoruke.isVisible() && listAkrivniPrijatelji.isShowing()) {
						prikaziPanelPrivatnePoruke();
					}
					if (selektAktivni != -1) {
						listAkrivniPrijatelji.setSelectedIndex(selektAktivni);
					}
					break;
				}
			}
		}
	}

	
	public void postaviPoruke(Map<String, Object> poruke) {
		// postavljanje privatnih poruka
		Map<String, Object> privatnePoruke=null;
		try {
			privatnePoruke=(Map<String, Object>) poruke.get("privatnePoruke");
		} catch (Exception e) {
		}
		
		this.privatnePoruke = privatnePoruke;
		if (listaAktivniPrijatelji!=null) {
			for (int i = 0; i < listaAktivniPrijatelji.size(); i++) {
				Map<String, List<PrivatnaPoruka>> dopisivanje = (Map<String, List<PrivatnaPoruka>>) privatnePoruke.get(listaAktivniPrijatelji.get(i).getKorisnickoIme());
				if (dopisivanje != null && dopisivanje.get("neprocitanePoruke") != null) {
					
					PrikazivanjeUListiAktivnih.promenaPozadine(i);
					btnPoruke.setText("Nova poruka");
					btnPoruke.setBackground(Color.GREEN);

				}
			}
		}
		if (privatnePoruke!=null) {
			for (Entry<String, Object> map : privatnePoruke.entrySet()) {
				Map<String, List<PrivatnaPoruka>> dopisivanje = (Map<String, List<PrivatnaPoruka>>) map.getValue();
				if (dopisivanje.get("neprocitanePoruke") != null) {
					cbNovePoruke.addItem(map.getKey());
					btnPoruke.setText("Nova poruka");
					btnPoruke.setBackground(Color.GREEN);
				}
			}
		}
		// postavljanje grupnih poruka
		Map<String, List<GrupnaPoruka>> grupnePoruke=null;
		try {
			grupnePoruke=(Map<String, List<GrupnaPoruka>>) poruke.get("grupnePoruke");
		} catch (Exception e) {
		}
		
		this.grupnePoruke=grupnePoruke;
		
		Map<Integer, Integer> brojProcitanihPoruka = null;
		try {
			brojProcitanihPoruka=(Map<Integer, Integer>) poruke.get("brojProcitanihPoruka");
		} catch (Exception e) {
			
		}
		this.brojProcitanihGrupnihPoruka=brojProcitanihPoruka;
		if(mojeGrupe!=null) {
			for(int i = 0; i < mojeGrupe.size(); i++) {
				List<GrupnaPoruka> por=grupnePoruke.get(mojeGrupe.get(i).getNazivGrupe());
				if(por!=null && por.size()>brojProcitanihPoruka.get(mojeGrupe.get(i).getIdGrupa()).intValue()) {
					PrikazivanjeUListiGrupnoDopisivanje.oznaciUListiAktivnih(i);
					btnGrupnePoruke.setText("Nove poruke");
					btnGrupnePoruke.setBackground(Color.GREEN);
				}
			}
		}

	}

	private void promeniStatusPrivatnePoruke(PrivatnaPoruka por) {
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(PROMENI_STATUS_PRIVATNIH_PORUKA_KOD_KORISNIKA);
		tk.setKlijentObjekat(por);
		Komunikacija.vratiInstancu().posalji(tk);

	}

	public void promeniStatusPoslatePoruke(PrivatnaPoruka pp) {
		List<PrivatnaPoruka> listaPoruka = trenutniRazgovori.get(pp.getPrimalac().getKorisnickoIme());
		for (int i = (listaPoruka.size() - 1); i >= 0; i--) {
			if (listaPoruka.get(i).getIdPoruke() == pp.getIdPoruke()) {
				listaPoruka.get(i).setStatusPoruke(PORUKA_JE_PROCITANA);
				break;
			}
		}

	}
	
	// ---------------------------metode vezane za grupe----------------------------------------


	public void kreiranjeGrupe(Grupa grupa) {

		if (grupa.getIdGrupa() != 0) {
			JOptionPane.showMessageDialog(null, "Grupa je uspesno kreirana!!!");
			try {
				mojeGrupe.add(grupa);
			} catch (Exception e) {
				mojeGrupe = new ArrayList<>();
			}

			dlmGrupe.addElement(grupa);

			prikaziPanelGrupe();
			dodajNovuGrupuUListuSvih(grupa);
		} else {
			JOptionPane.showMessageDialog(null, "Grupa nije kreirana, postoji takvo ime grupe!!!");
			tfUnesiImeGrupe.setBackground(Color.RED);
		}

	}

	public void dodajNovuGrupuUListuSvih(Grupa grupa) {

		if (sveGrupe != null) {
			dodajNovuGrupu(grupa);

		} else {
			sveGrupe = new HashMap<>();
			dodajNovuGrupu(grupa);
		}

	}

	private void dodajNovuGrupu(Grupa grupa) {
		sveGrupe.put(grupa.getNazivGrupe(), new HashMap<>());
		Map<String, Object> novaGrupa = (Map<String, Object>) sveGrupe.get(grupa.getNazivGrupe());
		novaGrupa.put("grupa", grupa);
		ClanGrupe kreator = new ClanGrupe(grupa.getKreatorGrupe(), grupa, CLAN_GRUPE, ADMIN_GRUPE, 0);
		novaGrupa.put("clanoviGrupe", new ArrayList<>());
		List<ClanGrupe> clanovi = (List<ClanGrupe>) novaGrupa.get("clanoviGrupe");
		clanovi.add(kreator);

	}

	public void izbrisiGrupuIzMojihGrupa(Grupa grupa) {

		int zaBrisanje = 0;
		for (Grupa g : mojeGrupe) {
			if (g.getNazivGrupe().equals(grupa.getNazivGrupe())) {
				break;
			}
			zaBrisanje++;
		}
		mojeGrupe.remove(zaBrisanje);
		dlmGrupe.removeElementAt(zaBrisanje);

	}

	public void postaviSveGrupe(Map<String, Object> sveGrupe) {
		this.sveGrupe = sveGrupe;

	}

	public void dodajGrupuUMojeGrupe(Map<String, Object> clanGrupe) {
		
		ClanGrupe noviClan = (ClanGrupe) clanGrupe.get("noviClan");
		Grupa novaGrupa=noviClan.getGrupa();
		mojeGrupe.add(novaGrupa);
		dlmGrupe.addElement(novaGrupa);
		btnGrupe.setText("Nova grupa");
		btnGrupe.setBackground(Color.GREEN);

		if (mojiPoslatiZahteviZaGrupe != null) {
			ClanGrupe zaBrisanje = null;
			for (ClanGrupe cg : mojiPoslatiZahteviZaGrupe) {
				if (cg.getGrupa().getNazivGrupe().equals(novaGrupa.getNazivGrupe()))
					;
				zaBrisanje = cg;
				break;
			}
			mojiPoslatiZahteviZaGrupe.remove(zaBrisanje);
			dlmPoslatiZahteviGrupe.removeElement(zaBrisanje);
		}
		grupnePoruke.put(novaGrupa.getNazivGrupe(), (List<GrupnaPoruka>) clanGrupe.get("dopisivanjeGrupe"));
		brojProcitanihGrupnihPoruka.put(novaGrupa.getIdGrupa(), 0);
		Map<String, Object> podaciGrupe=(Map<String, Object>) sveGrupe.get(novaGrupa.getNazivGrupe());
		List<ClanGrupe> clanoviGrupe=(List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		TransferKlasa tk=new TransferKlasa();
		for(ClanGrupe cg:clanoviGrupe) {
			if(cg.getClan().getIdProfila()!=noviClan.getClan().getIdProfila()) {
				posaljiPrijateljuSlikuProfila(cg.getClan().getKorisnickoIme(), tk);
			}
		}
		
	}

	public void izbrisiClanaIzGrupe(ClanGrupe clanZaBrisanje) {
		Map<String, Object> podaciGrupe = (Map<String, Object>) sveGrupe.get(clanZaBrisanje.getGrupa().getNazivGrupe());
		List<ClanGrupe> clanoviGrupe = (List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		Grupa grupa = (Grupa) podaciGrupe.get("grupa");
		ClanGrupe zaBrisanje = null;
		for (ClanGrupe cg : clanoviGrupe) {
			if (cg.getClan().getIdProfila() == clanZaBrisanje.getClan().getIdProfila()) {
				zaBrisanje = cg;
				break;
			}

		}
		clanoviGrupe.remove(zaBrisanje);
		dlmClanoviGrupe.removeElement(zaBrisanje);
		postaviClanoveGrupeUListuClanova(clanoviGrupe);

	}

	public void dodajClanaUPrimljeneZahteve(ClanGrupe primljenZahtev) {

		try {
			primljeniZahteviZaGrupe.add(primljenZahtev);
		} catch (Exception e) {
			primljeniZahteviZaGrupe = new ArrayList<>();
			primljeniZahteviZaGrupe.add(primljenZahtev);
		}
		dlmPrimljeniZahteviGrupe.addElement(primljenZahtev);
		btnGrupe.setText("Novi zahtev");
		btnGrupe.setBackground(Color.GREEN);
		lblPrimljeniZahteviZaGrupe.setText("Novi primljeni zahtevi");
		lblPrimljeniZahteviZaGrupe.setBackground(Color.GREEN);
		lblPrimljeniZahteviZaGrupe.setOpaque(true);
	}

	public void izbrisiClanaIzPrimljenihZahteva(ClanGrupe primljenZahtev) {
		ClanGrupe zaBrisanje = null;
		for (ClanGrupe cg : primljeniZahteviZaGrupe) {
			if (cg.getClan().getIdProfila() == primljenZahtev.getClan().getIdProfila()) {
				zaBrisanje = cg;
				break;
			}
		}
		primljeniZahteviZaGrupe.remove(zaBrisanje);
		dlmPrimljeniZahteviGrupe.removeElement(zaBrisanje);
		if (panelGrupa.isVisible()) {
			prikaziPanelGrupe();
		}
	}

	public void izbrisiClanaIzPoslatihZahteva(ClanGrupe poslatZahtev) {
		ClanGrupe zaBrisanje = null;
		for (ClanGrupe cg : mojiPoslatiZahteviZaGrupe) {
			if (cg.getClan().getIdProfila() == poslatZahtev.getClan().getIdProfila()) {
				zaBrisanje = cg;
				break;
			}
		}
		mojiPoslatiZahteviZaGrupe.remove(zaBrisanje);
		dlmPoslatiZahteviGrupe.removeElement(zaBrisanje);

	}
	
	// ---------------------------metode vezane za grupne poruke----------------------------------------


	public void prikaziNovuGrupnuPoruku(GrupnaPoruka grupnaPoruka) {
		List<GrupnaPoruka> poruke = grupnePoruke.get(grupnaPoruka.getGrupa().getNazivGrupe());
		if (poruke == null) {
			grupnePoruke.put(grupnaPoruka.getGrupa().getNazivGrupe(), new ArrayList<>());
			poruke = grupnePoruke.get(grupnaPoruka.getGrupa().getNazivGrupe());
		}
		List<GrupnaPoruka> neprocitanePoruke = noveGrupnePoruke.get(grupnaPoruka.getGrupa().getNazivGrupe());
		if (neprocitanePoruke == null) {
			noveGrupnePoruke.put(grupnaPoruka.getGrupa().getNazivGrupe(), new ArrayList<>());
			neprocitanePoruke = noveGrupnePoruke.get(grupnaPoruka.getGrupa().getNazivGrupe());
		}

		if (!panelPoruke.isVisible() || !listGrupeZaDopisivanje.isShowing()) {
			btnGrupnePoruke.setBackground(Color.GREEN);
			btnGrupnePoruke.setText("Nova poruka");
		}
		for (int i = 0; i < mojeGrupe.size(); i++) {
			if (mojeGrupe.get(i).getIdGrupa() == grupnaPoruka.getGrupa().getIdGrupa()) {
				if (listGrupeZaDopisivanje.isSelectedIndex(i) && listGrupeZaDopisivanje.isShowing()) {
					taPrikazPoruka.append(grupnaPoruka.toString());
					poruke.add(grupnaPoruka);
					int brojTrenutnihPoruka=brojProcitanihGrupnihPoruka.get(izabranaGrupa.getIdGrupa()).intValue();
					brojTrenutnihPoruka++;
					brojProcitanihGrupnihPoruka.put(izabranaGrupa.getIdGrupa(),brojTrenutnihPoruka);
				} else {
					neprocitanePoruke.add(grupnaPoruka);
					PrikazivanjeUListiGrupnoDopisivanje.oznaciUListiAktivnih(i);
					if (panelPoruke.isVisible() && listGrupeZaDopisivanje.isShowing()) {
						prikaziPanelGrupnePoruke();
					}
					if (indeksGrupnogDopisivanja != -1) {
						listGrupeZaDopisivanje.setSelectedIndex(indeksGrupnogDopisivanja);
					}
					break;

				}

			}
		}
	}

	
	// ---------------------------metode vezane za izmenu profila----------------------------------------

	public void izmenaProfila(Profil izmenaProfil) {
		if(izmenaProfil.getIdProfila()!=0) {
			this.profil=izmenaProfil;
			JOptionPane.showMessageDialog(null, "Uspesna izmena profila");
			TransferKlasa tk=new TransferKlasa();
			for(Profil pr:sviProfili) {
				if(pr.getIdProfila()!=izmenaProfil.getIdProfila()) {
					posaljiPrijateljuSlikuProfila(pr.getKorisnickoIme(), tk);
				}
			}
			prikaziProfil(profil, MOJ_PROFIL);
			
		}else{
			JOptionPane.showMessageDialog(null, "Izmena podataka nije uspela");
			prikaziPanelIzmenaProfila();
		}
		
	}

	public void dodajIzmenjenProfila(Profil izmenaProfil) {
		Profil zaBrisanje=null;
		for(Profil p:sviProfili) {
			if(p.getIdProfila()==izmenaProfil.getIdProfila()) {
				zaBrisanje=p;
				break;
			}
		}
		sviProfili.remove(zaBrisanje);
		sviProfili.add(izmenaProfil);
		
		int indeksZaBrisanje=-1;
		for(Profil p:listaPrijatelja) {
			if(p.getIdProfila()==izmenaProfil.getIdProfila()) {
				indeksZaBrisanje++;
				break;
			}
			
		}
		if(indeksZaBrisanje>-1 && indeksZaBrisanje<mojeGrupe.size()) {
			listaPrijatelja.remove(indeksZaBrisanje);
			listaPrijatelja.add(indeksZaBrisanje, izmenaProfil);
		}
		
		indeksZaBrisanje=-1;
		for(Profil p:listaAktivniPrijatelji) {
			if(p.getIdProfila()==izmenaProfil.getIdProfila()) {
				indeksZaBrisanje++;
				break;
			}
			
		}
		if(indeksZaBrisanje>-1 && indeksZaBrisanje<mojeGrupe.size()) {
			listaAktivniPrijatelji.remove(indeksZaBrisanje);
			listaAktivniPrijatelji.add(indeksZaBrisanje, izmenaProfil);
		}
	}
	
	// ---------------------------metode vezane za izmenu grupe----------------------------------------


	public void dodajIzmenjenuGrupu(Grupa izmenjenaGrupa) {
		if(izmenjenaGrupa.getIdGrupa()!=0) {
			int indeksBrisanje=0;
			for(Grupa g:mojeGrupe) {
				if(g.getNazivGrupe().equals(izmenjenaGrupa.getNazivGrupe())) {
					break;
				}
				indeksBrisanje++;
			}
			if(indeksBrisanje<mojeGrupe.size()) {
				mojeGrupe.remove(indeksBrisanje);
				mojeGrupe.add(indeksBrisanje, izmenjenaGrupa);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Podaci grupe nisu izmenjeni!!!");
			return;
		}
		Map<String, Object> podaciGrupe=(Map<String, Object>) sveGrupe.get(izmenjenaGrupa.getNazivGrupe());		
		podaciGrupe.put("grupa", izmenjenaGrupa);
		if(izmenjenaGrupa.getKreatorGrupe().getIdProfila()==profil.getIdProfila()) {
			prikaziGrupu(podaciGrupe);
		}
		List<ClanGrupe> clanoviGrupe=(List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		TransferKlasa tk=new TransferKlasa();
		for(ClanGrupe cg:clanoviGrupe) {
			if(cg.getClan().getIdProfila()!=izmenjenaGrupa.getKreatorGrupe().getIdProfila())
				posaljiSlikuGrupeClanu(cg, tk);
		}
			
	}
	
	
	// ---------------------------metoda vezana za predlog prijatelja----------------------------------------
	
	
	public void prikaziPredloge(List<Profil> predlogPrijatelja) {
		this.listaPredlogPrijatelja=predlogPrijatelja;
		for(Profil p:listaPredlogPrijatelja)
			dlmPredlogPrijatelja.addElement(p);
		
	}
}
