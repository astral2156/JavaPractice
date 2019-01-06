
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;

public class UserAct { //회원 관리

	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	registDialog dialog= new registDialog();

	public UserAct() { // DB 연결
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/javadb?&serverTimezone=UTC", "root", "kim27903");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

	public void dbClose() { //DB 닫기
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
	//id 받아와서 id 체크
	public boolean idCheck(String id) { // id체크하는 부분
		boolean result =true;
		
		try {
			
			 ps = con.prepareStatement("SELECT * FROM user WHERE id=?");
	            ps.setString(1, id.trim()); //id 받아와서 where문
	            rs = ps.executeQuery(); //실행
	            if (rs.next())
	                result = false; //레코드가 존재하면 false
	 
	        } catch (SQLException e) {
	            System.out.println(e + "=>  getIdByCheck fail");
	        } finally {
	            dbClose();
	        }
	 
		
		return result;
		
	} 
	
	public int userRegist(String id, String pw, String email) {  // user 등록하는 부분
        int result = 0;
        try {
        	dialog = new registDialog();
        	System.out.println("필드 값 갖오기" +id + pw + email);
            ps = con.prepareStatement("insert into user values(?,?,?)");
            ps.setString(1, id);
            ps.setString(2, pw);
            ps.setString(3, email);
 
            result = ps.executeUpdate(); //실행 -> 저장
			System.out.println("userRegist excuted");
 
        } catch (SQLException e) {
            System.out.println(e + "=> user insert fail");
        } finally {
            dbClose();
        }
 
        return result;
 
    }//userListInsert()
	
	public String loginCheck(String id, String pw) { // 로그인 체크
		
        try {
        	System.out.println(" login Check screen : "+id + pw);
        	ps = con.prepareStatement("SELECT ID, PW FROM user WHERE ID =?");
        	ps.setString(1, id);
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		if(pw.equals(rs.getString(2))) {
        			System.out.println("로그인 성공");
        			return "login";
        		}else {
        			System.out.println("로그인 실패");
            			return "fail";
            			
        		}
        	}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    return "none";// none은 의미 없음 이미 login return이 위에서 완료됨  0103 
    // 이따가 확인 해보기. post 등록할 때 uid등록
    }
	
	public int getPostNumber(String id) { // 게시물의 갯수 체크
		System.out.println("get Post Number id는? = " + id);
		int count=0;
		try {
			boardData bd= new boardData();
		
			//boardData에서 아이디 가져오고 그걸로 쿼리 수행 
			// boardData 슈정
        	ps = con.prepareStatement("SELECT COUNT(*) FROM posts WHERE pWriter =?");
        	ps.setString(1, id);
        	rs = ps.executeQuery();
        	
        	//System.out.println("포스트 갯수 "+rs.getRow());
			if(rs.next()) {
        		count =rs.getInt(1);
        	}
			System.out.println("포스트 갯수 "+count);

    		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
}
