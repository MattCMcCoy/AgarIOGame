package dyermccoy.agariogame;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The abstract class representing a cell.
 */
public abstract class ACell extends Circle implements ICell {

  static int cellsEaten = 0;
  private static final double MIN_RADIUS = 16;
  private final double MIN_SPEED = 1;
  private static final ArrayList<ACell> A_CELLS = new ArrayList<>();
  private boolean isDead = false;
  public double speed = 4;

  public ACell(Color color) {
    super(MIN_RADIUS);

    setStroke(color);
    setFill(color.deriveColor(1, 1, 1, .3));
    Random random = new Random();
    setCenterX(random.nextInt(Settings.windowWidth));
    setCenterY(random.nextInt(Settings.windowHeight));

    A_CELLS.add(this);
  }


  // moves the cell.
  public abstract void step(Point p);


  /**
   * Grows a cell if it eats a consumable.
   */
  private void grow() {

    double GROWTH_RATE = .6;
    setRadius(getRadius() + GROWTH_RATE);
    if (speed > MIN_SPEED) {
      speed = newSpeed();
    }

  }


  // checks the game boundaries
  public void checkBoundaries() {
    if (!isDead) {
      if (getCenterX() > Settings.windowWidth - 5) {
        setCenterX(15);
      } else if (getCenterX() < 5) {
        setCenterX(Settings.windowWidth - 15);
      }
      if (getCenterY() > Settings.windowHeight - 5) {
        setCenterY(15);
      } else if (getCenterY() < 5) {
        setCenterY(Settings.windowHeight - 15);
      }
    }
  }

  /**
   * Grows a cell to a new radius.
   *
   * @param addition the radius to be added on to the current one
   */
  private void grow(double addition) {

    setRadius(getRadius() + addition);
    if (speed > MIN_SPEED) {
      speed = newSpeed();
    }

  }

  /**
   * shrinks object if it eats a consumable allowing shrinking.
   */
  private void shrink() {

    double SHRINK_RATE = 30;
    double radius = getRadius() - SHRINK_RATE;
    if (radius < MIN_RADIUS)
      radius = MIN_RADIUS;
    setRadius(radius);
    speed = newSpeed();

  }

  /**
   * kills a cell.
   */
  private void die() {

    isDead = true;
    setRadius(0);
    speed = 0;
    setCenterX(-2000);
    setCenterY(-2000);

  }

  /**
   * checks collision between objects.
   *
   * @param c the current cell circle
   * @return whether it collided with the side
   */
  private boolean checkCollision(Circle c) {
    Point object = new Point(c.getCenterX(), c.getCenterY());
    Point cell = new Point(getCenterX(), getCenterY());

    double distance = cell.distance(object);
    return distance <= Math.abs(this.getRadius() - c.getRadius());

  }

  /**
   * sets a new speed if it grows or shrinks.
   *
   * @return the new speed
   */
  public double newSpeed() {
    double outputSpeed = (-0.02 * getRadius()) + 4.32;
    if (outputSpeed < MIN_SPEED) {
      outputSpeed = MIN_SPEED;
    }
    return outputSpeed;
  }

  /**
   * checks if the cell collides with a food object.
   */
  protected void eatsFood() {

    for (int i = 0; i < Food.foodObjects.size(); i++) {
      if (checkCollision(Food.foodObjects.get(i))) {

        grow();

        Main.playfield.getChildren().remove(Food.foodObjects.get(i));
        Food.foodObjects.remove(Food.foodObjects.get(i));

        Food newFood = new Food();
        Main.playfield.getChildren().add(newFood);

      }
    }
  }

  /**
   * checks if the cell collides with another cell.
   */
  protected void eatsPlayer() {

    for (ACell ACell : A_CELLS) {
      if (ACell.getRadius() < getRadius()) {
        if (checkCollision(ACell)) {

          grow(ACell.getRadius());
          ACell.die();
          // checks to see if player is out or if player is last alive
          if (ACell instanceof Player) {
            BattleGround.game.stop();
            Main.playfield.getChildren().add(Main.restart);
            Main.playfield.getChildren().add(Main.youLOSE);
          } else {
            cellsEaten++;
            if (cellsEaten == Settings.limitAi) {
              BattleGround.game.stop();
              Main.playfield.getChildren().add(Main.restart);
              Main.playfield.getChildren().add(Main.youWIN);
            }

          }

        }
      }
    }
  }

  /**
   * if collides with a virus will be attacked and shrink
   */
  protected void infected() {
    if (getRadius() >= Coronavirus.VIRUS_SIZE) {
      for (int i = 0; i < Coronavirus.virusObjects.size(); i++) {
        if (checkCollision(Coronavirus.virusObjects.get(i))) {

          shrink();
          Main.playfield.getChildren().remove(Coronavirus.virusObjects.get(i));
          Coronavirus.virusObjects.remove(Coronavirus.virusObjects.get(i));

          Coronavirus newVirus = new Coronavirus();
          Main.playfield.getChildren().add(newVirus);

        }
      }
    }
  }

  // returns a new point at a specific position.
  public Point getPoint() {
    return new Point(getCenterX(), getCenterY());
  }

  /**
   * Gets the arraylist with all the cells.
   *
   * @return the current cell array list
   */
  public static ArrayList<ACell> getCellArrayList() {
    return A_CELLS;
  }
}
