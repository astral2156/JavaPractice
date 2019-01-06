
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;

public class UserAct { //ȸ�� ����

	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	registDialog dialog= new registDialog();

	public UserAct() { // DB ����
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/javadb?&serverTimezone=UTC", "root", "kim27903");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

	public void dbClose() { //DB �ݱ�
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
	
	//id �޾ƿͼ� id üũ
	public boolean idCheck(String id) { // idüũ�ϴ� �κ�
		boolean result =true;
		
		try {
			
			 ps = con.prepareStatement("SELECT * FROM user WHERE id=?");
	            ps.setString(1, id.trim()); //id �޾ƿͼ� where��
	            rs = ps.executeQuery(); //����
	            if (rs.next())
	                result = false; //���ڵ尡 �����ϸ� false
	 
	        } catch (SQLException e) {
	            System.out.println(e + "=>  getIdByCheck fail");
	        } finally {
	            dbClose();
	        }
	 
		
		return result;
		
	} 
	
	public int userRegist(String id, String pw, String email) {  // user ����ϴ� �κ�
        int result = 0;
        try {
        	dialog = new registDialog();
        	System.out.println("�ʵ� �� ������" +id + pw + email);
            ps = con.prepareStatement("insert into user values(?,?,?)");
            ps.setString(1, id);
            ps.setString(2, pw);
            ps.setString(3, email);
 
            result = ps.executeUpdate(); //���� -> ����
			System.out.println("userRegist excuted");
 
        } catch (SQLException e) {
            System.out.println(e + "=> user insert fail");
        } finally {
            dbClose();
        }
 
        return result;
 
    }//userListInsert()
	
	public String loginCheck(String id, String pw) { // �α��� üũ
		
        try {
        	System.out.println(" login Check screen : "+id + pw);
        	ps = con.prepareStatement("SELECT ID, PW FROM user WHERE ID =?");
        	ps.setString(1, id);
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		if(pw.equals(rs.getString(2))) {
        			System.out.println("�α��� ����");
        			return "login";
        		}else {
        			System.out.println("�α��� ����");
            			return "fail";
            			
        		}
        	}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    return "none";// none�� �ǹ� ���� �̹� login return�� ������ �Ϸ��  0103 
    // �̵��� Ȯ�� �غ���. post ����� �� uid���
    }
	
	public int getPostNumber(String id) { // �Խù��� ���� üũ
		System.out.println("get Post Number id��? = " + id);
		int count=0;
		try {
			boardData bd= new boardData();
		
			//boardData���� ���̵� �������� �װɷ� ���� ���� 
			// boardData ����
        	ps = con.prepareStatement("SELECT COUNT(*) FROM posts WHERE pWriter =?");
        	ps.setString(1, id);
        	rs = ps.executeQuery();
        	
        	//System.out.println("����Ʈ ���� "+rs.getRow());
			if(rs.next()) {
        		count =rs.getInt(1);
        	}
			System.out.println("����Ʈ ���� "+count);

    		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
}
