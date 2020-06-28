package OrderProcess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

class CalculatorImplTest {

    static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][]{{-1, 2, 1}, {0, 5, 5}, {-1, -2, -3}});
    }

    @ParameterizedTest
    @MethodSource("data")
    void calculator(int num1, int num2, int expectedResult) {
        Calculator c = new CalculatorImpl();
        int actualResult = c.add(num1, num2);
        Assertions.assertEquals(actualResult, expectedResult);
    }
}