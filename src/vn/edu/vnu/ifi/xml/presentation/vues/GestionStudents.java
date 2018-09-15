package vn.edu.vnu.ifi.xml.presentation.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.toedter.calendar.JDateChooser;

import vn.edu.vnu.ifi.xml.dao.Dao;
import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.domaine.Students;
import vn.edu.vnu.ifi.xml.domaine.StudentsModel;
import vn.edu.vnu.ifi.xml.service.IService;
import vn.edu.vnu.ifi.xml.service.Service;

public class GestionStudents extends JInternalFrame {

	private IService service;
	private IDao dao;
	private JTable jTableStudents;
	private JPanel panel;
	private int row;
	private Students studentSelect = null;
	private String recher;
	private JDateChooser dateChooser;
	private JComboBox selectLieuNaiss;
	private JScrollPane scrollPane;
	private JComboBox selectNiveau;
	private JTextField textRechercher;
	private JTextField textFieldMatricule;
	private JTextField textFieldNoms;
	private JTextField textFieldPrenoms;
	private JTextField textFieldTelephone;
	private JTextField textFieldPolice;
	private JTextField textFieldAdresse;
	private JLabel labelMatricule;
	private JLabel labelNoms;
	private JLabel labelPrenoms;
	private JLabel labelDateNaiss;
	private JLabel labelLieuNaiss;
	private JLabel labelAdresse;
	private JLabel labelTelephone;
	private JLabel labelNiveau;
	private JLabel labelPoliceAssur;
	private JLabel labelIdClient;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionStudents frame = new GestionStudents();
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
	public GestionStudents() {

		// Initialisation des variables serviceClients et daoClients
		dao = new Dao();
		service = new Service(dao);

		setBounds(100, 100, 931, 505);
		setBorder(null);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Liste des \u00E9tudiants de la promotion 22", TitledBorder.LEFT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 11, 452, 287);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		labelMatricule = new JLabel("");
		labelMatricule.setForeground(Color.WHITE);
		labelMatricule.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelMatricule.setBounds(596, 26, 263, 21);
		getContentPane().add(labelMatricule);

		JLabel lblMatriculeInf = new JLabel("Matricule :");
		lblMatriculeInf.setForeground(Color.WHITE);
		lblMatriculeInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblMatriculeInf.setBounds(481, 26, 105, 21);
		getContentPane().add(lblMatriculeInf);

		JLabel lblNomsInf = new JLabel("Noms :");
		lblNomsInf.setForeground(Color.WHITE);
		lblNomsInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNomsInf.setBounds(481, 58, 105, 21);
		getContentPane().add(lblNomsInf);

		labelNoms = new JLabel("");
		labelNoms.setForeground(Color.WHITE);
		labelNoms.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelNoms.setBounds(596, 58, 263, 21);
		getContentPane().add(labelNoms);

		JLabel lblPrenomsInf = new JLabel("Pr\u00E9noms :");
		lblPrenomsInf.setForeground(Color.WHITE);
		lblPrenomsInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblPrenomsInf.setBounds(481, 90, 115, 21);
		getContentPane().add(lblPrenomsInf);

		labelPrenoms = new JLabel("");
		labelPrenoms.setForeground(Color.WHITE);
		labelPrenoms.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelPrenoms.setBounds(596, 90, 263, 21);
		getContentPane().add(labelPrenoms);

		JLabel lblDateNaissInf = new JLabel("Data Naiss. :");
		lblDateNaissInf.setForeground(Color.WHITE);
		lblDateNaissInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblDateNaissInf.setBounds(481, 122, 105, 21);
		getContentPane().add(lblDateNaissInf);

		JLabel lblLieuNaissInf = new JLabel("Lieu Naissance :");
		lblLieuNaissInf.setForeground(Color.WHITE);
		lblLieuNaissInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblLieuNaissInf.setBounds(481, 154, 105, 21);
		getContentPane().add(lblLieuNaissInf);

		labelLieuNaiss = new JLabel("");
		labelLieuNaiss.setForeground(Color.WHITE);
		labelLieuNaiss.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelLieuNaiss.setBounds(596, 154, 263, 21);
		getContentPane().add(labelLieuNaiss);

		JLabel lblAdresseInf = new JLabel("Adresse :");
		lblAdresseInf.setForeground(Color.WHITE);
		lblAdresseInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblAdresseInf.setBounds(481, 186, 105, 21);
		getContentPane().add(lblAdresseInf);

		labelAdresse = new JLabel("");
		labelAdresse.setForeground(Color.WHITE);
		labelAdresse.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelAdresse.setBounds(596, 186, 263, 21);
		getContentPane().add(labelAdresse);

		labelDateNaiss = new JLabel("");
		labelDateNaiss.setForeground(Color.WHITE);
		labelDateNaiss.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelDateNaiss.setBounds(596, 122, 263, 21);
		getContentPane().add(labelDateNaiss);

		JLabel lblTelephoneInf = new JLabel("T\u00E9l\u00E9phone :");
		lblTelephoneInf.setForeground(Color.WHITE);
		lblTelephoneInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblTelephoneInf.setBounds(481, 218, 105, 21);
		getContentPane().add(lblTelephoneInf);

		labelTelephone = new JLabel("");
		labelTelephone.setForeground(Color.WHITE);
		labelTelephone.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelTelephone.setBounds(596, 218, 263, 21);
		getContentPane().add(labelTelephone);

		JLabel lblNiveauInf = new JLabel("Niveau :");
		lblNiveauInf.setForeground(Color.WHITE);
		lblNiveauInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNiveauInf.setBounds(481, 250, 105, 21);
		getContentPane().add(lblNiveauInf);

		labelNiveau = new JLabel("");
		labelNiveau.setForeground(Color.WHITE);
		labelNiveau.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelNiveau.setBounds(596, 250, 263, 21);
		getContentPane().add(labelNiveau);

		JLabel lblPoliceInf = new JLabel("Police Assur. :");
		lblPoliceInf.setForeground(Color.WHITE);
		lblPoliceInf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblPoliceInf.setBounds(481, 282, 105, 21);
		getContentPane().add(lblPoliceInf);

		labelPoliceAssur = new JLabel("");
		labelPoliceAssur.setForeground(Color.WHITE);
		labelPoliceAssur.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		labelPoliceAssur.setBounds(596, 282, 263, 21);
		getContentPane().add(labelPoliceAssur);

		JLabel lblInformationsDtaillesDu = new JLabel("Informations d\u00E9taill\u00E9es d'un \u00E9tudiant");
		lblInformationsDtaillesDu.setForeground(Color.WHITE);
		lblInformationsDtaillesDu.setFont(new Font("Roboto Black", Font.BOLD | Font.ITALIC, 12));
		lblInformationsDtaillesDu.setBounds(590, 7, 242, 15);
		getContentPane().add(lblInformationsDtaillesDu);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(596, 45, 263, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(596, 76, 263, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(596, 109, 263, 2);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(596, 141, 263, 2);
		getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(596, 173, 263, 2);
		getContentPane().add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(596, 205, 263, 2);
		getContentPane().add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.BLACK);
		separator_6.setBackground(Color.BLACK);
		separator_6.setBounds(596, 237, 263, 2);
		getContentPane().add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.BLACK);
		separator_7.setBackground(Color.BLACK);
		separator_7.setBounds(596, 269, 263, 2);
		getContentPane().add(separator_7);

		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.BLACK);
		separator_8.setBackground(Color.BLACK);
		separator_8.setBounds(596, 301, 263, 2);
		getContentPane().add(separator_8);

		JButton btnAddEtu = new JButton("Ajouter");
		btnAddEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// code executé après le clic sur "Ajouter"

				String verifierNoms = textFieldNoms.getText();
				String verifierPrenoms = textFieldPrenoms.getText();
				String verifierDate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String verifierAdresse = textFieldAdresse.getText();
				String verifierTelephone = textFieldTelephone.getText();

				if (verifierNoms.isEmpty() || verifierPrenoms.isEmpty() || verifierDate.isEmpty()
						|| selectLieuNaiss.getSelectedIndex() == 0 || verifierAdresse.isEmpty()
						|| verifierTelephone.isEmpty() || selectNiveau.getSelectedIndex() == 0) {

					JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {

					// 3. Création de services et utilisation du contructeur générique. Elle prend
					// en paramètre un IDaoClients
					dao = new Dao();
					service = new Service(dao);

					// 1. Récupération des valeur saisies par l'utilisateur

					String noms = textFieldNoms.getText();
					String prenoms = textFieldPrenoms.getText();
					String dateNaiss = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String lieuNaiss = ((String) selectLieuNaiss.getSelectedItem());
					String adresse = textFieldAdresse.getText();
					String telephone = textFieldTelephone.getText();
					String niveau = ((String) selectNiveau.getSelectedItem());

					// 2. Création d'un objet student puis, maj des attributs de l'objet

					Students student = new Students();
					student.setNoms(noms);
					student.setPrenoms(prenoms);
					student.setDateNaissance(dateNaiss);
					student.setLieuNaissance(lieuNaiss);
					student.setAdresse(adresse);
					student.setTelephone(telephone);
					student.setNiveau(niveau);
					student.setMatricule(service.genererMatriculeEtudiant(student));
					student.setPolice(service.genererPoliceAssurance(student));

					// 4. On donne notre objet student à la méthode createStudentService de la
					// couche service

					// La variable verifier permet de contenir le résultat de l'instruction
					// d'enregistrement

					int verifier = service.createStudentsService(student);
					if (verifier == 1) {
						JOptionPane.showMessageDialog(null,
								"le " + student.getNoms() + " " + student.getPrenoms()
										+ " a bien été enregistré dans le fichier XML",
								"Information!", JOptionPane.INFORMATION_MESSAGE);

						viderChamps();

					} else {
						JOptionPane.showMessageDialog(null,
								"Erreur lors de l'enregistrement du client " + student.getNoms() + " "
										+ student.getPrenoms() + " dans le fichier XML",
								"Alerte!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddEtu.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/Add_User_Group_Woman_Man_32px.png")));
		btnAddEtu.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnAddEtu.setBounds(10, 442, 122, 37);
		getContentPane().add(btnAddEtu);

		JButton btnEditEtu = new JButton("Modifier");
		btnEditEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (studentSelect != null) {
					// code executé après le clic sur "Modifier"

					// 3. Création de services et utilisation du contructeur générique. Elle prend
					// en paramètre un IDao
					dao = new Dao();
					service = new Service(dao);

					// 1. Récupération des valeur saisies par l'utilisateur

					String matricule = textFieldMatricule.getText();
					String noms = textFieldNoms.getText();
					String prenoms = textFieldPrenoms.getText();
					String dateNaiss = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String lieuNaiss = ((String) selectLieuNaiss.getSelectedItem());
					String adresse = textFieldAdresse.getText();
					String telephone = textFieldTelephone.getText();
					String niveau = ((String) selectNiveau.getSelectedItem());
					String police = textFieldPolice.getText();

					// 2. Création d'un objet student puis, maj des attributs de l'objet student

					Students student = new Students();
					
					student.setMatricule(matricule);
					student.setNoms(noms);
					student.setPrenoms(prenoms);
					student.setDateNaissance(dateNaiss);
					student.setLieuNaissance(lieuNaiss);
					student.setAdresse(adresse);
					student.setTelephone(telephone);
					student.setNiveau(niveau);
					student.setPolice(police);

					// 3. On donne notre objet client à la méthode editStudentsService de la couche service

					int verifier = service.editStudentsService(student);

					if (verifier == 1) {
						JOptionPane.showMessageDialog(null,
								"Le client " + student.getNoms() + " " + student.getPrenoms()
										+ " a bien été modifié dans la Base de données",
								"Information!", JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null,
								"Erreur lors de la modification de l'étudiant " + student.getNoms() + " "
										+ student.getPrenoms() + " dans le fichier XML",
								"Alerte!", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "bien vouloir sélectionner un étudiant de la liste", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEditEtu.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/icons8_Edit_Property_32px.png")));
		btnEditEtu.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnEditEtu.setBounds(142, 442, 122, 37);
		getContentPane().add(btnEditEtu);

		JButton btnDelEtu = new JButton("Supprimer");
		btnDelEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// code executé après le clic sur "Supprimer"

				if (JOptionPane.showConfirmDialog(null, "Souhaitez-vous vraiment supprimer cet étudiant?",
						"Supprimer étudiant", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					if (textFieldMatricule.getText().length() != 0) {

						// 3. Création de services et utilisation du contructeur générique. Elle prend
						// en paramètre un IDao
						dao = new Dao();
						service = new Service(dao);

						// 1. Récupération de la valeur sélectionner par l'utilisateur

						String matricule = textFieldMatricule.getText();
						

						// 2. Création d'un objet client puis, maj des attributs de l'objet client

						

						// 4. On donne notre objet student à la méthode removeStudentsService() de la
						// couche service

						int verifier = service.removeStudentsService(matricule);

						if (verifier == 1) {
							JOptionPane.showMessageDialog(null,
									"L'étudiant ayant le matricule " + matricule
											+ " a bien été supprimé dans le fichier XML",
									"Information!", JOptionPane.INFORMATION_MESSAGE);

							viderChamps();

						} else {
							JOptionPane
									.showMessageDialog(null,
											"Erreur lors de la suppression de l'étudiant ayant le matricule "
													+ matricule + " dans le fichier XML",
											"Alerte!", JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "veuillez entrer le matricule de l'étudiant!", "Attention!",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnDelEtu.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/icons8_Trash_Can_32px.png")));
		btnDelEtu.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnDelEtu.setBounds(274, 442, 134, 37);
		getContentPane().add(btnDelEtu);

		JButton btnPrintEtu = new JButton("Imprimer");
		btnPrintEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (service.printStudentHtml() == true) {
					JOptionPane.showMessageDialog(null,
							"Impression en html réussi", "Information!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,
							"Echec de l'impression en html", "Attention!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrintEtu.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/icons8_Print_32px.png")));
		btnPrintEtu.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		btnPrintEtu.setBounds(418, 442, 129, 37);
		getContentPane().add(btnPrintEtu);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recher = textRechercher.getText();
				if (recher.isEmpty()) {
					JOptionPane.showMessageDialog(null, "veuillez entrer un nom!!!", "Attention!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					affichageStudentsTrouver();
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/icons8_Search_32px.png")));
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
		textRechercher.setToolTipText("Recherchez un client");
		textRechercher.setText("Saisir un matricule");
		textRechercher.setFont(new Font("Roboto", Font.PLAIN, 13));
		textRechercher.setColumns(10);
		textRechercher.setBounds(557, 442, 122, 37);
		getContentPane().add(textRechercher);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Formulaire d'enregistrement et modification d'un \u00E9tudiant", TitledBorder.LEFT, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 309, 808, 130);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMatricule = new JLabel("Matricule");
		lblMatricule.setBounds(10, 21, 70, 24);
		panel_1.add(lblMatricule);
		lblMatricule.setForeground(Color.BLACK);
		lblMatricule.setFont(new Font("Roboto Light", Font.BOLD, 13));

		JLabel lblNoms = new JLabel("Noms :");
		lblNoms.setBounds(10, 56, 70, 28);
		panel_1.add(lblNoms);
		lblNoms.setForeground(Color.BLACK);
		lblNoms.setFont(new Font("Roboto Light", Font.BOLD, 13));

		JLabel lblPrenoms = new JLabel("Pr\u00E9noms :");
		lblPrenoms.setForeground(Color.BLACK);
		lblPrenoms.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblPrenoms.setBounds(10, 95, 70, 28);
		panel_1.add(lblPrenoms);

		textFieldMatricule = new JTextField();
		textFieldMatricule.setForeground(Color.GREEN);
		textFieldMatricule.setFont(new Font("Dialog", Font.ITALIC, 13));
		textFieldMatricule.setBounds(90, 24, 160, 20);
		panel_1.add(textFieldMatricule);
		textFieldMatricule.setColumns(10);

		textFieldNoms = new JTextField();
		textFieldNoms.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldNoms.setColumns(10);
		textFieldNoms.setBounds(90, 61, 160, 20);
		panel_1.add(textFieldNoms);

		textFieldPrenoms = new JTextField();
		textFieldPrenoms.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldPrenoms.setColumns(10);
		textFieldPrenoms.setBounds(90, 100, 160, 20);
		panel_1.add(textFieldPrenoms);

		JLabel lblTelephone = new JLabel("T\u00E9l\u00E9phone : ");
		lblTelephone.setForeground(Color.BLACK);
		lblTelephone.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblTelephone.setBounds(536, 21, 96, 24);
		panel_1.add(lblTelephone);

		textFieldTelephone = new JTextField();
		textFieldTelephone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char vChar = evt.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					evt.consume();
				}
			}
		});
		textFieldTelephone.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(642, 23, 160, 20);
		panel_1.add(textFieldTelephone);

		JLabel lblPloiceAssur = new JLabel("Police Ass. :");
		lblPloiceAssur.setForeground(Color.BLACK);
		lblPloiceAssur.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblPloiceAssur.setBounds(536, 95, 96, 28);
		panel_1.add(lblPloiceAssur);

		textFieldPolice = new JTextField();
		textFieldPolice.setForeground(Color.GREEN);
		textFieldPolice.setFont(new Font("Dialog", Font.ITALIC, 13));
		textFieldPolice.setColumns(10);
		textFieldPolice.setBounds(642, 97, 160, 20);
		panel_1.add(textFieldPolice);

		JLabel lblAdresse = new JLabel("Adresse : ");
		lblAdresse.setForeground(Color.BLACK);
		lblAdresse.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblAdresse.setBounds(260, 95, 96, 28);
		panel_1.add(lblAdresse);

		textFieldAdresse = new JTextField();
		textFieldAdresse.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		textFieldAdresse.setColumns(10);
		textFieldAdresse.setBounds(366, 97, 160, 20);
		panel_1.add(textFieldAdresse);

		JLabel lblDateNaiss = new JLabel("Date Naiss :");
		lblDateNaiss.setForeground(Color.BLACK);
		lblDateNaiss.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblDateNaiss.setBounds(264, 21, 96, 24);
		panel_1.add(lblDateNaiss);

		JLabel lblNiveau = new JLabel("Niveau :");
		lblNiveau.setForeground(Color.BLACK);
		lblNiveau.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblNiveau.setBounds(536, 56, 96, 28);
		panel_1.add(lblNiveau);

		JLabel lblLieuNaiss = new JLabel("Lieu Naiss :");
		lblLieuNaiss.setForeground(Color.BLACK);
		lblLieuNaiss.setFont(new Font("Roboto Light", Font.BOLD, 13));
		lblLieuNaiss.setBounds(260, 56, 96, 28);
		panel_1.add(lblLieuNaiss);

		selectLieuNaiss = new JComboBox();
		selectLieuNaiss.setEditable(true);
		selectLieuNaiss.setModel(new DefaultComboBoxModel(
				new String[] { "", "Afrique ", "Asie", "Pacifique", "Am\u00E9rique", "Europe", "Oc\u00E9anie" }));
		selectLieuNaiss.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		selectLieuNaiss.setBounds(366, 61, 160, 20);
		panel_1.add(selectLieuNaiss);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(366, 25, 160, 20);
		panel_1.add(dateChooser);

		selectNiveau = new JComboBox();
		selectNiveau.setModel(new DefaultComboBoxModel(
				new String[] { "", "Licence I", "Licence II", "Licence III", "Master I", "Master II" }));
		selectNiveau.setEditable(true);
		selectNiveau.setBounds(642, 61, 160, 20);
		panel_1.add(selectNiveau);

		labelIdClient = new JLabel("");
		labelIdClient.setFont(new Font("Roboto Condensed Light", Font.BOLD, 13));
		labelIdClient.setBounds(852, 336, 30, 20);
		getContentPane().add(labelIdClient);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/Contrat.png")));
		lblNewLabel.setBounds(823, 308, 108, 171);
		getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setFont(new Font("Roboto Medium", Font.BOLD | Font.ITALIC, 12));
		label.setIcon(new ImageIcon(GestionStudents.class.getResource("/img/fondInternalFrame.jpg")));
		label.setBounds(0, 0, 931, 505);
		getContentPane().add(label);
		setBounds(100, 100, 931, 505);

		// Appelle de la méthode pour afficher la liste des étudiants
		affichage();

		// Appelle de la méthode pour supprimer la barre de titre
		remove_title_bar();

		// Appelle de la méthode pour vider les champs du formulaire
		viderChamps();

	}

	/**
	 * Cette méthode permet d'afficher la liste des étudiants
	 */
	public void affichage() {
		// Appel de la méthode service.getAllStudentsService() que je place dans mon model
		StudentsModel studentsModel = new StudentsModel(service.getAllStudentsService());
		jTableStudents = new JTable(studentsModel);
		jTableStudents.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				getContenuTab();
				remplirFormulaire(studentSelect);
			}
		});

		scrollPane = new JScrollPane(jTableStudents);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		scrollPane.setPreferredSize(new Dimension(450, 60));
		panel.add(scrollPane, BorderLayout.CENTER);
		jTableStudents.setBorder(null);

		jTableStudents.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setViewportView(jTableStudents);
	}

	/**
	 * Cette méthode permet d'afficher dans le jTable la liste des étudiants trouvés
	 * en base de données
	 */
	public void affichageStudentsTrouver() {
		StudentsModel studentsModel1 = new StudentsModel(service.rechercherStudentService(recher));
		jTableStudents.setModel(studentsModel1);
		panel.add(new JScrollPane(jTableStudents), BorderLayout.CENTER);
		jTableStudents.setFont(new Font("Roboto", Font.PLAIN, 12));
		jTableStudents.setBorder(null);
	}

	/**
	 * Cette méthode permet d'effacer les champs du formulaire d'enregistrement du
	 * client
	 */
	public void viderChamps() {
		textFieldMatricule.setText("G\u00E9n\u00E9r\u00E9");
		textFieldNoms.setText("");
		textFieldPrenoms.setText("");
		textFieldTelephone.setText("");
		textFieldPolice.setText("G\u00E9n\u00E9r\u00E9");
		textFieldAdresse.setText("");
	}

	/**
	 * Cette méthode permet de supprimer la barre de titre sur mon InternalFrame
	 * 
	 */
	public void remove_title_bar() {
		putClientProperty("GestionClients.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
	}

	/**
	 * Cette méthode permet de récupérer les données d'un champ de tableau
	 * sélectionné
	 */
	public void getContenuTab() {

		row = jTableStudents.getSelectedRow();
		labelMatricule.setText(jTableStudents.getModel().getValueAt(row, 0).toString());
		labelNoms.setText(jTableStudents.getModel().getValueAt(row, 1).toString());
		labelPrenoms.setText(jTableStudents.getModel().getValueAt(row, 2).toString());
		labelDateNaiss.setText(jTableStudents.getModel().getValueAt(row, 3).toString());
		labelLieuNaiss.setText(jTableStudents.getModel().getValueAt(row, 4).toString());
		labelAdresse.setText(jTableStudents.getModel().getValueAt(row, 5).toString());
		labelTelephone.setText(jTableStudents.getModel().getValueAt(row, 6).toString());
		labelNiveau.setText(jTableStudents.getModel().getValueAt(row, 7).toString());
		labelPoliceAssur.setText(jTableStudents.getModel().getValueAt(row, 8).toString());

		studentSelect = new Students();
		studentSelect.setMatricule(jTableStudents.getModel().getValueAt(row, 0).toString());
		studentSelect.setNoms(jTableStudents.getModel().getValueAt(row, 1).toString());
		studentSelect.setPrenoms(jTableStudents.getModel().getValueAt(row, 2).toString());
		studentSelect.setDateNaissance(jTableStudents.getModel().getValueAt(row, 3).toString());
		studentSelect.setLieuNaissance(jTableStudents.getModel().getValueAt(row, 4).toString());
		studentSelect.setAdresse(jTableStudents.getModel().getValueAt(row, 5).toString());
		studentSelect.setTelephone(jTableStudents.getModel().getValueAt(row, 6).toString());
		studentSelect.setNiveau(jTableStudents.getModel().getValueAt(row, 7).toString());
		studentSelect.setPolice(jTableStudents.getModel().getValueAt(row, 8).toString());

	}

	/**
	 * Cette méthode permet de remplir les champs du formulaire par les information
	 * de l'étudiant sélectionné
	 */
	public void remplirFormulaire(Students studentSelect) {
		
		textFieldMatricule.setText(studentSelect.getMatricule());
		textFieldNoms.setText(studentSelect.getNoms());
		textFieldPrenoms.setText(studentSelect.getPrenoms());
		dateChooser.setDateFormatString(studentSelect.getDateNaissance());
		selectLieuNaiss.setSelectedItem(studentSelect.getLieuNaissance());
		textFieldAdresse.setText(studentSelect.getAdresse());
		textFieldTelephone.setText(studentSelect.getTelephone());
		selectNiveau.setSelectedItem(studentSelect.getNiveau());
		textFieldPolice.setText(studentSelect.getPolice());

	}
}
