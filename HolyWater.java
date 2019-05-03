//
// ETNA PROJECT, 18/10/2018 by chen_l
// HolyWater.java
// File description:
//      [...]
//

public class HolyWater implements Tangible {

    private int lifesToAdd;

    public HolyWater(int lifes) {
        this.lifesToAdd = lifes;
    }

    public void action(Player player) {
        player.getHealthBar().addCurrentPoint(lifesToAdd);
        player.addEnergy(5);
    }
    
    public char showAs() {
        return 'W';
    }
}
