package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.PlanoDeAula;
import edu.ifsp.ped.spring.repository.PlanoDeAulaRepository;


@RestController
public class PlanoDeAulaControle {

    @Autowired
    PlanoDeAulaRepository repo;

    @GetMapping("/api/v1/ped/planosAula")
    public ArrayList<PlanoDeAula> endPoint1() {
        return (ArrayList<PlanoDeAula>) repo.findAll();
    }

    @PostMapping("/api/v1/ped/planosAula")
    public PlanoDeAula endPoint2(@RequestBody PlanoDeAula plano) {
        repo.save(plano);
        return plano;
    }

    @DeleteMapping("/api/v1/ped/planosAula/")
    public boolean endPoint3(@RequestBody PlanoDeAula plano) {
        try {
            repo.delete(plano);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
