package program;

import db.MongoDb;
import ui.InputInstructions;

public class Program {
    public void createAuthor() {
        MongoDb db = new MongoDb();
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

        db.createAuthor(firstName, lastName, title, isbn, year);
    }
    public void addBooksToAuthor() {
        MongoDb db = new MongoDb();
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
        MongoDb db = new MongoDb();
        InputHandler inp = new InputHandler();
        InputInstructions instr = new InputInstructions();

        instr.typeAuthorFirstName();
        String firstName = inp.getStringInput();
        instr.typeAuthorLastName();
        String lastName = inp.getStringInput();

        db.searchAuthor(firstName, lastName);
    }
}
