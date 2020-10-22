/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.service;

import fr.panda.controller.exception.PostNotFoundException;
import fr.panda.dto.PostDto;
import fr.panda.entities.Post;
import fr.panda.repository.PostRepository;
import fr.panda.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author gtu
 */
@Service
public class PostService {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    public void createPost(PostDto postDto){
        
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }
    
    public List<PostDto> showAllPosts() {
        
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }
    
    public PostDto readPost(Long id) {
        
        System.out.println("PostService -> readPost -> : id : " + id);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("For id " + id)
        );
        return mapFromPostToDto(post);
    }
    
    private PostDto mapFromPostToDto(Post post) {
        
        PostDto postDto = new PostDto(); 
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        System.out.println("PostService -> showAllPosts -> mapFromPostToDto : ");
        System.out.println("post : "+ post.getTitle());
        System.out.println("postDto : " + postDto.getTitle());
        return postDto;
    }
    
    private Post mapFromDtoToPost(PostDto postDto){
        
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        
        System.out.println("PostService -> showAllPosts -> mapFromDtoToPost : ");
        System.out.println("post : "+ post.getTitle());
        System.out.println("postDto : " + postDto.getTitle());
        return post;
    }
}
