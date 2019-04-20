package peertopeerPackage;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class QueuingModule {
	
    RandomAccessFile dataStore;
    public QueuingModule() {
    	this.dataStore=null;
    }
    
    public void writeData(String data) throws IOException {
    	
    	try {
    		this.dataStore=new RandomAccessFile("data.txt", "rw");
    	}catch(FileNotFoundException e) {
    		System.out.println("Error to open data file");
    	}
    	
    	this.dataStore.seek(this.dataStore.length());
    	this.dataStore.writeBytes(data);
    	this.dataStore.writeBytes("\n");
    	this.dataStore.close();
    	
    }
    
    public void getAllDataStore() {
    	
    	File file=null;
		Scanner reader=null;
		try {
    		file=new File("data.txt");
    	    reader=new Scanner(file);
    	}catch(FileNotFoundException e) {
    		System.out.println("Error to open data file");
    	}
		
		while(reader.hasNextLine()) {
			String address=reader.nextLine();
			System.out.println(address);
		}
		reader.close();
    	
    }
}
