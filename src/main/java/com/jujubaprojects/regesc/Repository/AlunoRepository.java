package com.jujubaprojects.regesc.Repository;

import org.springframework.data.repository.CrudRepository;

import com.jujubaprojects.regesc.Model.Aluno;
import java.util.List;


public interface AlunoRepository extends CrudRepository <Aluno, Long>{
    
    List<Aluno> findByNome(String nome);

    List<Aluno> findByFirstnameStartingWith(String termo);

    List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome, int idade);
}
