import java.awt.*;

public class Bullet {
    final int BULLET_WIDTH = 5;
    final int BULLET_HEIGHT = 10;
    private final boolean isFriendly;
    private final CustomRectangle bullet;
    private boolean toDisplay;
    //    private final int bulletX;
    //  private int bulletY;
    private boolean threadActive;

    public Bullet(Alien alien) {
        isFriendly = false;
        threadActive = false;
        int bulletX = alien.getAlienX() + (alien.getWidth() / 2);
        int bulletY = alien.getAlienY() + alien.getHeight();
        bullet = new CustomRectangle(bulletX, bulletY, BULLET_WIDTH, BULLET_HEIGHT, Color.WHITE);
        toDisplay = true;
    }

    public Bullet(SpaceShip spaceShip) {
        isFriendly = true;
        threadActive = false;
        Constants constants = new Constants();
        int bulletY = spaceShip.getShipY() + spaceShip.getHeight();
        int bulletX = spaceShip.getShipX() + (spaceShip.getWidth() / 2);
        bullet = new CustomRectangle(bulletX, bulletY, BULLET_WIDTH, BULLET_HEIGHT, constants.friendlyColor);
        toDisplay = true;
    }

    public void paint(Graphics graphics) {
        if (toDisplay) {
            this.bullet.paint(graphics);
        }
    }
public void moveUp(){
    bullet.setY(this.bullet.getY() - 2);
}
public void moveDown(){
    bullet.setY(this.bullet.getY() + 2);
}

    public boolean isFriendly() {
        return isFriendly;
    }

    public boolean isToDisplay() {
        return toDisplay;
    }

    public void setToDisplay(boolean toDisplay) {
        this.toDisplay = toDisplay;
    }

    public int getBulletX() {
        return bullet.getX();
    }

    public int getBulletY() {
        return bullet.getY();
    }

    public int getBulletWidth() {
        return BULLET_WIDTH;
    }

    public int getBulletHeight() {
        return BULLET_HEIGHT;
    }

    public boolean isThreadActive() {
        return threadActive;
    }

    public void setThreadActive(boolean threadActive) {
        this.threadActive = threadActive;
    }
}

