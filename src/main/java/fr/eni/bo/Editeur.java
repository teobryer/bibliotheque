package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Embeddable
@Table(name = "Editeur")
@Getter
@Setter
public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(name = "nom")
    @NotBlank

    private String nom;




}
