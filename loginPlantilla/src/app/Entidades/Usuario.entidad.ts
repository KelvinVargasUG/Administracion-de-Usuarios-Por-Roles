export interface Usuario {
  id: number;
  nombre: string;
  apellido: string;
  estado: string;
  email: string;
  password: string;
  roles: [{ idRol: number; nombre: string }];
}
