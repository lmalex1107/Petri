package control.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Klasse zur Dateiverwaltung
 * @author Alex
 *
 */
public class FileHandling {
	
	private File xmlfile = null;
	
	/**
	 * Konstruktor zur Dateiverwaltung; öffnet ein Dialogfenster zur Auswahl einer pnm-Datei.
	 */
	public FileHandling() {
		JFileChooser jfilechooser = new JFileChooser ();
		jfilechooser.setCurrentDirectory(new File ("../ProPra-WS20-Basis/Beispiele"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("pnml-Dateien", "pnml");
		jfilechooser.setFileFilter(filter);
		jfilechooser.showOpenDialog(null);
		xmlfile = jfilechooser.getSelectedFile();
	}
	
	/**
	 * Gibt die ausgewählte Datei zurück.
	 * @return
	 */
	public File getFile() {
		return xmlfile;
	}
	
	
	
}
