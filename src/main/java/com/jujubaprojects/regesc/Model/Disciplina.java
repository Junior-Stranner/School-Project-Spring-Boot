package com.jujubaprojects.regesc.Model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    private int semestre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id",nullable = false)
    private Professor professor;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disciplinas_alunos",
    joinColumns = @JoinColumn(name = "disciplinas_id"), 
    inverseJoinColumns = @JoinColumn(name = "alunos_id"))
     private List<Aluno>alunos;


    public List<Aluno> getAlunos() {
        return alunos;
    }



    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }  

    public Disciplina( String nome, int semestre, Professor professor) {
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professor;
    }

    public Disciplina(){

    }



    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }



    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", nome=" + nome + ", semestre=" + semestre + ", professor=" + professor
                + ", alunos=" + alunos + "]";
    }





   
    
}
