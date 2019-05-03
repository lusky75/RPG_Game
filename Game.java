//
// ETNA PROJECT, 24/10/2018 by chen_l
// Game.java
// File description:
//      [...]
//

public abstract class Game {

    private String name;
    private Key keyCondition;
    private Key keyReward;
    private boolean lose;
    
    public Game(String name, Key keyCondition, Key keyReward) {
        this.name = name;
        this.keyCondition = keyCondition;
        this.keyReward = keyReward;
        this.lose = false;
    }

    public abstract boolean play(Key k, String input);
    
    public abstract boolean play(Key k, String input, Question q);
    
    public abstract boolean canPlay(Key k);

    public abstract void hasLost();
    
    public String getName() {
        return name;
    }
    
    public Key getKeyCondition() {
        return keyCondition;
    }

    public Key getKeyReward() {
        return keyReward;
    }

    public boolean getLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public abstract Key reward();
    
}
