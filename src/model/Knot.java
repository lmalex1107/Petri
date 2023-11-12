package model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Klasse zur Repräsentation eines Knotens des Petrinetzes
 * @author Alexander Adams
 *
 */
public class Knot implements Comparable<Knot>{
	private String id;
	private String name;
	private int x;
	private int y;
	private ArrayList<Knot> predecessor = null;
	private ArrayList<Knot> successor = null;
	private int token = 0;
	private boolean firingpossible = false; 
	
	private boolean Selection = false;
	
	/**
	 * legt einen neuen knoten an
	 * @param Identifikationsnummer
	 */
	public Knot (String id) {
		this.id = id;
		predecessor = new ArrayList<Knot>();
		successor = new ArrayList<Knot>();
	}
	
	/**
	 * gibt zurück, ob noch geschaltet werden kann.
	 */
	public boolean isfiringpossible() {
		return firingpossible;
	}
	
	/**
	 * Legt fest, ob noch geschaltet werden kann.
	 */
	public void setfiringpossible(boolean b) {
		this.firingpossible = b;
	}
	
	/**
	 * Gibt die Id des Knotens zurück.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Gibt den Namen des Knotens zurück.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt die X-Koordinate des Knotens zurück
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gibt die Y-Koordinate des Knotens zurück.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Setzt die ID des Knotens
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/*
	 * Legt den Namen des Knotens fest.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setzt die X-Koordinate des Knotens
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setzt die Y-Kordinate des Knotens
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Fügt der Liste der Vorgänger des Knotens ein Element hinzu.
	 */
	public void setPredecessor (Knot knot) {
		this.predecessor.add(knot);	
	}
	
	/**
	 * Gibt die Liste der Vorgänger zurück.
	 */
	public ArrayList<Knot> getPredecessorList(){
		return this.predecessor;
	}
	
	/**
	 * Fügt der Liste der Nachfolger des Knotens ein Element hinzu.
	 */
	public void setSuccessor (Knot knot) {
		this.successor.add(knot);
	}
	
	/**
	 * Gibt die Liste der Nachfolger zurück.
	 */
	public ArrayList<Knot> getSuccessorList(){
		return this.successor;
	}
	
	public int getToken() {				
		return -1;
	}
	
	
	public void plusToken(int token) {
		
	}
	
	
	
	/**
	 * Erhöht die Zahl der Marken des Knotens
	 */
	public void setToken(int token) {
		this.token = token;
	}
	
	/**
	 * Gibt zurück, ob Knoten markiert wurde
	 */
	public boolean getSelection() {
		return this.Selection;
	}
	
	/**
	 * Setzt die Markierung des Knotens
	 */
	public void setSelection (boolean selection)  {
		this.Selection = selection;
	}
	
	/**
	 * Methode zur alphabetischen Sortierung der Knoten
	 */
	@Override
	public int compareTo(Knot knot) {
		return this.getId().compareTo(knot.getId());
	}
}
