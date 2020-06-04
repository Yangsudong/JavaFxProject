package board_pack;

import javafx.beans.property.SimpleStringProperty;

public class Board {
	private SimpleStringProperty title;
	private SimpleStringProperty password;
	private SimpleStringProperty publicity;
	private SimpleStringProperty exitDate;
	private SimpleStringProperty content;

	Board(String title, String password, String publicity, String exitDate, String content) {
		this.title = new SimpleStringProperty(title);
		this.password = new SimpleStringProperty(password);
		this.publicity = new SimpleStringProperty(publicity);
		this.exitDate = new SimpleStringProperty(exitDate);
		this.content = new SimpleStringProperty(content);
	}

	// title
	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getTitle() {
		return this.title.get();
	}

	public SimpleStringProperty titleProperty() {
		return this.title;
	}

	// password
	public void password(String password) {
		this.password.set(password);
	}

	public String getPassword() {
		return this.password.get();
	}

	public SimpleStringProperty titlePassword() {
		return this.password;
	}

	// publicity
	public void setPublicity(String publicity) {
		this.publicity.set(publicity);
	}

	public String getPublicity() {
		return this.publicity.get();
	}

	public SimpleStringProperty publicityProperty() {
		return this.publicity;
	}

	// exitDate
	public void setExitDate(String exitDate) {
		this.exitDate.set(exitDate);
	}

	public String getExitDate() {
		return this.exitDate.get();
	}

	public SimpleStringProperty exitDateProperty() {
		return this.exitDate;
	}
	
	// content
	public void setContent(String content) {
		this.content.set(content);
	}

	public String getContent() {
		return this.content.get();
	}

	public SimpleStringProperty contentProperty() {
		return this.content;
	}

}