package com.fireside.pantry.db.api;

import org.junit.jupiter.api.Test;

public class TheMealDBTest {

    @Test
    public void test_GetRecipesByFirstLetter() {
        String result = TheMealDB.getInstance().getRecipesByFirstLetter('a');
        if (result == null)
            throw new AssertionError();
    }
}
