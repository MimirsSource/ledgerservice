package de.mimirssource.accounting.ledgerservice.ledger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LedgerService {

    @Inject
    private MongoClient mongoClient;

    public List<Ledger> list(){
        List<Ledger> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Ledger ledger = new Ledger();
                ledger.setName(document.getString("name"));
                ledger.setDescription(document.getString("description"));
                list.add(ledger);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(Ledger ledger){
        Document document = new Document()
                .append("name", ledger.getName())
                .append("description", ledger.getDescription());
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("accounting").getCollection("ledger");
    }
}
