package com.example.cartiapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cartiapp.model.Carte;
import com.example.cartiapp.service.CarteService;

@Controller
public class CarteController {

    private final CarteService service;

    public CarteController(CarteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String afisareCarti(Model model) {
        model.addAttribute("carti", service.toateCartile());
        model.addAttribute("carte", new Carte());
        model.addAttribute("mesaj", "Lista cartilor preluate prin repository.");
        return "carti";
    }

    @PostMapping("/adauga")
    public String adaugaCarte(@ModelAttribute Carte carte, Model model) {
        String mesaj = service.adaugaCarte(carte);
        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", service.toateCartile());
        model.addAttribute("carte", new Carte());
        return "carti";
    }

    @PostMapping("/modifica")
    public String modificaCarte(@ModelAttribute Carte carte, Model model) {
    	
    	String mesaj = service.modificaCarte(carte);
        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", service.toateCartile());
        model.addAttribute("carte", new Carte());
        return "carti";
    }


    @PostMapping("/sterge")
    public String stergeCarte(@RequestParam String isbn, Model model) {
        String mesaj = service.stergeCarte(isbn);
        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", service.toateCartile());
        model.addAttribute("carte", new Carte());
        return "carti";
    }

    @PostMapping("/filtreaza")
    public String filtreaza(@RequestParam String autor, Model model) {
        List<Carte> carti = service.cartiDupaAutor(autor);
        String mesaj = autor.isBlank() ?
                "S-au afisat toate cartile din baza de date." :
                "Cărţile următoare aparţin autorului " + autor + ".";
        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", carti);
        model.addAttribute("carte", new Carte());
        return "carti";
    }
    
    //  filtrarea dupa autor si titlul 
    @PostMapping("/filtreaza2")
   
    public String filtreazaDupaAutorSiTitlu(
        @RequestParam(required = false) String autor, 
        @RequestParam(required = false) String titlu, 
        Model model) {

        if (autor == null) autor = "";
        if (titlu == null) titlu = "";

        List<Carte> carti = service.cartiDupaAutorSiTitlu(autor, titlu);

        String mesaj;
        if (autor.isBlank() && titlu.isBlank()) {
            mesaj = "S-au afișat toate cărțile.";
        } else {
            mesaj = "Rezultatele pentru filtrul: ";
            if (!autor.isBlank()) mesaj += "autor = " + autor + "; ";
            if (!titlu.isBlank()) mesaj += "titlu = " + titlu + ";";
        }

        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", carti);
        model.addAttribute("carte", new Carte());
        return "carti";
    }

}
// modificare sa se faca doar daca sunt date completate 
// filtarare dupa autor si titlu
// afisam intr un tabel cu bordura html simplu