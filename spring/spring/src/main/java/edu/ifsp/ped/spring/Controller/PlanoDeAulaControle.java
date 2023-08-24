package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.ifsp.ped.spring.Model.Objetos.PlanoDeAula;
import edu.ifsp.ped.spring.Model.ObjetosDAO.PlanoAulaDAO;

public class PlanoDeAulaControle {

    @GetMapping("/api/v1/ped/aula/professor/planosAula")
    public ArrayList<PlanoDeAula> endPoint1() {
        return PlanoAulaDAO.buscarBancoPA();
    }

    @PostMapping("/api/v1/ped/aula/professor/")
    public String endPoint2(@RequestBody PlanoDeAula plano) {
        PlanoAulaDAO.adicionaPA(plano);
        return "Plano salvo";
    }

    @DeleteMapping("/api/v1/ped/aula/professor/planosAula/{turmaPlan}")
    public String endPoint3(@PathVariable("turmaPlan") String turmaPlan) {
        try {
            PlanoAulaDAO.apagaBancoPA(turmaPlan);
            return "Plano deletado";
        } catch (Exception e) {
            return "Erro " + e;
        }
    }
    
}
