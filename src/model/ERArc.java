package model;

/**
 * Klasse der Kanten eines Erreichbarkeitsnetzes
 * @author Alexander Adams
 *
 */
public class ERArc {
	
	private String id;
	private String source;
	private String target;
	private boolean PartOfInfinityPath = false;	
	
	/**
	 * Erstellung einer neuen Kante des Erreichbarkeitsnetzes
	 * @param id Identifikationsnummer
	 */
	public ERArc(String id) {
			this.id = id;
	}
		
	/**
	 * Rückgabe der Identifikationsnummer
	 * @return Identifikationsnummer
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * liefert den Quellknoten
	 * @return Name des Quellknotens
	 */
	public String getSource() {
		return source;
	}
		
	/**
	 * iefert den Zielknoten
	 * @return Name des Zielknotens
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * Setzen der Identifikationsnummer des Knotens
	 * @param id Identifikationsnummer
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Setzen des Quellknotens der Kante
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Setzen des Zielknotens der Kante
	 * @param target Übergeben wird der Name des jeweiligen Zielknotens
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * zeigt an, ob Kanten Teil des Pfades ist, auf dem m und m' liegen 
	 * @return Pfad, auf dem m und m' liegen
	 */
	public boolean isPartOfInfinityPath() {
		return this.PartOfInfinityPath;
	}
	
	/**
	 * legt fest, dass es sich um den Pfad handelt, auf dem m und m'liegen
	 */
	public void setPartOfInfinityPath (boolean b) {
		this.PartOfInfinityPath = b;
	}
}
