package control.file;

import propra.pnml.*;
import model.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Klasse mit deren Hilfe eine pnml-Datei ausgelesen werden kann
 * @author Alexander Adams
 *
 */
public class Parser extends PNMLWopedParser {
	
	ArrayList<Knot> knotlist;
	ArrayList<Arc> arclist;
	Knot knot;
	Arc arc;
	
	public Parser(final File pnml) {
		super(pnml);
		knotlist = new ArrayList<Knot>();
		arclist = new ArrayList<Arc>();
		
		
	}
	/**
	 * Gibt eine Liste mit allen Knoten des ausgelesenen Petrinetzes zurück. 
	 * @return Petrinetz
	 */
	public ArrayList<Knot> getKnotList(){
		return this.knotlist;
	}
	
	/**
	 * Gibt eine Liste mit allen Kanten des ausgelesenen Petrinetzes zurück.
	 * @return
	 */
	public ArrayList<Arc> getArcList(){
		return arclist;
	}
	
	/**
	 * Legt einen neuen Transitor an. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void newTransition (final String id) {
		knot = new Transition(id);
		System.out.println("ID: " + id);
	}
	
	/**
	 * Legt eine neue Stelle an. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void newPlace (final String id) {
		knot = new Place(id);
		System.out.println("ID: " + id);
	}
	
	/*
	 * Legt eine neue Kante an. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void newArc (final String id, final String source, final String target) {
		arc = new Arc(id);
		arc.setSource(source);
		arc.setTarget(target);
		arclist.add(arc);
	}
	
	/**
	 * Weist einem Knoten eine bestimmte POsition zu. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void setPosition(final String id, final String x, final String y) {
		
		knot.setX(Integer.parseUnsignedInt(x));
		knot.setY(0 - Integer.parseUnsignedInt(y));
		System.out.println("Position x: " + x);
		System.out.println("Position y: " + y);
		knotlist.add(knot);
	}
	
	/*
	 * Setzt den Namen eines Elements. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void setName(final String id, final String name){
		knot.setName(name);
		System.out.println("Name: " + name);
	}
	
	/**
	 * Legt die Anzahl der Marken einer Transition fest. Diese Methode wurde aus der bereitgestellten 
	 * Klasse 'Parser' übernommen und überschrieben.
	 */
	@Override
	public void setTokens(final String id, final String tokens) {
		knot.plusToken(Integer.parseInt(tokens));
		
	}
	
	
}
