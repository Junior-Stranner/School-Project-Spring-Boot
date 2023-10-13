package com.jujubaprojects.regesc.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;                                                     
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "Professores")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false,unique = true)
    private String prontuario;

   @OneToMany(mappedBy = "professor",cascade = CascadeType.ALL)
   private List<Disciplina> disciplinas;

   
   
   

    public Professor( String nome, String prontuario) {

    this.nome = nome;
    this.prontuario = prontuario;
//    this.disciplinas = disciplinas;
}


    public Professor() {
        
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

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    @PreRemove
    public void atualizaDisciplinaOnRemove(){
        System.out.println("***********atualizaDisciplinaOnRemove************");
        for (Disciplina disciplina : this.getDisciplinas()) {
            disciplina.setProfessor(null);
        }
    }

   

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
    }


    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
  
   
  
}
