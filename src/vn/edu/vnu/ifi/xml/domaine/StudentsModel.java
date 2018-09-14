/**
 * 
 */
package vn.edu.vnu.ifi.xml.domaine;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * @author KENGNI Hippolyte
 *
 */
public class StudentsModel extends AbstractTableModel {

	// D�claration des propri�t�s et variables de class
	private final String[] entetes = { "Matricule", "Noms", "Pr�noms", "Date de Naissance", "Lieu de Naissance", "Adresse", "T�l�phone", "Niveau", "Police" };
	private Vector<Object> maListeStudents;

	/**
	 * Constructeur de la class StudentsModel
	 * 
	 * @param vector
	 */
	public StudentsModel(Vector<Object> vector) {
		super();
		this.maListeStudents = vector;
	}

	/**
	 * Cette m�thode permet d'obtenir une liste de students
	 * 
	 * @return the maListeStudents
	 */
	public Vector<Object> getMaListeStudents() {
		return maListeStudents;
	}

	/**
	 * Cette m�thode permet de modifier une liste de student
	 * 
	 * @param maListeStudents
	 *            the maListeStudents to set
	 */
	public void setMaListeStudents(Vector<Object> maListeStudents) {
		this.maListeStudents= maListeStudents;
	}

	/**
	 * @return taille de l'entete
	 */
	public int getColumnCount() {
		return entetes.length;
	}

	/**
	 *
	 * @return entetes[columnIndex]
	 * @param columnIndex
	 */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	/**
	 * @return la taille de la liste
	 */
	public int getRowCount() {
		return maListeStudents.size() / getColumnCount();
	}

	/**
	 * @param rowIndex et columnIndex
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (String) maListeStudents.elementAt((rowIndex * getColumnCount())
				+ columnIndex);
//		switch (columnIndex) {
//		
//		// Maticule de l'�tudiant
//		case 0:
//			return ((Students)maListeStudents.get(rowIndex)).getMatricule();
//
//		case 1:
//			// Noms	 de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getNoms();
//
//		case 2:
//			// pr�noms de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getPrenoms();
//
//		case 3:
//			// date de naissance de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getDateNaissance();
//			
//		case 4:
//			//lieu de naissance de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getLieuNaissance();
//
//		case 5:
//			// adresse de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getAdresse();
//
//		case 6:
//			// t�l�phone de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getTelephone();
//
//		case 7:
//			// niveau de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getNiveau();
//			
//		case 8:
//			// police d'assurance de l'�tudiant
//			return ((Students) maListeStudents.get(rowIndex)).getPolice();	
//
//		default:
//			throw new IllegalArgumentException();
//
//		}
	}
}
