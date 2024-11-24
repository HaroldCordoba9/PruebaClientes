package com.development.Clientes_Back.Model.Dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Clase que representa la entidad cliente.
 */
@Data
public class ClienteDto {

    /**
     * Atributo que define la llave primaria de la tabla cliente.
     */
    @JsonIgnore
    private Long id;

    /**
     * Atributo que define la llave del cliente.
     * Se conforma por la primera letra del nombre y el apellido del cliente.
     */
    private String sharedKey;

    /**
     * Atributo que define el nombre y apellido del cliente.
     */
    private String businessId;

    /**
     * Atributo que define el email o correo del cliente.
     */
    private String email;

    /**
     * Atributo que define el teléfono del cliente.
     */
    private String phone;

    /**
     * Atributo que define la fecha en la cual se insertó el registro del cliente.
     */
    private Date dataAdded;

    /**
     * Atributo que define la fecha de vinculación del cliente.
     */
    private Date startDate;

    /**
     * Atributo que define la fecha de retiro del cliente.
     */
    private Date endDate;

}