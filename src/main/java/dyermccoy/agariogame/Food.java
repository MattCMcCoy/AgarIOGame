package dyermccoy.agariogame;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Creates the food objects.
 */
public class Food extends AConsumable {

  public static ArrayList<Food> foodObjects = new ArrayList<>();

  // creates food objects
  public Food() {
    super(5);
    this.setFill(Color.BLACK);
    foodObjects.add(this);
  }
}
