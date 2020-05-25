package labs.lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MyStringUtilsTest {

    @Test
    void testfindWordsWithDifferentCharacters() {
        String str = "qwertyq qwerty qwertyw qwerty qwertye";
        String[] words = MyStringUtils.findWordsWithDifferentCharacters(str);
        assertArrayEquals(new String[]{"qwerty", "qwerty"}, words);
    }
}
