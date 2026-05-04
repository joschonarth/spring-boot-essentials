package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPhysicalAssessmentRepository extends JpaRepository<PhysicalAssessmentEntity, UUID> {

    @Query("""
        SELECT s.id AS studentId,
               s.name AS studentName,
               pa.id AS assessmentId,
               pa.weight AS weight,
               pa.height AS height,
               pa.bodyFatPercentage AS bodyFatPercentage
        FROM PhysicalAssessmentEntity pa
        JOIN StudentEntity s ON s.physicalAssessment = pa
        """)
    List<PhysicalAssessmentProjection> getAllAssessments();

    @Query(value = """
        SELECT s.id AS studentId,
               s.name AS studentName,
               pa.id AS assessmentId,
               pa.weight AS weight,
               pa.height AS height,
               pa.bodyFatPercentage AS bodyFatPercentage
        FROM PhysicalAssessmentEntity pa
        JOIN StudentEntity s ON s.physicalAssessment = pa
        """,
    countQuery = """
        SELECT count(pa.id)
        FROM PhysicalAssessmentEntity pa
        JOIN StudentEntity s ON s.physicalAssessment = pa
        """)
    Page<PhysicalAssessmentProjection> getAllAssessmentsPage(Pageable pageable);
}
