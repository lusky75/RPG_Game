//
// ETNA PROJECT, 18/10/2018 by chen_l
// Sword.java
// File description:
//      [...]
//

public class Sword implements Item, Tangible {

    private String name;
    private int attackPoints;

    public Sword() {
    }
    
    public Sword(int attackPoints,String name) {
        this.name = name;
        this.attackPoints = attackPoints;
    }

    public void action(Player player) {
        player.putSword(this);
    }

    public char showAs() {
        return 'S';
    }
    
    public void put(Player player) {
        int player_attack = player.getStats().getAttack();

        player.getStats().setAttack(player_attack + this.attackPoints);
    }

    public void remove(Player player) {
        int player_attack = player.getStats().getAttack();

        player.getStats().setAttack(player_attack - this.attackPoints);
    }
    
    public String getName() {
        return name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
