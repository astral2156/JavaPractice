
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO { 
	// 주로 DB에 쿼리를 쓰는 행동

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

	public void connect() { // DB 연결
		try {
			// 로드
			Class.forName(jdbcDriver);
			// 연결
			conn = DriverManager.getConnection(jdbcUrl, "root", "kim27903");
			// System.out.println("connected");
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
		} catch (SQLException e) {
			System.out.println(e + "=> 연결 fail");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void newBoard(boardData BD) { // 게시판 생성 쿼리

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

	public void delBoard(boardData BD)///////////////////////// 예림수정
									// 게시판 삭제 쿼리
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

	public void delPost(postData PD) {//////// 마지막 수정
									// 게시물 삭제 쿼리
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

	public ArrayList<boardData> refreshBD() { // 삭제 삽입 등의 행동 이후 refresh 쿼리
		// getall 하고 jlist에 추가

		sql = "select * from boards";
		ArrayList<boardData> datas = new ArrayList<boardData>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 다 가져와서 다시 등록
				countBoard++;

				boardData p = new boardData();
				p.bId = rs.getInt("bid");
				p.bName = rs.getString("boardname");
				datas.add(p); // product array 에 add
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

	public void getId(String uid) { // Post와 Board User가 따로 저장되어 있으므로 아이디 받아오기 위함
		id = uid;
		System.out.println("get ID : " + id);// 디버깅용 아이디 잘 받나
	}

	public void newPost(postData PD) { // 새 게시글 등록
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
			PD.pWriter = id; // pWriter에 id를 넣음

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<postData> refreshPS()// 게시물에 관한 정보 refresh
	{
		// getall 하고 jlist에 추가

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
				posts.add(p); // product array 에 add
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

	public void announce(postData PD) { // 전체 공지인 경우
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

	public void heartActive(postData PD) { //덕영 추가 
											// 하트 버튼이 눌렸을 경우 like에 1을 대입하여 Update수행
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

	public void heartShow(postData PD) { //덕영 추가
		sql = "select pid from posts where likes=?"; // id title contents image
		MainPanel2 mp;
		
		try {
			mp = new MainPanel2();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PD.likes);// 1인경우 좋아요 표시된 경우임
			// pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("pid");
				//System.out.println("id : " + id ); // heart 클릭된거 가져오기
				mp.heartChange();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
