import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Función usada para validar aplicar el validator del campo solo si éste está vacío.
 * 
 * @author Harold
 * @returns Null el campo está vacío. Validatos.email o Validators.pattern si el 
 * campo no está vacío. 
 */
export function conditionalValidator(validator: ValidatorFn): ValidatorFn {

  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value && control.value.trim() !== '') {
      return validator(control);
    }
    return null;
  };

}