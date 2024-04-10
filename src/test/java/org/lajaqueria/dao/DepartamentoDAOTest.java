package org.lajaqueria.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lajaqueria.core.Departamento;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.lajaqueria.dao.EmpleadoDAOTest.*;

public class DepartamentoDAOTest {

    private final static String DBPATH = "gestionEmpresarial.db";

    private DepartamentoDAO departamentoDAO;

    @Before
    public void setUp(){


        departamentoDAO = new DepartamentoDAOImpl(DBPATH);
    }

    @Test
    public void testInsertar(){

        Departamento departamento = new Departamento(33, "Marketing");

        departamentoDAO.insertar(departamento);

        Departamento departamento2 = departamentoDAO.obtenerDepartamentoPorId(departamento.getId());

        Assert.assertEquals(departamento.getId(), departamento2.getId());
    }

    @Test
    public void testBorrar(){

        Departamento departamento = new Departamento(55, "Telefonía");

        departamentoDAO.insertar(departamento);

        departamentoDAO.borrar(departamento.getId());

        Departamento departamento2 = departamentoDAO.obtenerDepartamentoPorId(departamento.getId());

        Assert.assertNull(departamento2);
    }

    @Test
    public void testActualizar(){

        Departamento departamento = new Departamento(76, "Ingeniería");

        departamentoDAO.insertar(departamento);

        departamento.setNombreDep("Filosofía");

        departamentoDAO.actuañizarDepartamento(departamento);

        Departamento departamento2 = departamentoDAO.obtenerDepartamentoPorId(departamento.getId());

        Assert.assertEquals(departamento.getId(), departamento2.getId());
    }

    @Test
    public void obtenerTodos(){
        Departamento departamento = new Departamento(88, "Matematicas");

        departamentoDAO.insertar(departamento);

        List<Departamento> lista = departamentoDAO.getAll();

        Assert.assertEquals(1, lista.size());

        Assert.assertEquals(departamento.getNombreDep(), lista.get(0).getNombreDep());
    }

    @After
    public  void tearDown(){

        File file = new File(DBPATH);

        file.delete();
    }


}
