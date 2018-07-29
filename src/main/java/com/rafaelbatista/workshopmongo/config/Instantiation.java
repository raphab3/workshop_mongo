package com.rafaelbatista.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelbatista.workshopmongo.domain.Post;
import com.rafaelbatista.workshopmongo.domain.User;
import com.rafaelbatista.workshopmongo.repositories.PostRepository;
import com.rafaelbatista.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		Post post1 = new Post(null, sdf.parse("29/07/2018"), "Post1 um ", "top de linha", maria);
		Post post2 = new Post(null, sdf.parse("28/07/2018"), "Post2 um ", "Massa d+", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob)); 
		postRepository.saveAll(Arrays.asList(post1, post2)); 
		
		
	}

}
