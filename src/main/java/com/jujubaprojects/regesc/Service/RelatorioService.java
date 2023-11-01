package com.jujubaprojects.regesc.Service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.jujubaprojects.regesc.Model.Aluno;
import com.jujubaprojects.regesc.Repository.AlunoRepository;

@Service
public class RelatorioService {
    
    private AlunoRepository alunoRepository;

    public RelatorioService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

        public void menu(Scanner in){
      boolean isTrue = true;
      int op;

      do{
        System.out.println("Qual Relatório vc deseja"
        + "\n 0 - Voltar ao menu anterior"
        + "\n 1 - Aluno por um dados Nome"
        + "\n 2 - ALunos por dados nome e idade menor ou igual");
       op = Integer.parseInt(in.nextLine()); 

       switch (op) {
        case 1:
            alunosPorNome(in);
            break;
        
        case 2: alunosPorNomeIdadeMenorOuIgual(in);break;    
      

        default:
           
            isTrue = false;
            break;
    }
      }while(isTrue);
        
    }

        private void alunosPorNomeIdadeMenorOuIgual(Scanner in) {

            System.out.print("Nome Aluno :");
            String nome = in.nextLine();

            System.out.println("Idade : ");
            int idade = Integer.parseInt(in.nextLine());

            List<Aluno> alunos = alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome,idade);

            for (Aluno aluno : alunos) {
            System.out.println(alunos);

            }
        }

        private void alunosPorNome(Scanner in) {

            System.out.print("Nome Aluno :");
            String nome = in.nextLine();

            List<Aluno> alunos = alunoRepository.findByNomeStartingWith(nome);

            for (Aluno aluno : alunos) {
            System.out.println(alunos);

            }
          //  alunos.forEach(System.out::prinln);
        }
}
