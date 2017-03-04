package com.danigu.web.blab;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author dani
 */
@Entity
@Data
public class Blab {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
}
