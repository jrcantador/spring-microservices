package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Cambio;

public interface CambioRepository  extends JpaRepository<Cambio, Long>{

	Cambio findByFromAndTo(String from, String to);
}
