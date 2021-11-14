package dyermccoy.agariogame;

import java.util.Random;

import javafx.scene.shape.Circle;

/**
 * The abstract class for consumable objects.
 */
public abstract class AConsumable extends Circle implements IConsumable {

  AConsumable(int radius) {

    super(radius);

    Random randomGen = new Random();

    int xValue = randomGen.nextInt(Settings.windowWidth - 40) + 20;
    int yValue = randomGen.nextInt(Settings.windowHeight - 40) + 20;

    this.setCenterX(xValue);
    this.setCenterY(yValue);

  }

  /**
   * gets the current point of the cells.
   *
   * @return a new point.
   */
  public Point getPoint() {
    return new Point(getCenterX(), getCenterY());
  }

}
