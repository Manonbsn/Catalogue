package com.glisse.catalogue.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id ;

    @Column(name = "nom")
    private  String nom;

    @Column(name = "couleur")
    private  String couleur;

    @Column(name = "prix")
    private  int prix;

    @Column(name = "description")
    private  String description;
}
