package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IStudentRepository extends JpaRepository<StudentEntity, UUID> {

    Optional<StudentEntity> findByEmail(String email);

    @Query(value = "SELECT s FROM StudentEntity s JOIN FETCH s.physicalAssessment WHERE s.id = :studentId")
    Optional<StudentEntity> findByIdFetch(UUID studentId);
}
