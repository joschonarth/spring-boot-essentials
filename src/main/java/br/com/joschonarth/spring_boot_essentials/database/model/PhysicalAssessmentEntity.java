package br.com.joschonarth.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "physical_assessment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhysicalAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private BigDecimal height;

    @Column(name = "body_fat_percentage", nullable = false)
    private BigDecimal bodyFatPercentage;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
