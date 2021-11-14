package dyermccoy.agariogame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main class for the game.
 */
public class Main extends Application {

  public static Player User;
  static Pane playfield, menufield;
  static StackPane menu, HTPtext, layerPane;
  static BorderPane root;
  static Point mouse = new Point();

  static Scene MainMenu;

  static Scene Instruct, agario;

  static Label playerScore;
  static Button strt, HowToPlay, backBttn, restart;
  static Text AGARIO, HTP, youLOSE, youWIN;

  /**
   * Starts the scene of the game.
   *
   * @param primaryStage the current stage of the game.
   */
  @Override
  public void start(Stage primaryStage) {
    // Creates Scenes and Game
    new BattleGround();

    // sets scene to main menu on launch
    primaryStage.setScene(MainMenu);
    primaryStage.setTitle("Main Menu");
    primaryStage.show();

    // event filters to switch between Scenes and track mouse movement
    HowToPlay.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
      primaryStage.setScene(Instruct);
      primaryStage.setTitle("How To Play");
    });

    backBttn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
      primaryStage.setScene(MainMenu);
      primaryStage.setTitle("MainMenu");
    });

    strt.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

      primaryStage.setScene(agario);
      primaryStage.setTitle("We do be eating circles (o.O)");
      BattleGround.game.play();

    });

    agario.addEventFilter(MouseEvent.ANY, e -> mouse.setLocation(e.getX(), e.getY()));

    agario.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ESCAPE) {
        BattleGround.game.stop();
        System.exit(0);
      }
    });

    // restarts game, clears cell array list
    restart.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

      ACell.getCellArrayList().clear();
      primaryStage.close();
      new Main().start(primaryStage);

    });

  }

  public static void main(String[] args) {
    launch(args);
  }
}
