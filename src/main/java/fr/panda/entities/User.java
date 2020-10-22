/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gtu
 */

@Entity
@Table
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    
    //l'attribut userName sera unique 
    @Column(unique=true)  
    private String userName;
    private String password;
    private String email; 
    
}
