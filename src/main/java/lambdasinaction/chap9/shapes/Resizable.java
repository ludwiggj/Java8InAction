package lambdasinaction.chap9.shapes;

public interface Resizable extends Drawable {
  int getWidth();

  int getHeight();

  void setWidth(int width);

  void setHeight(int height);

  void setAbsoluteSize(int width, int height);

  //TODO: uncomment, read the README for instructions
  default void setRelativeSize(int widthFactor, int heightFactor) {
    setAbsoluteSize(getWidth() / widthFactor, getHeight() / heightFactor);
  }
}