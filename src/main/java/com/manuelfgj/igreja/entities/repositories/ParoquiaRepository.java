package com.manuelfgj.igreja.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelfgj.igreja.entities.Paroquia;

@Repository
public interface ParoquiaRepository extends JpaRepository<Paroquia, Integer>{

}
