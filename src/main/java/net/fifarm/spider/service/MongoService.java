package net.fifarm.spider.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import net.fifarm.spider.util.DateUtils;
import net.fifarm.spider.util.JsonUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private String playerCollection = "player";

    public void insertToMongo(String jsonString) {
        JsonArray items = getItems(jsonString);
        items.forEach(this::tryInsertItemToMongo);
    }

    private void tryInsertItemToMongo(JsonElement jEle) {
        String id = jEle.getAsJsonObject().get("id").getAsString();

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        boolean existsId = mongoTemplate.exists(query, playerCollection);

        if (!existsId) {
            String now = DateUtils.getNow();
            jEle.getAsJsonObject().addProperty("createdDate", now);
            jEle.getAsJsonObject().addProperty("updatedDate", now);
            mongoTemplate.insert((DBObject) JSON.parse(jEle.toString()), playerCollection);
        }
    }

    private JsonArray getItems(String jsonString) {
        JsonObject jsonObject = JsonUtils.getJsonObjectFromString(jsonString);
        return JsonUtils.getJsonArray(jsonObject, "items");
    }

    // db.player.find({$text: {$search: "Heung Min"}},{score:{$meta: "textScore"}}).sort({score:{$meta:"textScore"}}).limit(100)
    public List<Document> searchByNameUsingTextScore(String name, int limit) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(name);
        Query query = TextQuery.queryText(textCriteria).sortByScore().limit(limit);
        return mongoTemplate.find(query, Document.class, playerCollection);
    }

    // db.player.find({"id":"268635560"})
    public List<Document> searchById(String id, int limit) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.limit(limit);
        return mongoTemplate.find(query, Document.class, playerCollection);
    }

    // db.player.find({$text: {$search: "Min"}},{"_id": false, "firstName": true, "lastName": true, "club": true, "nation": true, "headshot": true, "position": true, "composure": true, "quality": true, "id": true}).sort({"composure": -1}).limit(30)
    public List<Document> searchPlayerNames(String term, int limit) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(term);
        Query query = TextQuery.queryText(textCriteria);
        query.fields()
                .exclude("_id")
                .include("firstName")
                .include("lastName")
                .include("club")
                .include("nation")
                .include("headshot")
                .include("position")
                .include("composure")
                .include("quality")
                .include("id");
        query.with(new Sort(Sort.Direction.DESC, "composure"));
        query.limit(limit);
        return mongoTemplate.find(query, Document.class, playerCollection);
    }

}
