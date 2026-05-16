package com.example.footballranking.service;

import com.example.footballranking.dto.PlayerRequest;
import com.example.footballranking.dto.PlayerResponse;
import com.example.footballranking.entity.Player;
import com.example.footballranking.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public List<PlayerResponse> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerResponse findById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Transactional
    public PlayerResponse create(PlayerRequest request) {
        if (playerRepository.existsByNickname(request.nickname())) {
            throw new DataIntegrityViolationException("Nickname already exists");
        }

        Player player = Player.builder()
                .nickname(request.nickname())
                .realName(request.realName())
                .country(request.country())
                .score(request.score())
                .wins(request.wins())
                .losses(request.losses())
                .build();

        return toResponse(playerRepository.save(player));
    }

    @Transactional
    public PlayerResponse update(Long id, PlayerRequest request) {
        Player player = findEntityById(id);

        playerRepository.findByNickname(request.nickname())
                .filter(existingPlayer -> !existingPlayer.getId().equals(id))
                .ifPresent(existingPlayer -> {
                    throw new DataIntegrityViolationException("Nickname already exists");
                });

        player.setNickname(request.nickname());
        player.setRealName(request.realName());
        player.setCountry(request.country());
        player.setScore(request.score());
        player.setWins(request.wins());
        player.setLosses(request.losses());

        return toResponse(player);
    }

    @Transactional
    public void delete(Long id) {
        Player player = findEntityById(id);
        playerRepository.delete(player);
    }

    private Player findEntityById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + id));
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getNickname(),
                player.getRealName(),
                player.getCountry(),
                player.getScore(),
                player.getWins(),
                player.getLosses()
        );
    }

    @Transactional(readOnly = true)
    public List<PlayerResponse> getRanking() {
        return playerRepository.findAllByOrderByScoreDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
