package asgn2GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asgn2Manifests.CargoManifest;

/**
 * Creates a dialog box allowing the user to enter parameters for a new <code>CargoManifest</code>.
 *
 * @author Leandro Rodrigues n9382909
 */
@SuppressWarnings("serial")
public class ManifestDialog extends AbstractDialog {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 250;

    private JTextField txtNumStacks;
    private JTextField txtMaxHeight;
    private JTextField txtMaxWeight;

    private static CargoManifest manifest;

    /**
     * Constructs a modal dialog box that gathers information required for creating a cargo
     * manifest.
     *
     * @param parent the frame which created this dialog box.
     */
    private ManifestDialog(JFrame parent) {
        super(parent, "Create Manifest", WIDTH, HEIGHT);
        setName("New Manifest");
        setResizable(false);
        manifest = null;
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {

        txtNumStacks = createTextField(8, "Number of Stacks");
        txtMaxHeight = createTextField(8, "Maximum Height");
        txtMaxWeight = createTextField(8, "Maximum Weight");

        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());

        JLabel labelNumStacks = new JLabel("Number of Stacks:");
        JLabel labelMaxHeight = new JLabel("Max Stack Height:");
        JLabel labelMaxWeight = new JLabel("Max Weight:");
        
        
        GridBagConstraints constraints = new GridBagConstraints(); 
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 120;
        constraints.weighty = 100;
        
        addToPanel(toReturn, labelNumStacks,constraints,0,0,2,1);
		addToPanel(toReturn, txtNumStacks,constraints,3,0,2,1);
		
		addToPanel(toReturn, labelMaxHeight,constraints,0,1,2,1);
		addToPanel(toReturn, txtMaxHeight,constraints,3,1,2,1);
		
		addToPanel(toReturn, labelMaxWeight,constraints,0,2,2,1);
		addToPanel(toReturn, txtMaxWeight,constraints,3,2,2,1);   
        
        return toReturn;
    }

    /*
     * Factory method to create a named JTextField
     */
    private JTextField createTextField(int numColumns, String name) {
        JTextField text = new JTextField();
        text.setColumns(numColumns);
        text.setName(name);
        return text;
    }

    @Override
    protected boolean dialogDone() {
    	// After user insert inputs, converts user inputs to Integers, and finally tries to create a manifest
    	// Also handles exceptions
    	
    	Integer numStacks, maxHeight, maxWeight;
    	try {
    		numStacks = Integer.parseInt(txtNumStacks.getText());
    		maxHeight = Integer.parseInt(txtMaxHeight.getText());
    		maxWeight = Integer.parseInt(txtMaxWeight.getText());
    		
    		try {
				manifest = new CargoManifest(numStacks, maxHeight, maxWeight);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
    		
    		return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please use only whole numbers (Integer) for all inputs", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
    	
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>CargoManifest</code> instance with valid values.
     */
    public static CargoManifest showDialog(JFrame parent) {
    	// Creates a visible modal dialog and performs possible actions. After returns created manifest. 
    	final JFrame myJFrameParent = parent;
        ManifestDialog myManifestDialog = new ManifestDialog(myJFrameParent);
        
        myManifestDialog.setVisible(true);
        myManifestDialog.setModal(true);
        
		return manifest ;
    }
}
