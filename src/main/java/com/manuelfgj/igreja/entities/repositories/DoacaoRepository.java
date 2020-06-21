package com.manuelfgj.igreja.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelfgj.igreja.entities.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer>{

}
