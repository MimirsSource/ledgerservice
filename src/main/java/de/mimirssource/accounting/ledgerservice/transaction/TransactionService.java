package de.mimirssource.accounting.ledgerservice.transaction;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import de.mimirssource.accounting.ledgerservice.ledger.Ledger;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TransactionService {

    @Inject
    private MongoClient mongoClient;

    public List<Transaction> list(){
        List<Transaction> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Transaction transaction = new Transaction();
                transaction.setAmount(document.getDouble("amount"));
                transaction.setSource(document.getString("source"));
                transaction.setTarget(document.getString("target"));
                list.add(transaction);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(final Transaction transaction){
        Document document = new Document()
                .append("amount", transaction.getAmount())
                .append("source", transaction.getSource())
                .append("target", transaction.getTarget());
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("accounting").getCollection("transaction");
    }
}
