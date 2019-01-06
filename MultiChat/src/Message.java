
public class Message {
	
	String id,  msg ,  type;
	String password;
	
	
	
	public Message(String id, String msg ,String password, String type){
		this.id = id;
		this.msg = msg;
		this.password= password;
		this.type = type;
	
	}

	public Message() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
