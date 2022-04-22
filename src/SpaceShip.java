import javax.swing.*;
import java.awt.*;

public class SpaceShip extends Component {
    private int livesLeft;
    private ImageIcon spaceship;
    private int shipX;
    private final int SHIP_Y;

    public SpaceShip(){
        this.spaceship = new ImageIcon("src/spaceship.png");
        Constants constants=new Constants();
        final int INITIAL_SHIP_X= constants.WINDOW_WIDTH/2-(spaceship.getIconWidth())/2;
        SHIP_Y= 25*constants.WINDOW_HEIGHT/32;
        this.shipX=INITIAL_SHIP_X;
        final int INITIAL_LIVE_COUNT=3;
        livesLeft=INITIAL_LIVE_COUNT;
    }
    public void paint (Graphics graphics){
        if(livesLeft>0){
            this.spaceship.paintIcon(this,graphics,shipX,SHIP_Y);
        }
    }
    public void moveLeft(){
        if (shipX>0){
            this.shipX=shipX-5;
        }
    }
    public void moveRight(){
        Constants constants=new Constants();
        if (shipX+spaceship.getIconWidth()< constants.WINDOW_WIDTH){
            this.shipX=shipX+5;
        }
    }

    public int getShipX() {
        return shipX;
    }

    public int getShipY() {
        return SHIP_Y;
    }

    public int getLivesLeft() {
        return livesLeft;
    }
    public int getWidth(){
        return spaceship.getIconWidth();
    }
    public boolean checkCollision (Bullet bullet) {
        boolean collision = false;
        if(!bullet.isFriendly()){
        Rectangle spaceshipRect = new Rectangle(
                this.shipX,
                this.SHIP_Y,
                this.spaceship.getIconWidth(),
                this.spaceship.getIconHeight()
        );
        Rectangle bulletRect = new Rectangle(
                bullet.getBulletX(),
                bullet.getBulletY(),
                bullet.getBulletWidth(),
                bullet.getBulletHeight()
        );
        if (spaceshipRect.intersects(bulletRect)&&bullet.isToDisplay()) {
            collision = true;
        }
    }
        return collision;
    }
    public void decreesLiveCount(){
        this.livesLeft--;
    }
}
