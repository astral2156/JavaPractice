

public class boardData { //board data �Խ��� ���� ����
	// MVC �� M�� �ش�.

	public String userId;
	
	public int bId;
	public String bName;
	@Override
	public String toString() {
		return "boardData [bId=" + bId + ", bName=" + bName + "]";
	}

}
class postData { // post data �Խù� ���� ����

	   public int pId;
	   public int pboardId;
	   public String pTitle;
	   public String pImage;
	   public String pContents;
	   public String pWriter;
	   public int likes;
	   public int pbid;
	   
	   public String toString() {
	      return "postData [pId=" + pId + ", pTitle=" + pTitle + ", pImage=" + pImage + ", pContents=" + pContents
	            + ", pwriter=" + pWriter + ", likes=" + likes + "]";
	   }

	}