package br.com.joschonarth.springfit.database.repository;

import br.com.joschonarth.springfit.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRolesRepository extends JpaRepository<RolesEntity, UUID> {

    Optional<RolesEntity> findByName(String role);
}
