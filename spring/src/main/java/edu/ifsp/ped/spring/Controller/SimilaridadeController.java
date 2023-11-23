package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Similaridade;
import edu.ifsp.ped.spring.repository.SimilaridadeRepository;



@RestController
public class SimilaridadeController {

    @Autowired
    SimilaridadeRepository repo;

    @GetMapping("/api/v1/ped/similaridades/")
    public ArrayList<Similaridade> endPoint1() {
        return (ArrayList<Similaridade>) repo.findAll();
    }

    @PostMapping("/api/v1/ped/similaridades")
    public Similaridade endPoint2(@RequestBody Similaridade similaridade) {
        repo.save(similaridade);
        return similaridade;
    }

    @PutMapping("/api/v1/ped/similaridades/")
    public boolean endPoint3(@RequestBody Similaridade similaridade) {
        try {
            Optional<Similaridade> smExistente = repo.findById(similaridade.getId());
    
            if (smExistente.isPresent()) {
                Similaridade sm = smExistente.get();
                sm.setValor(similaridade.getValor());
                repo.save(sm);
                return true; 
            } else {
                return false; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }    
}
