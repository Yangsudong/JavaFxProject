import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppRoot extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 'Root.fxml'을 불러오겠다라는 의미 (지금 같은 경로이므로 상대경로를 써준다, 
		// 다른경로일경우 : "../com/yedam/Root.fxml"이런식으로 써줌)
		// getClass()에서 getResource : 파일을 찾는다는 뜻
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
				
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("FXML sample");
	
	}
	
	
	public static void main(String[] args) {
			launch(args);
	}
}
