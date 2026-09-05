package com.devhub.backend.controller;

import com.devhub.backend.dto.UserRequest;
import com.devhub.backend.dto.UserResponse;
import com.devhub.backend.entity.User;
import com.devhub.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = userService.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                UserResponse.fromEntity(userService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserRequest request) {

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        User createdUser = userService.create(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromEntity(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        User updatedUser = new User();
        updatedUser.setName(request.name());
        updatedUser.setEmail(request.email());
        updatedUser.setPassword(request.password());

        return ResponseEntity.ok(
                UserResponse.fromEntity(
                        userService.update(id, updatedUser)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
