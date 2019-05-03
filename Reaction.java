//
// ETNA PROJECT, 18/10/2018 by chen_l
// Reaction.java
// File description:
//      [...]
//

public interface Reaction {

    public ReactionType getTypeReaction();

    public String getTextOfReaction();

    public void action(Player player);
}
