package com.example.cartiapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.cartiapp.model.Carte;
import com.example.cartiapp.repository.CarteRepository;

@Service
public class CarteService {
    private final CarteRepository repo;

    public CarteService(CarteRepository repo) {
        this.repo = repo;
    }

    public List<Carte> toateCartile() {
        return repo.findAll();
    }

    public List<Carte> cartiDupaAutor(String autor) {
        return autor.isBlank() ? repo.findAll() : repo.findByAutor(autor);
    }

    public String adaugaCarte(Carte c) {
        if (c.getIsbn().isBlank() || c.getTitlu().isBlank() || c.getAutor().isBlank())
            return "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!";
        repo.save(c);
        return "Adaugare realizata cu succes!";
    }

    public String modificaCarte(Carte c) {
       // am veificat ca toate campurile sa fiecompletate
        if (c.getIsbn() == null || c.getIsbn().trim().isEmpty() ||
            c.getTitlu() == null || c.getTitlu().trim().isEmpty() ||
            c.getAutor() == null || c.getAutor().trim().isEmpty()) {
            return "Toate câmpurile (ISBN, Titlu, Autor) trebuie completate pentru modificare.";
        }

      
        Optional<Carte> carteOpt = repo.findById(c.getIsbn());
        if (carteOpt.isEmpty()) {
            return "Nu se găsește nicio carte cu ISBN-ul introdus.";
        }

       
        repo.save(c);
        return "Cartea cu ISBN-ul " + c.getIsbn() + " a fost modificată!";
    }


    public String stergeCarte(String isbn) {
        Optional<Carte> carteOpt = repo.findById(isbn);
        if (carteOpt.isEmpty())
            return "Nu exista carte cu acest ISBN!";
        repo.deleteById(isbn);
        return "Cartea cu ISBN-ul " + isbn + " a fost stearsa!";
    }
    
    public List<Carte> cartiDupaAutorSiTitlu(String autor, String titlu) {
        if ((autor == null || autor.isBlank()) && (titlu == null || titlu.isBlank())) {
            return repo.findAll();
        }
        if (autor != null && !autor.isBlank() && (titlu == null || titlu.isBlank())) {
            return repo.findByAutor(autor);
        }
        if ((autor == null || autor.isBlank()) && titlu != null && !titlu.isBlank()) {
            return repo.findByTitlu(titlu);
        }
        return repo.findByAutorAndTitlu(autor, titlu);
    }

    
}
