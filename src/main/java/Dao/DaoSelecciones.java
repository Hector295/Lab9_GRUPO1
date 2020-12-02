package Dao;

import Bean.Estadios;
import Bean.SeleccionesNacionales;

import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones  extends  DaoBase{

    public ArrayList<SeleccionesNacionales> listarSelecciones(){

        ArrayList<SeleccionesNacionales> seleccionesNacionalesArrayList = new ArrayList<>();
        String sql = "SELECT s.nombre, s.tecnico, e.nombre FROM seleccionesnacionales s, estadios e where s.estadios_idEstadios=e.idEstadios;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                SeleccionesNacionales seleccion= new SeleccionesNacionales();
                seleccion.setNombre(rs.getString(1));
                seleccion.setTecnico(rs.getString(2));

                Estadios estadios= new Estadios();
                estadios.setNombre(rs.getString(3));
                seleccion.setEstadio(estadios);

                seleccionesNacionalesArrayList.add(seleccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seleccionesNacionalesArrayList;
    }

}
