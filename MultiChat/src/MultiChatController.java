import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class MultiChatController implements Runnable{

	private final MultiChatUI v;
	private final MultiChatData chatData;
	Logger logger;
	Socket socket;
	Boolean status;
	BufferedReader inMsg = null;
	PrintWriter outMsg = null;
	Gson gson = new Gson();
	String ip = "10.0.0.3";
	Message m;
	Thread thread;
	
	
	
	public MultiChatController(MultiChatData chatData, MultiChatUI v) {
		logger = Logger.getLogger(this.getClass().getName());
		
		this.chatData = chatData;
		this.v =v;
	}

	public void appMain() {
		chatData.addObj(v.msgOut);
	
		v.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj == v.exitButton) {
					System.exit(0);
			
				}else if (obj == v.loginButton) {
					v.id=v.idInput.getText();
					v.outLabel.setText(" 대화명 : " + v.id);
					v.cardLayout.show(v.tab, "logout");
					connectServer();
				}else if (obj == v.logoutButton) {
					outMsg.println(gson.toJson(new Message(v.id,"","","logout")));
					v.msgOut.setText("");
					v.cardLayout.show(v.tab, "login");
					outMsg.close();
					try {
						inMsg.close();
						socket.close();
					}catch(IOException ex) {
						ex.printStackTrace();
					}
					status = false;
				}else if (obj == v.msgInput) {
					outMsg.println(gson.toJson(new Message(v.id,"",v.msgInput.getText(),"msg")));
					v.msgInput.setText("");
				}
			}
			
			
		
			});
		
		
	}

	public void connectServer() { // login button 눌렀을 때 호출
		//서버와 연결, 입출력 스트림 만든 후 메시지 수신에 필요한 스레드 생성
		
		try {
		socket = new Socket(ip, 8888);
		logger.info("[Client]Server 연결 성공!"); 
		
		inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outMsg =new PrintWriter(socket.getOutputStream(),true);
		
		m =new Message(v.id,"","","login");
		outMsg.println("gson.toJson(m)");
		
		thread = new Thread(this);
		thread.start();
		} catch(Exception e) {
			logger.warning("[MultiChatUI] connectServer() Exception 발생!");
			e.printStackTrace();
		}
		
	}

	public void run() {
		String msg;
		status =true;
		
		while(status) {
			try {
				msg = inMsg.readLine();
				m=gson.fromJson(msg, Message.class);
				
				chatData.refreshData(m.getId()+">"+m.getMsg()+"\n");
				
				v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
			}catch(IOException e) {
				logger.warning("[MultiChatUI] 메시지 스트림 종료");
			}
		}
		logger.info("[MultiChatUI]" + thread.getName() + "메시지 수신 스레드 종료됨!");
		
	}

	public static void main(String[] args){
		MultiChatController app = new MultiChatController(new MultiChatData(), new MultiChatUI());
		app.appMain();
		
	}
}
