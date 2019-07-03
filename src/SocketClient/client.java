package SocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import application.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.user;

public class client {
	private BufferedWriter os = null;
	private BufferedReader is = null;
	Socket socketOfClient = null;
	user u = null;
	thread1 t1 = new thread1();

	public client(user u) {
		// Địa chỉ máy chủ.
		final String serverHost = "localhost";
		this.u = u;
		try {
			// Gửi yêu cầu kết nối tới Server đang lắng nghe
			// trên máy 'localhost' cổng 7777.
			socketOfClient = new Socket(serverHost, 7777);

			// Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
			os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			// Luồng đầu vào tại Client (Nhận dữ liệu từ server).
			is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverHost);
			return;
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + serverHost);
			return;
		}
//		try {
//			while ((s = is.readLine()) != null) {
//				String s1[]=s.split("-");
//				if (s1[0].equals(u.getName())) {
//					FXMLLoader loader=new FXMLLoader(getClass().getResource("mainscreen.fxml"));
//					controller controll=loader.getController();
//					controll.insertMessage(s1[1]);
//					loader.setController(controll);
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		t1.start();
	}

	class thread1 extends Thread {
		public void run() {
			while (true) {
				try {
					String s = is.readLine();
					if (s != null && s.equals("OK")) {
						break;
					}
					if (s != null) {
						String s1[] = s.split("-");
						System.out.println(s);
						if (s1[0].equals(u.getName())) {
							FXMLLoader loader = new FXMLLoader();
							Pane p = loader.load(getClass().getResource("/application/mainscreen.fxml").openStream());
							controller controll=loader.getController();
							if (controll!=null)
							controll.insertMessage(s1[1]); else System.out.println(12);
							}
					}
					sleep(1000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void dataTranfer(String friend, String message) {
		try {
			os.write(friend + "-" + message);
			System.out.println(friend + "-" + message);
			os.newLine();
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void closeClient() throws IOException {
		os.write("QUIT");
		os.newLine();
		os.flush();
		t1.stop();
		os.close();
		is.close();
		socketOfClient.close();
	}
}