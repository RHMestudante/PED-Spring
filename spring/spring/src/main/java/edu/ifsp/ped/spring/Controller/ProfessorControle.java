package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Professor;
import edu.ifsp.ped.spring.Model.ObjetosDAO.ProfessorDAO;

@RestController
public class ProfessorControle {

    @GetMapping("/api/v1/ped/professor/")
    public ArrayList<Professor> endPoint1() {
        return ProfessorDAO.buscarBancoP();
    }

    @PostMapping("/api/v1/ped/professor/")
    public Professor endPoint2(@RequestBody Professor prof) {
        ProfessorDAO.adicionaP(prof);
        return prof;
    }

    @PutMapping("/api/v1/ped/professor/{prof_nomeC}/{novoD}")
    public boolean endPoint3(@PathVariable("prof_nomeC") String nome, @PathVariable("novoD") String dado) {
        ProfessorDAO.editaUsu(nome,dado);
        return true;
    }

    @DeleteMapping("/api/v1/ped/professor/{prof_nomeC}")
    public boolean endPoint4(@PathVariable("prof_nomeC") String nome) {
        try {
            ProfessorDAO.apagaBancoProf(nome);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
