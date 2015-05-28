package asgn2GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import asgn2Codes.ContainerCode;

/**
 * Creates a dialog box allowing the user to enter a ContainerCode.
 *
 * @author Leandro Rodrigues n9382909
 */
@SuppressWarnings("serial")
public class ContainerCodeDialog extends AbstractDialog {

    private final static int WIDTH = 400;
    private final static int HEIGHT = 120;

    private JTextField txtCode;
    private JLabel lblErrorInfo;

    private ContainerCode code;

    /**
     * Constructs a modal dialog box that requests a container code.
     *
     * @param parent the frame which created this dialog box.
     */
    private ContainerCodeDialog(JFrame parent) {
        super(parent, "Container Code", WIDTH, HEIGHT);
        setName("Container Dialog");
        setResizable(true);
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 100;
        constraints.weighty = 100;

        txtCode = new JTextField();
        txtCode.setColumns(22);
        txtCode.setName("Container Code");
        txtCode.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            /*
             * Attempts to validate the ContainerCode entered in the Container Code text field.
             */
            private void validate() {
            	// Verifies code, showing to the user a message with information about the errors in it
            	try {
            		if(txtCode.getText().equals(""))
            			lblErrorInfo.setText(" ");
            		else{
            			new ContainerCode(txtCode.getText());
            			lblErrorInfo.setText(" ");
            		}
				} catch (Exception e) {
					lblErrorInfo.setText("("+e.getMessage().substring(38)+")");
				}
            }
        });

        // Creates, adds, and returns Panel content.
        lblErrorInfo = new JLabel(" ");
        lblErrorInfo.setVisible(true);
        addToPanel(toReturn, new JLabel("Container Code:"), constraints, 0, 0, 1, 1);
        addToPanel(toReturn, txtCode, constraints, 3, 0, 1, 1);
        constraints.anchor = GridBagConstraints.CENTER;
        addToPanel(toReturn, lblErrorInfo, constraints, 0, 1, 6, 0);
        return toReturn;
    }

    @Override
    protected boolean dialogDone() {
    	// Tries to create a container code after user finish using the dialog. Also handles exceptions.
    	ContainerCode containerCode;
    	try {
    		containerCode = new ContainerCode(txtCode.getText());
			this.code = containerCode;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
    	
    	return true;
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>ContainerCode</code> instance with valid values.
     */
    public static ContainerCode showDialog(JFrame parent) {
    	// Creates a visible modal dialog and performs possible actions. After returns created container code.
    	final JFrame myJFrameParent = parent;
        ContainerCodeDialog myContainerCodeDialog = new ContainerCodeDialog(myJFrameParent);
        myContainerCodeDialog.setVisible(true);
        myContainerCodeDialog.setModal(true);
        
		return myContainerCodeDialog.code;
    }
}
