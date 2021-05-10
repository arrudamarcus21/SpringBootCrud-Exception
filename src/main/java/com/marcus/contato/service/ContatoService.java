package com.marcus.contato.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcus.contato.exception.ObjetoNaoEncontrado;
import com.marcus.contato.model.Contato;
import com.marcus.contato.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;

	public Contato salvar(Contato contato) {
		return contatoRepository.save(contato);
	}

	public Contato buscarId(Long id) {
		verificarContatoPorId(id);
		return contatoRepository.findById(id).get();
	}
	
	private boolean verificarContatoPorId(Long id) {
		Optional<Contato> ncontato = contatoRepository.findById(id);
		ncontato.orElseThrow(() -> new ObjetoNaoEncontrado("Contato Não encontrado!!!!!!!!!"));
		return true;
	}

	public void deletar(Long id) {
		verificarContatoPorId(id);
		contatoRepository.deleteById(id);
		
	}

	public void editar(Contato contato, Long id) {
//		System.out.println("-------caiu aqui--------");
//		contato.setId(id);
		verificarContatoPorId(id);
		contatoRepository.save(contato);
		
	}

	public List<Contato> buscarTodos() {
		List<Contato> ncontato =  contatoRepository.findAll();
		if(ncontato.size() == 0) {
			throw new ObjetoNaoEncontrado("Lista Vazia!!");
		}
		
		return ncontato;
	}
	
	
}
