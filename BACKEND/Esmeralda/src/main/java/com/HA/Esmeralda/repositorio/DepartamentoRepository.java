package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> getByNombreDepartamento(String nombreDepartamento);

    @Modifying
    @Transactional
    void deleteByNombreDepartamento(String nombreDepartamento);

}
