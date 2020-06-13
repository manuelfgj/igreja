package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.ParoquiaDTO;
import com.manuelfgj.igreja.entities.Paroquia;
import com.manuelfgj.igreja.entities.repositories.ParoquiaRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class ParoquiaService {
	
	@Autowired
	ParoquiaRepository repo;

	public Paroquia find(Integer id) {
		Optional<Paroquia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paroquia.class.getName()));
	}
	
	public Paroquia insert(Paroquia obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Paroquia update(Paroquia obj) {
		Paroquia newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Paroquia newObj, Paroquia obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma paroquia que contem entidades relacionadas");
		}
	}
	
	public List<Paroquia> findAll(){
		return repo.findAll();
	}
	
	public Page<Paroquia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Paroquia fromDTO(ParoquiaDTO objDto) {
		return new Paroquia(objDto.getId(), objDto.getNome(), null, null, null);
	}
}
