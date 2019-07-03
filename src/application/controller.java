package application;

import SHA.*;

import SocketClient.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.GroupLayout.Alignment;

import database.connect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import user.user;

public class controller implements Initializable {
	@FXML
	private TextField find = new TextField();
	@FXML
	private ScrollPane showMessage = new ScrollPane();
	@FXML
	private ScrollPane showFriend = new ScrollPane();
	@FXML
	private TextField insertFriend = new TextField();
	@FXML
	private TextField inputMessage = new TextField();
	@FXML
	private Label labelName=new Label();

	private user u = new user();
	connect con = new connect();
	String query = "";
	private String friend="";
	client c;
	private SHA256 sha=new SHA256();

	public controller(user u,client c) {
		super();
		this.u = u;
		this.c=c;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		showFriend();
		labelName.setText(u.getName());
		insertFriend.setOnKeyPressed(event -> {
			KeyCode ke = event.getCode();
			if (ke == KeyCode.ENTER) {
				insertFriend(insertFriend.getText());
				showFriend();
			}
		});
		inputMessage.setOnKeyPressed(event -> {
			KeyCode kc = event.getCode();
			if (kc == KeyCode.ENTER) {
				System.out.println(friend);
				c.dataTranfer(friend, inputMessage.getText());
				inputMessage(inputMessage.getText());
				inputMessage.setText("");
			}
		});

	}

	public void insertFriend(String name) {
		if (name.equals(u.getName()))
			return;
		String query = "INSERT INTO friend_" + u.getName() + " (name) VALUES ('" + name + "');";
		try {
			Connection c = connect.getMySQLConnection();
			Statement stmt=c.createStatement();
			ResultSet res = stmt.executeQuery("Select * from user where username='"+sha.getSHA256(name)+"';");
			if (res.next()) {
				Statement stmt1 = c.createStatement();
				stmt1.executeUpdate(query);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showFriend() {
		showFriend.setContent(null);
		String query = "SELECT * FROM friend_" + u.getName() + ";";
		try {
			Connection c = connect.getMySQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			VBox root = new VBox();
			while (rs.next()) {
				Label l = new Label(rs.getString(1));
				l.setPadding(new Insets(10, 0, 5, 8));
				l.setOnMouseClicked(event -> {
					showMessage(l.getText());
					friend=l.getText();
				});
				root.getChildren().add(l);
			}
			showFriend.setContent(root);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showMessage(String name) {
		try {
			Connection c = connect.getMySQLConnection();
			DatabaseMetaData meta = c.getMetaData();
			ResultSet res = meta.getTables(null, null, u.getName() + "__" + name, new String[] { "TABLE" });
			ResultSet res2 = meta.getTables(null, null, name + "__" + u.getName(), new String[] { "TABLE" });
			Statement stmt = c.createStatement();
			if (res.next()) {
				query = u.getName() + "__" + name;
				showMessage.setContent(null);
				System.out.println(query);
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + query + ";");
				VBox root = new VBox();
				while (rs.next()) {
					Label l = new Label(rs.getString(2));
					if (rs.getString(1).equals(u.getName()))
						l.setTextAlignment(TextAlignment.RIGHT);
					else
						l.setTextAlignment(TextAlignment.LEFT);
					root.getChildren().add(l);
					showMessage.setContent(root);
				}
			} else {
				if (res2.next()) {
					query = name + "__" + u.getName();
					showMessage.setContent(null);
					System.out.println(query);
					ResultSet rs = stmt.executeQuery("SELECT * FROM " + query + ";");
					VBox root = new VBox();
					while (rs.next()) {
						Label l = new Label(rs.getString(2));
						l.setStyle("-fx-margin-left:0;");
						if (rs.getString(1).equals(u.getName()))
							l.setTextAlignment(TextAlignment.RIGHT);
						else
							l.setTextAlignment(TextAlignment.LEFT);
						root.getChildren().add(l);
						showMessage.setContent(root);
					}
				} else {
					stmt.executeUpdate("CREATE TABLE " + u.getName() + "__" + name + "(id varchar(100) not null,"
							+ "message varchar(500));");
					query = u.getName() + "__" + name;
					showMessage.setContent(null);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inputMessage(String message) {
		if (query == "")
			return;
		String q = "INSERT INTO " + query + "(id,message) values('" + u.getName() + "','" + message + "');";
		try {
			Connection c = connect.getMySQLConnection();
			Statement stmt = c.createStatement();    
			stmt.executeUpdate(q);
			insertMessage(message);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertMessage(String message) {
		Pane root = (Pane) showMessage.getContent();
		Label l = new Label(message);
		l.setLayoutX(root.getLayoutX()-l.getLayoutX());
		root.getChildren().add(l);
		showMessage.setContent(root);
	}
}
