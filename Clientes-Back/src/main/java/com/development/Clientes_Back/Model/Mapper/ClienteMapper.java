package com.development.Clientes_Back.Model.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.development.Clientes_Back.Model.Dtos.ClienteDto;
import com.development.Clientes_Back.Model.Entities.Cliente;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDto mapToDto(Cliente entity) {
        if (entity == null)
            return null;
        return modelMapper.map(entity, ClienteDto.class);
    }

    public ClienteDto mapListToDto(List<Cliente> entity) {
        if (entity == null)
            return null;
        return modelMapper.map(entity, ClienteDto.class);
    }

    public List<ClienteDto> listClienteDto(List<Cliente> clientes) {
        List<ClienteDto> listClienteDtos = new ArrayList<>();
        clientes.forEach(cliente -> {
            listClienteDtos.add(mapToDto(cliente));
        });
        return listClienteDtos;
    }

    public Cliente mapToEntity(ClienteDto Dto) {
        if (Dto == null)
            return null;
        return modelMapper.map(Dto, Cliente.class);
    }

}