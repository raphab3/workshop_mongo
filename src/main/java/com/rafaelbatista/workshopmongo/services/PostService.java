package com.rafaelbatista.workshopmongo.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelbatista.workshopmongo.domain.Post;
import com.rafaelbatista.workshopmongo.repositories.PostRepository;
import com.rafaelbatista.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	
	//Buscar por id
	public Post findById(String id) {  
		Optional<Post> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public List<Post> findByTitle(String text) {  
		
		return repo.searchTitle(text);
	}
	
	
}
