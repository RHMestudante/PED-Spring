package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Similaridade;
import edu.ifsp.ped.spring.Model.ObjetosDAO.SimilaridadeDAO;


@RestController
public class SimilaridadeController {

    @GetMapping("/api/v1/ped/similaridades/")
    public ArrayList<Similaridade> endPoint1() {
        return SimilaridadeDAO.buscarBancoSM();
    }

    @PostMapping("/api/v1/ped/similaridades")
    public String endPoint2(@RequestBody Similaridade similaridade) {
        SimilaridadeDAO.adiciona(similaridade);
        return "Similaridade salva";
    }

    @PutMapping("/api/v1/ped/similaridades/{sim_cod}/{novo_v}")
    public String endPoint3(@PathVariable("sim_cod") int cod, @PathVariable("novo_v") Double valor) {
        SimilaridadeDAO.editaValor(cod, valor);
        return "Similariade editada";
    }    
}
