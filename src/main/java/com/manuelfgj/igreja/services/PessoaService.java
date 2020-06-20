package com.manuelfgj.igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuelfgj.igreja.dto.PessoaDTO;
import com.manuelfgj.igreja.dto.PessoaNewDTO;
import com.manuelfgj.igreja.entities.Cidade;
import com.manuelfgj.igreja.entities.Comunidade;
import com.manuelfgj.igreja.entities.Endereco;
import com.manuelfgj.igreja.entities.Pessoa;
import com.manuelfgj.igreja.entities.enuns.EstadoCivil;
import com.manuelfgj.igreja.entities.enuns.Sexo;
import com.manuelfgj.igreja.entities.repositories.EnderecoRepository;
import com.manuelfgj.igreja.entities.repositories.PessoaRepository;
import com.manuelfgj.igreja.services.exceptions.DataIntegrityException;
import com.manuelfgj.igreja.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ComunidadeService comunidadeService;

	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.save(obj.getEndereco());
		return obj;
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
	
	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa pes = new Pessoa(null, objDto.getNome(), objDto.getCpf(), objDto.getCelular(),  objDto.getEmail(), objDto.getDataNasc(), Sexo.toEnum(objDto.getSexo()), EstadoCivil.toEnum(objDto.getEstadoCivil()));
		Cidade cid = new Cidade(objDto.getCidadeId(),null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getBairro(), objDto.getComplemento(), objDto.getCep(), cid);
		pes.setEndereco(end);
		Comunidade com = comunidadeService.find(objDto.getComunidadeId());		
		pes.setComunidade(com);
		
		//TODO Fazer validacao: Para garantir que a pessoa é única tem que ter pelo menos o cpf, celular ou email. 
		//TODO Para ser usuario do sistema tem que ter pelo menos celular ou email.
		//TODO No momento estes não estão obrigatorios
		
		return pes;
	}

}
