package asgn2GUI;

import java.awt.Font;

import javax.swing.JTextArea;

import asgn2Codes.ContainerCode;
import asgn2Manifests.CargoManifest;

/**
 * Creates a JTextArea in which textual components are laid out to represent the cargo manifest.
 *
 * @author Leandro Rodrigues n9382909
 */
@SuppressWarnings("serial")
public class CargoTextArea extends JTextArea {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;

    private final CargoManifest cargo;

    private ContainerCode toFind;

    /**
     * Constructor initializes the JTextArea.
     *
     * @param cargo he <code>CargoManifest</code> on which the text area is based 
     * 
     */
    public CargoTextArea(CargoManifest cargo) {
        setFont(new Font("Calibri", Font.PLAIN, 12));
        setName("Cargo Text Area");
        setSize(WIDTH, HEIGHT);
        setEditable(false);
        this.cargo = cargo;
    }

    /**
     * Highlights a container.
     *
     * @param code ContainerCode to highlight.
     */
    public void setToFind(ContainerCode code) {
    	this.toFind = code;
    	String highlightedCargoManf = this.cargo.toString(toFind);
    	this.setText(highlightedCargoManf);
    }

    /**
     * Outputs the container representation from the cargo manifest on the text area.
     *
     */
    public void updateDisplay() {
    	String textualCargoManifest = this.cargo.toString();
    	this.setText(textualCargoManifest);
    }
}
