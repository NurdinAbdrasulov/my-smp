package it.camerino.qrcodegenerator.controller;

import it.camerino.qrcodegenerator.dto.RegistrationRequestDto;
import it.camerino.qrcodegenerator.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin
public class UserController {

    UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {
        String userId = userService.registerUser(registrationRequestDto.getEmail(), registrationRequestDto.getPassword());
        if (userId != null) {
            return ResponseEntity.ok("User registered successfully. UserID: " + userId);
        } else {
            return ResponseEntity.badRequest().body("Failed to register user.");
        }
    }

    @GetMapping("current")
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
