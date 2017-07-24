package com.preu.virtualu.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private int id;

    @Column(name = "question")
    @NotEmpty(message = "Ingrese pregunta.")
    private String question;

    @Column(name = "img")
    private String img;

    @Column(name = "options")
    private String options;

    @Column(name = "response")
    private String response;
}
