package br.com.joschonarth.spring_boot_essentials.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface PhysicalAssessmentProjection {

    UUID getStudentId();
    String getStudentName();
    UUID getAssessmentId();
    BigDecimal getWeight();
    BigDecimal getHeight();
    BigDecimal getBodyFatPercentage();
}
