
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO { 
	// �ַ� DB�� ������ ���� �ൿ

	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC";
	Connection conn = null;
	int countBoard = 0;

	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	String id;

	ProjectDAO() {

	}

	public void connect() { // DB ����
		try {
			// �ε�
			Class.forName(jdbcDriver);
			// ����
			conn = DriverManager.getConnection(jdbcUrl, "root", "kim27903");
			// System.out.println("connected");
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> �ε� fail");
		} catch (SQLException e) {
			System.out.println(e + "=> ���� fail");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void newBoard(boardData BD) { // �Խ��� ���� ����

		sql = "insert into boards values(null,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BD.bName);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delBoard(boardData BD)///////////////////////// ��������
									// �Խ��� ���� ����
	{
		sql = "Delete from boards Where boardname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BD.bName);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delPost(postData PD) {//////// ������ ����
									// �Խù� ���� ����
		sql = "Delete from posts Where pId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PD.pId);//
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<boardData> refreshBD() { // ���� ���� ���� �ൿ ���� refresh ����
		// getall �ϰ� jlist�� �߰�

		sql = "select * from boards";
		ArrayList<boardData> datas = new ArrayList<boardData>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { // �� �����ͼ� �ٽ� ���
				countBoard++;

				boardData p = new boardData();
				p.bId = rs.getInt("bid");
				p.bName = rs.getString("boardname");
				datas.add(p); // product array �� add
			}
			System.out.println(countBoard);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (datas != null) {
			for (boardData p : datas) {
				System.out.println(p.toString());
			}
		}
		return datas;
	}

	public void getId(String uid) { // Post�� Board User�� ���� ����Ǿ� �����Ƿ� ���̵� �޾ƿ��� ����
		id = uid;
		System.out.println("get ID : " + id);// ������ ���̵� �� �޳�
	}

	public void newPost(postData PD) { // �� �Խñ� ���
		sql = "insert into posts(pId,pTitle,pContents,pbid, pwRiter,pImage) values(null,?,?,?,?,?)"; // id title
																										// contents
																										// image
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, PD.pTitle);
			pstmt.setString(2, PD.pContents);
			pstmt.setInt(3, PD.pbid);
			pstmt.setString(4, id);
			pstmt.setString(5, PD.pImage);
			PD.pWriter = id; // pWriter�� id�� ����

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<postData> refreshPS()// �Խù��� ���� ���� refresh
	{
		// getall �ϰ� jlist�� �߰�

		sql = "select * from posts";
		ArrayList<postData> posts = new ArrayList<postData>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postData p = new postData();
				p.pId = rs.getInt("pId");
				p.pTitle = rs.getString("pTitle");
				p.pImage = rs.getString("pImage");
				p.pContents = rs.getString("pContents");
				p.pWriter = rs.getString("pWriter");
				p.likes = rs.getInt("likes");
				posts.add(p); // product array �� add
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (posts != null) {
			for (postData p : posts) {
				System.out.println(p.toString());
			}
		}
		return posts;
	}

	public void announce(postData PD) { // ��ü ������ ���
		sql = "insert into posts(pId,pTitle,pContents,pbid) values(null,?,?,?)"; // id title contents image
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, PD.pTitle);
			pstmt.setString(2, PD.pContents);
			pstmt.setString(3, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void heartActive(postData PD) { //���� �߰� 
											// ��Ʈ ��ư�� ������ ��� like�� 1�� �����Ͽ� Update����
		sql = "update posts set likes=(?) where pId=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, PD.pId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void heartShow(postData PD) { //���� �߰�
		sql = "select pid from posts where likes=?"; // id title contents image
		MainPanel2 mp;
		
		try {
			mp = new MainPanel2();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PD.likes);// 1�ΰ�� ���ƿ� ǥ�õ� �����
			// pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("pid");
				//System.out.println("id : " + id ); // heart Ŭ���Ȱ� ��������
				mp.heartChange();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
