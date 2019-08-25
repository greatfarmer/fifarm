package com.fifarm.spider.json;

import com.fifarm.spider.dto.Player;
import com.fifarm.spider.service.HomeService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonToObjectTest {

    @Test
    public void jsonToPlayer() throws Exception {
        HomeService homeService = new HomeService();
        Player player = homeService.jsonToPlayer();

        assertThat(player.getFirstName()).isEqualTo("Heung Min");
        assertThat(player.getLastName()).isEqualTo("Son");
        assertThat(player.getPosition()).isEqualTo("ST");
        assertThat(player.getAge()).isEqualTo(27);
    }

}
