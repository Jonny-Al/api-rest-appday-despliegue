package com.api.Repository;

import com.api.Entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAreaRepository extends JpaRepository<Areas, Long> {
}
