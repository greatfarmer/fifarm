package net.fifarm.spider.service;

import net.fifarm.spider.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MongoService {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    public boolean insertToMongo(String jsonString) {
        try {
            DBCollection collection = getCollection("player");
            JsonArray items = getItems(jsonString);
            items.forEach(jObj -> collection.insert((DBObject) JSON.parse(jObj.toString())));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private DBCollection getCollection(String name) {
        Mongo mongo = new Mongo(host, port);
        DB db = mongo.getDB(database);
        return db.getCollection(name);
    }

    private JsonArray getItems(String jsonString) {
        JsonObject jsonObject = JsonUtils.getJsonObjectFromString(jsonString);
        return JsonUtils.getJsonArray(jsonObject, "items");
    }

    // db.player.find({firstName: {$regex: "harry", $options: "i"}})
    public DBCursor searchByNameUsingFullMatch(String name) {
        DBCollection collection = getCollection("player");
        DBObject regexQuery = new BasicDBObject();
        regexQuery.put("firstName", new BasicDBObject("$regex", name).append("$options", "i"));
        return collection.find(regexQuery);
    }

    // db.player.find({$text: {$search: "Heung Min"}},{score:{$meta: "textScore"}}).sort({score:{$meta:"textScore"}}).limit(100)
    public DBCursor searchByNameUsingTextScore(String name, int limit) {
        DBCollection collection = getCollection("player");
        DBObject findCommand = new BasicDBObject("$text", new BasicDBObject("$search", name));
        DBObject projectCommand =  new BasicDBObject("score", new BasicDBObject("$meta", "textScore"));
        DBObject sortCommand = new BasicDBObject("score", new BasicDBObject("$meta", "textScore"));
        return collection.find(findCommand, projectCommand).sort(sortCommand).limit(limit);
    }

    // db.player.find({"id":"268635560"})
    public DBCursor searchById(String id, int limit) {
        DBCollection collection = getCollection("player");
        DBObject findCommand = new BasicDBObject("id", id);
        return collection.find(findCommand).limit(limit);
    }

    // db.player.find({$text: {$search: "Min"}},{"_id": false, "firstName": true, "lastName": true, "club": true, "nation": true, "headshot": true, "position": true, "composure": true, "quality": true, "id": true}).sort({"composure": -1}).limit(30)
    public DBCursor searchPlayerNames(String term, int limit) {
        DBCollection collection = getCollection("player");
        DBObject findCommand = new BasicDBObject("$text", new BasicDBObject("$search", term));
        DBObject projectCommand =  new BasicDBObject();
        projectCommand.put("_id", false);
        projectCommand.put("firstName", true);
        projectCommand.put("lastName", true);
        projectCommand.put("club", true);
        projectCommand.put("nation", true);
        projectCommand.put("headshot", true);
        projectCommand.put("position", true);
        projectCommand.put("composure", true);
        projectCommand.put("quality", true);
        projectCommand.put("id", true);
        DBObject sortCommand = new BasicDBObject(new BasicDBObject("composure", -1));
        return collection.find(findCommand, projectCommand).sort(sortCommand).limit(limit);
    }

}