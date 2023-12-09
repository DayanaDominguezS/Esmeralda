package com.HA.Esmeralda.repositorio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.NivelEscolaridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> getByNumeroDocIdentidad(String NumeroDocIdentidad);

    @Modifying
    @Transactional
    void deleteByNumeroDocIdentidad(String NumeroDocIdentidad);

    @Modifying
    @Transactional
    @Query("UPDATE Empleado e SET e.contrasena = :nuevaContrasena WHERE e.numeroDocIdentidad = :numeroDocIdentidad")
    void actualizarContrasena(String numeroDocIdentidad, String nuevaContrasena);

    @Modifying
    @Transactional
    @Query("UPDATE Empleado e SET e.numeroCelular = :nuevoNumeroCelular WHERE e.numeroDocIdentidad = :numeroDocIdentidad")
    void actualizarNumeroCelular(String numeroDocIdentidad, String nuevoNumeroCelular);

    @Modifying
    @Transactional
    @Query("UPDATE Empleado e SET e.nivelEscolaridad = :nuevoNivelEscolaridad WHERE e.numeroDocIdentidad = :numeroDocIdentidad")
    void actualizarNivelEscolaridad(String numeroDocIdentidad, NivelEscolaridad nuevoNivelEscolaridad);

    @Modifying
    @Transactional
    @Query("UPDATE Empleado e SET e.activo = :nuevoActivo WHERE e.numeroDocIdentidad = :numeroDocIdentidad")
    void actualizarActivo(String numeroDocIdentidad, Boolean nuevoActivo);


    // Puedes agregar más métodos según sea necesario para otros atributos.


}
