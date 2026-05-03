package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.UUID;

public interface IPhysicalAssessmentRepository extends JpaRepository<PhysicalAssessmentEntity, UUID> {

    @NativeQuery(value = """
        SELECT s.id AS studentId,
               s.name AS studentName,
               pa.id AS assessmentId, 
               pa.weight AS weight,
               pa.height AS height,
               pa.body_fat_percentage AS bodyFatPercentage
        FROM physical_assessment pa
        INNER JOIN student s
        ON s.physical_assessment_id = pa.id
        """)
    List<PhysicalAssessmentProjection> getAllAssessments();
}
