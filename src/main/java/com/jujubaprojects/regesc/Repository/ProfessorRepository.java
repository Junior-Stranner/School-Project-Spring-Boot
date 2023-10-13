package com.jujubaprojects.regesc.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.jujubaprojects.regesc.Model.Professor;

@Service
public interface ProfessorRepository extends CrudRepository <Professor, Long>{

    
}
