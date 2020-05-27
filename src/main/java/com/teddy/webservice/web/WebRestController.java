package com.teddy.webservice.web;

import com.teddy.webservice.domain.posts.PostsRepository;
import com.teddy.webservice.dto.posts.PostSaveRequestDto;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }
    
    @PostMapping("/posts")
    public void savePosts(@RequestBody PostSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }
}
