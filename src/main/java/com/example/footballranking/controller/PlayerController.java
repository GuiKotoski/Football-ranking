package com.example.footballranking.controller;

import com.example.footballranking.dto.PlayerRequest;
import com.example.footballranking.dto.PlayerResponse;
import com.example.footballranking.service.PlayerService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> findAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<PlayerResponse>> getRanking() {
        return ResponseEntity.ok(playerService.getRanking());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest request) {
        PlayerResponse response = playerService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PlayerRequest request
    ) {
        return ResponseEntity.ok(playerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
