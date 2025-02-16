package com.quasiriz.junittutorial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test Class
class CalculatorTest {

    @Test
    public void addTest() {
        Calculator calculator = new Calculator();
        int actualResult = calculator.add(10, 10);

        assertEquals(30, actualResult);
    }

}