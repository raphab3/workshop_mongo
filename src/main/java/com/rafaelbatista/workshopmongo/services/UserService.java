package com.rafaelbatista.workshopmongo.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelbatista.workshopmongo.DTO.UserDTO;
import com.rafaelbatista.workshopmongo.domain.User;
import com.rafaelbatista.workshopmongo.repositories.UserRepository;
import com.rafaelbatista.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	//Buscar por todos
	public List<User> findAll(){
		return repo.findAll();
	}
	
	//Buscar por id
	public User findById(String id) {  
		Optional<User> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	//inserir
	public User insert(User obj) {  
		return repo.insert(obj);	
	}
	//Deletar
		public void delete(String id) { 
			findById(id);
			repo.deleteById(id);	
	}
		
	//inserir via DTO
	public User fromDTO(UserDTO objDto) {  
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
			
	}
	
	
	
	
}
