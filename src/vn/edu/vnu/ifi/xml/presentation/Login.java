package vn.edu.vnu.ifi.xml.presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import vn.edu.vnu.ifi.xml.dao.Dao;
import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.presentation.vues.Accueil;
import vn.edu.vnu.ifi.xml.service.IService;
import vn.edu.vnu.ifi.xml.service.Service;

public class Login {
	// variables que nous manipulerons
	private IService service;
	private IDao dao;
	private JFrame frmPageDeConnexion;
	private JTextField jTextFieldLogin;
	private JPasswordField jPasswordField;
	private JButton jButtonConnexion;
	public static String recLogin;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(9000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Login window = new Login();
				window.frmPageDeConnexion.setVisible(true);
				window.frmPageDeConnexion.setLocationRelativeTo(null);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		dao = new Dao();
		service = new Service(dao);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPageDeConnexion = new JFrame();
		frmPageDeConnexion.pack();
		frmPageDeConnexion.setLocationRelativeTo(null);
		frmPageDeConnexion.setResizable(false);
		frmPageDeConnexion.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/icons8_Private_2_32px.png")));
		frmPageDeConnexion.setTitle("Page de connexion");
		frmPageDeConnexion.setBounds(100, 100, 668, 434);
		frmPageDeConnexion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPageDeConnexion.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(36, 47, 65));
		panel.setBounds(293, 0, 372, 407);
		frmPageDeConnexion.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblBienvenueDansCheckme = new JLabel("BIENVENUE DANS PROMOTION 22XML");
		lblBienvenueDansCheckme.setBounds(27, 41, 313, 24);
		lblBienvenueDansCheckme.setFont(new Font("Roboto Black", Font.BOLD, 18));
		lblBienvenueDansCheckme.setForeground(Color.WHITE);
		panel.add(lblBienvenueDansCheckme);

		JLabel lblNewLabel_1 = new JLabel("Application de gestion des étudiants de la promo. 22");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Roboto Light", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(51, 65, 265, 24);
		panel.add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(7, 93, 352, 11);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(114, 110, 138, 24);
		panel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(87, 100, 192, 11);
		panel.add(separator_2);

		jTextFieldLogin = new JTextField();
		jTextFieldLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTextFieldLogin.setText("");
			}
		});
		jTextFieldLogin.setFont(new Font("Roboto Light", Font.ITALIC, 12));
		jTextFieldLogin.setForeground(Color.WHITE);
		jTextFieldLogin.setText("Entrez Votre Login");
		jTextFieldLogin.setBackground(new Color(36, 47, 65));
		jTextFieldLogin.setBorder(null);
		jTextFieldLogin.setBounds(87, 184, 275, 24);
		panel.add(jTextFieldLogin);
		jTextFieldLogin.setColumns(10);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(87, 210, 275, 24);
		panel.add(separator_3);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/img/icons8_Male_User_32px.png")));
		label.setBounds(45, 184, 32, 32);
		panel.add(label);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(87, 271, 275, 24);
		panel.add(separator_4);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/img/icons8_Private_2_32px.png")));
		label_1.setBounds(45, 245, 32, 32);
		panel.add(label_1);

		jPasswordField = new JPasswordField();
		jPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jPasswordField.setText("");
			}
		});
		jPasswordField.setText("............");
		jPasswordField.setForeground(Color.WHITE);
		jPasswordField.setBackground(new Color(36, 47, 65));
		jPasswordField.setBorder(null);
		jPasswordField.setBounds(87, 245, 275, 24);
		panel.add(jPasswordField);

		jButtonConnexion = new JButton("CONNEXION");
		jButtonConnexion.addActionListener(new ActionListener() {
			/**
			 * @param arg0
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 *      Cette méthode permet de se connecter à l'application
			 */
			public void actionPerformed(ActionEvent arg0) {
				// La viariable login et pwd permettent de récupérer la saisie de l'utilisateur
				String login = jTextFieldLogin.getText();
				String pwd = jPasswordField.getText().toString();

				int connexionOK = service.connexionService(login, pwd);
				if (connexionOK == 1) {
					// recLogin reçoit le contenu de login
					recLogin = login;
					JOptionPane.showMessageDialog(null, "Login et Password ok!");
					frmPageDeConnexion.dispose();
					Accueil acP = new Accueil();
					acP.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect!");
				}
			}
		});
		jButtonConnexion.addMouseListener(new MouseAdapter() {
		});
		jButtonConnexion.setFont(new Font("Roboto Medium", Font.BOLD, 12));
		jButtonConnexion.setIcon(new ImageIcon(Login.class.getResource("/img/icons8_Unlock_32px_1.png")));
		jButtonConnexion.setBackground(new Color(126, 87, 194));
		jButtonConnexion.setForeground(Color.WHITE);
		jButtonConnexion.setBounds(205, 306, 157, 38);
		panel.add(jButtonConnexion);

		JLabel description = new JLabel("");
		description.setFont(new Font("Roboto Light", Font.BOLD, 10));
		description.setForeground(new Color(0, 153, 0));
		description.setBounds(45, 372, 317, 24);
		panel.add(description);

		JLabel jLabel2 = new JLabel("Login :");
		jLabel2.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		jLabel2.setForeground(new Color(57, 113, 177));
		jLabel2.setBackground(new Color(36, 47, 65));
		jLabel2.setBounds(87, 159, 56, 24);
		panel.add(jLabel2);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setForeground(new Color(57, 113, 177));
		lblMotDePasse.setFont(new Font("Roboto Medium", Font.BOLD, 11));
		lblMotDePasse.setBackground(new Color(36, 47, 65));
		lblMotDePasse.setBounds(87, 219, 103, 24);
		panel.add(lblMotDePasse);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/imageCom.png")));
		lblNewLabel.setBounds(0, 0, 294, 407);
		frmPageDeConnexion.getContentPane().add(lblNewLabel);
	}

	public String recupererLogin() {
		return recLogin;

	}

}
