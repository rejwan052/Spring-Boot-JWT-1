package com.preu.virtualu.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "form")
public class Form {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "form_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tags")
    private String tags;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "question_form", joinColumns = @JoinColumn(name = "form_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
