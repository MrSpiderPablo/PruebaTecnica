package org.lajaqueria;

import org.lajaqueria.controles.EmpleadosController;
import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static int opcion = -1;

    private static final String DEPARTAMENTO = "ID Departamento: ";



    public static void main(String[] args){

        EmpleadosController empleadosController = new EmpleadosController("gestioEmpresarial.db");

        Empleado empleado = new Empleado();
        int idEmpleado;
        String nombre = "";
        String apellido = "";
        int edad;
        Departamento departamento = new Departamento();
        int idDepartamento;
        String nombreDep = "";
        String menu = """
                        Elige opción:
                        1. - Insertar
                        2. - Borrar
                        3. - Actualizar
                        4. - Consultar
                        0. - Salir""";

        do {


            try {

                System.out.println(menu);
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Insertar ID empleado: ");
                        idEmpleado = Integer.parseInt(scanner.nextLine());
                        System.out.println("ID empleado: " + idEmpleado);

                        System.out.println("Insertar nombre: ");
                        nombre = scanner.nextLine();
                        System.out.println("Nombre empleado: " + nombre);

                        System.out.println("Insertar apellido: ");
                        apellido = scanner.nextLine();
                        System.out.println("Apellidos empleado: " + apellido);

                        System.out.println("Insertar edad: ");
                        edad = Integer.parseInt(scanner.nextLine());
                        System.out.println("Edad empleado: " + edad);

                        System.out.println("Insertar ID departamento: ");
                        idDepartamento = Integer.parseInt(scanner.nextLine());
                        System.out.println(DEPARTAMENTO + idDepartamento);

                        System.out.println("Insertar nombre departamento: ");
                        nombreDep = scanner.nextLine();
                        System.out.println("Nombre Departamento: " + nombreDep);

                        empleado.setId(idEmpleado);
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setEdad(edad);
                        empleado.setDepartamento(departamento);
                        departamento.setId(idDepartamento);
                        departamento.setNombreDep(nombreDep);

                        empleadosController.insertarEmpleado(empleado);
                        empleadosController.insertarDepartamento(departamento);
                        break;
                    case 2:
                        System.out.println("Borrar empleado: ");

                        idEmpleado = Integer.parseInt(scanner.nextLine());
                        System.out.println("ID Empleado: " + idEmpleado);


                        empleado.setId(idEmpleado);

                        empleadosController.borrarEmpleado(empleado);

                        break;
                    case 3:
                        System.out.println("Actualizar empleado o departamento: ");

                        System.out.println("Insertar ID empleado: ");
                        idEmpleado = Integer.parseInt(scanner.nextLine());
                        System.out.println("ID empleado: " + idEmpleado);

                        System.out.println("Insertar nombre: ");
                        nombre = scanner.nextLine();
                        System.out.println("Nombre empleado: " + nombre);

                        System.out.println("Insertar apellido: ");
                        apellido = scanner.nextLine();
                        System.out.println("Apellidos empleado: " + apellido);

                        System.out.println("Insertar edad: ");
                        edad = Integer.parseInt(scanner.nextLine());
                        System.out.println("Edad empleado: " + edad);

                        System.out.println("Insertar ID departamento: ");
                        idDepartamento = Integer.parseInt(scanner.nextLine());
                        System.out.println(DEPARTAMENTO + idDepartamento);

                        System.out.println("Insertar nombre departamento: ");
                        nombreDep = scanner.nextLine();
                        System.out.println("Nombre Departamento: " + nombreDep);

                        empleado.setId(idEmpleado);
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setEdad(edad);
                        empleado.setDepartamento(departamento);
                        departamento.setId(idDepartamento);
                        departamento.setNombreDep(nombreDep);

                        empleadosController.actualizarEmpleado(empleado);
                        empleadosController.actualizarDepartamento(departamento);
                        break;
                    case 4:
                        
                        try {
                            System.out.println("""
                                    ¿Qué desea hacer?:
                                    1. - Obtener empleados por ID de departamento
                                    2. - Obtener la edad media de un grupo de empleados por ID de departamento
                                    3. - Empleado más joven
                                    4. - Empleado más viejo
                                    5. - Lista de empleados por la edad""");
                            opcion = Integer.parseInt(scanner.nextLine());

                            switch (opcion) {
                                case 1:

                                    System.out.println("Introduce el id del departamento: ");
                                    idDepartamento = Integer.parseInt(scanner.nextLine());
                                    System.out.println(DEPARTAMENTO + idDepartamento);

                                    List<Empleado> lista = empleadosController.empleadosByDepId(idDepartamento);

                                    System.out.println("Lista de empleados por ID de departamento: " + lista);
                                    break;
                                case 2:

                                    System.out.println("Introduce el id del departamento: ");
                                    idDepartamento = Integer.parseInt(scanner.nextLine());
                                    System.out.println(DEPARTAMENTO + idDepartamento);

                                    Double edadMedia = empleadosController.edadMediaByDepId(idDepartamento);

                                    System.out.println("Edad media de empleados: " + edadMedia);

                                    break;
                                case 3:
                                    Empleado empleadoJoven = empleadosController.empleadoMasJoven();

                                    System.out.println("Emepleado más joven: " + empleadoJoven);

                                    break;
                                case 4:
                                    Empleado empleadoViejo = empleadosController.empleadoMasViejo();

                                    System.out.println("Empleado más viejo: " + empleadoViejo);

                                    break;
                                case 5:
                                    System.out.println("Introduzca la edad del empleado: ");
                                    edad = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Edad: " + edad);

                                    List<Empleado> lista2 = empleadosController.empleadosByEdad(edad);

                                    System.out.println("Lista de empleados por edad: " + lista2);
                                    break;
                                default:
                                    System.out.println("Esta opción no existe");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        System.out.println("¡Al fin se acabó!");
                        break;
                    default:
                        System.out.println("Esta opción no existe");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }while (opcion != 0);

    }
}
