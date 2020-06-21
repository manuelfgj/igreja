package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manuelfgj.igreja.dto.DoacaoDTO;
import com.manuelfgj.igreja.entities.Doacao;
import com.manuelfgj.igreja.entities.repositories.DoacaoRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class DoacaoService {
	
	@Autowired
	DoacaoRepository repo;

	public Doacao find(Integer id) {
		Optional<Doacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Doacao.class.getName()));
	}
	
	public Doacao insert(Doacao obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Doacao update(Doacao obj) {
		Doacao newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}	
	
	private void update(Doacao newObj, Doacao obj) {
		newObj.setValor(obj.getValor());
		newObj.setData(obj.getData());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma diocese que contem entidades relacionadas");
		}
	}
	
	public List<Doacao> findAll(){
		return repo.findAll();
	}
	
	public Page<Doacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Doacao fromDTO(DoacaoDTO objDto) {
		return new Doacao(objDto.getId(), objDto.getValor(), objDto.getData(), null, null, null, null);
	}

}
