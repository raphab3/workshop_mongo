package com.rafaelbatista.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelbatista.workshopmongo.DTO.AuthorDTO;
import com.rafaelbatista.workshopmongo.DTO.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob)); 
		
		Post post1 = new Post(null, sdf.parse("29/07/2018"), "Post1 um ", "top de linha", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("28/07/2018"), "Post2 um ", "Massa d+", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Gostei", sdf.parse("29/07/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Massa d+", sdf.parse("27/07/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Muito bom", sdf.parse("10/07/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		 
		
		
	}

}
