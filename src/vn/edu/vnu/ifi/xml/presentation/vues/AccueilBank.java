package vn.edu.vnu.ifi.xml.presentation.vues;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class AccueilBank extends JInternalFrame {


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AccueilBank() {	

		setFrameIcon(new ImageIcon(AccueilBank.class.getResource("/img/icons8_Merchant_Account_90px.png")));
		setTitle("Accueil Promo22Bank");
		setBounds(100, 100, 931, 505);
		setBorder(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AccueilBank.class.getResource("/img/imgAccueil.png")));
		lblNewLabel.setBounds(0, 0, 931, 505);
		getContentPane().add(lblNewLabel);
		

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AccueilBank.class.getResource("/img/fondInternalFrame.jpg")));
		label.setBounds(0, 0, 931, 505);
		getContentPane().add(label);
		setBounds(100, 100, 931, 505);

		// Appelle de la méthode pour supprimer la barre de titre
		remove_title_bar();

	}


	/**
	 * Cette méthode permet de supprimer la barre de titre sur mon InternalFrame
	 * 
	 */
	public void remove_title_bar() {
		putClientProperty("GestionAgences.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
	}
}
