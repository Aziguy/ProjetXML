package vn.edu.vnu.ifi.xml.presentation.vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.vnu.ifi.xml.dao.Dao;
import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.presentation.Login;
import vn.edu.vnu.ifi.xml.service.IService;
import vn.edu.vnu.ifi.xml.service.Service;

public class Accueil extends JFrame {

	private JPanel contentPane;
	private IDao dao;
	private IService service;
	public JLabel laDate;
	public JLabel lHeure;
	public JLabel afficherMsg;
	public static AccueilBank accueilBank;
	public static GestionStudents gestClients;
	public static GestionComptes gestComptes;
	public static TransactionsBancaire operaBank;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Accueil() {

		dao = new Dao();
		service = new Service(dao);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Accueil.class.getResource("/img/icons8_Computer_256px.png")));
		setTitle("Syst\u00E8me de gestion des \u00E9tudiants de la promotion 22");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 630);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmNouveau = new JMenuItem("Nouveau");
		mnFichier.add(mntmNouveau);

		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir");
		mnFichier.add(mntmOuvrir);

		JMenu mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);

		JMenuItem mntmCopier = new JMenuItem("Copier");
		mnEdition.add(mntmCopier);

		JMenuItem mntmCouper = new JMenuItem("Couper");
		mnEdition.add(mntmCouper);

		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(36, 47, 65));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		afficherMsg = new JLabel();
		afficherMsg.setText("Bonjour null");
		afficherMsg.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		afficherMsg.setBounds(10, -1, 374, 20);
		contentPane.add(afficherMsg);

		// Création d'un objet de type Login pour appeler la méthode recupererLogin
		Login log = new Login();
		// Appel de la méthode qui affiche le nom d'utilisateur
		afficherMsg.setText("Bonjour " + service.afficherMessageService(log.recupererLogin()));

		laDate = new JLabel();
		laDate.setText("222");
		laDate.setForeground(Color.WHITE);
		laDate.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		laDate.setBounds(10, 20, 416, 20);
		contentPane.add(laDate);

		lHeure = new JLabel();
		lHeure.setText("222");
		lHeure.setForeground(Color.WHITE);
		lHeure.setFont(new Font("DS-Digital", Font.BOLD, 18));
		lHeure.setBounds(823, 20, 251, 20);
		contentPane.add(lHeure);

		// Appel de la méthode currentDateHeure();
		currentHeureDate();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Accueil.class.getResource("/img/menu.png")));
		lblNewLabel.setBounds(0, 0, 1100, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Accueil.class.getResource("/img/Mbleu.png")));
		lblNewLabel_1.setBounds(0, 20, 1100, 20);
		contentPane.add(lblNewLabel_1);

		// Création de ma surface d'accueil des JInternalFrame
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(143, 51, 931, 505);
		contentPane.add(desktopPane);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				desktopPane.repaint();

				gestClients = new GestionStudents();

				desktopPane.add(gestClients);
				try {
					gestClients.setMaximum(true);
				} catch (PropertyVetoException ex) {
					Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
				}
				gestClients.show();
			}
		});
		button_1.setIcon(new ImageIcon(Accueil.class.getResource("/img/icons8_User_Group_Man_Woman_90px.png")));
		button_1.setBounds(10, 180, 123, 87);
		contentPane.add(button_1);

		JLabel lblGestionsDesClients = new JLabel("Gestions des Etudiants");
		lblGestionsDesClients.setForeground(Color.WHITE);
		lblGestionsDesClients.setFont(new Font("Roboto", Font.BOLD, 12));
		lblGestionsDesClients.setBounds(10, 278, 123, 20);
		contentPane.add(lblGestionsDesClients);

		JLabel lblGestionsDesAgences = new JLabel("Accueil Promo22XML");
		lblGestionsDesAgences.setForeground(Color.WHITE);
		lblGestionsDesAgences.setFont(new Font("Roboto", Font.BOLD, 12));
		lblGestionsDesAgences.setBounds(12, 149, 119, 20);
		contentPane.add(lblGestionsDesAgences);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.repaint();

				gestComptes = new GestionComptes();

				desktopPane.add(gestComptes);
				try {
					gestComptes.setMaximum(true);
				} catch (PropertyVetoException ex) {
					Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
				}
				gestComptes.show();
			}
		});
		button_2.setIcon(new ImageIcon(Accueil.class.getResource("/img/icons8_Withdrawal_Limit_90px.png")));
		button_2.setBounds(10, 309, 123, 87);
		contentPane.add(button_2);

		JLabel lblGestionsDesComptes = new JLabel("Gestions des Mati\u00E8res");
		lblGestionsDesComptes.setForeground(Color.WHITE);
		lblGestionsDesComptes.setFont(new Font("Roboto", Font.BOLD, 12));
		lblGestionsDesComptes.setBounds(10, 407, 123, 20);
		contentPane.add(lblGestionsDesComptes);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				desktopPane.repaint();

				operaBank = new TransactionsBancaire();

				desktopPane.add(operaBank );
				try {
					operaBank.setMaximum(true);
				} catch (PropertyVetoException ex) {
					Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
				}
				operaBank .show();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Accueil.class.getResource("/img/icons8_Transaction_90px.png")));
		btnNewButton.setBounds(10, 438, 123, 87);
		contentPane.add(btnNewButton);

		JLabel lblOprations = new JLabel("Gestion des Relev\u00E9s");
		lblOprations.setForeground(Color.WHITE);
		lblOprations.setFont(new Font("Roboto", Font.BOLD, 12));
		lblOprations.setBounds(10, 536, 123, 20);
		contentPane.add(lblOprations);

		// JDesktopPane desktopPane = new JDesktopPane();
		// desktopPane.setBounds(143, 51, 931, 505);
		// contentPane.add(desktopPane);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktopPane.removeAll();
				desktopPane.repaint();

				accueilBank = new AccueilBank();

				desktopPane.add(accueilBank);
				try {
					accueilBank.setMaximum(true);
				} catch (PropertyVetoException ex) {
					Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
				}
				accueilBank.show();
			}
		});
		button.setIcon(new ImageIcon(Accueil.class.getResource("/img/icons8_Merchant_Account_90px.png")));
		button.setBounds(10, 51, 123, 87);
		contentPane.add(button);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Accueil.class.getResource("/img/imgAccueil.png")));
		label.setBounds(0, 0, 931, 505);
		desktopPane.add(label);
	}

	/**
	 * Cette fonction permet d'afficher l'heure et la date Nous utilisons un thread
	 * pour que l'heure soit dynamique (changement en direct)...
	 */
	public void currentHeureDate() {

		Thread clock = new Thread() {

			public void run() {

				// Date aujourdhui = new Date();
				// DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,
				// DateFormat.FULL);
				// System.out.println("nouvelle date");
				// System.out.println(fullDateFormat.format(aujourdhui));

				for (;;) {
					Calendar cal = new GregorianCalendar();
					int secondes = cal.get(Calendar.SECOND);
					int minutes = cal.get(Calendar.MINUTE);
					int heure = cal.get(Calendar.HOUR);
					int AM_PM = cal.get(Calendar.AM_PM);
					String pa;

					if (AM_PM == 1) {
						pa = "PM";
					} else {
						pa = "AM";
					}

					int mois = cal.get(Calendar.MONTH);
					int annee = cal.get(Calendar.YEAR);
					int jour = cal.get(Calendar.DAY_OF_MONTH);
					// Afichage
					lHeure.setText("Horloge : " + heure + ":" + (minutes) + ":" + secondes + " " + pa);
					laDate.setText("Date du jour : " + jour + "/" + (mois + 1) + "/" + annee);

					try {
						sleep(1000);
					} catch (InterruptedException iex) {
						Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, iex);
					}
				}
			}
		};

		clock.start();

	}
}
