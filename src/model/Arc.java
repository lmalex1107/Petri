package model;


/**
 * Klasse zur Definition der Kante eines Petrinetzes
 * @author Alexander Aams
 *
 */
public class Arc {
	private String id;
	private String source;
	private String target;
	
	/**
	 * Konstruktion einer Kante mit Identifikationsnummer
	 * @param id Identifikationsnummer
	 */
	public Arc(String id) {
		this.id = id;
	}
	
	/**
	 * Rückgabe der Identifikationsnummer des Knoten
	 * @return Identifikationsnummer
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setzen der Identifikationsnummer des Quellknotens
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Rückgabe der Identifikationsnummer des Zielknotens
	 * @return Identifikationsnummer des Zielknotens
	 */
	public String getTarget() {
		return target;
	}
	
	
	/**
	 * Setzen der Identifikationsnummer der Kante
	 * @param id Ientifikationsnummer
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * legt den Quellknoten fest
	 * @param source Identifikationsnummer des Quellknotens
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * legt den Zielknoten fest
	 * @param target Identifikationsnummer des Zielknotens
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
}
