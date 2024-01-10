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

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

    @Column(name = "link")
    String link;

    @Column(name = "color")
    String color;

    @Column(name = "scan_number")
    BigInteger scanNumber;//How many times QR code  was scanned


}
