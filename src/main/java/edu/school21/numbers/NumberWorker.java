package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException(number + " is not valid argument");
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;
        int divider = 10;
        while (Math.abs(number) > 0) {
            sum += number % divider;
            number /= divider;
        }
        return sum;
    }
}

class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String message) {
        System.err.println(message);
    }
}
