//
// ETNA PROJECT, 18/10/2018 by chen_l
// Dress.java
// File description:
//      [...]
//

public class Dress implements Item, Tangible {

    private String name;
    private int lifePoints;
    private int defencePoints;

    public Dress() {
    }
    
    public Dress(int lifePoints, int defencePoints, String name) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.defencePoints = defencePoints;
    }

    public void action(Player player) {
        player.putDress(this);
    }

    public char showAs() {
        return 'D';
    }

    public void put(Player player) {
        int player_defence = player.getStats().getDefence();

        player.getHealthBar().addMaxPoint(this.lifePoints);
        player.getStats().setDefence(player_defence + this.defencePoints);
    }

    public void remove(Player player) {
        int player_defence = player.getStats().getDefence();

        player.getHealthBar().removeMaxPoint(this.lifePoints);
        player.getStats().setDefence(player_defence - this.defencePoints);
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getDefencePoints() {
        return defencePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setDefencePoints(int defencePoints) {
        this.defencePoints = defencePoints;
    }
}
