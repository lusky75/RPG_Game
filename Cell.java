//
// ETNA PROJECT, 17/10/2018 by chen_l
// Cell.java
// File description:
//      [...]
//

import java.util.*;

public class Cell {

    private List<Tangible> tangibles;
    private List<Reaction> reactions;

    public Cell(Tangible tangible) {
        this.tangibles = new ArrayList<Tangible>();
        tangibles.add(tangible);
    }
    
    public Cell(List<Tangible> listTangible) {
        this.tangibles = listTangible;
    }

    public void addReaction(Reaction reaction) {
        if (reactions != null)
            this.reactions.add(reaction);
        else {
            reactions = new ArrayList<Reaction>();
            reactions.add(reaction);
        }
    }
    
    public void addTangible(Tangible tangible) {
        this.tangibles.add(tangible);
    }

    public boolean isEmptyTangible() {
        return tangibles.isEmpty();
    }

    public boolean isEmptyReaction() {
        return reactions.isEmpty();
    }

    public List<Tangible> getTangibles() {
        return tangibles;
    }

    public char showAs() {
        for (Tangible t : tangibles) {
            if (t instanceof Player)
                return t.showAs();
        }
        return tangibles.get(tangibles.size() - 1).showAs();
    }
}
