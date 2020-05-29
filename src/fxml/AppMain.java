package fxml;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();						// Hbox 컨테이너 생성
		hbox.setPadding(new Insets(10,10,10,10));	// 안쪽 여백 설정
		hbox.setSpacing(10);						// 컨트롤 간의 수평 간격 설정
		
		TextField textField = new TextField();		// TextField 컨트롤 생성
		textField.setPrefWidth(200);				// TextField의 폭 설정
		textField.setPrefHeight(100);
		
		Button button = new Button();				// Button 컨트롤 생성
		button.setText("확인");						// Button 글자 설정
		button.setPrefSize(100, 100);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textField.setText("확인을 눌렀습니다");
			}
		});
		
		Button button1 = new Button();				// Button 컨트롤 생성
		button1.setText("취소");						// Button 글자 설정
		button1.setPrefSize(100, 100);
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textField.setText(null);
			}
		});
		ObservableList list = hbox.getChildren();	// Hbox의 ObservableList 얻기
		list.add(textField);						// TextField 컨트롤 배치
		list.add(button);							// Button의 컨트롤 배치
		list.add(button1);
		
		Scene scene = new Scene(hbox);				// 장면생성
		
		primaryStage.setTitle("레이아웃");			// 윈도우 창 제목설정
		primaryStage.setScene(scene);				// 윈도우 창에 장면 설정
		primaryStage.show();						// 윈도우 창 보여주기
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println("닫기 버튼 클릭.");
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
