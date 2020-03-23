package net.fifarm.spider.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    MongoTemplate mongoTemplate;

    String collectionName = "test";
    Query query = new Query(Criteria.where("type").is("sample"));

    @Before
    public void addPlayer() {
        Update update = new Update().set("type", "sample");
        mongoTemplate.upsert(query, update, collectionName);
    }

    @Test
    public void existTest() {
        assertThat(mongoTemplate.exists(query, collectionName)).isTrue();
        mongoTemplate.remove(query, collectionName);
    }

}
