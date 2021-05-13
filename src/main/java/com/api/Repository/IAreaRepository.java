package com.api.Repository;

import com.api.Entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.awt.geom.Area;
import java.util.List;

public interface IAreaRepository extends JpaRepository<Areas, Long> {

    @Query("SELECT a FROM Areas a where a.arNombre LIKE :area%")
    List<Areas> filerAreas(String area);
}
