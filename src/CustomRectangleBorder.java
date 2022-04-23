import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CustomRectangleBorder {
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private int borderThickness;

    public CustomRectangleBorder(int x, int y, int width, int height, Color color, int borderThickness) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.borderThickness=borderThickness;
    }
    private Shape createBorder() {
        Rectangle outer = new Rectangle(x, y, (int) width, (int) height);
        double innerX = (width / 2) - (width / 2 - borderThickness);
        double innerY = (height / 2) - (height / 2 - borderThickness);
        double innerWidth = width - 2 * borderThickness;
        double innerHeight = height - 2 * borderThickness;
        Rectangle inner = new Rectangle((int) innerX, (int) innerY, (int) innerWidth, (int) innerHeight);
        Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
    }
    public void paint (Graphics2D graphics) {
            graphics.setColor(this.color);
            Shape border=createBorder();
            graphics.draw(border);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return (int) width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return (int) height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBottom () {
        return this.y + (int) this.height;
    }
}
