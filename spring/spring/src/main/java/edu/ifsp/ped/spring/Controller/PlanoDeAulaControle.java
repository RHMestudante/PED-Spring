package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.PlanoDeAula;
import edu.ifsp.ped.spring.Model.ObjetosDAO.PlanoAulaDAO;

@RestController
public class PlanoDeAulaControle {

    @GetMapping("/api/v1/ped/planosAula")
    public ArrayList<PlanoDeAula> endPoint1() {
        return PlanoAulaDAO.buscarBancoPA();
    }

    @PostMapping("/api/v1/ped/planosAula")
    public PlanoDeAula endPoint2(@RequestBody PlanoDeAula plano) {
        PlanoAulaDAO.adicionaPA(plano);
        return plano;
    }

    @DeleteMapping("/api/v1/ped/planosAula/{turmaPlan}")
    public boolean endPoint3(@PathVariable("turmaPlan") String turmaPlan) {
        try {
            PlanoAulaDAO.apagaBancoPA(turmaPlan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
