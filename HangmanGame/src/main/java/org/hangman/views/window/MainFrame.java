package org.hangman.views.window;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JFrame;

import org.hangman.views.pan.HomePan;

public class MainFrame extends JFrame implements Observer {
	private Dimension defaultDim = new Dimension(700,500);
	public MainFrame(String title) throws HeadlessException {
		super(title);
		this.setSize(defaultDim);
		
		
		this.setContentPane(new HomePan());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
