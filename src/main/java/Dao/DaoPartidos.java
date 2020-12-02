package Dao;

import Bean.Partidos;
import Bean.SeleccionesNacionales;
import com.sun.xml.internal.fastinfoset.util.PrefixArray;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos {

    public ArrayList<Partidos> listaDePartidos(){

        ArrayList<Partidos> partidos = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.partidos";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Partidos partido = new Partidos();
                partido.setIdPartido(rs.getInt(1));
                partido.setNombre(rs.getString(2));
                partido.setTecnico(rs.getString(3));
                partido.setEstadios_idEstadio(rs.getInt(4));
                seleccionesNacionalesArrayList.add(seleccionesNacionales);
                System.out.println("ID: " + seleccionesNacionales.getIdSeleccionesNacionales()
                        + " | nombre: " + seleccionesNacionales.getNombre()
                        + " | Director Técnico: " + seleccionesNacionales.getTecnico()
                        + " | Estadio: " + seleccionesNacionales.getEstadios_idEstadio() );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return partidos;
    }

    public void crearPartido(Partidos partidos){

        /*
                Inserte su código aquí
                 */
    }


}
