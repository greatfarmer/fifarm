package com.fifarm.spider.repository;

import com.fifarm.spider.dto.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void test() {
        Player player = new Player();
        player.setFirstName("Seonghun");
        player.setLastName("Kang");

        Player newPlayer = playerRepository.save(player);

        assertThat(newPlayer).isNotNull();

        Optional<Player> existingPlayer = playerRepository.findByFirstName(newPlayer.getFirstName());
        assertThat(existingPlayer).isNotEmpty();

        Optional<Player> nonExistingPlayer = playerRepository.findByFirstName("greatfarmer");
        assertThat(nonExistingPlayer).isEmpty();
    }

}