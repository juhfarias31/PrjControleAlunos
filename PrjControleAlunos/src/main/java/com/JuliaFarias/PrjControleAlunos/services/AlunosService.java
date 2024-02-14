package com.JuliaFarias.PrjControleAlunos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JuliaFarias.PrjControleAlunos.entities.Alunos;
import com.JuliaFarias.PrjControleAlunos.repositories.AlunosRepository;

@Service
public class AlunosService {
	private final AlunosRepository alunosRepository;
	
	@Autowired
	public AlunosService(AlunosRepository alunosRepository) {
		this.alunosRepository = alunosRepository;
	}
	
	public Alunos saveAlunos(Alunos alunos) {
		return alunosRepository.save(alunos);
	}
	
	public Alunos getAlunosById(Long id) {
		return alunosRepository.findById(id).orElse(null);
	}
	
	public Alunos getProdutoById(long id) {
		return alunosRepository.findById(id).orElse(null);
	}
	
	public List<Alunos> getAllAlunos(){
		return alunosRepository.findAll();
	}
	
	public void deletAlunos(Long id) {
		alunosRepository.deleteById(id);
	}
	
	// fazendo o update do jogo com o optional
	
	public Alunos updateAlunos(Long id, Alunos novoAlunos) {
		Optional<Alunos> alunosOptional = alunosRepository.findById(id);
		
	    if (alunosOptional.isPresent()) {
	    	Alunos alunosExistente = alunosOptional.get();
	        alunosExistente.setName(novoAlunos.getName());
	        alunosExistente.setCpf(novoAlunos.getCpf());
	        alunosExistente.setRg(novoAlunos.getRg());
	        alunosExistente.setEndereco(novoAlunos.getEndereco());              
	        return alunosRepository.save(alunosExistente); 
	    } else {
	        return null; 
	    }
	}

}
