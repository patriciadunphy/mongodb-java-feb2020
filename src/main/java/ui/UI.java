package ui;

import program.InputHandler;
import program.Program;

public class UI {
    public void run() {
        InputHandler inp = new InputHandler();
        Program program = new Program();

        while (true) {
            System.out.println("--------------------------\n--------------------------");
            System.out.println(
                    "Vad vill du göra?\n1. Lägg till författare och bok\n2. Sök på författare\n3. Lägg till ny bok\n4. Avsluta programmet");
            System.out.println("--------------------------\n--------------------------");
            switch (inp.getIntInput()) {
                case 1:
                    program.createAuthor();
                    break;
                case 2:
                    program.searchAuthor();
                    break;
                case 3:
                    program.addBooksToAuthor();
                    break;
                case 4:
                    System.out.println("Programmet avslutas");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Felaktig inmatning");
                    break;


            }

        }
    }
}
