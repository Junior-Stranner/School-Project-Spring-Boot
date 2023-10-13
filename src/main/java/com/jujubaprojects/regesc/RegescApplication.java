package com.jujubaprojects.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jujubaprojects.regesc.Service.AlunoService;
import com.jujubaprojects.regesc.Service.DisciplinaService;
import com.jujubaprojects.regesc.Service.ProfessorService;


@SpringBootApplication
public class RegescApplication implements CommandLineRunner{

    private ProfessorService professorService;
	private DisciplinaService disciplinaService;
	private AlunoService alunoService;

/*os objetos passado por paramêtro são injetados automáticamente pelo spring
 * pq suas classes possuem a anotação @service
 */
	public RegescApplication(ProfessorService professorService , DisciplinaService disciplinaService, AlunoService alunoService){
		this.professorService = professorService;
        this.disciplinaService = disciplinaService;
		this.alunoService = alunoService;

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
        +"\n 2 - Disciplina"
		+"\n 3 - aluno");
		int op = Integer.parseInt(in.nextLine());

	   switch(op){
		 
		case 1: this.professorService.menu(); break;

		case 2: this.disciplinaService.menu(); break;

		case 3: this.alunoService.menu(in);break;

       
		default : isTrue = false;  break;
	   }
	}
}

}
