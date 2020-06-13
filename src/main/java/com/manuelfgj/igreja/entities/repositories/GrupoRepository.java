package com.manuelfgj.igreja.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelfgj.igreja.entities.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

}
