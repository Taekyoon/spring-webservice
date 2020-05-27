package com.teddy.webservice.domain.posts;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void loadSavedPost() {
		//given
		postsRepository.save(Posts.builder()
				.title("Test title")
				.content("Test content")
				.author("test@test.com")
				.build());
		
		//when
		List<Posts> postList = postsRepository.findAll();
		
		//then
		Posts posts = postList.get(0);
		assertThat(posts.getTitle(), is("Test title"));
		assertThat(posts.getContent(), is("Test content"));
	}
	
	@Test
	public void registerBaseTime() {
		//given
		LocalDateTime now = LocalDateTime.now();
		
		postsRepository.save(Posts.builder()
				.title("Test title")
				.content("Test content")
				.author("test@test.com")
				.build());
		
		//when
		List<Posts> postList = postsRepository.findAll();
		
		//then
		Posts posts = postList.get(0);
		assertTrue(posts.getCreateTime().isAfter(now));
		assertTrue(posts.getModifiedTime().isAfter(now) );
	}
}
