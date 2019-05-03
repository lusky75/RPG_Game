//
// ETNA PROJECT, 18/10/2018 by chen_l
// HealthBar.java
// File description:
//      [...]
//

public class HealthBar extends Bar {

    private boolean dead;

    public HealthBar(String name, int currentPoint, int maxPoint) {
        super(name, currentPoint, maxPoint);
        this.dead = false;
    }

    @Override
    public void addCurrentPoint(int point) {
        if (dead)
            return;
        currentPoint += point;
        if (currentPoint > maxPoint)
            currentPoint = maxPoint;
    }

    @Override
    public void removeCurrentPoint(int point) {
        if (dead)
            return;
        currentPoint -= point;
        if (currentPoint < 0){
            currentPoint = 0;
            dead = true;
        }
    }

    @Override
    public void addMaxPoint(int point) {
        if (dead)
            return;
        maxPoint += point;

    }

    @Override
    public void removeMaxPoint(int point) {
        if (dead)
            return;
        maxPoint -= point;
    }

    public boolean isDead() {
        if (currentPoint == 0)
            return true;
        return false;
    }

}
