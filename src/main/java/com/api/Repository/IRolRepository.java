package com.api.Repository;

import com.api.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IRolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.rolNombre LIKE :rol%")
    List<Rol> filterRol(String rol);
}
