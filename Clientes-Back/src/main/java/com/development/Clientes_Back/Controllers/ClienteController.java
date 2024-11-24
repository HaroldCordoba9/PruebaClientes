package com.development.Clientes_Back.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.development.Clientes_Back.Model.Dtos.ClienteDto;
import com.development.Clientes_Back.Services.ClienteService;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    /**
     * Constante usada para definir la propiedad logger que se usará en distintos
     * prints de la clase.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    /**
     * Constante usada para definir la propiedad que hace referencia a la interface
     * ClienteService.
     */
    private final ClienteService clienteService;

    /**
     * Constructor de la clase.
     * 
     * @param clienteServiceP Parámetro de tipo ClienteService usado para
     *                        inicializar la constante de tipo ClienteService.
     */
    public ClienteController(final ClienteService clienteServiceP) {
        this.clienteService = clienteServiceP;
    }

    /**
     * Método usado para llamar al servicio save de la clase ClienteService para
     * guardar un nuevo cliente en la base de datos. Se usa como endpoint /save.
     * 
     * @param dato Objeto tipo ClienteDto que llega en la petición desde el front.
     * @return Objeto cliente Dto del cliente que fue guardado en base de datos.
     */
    @PostMapping("/save")
    public ResponseEntity<ClienteDto> saveCliente(@RequestBody ClienteDto dato) {
        logger.info("Entra al metodo saveCliente");
        clienteService.save(dato);
        return ResponseEntity.ok(dato);
    }

    /**
     * Método usado para llamar al servicio findAll de la clase ClienteService para
     * obtener todos los clientes almacenados en la base de datos. Se usa como
     * endpoint /all.
     * 
     * @return Lista de todos los clientes almacenados en la base de datos.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        logger.info("Entra al metodo get all Clientes");
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    /**
     * Método usado para llamar al servicio getSharedByKey de la clase
     * ClienteService para obtener todos los clientes cuya propiedad sharedKey
     * coincida con la ingresada por parámetro. Se usa como endpoint /{sharedKey}
     * que contiene el valor enviado desde el front.
     * 
     * @param sharedKey Propiedad del cliente que se usará para buscar todos los
     *                  clientes cuyo valor de propiedad sharedKey coincida con el
     *                  enviado.
     * @return Lista de clientes encontrados por la consulta a base de datos.
     */
    @GetMapping("/{sharedKey}")
    public ResponseEntity<List<ClienteDto>> getClienteBySharedKey(@PathVariable String sharedKey) {
        logger.info("Entra al metodo getClienteBySharedKey");
        try {
            logger.info("Entra al try del metodo getClienteBySharedKey");
            return ResponseEntity.ok().body(clienteService.getBySharedKey(sharedKey));
        } catch (Exception e) {
            logger.error("Entra al catch del metodo getClienteBySharedKey");
            logger.error(e.getMessage());
            return new ResponseEntity<List<ClienteDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método usado para los clientes cuyas propiedades businessId, phone, email,
     * startDate, endDate coincidan con las enviadas desde pantalla (donde al menos
     * una coincida). Se usa como endpoint /advanced.
     * 
     * @param businessId Propiedad businessId de el/los cliente(s) a buscar en base de datos.
     * @param phone Propiedad teléfono de el/los cliente(s) a buscar en base de datos.
     * @param email Propiedad email o correo de el/los cliente(s) a buscar en base de datos.
     * @param startDate Propiedad fecha inicial de el/los cliente(s) a buscar en base de datos.
     * @param endDate Propiedad fecha final de el/los cliente(s) a buscar en base de datos.
     * @return Lista de clientes que correspondan con la/las propiedad(es) ingresada(s).
     */
    @GetMapping("/advanced")
    public ResponseEntity<List<ClienteDto>> advancedClientsSearch(@RequestParam("businessId") String businessId,
            @RequestParam("phone") String phone, @RequestParam("email") String email,
            @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        logger.info("Entra al metodo advancedClientsSearch");
        try {
            logger.info("Entra al try del metodo advancedClientsSearch");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date clienteStartDate = startDate != "" ? new Date(sdf.parse(startDate).getTime()) : new Date();
            Date clienteEndDate = endDate != "" ? new Date(sdf.parse(endDate).getTime()) : new Date();
            List<ClienteDto> clienteDtos = clienteService.advancedClientsSearch(businessId, phone, email,
                    clienteStartDate, clienteEndDate);
            return ResponseEntity.ok(clienteDtos);
        } catch (ParseException e) {
            logger.error("Entra al catch del metodo advancedClientsSearch");
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}