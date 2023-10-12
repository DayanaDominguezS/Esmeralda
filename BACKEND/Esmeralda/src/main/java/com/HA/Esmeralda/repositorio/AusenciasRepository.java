package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.Ausencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AusenciasRepository extends JpaRepository<Ausencias, Long>{

    Optional<Ausencias> getByDocId(String docId);


}
