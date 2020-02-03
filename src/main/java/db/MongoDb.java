package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;


public class MongoDb {

    private static MongoClient instance;

    private String url = "mongodb://localhost:27017";
    private String dataBaseName = "authorDB";
    private String collectionName = "authors";

    private MongoClient getMongoClient() {
        if (instance == null) {
            instance = new MongoClient(new MongoClientURI(url));
        }
        return instance;
    }

    private MongoDatabase getDB() {
        return getMongoClient().getDatabase(dataBaseName);
    }

    private MongoCollection<Document> getAuthorsCollection() {
        return getDB().getCollection(collectionName);
    }

    public void insertAuthor(String firstName, String lastName, String title, String isbn, int year) {
        try {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.ALL);

            Document doc1 = new Document("first_name", firstName);
            doc1.append("last_name", lastName);
            doc1.append("books", Collections.singletonList(new Document("title", title).append("isbn", isbn).append("published", year)));
            getAuthorsCollection().insertOne(doc1);

            System.out.println("Författaren har lagts till i databasen!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewBookToAuthor(String lastName, String title, String isbn, int year) {

        try {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.ALL);

            Document books = new Document().append("title", title).append("isbn", isbn).append("published", year);

            getAuthorsCollection().updateOne(eq("last_name", lastName), Updates.addToSet("books", books));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findAuthor(String firstName, String lastName) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.ALL);

        for (Document doc : getAuthorsCollection()
                .find(Filters.and(Filters.eq("first_name", firstName), Filters.eq("last_name", lastName)))) {
            ArrayList<Object> books = new ArrayList<>(doc.values());
            System.out.printf("\rTitel: %s%n----------------", books.get(3));
        }

    }
}