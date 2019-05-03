//
// ETNA PROJECT, 18/10/2018 by chen_l
// Acid.java
// File description:
//      [...]
//

public class Acid implements Tangible {

    private int damagePoint;
    
    public Acid(int damagePoint) {
        this.damagePoint = damagePoint;
    }

    public void action(Player player) {
        player.getHealthBar().removeCurrentPoint(damagePoint);
    }
    
    public char showAs() {
        return 'A';
    }
}
