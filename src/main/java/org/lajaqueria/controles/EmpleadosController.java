package org.lajaqueria.controles;

import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;
import org.lajaqueria.dao.DepartamentoDAO;
import org.lajaqueria.dao.DepartamentoDAOImpl;
import org.lajaqueria.dao.EmpleadoDAO;
import org.lajaqueria.dao.EmpleadoDAOImpl;

import java.util.Comparator;
import java.util.List;


public class EmpleadosController {

    private EmpleadoDAO empleadoDAO;

    private DepartamentoDAO departamentoDAO;




    public EmpleadosController(EmpleadoDAO empleadoDAO, DepartamentoDAO departamentoDAO){

        this.empleadoDAO = empleadoDAO;
        this.departamentoDAO = departamentoDAO;

    }

    public EmpleadosController(){

    }

    public EmpleadosController(String url){

        this.empleadoDAO = new EmpleadoDAOImpl(url);
        this.departamentoDAO = new DepartamentoDAOImpl(url);
    }

    public void insertarEmpleado(Empleado empleado){

        empleadoDAO.insertar(empleado);
    }

    public void insertarDepartamento(Departamento departamento){

        departamentoDAO.insertar(departamento);
    }

    public void borrarEmpleado(Empleado empleado){

        empleadoDAO.borrar(empleado.getId());
    }

    public void borrarDepartamento(Departamento departamento){

        departamentoDAO.borrar(departamento.getId());
    }

    public void actualizarEmpleado(Empleado empleado){

        empleadoDAO.actualizarEmpleado(empleado);
    }

    public void actualizarDepartamento(Departamento departamento){

        departamentoDAO.actuañizarDepartamento(departamento);
    }

    public List<Empleado> empleadosByDepId(int id){

        List<Empleado> lista = empleadoDAO.getAll();

        List<Empleado> lista2 = lista.stream().filter(empleado -> empleado.getDepartamento().getId()==id).toList();

        return lista2;
    }

    public double edadMediaByDepId(int id){

        List<Empleado> lista = empleadoDAO.getAll();

        double media =
        lista.stream().filter(empleado -> empleado.getDepartamento().getId()==id)
                .mapToDouble(Empleado::getEdad)
                .average()
                .orElse(0);

        return media;

    }

    public Empleado empleadoMasJoven(){

        List<Empleado> lista = empleadoDAO.getAll();

       return lista.stream().sorted(Comparator.comparing(Empleado::getEdad)).findFirst().get();

    }

    public Empleado empleadoMasViejo(){

        List<Empleado> lista = empleadoDAO.getAll();

        return lista.stream().sorted(Comparator.comparing(Empleado::getEdad).reversed()).findFirst().get();

    }

    public List<Empleado> empleadosByEdad(int edad){

        List<Empleado> lista = empleadoDAO.getAll();

        List<Empleado> lista2 = lista.stream().filter(empleado -> empleado.getEdad()==edad).toList();

        return lista2;
    }

}
