import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		try {
			DatagramSocket server = new DatagramSocket(8080);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			long longNumber = 0L;
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			server.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + "from " + receivePacket.getAddress() + " Packet no: " + longNumber++ + " " + sentence );
			String sendString = "Hi client";
			sendData =sendString.getBytes();
			InetAddress clientAddress = receivePacket.getAddress();
			int clientPort = receivePacket.getPort();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
			server.send(sendPacket);
		
			//server.close();
		}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
