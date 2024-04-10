package org.lajaqueria.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;

import java.io.File;
import java.sql.*;
import java.util.List;

public class EmpleadoDAOTest {

    private static final String DBPATH = "gestionEmpresarial.db";

    private EmpleadoDAO empleadoDAO;

    @Before
    public void setUp(){

        empleadoDAO = new EmpleadoDAOImpl(DBPATH);
    }


    @Test
    public void testInsertar(){

        Departamento departamento = new Departamento(33, "Marketing");
        Empleado empleado = new Empleado(88655932, "Ferrán", "Cervantes", 33, departamento);

        empleadoDAO.insertar(empleado);

        Empleado empleado2 = empleadoDAO.obtenerEmpleadoPorId(empleado.getId());

        Assert.assertEquals(empleado.getId(), empleado2.getId());

    }

    @Test
    public void testBorrar(){

        Departamento departamento = new Departamento(25, "Informatica");
        Empleado empleado = new Empleado(33455897, "Pedro", "Roñeras", 28, departamento);

        empleadoDAO.insertar(empleado);

        empleadoDAO.borrar(empleado.getId());

        Empleado empleado2 = empleadoDAO.obtenerEmpleadoPorId(empleado.getId());

        Assert.assertNull(empleado2);
    }

    @Test
    public void testActualizar(){

        Departamento departamento = new Departamento(76, "Ingeniería");
        Empleado empleado = new Empleado(55766821, "Maria", "Mercedes", 25, departamento);

        empleadoDAO.insertar(empleado);

        empleado.setNombre("Mario");

        empleadoDAO.actualizarEmpleado(empleado);

        Empleado empleado2 = empleadoDAO.obtenerEmpleadoPorId(empleado.getId());

        Assert.assertEquals(empleado.getNombre(), empleado2.getNombre());
    }

    @Test
    public void testObtenerTodos(){

        Departamento departamento = new Departamento(88, "Matematicas");
        Empleado empleado = new Empleado(44377892, "Lucas", "Lucas", 30, departamento);

        empleadoDAO.insertar(empleado);

        List<Empleado> lista = empleadoDAO.getAll();

        Assert.assertEquals(1, lista.size());

        Assert.assertEquals(empleado.getNombre(), lista.get(0).getNombre());
    }

    @After
    public  void tearDown(){

        File file = new File(DBPATH);

        file.delete();
    }

}
