package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.TipoDocIdentidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TipoDocIdentidadRepository extends JpaRepository<TipoDocIdentidad, Long> {

    Optional<TipoDocIdentidad> getByNombreTipoDocIdentidad(String nombreTipoDocIdentidad);

    @Modifying
    @Transactional
    void deleteByNombreTipoDocIdentidad(String nombreTipoDocIdentidad);

}
