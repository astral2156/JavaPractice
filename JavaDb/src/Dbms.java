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

		System.out.println("�̸��� �Է��ϼ���");
		uname = scan.nextLine();
		System.out.println("�̸����� �Է��ϼ���");
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

		// 6. ������ ����ϱ�
		try {
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
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

		// 6. ������ ����ϱ�
		try {
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. �����ϱ�
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC", "root", "kim27903");

			// 3. ���� ������ ���� Statement ��ü ����
			stmt = conn.createStatement();

			String sql = "SELECT uname, email FROM event";
			insertInfo();
			insert();
			// insertSelect(); �̱���

			// 5. ���� ����
			// ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
			printList();

			//pstmt.close();
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
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
