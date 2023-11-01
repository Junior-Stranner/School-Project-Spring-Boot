package com.jujubaprojects.regesc.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.jujubaprojects.regesc.Model.Aluno;
import com.jujubaprojects.regesc.Model.Disciplina;
import com.jujubaprojects.regesc.Model.Professor;
import com.jujubaprojects.regesc.Repository.AlunoRepository;
import com.jujubaprojects.regesc.Repository.DisciplinaRepository;
import com.jujubaprojects.regesc.Repository.ProfessorRepository;

@Service
public class DisciplinaService {
    
    private  DisciplinaRepository disciplinaRepository;
     private ProfessorRepository pRepository;
     private AlunoRepository  alunoRepository;


    public DisciplinaService(DisciplinaRepository disciplinaRepository,ProfessorRepository pRepository, AlunoRepository  alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.pRepository = pRepository;
        this.alunoRepository = alunoRepository;
    }

    
    public void menu(){

        Scanner in = new Scanner(System.in);
        boolean isTrue = true;
        int op ;
       

        do{
            System.out.println("Qual entidade você deseja interagir"
            + "\n 0 - Voltar ao menu anterior"
            + "\n 1 - Cadastrar Disciplina"
            + "\n 2 - Atualizar Disciplina"
            + "\n 3 - Visualizar todas as Disciplinas"
            + "\n 4 - deletar Disciplinas"
            + "\n 5 - Matricular aluno");
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
                deletar(in);  break;

            case 5 : matricularAluno(in);
            default:
               
                isTrue = false;
                break;
        }


        }while(isTrue);
    }


    private void matricularAluno(Scanner in) {

         List<Aluno> alunosNovos = new ArrayList<>();

        System.out.println(" Id do aluno a ser matriculado (Digite 0 para Sair)");
        long alunoId = Long.parseLong(in.nextLine());

        if(alunoId > 0){
            System.out.println("ALuno id : "+alunoId);
            Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);

            if(alunoOptional.isPresent()){

             alunosNovos.add(alunoOptional.get());
          //   Aluno aluno = alunoOptional.get();

            }else{
                System.out.println("nenhum aluno possui id : "+alunoId+" !");
            }
    }
       
            System.out.println("-----------------------------------------------");
            System.out.println("\n Digite o id da Disciplina para matricular o aluno");
            long id = Long.parseLong(in.nextLine());

            Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
            if(disciplinaOptional.isPresent()){
                Disciplina disciplina = disciplinaOptional.get();
                disciplina.getAlunos().addAll(alunosNovos);
                this.disciplinaRepository.save(disciplina);

            }else{
                System.out.println("\n O id da Disciplina informado : "+id+" não é válido !");
            }
    }


    private void deletar(Scanner in) {

        System.out.println("qual Disciplina vc deseja Remover ?");
        long id = Long.parseLong(in.nextLine());

        disciplinaRepository.deleteById(id);
        System.out.println("Disciplina com Id: "+id+" Removido com sucesso !");

    }


    private void visualizar() {

        Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();

        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina);

        }
    }


    private void atualizar(Scanner in) {

        System.out.println("Digite o id da Disciplina a ser atualizado");
        long id = Long.parseLong(in.nextLine());

        Optional<Disciplina> discipliOptional = disciplinaRepository.findById(id);

        if(discipliOptional.isPresent()){
            Disciplina disciplina = discipliOptional.get();

            System.out.println("Digita o nome da Disciplina");
            String nome = in.nextLine();

            System.out.println("digita qaul o semestre ");
            int semestre = Integer.parseInt(in.nextLine());

           System.out.println("Digite o id do Professor");
           long professorId = Long.parseLong(in.nextLine());


            Optional<Professor> professorOptional = this.pRepository.findById(id);

            if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
    
                
         //   disciplina.setId(id);
            disciplina.setNome(nome);
            disciplina.setSemestre(semestre);
            disciplina.setProfessor(professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Disciplina  atualizada com sucesso");

        }else{
            System.out.println("Não existe esse id :"+id+" , digite novamente...");
       
        }
      }
    }


    private void cadastrar(Scanner in) {

        System.out.println("Digite o nome da Disciplina");
        String nome = in.nextLine();

        System.out.println("Digite qual semestre ");
        int semestre = Integer.parseInt(in.nextLine());

        System.out.println("Digite o id do Professor");
        long professorId = Long.parseLong(in.nextLine());


        Optional<Professor> professorOptional = pRepository.findById(professorId);

        if(professorOptional.isPresent()){
          Professor professor = professorOptional.get();
          Disciplina disciplina = new Disciplina(nome,semestre,professor);
          disciplinaRepository.save(disciplina);
          System.out.println("Salvo !");

        } else{
              System.out.println("Professor com id :"+professorId+ " inválida");
        }
    }
}
