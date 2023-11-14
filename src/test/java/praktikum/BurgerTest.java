package praktikum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class BurgerTest {
    Ingredient ingredient1 = mock(Ingredient.class);
    Ingredient ingredient2 = mock(Ingredient.class);

    //создаем реальный объект, так как сравнение двух моков всегда возвращает true
    @Test
    public void setBunIsRight(){
       Burger burger = new Burger();
       Bun bun = new Bun("Булка", 34);
       burger.setBuns(bun);
       assertTrue(bun.equals(burger.bun));
    }

    //сравниваем наименования булок из стаба
    @Test
    public void setBunIsReturnRightName(){
        Bun bun = Mockito.mock(Bun.class);
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200j");
        burger.setBuns(bun);
        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void addIngredientsIncreaseSize (){
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void addIngredientsTest(){
        Ingredient ingredient = mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("Филе Люминесцентного тетраодонтимформа");
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(ingredient.getName(), burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredientThrowsExceptionOutOfBounds(){
        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(1));
    }
    @Test
    public void removeIngredientDecreaseSize(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        Mockito.when(ingredient1.getName()).thenReturn("Соус Spicy-X");
        assertAll(
                ()->assertEquals(ingredient1.getName(), burger.ingredients.get(0).getName()),
                ()->assertEquals(1, burger.ingredients.size())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "4, 1, 2, \"Биокотлета из марсианской Магнолии\"",
            "2, 1, 0, \"Сыр с астероидной плесенью\""})
    public void moveIngredientsIsWork(int size, int index, int newIndex, String name){
        Burger burger = new Burger();
        for (int i=0; i<size; i++){
            burger.addIngredient(mock(Ingredient.class));
        }
        Mockito.when(burger.ingredients.get(index).getName()).thenReturn(name);
        burger.moveIngredient(index, newIndex);
        assertEquals(name, burger.ingredients.get(newIndex).getName());
    }
    @Test
    public void getPriceBurgerCallGetPriceBunOnce(){
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        burger.getPrice();
        Mockito.verify(bun, Mockito.times(1)).getPrice();
    }

    @ParameterizedTest
    @CsvSource({"988, 1976","1255, 2510"})
    public void getPriceBurgerisMultiplyByTwoPriceBun(float bunPrice, float doubleBunPrice ){
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(doubleBunPrice, burger.getPrice());
    }


    static Stream<Arguments> isGetPriceTestData() {
        return Stream.of(
                arguments(1255, new float[]{90, 88, 1337, 3000, 4142}, 11167),
                arguments(988, new float[]{90, 88, 424, 874, 4142}, 7594)
        );
    }
    @ParameterizedTest
    @MethodSource("isGetPriceTestData")
    public void getPriceIsReturnRightValue(float bunPrice, float[]ingredientPrice, float expectedPrice){
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (float price : ingredientPrice)
        {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice, burger.getPrice());
    }

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
                "Price: 500,000000\n" ;
        String expectedReceipt2= "(==== red bun ====)\n" +
                "= sauce sour cream =\n" +
                "= filling sausage =\n" +
                "(==== red bun ====)\n" +
                "\n" +
                "Price: 1100,000000\n" ;

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
    public void getRecieptIsReturnRightValue(Bun bun, List<Ingredient> ingredients, String expectedReciept){
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients){
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedReciept, burger.getReceipt());
    }
}