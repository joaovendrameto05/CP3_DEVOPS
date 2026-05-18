public class LancamentoRepository {
    
}
package com.dimdim.api.repository;

import com.dimdim.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // Herdando o JpaRepository, o Spring cria o CRUD do banco automaticamente!
}