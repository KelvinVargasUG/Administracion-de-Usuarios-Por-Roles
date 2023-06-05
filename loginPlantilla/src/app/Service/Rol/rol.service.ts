import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Rol} from "../../Entidades/Rol.entidad";
import {UrlsApis} from '../UrlsApis'

@Injectable({
  providedIn: 'root'
})
export class RolService {
  url_api=UrlsApis.URL+'/rol';

  constructor(private http:HttpClient) { }
  
  getAllRol(){
    return this.http.get<Rol[]>(`${this.url_api}`)
  }

  getAllRolNoAsigne(id:number){
    return this.http.get<Rol[]>(`${this.url_api}/noAsiganados/${id}`)
  }

  deletelRol(idUser:number, idRol:number){
    return this.http.put(`${this.url_api}/delete/${idUser}/${idRol}`, "");
  }

}
