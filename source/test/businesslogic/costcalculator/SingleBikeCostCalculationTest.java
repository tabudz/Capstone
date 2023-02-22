package businesslogic.costcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SingleBikeCostCalculationTest {
    private  SingleBikeCostCalculation singleBikeCostCalculation;
    @BeforeEach
    void setUp(){
        singleBikeCostCalculation = new SingleBikeCostCalculation();
    }

    @ParameterizedTest
    @CsvSource({
            "1,0","10,0","30,10000","45,13000"
    })
    void test(int duration,int expected){
        int cost = singleBikeCostCalculation.calculateCost(duration);
        assertEquals(expected,cost);
    }
}