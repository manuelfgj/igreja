package com.manuelfgj.igreja.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manuelfgj.igreja.entities.Diocese;

@Repository
public interface DioceseRepository extends JpaRepository<Diocese, Integer>{

}
