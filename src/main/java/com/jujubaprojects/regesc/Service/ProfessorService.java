package com.jujubaprojects.regesc.Service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.jujubaprojects.regesc.Model.Professor;
import com.jujubaprojects.regesc.Repository.ProfessorRepository;

@Service
public class ProfessorService {

    private ProfessorRepository pRepository;

    public ProfessorService(ProfessorRepository pRepository){
      this.pRepository = pRepository;
    }
    

    public void menu(Scanner scanner){
        

        Boolean isTrue = true;
	Scanner in = new Scanner(System.in);

	while(isTrue){
		System.out.println("Qual entidade vc deseja interarigr"
		+"\n 0 - Sair" 
		+"\n 1 - Professor");
		int op = Integer.parseInt(in.nextLine());

	   switch(op){

		case 1:  this.cadastrar(in); break;

		default:
		isTrue = false;  break;
	   }
	}

}

     private void cadastrar(Scanner in){

        System.out.println("Digite o nome do Professor");
        String nome = in.nextLine();
    
        System.out.println("Digite o prontuario do Professor");
        String prontuario = in.nextLine();

        Professor professor = new Professor(nome , prontuario);
        pRepository.save(professor);
        System.out.println(" \n Professor salvo no Banco !!!");
    
    }

}