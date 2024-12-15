/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pace3.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author carles
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Equip")
public class Equip implements Serializable{
    
    static final long serialVersionUID = 17L;
    
    //ATRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equip;

    @Column
    private String tipus;

    //RELACIONS
    @OneToOne(mappedBy = "equip" )
    private Bussejador bussejador;

    //CONSTRUCTOR
    public Equip(String tipus) {
        this.tipus = tipus;
    }
}
