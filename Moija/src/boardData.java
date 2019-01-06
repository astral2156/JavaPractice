

public class boardData { //board data 게시판 정보 관리
	// MVC 의 M에 해당.

	public String userId;
	
	public int bId;
	public String bName;
	@Override
	public String toString() {
		return "boardData [bId=" + bId + ", bName=" + bName + "]";
	}

}
class postData { // post data 게시물 정보 관리

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