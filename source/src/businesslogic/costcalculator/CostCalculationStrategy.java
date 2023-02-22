package businesslogic.costcalculator;

public interface CostCalculationStrategy {
    /**
     *
     * @param duration: (s) - the amount of time user rent the bike
     * @return chi ph�
     */
    public int calculateCost(int duration);
}
