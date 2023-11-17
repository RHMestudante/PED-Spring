package edu.ifsp.ped.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ifsp.ped.spring.repository.ProfessorRepository;


public class MyUserDetailServices implements UserDetailsService{

    @Autowired
    ProfessorRepository profRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario userLogin = profRepository.findByUsername(username);
        return new MinhaUsuarioDetails(userLogin);
    }
    
}
