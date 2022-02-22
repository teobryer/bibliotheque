package fr.eni.dal;

import fr.eni.bo.Editeur;
import fr.eni.bo.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurDAO extends JpaRepository<Editeur, Long> {
}
