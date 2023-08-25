package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Aula;
import edu.ifsp.ped.spring.Model.ObjetosDAO.AulaDAO;

@RestController
public class AulaController {

    @GetMapping("/api/v1/ped/aula/")
    public ArrayList<Aula>endPoint1() {
        return AulaDAO.buscarBancoA();
    }

    @GetMapping("/api/v1/ped/aula/{aula_cod}")
    public Aula endPoint2(@PathVariable("aula_cod") int cod) {
        ArrayList<Aula> aulas = AulaDAO.buscarBancoA();
        return aulas.get(cod);
    }

    @PostMapping("/api/v1/ped/aula/")
    public String endPoint3(@RequestBody Aula aula) {
        AulaDAO.adiciona(aula);
        return "Aula salva";
    }

    @DeleteMapping("{/api/v1/ped/aula/{aula_cod}")
    public String endPoint4(@PathVariable("aula_cod") int cod) {
        try {
            AulaDAO.apagaBancoAula(cod);
            return "Aula deletada";
        } catch (Exception e) {
            return "Erro " + e;
        }
    }
    
}