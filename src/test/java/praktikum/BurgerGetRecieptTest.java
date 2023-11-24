package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BurgerGetRecieptTest {
    static Stream<Arguments> isGetIngredientTestData() {
        Ingredient sauce1 = Mockito.mock(Ingredient.class);
        Mockito.when(sauce1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce1.getName()).thenReturn("hot sauce");
        Mockito.when(sauce1.getPrice()).thenReturn(100F);

        Ingredient filling1 = Mockito.mock(Ingredient.class);
        Mockito.when(filling1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling1.getName()).thenReturn("dinosaur");
        Mockito.when(filling1.getPrice()).thenReturn(200F);

        Bun bun1 = Mockito.mock(Bun.class);
        Mockito.when(bun1.getName()).thenReturn("black bun");
        Mockito.when(bun1.getPrice()).thenReturn(100F);

        Ingredient sauce2 = Mockito.mock(Ingredient.class);
        Mockito.when(sauce2.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce2.getName()).thenReturn("sour cream");
        Mockito.when(sauce2.getPrice()).thenReturn(200F);

        Ingredient filling2 = Mockito.mock(Ingredient.class);
        Mockito.when(filling2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling2.getName()).thenReturn("sausage");
        Mockito.when(filling2.getPrice()).thenReturn(300F);

        Bun bun2 = Mockito.mock(Bun.class);
        Mockito.when(bun2.getName()).thenReturn("red bun");
        Mockito.when(bun2.getPrice()).thenReturn(300F);

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(sauce1);
        ingredients1.add(filling1);

        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(sauce2);
        ingredients2.add(filling2);

        List<Ingredient> ingredients3 = new ArrayList<>();

        String expectedReceipt1 = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling dinosaur =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 500,000000\n";
        String expectedReceipt2 = "(==== red bun ====)\n" +
                "= sauce sour cream =\n" +
                "= filling sausage =\n" +
                "(==== red bun ====)\n" +
                "\n" +
                "Price: 1100,000000\n";

        String expectedReceipt3 = "(==== red bun ====)\n" +
                "(==== red bun ====)\n" +
                "\n" +
                "Price: 600,000000\n";

        return Stream.of(
                arguments(bun1, ingredients1, expectedReceipt1),
                arguments(bun2, ingredients2, expectedReceipt2),
                arguments(bun2, ingredients3, expectedReceipt3)
        );
    }

    @ParameterizedTest
    @MethodSource("isGetIngredientTestData")
    public void getRecieptIsReturnRightValue(Bun bun, List<Ingredient> ingredients, String expectedReciept) {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedReciept, burger.getReceipt());
    }
}

