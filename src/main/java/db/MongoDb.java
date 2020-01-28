package db;

import com.mongodb.MongoClient;
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

    public void createAuthor(String firstName, String lastName, String title, String isbn, int year) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = mongoClient.getDatabase("authorDB");
            Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

            MongoCollection<Document> projectsCollection = mongoDB.getCollection("authors");

            Document doc1 = new Document("first_name", firstName);
            doc1.append("last_name", lastName);
            doc1.append("books", Collections.singletonList(new Document("title", title).append("isbn", isbn).append("published", year)));


            projectsCollection.insertOne(doc1);
            mongoClient.close();
            System.out.println("Författaren har lagts till i databasen!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewBookToAuthor(String lastName, String title, String isbn, int year) {

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = mongoClient.getDatabase("authorDB");
            Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

            MongoCollection<Document> authorsCollection = mongoDB.getCollection("authors");

            Document books = new Document().append("title", title).append("isbn", isbn).append("published", year);

            authorsCollection.updateOne(eq("last_name", lastName), Updates.addToSet("books", books));

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    public void searchAuthor(String firstName, String lastName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDB = mongoClient.getDatabase("authorDB");
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

        MongoCollection<Document> authorCollection = mongoDB.getCollection("authors");

        for (Document doc : authorCollection
                .find(Filters.and(Filters.eq("first_name", firstName), Filters.eq("last_name", lastName)))) {
            ArrayList<Object> books = new ArrayList<>(doc.values());
            System.out.printf("\rTitel: %s%n----------------", books.get(3));
        }
        mongoClient.close();

    }

}
