package com.comtrade.forma;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerServer.KontrolerServer;
import com.comtrade.niti.NitServer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class ServerForma extends JFrame implements Konstante {

	private JPanel contentPane;
	private JTextArea taKorisnici;
	private JScrollPane scrollPane;
	private JButton btnPokreni;
	JLabel lblPrikazVremena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerForma frame = new ServerForma();
					frame.setVisible(true);
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 600, 400);
		setTitle("Server-miniViber");
		contentPane = new JPanel();
		setIconImage(new ImageIcon(ICON_LOGO).getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel pozadina=null;
		try {
			pozadina = new JLabel( new ImageIcon(ImageIO.read(new File(POZADINA_SERVER))));
			pozadina.setLocation(0, 0);
			pozadina.setSize(600, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.add(pozadina);
		
		btnPokreni = new JButton("POKRENI SERVER");
		btnPokreni.setBackground(new Color(230, 230, 250));
		btnPokreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NitServer ns=new NitServer();
				ns.start();
				btnPokreni.setEnabled(false);
			}
		});
		btnPokreni.setBounds(170, 57, 252, 23);
		pozadina.add(btnPokreni);
		
		lblPrikazVremena = new JLabel("");
		lblPrikazVremena.setBounds(223, 11, 116, 23);
		pozadina.add(lblPrikazVremena);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 107, 415, 199);
		pozadina.add(scrollPane);
		
		taKorisnici = new JTextArea();
		taKorisnici.setBackground(new Color(245, 245, 245));
		taKorisnici.setFont(new Font("Arial", Font.BOLD, 13));
		scrollPane.setViewportView(taKorisnici);
		
		KontrolerServer.vratiObjekat().postaviFormu(this);
	}

	public void prikaziObavestenje(String poruka) {
		
		taKorisnici.append(poruka);
		
	}
}
