package board_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;
import view_pack.Phone;

public class BoardController implements Initializable {
	Connection conn;

	@FXML
	TableView<Board> tableView;
	@FXML TextField title;
	@FXML TextField exitDate;
	@FXML ComboBox<String> publicity;
	@FXML TextArea content;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// 정적
		ObservableList<Board> boardList = getBoardList();
//		boardList.add(new Board("test", "1234", "공개", "2020/06/04", "내용.."));

		
		// tableView에서 클릭시 나오게
		tableView.getSelectionModel().selectedItemProperty().addListener
		(new ChangeListener<Board>() {
			@Override
			public void changed(ObservableValue<? extends Board> arg0, Board oldVal, Board newVal) {
				title.setText(newVal.getContent());
				publicity.setValue(newVal.getPublicity());
				exitDate.setText(newVal.getExitDate());
				content.setText(newVal.getContent());
			}
			
		});
			

		
		

		// title column정의
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();
		// CellDataFeatures 입력값 , ObservableValue 리턴값
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
		});
		tcTitle.setText("제목");

		// 37~49라인과 같음(컬럼정의)
		// exitDate column정의
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));
		tcExitDate.setText("종료일자");

		tableView.getColumns().add(tcTitle);
		tableView.getColumns().add(tcExitDate);

		tableView.setItems(boardList);

	} // end of initialize()

	public ObservableList<Board> getBoardList() {
		ObservableList<Board> list = FXCollections.observableArrayList();
		String sql = "select title, publicity, exit_date, content from board";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getString("title"), null, rs.getString("publicity"),
						rs.getString("exit_date"), rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of getBoardList

}
