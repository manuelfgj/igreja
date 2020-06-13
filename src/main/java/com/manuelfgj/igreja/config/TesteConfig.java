package com.manuelfgj.igreja.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.manuelfgj.igreja.entities.Comunidade;
import com.manuelfgj.igreja.entities.Diocese;
import com.manuelfgj.igreja.entities.Grupo;
import com.manuelfgj.igreja.entities.Paroquia;
import com.manuelfgj.igreja.entities.repositories.ComunidadeRepository;
import com.manuelfgj.igreja.entities.repositories.DioceseRepository;
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
		
		dcs1.getParoquias().addAll(Arrays.asList(prq1, prq2));
		dioceseRepository.saveAll(Arrays.asList(dcs1));
		prq1.getComunidades().addAll(Arrays.asList(cmnd1, cmnd1, cmnd3));
		paroquiaRepository.saveAll(Arrays.asList(prq1));
		cmnd1.getGrupos().addAll(Arrays.asList(grp1, grp2, grp3));
		cmnd1.getGrupos().addAll(Arrays.asList(grp4, grp5));
		comunidadeRepository.saveAll(Arrays.asList(cmnd1, cmnd2));

	}
	
	
}
