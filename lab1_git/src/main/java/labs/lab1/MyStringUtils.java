package labs.lab1;

import java.util.Arrays;

public class MyStringUtils {

    public static String[] findWordsWithDifferentCharacters(String str) {
        String[] words = str.trim().split("\\s+");

        String[] ret = new String[words.length];
        int n = 0;

        for (String word : words) {
            boolean accept = true;
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (word.charAt(j) == word.charAt(i)) {
                        accept = false;
                        break;
                    }
                }
                if (!accept) {
                    break;
                }
            }
            if (accept) {
                ret[n++] = word;
            }
        }

        if (n < ret.length) {
            ret = Arrays.copyOf(ret, n);
        }

        return ret;
    }
}
