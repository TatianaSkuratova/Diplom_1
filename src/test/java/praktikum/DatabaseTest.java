package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DatabaseTest {
    Database database = new Database();

    @Test
    public void databaseIsNotEmpty() {
        assertAll(
                () -> assertFalse(database.availableBuns().isEmpty()),
                () -> assertFalse(database.availableIngredients().isEmpty())
        );
    }

}