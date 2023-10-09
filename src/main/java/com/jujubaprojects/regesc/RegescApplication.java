package com.jujubaprojects.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jujubaprojects.regesc.Model.Disciplina;
import com.jujubaprojects.regesc.Service.DisciplinaService;
import com.jujubaprojects.regesc.Service.ProfessorService;


@SpringBootApplication
public class RegescApplication implements CommandLineRunner{

    private ProfessorService professorService;
	private DisciplinaService disciplinaService;


	public RegescApplication(ProfessorService professorService , DisciplinaService disciplinaService){
		this.professorService = professorService;
        this.disciplinaService = disciplinaService;

	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}



	@Override
    public void run(String... args) throws Exception {
    // Correção: Use a palavra-chave "new" para criar uma instância de Professor.
     
	Boolean isTrue = true;
	Scanner in = new Scanner(System.in);

	while(isTrue){
		System.out.println("Qual entidade vc deseja interarigr"
		+"\n 0 - Sair" 
		+"\n 1 - Professor"
        +"\n 2 - Disciplina");
		int op = Integer.parseInt(in.nextLine());

	   switch(op){
		 
		case 1: this.professorService.menu(); break;

		case 2: this.disciplinaService.menu(); break;

       
		default : isTrue = false;  break;
	   }
	}
}

}
