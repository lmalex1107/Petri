package view;


import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import model.*;
/**
 * Klasse zur graphischen Darstellung eines Petrinetzes
 * @author Alexander Adams
 *
 */
public class PetriGraph extends MultiGraph {
	
	private static String CSS_FILE = "url(" + PetriGraph.class.getResource("/graph.css") + ")";	//Test
	
	
	ArrayList<Knot> knotlist;
	ArrayList<Arc> arclist;
	/**
	 * 
	 * Erstellt ein neues Petrinetz mithilfe der übergebenen Listen der knoten und Kanten
	 */
	public PetriGraph (ArrayList<Knot> knotlist, ArrayList<Arc> arclist) {
		super("");
		
		this.knotlist = knotlist;
		this.arclist = arclist;
		loadGraph (this.knotlist, this.arclist);
	}
	/**
	 * Erstellt das Petrinetz, indem alle Knoten erstellt und mittels Kanten verbunden werden.
	 * @param knotlist 
	 * @param arclist
	 */
	public void loadGraph(ArrayList<Knot> knotlist, ArrayList<Arc> arclist) {
		
		this.clear();
		this.addAttribute("ui.stylesheet", CSS_FILE);
		this.knotlist = knotlist;
		this.arclist = arclist;
		if (this.knotlist != null) {
			for (int i = 0; i < this.knotlist.size(); i++) {
				String label = new String();
				String sortofnode = new String();
				String Selection = new String();
				String tokens = new String();
				System.out.println("Einfügen der Knoten in den Graphen");
				if (this.knotlist.get(i).getClass().toString().equals("class model.Place") == true) {
					label = "[" + this.knotlist.get(i).getId() + "] " + this.knotlist.get(i).getName() + " <" + this.knotlist.get(i).getToken() + ">";
					
					sortofnode = getNumberoftokens(i);
					
				}
				else {
					label = "[" + this.knotlist.get(i).getId() + "] " + this.knotlist.get(i).getName();
					sortofnode = "Transition";
				}
				
				tokens = Integer.toString(this.knotlist.get(i).getToken());
				

				
				Node node = this.addNode(knotlist.get(i).getId());
				node.addAttribute("ui.class", sortofnode);
				node.addAttribute("ui.label", label);
				node.addAttribute("id", this.knotlist.get(i).getId());
				node.addAttribute("tokens", tokens);
				node.addAttribute("xy", knotlist.get(i).getX(), knotlist.get(i).getY());
			
			}
		}
		
		if(this.arclist != null) {
			System.out.println("Knoten mittels Kanten verbinden");
			for (int i = 0; i < arclist.size(); i++) {
				String label = new String();
				label = "[" + this.arclist.get(i).getId() + "]";
				Edge edge = this.addEdge(arclist.get(i).getId(), arclist.get(i).getSource(), arclist.get(i).getTarget(), true);
				edge.addAttribute("ui.label", label);
			}
		}
		
		
	}
	
	/*
	 * Methode, mit der das richtige Bild mit der Anzahl der Marken ausgewählt werden kann
	 */
	private String getNumberoftokens (int i) {
		String numberoftokens;
		switch (this.knotlist.get(i).getToken()) {
		case 0: numberoftokens = "Place";
				break;
		case 1: numberoftokens = "Place_one";
				break;
		case 2: numberoftokens = "Place_two";
				break;
		case 3: numberoftokens = "Place_three";
				break;
		case 4: numberoftokens = "Place_four";
				break;
		case 5: numberoftokens = "Place_five";
				break;
		case 6: numberoftokens = "Place_six";
				break;
		case 7: numberoftokens = "Place_seven";
				break;
		case 8: numberoftokens = "Place_eight";
				break;
		case 9: numberoftokens = "Place_nine";
				break;
		default: numberoftokens = "Place_more";
				break;
		};
		return numberoftokens;
	}
	/**
	 * Geht alle Knoten des Petrigraphen durch und überprüft, ob sich etwas veränder hat.
	 * @param knotlist
	 */
	public void changeGraph(ArrayList<Knot> knotlist) {		//Das Petrinet durchlaufen und schauen, was sich verändert hat. Nur diese geänderten Stellen auch in der Derstellung verändern.
		this.knotlist = knotlist;
		String placeselect ="";
		String numberoftokens;
		for (int i = 0; i < this.knotlist.size(); i++) {
			
			if (this.knotlist.get(i).getClass().toString().equals("class model.Place")){
				//Welche Stelle wurde selektiert?
				if ((this.knotlist.get(i).getSelection() == true)) {		
					placeselect = "Placeselect";
				}
				if ((this.knotlist.get(i).getSelection() == false))  {			
						placeselect = "Place";
				}
				//wie viele Marke liegen auf dieser Stelle?
				numberoftokens = getNumberoftokens(i);						
				String classString = numberoftokens + ", " + placeselect;
				if (this.getNode(this.knotlist.get(i).getId()).getAttribute("ui.class").equals(classString)){
				
				}
				else {
					this.getNode(this.knotlist.get(i).getId()).removeAttribute("ui.class");
					this.getNode(this.knotlist.get(i).getId()).addAttribute("ui.class", classString);
				}
				//Wie lautet die Bezeichnung der Stelle?
				String newlabel = "[" + this.knotlist.get(i).getId() + "] " + this.knotlist.get(i).getName() + " <" + this.knotlist.get(i).getToken() + ">"; 
				if (this.getNode(this.knotlist.get(i).getId()).getAttribute("ui.label").equals(newlabel)){
					
				}
				else {
					this.getNode(this.knotlist.get(i).getId()).removeAttribute("ui.label");
					this.getNode(this.knotlist.get(i).getId()).addAttribute("ui.label", newlabel);
				}	
		}
			
			
			
			if ((this.getNode(this.knotlist.get(i).getId()).getAttribute("ui.class") == "Transition") && (this.knotlist.get(i).isfiringpossible() == true)){
				//this.getNode(this.knotlist.get(i).getId()).removeAttribute("ui.class");
				this.getNode(this.knotlist.get(i).getId()).addAttribute("ui.class", "firingpossible");
			}
			
			if ((this.getNode(this.knotlist.get(i).getId()).getAttribute("ui.class") == "firingpossible") && (this.knotlist.get(i).isfiringpossible() == false)){
				this.getNode(this.knotlist.get(i).getId()).removeAttribute("ui.class");
				this.getNode(this.knotlist.get(i).getId()).addAttribute("ui.class", "Transition");
			}
			
			
			
			
		}
	}
	
	
}
