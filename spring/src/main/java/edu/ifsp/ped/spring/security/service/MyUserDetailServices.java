package edu.ifsp.ped.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ifsp.ped.spring.Model.Objetos.Professor;
import edu.ifsp.ped.spring.repository.ProfessorRepository;


public class MyUserDetailServices implements UserDetailsService{

    @Autowired
    ProfessorRepository profRepository;
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Professor userLogin = profRepository.findByUsername(usuario);
        return new MyUserDetails(userLogin);
    }
    
}
