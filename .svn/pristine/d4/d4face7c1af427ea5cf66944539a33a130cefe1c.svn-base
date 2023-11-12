import java.io.File;

import control.file.Parser;
import model.Knot;
import model.PetriNet;

public class TestFiringAll {

	
	public static void main (String args[]) {
		if (args.length > 0) {
			File pnmlDatei = new File(args[0]);
			if (pnmlDatei.exists()) {
				Parser pnmlParser = new Parser(pnmlDatei);
				pnmlParser.initParser();
				pnmlParser.parse();
				for (int i = 0; i < pnmlParser.getKnotList().size(); i++) {
					System.out.println(pnmlParser.getKnotList().get(i).getId());
					System.out.println(pnmlParser.getKnotList().get(i).getName());
					System.out.println(pnmlParser.getKnotList().get(i).getX());
					System.out.println(pnmlParser.getKnotList().get(i).getY());
					System.out.println("------");
				}
				
				for (int j = 0; j < pnmlParser.getArcList().size(); j++) {
					System.out.println(pnmlParser.getArcList().get(j).getId());
					System.out.println(pnmlParser.getArcList().get(j).getSource());
					System.out.println(pnmlParser.getArcList().get(j).getTarget());
					System.out.println("------");
				}
				
				PetriNet petrinet = new PetriNet (pnmlParser.getKnotList(), pnmlParser.getArcList());
				petrinet.firing_all(petrinet.getMarking());
			}

		}
	}
}
