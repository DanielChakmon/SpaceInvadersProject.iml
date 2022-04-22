import javax.swing.*;
import java.awt.*;

public class Alien extends Component {
    private boolean isAlive;
    private int alienX;
    private int alienY;
    private ImageIcon alien;
//    private boolean collisionCheckThreadActive;
//    private boolean movementThreadActive;
    public Alien(int alienX,int alienY) {
        isAlive=true;
        this.alien = new ImageIcon("src/alien.png");
        this.alienX = alienX;
        this.alienY = alienY;
//        collisionCheckThreadActive=false;
//        movementThreadActive=false;
    }
    public void paint (Graphics graphics){
        if(isAlive){
            this.alien.paintIcon(this,graphics,alienX,alienY);
        }
    }
        public void moveRight() {
            this.alienX=alienX+2;
        }

        public void moveLeft() {
            this.alienX=alienX-2;
        }

        public void moveUp () {
            this.alienY=alienY+20;
        }

        public void moveDown () {
            this.alienY=alienY-20;
        }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getAlienX() {
        return alienX;
    }

    public int getAlienY() {
        return alienY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public int getWidth() {
        return alien.getIconWidth();
    }
    public int getHeight() {
        return alien.getIconHeight();
    }

    public boolean checkCollision (Bullet bullet) {
        boolean collision = false;
        Rectangle alienRect = new Rectangle(
                this.alienX,
                this.alienY,
                this.alien.getIconWidth(),
                this.alien.getIconHeight()
        );
        Rectangle bulletRect = new Rectangle(
                bullet.getBulletX(),
                bullet.getBulletY(),
                bullet.getBulletWidth(),
                bullet.getBulletHeight()
        );
        if (alienRect.intersects(bulletRect)&&bullet.isFriendly()&&bullet.isToDisplay()) {
            collision = true;
        }
        return collision;
    }
    public void kill(){
        this.isAlive=false;
    }

//    public boolean isCollisionCheckThreadActive() {
//        return collisionCheckThreadActive;
//    }
//
//    public void setCollisionCheckThreadActive(boolean collisionCheckThreadActive) {
//        this.collisionCheckThreadActive = collisionCheckThreadActive;
//    }
//
//    public boolean isMovementThreadActive() {
//        return movementThreadActive;
//    }
//
//    public void setMovementThreadActive(boolean movementThreadActive) {
//        this.movementThreadActive = movementThreadActive;
//    }
}
