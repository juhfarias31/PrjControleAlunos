package com.JuliaFarias.PrjControleAlunos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.JuliaFarias.PrjControleAlunos.entities.Alunos;
import com.JuliaFarias.PrjControleAlunos.services.AlunosService;

@RestController
@RequestMapping("/alunos")
public class AlunosController {
	private final AlunosService alunosService;

	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}

	@Autowired
	public AlunosController(AlunosService alunosService) {
		this.alunosService = alunosService;
	}
	
	@PostMapping
	public Alunos createAlunos(@RequestBody Alunos alunos) {
		return alunosService.saveAlunos(alunos);
	}
	
	//Utilizando o ResponseEntity e RequestEntit
	@GetMapping
	public ResponseEntity<List<Alunos>> getAllAlunos(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Alunos> alunos = alunosService.getAllAlunos();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(alunos);
	}
	
	@PutMapping("/{id}")
	public Alunos updateAlunos(@PathVariable Long id, @RequestBody Alunos alunos) {
		return alunosService.updateAlunos(id, alunos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Alunos> getAlunos(@PathVariable Long id) {
		Alunos alunos = alunosService.getAlunosById(id);
		if (alunos != null) {
			return ResponseEntity.ok(alunos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public void deleteAlunos(@PathVariable Long id) {
		alunosService.deletAlunos(id);
	}
}
