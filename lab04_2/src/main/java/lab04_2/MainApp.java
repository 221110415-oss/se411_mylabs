package lab04_2;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
		    primaryStage.setTitle("My Project");


		    primaryStage.show();


		  } catch (Exception e) {
		    e.printStackTrace();
		  }

	}
}
