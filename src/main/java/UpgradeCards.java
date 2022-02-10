/**
 * class for upgradeCards
 * @author chen
 */
public abstract class UpgradeCards extends Cards {

    int cost;

    /**
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
