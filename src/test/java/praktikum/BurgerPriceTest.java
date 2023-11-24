package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BurgerPriceTest {
    static Stream<Arguments> isGetPriceTestData() {
        return Stream.of(
                arguments(1255, new float[]{90, 88, 1337, 3000, 4142}, 11167),
                arguments(988, new float[]{90, 88, 424, 874, 4142}, 7594)
        );
    }

    @ParameterizedTest
    @MethodSource("isGetPriceTestData")
    public void getPriceIsReturnRightValue(float bunPrice, float[] ingredientPrice, float expectedPrice) {
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (float price : ingredientPrice) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice, burger.getPrice());
    }
}

