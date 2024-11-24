import { AbstractControl, ValidationErrors, ValidatorFn, FormGroup } from '@angular/forms';

/**
 * Función usada para validar fechas.
 * La fecha inicial no puede ser después que la final y viceversa.
 * 
 * @author Harold
 * @returns Null si las fechas son válidas o la propiedad invalidDateRange 
 * con valor true si las fechas no son válidas.
 */
export function dateRangeValidator(): ValidatorFn {

  return (control: AbstractControl): ValidationErrors | null => {
    const formGroup = control as FormGroup;
    const startDate = formGroup.get('startDate')?.value;
    const endDate = formGroup.get('endDate')?.value;
    if (!startDate || !endDate) {
      return null;
    }
    const start = new Date(startDate);
    const end = new Date(endDate);
    return start > end ? { 'invalidDateRange': true } : null;
  };
  
}