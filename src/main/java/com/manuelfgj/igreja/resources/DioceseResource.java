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

import com.manuelfgj.igreja.dto.DioceseDTO;
import com.manuelfgj.igreja.entities.Diocese;
import com.manuelfgj.igreja.services.DioceseService;

@RestController
@RequestMapping(value="/dioceses/")
public class DioceseResource {

	@Autowired
	private DioceseService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Diocese> find(@PathVariable Integer id) {
		Diocese obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DioceseDTO objDto){
		Diocese obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DioceseDTO objDto, @PathVariable Integer id) {
		Diocese obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<DioceseDTO>> findAll() {
		List<Diocese> list = service.findAll();
		List<DioceseDTO> listDto = list.stream().map(obj -> new DioceseDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}	
}
