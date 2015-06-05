package nexuusgame.launcher;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import nexuusgame.main.Nexuus;

@SuppressWarnings("serial")
public class Launcher extends Applet {
	private static Nexuus launcher = new Nexuus();
	public static final boolean DEBUG = false;
	
	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(launcher, BorderLayout.CENTER);
		setMaximumSize(Nexuus.DIMENSIONS);
		setMinimumSize(Nexuus.DIMENSIONS);
		setPreferredSize(Nexuus.DIMENSIONS)
		launcher.debug = DEBUG;
		launcher.isApplet = true;
	}
	
	@Override
	public void start() {
		launcher.start();
	}
	
	@Override
	public void stop() {
		launcher.stop();
	}
	
	public static void Main(String[] args) {
		launcher.setMinimumSize(Nexuus.DIMENSIONS);
		launcher.setMaximumSize(Nexuus.DIMENSIONS);
		launcher.setPreferredSize(Nexuus.DIMENSIONS);
		
		launcher.frame = new JFrame(Nexuus.NAME);
		
		launcher.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		launcher.frame.setLayout(new BorderLayout());
		
		launcher.frame.add(launcher, BorderLayout.CENTER);
		launcher.frame.pack();
		
		launcher.frame.setResizable(false);
		launcher.frame.setLocationRelativeTo(null);
		launcher.frame.setVisible(true);
		
		launcher.gameHandler = new GameHandler(launcher);
		launcher.debug = DEBUG;
		
		launcher.start();
	}

}