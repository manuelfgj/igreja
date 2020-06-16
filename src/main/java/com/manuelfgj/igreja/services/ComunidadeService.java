package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.ComunidadeDTO;
import com.manuelfgj.igreja.entities.Comunidade;
import com.manuelfgj.igreja.entities.repositories.ComunidadeRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class ComunidadeService {
	
	@Autowired
	ComunidadeRepository repo;

	public Comunidade find(Integer id) {
		Optional<Comunidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comunidade.class.getName()));
	}
	
	public Comunidade insert(Comunidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Comunidade update(Comunidade obj) {
		Comunidade newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Comunidade newObj, Comunidade obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma comunidade que contem entidades relacionadas");
		}
	}
	
	public List<Comunidade> findAll(){
		return repo.findAll();
	}
	
	public Page<Comunidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Comunidade fromDTO(ComunidadeDTO objDto) {
		return new Comunidade(objDto.getId(), objDto.getNome(), null, null);
	}
}
