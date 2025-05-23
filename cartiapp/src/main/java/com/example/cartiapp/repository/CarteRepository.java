package com.example.cartiapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cartiapp.model.Carte;

public interface CarteRepository extends JpaRepository<Carte, String> {
  
    List<Carte> findByAutor(String autor);
    List<Carte> findByTitlu(String titlu);
    List<Carte> findByAutorAndTitlu(String autor, String titlu);

}
