package it.camerino.qrcodegenerator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "qr_code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "hash")
    String hash;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

}
