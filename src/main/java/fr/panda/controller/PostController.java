/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.controller;

import fr.panda.dto.PostDto;
import fr.panda.repository.PostRepository;
import fr.panda.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gtu
 */

@RestController
//@RequestMapping("/api/posts/")
public class PostController {
    
   @Autowired
   private PostService postService;
   
   @Autowired
   private PostRepository postRepository;
    
   @PostMapping
   public ResponseEntity createdPost(@RequestBody PostDto posDto) {
       postService.createPost(posDto);
       return new ResponseEntity(HttpStatus.OK);
   }
   
   @GetMapping("/api/posts/all")
   public ResponseEntity<List<PostDto>> showAllPosts() {
       return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
   }
   
   @GetMapping("/api/posts/{id}")
   public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
       System.out.println(" PostController Id : " +id);
       return new ResponseEntity<>(postService.readPost(id), HttpStatus.OK);
   }
   
   @GetMapping("/api/tests/all")
   public ResponseEntity<String> showAllTests() {
       return new ResponseEntity<>("tests", HttpStatus.OK);
   }
   
}
