package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Professor;
import edu.ifsp.ped.spring.repository.ProfessorRepository;


@RestController
public class ProfessorControle {

    @Autowired
    ProfessorRepository repo;

    @GetMapping("/api/v1/ped/professor/")
    public ArrayList<Professor> endPoint1() {
        return (ArrayList<Professor>) repo.findAll();
    }

    @PostMapping("/api/v1/ped/professor/")
    public Professor endPoint2(@RequestBody Professor prof) {
        repo.save(prof);
        return prof;
    }

    @PutMapping("/api/v1/ped/professor/")
    public boolean endPoint3(@RequestBody Professor prof) {
        try {
            Optional<Professor> professorExistente = repo.findById(prof.getId());
    
            if (professorExistente.isPresent()) {
                Professor professor = professorExistente.get();
                professor.setNomeC(prof.getNomeC());
                professor.setSenha(prof.getSenha());
                repo.save(professor);
                
                return true; 
            } else {
                return false; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    @DeleteMapping("/api/v1/ped/professor/")
    public boolean endPoint4(@RequestBody Professor prof) {
        try {
            repo.delete(prof);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
