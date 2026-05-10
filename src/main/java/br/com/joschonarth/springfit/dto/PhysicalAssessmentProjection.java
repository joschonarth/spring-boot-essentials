package br.com.joschonarth.springfit.dto;

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
