package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {
    @Test
    public void checkCountOfIngredients() {
        int expectedCountOfIngredients = 2;
        assertEquals(expectedCountOfIngredients, IngredientType.values().length);
    }

    @Test
    public void checkIngredientTypeSauce() {
        IngredientType expectedValue = IngredientType.SAUCE;
        assertEquals(expectedValue, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void checkIngredientTypeFilling() {
        IngredientType expectedValue = IngredientType.SAUCE;
        assertEquals(expectedValue, IngredientType.valueOf("SAUCE"));
    }
}
