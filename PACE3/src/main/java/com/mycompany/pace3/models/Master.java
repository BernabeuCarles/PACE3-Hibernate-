/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pace3.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author carles
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="Master")
public class Master {
    
    //ATRIBUTS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_master;
    
    @Column 
    private String nom;
    
    @Column 
    private String especialitat;
    
    //RELACIONS
    @OneToMany(mappedBy="master",
            cascade=CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List <Bussejador> bussejadors = new ArrayList();
    
    //CONSTRUCTOR
    public Master(String nom, String especialitat){
        this.nom=nom;
        this.especialitat=especialitat;
    }
    
    public void addBus (Bussejador b){
        this.bussejadors.add(b);
    }
}


