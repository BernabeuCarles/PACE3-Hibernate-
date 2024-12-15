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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author carles
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Bussejador")
public class Bussejador implements Serializable{
    
    static final long serialVersionUID = 17L;
    
    //ATRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bussejador;

    @Column
    private String nom;

    @Column
    private String nivell;

    //RELACIONS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "equip_id",
            foreignKey = @ForeignKey(name = "FK_EQUIP_BUS"))
    private Equip equip;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="master_id",
                foreignKey = @ForeignKey(name = "FK_MASTER" ))
    private Master master;
    
    @ManyToMany(mappedBy="elsBussejadors",cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List <LlocDeBusseig> elsLlocs = new ArrayList<>();
    
    public void addLloc(LlocDeBusseig l){   
        if (!elsLlocs.contains(l)){            
            elsLlocs.add(l);    
            l.addBus(this);
        }
    }
    
    //CONSTRUCTOR
    public Bussejador(String nom, String nivell) {
        this.nivell = nivell;
        this.nom = nom;
    }
}
