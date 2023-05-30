import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/Entidades/Usuario.entidad';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url_api='http://localhost:8080/usuario';

  constructor(private http:HttpClient) { }
  getAllProducts(){
    console.log( this.http.get<Usuario[]>(`${this.url_api}`));
  }

  createUsuario(usuario:Usuario){
    usuario.estado='A';
    usuario.roles=[{ idRol: 1, nombre:"" }];
    console.log(usuario)

    return this.http.post<Usuario>(`${this.url_api}`,usuario);
  }
}
