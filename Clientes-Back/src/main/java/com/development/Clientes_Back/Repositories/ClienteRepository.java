package com.development.Clientes_Back.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.development.Clientes_Back.Model.Entities.Cliente;

/**
 * Interface para realizar operaciones CRUD sobre la entidad cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Método usado para buscar los clientes cuyo sharedKey coincida con el
     * ingresado.
     * 
     * @param sharedKey Propiedad sharedKey a buscar.
     * @return Lista de clientes cuyo sharedKey coincida con el ingresado por
     *         parámetro.
     */
    List<Cliente> findBysharedKey(String sharedKey);

    /**
     * Método usado para buscar clientes dadas las propiedades ingresadas por
     * parámetro donde al menos el valor de una de las propiedades ingresadas se
     * encuentre en base de datos.
     * 
     * @param businessId Propiedad businessId de el/los cliente(s) a buscar en base de datos.
     * @param phone Propiedad teléfono de el/los cliente(s) a buscar en base de datos.
     * @param email Propiedad email o correo de el/los cliente(s) a buscar en base de datos.
     * @param startDate Propiedad fecha inicial de el/los cliente(s) a buscar en base de datos.
     * @param endDate Propiedad fecha final de el/los cliente(s) a buscar en base de datos.
     * @return Lista de clientes que correspondan con las propiedades ingresadas.
     */
    @Query("SELECT c FROM Cliente c WHERE c.businessId = :businessId OR c.phone = :phone OR c.email = :email OR c.startDate = :startDate OR c.endDate = :endDate")
    List<Cliente> advancedClientsSearch(@Param("businessId") String businessId, @Param("phone") String phone,
            @Param("email") String email, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}