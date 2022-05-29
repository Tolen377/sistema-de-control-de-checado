/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.app_web_responsiva.persistencia.repositorio;

import com.mycompany.app_web_responsiva.persistencia.entidad.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEmpleado extends JpaRepository<Empleado,Long>{
    public List<Empleado> getByClaveLikeOrderByClave(String clave);
}