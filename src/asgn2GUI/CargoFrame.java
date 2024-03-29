package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import asgn2Manifests.CargoManifest;

/**
 * The main window for the Cargo Manifest graphics application.
 *
 * @author CAB302
 */
public class CargoFrame extends JFrame {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private JButton btnLoad;
	private JButton btnUnload;
	private JButton btnFind;
	private JButton btnNewManifest;

	private CargoCanvas canvas;

	private JPanel pnlControls;
	private JPanel pnlDisplay;

	private CargoManifest cargo;

	/**
	 * Constructs the GUI.
	 *
	 * @param title
	 *            The frame title to use.
	 * @throws HeadlessException
	 *             from JFrame.
	 */
	public CargoFrame(String title) throws HeadlessException {
		super(title);
		createControlPanel();
		constructorHelper();
		disableButtons();
		redraw();
		setVisible(true);
	}

	/**
	 * Initialises the container display area.
	 *
	 * @param cargo
	 *            The <code>CargoManifest</code> instance containing necessary
	 *            state for display.
	 */
	private void setCanvas(CargoManifest cargo) {
		if (canvas != null) {
			pnlDisplay.remove(canvas);
		}
		if (cargo == null) {
			disableButtons();
		} else {
			canvas = new CargoCanvas(cargo);
			enableButtons();
		}
		redraw();
	}

	/**
	 * Enables buttons for user interaction.
	 */
	private void enableButtons() {
		btnLoad.setEnabled(true);
		btnUnload.setEnabled(true);
		btnFind.setEnabled(true);
		btnNewManifest.setEnabled(true);
	}

	/**
	 * Disables buttons from user interaction.
	 */
	private void disableButtons() {
		btnLoad.setEnabled(false);
		btnUnload.setEnabled(false);
		btnFind.setEnabled(false);
		btnNewManifest.setEnabled(false);
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
						CargoFrame.this.resetCanvas();
						CargoFrame.this.doLoad();
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
						CargoFrame.this.resetCanvas();
						CargoFrame.this.doUnload();
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
						CargoFrame.this.resetCanvas();
						CargoFrame.this.doFind();
					}
				};
				SwingUtilities.invokeLater(doRun);
			}
			// implementation here
		});
		btnNewManifest = createButton("New Manifest", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Runnable doRun = new Runnable() {
					@Override
					public void run() {
						CargoFrame.this.resetCanvas();
						CargoFrame.this.setNewManifest();
					}
				};
				SwingUtilities.invokeLater(doRun);
			}
		});
		// implementation here
		repaint();
	}

	/**
	 * Creates a JPanel containing user controls (buttons).
	 *
	 * @return User control panel.
	 */
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(canvas, BorderLayout.NORTH);
		panel.add(btnLoad, BorderLayout.SOUTH);
		panel.add(btnUnload, BorderLayout.SOUTH);
		panel.add(btnFind, BorderLayout.SOUTH);
		panel.add(btnNewManifest, BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * Factory method to create a JButton and add its ActionListener.
	 *
	 * @param name
	 *            The text to display and use as the component's name.
	 * @param btnListener
	 *            The ActionListener to add.
	 * @return A named JButton with ActionListener added.
	 */
	private JButton createButton(String name, ActionListener btnListener) {
		JButton btn = new JButton(name);
		btn.setName(name);
		btn.addActionListener(btnListener);
		return btn;
	}

	/**
	 * Initiate the New Manifest dialog which sets the instance of CargoManifest
	 * to work with.
	 */
	private void setNewManifest() {

	}

	/**
	 * Turns off container highlighting when an action other than Find is
	 * initiated.
	 */
	private void resetCanvas() {
		// implementation here
	}

	/**
	 * Initiates the Load Container dialog.
	 */
	private void doLoad() {
		// implementation here
		// don't forget to redraw
	}

	private void doUnload() {
		// implementation here
		// don't forget to redraw
	}

	private void doFind() {
		// implementation here
	}

	private void redraw() {
		invalidate();
		validate();
		repaint();
	}
}
