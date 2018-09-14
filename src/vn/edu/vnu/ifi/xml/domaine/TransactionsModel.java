/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vn.edu.vnu.ifi.xml.dao.IDao;
import vn.edu.vnu.ifi.xml.service.IService;

/**
 * @author KENGNI Hippolyte
 *
 */
public class TransactionsModel extends AbstractTableModel {

	// D�claration des propri�t�s et variables de class

	private final String[] entetes = {"ID", "N� Compte", "Date", "Type de transaction", "Montant", "Gestionnaire"};
	private List<Transactions> maListeTransactions;
	private IService service;
	private IDao dao;

	/**
	 * Constructeur de la class ComptesModel
	 * 
	 * @param maListeOperations
	 */
	public TransactionsModel(List<Transactions> maListeTransactions) {
		super();
		this.setMaListeOperations(maListeTransactions);
	}

	/**
	 * Cette m�thode permet d'obtenir une liste d'op�ration
	 * @return the maListeOperations
	 */
	public List<Transactions> getMaListeTransactions() {
		return maListeTransactions;
	}

	/**
	 * Cette m�thode permet de modifier une liste d'op�ration
	 * @param maListeOperations the maListeOperations to set
	 */
	public void setMaListeOperations(List<Transactions> maListeTransactions) {
		this.maListeTransactions = maListeTransactions;
	}


	/**
	 * @return taille de l'entete
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return entetes.length;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 * @return entetes[columnIndex]
	 * @param columnIndex
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 * @return la taille de la liste
	 */
	public int getRowCount() {
		return maListeTransactions.size();
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 * @param rowIndex
	 *            et columnIndex
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		// id de la transaction
		case 0:
			return maListeTransactions.get(rowIndex).getIdTransaction();

		case 1:
			// N� de compte de la transaction
			return maListeTransactions.get(rowIndex).getCompte();

		case 2:
			// Date de la transaction
			return maListeTransactions.get(rowIndex).getDate();

		case 3:
			// Le type de transaction (Cr�dit | D�bit)
			return maListeTransactions.get(rowIndex).getTypeTransaction();
		case 4:
			// Montant de la transaction
			return maListeTransactions.get(rowIndex).getMontant();
			
		case 5:
			// Gestionnaire de la transaction (celui qui � effectu� la transaction)
			return maListeTransactions.get(rowIndex).getGestionnaires_idGestionnaires();

		default:
			throw new IllegalArgumentException();

		}
	}

}
