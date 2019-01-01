
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPIPClient {
	public static void main(String[] args) throws Exception {

		
		try {
			Socket s = new Socket("127.0.0.1", 5000);
		
			System.out.println("## Client Executed..");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
			// sending to client (pwrite object)
			pw.println("Hi! From Client");

			
			pw.close();
			s.close();
			System.out.println("## Client End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
