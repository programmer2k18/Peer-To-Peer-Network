package peertopeerPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Vector;

public class NodeDiscovery {
	
	RandomAccessFile address;
	Vector<String> AllAdresses;
	public NodeDiscovery() {
		this.address=null;
		this.AllAdresses=new Vector();
	}
	public void writeNewAddress(InetAddress inetAddress) throws IOException {
		
		
		String address=String.valueOf(inetAddress);
		this.getAllAddresses();
		
		if(!this.AllAdresses.contains(address)) {
			
			this.AllAdresses.add(address);
			try {
	    		this.address=new RandomAccessFile("addresses.txt", "rw");
	    	}catch(FileNotFoundException e) {
	    		System.out.println("Error to open data file");
	    	}
	    	
	    	this.address.seek(this.address.length());
	    	this.address.writeBytes(address);
	    	this.address.writeBytes("\n");
	    	this.address.close();
		}	
		
	}
	
	public void getAllAddresses() throws IOException {
		
		File file=null;
		Scanner reader=null;
		try {
    		file=new File("addresses.txt");
    	    reader=new Scanner(file);
    	}catch(FileNotFoundException e) {
    		System.out.println("Error to open data file");
    	}
		
		while(reader.hasNextLine()) {
			String	address=reader.nextLine();
			this.AllAdresses.add(address);
		}
		reader.close();
	}
}
