package com.yarikonen.web4.Data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Dot {
    @Id
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "dot_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dot_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private Double x;
    private Double y;
    private Double r;
    private LocalTime birthTime;
    private String hit = "error";
    private long exTime;
    private String userName;

    public Dot(Double x, Double y, Double r, String userName) {
        this.x=x;
        this.y=y;
        this.r=r;
        this.userName=userName;
    }
}
