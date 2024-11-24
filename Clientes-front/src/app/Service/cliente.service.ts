import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../Models/cliente';
import { Observable } from 'rxjs';
import { environment } from '../Environments/enviroment';
import { AdvancedClientSearch } from '../Models/advancedClientSearch';

/**
 * Servicio que interactúa con el back-end para llevar a cabo todas las 
 * funcionalidades a realizar con los clientes.
 * 
 * @author Harold
 */
@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  /**
   * Constructor de la clase.
   * @param http Parámetro usado para realizar las peticiones al 
   * back-end.
   */
  constructor(private http: HttpClient) { }

  /**
   * Método usado para obtener desde el back-end la lista de todos los 
   * clientes registrados en la base de datos.
   * @returns Observable con la lista de todos los clientes de la base 
   * de datos.
   */
  getAllClientes(): Observable<Cliente[]> {
    const url = `${environment.host}/api/cliente/all`;
    return this.http.get<Cliente[]>(url);
  }

  /**
   * Método usado para obtener desde el back-end la lista de todos los 
   * clientes cuya información de sharedKey coincida con la enviada en
   *  la petición.
   * @param sharedKey Propiedad enviada al back-end para hacer la 
   * consulta.
   * @returns Observable con la lista de todos los clientes encontrados
   *  dada la consulta realizada.
   */
  getClienteBySharedKey(sharedKey: string): Observable<Cliente[]> {
    const url = `${environment.host}/api/cliente/${sharedKey}`;
    return this.http.get<Cliente[]>(url);
  }

  /**
   * Método usado para enviar al back-end el objeto que representa al 
   * cliente que se va a agregar en la base de datos.
   * @param cliente Objeto que representa al cliente.
   * @returns Observable con el objeto que representa al cliente que 
   * fue agregado en la base de datos.
   */
  saveCliente(cliente: Cliente): Observable<Cliente> {
    const url = `${environment.host}/api/cliente/save`;
    return this.http.post(url, cliente);
  }

  /**
   * Método usado para obtener desde el back-end la lista con todos los 
   * clientes cuya información coincida con la información enviada en las
   * propiedades ingresadas.
   * @param advancedClientSearch Objeto que contiene las propiedades a 
   * enviar para la búsqueda.
   * @returns Observable con la lista de los clientes encontrados dada la 
   * consulta.
   */
  advancedClientsSearch(advancedClientSearch: AdvancedClientSearch): Observable<Cliente[]> {
    const params: { [key: string]: string } = {
      businessId: advancedClientSearch.businessId || '',
      phone: advancedClientSearch.phone || '',
      email: advancedClientSearch.email || '',
      startDate: advancedClientSearch.startDate ? new Date(advancedClientSearch.startDate).toISOString().split('T')[0] : '',
      endDate: advancedClientSearch.endDate ? new Date(advancedClientSearch.endDate).toISOString().split('T')[0] : '',
    };
    return this.http.get<Cliente[]>(`${environment.host}/api/cliente/advanced`, { params });
  }  

}