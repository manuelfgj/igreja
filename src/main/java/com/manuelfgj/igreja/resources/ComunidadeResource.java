package com.manuelfgj.igreja.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manuelfgj.igreja.dto.ComunidadeDTO;
import com.manuelfgj.igreja.entities.Comunidade;
import com.manuelfgj.igreja.services.ComunidadeService;

@RestController
@RequestMapping(value="/comunidades/")
public class ComunidadeResource {

	@Autowired
	private ComunidadeService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comunidade> find(@PathVariable Integer id) {
		Comunidade obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ComunidadeDTO objDto){
		Comunidade obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ComunidadeDTO objDto, @PathVariable Integer id) {
		Comunidade obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ComunidadeDTO>> findAll() {
		List<Comunidade> list = service.findAll();
		List<ComunidadeDTO> listDto = list.stream().map(obj -> new ComunidadeDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}	
}
