package com.jujubaprojects.regesc.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    private int idade;

    @ManyToMany(mappedBy = "alunos")
     private List<Disciplina>disciplinas;
   //  private set<Disciplina>disciplinas;


    public Aluno(){
        
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Aluno(long id, String nome, int idade, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.disciplinas = disciplinas;
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
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade +"]";
    }
}
