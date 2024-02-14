package com.JuliaFarias.PrjControleAlunos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JuliaFarias.PrjControleAlunos.entities.Alunos;

public interface AlunosRepository extends JpaRepository<Alunos, Long >{

}