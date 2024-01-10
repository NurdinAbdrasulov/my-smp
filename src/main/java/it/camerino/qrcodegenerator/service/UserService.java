package it.camerino.qrcodegenerator.service;

import com.google.firebase.auth.UserRecord;
import it.camerino.qrcodegenerator.dto.UserDto;
import it.camerino.qrcodegenerator.entity.User;

public interface UserService {
    String registerUser(String email, String password);

    UserRecord getCurrentUser();

    User getCurrentUserEntity();

    static UserDto toModel(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .status(user.getStatus())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
