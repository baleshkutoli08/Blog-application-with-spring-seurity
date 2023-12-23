package com.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comment_id;
    private String email;
    private String name;
    private String body;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private post post;
}
