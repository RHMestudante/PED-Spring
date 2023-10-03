package edu.ifsp.ped.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifsp.ped.spring.Model.Objetos.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
