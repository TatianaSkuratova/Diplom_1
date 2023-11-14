package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database = new Database();

    @Test
    public void databaseIsNotEmpty(){
        assertAll(
                ()-> assertFalse(database.availableBuns().isEmpty()),
                ()->assertFalse(database.availableIngredients().isEmpty())
        );
    }

}