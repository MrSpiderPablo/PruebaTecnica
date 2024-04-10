package org.lajaqueria.core;

import java.util.Objects;

public class Departamento {

    private  int id;
    private String nombreDep;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return id == that.id && Objects.equals(nombreDep, that.nombreDep);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombreDep='" + nombreDep + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreDep);
    }

    public Departamento(){

    }

    public Departamento(int id, String nombreDep){

        this.id = id;
        this.nombreDep = nombreDep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDep() {
        return nombreDep;
    }

    public void setNombreDep(String nombreDep) {
        this.nombreDep = nombreDep;
    }
}
