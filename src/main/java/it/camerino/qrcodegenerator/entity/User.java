package it.camerino.qrcodegenerator.entity;

import it.camerino.qrcodegenerator.enums.UserRole;
import it.camerino.qrcodegenerator.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    UserRole role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "created_at")
    LocalDateTime createdAt;

;


}
