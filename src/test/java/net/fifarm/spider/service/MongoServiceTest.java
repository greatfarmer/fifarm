package net.fifarm.spider.service;

import net.fifarm.spider.util.FileUtils;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceTest {

    @Autowired
    MongoService mongoService;

    @Test
    public void insertToMongo() throws IOException {
        String jsonString = FileUtils.readFile("test/json/SeonghunKang.json");
        mongoService.insertToMongo(jsonString);
    }

    @Test
    public void searchByNameUsingTextScore() {
        List<Document> players = mongoService.searchByNameUsingTextScore("Seonghun", 1);
        assertThat(players.size()).isEqualTo(1);
        assertThat(players.get(0).get("firstName")).isEqualTo("Seonghun");
    }

    @Test
    public void searchById() {
        List<Document> players = mongoService.searchById("9107107107", 1);
        assertThat(players.size()).isEqualTo(1);
        assertThat(players.get(0).get("firstName")).isEqualTo("Seonghun");
    }

    @Test
    public void searchPlayerNames() {
        List<Document> players = mongoService.searchPlayerNames("Seonghun", 1);
        assertThat(players.size()).isEqualTo(1);
        assertThat(players.get(0).get("firstName")).isEqualTo("Seonghun");
    }

}