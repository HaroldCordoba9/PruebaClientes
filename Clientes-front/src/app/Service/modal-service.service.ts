import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

/**
 * Servicio usado para el manejo de modal.
 * 
 * @author Harold
 */
@Injectable({
  providedIn: 'root'
})
export class ModalService {

  /**
   * Propiedad usada para emitir un evento. En este caso se usa 
   * para informar un evento de cierre de modal.
   */
  private closeModalSubject = new Subject<void>();
  closeModal$ = this.closeModalSubject.asObservable();

  /**
   * Método usado para informar que el modal debe cerrarse.
   */
  eventCloseModal() {
    this.closeModalSubject.next();
  }
}
