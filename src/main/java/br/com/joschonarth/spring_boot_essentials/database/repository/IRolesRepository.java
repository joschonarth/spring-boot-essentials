package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRolesRepository extends JpaRepository<RolesEntity, UUID> {
}
