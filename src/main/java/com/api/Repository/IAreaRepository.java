package com.api.Repository;

import com.api.Entity.Areas;
import org.springframework.data.repository.CrudRepository;

public interface IAreaRepository extends CrudRepository<Areas, Long> {
}
