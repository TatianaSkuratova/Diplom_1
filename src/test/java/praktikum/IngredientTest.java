package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IngredientTest {

    @Mock
    private static IngredientType ingredientType;

    public static Stream<Arguments> isDataForIngredient() {
        return Stream.of(
                arguments(ingredientType, "Соус Spicy-X", 90),
                arguments(ingredientType, "Мясо бессмертных моллюсков Protostomia", 1337)
        );
    }

    public static Stream<Arguments> isTypeForIngredient() {
        return Stream.of(
                arguments(IngredientType.SAUCE, "Соус Spicy-X", 90),
                arguments(IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 1337)
        );
    }

    //изолированный тест с моком
    @ParameterizedTest
    @MethodSource("isDataForIngredient")
    public void gettersOfIngredientAreReturnRightValue(IngredientType ingredientType, String name, float price) {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertAll(
                () -> assertEquals(ingredientType, ingredient.getType()),
                () -> assertEquals(name, ingredient.getName()),
                () -> assertEquals(price, ingredient.getPrice())
        );
    }

    @ParameterizedTest
    @MethodSource("isTypeForIngredient")
    public void getTypeIsReturnRightValue(IngredientType ingredientType, String name, float price) {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertAll(
                () -> assertEquals(ingredientType, ingredient.getType()),
                () -> assertEquals(name, ingredient.getName()),
                () -> assertEquals(price, ingredient.getPrice())
        );
    }
}