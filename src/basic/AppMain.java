package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application {
	// 실행순서 AppMain -> init -> start -> 종료시 stop
	
	public AppMain() {
		System.out.println(Thread.currentThread().getName() + " : AppMain() 실행.");
	}
		
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init() 실행.");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName() + " : start() 실행.");
		VBox root = new VBox();				// VBox생성
		root.setPrefWidth(350);				// 넓이(폭)
		root.setPrefHeight(150); 			// 높이
		root.setAlignment(Pos.CENTER);		// 컨트롤을 중앙정렬
		root.setSpacing(20); 				// 컨트롤 수직 간격
		
		Label label = new Label();			// Label 컨트롤 생성
		label.setText("Hello, JavaFX");		// text 속성 설정
		label.setFont(new Font(50));		// font 속성 설정
		
		Button button = new Button();		// Button 컨트롤 생성
		button.setText("확인");				// text 속성 설정
		button.setOnAction(event -> Platform.exit());	// 클릭이벤트 처리
		
		
		root.getChildren().add(label);		// VBox 자식으로 Label 컨트롤 추가
		root.getChildren().add(button);		// VBox 자식으로 Button 컨트롤 추가
		
		Scene scene = new Scene(root);		// VBox를 루트 컨테이너로 해서 Scene생성
		
		primaryStage.setTitle("AppMain");	// 윈도우의 제목 설정
		primaryStage.setScene(scene);		// 윈도우에  장면 설정
		primaryStage.show();				// 윈도우에 보여주기
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : stop() 실행.");
	}
	
	// 제일먼저 실행하는 메소드
	public static void main(String[] args) {
		Application.launch(args); // AppMain 객체 생성 및 윈도우 생성
	}
	
	
}
