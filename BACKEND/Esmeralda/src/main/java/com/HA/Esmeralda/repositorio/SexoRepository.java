package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SexoRepository extends JpaRepository<Sexo, Long> {

    Optional<Sexo> getByNombreSexo(String nombreSexo);

    @Modifying
    @Transactional
    void deleteByNombreSexo(String nombreSexo);

}
