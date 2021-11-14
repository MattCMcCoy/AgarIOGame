package dyermccoy.agariogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Sets up the AgarIO battleground of the game.
 */
public class BattleGround extends Main {
  /**
   * creates frame rate event handler to update game.
   */
  static EventHandler<ActionEvent> handler = event -> {

    User.step(Main.mouse);

    for (int i = 0; i < AiPlayer.AiPlayers.size(); i++) {
      AiPlayer.AiPlayers.get(i).step(AiPlayer.AiPlayers.get(i).AiControl());
    }

    ACell.getCellArrayList().forEach(ACell::infected);

    ACell.getCellArrayList().forEach(ACell::eatsFood);

    ACell.getCellArrayList().forEach(ACell::eatsPlayer);

    ACell.getCellArrayList().forEach(ACell::checkBoundaries);

  };
  static Timeline game = new Timeline(new KeyFrame(Duration.millis(30), handler));

  /**
   * Creates the scenes and objects.
   */
  BattleGround() {
    createMainMenu();
    createAgarioScene();
    spawnConsumables();
    spawnCells();

    game.setCycleCount(Timeline.INDEFINITE);

  }

  /**
   * Creates the main menu scene.
   */
  private void createMainMenu() {

    menu = new StackPane();
    HTPtext = new StackPane();
    menufield = new Pane();
    strt = new Button("Start");
    strt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    HowToPlay = new Button("How to play");
    HowToPlay.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    backBttn = new Button("Back");
    backBttn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    HTP = new Text(Settings.HowTo);
    HTP.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    AGARIO = new Text("AGAR.IO");
    AGARIO.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
    menu.getChildren().add(menufield);
    menu.getChildren().add(strt);
    menu.getChildren().add(HowToPlay);
    menu.getChildren().add(AGARIO);
    HowToPlay.setTranslateY(30);
    AGARIO.setTranslateY(-60);

    backBttn.setTranslateY(HTP.getY() + 100);

    HTPtext.getChildren().add(HTP);
    HTPtext.getChildren().add(backBttn);

    MainMenu = new Scene(menu, Settings.mainMenuWidth, Settings.mainMenuHeight);
    Instruct = new Scene(HTPtext, Settings.mainMenuWidth, Settings.mainMenuHeight);


  }

  /**
   * Creates the Agario Scene.
   */
  private void createAgarioScene() {

    root = new BorderPane();
    layerPane = new StackPane();
    restart = new Button("Restart");
    restart.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
    playfield = new Pane();
    youWIN = new Text("You Win... Play Again?");
    youLOSE = new Text("You Lose... Play Again?");
    playerScore = new Label();
    playerScore.setAlignment(Pos.TOP_CENTER);
    playerScore.setTextFill(Color.BLACK);
    youLOSE.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
    youWIN.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
    youLOSE.setX(300);
    youLOSE.setY(400);
    youWIN.setX(300);
    youWIN.setY(400);
    restart.setTranslateX(400);
    restart.setTranslateY(425);

    playfield.getChildren().add(playerScore);

    layerPane.getChildren().addAll(playfield);

    root.setCenter(layerPane);

    agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);
  }

  /**
   * Spawns the consumable objects of the game.
   */
  private static void spawnConsumables() {

    for (int i = Food.foodObjects.size(); i < Settings.limitFood; i++) {

      new Food();
    }
    for (int i = Coronavirus.virusObjects.size(); i < Settings.limitVirus; i++) {

      new Coronavirus();
    }

    playfield.getChildren().addAll(Food.foodObjects);
    playfield.getChildren().addAll(Coronavirus.virusObjects);

  }

  /**
   * Spawns the cells of the game.
   */
  private static void spawnCells() {

    User = new Player();
    playfield.getChildren().add(User);

    for (int i = 0; i < Settings.limitAi; i++) {
      AiPlayer newAi = new AiPlayer();
      playfield.getChildren().add(newAi);
    }

  }

}
