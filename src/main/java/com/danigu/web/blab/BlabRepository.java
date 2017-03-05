package com.danigu.web.blab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dani
 */
@Repository
public interface BlabRepository extends JpaRepository<Blab, Long> {
    List<Blab> findAllByOrderByCreatedAtDesc();
}
