package nexuusgame.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import nexuusgame.main.Nexuus;

import com.sun.xml.internal.ws.api.message.Packet;

public class NexuusClient extends Thread {
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Nexuus nexuus;
	
	public NexuusClient(Nexuus nexuus, String ipAddress) {
		this.nexuus = nexuus;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException event) {
			event.printStackTrace();
		} catch (UnknownHostException event) {
			event.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException event) {
				event.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			handleLogin((Packet00Login) packet, address, port);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet01Disconnect) packet).getUsername() + " has left the game.");
			game.level.removePlayerMp(((Packet01Disconnect) packet).getUsername());
		case MOVE:
			packet = new Packet02Move(data);
			handleMove((Packet02Move) packet);
		}
	}
	
	public void sendData(byte[] data) {
		if (!nexuus.isApplet) {
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
			try {
				socket.send(packet);
			} catch (IOException event) {
				event.printStackTrace();
			}
		}
	}
	
	private void handleLogin(Packet00Login packet, InetAddress address, int port) {
		System.out.println("[" + address.getHostAddress() + ":" + port + "]" + packet.getUsername() + " has joined the game.");
		PlayerMP player = new PlayerMP(nexuus.level, packet.getX(), packet.getY(), packet.getUsername(), address, port);
		nexuus.level.addEntity(player);
	}
	
	private void handleMove(Packet02Move packet) {
		this.nexuus.level.movePlayer(packet.getUsername(), packet.getX(), packet.getY(), packet.getNumSteps(), packet.isMoving(), packet.getMovingDir());
	}

}
