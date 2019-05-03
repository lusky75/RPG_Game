//
// ETNA PROJECT, 18/10/2018 by chen_l
// Hat.java
// File description:
//      [...]
//

public class Hat implements Item, Tangible {

    private String name;
    private int defencePoints;

    public Hat() {
    }

    public Hat(int defencePoints, String name) {
        this.name = name;
        this.defencePoints = defencePoints;
    }

    public void action(Player player) {
        player.putHat(this);
    }

    public char showAs() {
        return 'H';
    }

    public void put(Player player) {
        int player_defence = player.getStats().getDefence();

        player.getStats().setDefence(player_defence + this.defencePoints);
        
    }

    public void remove(Player player) {
        int player_defence = player.getStats().getDefence();

        player.getStats().setDefence(player_defence - this.defencePoints);
    }

    public String getName() {
        return name;
    }

    public int getDefencePoints() {
        return defencePoints;
    }
}
