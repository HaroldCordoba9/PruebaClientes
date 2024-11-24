import { Injectable } from '@angular/core';

/**
 * Servicio usado para generar un archivo en formato csv.
 * 
 * @author Harold
 */
@Injectable({
  providedIn: 'root',
})
export class CsvExportService {

  /**
   * Método usado para generar un archivo en formato csv partiendo
   * de información recibida por parámetro.
   * @param data Arreglo con el contenido que se tendrá en el archivo.
   * @param headers El encabezado que tendrá el contenido del archivo.
   * @param fileName Nombre con el que se descargará el archivo.
   */
  exportToCSV(data: any[], headers: string[], fileName: string): void {
    const csvRows = [];

    csvRows.push(headers.join(';'));

    for (const row of data) {
      const values = headers.map(header => row[header] ?? '');
      csvRows.push(values.join(';'));
    }

    const csvContent = csvRows.join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });

    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', fileName);
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

}