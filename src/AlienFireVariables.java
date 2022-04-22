public class AlienFireVariables {
    private int initialLevelFireChanceModifier = 2000;
    private int fireChanceModifier = 2000;
    private final int FIRE_CHANCE_INCREMENT_MODIFIER = 92;
    private final int LEVEL_PASSAGE_FIRE_CHANCE_INCREMENT_MODIFIER = 75;
    private final int MINIMUM_FIRE_CHANCE_MODIFIER = 75;

    public int getInitialLevelFireChanceModifier() {
        return initialLevelFireChanceModifier;
    }

    public void setInitialLevelFireChanceModifier(int initialLevelFireChanceModifier) {
        if (initialLevelFireChanceModifier > MINIMUM_FIRE_CHANCE_MODIFIER) {
            this.initialLevelFireChanceModifier = initialLevelFireChanceModifier;
        } else {
            this.initialLevelFireChanceModifier=MINIMUM_FIRE_CHANCE_MODIFIER;
        }
    }

    public int getFireChanceModifier() {
        return fireChanceModifier;
    }

    public void setFireChanceModifier(int fireChanceModifier) {
        if(fireChanceModifier>MINIMUM_FIRE_CHANCE_MODIFIER) {
            this.fireChanceModifier = fireChanceModifier;
        }else{
            this.fireChanceModifier=MINIMUM_FIRE_CHANCE_MODIFIER;
        }
    }

    public int getFireChanceIncrementModifier() {
        return FIRE_CHANCE_INCREMENT_MODIFIER;
    }

    public int getLevelPassageFireChanceIncrementModifier() {
        return LEVEL_PASSAGE_FIRE_CHANCE_INCREMENT_MODIFIER;
    }
}
