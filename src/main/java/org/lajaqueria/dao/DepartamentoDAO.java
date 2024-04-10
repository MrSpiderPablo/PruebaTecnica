package org.lajaqueria.dao;

import org.lajaqueria.core.Departamento;

import java.util.List;

public interface DepartamentoDAO {

    void insertar(Departamento departamento);

    void borrar(int id);

    List<Departamento> getAll();

    Departamento obtenerDepartamentoPorId(int id);

    Departamento actualizarDepartamento(Departamento departamento);
}
