package com.iftm.postsMongoDB.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
	}

}
