package labs.lab1;

import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("enter words (empty to stop): ");
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] result = MyStringUtils.findWordsWithDifferentCharacters(line);
            System.out.println("result: " + Arrays.toString(result));
        }
    }
}
