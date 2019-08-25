package com.fifarm.spider.repository;

import com.fifarm.spider.dto.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>  {
    Optional<Player> findByFirstName(String firstName);
}