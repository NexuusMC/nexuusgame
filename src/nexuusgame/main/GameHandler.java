package nexuusgame.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameHandler implements WindowListener {
	
	private final Nexuus nexuus;
	
	public GameHandler(Nexuus nexuus) {
		
		this.nexuus = nexuus;
		this.nexuus.frame.addWindowListener(this);
		
	}
	
	@Override
	public void windowActivated(WindowEvent event) {
		
	}
	
	@Override
	public void windowClosed(WindowEvent event) {
		
		
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		Packet01Disconnect packet = new Packet01Disconnect(this.nexuus.player.getUsername());
		packet.writeData(this.nexuus.socketClient);
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
		
	}

	@Override
	public void windowIconified(WindowEvent event) {
		
	}

	@Override
	public void windowOpened(WindowEvent event) {
		
	}

}
