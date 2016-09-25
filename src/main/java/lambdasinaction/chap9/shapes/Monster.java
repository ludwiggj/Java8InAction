package lambdasinaction.chap9.shapes;

public class Monster implements Rotatable, Moveable, Resizable {

  @Override
  public int getX() {
    return 0;
  }

  @Override
  public int getY() {
    return 0;
  }

  @Override
  public void setX(int x) {
  }

  @Override
  public void setY(int y) {
  }

  @Override
  public void draw() {
  }

  @Override
  public void setRotationAngle(int angleInDegrees) {
  }

  @Override
  public int getRotationAngle() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void setWidth(int width) {
  }

  @Override
  public void setHeight(int height) {
  }

  @Override
  public void setAbsoluteSize(int width, int height) {
  }

  public static void main(String[] args) {
    // Exercise default interface methods
    Monster m = new Monster();
    m.rotateBy(180);
    m.moveVertically(10);
  }
}
