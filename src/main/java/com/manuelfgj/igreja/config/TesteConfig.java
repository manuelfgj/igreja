package com.manuelfgj.igreja.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.manuelfgj.igreja.entities.Cidade;
import com.manuelfgj.igreja.entities.Comunidade;
import com.manuelfgj.igreja.entities.Diocese;
import com.manuelfgj.igreja.entities.Endereco;
import com.manuelfgj.igreja.entities.Estado;
import com.manuelfgj.igreja.entities.Grupo;
import com.manuelfgj.igreja.entities.Paroquia;
import com.manuelfgj.igreja.entities.repositories.CidadeRepository;
import com.manuelfgj.igreja.entities.repositories.ComunidadeRepository;
import com.manuelfgj.igreja.entities.repositories.DioceseRepository;
import com.manuelfgj.igreja.entities.repositories.EnderecoRepository;
import com.manuelfgj.igreja.entities.repositories.EstadoRepository;
import com.manuelfgj.igreja.entities.repositories.GrupoRepository;
import com.manuelfgj.igreja.entities.repositories.ParoquiaRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private DioceseRepository dioceseRepository;
	
	@Autowired
	private ParoquiaRepository paroquiaRepository;
	
	@Autowired
	private ComunidadeRepository comunidadeRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Diocese dcs1 = new Diocese(null, "Arquidiocese de Natal");	
		dioceseRepository.saveAll(Arrays.asList(dcs1));
		
		Paroquia prq1 = new Paroquia(null, "Paróquia da Cristo Rei", "60838092000105", "Cristo Rei", dcs1);
		Paroquia prq2 = new Paroquia(null, "Paróquia da Santa Maria", "30773837000134", "Santa Maria", dcs1);		
		paroquiaRepository.saveAll(Arrays.asList(prq1, prq2));				
		
		Comunidade cmnd1 = new Comunidade(null, "Capela São Pedro", prq1);
		Comunidade cmnd2 = new Comunidade(null, "Capela São João", prq1);
		Comunidade cmnd3 = new Comunidade(null, "Capela São Lucas", prq1);		
		comunidadeRepository.saveAll(Arrays.asList(cmnd1, cmnd2, cmnd3));
		
		Grupo grp1 = new Grupo(null, "Pastoral da Catequese", cmnd1);
		Grupo grp2 = new Grupo(null, "Pastoral do Dízimo", cmnd1);
		Grupo grp3 = new Grupo(null, "Pastoral da Família", cmnd1);
		Grupo grp4 = new Grupo(null, "Pastoral da Juventude", cmnd2);
		Grupo grp5 = new Grupo(null, "Legião de Maria", cmnd2);		
		grupoRepository.saveAll(Arrays.asList(grp1, grp2, grp3, grp4, grp5));
		
		Estado estd1 = new Estado(null, "RN");		
		estadoRepository.saveAll(Arrays.asList(estd1));
		
		Cidade cdd1 = new Cidade(null, "Natal", estd1);		
		cidadeRepository.saveAll(Arrays.asList(cdd1));
		
		Endereco endrc1 = new Endereco(null, "rua Azul", "100", "Centro", null, "59020100", cdd1);
		Endereco endrc2 = new Endereco(null, "rua Verde", "200", "Norte", null, "59020200", cdd1);
		Endereco endrc3 = new Endereco(null, "rua Vermelha", "300", "Sul", null, "59020300", cdd1);	
		Endereco endrc4 = new Endereco(null, "rua Amarela", "400", "Leste", null, "59020400", cdd1);	
		Endereco endrc5 = new Endereco(null, "rua Branca", "500", "Oeste", null, "59020500", cdd1);	
		enderecoRepository.saveAll(Arrays.asList(endrc1, endrc2, endrc3));
		
		dcs1.getParoquias().addAll(Arrays.asList(prq1, prq2));
		dcs1.setEndereco(endrc1);
		dioceseRepository.saveAll(Arrays.asList(dcs1));
		prq1.getComunidades().addAll(Arrays.asList(cmnd1, cmnd1, cmnd3));
		prq1.setEndereco(endrc2);
		paroquiaRepository.saveAll(Arrays.asList(prq1));
		cmnd1.getGrupos().addAll(Arrays.asList(grp1, grp2, grp3));		
		cmnd1.getGrupos().addAll(Arrays.asList(grp4, grp5));
		cmnd1.setEndereco(endrc3);
		cmnd2.setEndereco(endrc4);
		cmnd3.setEndereco(endrc5);
		comunidadeRepository.saveAll(Arrays.asList(cmnd1, cmnd2, cmnd3));

	}
	
	
}
