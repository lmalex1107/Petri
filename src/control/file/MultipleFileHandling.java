package control.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Klasse zur Auswahl von Dateien
 * @author Alexander Adams
 *
 */
public class MultipleFileHandling {
	
	private File[] xmlfiles = null;		//Array, der die zu analysierenden Dateien enthält
	
	/**
	 * Konstruktor zum Aufruf eines Datei-Auswahl-Menüs
	 */
	public MultipleFileHandling() {
		JFileChooser jfilechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("pnml-Dateien", "pnml");
		jfilechooser.setFileFilter(filter);
		jfilechooser.setCurrentDirectory(new File ("../ProPra-WS20-Basis/Beispiele"));
		jfilechooser.setMultiSelectionEnabled(true);
		jfilechooser.showOpenDialog(null);
		xmlfiles = jfilechooser.getSelectedFiles();
	}
	
	/**
	 * Gibt die ausgewählten Dateien zurück.
	 * @return
	 */
	public File[] getFiles() {
		return this.xmlfiles;
	}
	
}
