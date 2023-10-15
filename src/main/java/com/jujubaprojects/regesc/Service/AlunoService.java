package com.jujubaprojects.regesc.Service;

import java.util.Optional;
import java.util.Scanner;


import org.springframework.stereotype.Service;

import com.jujubaprojects.regesc.Model.Aluno;
import com.jujubaprojects.regesc.Model.Disciplina;
import com.jujubaprojects.regesc.Repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoService {
    
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void menu(Scanner in){
      boolean isTrue = true;
      int op;

      do{
        System.out.println("Qual entidade você deseja interagir"
        + "\n 0 - Voltar ao menu anterior"
        + "\n 1 - Cadastrar Aluno"
        + "\n 2 - Atualizar Aluno"
        + "\n 3 - Visualizar todas as Alunos"
        + "\n 4 - deletar Alunos"
        +"\n  5 - visualizar Alunos e disciplinas");
       op = Integer.parseInt(in.nextLine()); 

       switch (op) {
        case 1:
            cadastrar(in);
            break;
        case 2:
            atualizar(in);
            break;
        case 3:
            visualizar();
            break;

        case 4:
            deletar(in);    
            break;

        case 5:
            visualizarAluno(in);

        default:
           
            isTrue = false;
            break;
    }
      }while(isTrue);
        
    }
 
    @Transactional
private void visualizarAluno(Scanner in) {

      System.out.println("Digite o id do ALuno ");
      Long id = Long.parseLong(in.nextLine());

      Optional<Aluno> alunoOptional = alunoRepository.findById(id);

      if(alunoOptional.isPresent()){
        Aluno aluno = alunoOptional.get();

        System.out.println("\n Id :"+aluno.getId()
        +"\n Nome : "+aluno.getNome()
        +"\n Idade : "+aluno.getIdade());
        System.out.println("Disciplinas : [");
       
        if(aluno.getDisciplinas() != null){
            for (Disciplina disciplina : aluno.getDisciplinas()) {
                
                System.out.println("Disciplinas : "+disciplina.getNome() );
                System.out.println("\n Semestre : "+disciplina.getSemestre());
            }
        }

        System.out.println("]");
      }else{
        System.out.println("ALuno com Id: "+id+" é inváido !");

      }
    }

    
    private void deletar(Scanner in) {

        System.out.println("Digite o id do ALuno ");
        Long id = Long.parseLong(in.nextLine());

        alunoRepository.deleteById(id);
        System.out.println("ALuno com Id: "+id+" Removido com sucesso !");
    }

    private void visualizar() {

        Iterable<Aluno> alunos = alunoRepository.findAll();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        System.out.println("---------------------");
    }

    private void atualizar(Scanner in) {

        System.out.println("Digite o id do ALuno ");
        Long id = Long.parseLong(in.nextLine());

        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if(alunoOptional.isPresent()){
          Aluno aluno = alunoOptional.get();
          
        System.out.println("Digite o nome do Aluno");
        String nome = in.nextLine();

        System.out.println("Digite a idade do Aluno");
        int idade = Integer.parseInt(in.nextLine());

        aluno.setNome(nome);
        aluno.setIdade(idade);

        alunoRepository.save(aluno);

        }
    }

    private void cadastrar(Scanner in) {

        System.out.println("Digite o nome do Aluno");
        String nome = in.nextLine();

        System.out.println("Digite a idade do Aluno");
        int idade = Integer.parseInt(in.nextLine());

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        alunoRepository.save(aluno);
        System.out.println("\n Aluno salvo no Banco!!!");
    }
}
