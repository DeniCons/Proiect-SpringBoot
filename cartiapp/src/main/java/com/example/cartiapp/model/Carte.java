package com.example.cartiapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carte {
    @Id
    private String isbn;

    private String titlu;
    private String autor;

   
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitlu() {
        return titlu;
    }
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
}


/*import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carte {
    @Id
    private String isbn;
    private String titlu;
    private String autor;
}
*/