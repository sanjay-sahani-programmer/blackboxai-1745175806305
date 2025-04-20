package com.example.employeeapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;
}
