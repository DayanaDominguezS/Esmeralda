package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.NivelEscolaridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface NivelEscolaridadRepository extends JpaRepository<NivelEscolaridad, Long> {

    Optional<NivelEscolaridad> getByNombreNivelEscolaridad(String nombreNivelEscolaridad);

    @Modifying
    @Transactional
    void deleteByNombreNivelEscolaridad(String nombreNivelEscolaridad);
}
