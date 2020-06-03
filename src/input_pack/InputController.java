package input_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InputController implements Initializable {
	@FXML TextField txtTitle;
	@FXML PasswordField txtPassword;
	@FXML ComboBox<String> comboPublic;
	@FXML DatePicker dateExit;
	@FXML TextArea txtContent;
	@FXML Button btnReg;
	
	Connection conn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	//url주소 고정  @localhost:포트번호:이름
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");	// ("주소","계정아이디","비밀번호")
		} catch (Exception e) {		
			e.printStackTrace();		
		}
	}
	
	public void handleBtnRegAction(ActionEvent e) {
		if(txtTitle.getText() == null || txtTitle.getText().equals("")) {
			messageDialog("제목을 입력하세요.");
		}
		else if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			messageDialog("비밀번호 입력하세요.");
		}
		else if(comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			messageDialog("공개여부를 입력하세요");
		}
		else if(dateExit.getValue() == null || dateExit.getValue().equals("")) {
			messagePopup("종료일을 입력하세요");
		}
		else if(txtContent.getText() == null || txtContent.getText().equals("")) {
			messagePopup("내용 입력하세요");
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String sql = "insert into board(title,password,publicity,exit_date,content)" 
					+ " values(?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtTitle.getText());
				pstmt.setString(2, txtPassword.getText());
				pstmt.setString(3, comboPublic.getValue());
				pstmt.setString(4, dateExit.getValue().format(formatter));
				pstmt.setString(5, txtContent.getText());
				
				int r = pstmt.executeUpdate();
				System.out.println(r + "건 입력됨");
				
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
			// 각 필드 초기화
			txtTitle.setText(null);
			txtPassword.setText(null);
			comboPublic.setValue("공개");
			dateExit.setValue(null);
			txtContent.setText(null);
		}	// end of if
	}	// end of handleBtnRegAction
		
		
	
	public void handleBtnCancelAction(ActionEvent e) { 
		Platform.exit();
	}
	public void messageDialog(String message) {
		Stage customStage = new Stage(StageStyle.UTILITY);
		customStage.initModality(Modality.WINDOW_MODAL);
		customStage.initOwner(btnReg.getScene().getWindow()); // 주인 window
		customStage.setTitle("확인");
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400, 150); 	// 외부 컨테이너 size 설정
		
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/panes/icons/dialog-info.png"));
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);	// 크기에 맞춤
		imageView.setLayoutX(15);
		imageView.setLayoutY(15);
		
		Button button = new Button("확인");
		button.setLayoutX(336);
		button.setLayoutY(104);
		button.setOnAction(e -> customStage.close()); // 확인 누르면 닫기
		
		Label label = new Label(message);
		label.setLayoutX(87);
		label.setLayoutY(33);
		label.setPrefHeight(15);
		label.setPrefWidth(290);
		
		ap.getChildren().add(imageView);
		ap.getChildren().add(button);
		ap.getChildren().add(label);
		
		Scene scene = new Scene(ap);
		customStage.setScene(scene);
		customStage.show();
		
	}
	
	public void messagePopup(String message) {
		// 컨테이너 (HBox) 생성.
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: red; -fx-background-radius: 20;"); // <- 배경색지정, 모서리 둥글게 
		hbox.setAlignment(Pos.CENTER);
		
		// 컨트롤(ImageView)
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/panes/icons/dialog-info.png"));
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		
		// 컨트롤(Label)
		Label label = new Label();
		HBox.setMargin(label, new Insets(0,5,0,5));
		label.setText(message);
		label.setStyle("-fx-text-fill:white;"); 	// <-글자색 :흰색
		
		// 컨테이너에 컨트롤 담기.
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(label);
		
		// 팝업생성. 컨테이너 담아서 팝업 호출.
		Popup popup = new Popup();
		popup.getContent().add(hbox);
		popup.setAutoHide(true);	// <--포커스를 잃으면 팝업이 사라짐
		popup.show(btnReg.getScene().getWindow());
	}
	
}