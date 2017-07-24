package com.preu.virtualu.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "code")
public class Code {


    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_id")
    private int id;

    @Column(name = "code")
    @NotEmpty(message = "Ingrese codigo")
    private String code;

    @OneToOne(mappedBy = "code")
    private User user;

    @Column(name = "expire_date")
    private String expire_date;

    @Column(name = "create_date")
    private String create_date;

    @Column(name = "collage")
    private String collage;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "code_form", joinColumns = @JoinColumn(name = "code_id"), inverseJoinColumns = @JoinColumn(name = "form_id"))
    private Set<Form> forms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
