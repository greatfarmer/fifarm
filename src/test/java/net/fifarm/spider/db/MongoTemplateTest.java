package net.fifarm.spider.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void existTest() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("84106873"));
        boolean existsId = mongoTemplate.exists(query, "player");
        assertThat(existsId).isTrue();
    }

}
