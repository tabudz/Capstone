package businesslogic.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckCardInUseControllerTest {

    @BeforeEach
    void setUp() {

    }
    @ParameterizedTest
    @CsvSource({
            "group5_2022, true",
            "group6_2022, false"
    })

    void checkCardInUse(String cardCode, Boolean expected) {
        CheckCardInUseController checkCardInUseController = new CheckCardInUseController();
        Boolean check = checkCardInUseController.checkCardInUse(cardCode);
        assertEquals(expected, check);
    }


}