package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurgerGetPriceTwoBuns {
    @ParameterizedTest
    @CsvSource({"988, 1976", "1255, 2510"})
    public void getPriceBurgerIsMultiplyByTwoPriceBun(float bunPrice, float doubleBunPrice) {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(doubleBunPrice, burger.getPrice());
    }

}
