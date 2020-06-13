package com.manuelfgj.igreja.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelfgj.igreja.entities.Comunidade;

@Repository
public interface ComunidadeRepository extends JpaRepository<Comunidade, Integer>{

}
