import { Component, OnInit } from '@angular/core';
import { ClienteService } from './Service/cliente.service';
import { Cliente } from './Models/cliente';
import { formatDate } from '@angular/common';
import { CsvExportService } from './Service/csv-export-service.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalNewClientComponent } from './Components/modal-new-client/modal-new-client.component';
import { AdvancedClientSearch } from './Models/advancedClientSearch';
import { ModalService } from './Service/modal-service.service';
import { LogService } from './Service/log-service.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { dateRangeValidator } from './Validators/date-range.validator';
import { conditionalValidator } from './Validators/conditional-validator.validator';

/**
 * Componente principal que maneja la pantalla inicial de clientes.
 * Este componente permite visualizar, buscar y agregar clientes.
 * 
 * @author Harold
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  /**
   * Lista de clientes retornada por alguna funcion del componente.
   * 
   * @type {Cliente[]}
   */
  listaClientes: Cliente[] = [];

  /**
   * Propiedad que recibe el valor de sharedKey para la busqueda del o los clientes por esta propiedad.
   * 
   * @type {string}
   */
  sharedKey: string = '';

  /**
   * Propiedad que define el formulario que recibirá la información del 
   * cliente a buscar.
   */
  advancedClientSearchForm!: FormGroup;

  /**
   * Arreglo fijo que contiene la referencia de las columnas que se presentaran en la tabla de la vista.
   * 
   * @type {string[]}
   */
  displayedColumns: string[] = ['sharedKey', 'businessId', 'email', 'phone', 'dataAdded', 'edit'];

  /**
   * Propiedad boolean que define si se visualiza o no el boton de Advanced Search.
   * 
   * @type {boolean}
   */
  advancedSearch = false;

  /**
   * Constructor del componente.
   * 
   * @param clientService Servicio para interactuar con la API de clientes.
   * @param csvExportService Servicio usado para exportar la informacion de los clientes presentados por pantalla en formato csv.
   * @param dialog Propiedad usada para manejar el modal del formulario para agregar nuevos clientes.
   * @param modalService Servicio usado para manejar los eventos del modal. Se usa para que una vez registrado un nuevo cliente, 
   * el componente con el formulario informe al principal que debe cerrar el modal.
   * @param logService Servicio usado para generar enviar los logs del front-end al back-end.
   */
  constructor(private clienteService: ClienteService, private csvExportService: CsvExportService, private dialog: MatDialog, private modalService: ModalService, private logService: LogService) { }

  /**
   * Método que se ejecuta al inicializar el componente.
   * 
   * Se esta usando para llamar al metodo que obtiene la lista de todos los clientes registrados en la base de datos.
   * 
   * @memberof AppComponent
   */
  ngOnInit() {

    this.advancedClientSearchForm = new FormGroup({
      businessId: new FormControl('', [conditionalValidator(Validators.pattern('^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+ [a-zA-ZñÑáéíóúÁÉÍÓÚ]+$'))]),
      phone: new FormControl(''),
      email: new FormControl('', [conditionalValidator(Validators.email)]),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
    }, { validators: dateRangeValidator() });

    this.getAllClientes();
  }

  /**
   * Método usado para obtener todos los clientes registrados en la base de datos.
   */
  getAllClientes() {
    this.logService.logInfo('Entra al metodo getAllClientes del front-end');
    this.clienteService.getAllClientes().subscribe((res: Cliente[]) => {
      if (res) {
        this.listaClientes = res;
      } else {
        alert("An error occurred while searching for all clients. Please validate the log");
        this.logService.logError('La lista de todos los clientes que llega al front es null');
      }
    })
  }

  /**
   * Método usado para obtener la lista de clientes dada la propiedad sharedKey enviada por pantalla.
   * @param sharedKey Propiedad por la cual se buscará a todos los clientes cuyo valor de sharedKey 
   * coincida con el enviado.
   */
  getClienteBySharedKey(sharedKey: string) {
    this.logService.logInfo('Entra al metodo getClienteBySharedKey del front-end');
    if (sharedKey) {
      this.listaClientes = [];
      this.clienteService.getClienteBySharedKey(sharedKey.trim()).subscribe((res: Cliente[]) => {
        if (res && res.length != 0) {
          this.listaClientes = res;
        } else {
          this.logService.logInfo('La lista de todos los clientes por sharedkey que llega al front es null');
        }
      })
    } else {
      alert("An error occurred while searching for all clients by sharedKey. Please validate the log");
      this.logService.logError('Se debe digitar el valor de sharedKey');
    }
  }

  /**
   * Método que usa el servicio CsvExportService para exportar la información de los clientes presentados 
   * en pantalla en formato csv.
   */
  exportToCSV() {
    this.logService.logInfo('Entra al metodo exportToCSV del front-end');
    if (this.listaClientes.length != 0) {
      const formattedClientes = this.listaClientes.map(cliente => ({
        sharedKey: cliente.sharedKey,
        businessId: cliente.businessId,
        email: cliente.email,
        phone: cliente.phone,
        dataAdded: this.formatDateToDDMMYYYY(cliente.dataAdded),
      }));

      const headers = ['sharedKey', 'businessId', 'email', 'phone', 'dataAdded'];
      this.csvExportService.exportToCSV(formattedClientes, headers, 'Clients Report.csv');
    } else {
      alert("There is no clients information to export");
      this.logService.logInfo('No se exporta archivo porque no hay clientes');
    }
  }

  /**
   * Método usado para convertir la una fecha dada por parámetro al formato dd/MM/YYYY.
   * @param date Fecha enviada por parámetro para ser convertida.
   * @returns Cadena string con la fecha convertida al formato dd/MM/yyyy.
   */
  private formatDateToDDMMYYYY(date: Date | string | undefined): string {
    this.logService.logInfo('Entra al metodo formatDateToDDMMYYYY del front-end');
    if (!date) {
      this.logService.logError(`La fecha enviada para formatear es null: ${date}`);
      alert("Date null");
      return 'N/A';
    } else {
      const parsedDate = typeof date === 'string' ? new Date(date) : date;
      if (isNaN(parsedDate.getTime())) {
        this.logService.logError(`La fecha enviada para formatear no es valida: ${parsedDate.getTime()}`);
        alert("Invalid Date");
        return 'Invalid Date';
      } else {
        this.logService.logInfo(`Formatea correctamente a la fecha: ${formatDate(parsedDate, 'dd/MM/yyyy', 'en-US')}`);
        return formatDate(parsedDate, 'dd/MM/yyyy', 'en-US');
      }
    }
  }

  /**
   * Método usado para cambiar el estado de visibilidad del botón Advanced Search.
   */
  toggleAdvancedSearch() {
    this.advancedSearch = !this.advancedSearch;
  }

  /**
   * Método usado para buscar los clientes cuya información coincida con la o las propiedades enviadas por parámetro.
   */
  advancedClientsSearch() {
    this.logService.logInfo(`Entra al metodo advancedClientsSearch del front-end`);
    if (this.advancedClientSearchForm.valid) {
      const advancedClientSearch = this.advancedClientSearchForm.value as AdvancedClientSearch;
      this.clienteService.advancedClientsSearch(advancedClientSearch).subscribe((res: Cliente[]) => {
        if (res && res.length != 0) {
          this.listaClientes = res;
          this.advancedClientSearchForm.reset();
          this.advancedSearch = false;
        } else {
          this.logService.logInfo(`El metodo advancedClientsSearch devuelve una lista vacia`);
        }
      });
    } else {
      console.log("entra else")
      this.logService.logError(`No se envia ninguna propiedad para el metodo advancedClientsSearch`);
      alert("At least one property must be entered for the search");
    }
    this.advancedSearch = false;
  }

  /**
   * Método que controla la apertura o cierre del modal con el formulario para agregar nuevos clientes.
   */
  openNewClientModal() {
    const dialogRef = this.dialog.open(ModalNewClientComponent);
    this.modalService.closeModal$.subscribe(() => {
      dialogRef.close();
      this.getAllClientes();
    });
  }

}