package program;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHandler {
    /**
     * Gets input from user as String.
     *
     * @return the string input
     */
    public String getStringInput() {
        Scanner sc = new Scanner(System.in);
        String in;
        do {
            try {
                in = sc.nextLine();
                break;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } while (true);
        return in;
    }

    /**
     * Gets input from user as int.
     *
     * @return the int input
     */

    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        int in;
        do {
            try {
                in = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } while (true);
        return in;
    }
}
