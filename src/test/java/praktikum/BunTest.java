package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {
    @ParameterizedTest
    @CsvSource({"\"Флюоресцентная булка R2-D3\", 988","\"Краторная булка N-200i\", 1255"})
    public void gettersOfBunAreReturnRightValue(String name, float price) {
        Bun bun = new Bun(name, price);
        assertAll(
                ()->assertEquals(name, bun.getName(),"Возвращается корректное название булки"),
                ()->assertEquals(price, bun.getPrice(), "Возвращается корректная цена булки")
        );
    }
}