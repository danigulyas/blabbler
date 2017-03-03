package com.danigu.web.blab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author dani
 */
@Entity
public class Blab {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
}
