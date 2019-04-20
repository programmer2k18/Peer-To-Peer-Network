package peertopeerPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



public class PeerRecieving extends Thread {
	
	private MulticastSocket multiSocket = null;
	private QueuingModule queue;
	private NodeDiscovery discovery;
	private InetAddress group = null;
	
	private byte[] buf = new byte[1024];
	private String dataRecieved;
   
   public PeerRecieving() throws IOException {
	   multiSocket = new MulticastSocket(4446);
	   queue=new QueuingModule();
	   discovery=new NodeDiscovery();
	   dataRecieved="";
   }
   
   public void joinGroup() throws IOException {
	 	 
		group = InetAddress.getByName("230.0.0.0"); 
		this.multiSocket.joinGroup(group);	
   }
   
   public void run() {
	   try {
		   
				this.joinGroup();
				
				while(true) {
					
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
				    this.multiSocket.receive(packet);
				    this.dataRecieved = new String(
				    packet.getData(), 0, packet.getLength());
				    System.out.println("R " + this.dataRecieved);
				            
				    this.queue.writeData(this.dataRecieved);
				    
				    this.discovery.writeNewAddress(packet.getAddress());
				    if("done".equals(this.dataRecieved)) {
				    	System.out.println("connection is over");
				    	break;
				    }
				    this.dataRecieved="";
					
				}
				
		        //write the received data and the address
				this.multiSocket.leaveGroup(this.group);
		        this.multiSocket.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}   
   }
   
   public String getData() {
	   return this.dataRecieved;
   }
   
   public QueuingModule getQueue() {
		return this.queue;
   }
   
   public MulticastSocket getMultiCastSocket() {
	   return this.multiSocket;
   }
   
   public NodeDiscovery getNodeDiscovery() {
	   return this.discovery;
   }
}
