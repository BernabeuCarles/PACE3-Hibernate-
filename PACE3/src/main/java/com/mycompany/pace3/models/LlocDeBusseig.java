/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pace3.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author carles
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "LlocDeBusseig")
public class LlocDeBusseig {

    //ATRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lloc;

    @Column
    private String nom;

    @Column
    private String ubicacio;

    //RELACIONS
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "Visita",
            joinColumns = {
                @JoinColumn(name = "lloc_id", foreignKey = @ForeignKey(name = "FK_VIS_LLOC"))},
            inverseJoinColumns = {
                @JoinColumn(name = "bussejador_id", foreignKey = @ForeignKey(name = "FK_VIS_BUS"))})
    @EqualsAndHashCode.Exclude
    private List <Bussejador> elsBussejadors = new ArrayList<>();

    public void addBus(Bussejador b) {
        if (!elsBussejadors.contains(b)) {
            elsBussejadors.add(b);
            b.addLloc(this);
        }
    }

    //CONSTRUCTOR
    public LlocDeBusseig(String nom, String ubicacio) {
        this.nom = nom;
        this.ubicacio = ubicacio;
    }
}
