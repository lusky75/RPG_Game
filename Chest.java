//
// ETNA PROJECT, 17/10/2018 by chen_l
// Chest.java
// File description:
//      [...]
//

public class Chest implements Tangible {

    public Chest() {
    }
    
    public void action(Player player) {
        player.setPoint(player.getPoint() + 1);
    }
    
    public char showAs() {
        return 'C';
    }
}
