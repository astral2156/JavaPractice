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
		
		/////////////////////////////////////////// 위에는 파일 만들기, 파일에 입력
		
		File rfile = new File("fileioexam.txt");
		File wfile = new File("fileioexam_new.txt");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(rfile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(wfile));
			String s;
			while((s = reader.readLine())!=null) {
				writer.write(s + "라인종료 ");// _new 파일에 새로 저장
				
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
	
	/////////////////////////////////////////////// 파일 수정 종료하기
	
*/
		System.out.println("## 메모장  ##");
		System.out.println("## 저장 파일 명 : ");
		
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.next();
		System.out.println("## 저장은 마지막 라인에 q 입력\n");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 인풋 저장하기
			BufferedWriter writer = new BufferedWriter(new FileWriter("c:/JavaPrectice/"+filename+".txt")); // 파일로 적기
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
		System.out.println("## 프로그램 종료 ");
		System.out.println(filename + "저장 완료! ");
		
		
	}
		
}

