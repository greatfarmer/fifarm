package com.fifarm.spider.simple;

import com.fifarm.spider.dto.Player;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    @Test
    public void test() {
        Player player = new Player();
        player.setFirstName("son");

        String firstName = Optional.ofNullable(player.getFirstName()).orElse("none");
        String lastName = Optional.ofNullable(player.getLastName()).orElse("none");

        assertThat(firstName).isEqualTo("son");
        assertThat(lastName).isEqualTo("none");
    }

}
