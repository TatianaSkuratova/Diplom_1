package praktikum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BurgerTest {
    Ingredient ingredient1 = mock(Ingredient.class);
    Ingredient ingredient2 = mock(Ingredient.class);


    //создаем реальный объект, так как сравнение двух моков всегда возвращает true
    @Test
    public void setBunIsRight() {
        Burger burger = new Burger();
        Bun bun = new Bun("Булка", 34);
        burger.setBuns(bun);
        assertEquals("Булка", burger.bun.getName());
    }

    //сравниваем наименования булок из стаба
    @Test
    public void setBunIsReturnRightName() {
        Bun bun = Mockito.mock(Bun.class);
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200j");
        burger.setBuns(bun);
        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void addIngredientsIncreaseSize() {
        int countOfIngredients = 1;
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(countOfIngredients, burger.ingredients.size());
    }

    @Test
    public void addIngredientsTest() {
        Ingredient ingredient = mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("Филе Люминесцентного тетраодонтимформа");
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(ingredient.getName(), burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredientThrowsExceptionOutOfBounds() {
        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(1));
    }

    @Test
    public void removeIngredientDecreaseSize() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        Mockito.when(ingredient1.getName()).thenReturn("Соус Spicy-X");
        assertAll(
                () -> assertEquals(ingredient1.getName(), burger.ingredients.get(0).getName()),
                () -> assertEquals(1, burger.ingredients.size())
        );
    }

    @Test
    public void getPriceBurgerCallGetPriceBunOnce() {
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        burger.getPrice();
        Mockito.verify(bun, Mockito.times(1)).getPrice();
    }


}