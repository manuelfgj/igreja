package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.GrupoDTO;
import com.manuelfgj.igreja.entities.Grupo;
import com.manuelfgj.igreja.entities.repositories.GrupoRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoService {
	
	@Autowired
	GrupoRepository repo;

	public Grupo find(Integer id) {
		Optional<Grupo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Grupo.class.getName()));
	}
	
	public Grupo insert(Grupo obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Grupo update(Grupo obj) {
		Grupo newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Grupo newObj, Grupo obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma grupo que contem entidades relacionadas");
		}
	}
	
	public List<Grupo> findAll(){
		return repo.findAll();
	}
	
	public Page<Grupo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Grupo fromDTO(GrupoDTO objDto) {
		return new Grupo(objDto.getId(), objDto.getNome(), null);
	}
}
