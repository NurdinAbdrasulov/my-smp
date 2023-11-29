package it.camerino.qrcodegenerator.repository;

import it.camerino.qrcodegenerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
