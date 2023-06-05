import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/Entidades/Usuario.entidad';
import {UrlsApis} from '../UrlsApis'


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  url_api=UrlsApis.URL+'/usuarios';

  constructor(private http:HttpClient) { }

  registreUsuario(usuario:Usuario){
    usuario.estado='A';
    usuario.roles=[{ idRol: 2}];
    console.log(usuario)
    return this.http.post<Usuario>(`${this.url_api}`,usuario);
  }

  createUsuario(usuario:Usuario){
    return this.http.post<Usuario>(`${this.url_api}`,usuario);
  }

  getAllUsuario(){
    return this.http.get<Usuario[]>(`${this.url_api}`)
  }

  deleteUsuario(id:number){
    return this.http.put(`${this.url_api}/delete`, id);
  }

  getUsuarioById(id:number){
    return this.http.get<Usuario>(`${this.url_api}/${id}`)
  }

  updateUsuario(usuario:Usuario){
    const id = usuario.idUsuario;
    return this.http.put<Usuario>(`${this.url_api}/${id}`,usuario);
  }


}
