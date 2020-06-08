package stages_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Root.fxml
// AddForm.fxml
// ScoreChart.fxml
// StageController.java

public class StageMain extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
//		FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
		
		// StageController 에 stage 값을 넘겨준다. , root.fxml의 controller를 호출
//		StageController cont = loader.getController();
//		cont.setPrimaryStage(stage);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
