package businesslogic.costcalculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DoubleBikeCostCalculationTest {
    private DoubleBikeCostCalculation doubleBikeCostCalculation;
    @BeforeEach
    void setUp(){
        doubleBikeCostCalculation = new DoubleBikeCostCalculation();
    }
    @ParameterizedTest
    @CsvSource({
            "1,0","10,0","30,15000","45,19500"
    })
    void test(int duration,int expected){
        int cost = doubleBikeCostCalculation.calculateCost(duration);
        assertEquals (expected,cost);
    }

}