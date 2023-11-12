package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import org.graphstream.graph.Node;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import control.Control;
import control.file.FileHandling;
import control.file.MultipleFileHandling;
import control.file.Parser;
import model.Arc;
import model.Knot;

/**
 * Klasse zur Erstellung der graphischen Benutzeroberfläche
 * @author Alexander Adams
 *
 */
public class GUI extends JFrame{
	File xmlfile;
	
	private PetriGraph petrigraph;
	private PetriGraph lastpetrigraph;
	
	private Erreichbarkeitsgraph ergraph;
	
	public JMenuItem oeffnen;
	public JMenuItem neu_laden;
	public JMenuItem analyse_ALL;
	public JMenuItem beenden;
	
	private ArrayList<Knot> knotlist;
	private ArrayList<Arc> arclist;
	
	private Viewer viewer;
	private ViewPanel viewpanel;
	private ViewerPipe viewerpipe;
	
	private Viewer erviewer;
	private ViewPanel erviewpanel;
	private ViewerPipe erviewerpipe;
	
	
	
	private JPanel centerpanel;
	private JPanel textpanel;
	private JSplitPane graphpanel;
	private JPanel symbolpanel;
	private JPanel petripanel;
	private JPanel erpanel;
	private JScrollPane scrollpane;
	public JTextArea textarea;
	private JSplitPane mainpanel;
	

	public JButton analyse_ONE;
	public JButton EG_loeschen;
	public JButton reset;
	public JButton plus;
	public JButton minus;
	public JButton text_loeschen;

	
	
	/**
	 * Aufbau der grafischen Benutzeroberfläche und Initialisierung aller dafür notwendigen Funktionen.
	 * @param control
	 * @param knotlist
	 * @param arclist
	 * @param petrigraph
	 * @param ergraph
	 */
	public GUI (Control control, ArrayList<Knot> knotlist, ArrayList<Arc> arclist, PetriGraph petrigraph, Erreichbarkeitsgraph ergraph) {
		super("Alexander Adams q9264825");
		this.knotlist = knotlist;
		this.arclist = arclist;
		this.petrigraph = petrigraph;
		this.ergraph = ergraph;
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		lastpetrigraph = null;
		
		JMenuBar menubar = new JMenuBar();
		JMenu datei = new JMenu("Datei");
		oeffnen = new JMenuItem ("Öffnen");
		oeffnen.addActionListener(control);
		neu_laden = new JMenuItem ("Neu laden");
		neu_laden.addActionListener(control);
		analyse_ALL = new JMenuItem ("Analyse mehrerer Dateien");
		analyse_ALL.addActionListener(control);
		beenden = new JMenuItem ("Beenden");
		beenden.addActionListener(control);
		datei.add(oeffnen);
		datei.add(neu_laden);
		datei.add(analyse_ALL);
		datei.add(beenden);
		menubar.add(datei);
		this.add(menubar, BorderLayout.NORTH);
		
		viewer = new Viewer(petrigraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.disableAutoLayout();
		viewpanel = viewer.addDefaultView(false);
		viewpanel.addMouseListener(control);
		viewerpipe = viewer.newViewerPipe();
		viewerpipe.addAttributeSink(petrigraph);
		viewerpipe.addViewerListener(control);
		//this.add(viewpanel, BorderLayout.CENTER);
		
		petripanel = new JPanel();
		petripanel.setLayout(new BorderLayout());
		petripanel.add(viewpanel, BorderLayout.CENTER);
		
		erviewer = new Viewer(ergraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		erviewer.enableAutoLayout();
		erviewpanel = erviewer.addDefaultView(false);
		erviewpanel.addMouseListener(control);
		erviewerpipe = erviewer.newViewerPipe();
		erviewerpipe.addAttributeSink(ergraph);
		erviewerpipe.addViewerListener(control);
		
		erpanel = new JPanel();
		erpanel.setLayout(new BorderLayout());
		erpanel.add(erviewpanel, BorderLayout.CENTER);
		
		graphpanel = new JSplitPane();							//Hier werden die Panels der beiden Graphen eingefügt
		graphpanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		graphpanel.setLeftComponent(petripanel);
		graphpanel.setRightComponent(erpanel);
		graphpanel.setVisible(true);
		graphpanel.setResizeWeight(0.5d);
		
		
					
		analyse_ONE = new JButton("Analyse");					//Einfügen der Buttons der Toolbar
		analyse_ONE.addActionListener(control);
		EG_loeschen = new JButton("EG löschen");
		EG_loeschen.addActionListener(control);
		reset = new JButton("Reset");
		reset.addActionListener(control);
		plus = new JButton("+1");
		plus.addActionListener(control);
		minus = new JButton("-1");
		minus.addActionListener(control);
		text_loeschen = new JButton("Text löschen");
		text_loeschen.addActionListener(control);
		symbolpanel = new JPanel();
		symbolpanel.add(minus);
		symbolpanel.add(analyse_ONE);
		symbolpanel.add(EG_loeschen);
		symbolpanel.add(reset);
		symbolpanel.add(text_loeschen);
		symbolpanel.add(plus);
		
		centerpanel = new JPanel();
		centerpanel.setLayout(new BorderLayout());
		centerpanel.add(symbolpanel, BorderLayout.NORTH);
		centerpanel.add(graphpanel, BorderLayout.CENTER);
		
		
		textpanel = new JPanel();									//Erstellen des Textbereichs
		textpanel.setLayout(new BorderLayout());
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);
		textarea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		textarea.append("Petrinetze und Erreichbarkeitsgraphen Alexander Adams, Fernuni-Hagen (q9264825), Version 0.7" + "\n" + "\n");
		scrollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textpanel.add(scrollpane, BorderLayout.CENTER);
		
		mainpanel = new JSplitPane();
		mainpanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainpanel.setTopComponent(centerpanel);
		mainpanel.setBottomComponent(textpanel);
		mainpanel.setVisible(true);
		mainpanel.setResizeWeight(0.7d);
		
		
		this.add(mainpanel, BorderLayout.CENTER);
		
		
		this.setVisible(true);
		
		
	}
	
	/**Diese Methode lädt den Petrigraphen aus der gewählten Datei und zeigt ihn im Anschluss daran an.
	 *
	 * @param petrigraph
	 * @return
	 */
	public PetriGraph open(PetriGraph petrigraph) {
		//this.petrigraph = petrigraph;
		//lastpetrigraph = this.petrigraph;
		FileHandling filehandling = new FileHandling();
		this.xmlfile = filehandling.getFile();
		if (xmlfile != null) {
			System.out.println("Test 1");
			Parser parser = new Parser (xmlfile);
			parser.initParser();
			parser.parse();
			this.knotlist = parser.getKnotList();
			this.arclist = parser.getArcList();
			petrigraph.loadGraph(this.knotlist, this.arclist);
			ergraph.clear();
			
			System.out.println(System.identityHashCode( petrigraph ));	//Test
		}
		return petrigraph;
	}
	/**
	 * Methode zum Laden von dateien, um sie im Anschluss analysieren zu können
	 */
	public File[] analyse_ALL() {
		File[] xmlfiles = null;
		MultipleFileHandling multiplefilehandling = new MultipleFileHandling();
		xmlfiles = multiplefilehandling.getFiles();
		return xmlfiles;
	}
	
	/**
	 * Öffnet erneut die Datei mit dem Petrinetz und lässt es darstellen.
	 */
	public PetriGraph reopen(PetriGraph petrigraph) {
		if (xmlfile != null) {
			System.out.println("Test 1");
			Parser parser = new Parser (xmlfile);
			parser.initParser();
			parser.parse();
			this.knotlist = parser.getKnotList();
			this.arclist = parser.getArcList();
			ergraph.clear();
			
			System.out.println(System.identityHashCode( petrigraph ));	//Test
		}
		return petrigraph;
	}
	
	/**
	 * Gibt die Liste mit den Knoten des Petrinetzes zurück.
	 */
	public ArrayList<Knot> getKnotList(){
		return this.knotlist;
	}
	
	/**
	 * Rückgabe der Liste mit den Kanten.
	 */
	public ArrayList<Arc> getArcList(){
		return this.arclist;
	}
	
	/**
	 * Gibt den Petrigraphen zurück
	 */
	public PetriGraph getPetriGraph() {
		return this.petrigraph;
	}
	
	
	
	/**
	 * Gibt die geladene Datei zurück.
	 */
	public File getXmlFile () {
		return this.xmlfile;
	}
	
	/**
	 *Beendet das Programm ordnungsgemäß 
	 */
	public void exit( ) {
		System.exit(0);
	}
	
	/**
	 * Methode, die permanent überprüft, ob sich an den Graphen etwas geändert hat.
	 */
	public void pump() {
		if (viewerpipe != null) {
			viewerpipe.pump();
		}
		if (erviewerpipe != null) {
			erviewerpipe.pump();
		}
	}
	/**
	 * Löscht das Textfeld
	 */
	public void delete_text () {
		this.textarea.setText("");
	}
	
	/**
	 * Diese Methode gibt das Ergenis des Beschränktheitsanalyse in einem kleinen Dialogfenster aus.
	 */
	public void showResult(String []outputString) {
		JDialog jdialog = new JDialog();
		jdialog.setTitle(outputString[0]);
		jdialog.setSize(250,100);
		jdialog.add (new JLabel (outputString[1]));
		jdialog.setVisible(true);
	}
		
}
