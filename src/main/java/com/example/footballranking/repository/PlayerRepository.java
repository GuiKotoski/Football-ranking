package com.example.footballranking.repository;

import com.example.footballranking.entity.Player;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByOrderByScoreDesc();
    Optional<Player> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
}
