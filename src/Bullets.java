import java.util.ArrayList;

public class Bullets {

    private ArrayList<Bullet> bullets;
    private int numberOfFriendlyBullets;

    public Bullets() {
        bullets = new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBullets() {
        if (bullets != null) {
            return bullets;
        } else {
            return null;
        }
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
//        if(bullets!=null) {
//            System.out.println(bullets.size());
//        }
    }

    public int getNumberOfFriendlyBullets() {
        return numberOfFriendlyBullets;
    }

    public void setNumberOfFriendlyBullets(int numberOfFriendlyBullets) {
        this.numberOfFriendlyBullets = numberOfFriendlyBullets;
      // System.out.println(numberOfFriendlyBullets);
    }
}
