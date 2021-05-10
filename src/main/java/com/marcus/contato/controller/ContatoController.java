package com.marcus.contato.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcus.contato.model.Contato;
import com.marcus.contato.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	ContatoService contatoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Contato> save(@RequestBody Contato contato){
		Contato ncontato = contatoService.salvar(contato);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(ncontato.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
				
				
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Contato> buscarId(@PathVariable Long id) {
		
		Contato ncontato = contatoService.buscarId(id);
		
		return ResponseEntity.ok(ncontato);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Contato> deletar(@PathVariable Long id){
		contatoService.deletar(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Contato> edit(@RequestBody Contato contato,@PathVariable Long id){
		contatoService.editar(contato, id);
		
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/buscar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Contato>> buscarTodos(){
		List<Contato> ncontato = 	contatoService.buscarTodos();
		
		return ResponseEntity.ok(ncontato);
	}
}
