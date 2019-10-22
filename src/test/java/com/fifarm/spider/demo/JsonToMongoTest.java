package com.fifarm.spider.demo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import org.junit.Test;

public class JsonToMongoTest {

    private String host = "34.85.116.234";
    private int port = 27017;
    private String database = "fifarm";
    private String dummyColl = "dummyColl";

    @Test
    public void jsonToMongo() {
        try {
            Mongo mongo = new Mongo(host, port);
            DB db = mongo.getDB(database);
            DBCollection collection = db.getCollection(dummyColl);

            // convert JSON to DBObject directly
            DBObject dbObject = (DBObject) JSON
                    .parse("{'name':'seonghun', 'age':32}");

            collection.insert(dbObject);

            DBCursor cursorDoc = collection.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
