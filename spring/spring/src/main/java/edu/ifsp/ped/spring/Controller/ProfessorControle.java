package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.ifsp.ped.spring.Model.Objetos.Professor;
import edu.ifsp.ped.spring.Model.ObjetosDAO.ProfessorDAO;

public class ProfessorControle {

    @GetMapping("/api/v1/ped/professor/")
    public ArrayList<Professor> endPoint1() {
        return ProfessorDAO.buscarBancoP();
    }

    @PostMapping("/api/v1/ped/professor/")
    public String endPoint2(@RequestBody Professor prof) {
        ProfessorDAO.adicionaP(prof);
        return "Professor salvo";
    }

    @PutMapping("/api/v1/ped/professor/{prof_cod}/{novoD}")
    public String endPoint3(@PathVariable("prof_cod") int cod, @PathVariable("novoD") String dado) {

        ProfessorDAO.editaUsu(cod,dado);
        return "Professor editado";
    }

    @DeleteMapping("/api/v1/ped/professor/{prof_cod}")
    public String endPoint4(@PathVariable("prof_cod") int id) {
        try {
            ProfessorDAO.apagaBancoProf(id);
            return "Prof deletado";
        } catch (Exception e) {
            return "Erro " + e;
        }
    }
    
}
