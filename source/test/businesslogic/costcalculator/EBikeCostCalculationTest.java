package businesslogic.costcalculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
class EBikeCostCalculationTest {
    private  EBikeCostCalculation eBikeCostCalculation;
    @BeforeEach
    void setUp(){
        eBikeCostCalculation = new EBikeCostCalculation();
    }
    @ParameterizedTest
    @CsvSource({
            "1,0","10,0","30,15000","45,19500"
    })
    void test(int duration,int expected){
        int cost = eBikeCostCalculation.calculateCost(duration);
        assertEquals(expected,cost);
    }

}