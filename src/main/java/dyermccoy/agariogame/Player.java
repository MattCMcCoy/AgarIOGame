package dyermccoy.agariogame;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The class for a player cell.
 */
public class Player extends ACell {
  // creates player
  public Player() {
    super(Color.BLUE);
  }


  /**
   * relocates player based on mouse movement.
   *
   * @param mouse the point of the mouse
   */
  @Override
  public void step(Point mouse) {
    Point position = getPoint();
    Point mousePoint = new Point(mouse.x, mouse.y);
    position.moveDistanceTowardsPoint(mousePoint, speed);
    setCenterX(position.x);
    setCenterY(position.y);
    Main.playerScore.relocate(position.x, position.y);

    Main.playerScore.setText(Integer.toString((int) (getRadius())));
    Main.playerScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
            20));

  }
}
