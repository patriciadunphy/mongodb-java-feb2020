package program;

import db.MongoDb;
import ui.InputInstructions;

public class Program {
    MongoDb db = new MongoDb();

    public void createAuthor() {
        InputHandler inp = new InputHandler();
        InputInstructions instr = new InputInstructions();

        instr.typeAuthorFirstName();
        String firstName = inp.getStringInput();
        instr.typeAuthorLastName();
        String lastName = inp.getStringInput();
        instr.typeBookTitle();
        String title = inp.getStringInput();
        instr.typeISBN();
        String isbn = inp.getStringInput();
        instr.typeYear();
        int year = inp.getIntInput();
        db.insertAuthor(firstName, lastName, title, isbn, year);
    }
    public void addBooksToAuthor() {
        InputHandler inp = new InputHandler();
        InputInstructions instr = new InputInstructions();
        instr.typeAuthorLastName();
        String lastName = inp.getStringInput();
        instr.typeBookTitle();
        String title = inp.getStringInput();
        instr.typeISBN();
        String isbn = inp.getStringInput();
        instr.typeYear();
        int year = inp.getIntInput();

        db.addNewBookToAuthor(lastName, title, isbn, year);

    }
    public void searchAuthor() {
        InputHandler inp = new InputHandler();
        InputInstructions instr = new InputInstructions();

        instr.typeAuthorFirstName();
        String firstName = inp.getStringInput();
        instr.typeAuthorLastName();
        String lastName = inp.getStringInput();
        db.findAuthor(firstName, lastName);
    }
}
