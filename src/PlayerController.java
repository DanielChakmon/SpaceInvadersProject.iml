import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerController implements KeyListener {

    private GameScreen gameScreen;

    public PlayerController (GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyReleased(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if(gameScreen.getPlayer().getLivesLeft()>0) {
            switch (keyCode) {
                case KeyEvent.VK_SPACE: {
                    if (this.gameScreen.getNumberOfFriendlyBullets() == 0) {
                        ArrayList<Bullet> bullets = this.gameScreen.getBullets().getBullets();
                        Bullet bullet = new Bullet(this.gameScreen.getPlayer());
                        ArrayList<Bullet> bulletsFixed = new ArrayList<>();
                        if (bullets != null) {
                            if (bullets.size() > 0) {
                                for (Bullet bulletInArray : bullets) {
                                    bulletsFixed.add(bulletInArray);
                                }
                            }
                        }
                        bulletsFixed.add(bullet);
                        gameScreen.bulletControl(bullet);
                        this.gameScreen.getBullets().setBullets(bulletsFixed);
                        this.gameScreen.setNumberOfFriendlyBullets(this.gameScreen.getNumberOfFriendlyBullets() + 1);
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    this.gameScreen.getPlayer().moveRight();
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    this.gameScreen.getPlayer().moveLeft();
                    break;
                }
            }
        }
    }
}
