/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.repository;

import fr.panda.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gtu
 */

@RestController
public interface PostRepository extends JpaRepository <Post, Long> {
    
}
