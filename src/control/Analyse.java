package control;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextArea;

import control.file.Parser;
import model.Arc;
import model.Erreichbarkeitsnet;
import model.Knot;
import model.PetriNet;

/**
 * Klasse zur Steuerung der Beschränktheitsanalyse
 * @author Alexander Adams
 *
 */
public class Analyse {
	
	ArrayList<String> textlist;
	File[] xmlfiles;
	File xmlfile;
	
	ArrayList<String> SolutionStrings;
	JTextArea textarea;
	
	PetriNet petrinet;
	Erreichbarkeitsnet ernet;
	
	/**
	 * Konstruktor der Klasse Analyse, um (per Stapelverarbeitung) mehrere Dateien zu analysieren
	 * @param xmlfiles
	 * @param textarea
	 */
	public Analyse(File[] xmlfiles, JTextArea textarea) {
		this.xmlfiles = xmlfiles;
		this.textarea = textarea;
		Arrays.sort(xmlfiles);
	}
	
	/**
	 * Konstruktor der Klasse Analyse, um eine einzige Datei zu analysieren
	 * @param xmlfile
	 * @param textarea
	 */
	public Analyse (File xmlfile, JTextArea textarea) {
		this.xmlfile = xmlfile;
		this.textarea = textarea;
	}
	
	/**
	 * gib dieses 
	 * @return
	 */
	public PetriNet getPetriNet() {
		return this.petrinet;
	}
	
	/**
	 * Diese Methode gibt den berechneten Erreichbarkeitsgraphen zurück, unabhängig davon, ob
	 * dieser endlich oder unendlich (also nur partiell vorhanden) ist.
	 * @return
	 */
	public Erreichbarkeitsnet getERNet() {
		return this.ernet;
	}
	
	/**
	 * Durchführung der Analyse für mehrere Petrinetze, kann in Form einer Stapelverarbeitung aufgerufen werden.
	 */
	public void analyse_ALL() {
		ArrayList<String> StringList = new ArrayList<String>();
		String header1 = String.format("%-60s", "" + "|" + String.format("-15s", "") + "|" + "" + "|" + " Kanten / Knoten bzw.");
		String header2 = String.format("%-60s", "Dateiname") + "|" + String.format("%-15s", " beschränkt") + "|" + " Pfadlänge:Pfad; m, m'" + "\n";
		StringList.add(header2);
		for (int i = 0; i < xmlfiles.length; i++) {													//Test
			System.out.println("         ++++++++--------+++++++");									//Test
			System.out.println(xmlfiles[i].getName() + "  started...");								//Test
			String startString = new String (xmlfiles[i].getName() + "    wird analysiert \n");
			this.textarea.append(startString);
			Parser parser = new Parser (xmlfiles[i]);										
			parser.initParser();
			parser.parse();
			ArrayList<Knot> knotlist = parser.getKnotList();
			ArrayList<Arc> arclist = parser.getArcList();
			this.petrinet = new PetriNet(knotlist, arclist);
			this.petrinet.firing_all(this.petrinet.getMarking());
			String SolutionString = new String("");
			if (this.petrinet.isInfinity() == true) { //Konstruktion des Ausgabestrings für einen endlichen Graphen
				String name = String.format("%-60s", xmlfiles[i].getName());
				String result = String.format("%-15s", " ja");
				String numbers =  Integer.toString(this.petrinet.getERNet().getNumberOfKnots()) + " / " + Integer.toString(this.petrinet.getERNet().getNumberOfArcs());
				numbers.format("%-80s", numbers);
				SolutionString = name + "|" + result + "| " + numbers + "\n";
				this.textarea.append("Der zugehörige Erreichbarkeitsgraph ist beschränkt. \n \n");
				System.out.println(SolutionString);
			} 
			
			else {															//Konstruktion der Ausgabe für einen unendlichen Graphen
				SolutionString = SolutionString + xmlfiles[i].getName() + "   nein ";
				String name = String.format("%-60s", xmlfiles[i].getName());
				String result = String.format("%-15s", " nein");
				String numbers = Integer.toString(this.petrinet.getERNet().getArcToInfinity().size()) + ":(";
				for (int j = this.petrinet.getERNet().getArcToInfinity().size() - 1; j > 0; j--) {
					numbers = numbers + this.petrinet.getERNet().getArcToInfinity().get(j).getId() + ",";
				}
				numbers = numbers + this.petrinet.getERNet().getArcToInfinity().get(0).getId() + ");";
				numbers = String.format("%-30s", numbers);
				String m1 = String.format("%-25s", this.petrinet.getERNet().getInfinityKnots()[1].getLabel());
				String m2 = String.format("%-25s", this.petrinet.getERNet().getInfinityKnots()[0].getLabel());
				SolutionString = name + "|" + result + "| " + numbers +"|" + m1 + m2 + "\n";
				this.textarea.append("Der zugehörige Erreichbarkeitsgraph ist unbeschränkt. \n \n");
				System.out.println(SolutionString);
			}
			StringList.add(SolutionString);
			
		}
		
		this.textarea.append("");
		for (int j = 0; j < StringList.size(); j++) {
			this.textarea.append(StringList.get(j));
		}
		
	}
	
	
	/**
	 * Durchführung der Analyse für ein Petrinetz
	 * @return liefert als Ergebnis true, wenn der Erreichbarkeitsgraph des entsprechenden Petrinetzes unbeschränkt ist
	 * @return und false, wenn es sich um einen unbegrenzten Erreichbarkeitsgraphen handelt.
	 */
	public boolean analyse_ONE(PetriNet petrinet) {
		this.petrinet = petrinet;
		this.petrinet.firing_all(petrinet.getInitialMarking()); 
		this.ernet = this.petrinet.getERNet();
		return this.petrinet.isInfinity();
	}
	
	/**
	 * Rückgabe des verschriftlichten Ergebnisses der Analyse
	 * @return Text mit dem Ergebnis der Analyse
	 */
	public ArrayList<String> getSolutionStrings () {
		return this.SolutionStrings;
	}
	
	
}
