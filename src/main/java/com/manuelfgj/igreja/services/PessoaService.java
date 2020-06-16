package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.PessoaDTO;
import com.manuelfgj.igreja.entities.Pessoa;
import com.manuelfgj.igreja.entities.repositories.PessoaRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repo;

	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma diocese que contem entidades relacionadas");
		}
	}
	
	public List<Pessoa> findAll(){
		return repo.findAll();
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), null, null, null, null, null, null);
	}

}
