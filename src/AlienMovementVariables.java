public class AlienMovementVariables {
    private int numberOfAliens = 12;
    private int waitTime = 30;
    private int moveDirection = 0;
    private final int WAIT_TIME_MULTIPLIER = 92;
    private final int LEVEL_PASSAGE_WAIT_TIME_MULTIPLIER = 75;
    private int initialLevelWaitTime=30;
    private final int MINIMUM_WAIT_TIME=6;

    public int getNumberOfAliens() {
        return numberOfAliens;
    }

    public void setNumberOfAliens(int numberOfAliens) {
        this.numberOfAliens = numberOfAliens;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        if(waitTime>MINIMUM_WAIT_TIME) {
            this.waitTime = waitTime;
        }
        else {
            this.waitTime=MINIMUM_WAIT_TIME;
        }
    }

    public int getInitialLevelWaitTime() {
        return initialLevelWaitTime;
    }

    public void setInitialLevelWaitTime(int initialLevelWaitTime) {
        if(initialLevelWaitTime>MINIMUM_WAIT_TIME) {
            this.initialLevelWaitTime = initialLevelWaitTime;
        }else {
            this.initialLevelWaitTime=MINIMUM_WAIT_TIME;
        }
    }

    public int getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(int moveDirection) {
        this.moveDirection = moveDirection;
    }

    public int getWaitTimeMultiplier() {
        return WAIT_TIME_MULTIPLIER;
    }
    public int getLevelPassageWaitTimeMultiplier() {
        return LEVEL_PASSAGE_WAIT_TIME_MULTIPLIER;
    }
}
