package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name = "Auteur")
@Getter
@Setter
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;


}
