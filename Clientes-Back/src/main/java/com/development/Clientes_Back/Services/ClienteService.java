package com.development.Clientes_Back.Services;

import java.util.Date;
import java.util.List;

import com.development.Clientes_Back.Model.Dtos.ClienteDto;

public interface ClienteService {

    ClienteDto save(ClienteDto clienteDto);

    List<ClienteDto> findAll();

    List<ClienteDto> getBySharedKey(String sharedKey);

    List<ClienteDto> advancedClientsSearch(String businessId, String phone, String email, Date startDate, Date endDate);
}