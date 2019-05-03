//
// ETNA PROJECT, 18/10/2018 by chen_l
// Boots.java
// File description:
//      [...]
//

public class Boots implements Item, Tangible {

    private String name;
    private int lifePoints;
    private int attackPoints;

    public Boots() {
    }
    
    public Boots(int lifePoints, int attackPoints,String name) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints = attackPoints;
    }

    public void action(Player player) {
        player.putBoots(this);
    }

    public char showAs() {
        return 'B';
    }
    
    public void put(Player player) {
        int player_attack = player.getStats().getAttack();

        player.getHealthBar().addMaxPoint(this.lifePoints);
        player.getStats().setAttack(player_attack + this.attackPoints);
    }

    public void remove(Player player) {
        int player_attack = player.getStats().getAttack();

        player.getHealthBar().removeMaxPoint(this.lifePoints);
        player.getStats().setAttack(player_attack - this.attackPoints);
    }
    
    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
