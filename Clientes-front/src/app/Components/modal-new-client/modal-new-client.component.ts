import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Cliente } from 'src/app/Models/cliente';
import { ClienteService } from 'src/app/Service/cliente.service';
import { LogService } from 'src/app/Service/log-service.service';
import { ModalService } from 'src/app/Service/modal-service.service';
import { dateRangeValidator } from 'src/app/Validators/date-range.validator';

/**
 * Componente que se presenta al abrir el modal contenido en el AppComponent.
 * Este componente contiene el formulario necesario para registrar un cliente.
 * Este componente contiene el botón que enviará la información al back-end para registrar un cliente.
 * 
 * @author Harold
 */
@Component({
  selector: 'app-modal-new-client',
  templateUrl: './modal-new-client.component.html',
  styleUrls: ['./modal-new-client.component.scss']
})
export class ModalNewClientComponent implements OnInit {
  
  /**
   * Propiedad que define el formulario que recibirá la información del 
   * cliente a registrar.
   */
  newClientForm!: FormGroup;

  /**
   * Constructor del componente.
   * 
   * @param clientService Servicio para interactuar con la API de clientes.
   * @param modalService Servicio usado para manejar los eventos del modal. Se usa para que una vez registrado un nuevo cliente, 
   * se informe al componente principal del evento y poder cerrar el modal.
   * @param logService Servicio usado para generar y enviar los logs del front-end al back-end.
   */
  constructor(private clienteService: ClienteService, private modalService: ModalService, private logService: LogService) { }

  ngOnInit() {

    this.newClientForm = new FormGroup({
      businessId: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+ [a-zA-ZñÑáéíóúÁÉÍÓÚ]+$')]),
      phone: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      startDate: new FormControl('', Validators.required,),
      endDate: new FormControl(''),
    }, { validators: dateRangeValidator() });

  }

  /**
   * Método usado para registrar un nuevo cliente.
   */
  saveNewClient() {
    this.logService.logInfo('Entra al metodo saveNewClient del front-end');
    if (this.newClientForm.valid) {
      const newCliente = this.newClientForm.value as Cliente;
      this.clienteService.saveCliente(newCliente).subscribe((res: Cliente) => {
        if (res) {
          this.newClientForm.reset();
          this.modalService.eventCloseModal();
          this.logService.logInfo('Se crea con exito el nuevo cliente');
        }
      });
    } else { this.logService.logError('No fue posible registrar un nuevo cliente. Verificar la informacion'); }
  }

}