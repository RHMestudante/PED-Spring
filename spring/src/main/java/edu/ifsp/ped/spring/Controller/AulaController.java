package edu.ifsp.ped.spring.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.ped.spring.Model.Objetos.Aula;
import edu.ifsp.ped.spring.repository.AulaRepository;


@RestController
public class AulaController {

    @Autowired
    AulaRepository repo;

    @GetMapping("/api/v1/ped/aula/")
    public ArrayList<Aula>endPoint1() {
        return (ArrayList<Aula>) repo.findAll();
    }


    @PostMapping("/api/v1/ped/aula/")
    public Aula endPoint3(@RequestBody Aula aula) {
        repo.save(aula);
        return aula;
    }

    @DeleteMapping("/api/v1/ped/aula/")
    public boolean endPoint4(@RequestBody Aula aula) {
        try {
            repo.delete(aula);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}