package vn.edu.vnu.ifi.xml.presentation.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.toedter.calendar.JDateChooser;

import vn.edu.vnu.ifi.xml.dao.Dao;
import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.domaine.Clients;
import vn.edu.vnu.ifi.xml.domaine.Comptes;
import vn.edu.vnu.ifi.xml.domaine.ComptesModel;
import vn.edu.vnu.ifi.xml.domaine.Gestionnaires;
import vn.edu.vnu.ifi.xml.domaine.TypeComptes;
import vn.edu.vnu.ifi.xml.service.IService;
import vn.edu.vnu.ifi.xml.service.Service;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionComptes extends JInternalFrame {

	private IService service;
	private IDao dao;
	private JTable jTableComptes;
	private JPanel panel;
	private int row;
	private Comptes compteSelect = null;
	private String recher;
	private JComboBox selectClients;
	private JComboBox selectCompte;
	private JComboBox selectGestionnaire;
	private JComboBox selectTypeCpte;
	private JTextField textRechercher;
	private JTextField textFieldNumero;
	private JTextField textFieldSolde;
	private JLabel labelNumero;
	private JLabel labelIdentifiant;
	private JLabel labelSolde;
	private JLabel labelClient;
	private JLabel labelGestionnaire;
	private JLabel labelTypeCpte;
	private List<Comptes> maListeComptes;
	private JTextField textFieldIdent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionComptes frame = new GestionComptes();
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
	public GestionComptes() {

		// Initialisation des variables serviceComptes et daoComptes
		dao = new Dao();
		service = new Service(dao);
		dao= new Dao();
		service = new Service(dao);

		setBounds(100, 100, 931, 505);
		setBorder(null);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste des comptes Proxi22Bank", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 452, 292);
		getContentPane().add(panel);

		labelNumero = new JLabel("");
		labelNumero.setForeground(Color.WHITE);
		labelNumero.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelNumero.setBounds(628, 56, 254, 21);
		getContentPane().add(labelNumero);

		JLabel lblNumeroCpte = new JLabel("N\u00B0 du compte : ");
		lblNumeroCpte.setForeground(Color.WHITE);
		lblNumeroCpte.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNumeroCpte.setBounds(488, 56, 130, 21);
		getContentPane().add(lblNumeroCpte);

		JLabel lblIdentifiant = new JLabel("ID du compte  : ");
		lblIdentifiant.setForeground(Color.WHITE);
		lblIdentifiant.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblIdentifiant.setBounds(488, 88, 130, 21);
		getContentPane().add(lblIdentifiant);

		labelIdentifiant = new JLabel("");
		labelIdentifiant.setForeground(Color.WHITE);
		labelIdentifiant.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelIdentifiant.setBounds(628, 88, 254, 21);
		getContentPane().add(labelIdentifiant);

		JLabel lblSolde = new JLabel("Solde du compte : ");
		lblSolde.setForeground(Color.WHITE);
		lblSolde.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblSolde.setBounds(488, 120, 130, 21);
		getContentPane().add(lblSolde);

		labelSolde = new JLabel("");
		labelSolde.setForeground(Color.WHITE);
		labelSolde.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelSolde.setBounds(628, 120, 254, 21);
		getContentPane().add(labelSolde);

		JLabel lblClient = new JLabel("Client : ");
		lblClient.setForeground(Color.WHITE);
		lblClient.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblClient.setBounds(488, 152, 130, 21);
		getContentPane().add(lblClient);

		JLabel lblGestionnaire = new JLabel("Gestionnaire : ");
		lblGestionnaire.setForeground(Color.WHITE);
		lblGestionnaire.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblGestionnaire.setBounds(488, 184, 130, 21);
		getContentPane().add(lblGestionnaire);

		labelGestionnaire = new JLabel("");
		labelGestionnaire.setForeground(Color.WHITE);
		labelGestionnaire.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelGestionnaire.setBounds(628, 184, 254, 21);
		getContentPane().add(labelGestionnaire);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(628, 235, 254, 2);
		getContentPane().add(separator_5);
		
		labelTypeCpte = new JLabel("");
		labelTypeCpte.setForeground(Color.WHITE);
		labelTypeCpte.setFont(new Font("Roboto Lt", Font.BOLD | Font.ITALIC, 12));
		labelTypeCpte.setBounds(628, 216, 254, 21);
		getContentPane().add(labelTypeCpte);
		
		JLabel labelTypeCpteT = new JLabel("Type de Compte : ");
		labelTypeCpteT.setForeground(Color.WHITE);
		labelTypeCpteT.setFont(new Font("Roboto", Font.PLAIN, 14));
		labelTypeCpteT.setBounds(488, 216, 130, 21);
		getContentPane().add(labelTypeCpteT);

		labelClient = new JLabel("");
		labelClient.setForeground(Color.WHITE);
		labelClient.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelClient.setBounds(628, 152, 254, 21);
		getContentPane().add(labelClient);

		JLabel lblInformationsDtaillesDu = new JLabel("INFORMATIONS DETAILLEES DU COMPTE ");
		lblInformationsDtaillesDu.setForeground(Color.WHITE);
		lblInformationsDtaillesDu.setFont(new Font("Roboto Bk", Font.BOLD | Font.ITALIC, 14));
		lblInformationsDtaillesDu.setBounds(557, 7, 284, 21);
		getContentPane().add(lblInformationsDtaillesDu);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(628, 75, 254, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(628, 106, 254, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(628, 139, 254, 2);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(628, 171, 254, 2);
		getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(628, 203, 254, 2);
		getContentPane().add(separator_4);

		JButton btnAddCompte = new JButton("Ajouter");
		btnAddCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// code executé après le clic sur "Ajouter"

				String verifierSolde = textFieldSolde.getText();
				
				if (verifierSolde.isEmpty() || selectClients.getSelectedIndex() == 0 || selectGestionnaire.getSelectedIndex() == 0 || selectTypeCpte.getSelectedIndex() == 0) {
					
					
					JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					// 3. Création de services et utilisation du contructeur générique. Elle prend
					// en paramètre un IDaoClients
					dao = new Dao();
					service = new Service(dao);

					// 1. Récupération des valeur saisies par l'utilisateur

					String soldeCpte = textFieldSolde.getText();
					Clients clientSelect = ((Clients) selectClients.getSelectedItem());
					String idClientSelect = clientSelect.getIdClients();
					Gestionnaires gestionnaireSelect = ((Gestionnaires) selectGestionnaire.getSelectedItem());
					String idGestionnaireSelect = gestionnaireSelect.getIdGestionnaires();
					TypeComptes typeCompteSelect = ((TypeComptes) selectTypeCpte.getSelectedItem());
					String idTypeCompteSelect = typeCompteSelect.getIdTypeComptes();
					
					// 2. Création d'un objet Comptes puis, maj des attributs de l'objet compte

					Comptes compte = new Comptes();
					
					compte.setNumComptes(service.genererNumeroCompte());
					compte.setNumIdent(service.genererNumeroCompte());
					compte.setSolde(Float.parseFloat(soldeCpte));
					compte.setClients_idClients(idClientSelect);
					compte.setGestionnaires_idGestionnaires(idGestionnaireSelect);
					compte.setTypesComptes_idTypesComptes(idTypeCompteSelect);

					// 4. On donne notre objet compte à la méthode enregistrerComptesService de la
					// couche serviceComptes

					// La variable verifier permet de contenir le résultat de l'instruction
					// d'enregistrement

					int verifier = service.enregistrerComptesService(compte);
					if (verifier == 1) {
						JOptionPane.showMessageDialog(null,
								"le compte N° " + compte.getNumComptes() + " du " + compte.getNumIdent()
										+ " a bien été enregistré dans la Base de données",
								"Information!", JOptionPane.INFORMATION_MESSAGE);

						viderChamps();

					} else {
						JOptionPane.showMessageDialog(null,
								"Erreur lors de l'enregistrement du compte N° " + compte.getNumComptes() + " du "
										+ compte.getNumIdent() + " dans la Base de données",
								"Alerte!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddCompte.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/Add_User_Group_Woman_Man_32px.png")));
		btnAddCompte.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnAddCompte.setBounds(10, 442, 122, 37);
		getContentPane().add(btnAddCompte);

		JButton btnEditCompte = new JButton("Modifier");
		btnEditCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (compteSelect != null) {
					// code executé après le clic sur "Modifier"
					
					// 3. Création de services et utilisation du contructeur générique. Elle prend
					// en paramètre un IDao
					dao = new Dao();
					service = new Service(dao);

					// 1. Récupération des valeur saisies par l'utilisateur

					String soldeCpte = textFieldSolde.getText();
					Clients clientSelect = ((Clients) selectClients.getSelectedItem());
					String idClientSelect = clientSelect.getIdClients();
					Gestionnaires gestionnaireSelect = ((Gestionnaires) selectGestionnaire.getSelectedItem());
					String idGestionnaireSelect = gestionnaireSelect.getIdGestionnaires();
					TypeComptes typeCompteSelect = ((TypeComptes) selectTypeCpte.getSelectedItem());
					String idTypeCompteSelect = typeCompteSelect.getIdTypeComptes();

					// 2. Création d'un objet compte puis, maj des attributs de l'objet compte

					Comptes compte = new Comptes();
					
					compte.setNumComptes(service.genererNumeroCompte());
					compte.setNumIdent(service.genererNumeroCompte());
					compte.setSolde(Float.parseFloat(soldeCpte));
					compte.setClients_idClients(idClientSelect);
					compte.setGestionnaires_idGestionnaires(idGestionnaireSelect);
					compte.setTypesComptes_idTypesComptes(idTypeCompteSelect);

					// 3. On donne notre objet compte à la méthode modifierComptesServices() de la couche
					// service

					int verifier = service.modifierComptesServices(compte);

					if (verifier == 1) {
						JOptionPane.showMessageDialog(null,
								"Le compte n° " + compte.getNumComptes() + " du " + compte.getNumIdent()
										+ " a bien été modifié dans la Base de données",
								"Information!", JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Erreur lors de la modification du compte n° "
								+ compte.getNumComptes() + " du " + compte.getNumIdent() + " dans la Base de donnees");
					}

				} else {
					JOptionPane.showMessageDialog(null, "bien vouloir sélectionner un compte de la liste", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEditCompte.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/icons8_Edit_Property_32px.png")));
		btnEditCompte.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnEditCompte.setBounds(142, 442, 122, 37);
		getContentPane().add(btnEditCompte);

		JButton btnDelCompte = new JButton("Supprimer");
		btnDelCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// code executé après le clic sur "Supprimer"

				if (JOptionPane.showConfirmDialog(null, "Souhaitez-vous vraiment supprimer ce compte?",
						"Supprimer compte", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					if (textFieldNumero.getText().length() != 0) {

						// 3. Création de services et utilisation du contructeur générique. Elle prend
						// en paramètre un IDao
						dao = new Dao();
						service = new Service(dao);

						// 1. Récupération de la valeur sélectionner par l'utilisateur

						String soldeCpte = textFieldSolde.getText();
						Clients clientSelect = ((Clients) selectClients.getSelectedItem());
						String idClientSelect = clientSelect.getIdClients();
						Gestionnaires gestionnaireSelect = ((Gestionnaires) selectGestionnaire.getSelectedItem());
						String idGestionnaireSelect = gestionnaireSelect.getIdGestionnaires();
						TypeComptes typeCompteSelect = ((TypeComptes) selectTypeCpte.getSelectedItem());
						String idTypeCompteSelect = typeCompteSelect.getIdTypeComptes();

						// 2. Création d'un objet compte puis, maj des attributs de l'objet compte

						Comptes compte = new Comptes();
						
						compte.setNumComptes(Integer.parseInt(textFieldNumero.getText()));
						compte.setNumIdent(Integer.parseInt(textFieldIdent.getText()));
						compte.setSolde(Float.parseFloat(soldeCpte));
						compte.setClients_idClients(idClientSelect);
						compte.setGestionnaires_idGestionnaires(idGestionnaireSelect);
						compte.setTypesComptes_idTypesComptes(idTypeCompteSelect);

						// 4. On donne notre objet compte à la méthode supprimerComptesService() de la
						// couche service

						int verifier = service.supprimerComptesService(compte);

						if (verifier == 1) {
							JOptionPane.showMessageDialog(null,
									"Le compte " + compte.getNumComptes() + " du " + compte.getNumIdent()
											+ " a bien été supprimé dans la Base de données",
									"Information!", JOptionPane.INFORMATION_MESSAGE);

							viderChamps();

						} else {
							JOptionPane.showMessageDialog(null,
									"Erreur lors de la suppression du compte " + compte.getNumComptes() + " du "
											+ compte.getNumIdent() + " dans la Base de donnees",
									"Alerte!", JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "veuillez choisir  un compte!", "Attention!",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnDelCompte.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/icons8_Trash_Can_32px.png")));
		btnDelCompte.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnDelCompte.setBounds(274, 442, 134, 37);
		getContentPane().add(btnDelCompte);

		JButton btnPrintCompte = new JButton("Imprimer");
		btnPrintCompte.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/icons8_Print_32px.png")));
		btnPrintCompte.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnPrintCompte.setBounds(418, 442, 129, 37);
		getContentPane().add(btnPrintCompte);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recher = textRechercher.getText();
				if (recher.isEmpty()) {
					JOptionPane.showMessageDialog(null, "veuillez entrer un numéro de compte!!!", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					affichageComptesTrouver();
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/icons8_Search_32px.png")));
		btnSearch.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnSearch.setBounds(689, 442, 129, 37);
		getContentPane().add(btnSearch);

		textRechercher = new JTextField();
		textRechercher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textRechercher.setText("");
			}
		});
		textRechercher.setToolTipText("Recherchez un compte");
		textRechercher.setText("Saisir le num\u00E9ro du compte");
		textRechercher.setFont(new Font("Roboto", Font.PLAIN, 12));
		textRechercher.setColumns(10);
		textRechercher.setBounds(557, 442, 122, 37);
		getContentPane().add(textRechercher);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire d'enregistrement et modification d'un compte", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 309, 808, 130);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNumCpte = new JLabel("Num\u00E9ro du compte :");
		lblNumCpte.setBounds(10, 21, 151, 24);
		panel_1.add(lblNumCpte);
		lblNumCpte.setForeground(Color.BLACK);
		lblNumCpte.setFont(new Font("Roboto Light", Font.BOLD, 13));

		JLabel lblSoldeCptes = new JLabel("Solde de base :");
		lblSoldeCptes.setBounds(10, 91, 151, 28);
		panel_1.add(lblSoldeCptes);
		lblSoldeCptes.setForeground(Color.BLACK);
		lblSoldeCptes.setFont(new Font("Roboto Light", Font.BOLD, 13));

		textFieldNumero = new JTextField();
		textFieldNumero.setEditable(false);
		textFieldNumero.setFont(new Font("Roboto Condensed Light", Font.BOLD, 11));
		textFieldNumero.setBounds(171, 24, 185, 20);
		panel_1.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		JLabel lblIdDuCompte = new JLabel("ID du compte :");
		lblIdDuCompte.setForeground(Color.BLACK);
		lblIdDuCompte.setFont(new Font("Roboto Lt", Font.BOLD, 13));
		lblIdDuCompte.setBounds(10, 56, 151, 24);
		panel_1.add(lblIdDuCompte);
		
		textFieldIdent = new JTextField();
		textFieldIdent.setText("Sera g\u00E9n\u00E9r\u00E9 par le syst\u00E8me");
		textFieldIdent.setFont(new Font("Dialog", Font.BOLD, 11));
		textFieldIdent.setEditable(false);
		textFieldIdent.setColumns(10);
		textFieldIdent.setBounds(171, 59, 185, 20);
		panel_1.add(textFieldIdent);

		textFieldSolde = new JTextField();
		textFieldSolde.addKeyListener(new KeyAdapter() {
			/** 
			 * Contraint l'utilisateur à entrer uniquement des chiffres
			 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar)) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)) {

					e.consume();
				}
			}
		});
		textFieldSolde.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldSolde.setColumns(10);
		textFieldSolde.setBounds(171, 96, 185, 20);
		panel_1.add(textFieldSolde);

		JLabel lblClientsCpte = new JLabel("Client du compte :");
		lblClientsCpte.setForeground(Color.BLACK);
		lblClientsCpte.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblClientsCpte.setBounds(366, 21, 151, 24);
		panel_1.add(lblClientsCpte);

		JLabel lblSelectGest = new JLabel("Gestionnaire :");
		lblSelectGest.setForeground(Color.BLACK);
		lblSelectGest.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblSelectGest.setBounds(366, 56, 151, 28);
		panel_1.add(lblSelectGest);
		
		JLabel labelTypeComptes = new JLabel("Type de compte :");
		labelTypeComptes.setForeground(Color.BLACK);
		labelTypeComptes.setFont(new Font("Roboto Lt", Font.BOLD, 13));
		labelTypeComptes.setBounds(366, 91, 151, 28);
		panel_1.add(labelTypeComptes);
		
		selectTypeCpte = new JComboBox();
		selectTypeCpte.setFont(new Font("Dialog", Font.BOLD, 13));
		selectTypeCpte.setBounds(523, 96, 160, 20);
		panel_1.add(selectTypeCpte);
		List<TypeComptes> maListeTypeCpte = new ArrayList<TypeComptes>();
		maListeTypeCpte = service.getAllTypeComptesService();
		for (TypeComptes typeCpte : maListeTypeCpte) {
			selectTypeCpte.addItem(typeCpte);
		}

		selectClients = new JComboBox();
		selectClients.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		selectClients.setBounds(523, 23, 160, 20);
		panel_1.add(selectClients);
		List<Clients> maListeClients = new ArrayList<Clients>();
		maListeClients = service.getAllClientsService();
		for (Clients clients : maListeClients) {
			selectClients.addItem(clients);
		}
		
		selectGestionnaire = new JComboBox();
		selectGestionnaire.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		selectGestionnaire.setBounds(523, 60, 160, 20);
		panel_1.add(selectGestionnaire);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int verifierMaj = 2, verifierMaj2 = 2;
				List<Comptes> maListeCompteInteret = new ArrayList<Comptes>(service.getAllComptesService());
			for (Comptes comptesInt : maListeCompteInteret) {

				if (comptesInt.getTypesComptes_idTypesComptes().equals("Epargne") ) {
					float newSolde = (float) (comptesInt.getSolde() + (comptesInt.getSolde()*0.05));
					verifierMaj = service.calculInteretComptesService(comptesInt.getNumComptes(), newSolde);
					//System.out.println("Le nouveau solde du compte : "+ comptesInt.getNumComptes() + " est : " + newSolde);
				}else {
					float newSolde = (float) (comptesInt.getSolde() + (comptesInt.getSolde()*0.10));
					verifierMaj2 = service.calculInteretComptesService(comptesInt.getNumComptes(), newSolde);
					
					//System.out.println("Le nouveau solde du compte : "+ comptesInt.getNumComptes() + " est : " + newSolde);
				}
			}
			
			if (verifierMaj == 1 && verifierMaj2 == 1) {
				JOptionPane.showMessageDialog(null, "Tous les comptes ont été mis à jour.", "Information!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			}
		});
		button.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/icons8_Withdrawal_Limit_90px.png")));
		button.setBounds(693, 11, 113, 80);
		panel_1.add(button);
		
		JLabel lblMajIntrts = new JLabel("MAJ des int\u00E9r\u00EAts");
		lblMajIntrts.setForeground(Color.BLACK);
		lblMajIntrts.setFont(new Font("Roboto", Font.BOLD, 13));
		lblMajIntrts.setBounds(693, 95, 113, 20);
		panel_1.add(lblMajIntrts);
		List<Gestionnaires> maListeGestionnaires = new ArrayList<Gestionnaires>();
		maListeGestionnaires = service.getAllGestionnairesService();
		for (Gestionnaires gestionnaire : maListeGestionnaires) {
			selectGestionnaire.addItem(gestionnaire);
		}

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/Contrat.png")));
		lblNewLabel.setBounds(823, 308, 108, 171);
		getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		label.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/fondInternalFrame.jpg")));
		label.setBounds(0, 0, 931, 505);
		getContentPane().add(label);
		setBounds(100, 100, 931, 505);

		// Appelle de la méthode pour afficher la liste des clients
		affichage();

		// Appelle de la méthode pour supprimer la barre de titre
		remove_title_bar();

		// Appelle de la méthode pour vider les champs du formulaire
		viderChamps();

	}

	/**
	 * Cette méthode permet d'afficher la liste des comptes
	 */
	public void affichage() {
		// Appel de la méthode getAllComptesService() que je place dans mon model
		ComptesModel comptesModel = new ComptesModel(service.getAllComptesService());
		jTableComptes = new JTable(comptesModel);
		jTableComptes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				textFieldSolde.setEditable(false);
				getContenuTab();
				remplirFormulaire(compteSelect);
			}
		});
		JScrollPane scrollPane = new JScrollPane(jTableComptes);
		scrollPane.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		panel.add(scrollPane, BorderLayout.CENTER);
		jTableComptes.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableComptes.setBorder(null);
	}

	/**
	 * Cette méthode permet d'afficher dans le jTable la liste des comptes trouvés
	 * en base de données
	 */
	public void affichageComptesTrouver() {
		ComptesModel comptesModel = new ComptesModel(service.rechercherComptesService(recher));
		jTableComptes.setModel(comptesModel);
		panel.add(new JScrollPane(jTableComptes), BorderLayout.CENTER);
		jTableComptes.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableComptes.setBorder(null);
	}

	/**
	 * Cette méthode permet d'effacer les champs du formulaire d'enregistrement du
	 * client
	 */
	public void viderChamps() {
		textFieldNumero.setText("Sera g\u00E9n\u00E9r\u00E9 par le syst\u00E8me");
		textFieldIdent.setText("Sera g\u00E9n\u00E9r\u00E9 par le syst\u00E8me");
		textFieldSolde.setText("");
		// selectCons.removeAllItems();
	}

	/**
	 * Cette méthode permet de supprimer la barre de titre sur mon InternalFrame
	 * 
	 */
	public void remove_title_bar() {
		putClientProperty("GestionComptes.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
	}

	/**
	 * Cette méthode permet de récupérer les données d'un champ de tableau
	 * sélectionné
	 */
	public void getContenuTab() {

		row = jTableComptes.getSelectedRow();
		labelNumero.setText(jTableComptes.getModel().getValueAt(row, 0).toString());
		labelIdentifiant.setText(jTableComptes.getModel().getValueAt(row, 1).toString());
		labelSolde.setText(jTableComptes.getModel().getValueAt(row, 2).toString());
		labelClient.setText(jTableComptes.getModel().getValueAt(row, 3).toString());
		labelGestionnaire.setText(jTableComptes.getModel().getValueAt(row, 4).toString());
		labelTypeCpte.setText(jTableComptes.getModel().getValueAt(row, 5).toString());

		compteSelect = new Comptes();
		compteSelect.setNumComptes(Integer.parseInt(jTableComptes.getModel().getValueAt(row, 0).toString()));
		compteSelect.setNumIdent(Integer.parseInt(jTableComptes.getModel().getValueAt(row, 1).toString()));
		compteSelect.setSolde(Float.parseFloat(jTableComptes.getModel().getValueAt(row, 2).toString()));
		compteSelect.setClients_idClients(jTableComptes.getModel().getValueAt(row, 3).toString());
		compteSelect.setGestionnaires_idGestionnaires(jTableComptes.getModel().getValueAt(row, 4).toString());
		compteSelect.setTypesComptes_idTypesComptes(jTableComptes.getModel().getValueAt(row, 5).toString());

	}

	/**
	 * Cette méthode permet de remplir les champs du formulaire par les information
	 * du client sélectionné
	 */
	public void remplirFormulaire(Comptes compteSelect) {

		textFieldNumero.setText(String.valueOf(compteSelect.getNumComptes()));
		textFieldIdent.setText(String.valueOf(compteSelect.getNumIdent()));
		textFieldSolde.setText(String.valueOf(compteSelect.getSolde()));
		selectClients.setSelectedItem(compteSelect.getClients_idClients());
		selectGestionnaire.setSelectedItem(compteSelect.getGestionnaires_idGestionnaires());
		selectTypeCpte.setSelectedItem(compteSelect.getTypesComptes_idTypesComptes());

	}
}
