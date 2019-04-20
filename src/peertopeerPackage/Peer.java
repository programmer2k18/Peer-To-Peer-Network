package peertopeerPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Peer{
	
	 private DatagramSocket socket;
	 private InetAddress group;
	 
	 private byte[] buf= new byte[1024];
	 private String message;
	 
	 public Peer() {
		 
	 }

	public void SendDataToAllNodes(String Message) throws IOException{
			this.message=Message;
	        socket = new DatagramSocket();
	        
	        group = InetAddress.getByName("230.0.0.0");
	        buf = Message.getBytes(); //convert message to bytes first
	 
	        DatagramPacket packet 
	          = new DatagramPacket(buf, buf.length, group, 4446);
	        socket.send(packet);
	        socket.close();
	 }
	 /*
	 public void run() {
		 try {
			this.SendDataToAllNodes(this.message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }*/

	 public DatagramSocket getSocket() {
		 return this.socket;
	 }
	 
	 public InetAddress getGroup() {
		 return this.group;
	}
}
