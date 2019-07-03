package ClientSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import application.controller;
import javafx.fxml.FXMLLoader;
import user.user;

public class client {
	private OutputStream os = null;
	private InputStream is = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	Socket socketOfClient = null;

	public client(user u) {
		// Địa chỉ máy chủ.
		final String serverHost = "localhost";

		try {
			// Gửi yêu cầu kết nối tới Server đang lắng nghe
			// trên máy 'localhost' cổng 7777.
			socketOfClient = new Socket(serverHost, 7777);

			// Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
			os = socketOfClient.getOutputStream();
			oos = new ObjectOutputStream(os);
			// Luồng đầu vào tại Client (Nhận dữ liệu từ server).
			is = socketOfClient.getInputStream();
			ois = new ObjectInputStream(is);

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverHost);
			return;
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + serverHost);
			return;
		}
		data d=null;
		try {
			while ((d = (data)ois.readObject()) != null) {
				if (d.getFriend().equals(u.getName())) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("mainscreen.fxml"));
					controller controll=loader.getController();
					controll.insertMessage(d.getMessage());
					loader.setController(controll);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dataTranfer(String friend, String message) {
		data d = new data(friend, message);
		try {
			oos.writeObject(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeClient() throws IOException {
		os.flush();
		oos.close();
		os.close();
		is.close();
		ois.close();
		socketOfClient.close();
	}

	public class data {
		private String friend;
		private String message;

		public data(String friend, String message) {
			super();
			this.friend = friend;
			this.message = message;
		}

		public String getFriend() {
			return friend;
		}

		public void setFriend(String friend) {
			this.friend = friend;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}