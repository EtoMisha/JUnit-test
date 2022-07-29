package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTests {
    NumberWorker nw;

    @BeforeEach
    void beforeEachMethod() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 23, 3571, Integer.MAX_VALUE})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 25, 1000, 2121, 424242})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -42, Integer.MIN_VALUE})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> nw.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitSum(int x, int y) {
        Assertions.assertEquals(nw.digitsSum(x), y);
    }

}

