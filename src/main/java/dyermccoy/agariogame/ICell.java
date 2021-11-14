package dyermccoy.agariogame;


/**
 * Represents a cell object.
 */
public interface ICell {

  /**
   * Checks to see where the cell is in relationship to the game boundaries.
   */
  void checkBoundaries();

  /**
   * Sets a new speed.
   *
   * @return the new speed
   */
  double newSpeed();

  /**
   * Gets a point.
   *
   * @return a new point
   */
  Point getPoint();

  /**
   * Moves a cell.
   *
   * @param p the current point
   */
  void step(Point p);
}
