package com.comtrade.forma.admin;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.ClanGrupe;
import com.comtrade.domen.Grupa;
import com.comtrade.domen.PrikazivanjeUListiGrupa;
import com.comtrade.domen.PrikazivanjeUListiPrijatelja;
import com.comtrade.domen.Profil;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.niti.NitObradeKlijent;
import com.comtrade.transfer.TransferKlasa;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.Socket;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused","unchecked"})
public class AdminForma extends JFrame implements Konstante{

	private JPanel contentPane;
	private JPanel panelPrikazSvihProfila;
	private JPanel panelPrikazSvihGrupa;
	private JPanel panelObavestenje;
	private JPanel panelPrikazProfila;
	private JPanel panelPrikazGrupa;
	private JScrollPane spPrikazProfila;
	private JScrollPane spPrikazGrupa;
	private JScrollPane spPodaciGrupe;
	private JScrollPane scrollPane;
	private JScrollPane spOKorisniku;
	private JLabel lblLogo;
	private JLabel lblObavestenje;
	private JLabel lblSlikaAdmin;
	private JLabel lblPrikaziProfil;
	private JLabel lblPrikazSvigGrupa;
	private JLabel lblPrikaziGrupu;
	private JLabel lblSlikaProfila;
	private JLabel lblSlikaGrupe;
	private JLabel lblListaClanova;
	private JLabel lblOKorisniku;
	private JButton btnIzadjiIzProfila;
	private JButton btnIzadjiIzGrupe;
	private JTextField tfPretragaProfila;
	private JTextField tfPretragaGrupa;
	private JList<Object> listSviProfili;
	private JList<Object> listSveGrupe;
	private JList<Object> listClanoviGrupe;
	private JTextPane tpPodaciProfila;
	private JTextPane tpPodaciGrupe;
	private JTextPane tpOKorisniku;
	private Profil izabranProfil;
	private Grupa izabranaGrupa;
	private Map<String, Object> podaciProfila = new HashMap<>();
	private Map<String, Object> sveGrupe = new HashMap<>();
	private List<Profil> sviProfili=new ArrayList<>();
	private DefaultListModel<Object> dlmSviProfili=new DefaultListModel<>();
	private DefaultListModel<Object> dlmSveGrupe=new DefaultListModel<>();
	private DefaultListModel<Object> dlmClanoviGrupe=new DefaultListModel<>();
	
	public AdminForma(Object obj) {
		
		podaciProfila=(Map<String, Object>) obj;
		
		sviProfili = (List<Profil>) podaciProfila.get("sviProfili");
		Map<String, Object> grupe = (Map<String, Object>) podaciProfila.get("grupe");
		sveGrupe = (Map<String, Object>) grupe.get("sveGrupe");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int odjava=JOptionPane.showConfirmDialog(null, "Da li želite da se odjavite?","Odjava",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(odjava==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		});
		
		setTitle("Admin-miniViber");
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		setMinimumSize(new Dimension(1000, 600));
		setContentPane(contentPane);
		
		panelObavestenje = new JPanel();
		panelObavestenje.setBackground(Color.WHITE);
		panelObavestenje.setBorder(null);
		
		panelPrikazSvihProfila = new JPanel();
		panelPrikazSvihProfila.setBackground(Color.WHITE);
		
		panelPrikazProfila = new JPanel();
		panelPrikazProfila.setBackground(Color.WHITE);
		
		panelPrikazSvihGrupa = new JPanel();
		panelPrikazSvihGrupa.setBackground(Color.WHITE);
		
		panelPrikazGrupa = new JPanel();
		panelPrikazGrupa.setBackground(Color.WHITE);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panelObavestenje, GroupLayout.DEFAULT_SIZE, 1333, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelPrikazProfila, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
						.addComponent(panelPrikazSvihProfila, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
						)
						.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelPrikazGrupa, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
						.addComponent(panelPrikazSvihGrupa, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
						))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panelObavestenje, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGap(2)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelPrikazGrupa, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
								.addComponent(panelPrikazSvihGrupa, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
								)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelPrikazProfila, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
								.addComponent(panelPrikazSvihProfila, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
								)))
		);
		
		lblSlikaGrupe = new JLabel("");
		
		btnIzadjiIzGrupe = new JButton("");
		btnIzadjiIzGrupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prikaziPanelSveGrupe();
			}
		});
		btnIzadjiIzGrupe.setIcon(new ImageIcon(ICON_NAZAD));
		btnIzadjiIzGrupe.setBackground(new Color(255, 255, 255));
		
		spPodaciGrupe = new JScrollPane();
		
		lblListaClanova = new JLabel("LISTA \u010CLANOVA GRUPE");
		lblListaClanova.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaClanova.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		
		scrollPane = new JScrollPane();
		GroupLayout gl_panelPrikazGrupa = new GroupLayout(panelPrikazGrupa);
		gl_panelPrikazGrupa.setHorizontalGroup(
			gl_panelPrikazGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
					.addGroup(gl_panelPrikazGrupa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
							.addGap(80)
							.addComponent(lblSlikaGrupe, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(spPodaciGrupe, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
						.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnIzadjiIzGrupe, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(42))
				.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
					.addGap(160)
					.addGroup(gl_panelPrikazGrupa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
							.addGap(160))
						.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
							.addComponent(lblListaClanova, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
							.addGap(158))))
		);
		gl_panelPrikazGrupa.setVerticalGroup(
			gl_panelPrikazGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazGrupa.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnIzadjiIzGrupe, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_panelPrikazGrupa.createParallelGroup(Alignment.LEADING)
						.addComponent(spPodaciGrupe, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSlikaGrupe, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblListaClanova, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		listClanoviGrupe = new JList<Object>(dlmClanoviGrupe);
		listClanoviGrupe.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		listClanoviGrupe.setFixedCellHeight(50);
		scrollPane.setViewportView(listClanoviGrupe);
		
		tpPodaciGrupe = new JTextPane();
		tpPodaciGrupe.setFont(new Font("Arial", Font.PLAIN, 13));
		tpPodaciGrupe.setEditable(false);
		tpPodaciGrupe.setBorder(new EmptyBorder(5, 5, 5, 5));
		spPodaciGrupe.setViewportView(tpPodaciGrupe);
		panelPrikazGrupa.setLayout(gl_panelPrikazGrupa);
		
		lblSlikaProfila = new JLabel("");
		
		tpPodaciProfila = new JTextPane();
		tpPodaciProfila.setFont(new Font("Arial", Font.PLAIN, 13));
		tpPodaciProfila.setBorder(new EmptyBorder(5, 5, 5, 5));
		tpPodaciProfila.setEditable(false);
		
		spOKorisniku = new JScrollPane();
		
		btnIzadjiIzProfila = new JButton("");
		btnIzadjiIzProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				prikaziPanelSviProfili();
				
			}
		});
		btnIzadjiIzProfila.setIcon(new ImageIcon(ICON_NAZAD));
		btnIzadjiIzProfila.setBackground(new Color(255, 255, 255));
		
		lblOKorisniku = new JLabel("O korisniku:");
		lblOKorisniku.setFont(new Font("Arial", Font.PLAIN, 15));
		GroupLayout gl_panelPrikazProfila = new GroupLayout(panelPrikazProfila);
		gl_panelPrikazProfila.setHorizontalGroup(
			gl_panelPrikazProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazProfila.createSequentialGroup()
					.addGroup(gl_panelPrikazProfila.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazProfila.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnIzadjiIzProfila, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelPrikazProfila.createSequentialGroup()
							.addGap(80)
							.addGroup(gl_panelPrikazProfila.createParallelGroup(Alignment.LEADING)
								.addComponent(spOKorisniku, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
								.addGroup(gl_panelPrikazProfila.createSequentialGroup()
									.addComponent(lblSlikaProfila, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tpPodaciProfila, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
								.addComponent(lblOKorisniku, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
					.addGap(44))
		);
		gl_panelPrikazProfila.setVerticalGroup(
			gl_panelPrikazProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazProfila.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnIzadjiIzProfila, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addGroup(gl_panelPrikazProfila.createParallelGroup(Alignment.LEADING)
						.addComponent(tpPodaciProfila, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSlikaProfila, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(lblOKorisniku, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(spOKorisniku, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		
		tpOKorisniku = new JTextPane();
		tpOKorisniku.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		tpOKorisniku.setEditable(false);
		tpOKorisniku.setBorder(new EmptyBorder(5, 5, 5, 5));
		spOKorisniku.setViewportView(tpOKorisniku);
		panelPrikazProfila.setLayout(gl_panelPrikazProfila);
		
		lblPrikazSvigGrupa = new JLabel("PRIKAZ I PRETRAGA GRUPA");
		lblPrikazSvigGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrikazSvigGrupa.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		
		tfPretragaGrupa = new JTextField();
		tfPretragaGrupa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				dlmSveGrupe.removeAllElements();
				String pretraga=tfPretragaGrupa.getText();
				for (Entry<String, Object> mapa : sveGrupe.entrySet()) {	
					if (mapa.getKey().toLowerCase().contains(pretraga.toLowerCase())) {
						Map<String, Object> podaciGrupe=(Map<String, Object>) mapa.getValue();
						Grupa grupa=(Grupa) podaciGrupe.get("grupa");
						dlmSveGrupe.addElement(grupa);

					}
				}
				
			}
		});
		tfPretragaGrupa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfPretragaGrupa.setText("");
			}
		});
		tfPretragaGrupa.setText("Pretraži grupe...");
		tfPretragaGrupa.setFont(new Font("Arial", Font.PLAIN, 13));
		tfPretragaGrupa.setColumns(10);
		
		lblPrikaziGrupu = new JLabel("Prika\u017Ei grupu");
		lblPrikaziGrupu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(izabranaGrupa!=null) {
					prikaziGrupu(izabranaGrupa);
				}
			}

			
		});
		lblPrikaziGrupu.setIcon(new ImageIcon(ICON_GRUPA));
		lblPrikaziGrupu.setOpaque(true);
		lblPrikaziGrupu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrikaziGrupu.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPrikaziGrupu.setBackground(new Color(230, 230, 250));
		
		spPrikazGrupa = new JScrollPane();
		GroupLayout gl_panelPrikazSvihGrupa = new GroupLayout(panelPrikazSvihGrupa);
		gl_panelPrikazSvihGrupa.setHorizontalGroup(
			gl_panelPrikazSvihGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazSvihGrupa.createSequentialGroup()
					.addGap(120)
					.addGroup(gl_panelPrikazSvihGrupa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrikazSvihGrupa.createSequentialGroup()
							.addComponent(spPrikazGrupa, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
							.addGap(120))
						.addGroup(gl_panelPrikazSvihGrupa.createSequentialGroup()
							.addGroup(gl_panelPrikazSvihGrupa.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrikazSvigGrupa, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
								.addComponent(tfPretragaGrupa, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
							.addGap(121))))
				.addGroup(Alignment.TRAILING, gl_panelPrikazSvihGrupa.createSequentialGroup()
					.addGap(190)
					.addComponent(lblPrikaziGrupu, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(190))
		);
		gl_panelPrikazSvihGrupa.setVerticalGroup(
			gl_panelPrikazSvihGrupa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazSvihGrupa.createSequentialGroup()
					.addGap(30)
					.addComponent(lblPrikazSvigGrupa, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tfPretragaGrupa, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(spPrikazGrupa, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPrikaziGrupu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		listSveGrupe = new JList<Object>(dlmSveGrupe);
		listSveGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				izabranaGrupa=(Grupa) listSveGrupe.getSelectedValue();
				
			}
		});
		listSveGrupe.setFixedCellHeight(50);
		listSveGrupe.setCellRenderer(new PrikazivanjeUListiGrupa());
		spPrikazGrupa.setViewportView(listSveGrupe);
		panelPrikazSvihGrupa.setLayout(gl_panelPrikazSvihGrupa);
		
		JLabel lblPrikazSvihProfila = new JLabel("PRIKAZ I PRETRAGA PROFILA");
		lblPrikazSvihProfila.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblPrikazSvihProfila.setHorizontalAlignment(SwingConstants.CENTER);
		tfPretragaProfila = new JTextField();
		tfPretragaProfila.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				dlmSviProfili.removeAllElements();
				String pretraga=tfPretragaProfila.getText();
				for(Profil prof:sviProfili) {
					if(prof.getStatus()!=ADMIN_STATUS)
						if(prof.getKorisnickoIme().toLowerCase().contains(pretraga.toLowerCase())
										|| prof.getIme().toLowerCase().contains(pretraga.toLowerCase())
										|| prof.getPrezime().toLowerCase().contains(pretraga.toLowerCase())) {
							
							dlmSviProfili.addElement(prof);
						}
				}
		
			}
		});
		tfPretragaProfila.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfPretragaProfila.setText("");
			}
		});
		tfPretragaProfila.setText("Pretraži profile...");
		tfPretragaProfila.setFont(new Font("Arial", Font.PLAIN, 13));
		
		spPrikazProfila = new JScrollPane();
		
		lblPrikaziProfil = new JLabel("Prika\u017Ei profil");
		lblPrikaziProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(izabranProfil!=null) {
					prikaziProfil(izabranProfil);
				}
				
			}

		
		});
		lblPrikaziProfil.setIcon(new ImageIcon(ICON_PROFIL));
		lblPrikaziProfil.setOpaque(true);
		lblPrikaziProfil.setBackground(new Color(230, 230, 250));
		lblPrikaziProfil.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPrikaziProfil.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		GroupLayout gl_panelPrikazSvihProfila = new GroupLayout(panelPrikazSvihProfila);
		gl_panelPrikazSvihProfila.setHorizontalGroup(
			gl_panelPrikazSvihProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazSvihProfila.createSequentialGroup()
					.addGap(120)
					.addGroup(gl_panelPrikazSvihProfila.createParallelGroup(Alignment.LEADING)
						.addComponent(spPrikazProfila, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addComponent(tfPretragaProfila, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addComponent(lblPrikazSvihProfila, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
					.addGap(120))
				.addGroup(gl_panelPrikazSvihProfila.createSequentialGroup()
					.addGap(190)
					.addComponent(lblPrikaziProfil, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(190))
		);
		gl_panelPrikazSvihProfila.setVerticalGroup(
			gl_panelPrikazSvihProfila.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrikazSvihProfila.createSequentialGroup()
					.addGap(30)
					.addComponent(lblPrikazSvihProfila, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tfPretragaProfila, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(spPrikazProfila, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPrikaziProfil, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		listSviProfili = new JList<Object>(dlmSviProfili);
		listSviProfili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				izabranProfil=(Profil) listSviProfili.getSelectedValue();
				
			}
		});
		listSviProfili.setFixedCellHeight(50);
		listSviProfili.setCellRenderer(new PrikazivanjeUListiPrijatelja());
		spPrikazProfila.setViewportView(listSviProfili);
		panelPrikazSvihProfila.setLayout(gl_panelPrikazSvihProfila);
		
		lblLogo = new JLabel("");
		lblLogo.setSize(200,100);
		lblLogo.setIcon(new ImageIcon(LOGO));
		
		lblObavestenje = new JLabel("DOBRODO\u0160LI NA ADMINISTRATORSKU FORMU");
		lblObavestenje.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		lblObavestenje.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblSlikaAdmin = new JLabel("");
		lblSlikaAdmin.setIcon(new ImageIcon(ADMIN_SLIKA));
		GroupLayout gl_panelObavestenje = new GroupLayout(panelObavestenje);
		gl_panelObavestenje.setHorizontalGroup(
			gl_panelObavestenje.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelObavestenje.createSequentialGroup()
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(150)
					.addComponent(lblObavestenje, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
					.addGap(234)
					.addComponent(lblSlikaAdmin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelObavestenje.setVerticalGroup(
			gl_panelObavestenje.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelObavestenje.createSequentialGroup()
					.addGroup(gl_panelObavestenje.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelObavestenje.createSequentialGroup()
							.addGap(34)
							.addComponent(lblObavestenje, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSlikaAdmin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelObavestenje.setLayout(gl_panelObavestenje);
		contentPane.setLayout(gl_contentPane);
		
		
		KontrolerKlijent.vratiObjekat().postaviAdminFormu(this);
		
		Socket s=Komunikacija.vratiInstancu().getKlijentSocket();
		NitObradeKlijent nok = new NitObradeKlijent(s);
		nok.start();
		
		prikaziUListuSvihProfila();
		prikaziUListuSvihGrupa();
		
		prikaziPanelSviProfili();
		prikaziPanelSveGrupe();
		
		
	}

//=====================================KRAJ KONSTRUKTORA===============================================
	
	
//----------------------------Definisanje panela koji prikazuje listu profila---------------------------------
	
	private void prikaziPanelSviProfili() {
		
		panelPrikazSvihProfila.setVisible(true);
		panelPrikazProfila.setVisible(false);
		
		
		izabranProfil=null;
		listSviProfili.setSelectedIndex(-1);
		
		
		
	}

	private void prikaziUListuSvihProfila() {
		dlmSviProfili.clear();
		for(Profil profil:sviProfili) {
			if(profil.getStatus()!=ADMIN_STATUS) {
				dlmSviProfili.addElement(profil);
			}
		}
		
	}
	
	
//----------------------------Definisanje panela koji prikazuje listu grupa---------------------------------
	
	private void prikaziPanelSveGrupe() {
		
		panelPrikazSvihGrupa.setVisible(true);
		panelPrikazGrupa.setVisible(false);
		
		izabranaGrupa=null;
		listSveGrupe.setSelectedIndex(-1);
		
	
		
	}

	private void prikaziUListuSvihGrupa() {
		dlmSveGrupe.clear();
		for(Entry<String, Object> mapa:sveGrupe.entrySet()) {
			Map<String, Object> podaciGrupe=(Map<String, Object>) mapa.getValue();
			Grupa grupa=(Grupa) podaciGrupe.get("grupa");
			dlmSveGrupe.addElement(grupa);
		}
		
		
	}

		
	
//----------------------------Definisanje panela koji prikazuje profil---------------------------------
	
	private void prikaziProfil(Profil izabranProfil) {
		
		panelPrikazProfila.setVisible(true);
		panelPrikazSvihProfila.setVisible(false);
		
		StringBuffer sbPodaci = new StringBuffer("LIČNI PODACI:\n\n");
		sbPodaci.append("Ime i prezime: " + izabranProfil.getIme() + " " + izabranProfil.getPrezime() + "\n\n");
		sbPodaci.append("Zanimanje: " + izabranProfil.getZanimanje() + "\n\n");
		sbPodaci.append("Pol: " + izabranProfil.getPol() + "\n\n");
		sbPodaci.append("Datum rođenja: " + izabranProfil.getDatumRodjenja() + "\n\n");
		sbPodaci.append("Datum registracije: " + izabranProfil.getDatumPrijave() + "\n\n");
		sbPodaci.append("Država: " + izabranProfil.getDrzava() + "\n\n");
		sbPodaci.append("Grad stanovanja: " + izabranProfil.getGradStanovanja() + "\n\n");
		tpPodaciProfila.setText(sbPodaci.toString());
		tpOKorisniku.setText(izabranProfil.getoMeni());
		Image slikaProfila = null;
		try {
			Image slika = ImageIO.read(new File(izabranProfil.getSlikaProfila()));
			slikaProfila=slika.getScaledInstance(lblSlikaProfila.getWidth(), lblSlikaProfila.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblSlikaProfila.setIcon(new ImageIcon(slikaProfila));
		
		
	}
	
//----------------------------Definisanje panela koji prikazuje grupu---------------------------------	
	
	
	private void prikaziGrupu(Grupa izabranaGrupa) {
		
		panelPrikazGrupa.setVisible(true);
		panelPrikazSvihGrupa.setVisible(false);
		
		StringBuffer sbPodaci = new StringBuffer("PODACI GRUPE:\n\n");
		sbPodaci.append("Naziv grupe: " + izabranaGrupa.getNazivGrupe() +"\n\n");
		sbPodaci.append("Kreator grupe: " + izabranaGrupa.getKreatorGrupe().getKorisnickoIme() + "\n\n");
		sbPodaci.append("Opis grupe: \n");
		sbPodaci.append(izabranaGrupa.getOpisGrupe());
		tpPodaciGrupe.setText(sbPodaci.toString());
		Image slikaGrupe = null;
		try {
			Image slika = ImageIO.read(new File(izabranaGrupa.getSlikaGrupe()));
			slikaGrupe=slika.getScaledInstance(lblSlikaGrupe.getWidth(), lblSlikaGrupe.getHeight(), Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblSlikaGrupe.setIcon(new ImageIcon(slikaGrupe));
		
		Map<String, Object> podaciGrupe=(Map<String, Object>) sveGrupe.get(izabranaGrupa.getNazivGrupe());
		List<ClanGrupe> clanoviGrupe=(List<ClanGrupe>) podaciGrupe.get("clanoviGrupe");
		
		postaviClanoveGrupeUListuClanova(clanoviGrupe);
		
	}

	private void postaviClanoveGrupeUListuClanova(List<ClanGrupe> clanoviGrupe) {
		dlmClanoviGrupe.clear();
		for (ClanGrupe cg : clanoviGrupe) {
			if (cg.getStatusClana() == CLAN_GRUPE) {
				dlmClanoviGrupe.addElement(cg.getClan());

			}
		}
		
	}
	
	
//----------------------------Metode kojima upravljaju desavanja od drugih korisnika---------------------------------	

	public void postaviListuSvihProfila(List<Profil> listaSvihProfila) {
		
		this.sviProfili=listaSvihProfila;
		if(panelPrikazProfila.isVisible()) {
			prikaziPanelSviProfili();
		}
		
	}

	public void dodajNovuGrupuUListuSvih(Grupa grupa) {

		if (sveGrupe != null) {
			dodajNovuGrupu(grupa);

		} else {
			sveGrupe = new HashMap<>();
			dodajNovuGrupu(grupa);
		}
		if(panelPrikazGrupa.isVisible()) {
			prikaziPanelSveGrupe();
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
	
	public void postaviSveGrupe(Map<String, Object> sveGrupe) {
		this.sveGrupe = sveGrupe;

	}

	
}
