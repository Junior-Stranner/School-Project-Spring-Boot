package com.jujubaprojects.regesc.Service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jujubaprojects.regesc.Model.Disciplina;
import com.jujubaprojects.regesc.Model.Professor;
import com.jujubaprojects.regesc.Repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorService {

    private ProfessorRepository pRepository;

    public ProfessorService(ProfessorRepository pRepository) {
        this.pRepository = pRepository;
    }

    public void menu(){
        boolean isTrue = true;
        Scanner in = new Scanner(System.in);

        while (isTrue) {
            System.out.println("Qual entidade você deseja interagir"
                    + "\n 0 - Voltar ao menu anterior"
                    + "\n 1 - Cadastrar Professor"
                    + "\n 2 - Atualizar Professor"
                    + "\n 3 - Visualizar todos os professores"
                    + "\n 4 - deletar Professor"
                    + "\n 5 - visualizar um Professor(Disciplinas)");
            int op = Integer.parseInt(in.nextLine());

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
                    
                case 5:
                    visualizarProfessor(in);    
                default:
                    isTrue = false;
                    break;
            }
        }
    }

    private void visualizarProfessor(Scanner in) {

        System.out.println("digite o id do Professor ");
        long professorId = Long.parseLong(in.nextLine());

        Optional<Professor> professorOptional = pRepository.findById(professorId);

        if(professorOptional.isPresent()){
            Professor professor = professorOptional.get();

            System.out.println("Professor {"
            +"\n Id "+professor.getId()
            +"\n Nome "+professor.getNome()
            +"\n Prontuario "+professor.getProntuario());

            System.out.println("Disciplinas [");
            
            for (Disciplina disciplina : professor.getDisciplinas()) {
                
                System.out.println("\n id "+disciplina.getId()
                +"\n Nome "+disciplina.getId()
                +"\n Semestre "+disciplina.getSemestre());
            }
            System.out.println("] \n }");
        }

    }

    private void deletar(Scanner in) {

        System.out.println("Digite o id do Professor a ser atualizado");
        long id = Long.parseLong(in.nextLine());

        this.pRepository.deleteById(id);
        System.out.println("Professor com Id: "+id+" Removido com sucesso !");
      }



    private void visualizar() {
        Iterable<Professor> professores = this.pRepository.findAll();

        for (Professor professor : professores) {
            System.out.println(professor);
        }
    }

    private void atualizar(Scanner in) {
        System.out.println("Digite o id do Professor a ser atualizado");
        long id = Long.parseLong(in.nextLine());

        Optional<Professor> professorOptional = this.pRepository.findById(id);

        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();

            System.out.println("Digite o nome do Professor");
            String nome = in.nextLine();

            System.out.println("Digite o prontuário do Professor");
            String prontuario = in.nextLine();

            professor.setNome(nome);
            professor.setProntuario(prontuario);
            pRepository.save(professor);
            System.out.println("Professor atualizado com sucesso");
        } else {
            System.out.println("O ID do Professor informado: " + id + " é inválido\n");
        }
    }

    private void cadastrar(Scanner in) {
        System.out.println("Digite o nome do Professor");
        String nome = in.nextLine();

        System.out.println("Digite o prontuário do Professor");
        String prontuario = in.nextLine();

        Professor professor = new Professor(nome, prontuario);
        pRepository.save(professor);
        System.out.println("\nProfessor salvo no Banco!!!");
     }
 }
