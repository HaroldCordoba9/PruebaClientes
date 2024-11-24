package com.development.Clientes_Back.Services.Impl;

import java.text.Normalizer;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.development.Clientes_Back.Controllers.ClienteController;
import com.development.Clientes_Back.Model.Dtos.ClienteDto;
import com.development.Clientes_Back.Model.Entities.Cliente;
import com.development.Clientes_Back.Model.Mapper.ClienteMapper;
import com.development.Clientes_Back.Repositories.ClienteRepository;
import com.development.Clientes_Back.Services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    /**
     * Constante usada para definir la propiedad logger que se usará en distintos
     * prints de la clase.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    /**
     * Constante usada para definir la propiedad que hace referencia al repository
     * de la tabla cliente de la base de datos.
     */
    private final ClienteRepository clienteRepository;

    /**
     * Constante usada para definir la propiedad que hace referencia al mapper para
     * las clases ClienteDto y Cliente (entity).
     */
    private final ClienteMapper clienteMapper;

    /**
     * Método constructor de la clase.
     * 
     * @param clienteRepositoryP Parámetro de tipo ClienteRepository usada para
     *                           inicializar el atributo de la clase
     *                           ClienteRepository.
     * @param clienteMapperP     Parámetro de tipo ClienteMapper usada para
     *                           inicializar el atributo de la clase ClienteMapper.
     */
    public ClienteServiceImpl(final ClienteRepository clienteRepositoryP, final ClienteMapper clienteMapperP) {
        this.clienteRepository = clienteRepositoryP;
        this.clienteMapper = clienteMapperP;
    }

    /**
     * Método que retorna la lista de todos los clientes encontrados en la base de
     * datos.
     */
    public List<ClienteDto> findAll() {
        logger.info("Entra al metodo findAll del servicio");
        List<Cliente> clientes = clienteRepository.findAll();
        logger.info("La cantidad de objetos en la lista a retornar es: " + clientes.size());
        return (List<ClienteDto>) clienteMapper.listClienteDto(clientes);
    }

    /**
     * Método que guarda un nuevo cliente en la base de datos.
     * La propiedad dataAdded se obtiene de manera automática del sistema.
     */
    public ClienteDto save(ClienteDto clienteDto) {
        logger.info("Entra al metodo save del servicio");
        String businessIdSinTildes = Normalizer.normalize(clienteDto.getBusinessId(), Normalizer.Form.NFD);
        businessIdSinTildes = businessIdSinTildes.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
        String[] partes = businessIdSinTildes.split(" ");
        clienteDto.setSharedKey(partes[0].substring(0, 1) + partes[1]);
        clienteDto.setDataAdded(new Date());
        logger.info("El valor de dataAdded es: " + clienteDto.getDataAdded());
        Cliente cliente = clienteMapper.mapToEntity(clienteDto);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.mapToDto(savedCliente);
    }

    /**
     * Método usado para buscar los clientes cuyo sharedKey coincida con el
     * ingresado.
     * Llama al interface ClienteRepository para usar la consulta a base de datos.
     * 
     * @param sharedKey Propiedad sharedKey a buscar.
     * @return Lista de clientes cuyo sharedKey coincida con el ingresado por
     *         parámetro.
     */
    public List<ClienteDto> getBySharedKey(String sharedKey) {
        logger.info("Entra al metodo getBySharedKey del servicio");
        List<Cliente> entity = clienteRepository.findBysharedKey(sharedKey);
        List<ClienteDto> clienteDto = clienteMapper.listClienteDto(entity);
        logger.info("La cantidad de clientes a retornar es: " + clienteDto.size());
        return (clienteDto);
    }

    /**
     * Método usado para buscar clientes dadas las propiedades ingresadas por
     * parámetro donde al menos el valor de una de las propiedades ingresadas se
     * encuentre en base de datos.
     * Llama al interface ClienteRepository para usar la consulta a base de datos.
     * 
     * @param businessId Propiedad businessId de el/los cliente(s) a buscar en base de datos.
     * @param phone Propiedad teléfono de el/los cliente(s) a buscar en base de datos.
     * @param email Propiedad email o correo de el/los cliente(s) a buscar en base de datos.
     * @param startDate Propiedad fecha inicial de el/los cliente(s) a buscar en base de datos.
     * @param endDate Propiedad fecha final de el/los cliente(s) a buscar en base de datos.
     * @return Lista de clientes que correspondan con la/las propiedad(es) ingresada(s).
     */
    public List<ClienteDto> advancedClientsSearch(String businessId, String phone, String email, Date startDate,Date endDate) {
        logger.info("Entra al metodo advancedClientsSearch del servicio");
        List<Cliente> clientes = clienteRepository.advancedClientsSearch(businessId, phone, email, startDate, endDate);
        logger.info("La cantidad de clientes a retornar es: " + clientes.size());
        return (List<ClienteDto>) clienteMapper.listClienteDto(clientes);
    }

}