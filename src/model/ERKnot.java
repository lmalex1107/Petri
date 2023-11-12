package model;

import java.util.ArrayList;

/**
 * Klasse zur Definition von Knoten des Erreichbarkeitsnetzes
 * @author Alexander Adams
 *
 */
public class ERKnot {

	private int[] Marking;
	private String id;
	private ArrayList<ERKnot> predecessor = null;
	private ArrayList<ERKnot> successor = null;
	private String label;
	private boolean PartOfInfinityPath = false;
	private int knot_mx = 0;			//0 = kein besonderer Knoten, 1 = m - Knoten, 2 = m' - Knoten
	private boolean initial_knot = false;
	private boolean Selection;
	
	public ERKnot(int[] Marking) {
		this.Marking = Marking;
		this.label = MarkingToString(Marking);
		predecessor = new ArrayList<ERKnot>();
		successor = new ArrayList<ERKnot>();
		
	}
	
	/**
	 * Setzt den Anfangsknoten
	 * @param b Anfangsknoten (initial_knot)
	 */
	public void setInitialKnot (boolean b) {
		this.initial_knot = b;
	}
	
	/**
	 * Gibt den Anfangsknoten zurück
	 * @return Anfangsknoten (initial_knot)
	 */
	public boolean getInitialKnot () {
		return this.initial_knot;
	}
	
	/**
	 * Gibt zurück, ob ein Knoten ausgewählt wurde oder nicht
	 * @return Selction
	 */
	public boolean getSelection () {
		return this.Selection;
	}
	
	/**
	 * verändert die Selection des Knotens, d. h., er ist nun entweder markiert oder nicht
	 * @param b Wahrheitswert zum Setzen der Selection
	 */
	public void setSelection (boolean b) {
		this.Selection = b;
	}
	
	/**
	 * wandelt eine gegeben Markierung in Text um
	 * @param Marking Markierung in nummerischer Form
	 * @return String, der Markierung in Textform enthält
	 */
	public static String MarkingToString(int[] Marking) {
		String label = "(";
		for (int i = 0; i < Marking.length; i++) {
			label = label + Integer.toString(Marking[i]);
			label = label + "|";
		}
		label = label + ")";
		return label;
	}
	
	/**
	 * gibt das Label des Knotens zurück. Ist in diesem Fall gleich der id.
	 * @return Label des Knotens
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * gibt die Identifikationsnummer des Knotens zurück. Ist in diesem Fall gleich dem Label.
	 * @return
	 */
	public String getId() {
		return this.label;
	}
	
	/**
	 * gibt die Markierung des Knotens in nummerischer Form zurück
	 * @return Markierung
	 */
	public int[] getMarking() {
		return this.Marking;
	}
	
	/**
	 * fügt der Liste der Vorgänger einen Knoten hinzu
	 * @param erknot Knoten, der eingefügt wird
	 */
	public void setPredecessor(ERKnot erknot) {
		this.predecessor.add(erknot);
	}
	
	/**
	 * gibt die Vorgänger-Liste zurück
	 * @return Liste der Vorgänger
	 */
	public ArrayList<ERKnot> getPredecessor(){
		return this.predecessor;
	}
	
	/**
	 * fügt der Liste der Nachfolger einen Knoten hinzu
	 * @param erknot Knoten, der eingefügt wird
	 */
	public void setSuccessor(ERKnot erknot) {
		this.successor.add(erknot);
	}
	
	/**
	 * gibt die Liste mit den Vorgängern zurück
	 * @return Liste mit Vorgängern
	 */
	public ArrayList<ERKnot> getSuccessor(){
		return this.successor;
	}
	
	/**
	 * gibt an, ob der Knoten Teil des Pfades ist, auf dem m und m' liegen
	 * @return Wahrheitswert
	 */
	public boolean isPartOfInfinityPath() {
		return PartOfInfinityPath;
	}
	
	/**
	 * fügt diesen Knoten dem Pfad hinzu, auf dem m und m' liegen
	 * @param Wahrheitswert
	 */
	public void setPartOfInfinityPath(boolean b) {
		this.PartOfInfinityPath = b;
	}
	
	/**
	 * Testen der Markierung
	 * @return Markierung
	 */
	public String Testmarking() {
		String temp = "";
		for (int i = 0; i < this.Marking.length; i++) {
			temp = temp + " " + this.Marking[i];
		}
		return temp;
	}
	
	/**
	 * gibt zurück, ob es sich um den Knoten m, m' oder einen normalen Knoten handelt
	 * @return Zahl (0 = normaler Knoten, 1 = m', 2 = m
	 */
	public int getknot_mx () {
		return knot_mx;
	}
	
	/**
	 * legt fest, ob es sich den Knoten m, m' oder einen normalen Knoten handelt
	 * @param v Zahl (0 = normaler Knoten, 1 = m', 2 = m
	 */
	public void setknot_mx (int v) {
		this.knot_mx = v;
	}
}
