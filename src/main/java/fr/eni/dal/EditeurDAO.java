package fr.eni.dal;

import fr.eni.bo.Editeur;
import fr.eni.bo.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditeurDAO extends JpaRepository<Editeur, Long> {


    @Query("FROM Editeur e WHERE lower(e.nom)  = ?1")
    List<Editeur> findByNameNoCaseSensitive(String nom);

}
