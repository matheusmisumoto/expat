package br.com.expat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.expat.model.Custo;

@Repository
public interface CustoRepository extends JpaRepository<Custo, Long> {

}
