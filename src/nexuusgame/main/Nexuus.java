package nexuusgame.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Nexuus extends Canvas implements Runnable {
	
	//Main file - Copyright - Christopher Albon;
	//Started the project on 5th June 2015, 12:40
		
	//Basically a game, and coded when leaving year 11 :)
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Nexuus";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static Nexuus nexuus;
	
	public JFrame frame;
	
	public boolean debug = true;
	public boolean isApplet = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
