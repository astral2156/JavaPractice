import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Dbms {

	static String uname = "";
	static String email = "";
	static Connection conn = null;
	static java.sql.Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt;
	
	public static void insertInfo() {

		Scanner scan = new Scanner(System.in);

		System.out.println("이름을 입력하세요");
		uname = scan.nextLine();
		System.out.println("이메일을 입력하세요");
		email = scan.nextLine();

	}

	public static void printList() {
		String sql = "SELECT uname, email FROM event";
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 6. 실행결과 출력하기
		try {
			while (rs.next()) {
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String name = rs.getString(1);
				String email1 = rs.getString(2);
				System.out.println("user name/ email : " + name +"/"+ email1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void insert() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC", "root", "kim27903");
			

			stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement("insert into event values(?,?)");
			pstmt.setString(1, uname);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertSelect() {
		
		String sql = "SELECT uname, email FROM event";
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 6. 실행결과 출력하기
		try {
			while (rs.next()) {
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String name = rs.getString(1);
				String email1 = rs.getString(2);
				System.out.println("user name/ email : " + name +"/"+ email1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws SQLException {
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC", "root", "kim27903");

			// 3. 쿼리 수행을 위한 Statement 객체 생성
			stmt = conn.createStatement();

			String sql = "SELECT uname, email FROM event";
			insertInfo();
			insert();
			// insertSelect(); 미구현

			// 5. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			printList();

			//pstmt.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
