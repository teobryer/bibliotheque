package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Livre")
@Getter @Setter
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn (name="id_auteur")
    private Auteur auteurObj;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn (name="id_editeur")
    private Editeur editeur;

    @Column(name = "isbn")
    private String isbn;

    @Column(name="titre")
    private String titre;

    @Column(name="auteur")
    private String auteur;

    @Column(name="lu")
    private Boolean lu;

    @Column(name="nbPages")
    private Integer nbPages;

    @Lob
    @Column(name="resume")
    private String resume;


    public Livre() {
    }

    public Livre(String isbn, String titre, String auteur, Boolean lu, Integer nbPages, String resume) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.lu = lu;
        this.nbPages = nbPages;
        this.resume = resume;
        this.auteurObj = new Auteur();
    }
}
