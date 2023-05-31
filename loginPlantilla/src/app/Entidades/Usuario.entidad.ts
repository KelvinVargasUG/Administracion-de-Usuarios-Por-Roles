import { Rol } from "./Rol.entidad";

export interface Usuario {
  idUsuario?: number;
  nombre: string;
  apellido: string;
  estado: string;
  email: string;
  password: string;
  roles: Rol[];
}
