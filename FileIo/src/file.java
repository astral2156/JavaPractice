import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class file {

	public static void main(String[] args) {
/*
		String path = "C:\\JavaPrectice\\FileIo\\fileioexam.txt";
		File file = new File(path);
		
		
		try {
			FileWriter fw = new FileWriter(file);
			for(int i ='a'; i<='z'; i++) {
				fw.write(i);
			}
			fw.close();
			System.out.println("done");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		/////////////////////////////////////////// ������ ���� �����, ���Ͽ� �Է�
		
		File rfile = new File("fileioexam.txt");
		File wfile = new File("fileioexam_new.txt");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(rfile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(wfile));
			String s;
			while((s = reader.readLine())!=null) {
				writer.write(s + "�������� ");// _new ���Ͽ� ���� ����
				
			}
			reader.close();
			writer.close();
			//rfile.delete();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("catch");
			
		}
		System.out.println("Done");
	}
	
	/////////////////////////////////////////////// ���� ���� �����ϱ�
	
*/
		System.out.println("## �޸���  ##");
		System.out.println("## ���� ���� �� : ");
		
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.next();
		System.out.println("## ������ ������ ���ο� q �Է�\n");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // ��ǲ �����ϱ�
			BufferedWriter writer = new BufferedWriter(new FileWriter("c:/JavaPrectice/"+filename+".txt")); // ���Ϸ� ����
			String s;
			while(!(s=reader.readLine()).equals("q")) {
				writer.write(s +"\r\n");
			}
			reader.close();
			writer.close();
			scanner.close();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("## ���α׷� ���� ");
		System.out.println(filename + "���� �Ϸ�! ");
		
		
	}
		
}

