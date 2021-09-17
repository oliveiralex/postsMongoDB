package com.iftm.postsMongoDB.config;

import java.time.Instant;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.iftm.postsMongoDB.models.embedded.Author;
import com.iftm.postsMongoDB.models.embedded.Comment;
import com.iftm.postsMongoDB.models.entities.Post;
import com.iftm.postsMongoDB.models.entities.User;
import com.iftm.postsMongoDB.repositories.PostRepository;
import com.iftm.postsMongoDB.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	/* 
	 * @PostConstruct permite que o método seja executado assim que o contexto da app
	 * for iniciado. Ou seja, depois que o componente foi criado, então é executado. 
	 * */
	
	@PostConstruct
	public void init() {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Gray", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, Instant.parse("2021-09-16T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
		Post post2 = new Post(null, Instant.parse("2021-09-15T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));
		
		Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2021-09-16T12:34:26Z"), new Author(alex));
		Comment c2 = new Comment("Aproveite", Instant.parse("2021-09-16T15:34:26Z"), new Author(bob));
		Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-09-15T12:34:26Z"), new Author(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);		
	}

}
