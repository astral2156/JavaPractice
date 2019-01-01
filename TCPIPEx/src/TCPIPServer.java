import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPIPServer {
	private static DataInputStream streamIn =  null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ServerSocket sc = new ServerSocket(5000);
			System.out.println("## Server Execute ... ");
			while(true) {
				
				Socket s = sc.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				System.out.println("## Client Connected!");
				System.out.println("## Client Message : " + br.readLine());
				
				br.close();
						}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
