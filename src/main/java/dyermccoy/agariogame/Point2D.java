package dyermccoy.agariogame;

/**
 * Class for a 2D point.
 */
public class Point2D {

  public float x;


  public float y;


  public Point2D() {
  }

  /**
   * Sets the location of this point to the given inputs.
   *
   * @param x the x position
   * @param y the y position
   */
  public void setLocation(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the distance between two points.
   *
   * @param pt the point to be compared to
   * @return the distance between the points.
   */
  public float distance(Point2D pt) {
    float px = pt.x - this.x;
    float py = pt.y - this.y;
    return (float) Math.sqrt(px * px + py * py);
  }

  /**
   * Compares two 2D points.
   *
   * @param obj the object to be compared to this
   * @return whether they are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj instanceof Point2D) {
      Point2D p2d = (Point2D) obj;
      return (x == p2d.x) && (y == p2d.y);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int bits = java.lang.Float.floatToIntBits(x);
    bits ^= java.lang.Float.floatToIntBits(y) * 31;
    return bits;
  }


}


