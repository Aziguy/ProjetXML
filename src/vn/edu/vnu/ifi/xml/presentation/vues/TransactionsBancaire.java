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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import vn.edu.vnu.ifi.xml.dao.Dao;
import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.domaine.Comptes;
import vn.edu.vnu.ifi.xml.domaine.ComptesModel;
import vn.edu.vnu.ifi.xml.domaine.Gestionnaires;
import vn.edu.vnu.ifi.xml.domaine.Transactions;
import vn.edu.vnu.ifi.xml.domaine.TransactionsModel;
import vn.edu.vnu.ifi.xml.domaine.TypeComptes;
import vn.edu.vnu.ifi.xml.service.IService;
import vn.edu.vnu.ifi.xml.service.Service;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class TransactionsBancaire extends JInternalFrame {

	private IService service;
	private IDao dao;
	private JTable jTableComptes;
	private JTable jTableTransactions;
	private JPanel panel;
	private JPanel panel_2;
	private int row;
	private Transactions transactionSelect = null;
	private String recher;
	private JComboBox selectNumCpte;
	private JComboBox selectCompte;
	private JComboBox selectTypeTrans;
	private JComboBox selectGestionnaire;
	private JDateChooser dateTransaction;
	private JTextField textRechercher;
	private JTextField textFieldMontant;
	private JLabel labelCpteAff;
	private JLabel labelDateAff;
	private JLabel labelTypeTransAff;
	private JLabel labelMontantAff;
	private JLabel labelGestAff;
	private List<Comptes> maListeComptes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionsBancaire frame = new TransactionsBancaire();
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
	public TransactionsBancaire() {

		// Initialisation des variables serviceComptes et daoComptes
		dao = new Dao();
		service = new Service(dao);

		setBounds(100, 100, 931, 505);
		setBorder(null);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste des comptes ayant moins de 100\u20AC comme solde", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(472, 264, 452, 233);
		getContentPane().add(panel);

		panel_2 = new JPanel();
		panel_2.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Historique des transfert ProxiBanqueSI", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		panel_2.setBounds(10, 11, 449, 176);
		getContentPane().add(panel_2);

		labelCpteAff = new JLabel("");
		labelCpteAff.setForeground(Color.WHITE);
		labelCpteAff.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelCpteAff.setBounds(628, 56, 254, 21);
		getContentPane().add(labelCpteAff);

		JLabel lblNumeroCpte = new JLabel("N\u00B0 du compte : ");
		lblNumeroCpte.setForeground(Color.WHITE);
		lblNumeroCpte.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNumeroCpte.setBounds(488, 56, 130, 21);
		getContentPane().add(lblNumeroCpte);

		JLabel lblDateTrans = new JLabel("Date transaction : ");
		lblDateTrans.setForeground(Color.WHITE);
		lblDateTrans.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblDateTrans.setBounds(488, 88, 130, 21);
		getContentPane().add(lblDateTrans);

		labelDateAff = new JLabel("");
		labelDateAff.setForeground(Color.WHITE);
		labelDateAff.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelDateAff.setBounds(628, 88, 254, 21);
		getContentPane().add(labelDateAff);

		JLabel lblTypeTrans = new JLabel("Type transaction");
		lblTypeTrans.setForeground(Color.WHITE);
		lblTypeTrans.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblTypeTrans.setBounds(488, 120, 130, 21);
		getContentPane().add(lblTypeTrans);

		labelTypeTransAff = new JLabel("");
		labelTypeTransAff.setForeground(Color.WHITE);
		labelTypeTransAff.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelTypeTransAff.setBounds(628, 120, 254, 21);
		getContentPane().add(labelTypeTransAff);

		JLabel lblMontantAff = new JLabel("Montant :");
		lblMontantAff.setForeground(Color.WHITE);
		lblMontantAff.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblMontantAff.setBounds(488, 152, 130, 21);
		getContentPane().add(lblMontantAff);

		JLabel lblGestionnaire = new JLabel("Gestionnaire");
		lblGestionnaire.setForeground(Color.WHITE);
		lblGestionnaire.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblGestionnaire.setBounds(488, 184, 130, 21);
		getContentPane().add(lblGestionnaire);

		labelGestAff = new JLabel("");
		labelGestAff.setForeground(Color.WHITE);
		labelGestAff.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelGestAff.setBounds(628, 184, 254, 21);
		getContentPane().add(labelGestAff);

		labelMontantAff = new JLabel("");
		labelMontantAff.setForeground(Color.WHITE);
		labelMontantAff.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelMontantAff.setBounds(628, 152, 254, 21);
		getContentPane().add(labelMontantAff);

		JLabel lblInformationsDtaillesDu = new JLabel("INFORMATIONS DETAILLEES DE LA TRANSACTION");
		lblInformationsDtaillesDu.setForeground(Color.WHITE);
		lblInformationsDtaillesDu.setFont(new Font("Roboto Black", Font.BOLD | Font.ITALIC, 12));
		lblInformationsDtaillesDu.setBounds(565, 7, 317, 21);
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

		JButton btnPrintCompte = new JButton("Imprimer");
		btnPrintCompte.setIcon(new ImageIcon(TransactionsBancaire.class.getResource("/img/icons8_Print_32px.png")));
		btnPrintCompte.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnPrintCompte.setBounds(10, 216, 129, 37);
		getContentPane().add(btnPrintCompte);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recher = textRechercher.getText();
				if (recher.isEmpty()) {
					JOptionPane.showMessageDialog(null, "veuillez entrer un numéro de compte!!!", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					affichageTransactionsTrouver();
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(TransactionsBancaire.class.getResource("/img/icons8_Search_32px.png")));
		btnSearch.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnSearch.setBounds(330, 216, 129, 37);
		getContentPane().add(btnSearch);

		textRechercher = new JTextField();
		textRechercher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textRechercher.setText(" ");
			}
		});
		textRechercher.setToolTipText("Recherchez une transaction");
		//textRechercher.setText("Saisir le num\u00E9ro du compte");
		textRechercher.setFont(new Font("Roboto", Font.PLAIN, 12));
		textRechercher.setColumns(10);
		textRechercher.setBounds(149, 216, 171, 37);
		getContentPane().add(textRechercher);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire de cr\u00E9dit ou d\u00E9bit de compte", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 264, 452, 233);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMontant = new JLabel("Montant :");
		lblMontant.setBounds(10, 114, 151, 28);
		panel_1.add(lblMontant);
		lblMontant.setForeground(Color.BLACK);
		lblMontant.setFont(new Font("Roboto Light", Font.BOLD, 13));

		textFieldMontant = new JTextField();
		textFieldMontant.addKeyListener(new KeyAdapter() {
			/** (
			 * Contraint l'utilisateur à entrer uniquement des chiffres
			 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vChar = arg0.getKeyChar();
				if (!(Character.isDigit(vChar)) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)) {

					arg0.consume();
				}
			}
		});
		textFieldMontant.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(171, 118, 160, 20);
		panel_1.add(textFieldMontant);

		JLabel labelNumCpte = new JLabel("N\u00B0 Compte :");
		labelNumCpte.setForeground(Color.BLACK);
		labelNumCpte.setFont(new Font("Roboto Light", Font.BOLD, 13));
		labelNumCpte.setBounds(10, 21, 151, 24);
		panel_1.add(labelNumCpte);

		JLabel lblSelectGest = new JLabel("Gestionnaire :");
		lblSelectGest.setForeground(Color.BLACK);
		lblSelectGest.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblSelectGest.setBounds(10, 145, 151, 28);
		panel_1.add(lblSelectGest);

		selectNumCpte = new JComboBox();
		selectNumCpte.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		selectNumCpte.setBounds(171, 23, 160, 20);
		panel_1.add(selectNumCpte);
		List<Comptes> maListeComptesEmt = new ArrayList<Comptes>();
		maListeComptesEmt = service.getAllComptesService();
		for (Comptes compte : maListeComptesEmt) {
			selectNumCpte.addItem(compte);
		}

		selectGestionnaire = new JComboBox();
		selectGestionnaire.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		selectGestionnaire.setBounds(171, 149, 160, 20);
		panel_1.add(selectGestionnaire);
		List<Gestionnaires> maListeGestionnaires = new ArrayList<Gestionnaires>();
		maListeGestionnaires = service.getAllGestionnairesService();
		for (Gestionnaires gestionnaire : maListeGestionnaires) {
			selectGestionnaire.addItem(gestionnaire);
		}

		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setBounds(195, 178, 136, 37);
		panel_1.add(btnConfirmer);
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// code executé après le clic sur "Envoyer"

				String verifierMontant = textFieldMontant.getText();
				//String verifierDate = ((JTextField) dateOperation.getDateEditor().getUiComponent()).getText();

				// || selectCpteRec.getSelectedIndex() == 0
				// || selectCpteEmt.getSelectedIndex() == 0)

				if (verifierMontant.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {

					// 3. Création de services et utilisation du contructeur générique. Elle prend
					// en paramètre un IDao
					dao = new Dao();
					service = new Service(dao);

					// 1. Récupération des valeur saisies par l'utilisateur
					
					Comptes compteSelect = ((Comptes) selectNumCpte.getSelectedItem());
					int numCpteSelect = compteSelect.getNumComptes();
					String dateTrans = ((JTextField) dateTransaction.getDateEditor().getUiComponent()).getText();
					String typeTransaction = (String) selectTypeTrans.getSelectedItem();
					float montTransaction = Float.parseFloat(textFieldMontant.getText());
					Gestionnaires gestionnaireSelect = ((Gestionnaires) selectGestionnaire.getSelectedItem());
					String idGestionnaire = gestionnaireSelect.getIdGestionnaires();
					
//					Date date = new Date();
//					DateFormat actuelle = new SimpleDateFormat("yyyy-MM-dd");
//					String dateOperation = actuelle.format(date);

					// 2. Création d'un objet transaction puis, maj des attributs de l'objet Transactions

					Transactions transaction = new Transactions();

					transaction.setCompte(numCpteSelect);
					transaction.setDate(dateTrans);
					transaction.setTypeTransaction(typeTransaction);
					transaction.setMontant(montTransaction);
					transaction.setGestionnaires_idGestionnaires(idGestionnaire);
					

					// On teste si le montant que l'emmetteur essaie d'envoyer est inférieur au
					// montant de son solde

					// variable qui va contenir le solde du compte sélectionné
					float verifierSolde = service.getSoldeComptesService(numCpteSelect);
					// variable qui va contenir le type de transaction
					String verifierTransaction = typeTransaction;
					int numeroCpte = numCpteSelect;
					float montantEntre = montTransaction;
					float somme = verifierSolde + montantEntre;
					float reste = verifierSolde - montantEntre;

					if (verifierTransaction == "Crédit") {
						int verifier1 = service.faireCreditTransactionsService(somme, numeroCpte);
						if (verifier1 == 1) {
							JOptionPane.showMessageDialog(null, "le compte N° " + transaction.getCompte() + " a été crédité de : " + transaction.getMontant() + " !", "Information!", JOptionPane.INFORMATION_MESSAGE);
							
							//ENREGISTREMENT DE LA TRANSACTION
							
							// 4. On donne notre objet transaction à la méthode enregistrerTransactionsService de la couche service
							// La variable verifier permet de contenir le résultat de l'instruction d'enregistrement
							
							int verifier = service.enregistrerTransactionsService(transaction);
							if (verifier == 1) {
								JOptionPane.showMessageDialog(null,
										"La transaction est un succès", "Information!", JOptionPane.INFORMATION_MESSAGE);

								viderChamps();

							} else {
								JOptionPane.showMessageDialog(null, "Erreur survenue lors de la transaction!", "Alerte!",
										JOptionPane.ERROR_MESSAGE);
							}
							//FIN DE LA TRANSACTION
						}
						
					} else {
						if (verifierSolde<montantEntre) {
							JOptionPane.showMessageDialog(null, "Attention le montant entré est supérieur au solde en compte! " + transaction.getMontant() + " !", "Attention!", JOptionPane.WARNING_MESSAGE);
						}else {
							int verifier2 = service.faireDebitTransactionsService(reste, numeroCpte);
							if (verifier2 == 1) {
								JOptionPane.showMessageDialog(null, "le compte N° " + transaction.getCompte() + " a été débité de : " + transaction.getMontant() + " !", "Information!", JOptionPane.INFORMATION_MESSAGE);
								
								//ENREGISTREMENT DE LA TRANSACTION
								
								// 4. On donne notre objet transaction à la méthode enregistrerTransactionsService de la couche service
								// La variable verifier permet de contenir le résultat de l'instruction d'enregistrement
								
								int verifier = service.enregistrerTransactionsService(transaction);
								if (verifier == 1) {
									JOptionPane.showMessageDialog(null,
											"La transaction est un succès", "Information!", JOptionPane.INFORMATION_MESSAGE);

									viderChamps();

								} else {
									JOptionPane.showMessageDialog(null, "Erreur survenue lors de la transaction!", "Alerte!", JOptionPane.ERROR_MESSAGE);
								}
								//FIN DE LA TRANSACTION
							}
						}
					}

				}
			}
		});
		btnConfirmer
				.setIcon(new ImageIcon(TransactionsBancaire.class.getResource("/img/icons8_Cash_in_Hand_32px.png")));
		btnConfirmer.setFont(new Font("Roboto Medium", Font.BOLD, 11));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TransactionsBancaire.class.getResource("/img/transfert.png")));
		label_1.setBounds(341, 11, 101, 193);
		panel_1.add(label_1);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setForeground(Color.BLACK);
		lblType.setFont(new Font("Roboto Lt", Font.BOLD, 13));
		lblType.setBounds(10, 82, 151, 28);
		panel_1.add(lblType);
		
		selectTypeTrans = new JComboBox();
		selectTypeTrans.setModel(new DefaultComboBoxModel(new String[] {"Cr\u00E9dit", "D\u00E9bit"}));
		selectTypeTrans.setBounds(172, 87, 159, 20);
		panel_1.add(selectTypeTrans);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Roboto Lt", Font.BOLD, 13));
		lblDate.setBounds(10, 56, 151, 24);
		panel_1.add(lblDate);
		
		dateTransaction = new JDateChooser();
		dateTransaction.setDateFormatString("yyyy-MM-dd");
		dateTransaction.setBounds(171, 56, 160, 20);
		panel_1.add(dateTransaction);

		JLabel label = new JLabel("");
		label.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		label.setIcon(new ImageIcon(GestionComptes.class.getResource("/img/fondInternalFrame.jpg")));
		label.setBounds(0, 0, 931, 505);
		getContentPane().add(label);
		setBounds(100, 100, 931, 505);

		// Appelle de la méthode pour afficher la liste des comptes seuils
		affichageSeuil();

		// Appelle de la méthode pour afficher la liste des operations
		affichageTransactions();

		// Appelle de la méthode pour supprimer la barre de titre
		remove_title_bar();

		// Appelle de la méthode pour vider les champs du formulaire
		viderChamps();

	}

	/**
	 * Cette méthode permet d'afficher la liste des comptes seuils
	 */
	public void affichageSeuil() {
		// Appel de la méthode getAllComptesService() que je place dans mon model
		ComptesModel comptesModel = new ComptesModel(service.getAllComptesSeuilService());
		jTableComptes = new JTable(comptesModel);
		jTableComptes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				getContenuTab();
			}
		});
		JScrollPane scrollPane = new JScrollPane(jTableComptes);
		scrollPane.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		panel.add(scrollPane, BorderLayout.CENTER);
		jTableComptes.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableComptes.setBorder(null);
	}

	/**
	 * Cette méthode permet d'afficher les opérations
	 */
	public void affichageTransactions() {
		// Appel de la méthode getAllOperationsService() que je place dans mon model
		TransactionsModel transactionsModel = new TransactionsModel(service.getAllTransactionsService());
		jTableTransactions = new JTable(transactionsModel);
		jTableTransactions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getContenuTab();
			}
		});

		JScrollPane scrollPane2 = new JScrollPane(jTableTransactions);
		scrollPane2.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		panel_2.add(scrollPane2, BorderLayout.CENTER);
		jTableTransactions.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableTransactions.setBorder(null);
	}

	/**
	 * Cette méthode permet d'afficher dans le jTable la liste des comptes trouvés
	 * en base de données
	 */
	public void affichageTransactionsTrouver() {
		TransactionsModel transactionModel = new TransactionsModel(service.rechercherTransactionsService(recher));
		jTableTransactions.setModel(transactionModel);
		panel_2.add(new JScrollPane(jTableTransactions), BorderLayout.CENTER);
		jTableTransactions.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableTransactions.setBorder(null);
	}

	/**
	 * Cette méthode permet d'effacer les champs du formulaire d'enregistrement du
	 * client
	 */
	public void viderChamps() {
		textFieldMontant.setText("");
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

		row = jTableTransactions.getSelectedRow();
		labelCpteAff.setText(jTableTransactions.getModel().getValueAt(row, 1).toString());
		labelDateAff.setText(jTableTransactions.getModel().getValueAt(row, 2).toString());
		labelTypeTransAff.setText(jTableTransactions.getModel().getValueAt(row, 3).toString());
		labelMontantAff.setText(jTableTransactions.getModel().getValueAt(row, 4).toString());
		labelGestAff.setText(jTableTransactions.getModel().getValueAt(row, 5).toString());

		transactionSelect = new Transactions();
		transactionSelect.setCompte((Integer.parseInt(jTableTransactions.getModel().getValueAt(row, 1).toString())));
		transactionSelect.setDate((jTableTransactions.getModel().getValueAt(row, 2).toString()));
		transactionSelect.setIdTransaction((jTableTransactions.getModel().getValueAt(row, 3).toString()));
		//compteSelect.setDate((jTableComptes.getModel().getValueAt(row, 3).toString()));
		transactionSelect.setMontant((Float.parseFloat(jTableTransactions.getModel().getValueAt(row, 4).toString())));
		transactionSelect.setGestionnaires_idGestionnaires((jTableTransactions.getModel().getValueAt(row, 5).toString()));

	}
}
