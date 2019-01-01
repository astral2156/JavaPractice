import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.AbstractDocument.BranchElement;

public class Connection {

	public static void main(String[] args) {

		try {

			URL url = new URL("https://www.naver.com/index.html");
			System.out.println("�������� : " + url.getProtocol());
			System.out.println("ȣ��Ʈ : " + url.getHost());
			System.out.println("��Ʈ : " + url.getPort());
			
			System.out.println("   HTML ����  ");
			InputStream is;
			is = url.openStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String msg;
			while ((msg = br.readLine()) != null) {
				System.out.println(msg);
			}
			
			br.close();
			is.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
