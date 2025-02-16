package com.quasiriz.junittutorial.annotations;

import com.quasiriz.junittutorial.Factorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    public void factorialTest(){
        Factorial factorial = new Factorial();

        int actualResult = factorial.factorial(5);

        assertEquals(120,actualResult);
    }

}