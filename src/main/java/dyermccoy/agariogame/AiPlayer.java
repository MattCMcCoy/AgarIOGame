package dyermccoy.agariogame;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * the class for Ai player cells, allows for them to find the closest food or cell.
 */
public class AiPlayer extends ACell {

  Random random = new Random();
  public static ArrayList<AiPlayer> AiPlayers = new ArrayList<>();
  public int aggressiveness = random.nextInt(20) + 1;

  public AiPlayer() {
    super(Color.GRAY);
    Color c = randomColor();
    setStroke(c);
    setFill(c.deriveColor(1, 1, 1, .3));
    AiPlayers.add(this);
  }

  /**
   * Decides whether there is an eatable cell within radius, or if it
   * should go towards the closest food.
   *
   * @return a new point
   */
  public Point AiControl() {
    if (getClosestCell().getPoint().distance(this.getPoint()) <= 200 + getRadius()) {
      return playerInteraction(getClosestCell());
    } else {
      return getClosestFood();
    }
  }

  /**
   * finds the closest food object.
   *
   * @return a new point for the food object.
   */
  private Point getClosestFood() {
    Point food = Food.foodObjects.get(0).getPoint();
    double closestDistance = getPoint().distance(food);
    for (int i = 0; i < Food.foodObjects.size(); i++) {
      Food checkedFood = Food.foodObjects.get(i);
      if (checkedFood.equals(this)) {
        continue;
      }
      if (getPoint().distance(checkedFood.getPoint()) < closestDistance) {
        closestDistance = getPoint().distance(checkedFood.getPoint());
        food = checkedFood.getPoint();
      }
    }
    return food;

  }


  /**
   * Depending on the aggressiveness of the cell decides whether it should go after the cell.
   *
   * @param c the cell object to be compared to
   * @return a new point to go to
   */
  private Point playerInteraction(ACell c) {
    Point cell;
    if (c.getRadius() >= this.getRadius() + aggressiveness - 1) {
      cell = c.getPoint().getOppositePoint(this.getPoint());
    } else if (c.getRadius() < this.getRadius() - aggressiveness) {
      cell = c.getPoint();
    } else {
      return getClosestFood();
    }
    return cell;

  }

  //finds the closest cell

  /**
   * Finds the closest cell to this.
   *
   * @return the closest cell
   */
  private ACell getClosestCell() {
    Point cell = getCellArrayList().get(0).getPoint();
    double closestDistance = getPoint().distance(cell);
    ACell c = getCellArrayList().get(0);
    for (int i = 0; i < getCellArrayList().size(); i++) {
      ACell checkedACell = getCellArrayList().get(i);
      if (checkedACell.equals(this)) {
        continue;
      }
      if (getPoint().distance(checkedACell.getPoint()) < closestDistance) {
        closestDistance = getPoint().distance(checkedACell.getPoint());
        c = checkedACell;
      }
    }
    return c;
  }

  // moves the AiPlayer
  @Override
  public void step(Point p) {
    Point position = getPoint();
    position.moveDistanceTowardsPoint(p, speed);
    setCenterX(position.x);
    setCenterY(position.y);

  }

  /**
   * generates a new random color with a different shade of red depending on aggressiveness.
   *
   * @return the new color
   */
  private Color randomColor() {
    Random randomGen = new Random();

    int r = (int) ((20 / aggressiveness) * 12.75);
    int g = randomGen.nextInt(64);
    int b = randomGen.nextInt(64);
    return Color.rgb(r, g, b);
  }

}
