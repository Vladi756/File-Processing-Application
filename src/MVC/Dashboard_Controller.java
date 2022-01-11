package cw2;

import java.io.File;

import javax.swing.SwingUtilities;

import cw2.ChartFactory.ChartType;

// Acts as an interface between the model and the view. 
// Responsible for interpreting input (mouse click, keyboard input, etc.) from user.

public class Dashboard_Controller {
	private Dashboard_View view;
	private Dashboard_Model model;

	public Dashboard_Controller() {
		this.view = new Dashboard_View(this);
		this.model = new Dashboard_Model(this.view);
	}

	public void setMenu(ChartType cT) {
		try {
			this.model.updateMenu(cT);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			System.out.println(x + "is not a valid argument!");
		}
	}

	public void setFolder(File f) {
		try {
		this.model.setFolder(f);
		} catch(IllegalArgumentException x) {
			x.printStackTrace();
		}
	}
	
	private static void launch() {
		new Dashboard_Controller();
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> launch());
	}
}
