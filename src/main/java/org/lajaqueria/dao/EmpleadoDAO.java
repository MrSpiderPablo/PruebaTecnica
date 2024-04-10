package org.lajaqueria.dao;//@FunctionalInterface

import org.lajaqueria.core.Empleado;

import java.util.List;

public interface EmpleadoDAO {

     void insertar(Empleado empleado);

     void borrar(int id);

     List<Empleado> getAll();

     Empleado obtenerEmpleadoPorId(int id);

     Empleado actualizarEmpleado(Empleado empleado);

}
