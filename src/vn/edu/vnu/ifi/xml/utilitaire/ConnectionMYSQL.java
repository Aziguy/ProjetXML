/**
 * 
 */
package vn.edu.vnu.ifi.xml.utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Cette Class permet de se connecter � la base de donn�es
 * 
 * @author KENGNI Hippolyte
 * @version 0.1 Android
 */
public class ConnectionMYSQL {

	// Information d'acc�s � la base de donn�es
	public static Connection connection;
	public static String ip = "127.0.0.1";
	public static String port = "3306";
	public static String dataBase = "xmlproject";
	public static String user = "root";
	public static String password = "01659948108313495";

	/**
	 * M�thode de connexion Elle ne prend pas de param�tre
	 * 
	 * @return connection
	 */
	public static Connection getInstance() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;

	}
}
