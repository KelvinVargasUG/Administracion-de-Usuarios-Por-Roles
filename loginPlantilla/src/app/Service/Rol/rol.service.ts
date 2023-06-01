import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Usuario} from "../../Entidades/Usuario.entidad";
import {Rol} from "../../Entidades/Rol.entidad";

@Injectable({
  providedIn: 'root'
})
export class RolService {
  url_api='http://localhost:8080/rol';

  constructor(private http:HttpClient) { }
  getAllRol(){
    return this.http.get<Rol[]>(`${this.url_api}`)
  }
}
