package org.lajaqueria.controles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;
import org.lajaqueria.dao.DepartamentoDAO;
import org.lajaqueria.dao.DepartamentoDAOImpl;
import org.lajaqueria.dao.EmpleadoDAO;
import org.lajaqueria.dao.EmpleadoDAOImpl;

import java.io.File;
import java.sql.*;
import java.util.List;

public class EmpleadosControllerTest {

    private static final String DBPATH = "gestionEmpresarial.db";

    private EmpleadosController empleadosController;

    private EmpleadoDAO empleadoDAO;

    private DepartamentoDAO departamentoDAO;

    @Before
    public void setUp(){



        empleadoDAO = new EmpleadoDAOImpl(DBPATH);

        departamentoDAO = new DepartamentoDAOImpl(DBPATH);

        empleadosController = new EmpleadosController(empleadoDAO, departamentoDAO);
    }

    @Test
    public void empleadosByDepIdTest(){

        Departamento departamento = new Departamento(33, "Marketing");
        Empleado empleado = new Empleado(88655932, "Ferrán", "Cervantes", 33, departamento);

        empleadoDAO.insertar(empleado);

        List<Empleado> lista = empleadosController.empleadosByDepId(empleado.getDepartamento().getId());

        Assert.assertEquals(1, lista.size());

        Assert.assertEquals(empleado.getDepartamento().getId(), lista.get(0).getDepartamento().getId());

    }

    @Test
    public void edadMediaByDepIdTest(){

        Departamento departamento = new Departamento(40, "Enseñanza");
        Empleado empleado = new Empleado(44322978, "Gumball", "Waterson", 26, departamento);

        empleadoDAO.insertar(empleado);

        double edadMedia = empleadosController.edadMediaByDepId(empleado.getDepartamento().getId());

        Assert.assertEquals(26.0, edadMedia, 0.001d);


    }

    @Test
    public void empleadoMasJovenTest(){

        Departamento departamento = new Departamento(50, "Medicina");
        Empleado empleado = new Empleado(55677893, "Richard", "Waterson", 35, departamento);

        empleadoDAO.insertar(empleado);

        empleadosController.empleadoMasJoven();

        Assert.assertEquals(35, empleado.getEdad());

    }

    @Test
    public void empleadoMasViejoTest(){

        Departamento departamento = new Departamento(60, "Biologia");
        Empleado empleado = new Empleado(22344567, "Bruce", "Banner", 40, departamento);

        empleadoDAO.insertar(empleado);

        empleadosController.empleadoMasViejo();

        Assert.assertEquals(40, empleado.getEdad());

    }

    @Test
    public void empleadosByEdadTest(){

        Departamento departamento = new Departamento(66, "Psicología");
        Empleado empleado = new Empleado(33455789, "Ali", "G", 28, departamento);

        empleadoDAO.insertar(empleado);

        List<Empleado> lista = empleadosController.empleadosByEdad(empleado.getEdad());

        Assert.assertEquals(1, lista.size());

        Assert.assertEquals(empleado.getEdad(), lista.get(0).getEdad());
    }

    @After
    public void tearDown(){
        File file = new File("C:\\sqlite\\db\\gestionEmpresarialtest.db");

        file.delete();
    }



}
