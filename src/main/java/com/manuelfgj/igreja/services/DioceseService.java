package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.DioceseDTO;
import com.manuelfgj.igreja.entities.Diocese;
import com.manuelfgj.igreja.entities.repositories.DioceseRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class DioceseService {
	
	@Autowired
	DioceseRepository repo;

	public Diocese find(Integer id) {
		Optional<Diocese> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Diocese.class.getName()));
	}
	
	public Diocese insert(Diocese obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Diocese update(Diocese obj) {
		Diocese newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Diocese newObj, Diocese obj) {
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
	
	public List<Diocese> findAll(){
		return repo.findAll();
	}
	
	public Page<Diocese> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Diocese fromDTO(DioceseDTO objDto) {
		return new Diocese(objDto.getId(), objDto.getNome());
	}

}
