package view;

import java.util.ArrayList;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import model.Arc;
import model.ERArc;
import model.ERKnot;
import model.Knot;

/**
 * Klasse zur graphischen Darstellung eines Erreichbarkeitsgraphen. Sie ist von der Klasse MultiGraph
 * des Graphstream-Paketes abgeleitet
 * @author alex
 *
 */
public class Erreichbarkeitsgraph extends MultiGraph{
	
	private static String CSS_FILE = "url(" + PetriGraph.class.getResource("/graph.css") + ")";
	
	ArrayList<ERKnot>erknotlist = null;
	ArrayList<ERArc>erarclist = null;

	/**
	 * Der Konstruktor, der einen neuen Graphen anlegt
	 */
	public Erreichbarkeitsgraph() {
		super("");
		this.addAttribute("ui.stylesheet", CSS_FILE);
	}
	
	/**
	 * Diese Methode fügt neue Knoten in den Erreichbarkeitsgraphen ein und 
	 * überprüft, was sich an den bereits vorhandenen Knoten des Erreichbarkeitsnetzes verändert hat. 
	 * @param erknotlist Liste mit den Knoten des Erreichbarkeitsgraphen
	 * @param erarclist Liste mit den Kanten des Erreichbarkeitsgraphen
	 */
	public void changeGraph (ArrayList<ERKnot> erknotlist, ArrayList<ERArc> erarclist) {
		this.addAttribute("ui.stylesheet", CSS_FILE);
		this.erknotlist = erknotlist;
		this.erarclist = erarclist;
		
		for (int i = 0; i < erknotlist.size(); i++) {
			
			if (this.getNode(this.erknotlist.get(i).getLabel()) == null) {			//Einfügen neuer Knoten in den ER-Graphen
				Node node = this.addNode(erknotlist.get(i).getLabel());				
				node.addAttribute("ui.label", erknotlist.get(i).getLabel());
				node.addAttribute("id", this.erknotlist.get(i).getId());
				node.addAttribute("ui.class", "ernode");		
			}
			
			if (this.erknotlist.get(i).getSelection() == true) {					//Darstellung bei Anklicken des Knotens
				this.getNode(this.erknotlist.get(i).getLabel()).removeAttribute("ui.class");
				this.getNode(this.erknotlist.get(i).getLabel()).addAttribute("ui.class", "erselect");
			}
			
			if (this.erknotlist.get(i).getSelection() == false) {
				if (this.erknotlist.get(i).getknot_mx() == 1 ) { 						//Darstellung des Knotens m
					this.getNode(this.erknotlist.get(i).getLabel()).removeAttribute("ui.class");
					this.getNode(this.erknotlist.get(i).getLabel()).addAttribute("ui.class", "ernodem1"); 
				}
				if (this.erknotlist.get(i).getknot_mx() == 2 ) {						//Darstellung des Knotens m'
					this.getNode(this.erknotlist.get(i).getLabel()).removeAttribute("ui.class");
					this.getNode(this.erknotlist.get(i).getLabel()).addAttribute("ui.class", "ernodem2");
				}
				if (this.erknotlist.get(i).getInitialKnot() == true) {					//Darstellung des Anfangsknotens
					this.getNode(this.erknotlist.get(i).getLabel()).removeAttribute("ui.class");
					this.getNode(this.erknotlist.get(i).getLabel()).addAttribute("ui.class", "initialknot");
				}
				
				if ((this.erknotlist.get(i).getknot_mx() == 0) && (this.erknotlist.get(i).getInitialKnot() == false)) {
					this.getNode(this.erknotlist.get(i).getLabel()).removeAttribute("ui.class");
					this.getNode(this.erknotlist.get(i).getLabel()).addAttribute("ui.class", "ernode");
				}
			}
			
			
			
			
			
			
		}
		
		
		for (int j = 0; j < this.erarclist.size(); j++) {				//Neue Kante dann einfügen, wenn noch keine Kante mit dieser
			String label = this.erarclist.get(j).getId();				//ID vorhanden ist oder, wenn ID zwar bereits vorhanden ist,
			String source = this.erarclist.get(j).getSource() ;			//aber andere Knoten miteiander verbindet.
			String target = this.erarclist.get(j).getTarget();
			String id = Integer.toString(j);
			if ((this.getEdge(id) != null) && ((this.getEdge(id).getSourceNode().hasAttribute(source) == false) && (this.getEdge(id).getTargetNode().hasAttribute(target) == false))){
			}
			else {
			   Edge edge = this.addEdge(id, source, target, true);
				edge.addAttribute("ui.label", label);	
			}
		}
		for (int k = 0; k < this.erarclist.size(); k++) {					//Hervorhebung des gefundenen Pfades 
			if (this.erarclist.get(k).isPartOfInfinityPath() == true) {
				this.getEdge(Integer.toString(k)).addAttribute("ui.class", "infinitypath");
			}
		}
		
		System.out.println("Kanten-Test");												//Test
		for (int j = 0; j < this.erarclist.size(); j++) {								//Test
			System.out.println("ID: " + this.erarclist.get(j).getId());					//Test
			System.out.println("Quelle: " + this.erarclist.get(j).getSource());			//Test
			System.out.println("Ziel: " + this.erarclist.get(j).getTarget());			//Test
		}																				//Test
		System.out.println("Ende Kanten-Test");											//Test
		
		
	}
	
	
	
}
