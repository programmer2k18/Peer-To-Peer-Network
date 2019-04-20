package peertopeerPackage;

import java.io.IOException;
import java.util.Scanner;

public class mymain {

	public static void main(String[] args) throws IOException {
		
			Peer p=new Peer();
			while(true) {
				
				System.out.println("Enter Message!, to exit enter end!");
				String msg=new Scanner(System.in).nextLine();
				if(msg.equals("end"))
					break;
				p.SendDataToAllNodes(msg);
				
			}
	}

}
