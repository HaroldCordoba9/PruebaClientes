import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { HttpClient } from '@angular/common/http';
import { environment } from '../Environments/enviroment';

/**
 * Servicio que interactúa con el back-end para guardar los logs enviados desde el 
 * front-end. 
 * 
 * @author Harold
 */
@Injectable({
  providedIn: 'root'
})
export class LogService {

  /**
   * Constructor de la clase.
   * @param logger Parámetro que representa a la clase NGXLogger de la librería 
   * ngx-logger. Se está usando para definir el nivel de logging del mensaje que se genera.
   * @param http Parámetro usado para realizar las peticiones al 
   * back-end.
   */
  constructor(private logger: NGXLogger, private http: HttpClient) {}

  /**
   * Método usado para el envío de los logs al back-end.
   * @param message Mensaje o log a enviar al back-end.
   */
  sendLog(message: string) {
    this.http.post(`${environment.host}/api/logs`, message).subscribe(
      () => this.logger.info('Log enviado al back Clientes'),
      (error) => this.logger.error('Error al enviar log al back Clientes', error)
    );
  }

  /**
   * Método que genera un log de tipo info y lo envía al back end.
   * @param message Mensaje a catalogar como tipo info que se enviará al back-end.
   */
  logInfo(message: string) {
    this.logger.info(message);
    this.sendLog(message);
  }

  /**
   * Método que genera un log de tipo error y lo envía al back end.
   * @param message Mensaje a catalogar como tipo error que se enviará al back-end.
   */
  logError(message: string) {
    this.logger.error(message);
    this.sendLog(message);
  }

  /**
   * Método que genera un log de tipo debbug y lo envía al back end.
   * @param message Mensaje a catalogar como tipo debbug que se enviará al back-end.
   */
  logDebug(message: string) {
    this.logger.debug(message);
    this.sendLog(message);
  }

}