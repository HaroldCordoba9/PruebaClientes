package com.development.Clientes_Back.Model.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la tabla cliente de la base de datos.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /**
     * Atributo que define la llave primaria del registro en la tabla cliente de la
     * base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * Atributo que define la llave del cliente.
     * Se conforma por la primera letra del nombre y el apellido del cliente.
     */
    @Column(name = "sharedKey")
    private String sharedKey;

    /**
     * Atributo que define el nombre y apellido del cliente.
     */
    @Column(name = "businessId")
    private String businessId;

    /**
     * Atributo que define el email o correo del cliente.
     */
    @Column(name = "email")
    private String email;

    /**
     * Atributo que define el teléfono del cliente.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Atributo que define la fecha en la cual se insertó el registro del cliente.
     */
    @Column(name = "dataAdded")
    private Date dataAdded;

    /**
     * Atributo que define la fecha de vinculación del cliente.
     */
    @Column(name = "startDate")
    private Date startDate;

    /**
     * Atributo que define la fecha de retiro del cliente.
     */
    @Column(name = "endDate")
    private Date endDate;

    public Cliente(){}

    //PRUEBAS
    public Cliente(Long id, String sharedKey, String businessId, String email, String phone, Date dataAdded, Date startDate, Date endDate) {
        this.id = id;
        this.sharedKey = sharedKey;
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.dataAdded = dataAdded;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", sharedKey='" + sharedKey + "', businessId='" + businessId + "', email='" + email + "', phone='" + phone + "', dataAdded='" + dataAdded + "'}";
    }
}