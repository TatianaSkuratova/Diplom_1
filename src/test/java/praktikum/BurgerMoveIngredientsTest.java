package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BurgerMoveIngredientsTest {
    @ParameterizedTest
    @CsvSource({
            "4, 1, 2, \"Биокотлета из марсианской Магнолии\"",
            "2, 1, 0, \"Сыр с астероидной плесенью\""})
    public void moveIngredientsIsWork(int size, int index, int newIndex, String name) {
        Burger burger = new Burger();
        for (int i = 0; i < size; i++) {
            burger.addIngredient(mock(Ingredient.class));
        }
        Mockito.when(burger.ingredients.get(index).getName()).thenReturn(name);
        burger.moveIngredient(index, newIndex);
        assertEquals(name, burger.ingredients.get(newIndex).getName());
    }
}
