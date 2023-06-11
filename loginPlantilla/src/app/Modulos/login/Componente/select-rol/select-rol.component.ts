import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-select-rol',
  templateUrl: './select-rol.component.html',
  styleUrls: ['./select-rol.component.scss']
})
export class SelectRolComponent {
  @Input() user:any;
  @Input() mostrarBtnSeleccionarRol!:boolean;
  @Output() seltRol:EventEmitter<any>= new EventEmitter<any>();
  @Output() cancelarSeleccion:EventEmitter<any>= new EventEmitter<any>();


  seleccionarRol(rol:string){
    this.user.authorities = this.user.authorities.filter((authority:any) => authority.authority === rol);
    this.seltRol.emit(this.user);
  }

  cancelar(){
    this.cancelarSeleccion.emit(true);
  }
}
