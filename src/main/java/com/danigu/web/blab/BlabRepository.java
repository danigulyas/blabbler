package com.danigu.web.blab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dani
 */
@Repository
public interface BlabRepository extends JpaRepository<Blab, Long> {
}
