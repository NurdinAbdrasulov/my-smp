package it.camerino.qrcodegenerator.entity;

import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "qr_code_dtl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrCodeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "qr_code_id")
    QrCode qrCode;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    QrCodeDetailStatus status;

    @Column(name = "link")
    String link;

    @Column(name = "qr_color")
    String qrColor;

    @Column(name = "background_color")
    String backgroundColor;

    @Column(name = "scan_number")
    BigInteger scanNumber;//How many times QR code  was scanned

    @Column(name = "frame")
    String frame;
}
