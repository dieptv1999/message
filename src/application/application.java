package application;
	
import java.io.IOException;

import SocketClient.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.user;


public class application extends Application {
	private user u=new user();
	private controller controll=null;
	private FXMLLoader loader=null;
	Stage stage=new Stage();
	public application(user u) {
		// TODO Auto-generated constructor stub
		this.u=u;
		start(stage);
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			loader=new FXMLLoader(getClass().getResource("mainscreen.fxml"));
			client c=new client(u);
			controll=new controller(u,c);
			loader.setController(controll);
			Pane root =loader.load();
			Scene scene = new Scene(root,628,413);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Message");
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(event->{
				try {
					c.closeClient();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}