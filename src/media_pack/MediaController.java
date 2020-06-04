package media_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;



public class MediaController implements Initializable {
	@FXML ImageView imageView;
	@FXML MediaView mediaView;
	@FXML Button btnPlay, btnPause, btnStop;
	@FXML ProgressBar progressBar;
	@FXML ProgressIndicator progressIndicator;
	@FXML Label labelTime;
	@FXML Slider sliderVolume;
	
	boolean endOfMedia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Media media = new Media(getClass().getResource("/medias/video.m4v").toString());
		MediaPlayer player = new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		
		
		// 레디상태
		player.setOnReady(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(false); // 사용 O
				btnStop.setDisable(true);  // 사용 X
				btnPause.setDisable(true); // 사용 X
			}
		});
		
		player.setOnPlaying(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(true); 
				btnStop.setDisable(false);
				btnPause.setDisable(false); 
			}
		});
		
		player.setOnPaused(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(false); 
				btnStop.setDisable(false);
				btnPause.setDisable(true); 
			}
		});
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(false); 
				btnStop.setDisable(true);
				btnPause.setDisable(true); 
			}
		});
		player.setOnStopped(new Runnable() {
			@Override
			public void run() {
				btnPlay.setDisable(false); 
				btnStop.setDisable(true);
				btnPause.setDisable(true); 
			}
		});
		
				
		
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(endOfMedia) {
					player.stop();
					player.seek(player.getStartTime());					
				}
				player.play();
				endOfMedia = false; 
			}
		});
		// ↑↓(람다식)같음
		btnStop.setOnAction((e) -> player.stop());
		btnPause.setOnAction((e) -> player.pause());
		
	}

}
