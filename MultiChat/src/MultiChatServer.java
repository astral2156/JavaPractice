

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.google.gson.Gson;

public class MultiChatServer {

	private ServerSocket ss = null;
	private Socket s = null;
	MultiChatUI v;

	ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
	Logger logger;

	public void start() {
		logger = Logger.getLogger(this.getClass().getName());

		try {
			ss = new ServerSocket(8888);
			logger.info("MultiChatServer start");

			while (true) {
				s = ss.accept();
				ChatThread chat = new ChatThread();
				chatThreads.add(chat);
				chat.start();
			}

		} catch (Exception e) {
			logger.info("[MultiChatServer]start() Exception!!");
			e.printStackTrace();
		}
	}

	void msgSendAll(String msg) {
		for (ChatThread ct : chatThreads) {
			ct.outMsg.println(msg);
		}
	}

	class ChatThread extends Thread {

		String msg;
		Message m;
		Gson gson = new Gson();
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;

		boolean status = true;

		public void run() {

			try {

				inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
				outMsg = new PrintWriter(s.getOutputStream(), true);

				while (status) {
					msg = inMsg.readLine();
					System.out.println(msg);

					m = gson.fromJson(msg, Message.class);

				//	System.out.println(m.getType());
					if (m.getType().equals("logout")) {
						chatThreads.remove(this);
						msgSendAll(gson.toJson(new Message(m.getId(), "", "is logout", "server")));
						status = false;
					} else if (m.getType().equals("login")) {
						msgSendAll(gson.toJson(new Message(m.getId(), "", "is login", "server")));
					} else {
						msgSendAll(msg);
					}
				}

				this.interrupt();
				logger.info(this.getName() );

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String args[]) {

		MultiChatServer s = new MultiChatServer();
		s.start();
	}

}
