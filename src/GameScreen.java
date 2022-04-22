import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel implements WindowDataReceiver {
    private Font emulogic;
    private Window window;
    private SpaceShip player;
    private ArrayList<Alien> aliens;
    private Bullets bullets;


    public void sendWindowData(Window window) {
        this.window = window;
    }

    public GameScreen(int x, int y, int width, int height) {
        //  Color backgroundColor=new Color(103,106,107);
        Constants constants=new Constants();
        bullets = new Bullets();
        this.setBackground(Color.BLACK);
        try {
            emulogic = Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")).deriveFont(Font.BOLD & Font.CENTER_BASELINE, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")));
        } catch (IOException | FontFormatException e) {
        }
        this.setBounds(x, y, width, height);
        this.aliens = new ArrayList<>();
        AlienMovementVariables alienMovementVariables = new AlienMovementVariables();
        AlienFireVariables alienFireVariables = new AlienFireVariables();
        beginCurrentLevel(alienMovementVariables, alienFireVariables);
        this.player = new SpaceShip();
        JLabel liveCounter=new JLabel("Lives left: "+player.getLivesLeft()+"*");
        liveCounter.setFont(emulogic);
        liveCounter.setForeground(Color.white);
        liveCounter.setBounds(constants.WINDOW_WIDTH/64,55*constants.WINDOW_HEIGHT/64,constants.WINDOW_WIDTH/4,2*constants.WINDOW_HEIGHT/32);
        this.add(liveCounter);
        this.mainGameLoop(alienMovementVariables, alienFireVariables,liveCounter);


    }

    public void beginCurrentLevel(AlienMovementVariables alienMovementVariables, AlienFireVariables alienFireVariables) {
        Constants constants = new Constants();
        if (bullets.getBullets() != null) {
            if (bullets.getBullets().size() != 0) {
                    ArrayList<Bullet> temp = new ArrayList<Bullet>();
                    bullets.setBullets(temp);
            }
        }
        int newAlienXMultiplier = 3;
        int newAlienYMultiplier = 6;
        final int ALIEN_COUNT = 12;
        for (int i = 0; i < ALIEN_COUNT; i++) {
            Alien alien = new Alien((newAlienXMultiplier * (constants.WINDOW_WIDTH)) / 16, (newAlienYMultiplier * (constants.WINDOW_HEIGHT)) / 32);
            if (i == 3 || i == 7) {
                newAlienYMultiplier = newAlienYMultiplier + 3;
                newAlienXMultiplier = 3;
            } else {
                newAlienXMultiplier = newAlienXMultiplier + 3;
            }
            this.aliens.add(alien);
        }

        alienMovementVariables.setNumberOfAliens(ALIEN_COUNT);
        alienMovementVariables.setInitialLevelWaitTime(alienMovementVariables.getInitialLevelWaitTime() * alienMovementVariables.getLevelPassageWaitTimeMultiplier() / 100);
        alienMovementVariables.setWaitTime(alienMovementVariables.getInitialLevelWaitTime());
        alienFireVariables.setInitialLevelFireChanceModifier(alienFireVariables.getInitialLevelFireChanceModifier() * alienFireVariables.getLevelPassageFireChanceIncrementModifier() / 100);
        alienFireVariables.setFireChanceModifier(alienFireVariables.getInitialLevelFireChanceModifier());
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.player.paint(graphics);
        if (this.aliens != null) {
        //    System.out.println(aliens.size());
            if (this.aliens.size() != 0) {
                for (Alien alien : this.aliens) {
                    alien.paint(graphics);
                }
            }
        }
        if (bullets.getBullets() != null) {
            if (bullets.getBullets().size() != 0) {
                for (Bullet bullet : this.bullets.getBullets()) {
                    bullet.paint(graphics);
                }
            }
        }
    }

    public void mainGameLoop(AlienMovementVariables alienMovementVariables, AlienFireVariables alienFireVariables, JLabel liveCounter) {


        Constants constants = new Constants();
        new Thread(() -> {
            this.setFocusable(true);
            this.requestFocus();
            PlayerController playerController = new PlayerController(this);
            this.addKeyListener(playerController);

            new Thread(() -> {
                while (player.getLivesLeft() > 0) {
                    liveCounter.setText("Lives left: "+player.getLivesLeft()+"*");
                    boolean toDecreesLiveCount = false;
                    if (bullets.getBullets() != null) {
                        for (Bullet bullet : this.bullets.getBullets()) {
                            toDecreesLiveCount = player.checkCollision(bullet);
                            if (toDecreesLiveCount) {
                                player.decreesLiveCount();
                                bullet.setToDisplay(false);
                                //removeABullet(bullet);

                            }
                        }
                    }
                }
            }).start();

            if (this.aliens != null) {
                if (this.aliens.size() != 0) {
                    alienControl(alienMovementVariables, alienFireVariables);
                }
            }
            while (true) {
                repaint();

                if (aliens == null) {
                    beginCurrentLevel(alienMovementVariables, alienFireVariables);
                    alienControl(alienMovementVariables, alienFireVariables);
                } else {
                    if (aliens.size() == 0) {
                        beginCurrentLevel(alienMovementVariables, alienFireVariables);
                        alienControl(alienMovementVariables, alienFireVariables);
                    }
                }

            }
        }).start();
    }

    public void bulletControl(Bullet bullet) {
        Constants constants = new Constants();
        // if (this.bullets.getBullets() != null) {
        //    if (this.bullets.getBullets().size() != 0) {
        //          for (Bullet bullet : this.bullets.getBullets()) {

        final int BULLET_SPEED_INDEX = 7;//lower=faster
        if (!bullet.isThreadActive()) {
            new Thread(() -> {
                bullet.setThreadActive(true);
                while (bullet.isToDisplay()) {
                    if (bullet.isFriendly()) {
                        bullet.moveUp();
                        try {
                            Thread.sleep(BULLET_SPEED_INDEX);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bullet.moveDown();
                        try {
                            Thread.sleep(BULLET_SPEED_INDEX);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bullet.getBulletY() + bullet.getBulletHeight() <= 0 || bullet.getBulletY() >= constants.WINDOW_HEIGHT) {
                        bullet.setToDisplay(false);
                        if (bullet.isFriendly()) {
                            bullets.setNumberOfFriendlyBullets(bullets.getNumberOfFriendlyBullets() - 1);
                        }
                    }
                }
            }).start();
        }


        repaint();
        // }
        //  }
        //   }
    }

    public void alienControl(AlienMovementVariables alienMovementVariables, AlienFireVariables alienFireVariables) {
        final int RIGHT = 0;
        final int LEFT = 1;
        Constants constants = new Constants();
        for (Alien alien : this.aliens) {
            //     if (!alien.isMovementThreadActive()) {
            new Thread(() -> {
                //           alien.setMovementThreadActive(true);
                int timesLooped = 0;
                while (alien.isAlive()) {
                    if (timesLooped == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        timesLooped++;
                    } else {


                        boolean changeDirection = false;
                        switch (alienMovementVariables.getMoveDirection()) {
                            case RIGHT: {
                                alien.moveUp();

                                while (!changeDirection) {
                                    alien.moveRight();
                                    randomFire(alienFireVariables,alien);
                                    if (alien.getAlienX() + alien.getWidth() >= constants.WINDOW_WIDTH - 20) {
                                        changeDirection = true;
                                        alienMovementVariables.setMoveDirection(LEFT);
                                        break;
                                    }
                                    if (alienMovementVariables.getMoveDirection() == LEFT) {
                                        changeDirection = true;
                                        break;
                                    }
                                    try {
                                        Thread.sleep(alienMovementVariables.getWaitTime());
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                changeDirection = false;
                                break;
                            }
                            case LEFT: {
                                alien.moveDown();

                                while (!changeDirection) {
                                    alien.moveLeft();
                                    randomFire(alienFireVariables,alien);
                                    if (alien.getAlienX() <= 10) {
                                        changeDirection = true;
                                        alienMovementVariables.setMoveDirection(RIGHT);
                                        break;
                                    }
                                    if (alienMovementVariables.getMoveDirection() == RIGHT) {
                                        changeDirection = true;
                                        break;
                                    }
                                    try {
                                        Thread.sleep(alienMovementVariables.getWaitTime());
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                changeDirection = false;
                                break;
                            }
                        }
                    }
                }
            }).start();
            //  }
            //   if (!alien.isCollisionCheckThreadActive()) {
            new Thread(() -> {
                //     alien.setCollisionCheckThreadActive(true);
                while (alien.isAlive()) {
                    boolean toKill = false;
                    if (this.bullets.getBullets() != null) {
                        for (Bullet bullet : this.bullets.getBullets()) {
                            toKill = alien.checkCollision(bullet);
                            if (toKill) {
                                bullets.setNumberOfFriendlyBullets(0);
                                alien.kill();
                                alienMovementVariables.setNumberOfAliens(alienMovementVariables.getNumberOfAliens() - 1);
                                alienMovementVariables.setWaitTime((alienMovementVariables.getWaitTime() * alienMovementVariables.getWaitTimeMultiplier()) / 100);
                                alienFireVariables.setFireChanceModifier((alienFireVariables.getFireChanceModifier() * alienFireVariables.getFireChanceIncrementModifier()) / 100);
                                bullet.setToDisplay(false);
                                //removeABullet(bullet);
                                aliens.remove(alien);
                                break;
                            }
                        }
                    }
                }
            }).start();
            //   }
        }
    }
    public void randomFire(AlienFireVariables alienFireVariables, Alien alien){
        Random random = new Random();
        final int TO_FIRE = 1;
        int fireCase = random.nextInt(alienFireVariables.getFireChanceModifier());
        if (fireCase == TO_FIRE) {
            Bullet bullet = new Bullet(alien);
            ArrayList<Bullet> bulletsFixed = new ArrayList<>();
            if (bullets.getBullets() != null) {
                if (bullets.getBullets().size() > 0) {
                    for (Bullet bulletInArray : bullets.getBullets()) {
                        bulletsFixed.add(bulletInArray);
                    }
                }
            }
            bulletsFixed.add(bullet);
            bulletControl(bullet);
            this.getBullets().setBullets(bulletsFixed);
        }
    }

    //public void removeABullet(Bullet bullet){
//
//    bullets.getBullets().remove(bullet);
//}
    public Bullets getBullets() {
        return this.bullets;
    }

    public void setNumberOfFriendlyBullets(int numberOfFriendlyBullets) {
        this.bullets.setNumberOfFriendlyBullets(numberOfFriendlyBullets);
    }

    public int getNumberOfFriendlyBullets() {
        return bullets.getNumberOfFriendlyBullets();
    }

    public SpaceShip getPlayer() {
        return this.player;
    }
}
