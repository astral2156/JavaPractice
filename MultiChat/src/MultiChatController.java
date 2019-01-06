
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;
import com.google.gson.Gson;

public class MultiChatController extends Thread {

	private final MultiChatUI v;
	private final MultiChatData chatData;
	Message m;
	Gson gson = new Gson();
	boolean status = true;
	Logger logger;
	String ip = "127.0.0.1";
	Socket socket;
	BufferedReader inMsg = null;
	PrintWriter outMsg = null;
	Thread thread;
	

	public MultiChatController(MultiChatData chatData, MultiChatUI v) {
		logger = Logger.getLogger(this.getClass().getName());

		this.chatData = chatData;
		this.v = v;

	}

	public void appMain() {

		chatData.addObj(v.msgOut);
		
		v.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				
				if (obj == v.exitButton) {
					System.exit(0);
				} else if (obj == v.loginButton) {
					v.id = v.idInput.getText();
					v.outLabel.setText("ID : " + v.id);
					v.cardLayout.show(v.tab, "logout");
					connectServer();
				} else if (obj == v.logoutButton) {
					outMsg.println(gson.toJson(new Message(v.id, "", "", "logout")));
					v.msgOut.setText("");
					v.cardLayout.show(v.tab, "login");
					outMsg.close();
					try {
						inMsg.close();
						socket.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					status = false;
				}else if (obj ==v.msgInput) {
					outMsg.println(gson.toJson(new Message(v.id,v.msgInput.getText(),"","")));
					v.msgInput.setText("");
				}
			}

		});

		
	}

	public void connectServer() {
		try {
			
			socket = new Socket(ip, 8888);
			logger.info("[Client]Server ");
			inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outMsg = new PrintWriter(socket.getOutputStream(), true);
			m = new Message(v.idInput.getText(), "", "", "login");
			outMsg.println(gson.toJson(m));
			thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			logger.warning("[MultiChatUI]connectServer() Exception!");
			e.printStackTrace();
		}
	}
	
	public void run() {
		String msg;
		Message m = new Message();
		Gson gson = new Gson();
		//status = true;
		while(status) {
			try {
				msg = inMsg.readLine();
				m = gson.fromJson(msg,Message.class);
				chatData.refreshData(m.getId()+">"+m.getMsg()+"\n");
				v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
				
			}catch(IOException e) {
				logger.warning("[MultiChatUI] Exception!");
			}
		}
		logger.info("[MultiChatUI]"+thread.getName()+"ddd");
	}

	public static void main(String args[]) {
		MultiChatController app = new MultiChatController(new MultiChatData(), new MultiChatUI());
		app.appMain();

	}

}
