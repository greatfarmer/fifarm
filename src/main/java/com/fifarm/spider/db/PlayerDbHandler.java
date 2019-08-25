package com.fifarm.spider.db;

import com.fifarm.spider.dto.Player;
import com.fifarm.spider.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDbHandler {

    @Autowired
    PlayerRepository playerRepository;

    public void addPlayer(Player player) {
        Optional<Player> existingPlayer = playerRepository.findByFirstName(player.getFirstName());
        boolean isNewPlayer = !existingPlayer.isPresent() || !player.equals(existingPlayer.get());
        if (isNewPlayer) {
            playerRepository.save(player);
        }
    }
}
