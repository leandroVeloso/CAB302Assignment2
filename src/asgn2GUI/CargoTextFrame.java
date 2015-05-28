package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * The main window for the Cargo Manifest Text application.
 *
 * @author Leandro Rodrigues n9382909
 */
@SuppressWarnings("serial")
public class CargoTextFrame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JButton btnLoad;
    private JButton btnUnload;
    private JButton btnFind;
    private JButton btnNewManifest;

    private CargoTextArea canvas;

    private JPanel pnlControls;
    private JPanel pnlDisplay;

    private CargoManifest cargo;

    /**
     * Constructs the GUI.
     *
     * @param title The frame title to use.
     * @throws HeadlessException from JFrame.
     */
    public CargoTextFrame(String frameTitle) throws HeadlessException {
        super(frameTitle);
        constructorHelper();
        disableButtons();
        setVisible(true);
    }

    /**
     * Initialises the container display area.
     *
     * @param cargo The <code>CargoManifest</code> instance containing necessary state for display.
     */
    private void setCanvas(CargoManifest cargo) {
    	pnlDisplay = new JPanel();
        if (canvas != null) {
            pnlDisplay.remove(canvas);
        }
        if (cargo == null) {
            disableButtons();
        } else {
        	// If a cargo was instantiated properly (!= null) then creates, updates, shows, and adds a canvas with its textual representation
            canvas = new CargoTextArea(cargo);
            canvas.updateDisplay();
            pnlDisplay.add(canvas);
            pnlDisplay.setVisible(true);
            this.getContentPane().add(pnlDisplay, BorderLayout.CENTER);
            redraw();
        }

    }

    /**
     * Enables buttons for user interaction.
     */
    private void enableButtons() {
    	btnLoad.setEnabled(true);
    	btnUnload.setEnabled(true);
    	btnFind.setEnabled(true);
    }

    /**
     * Disables buttons from user interaction.
     */
    private void disableButtons() {
    	btnLoad.setEnabled(false);
    	btnUnload.setEnabled(false);
    	btnFind.setEnabled(false);
    }

    /**
     * Initialises and lays out GUI components.
     */
    private void constructorHelper() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLoad = createButton("Load", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoTextFrame.this.resetCanvas();
                        CargoTextFrame.this.doLoad();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnUnload = createButton("Unload", new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                 Runnable doRun = new Runnable() {
                     @Override
                     public void run() {
                         CargoTextFrame.this.resetCanvas();
                         CargoTextFrame.this.doUnload();
                     }
                 };
                 SwingUtilities.invokeLater(doRun);
             }
        });
        btnFind = createButton("Find", new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoTextFrame.this.resetCanvas();
                        CargoTextFrame.this.doFind();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnNewManifest = createButton("New Manifest", new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoTextFrame.this.resetCanvas();
                        CargoTextFrame.this.setNewManifest();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            } 
        });

        this.getContentPane().add(createControlPanel(), BorderLayout.SOUTH);
        repaint();
    }

    /**
     * Creates a JPanel containing user controls (buttons).
     *
     * @return User control panel.
     */
    private JPanel createControlPanel() {
    	pnlControls = new JPanel();
        pnlControls.add(btnLoad);
        pnlControls.add(btnUnload);
        pnlControls.add(btnFind);
        pnlControls.add(btnNewManifest);
        enableButtons();
        return pnlControls;
    	
    }

    /**
     * Factory method to create a JButton and add its ActionListener.
     *
     * @param name The text to display and use as the component's name.
     * @param btnListener The ActionListener to add.
     * @return A named JButton with ActionListener added.
     */
    private JButton createButton(String name, ActionListener btnListener) {
        JButton btn = new JButton(name);
        btn.setName(name);
        btn.addActionListener(btnListener);
        return btn;
    }

    /**
     * Initiate the New Manifest dialog which sets the instance of CargoManifest to work with.
     */
    private void setNewManifest() {
        CargoManifest aCargo = ManifestDialog.showDialog(this);
        if(aCargo != null){
        	this.cargo = aCargo;
    		enableButtons();
    	}
        setCanvas(cargo);
    }

    /**
     * Turns off container highlighting when an action other than Find is initiated.
     */
    private void resetCanvas() {
    	if (canvas != null) 
        	canvas.updateDisplay();
    }

    /**
     * Initiates the Load Container dialog.
     */
    private void doLoad() {
    	// Show a dialog and check if it returns a notnull object if so tries to load the container into the ship. Also handles possible exceptions
    	FreightContainer con = LoadContainerDialog.showDialog(this);
    	if(con != null){
	    	try {
				cargo.loadContainer(con);
			} catch (ManifestException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	    	redraw();
    	}

    }

    /**
     * Initiates the Unload Container dialog.
     */
    private void doUnload() {
    	// Show a dialog and check if it returns a notnull object if so tries to unload the container. Also handles possible exceptions
    	ContainerCode code = ContainerCodeDialog.showDialog(this);
    	if(code != null){
			try {
				
				cargo.unloadContainer(code);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			redraw();
    	}
    	
    }

    /**
     * Initiates the Find Container dialog.
     */
    private void doFind() {
    	// Show a dialog and check if it returns a notnull object if so tries to find the container into the ship nad highLight it. Also handles possible exceptions
    	ContainerCode con = ContainerCodeDialog.showDialog(this);
    	if(con != null){
	    	try {
				canvas.setToFind(con);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
    	}
    	
    }

    /**
     * 
     * Updates the display area.
     *
     */
    private void redraw() {
    	canvas.updateDisplay();
    	canvas.revalidate();
    }
}
