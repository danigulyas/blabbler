package com.danigu.web.blab;

import com.danigu.web.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author dani
 */
@Entity
@Data
public class Blab {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private Date createdAt = new Date();
    @OneToOne(targetEntity = User.class)
    private User owner;
}
