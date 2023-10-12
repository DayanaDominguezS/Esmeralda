package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> getByNumeroDocIdentidad(String NumeroDocIdentidad);

    @Modifying
    @Transactional
    void deleteByNumeroDocIdentidad(String NumeroDocIdentidad);


}
