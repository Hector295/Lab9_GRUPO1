package Dao;

import Bean.Arbitros;
import Bean.Estadios;
import Bean.Partidos;
import Bean.SeleccionesNacionales;
import com.sun.xml.internal.fastinfoset.util.PrefixArray;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {

    public ArrayList<Partidos> listaDePartidos() {

        ArrayList<Partidos> partidos = new ArrayList<>();
        String sql = "select p.idPartidos, p.numeroJornada, p.fecha, p.seleccionLocal, p.seleccionVisitante, e.nombre, a.nombre " +
                "from partidos p, seleccionesnacionales s, arbitros a, estadios e where p.seleccionLocal=s.idSeleccionesNacionales " +
                "and p.arbitro=a.idArbitros and e.idEstadios=s.estadios_idEstadios and s.idSeleccionesNacionales=p.seleccionLocal; ";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Partidos partido = new Partidos();
                partido.setIdPartido(rs.getInt(1));
                partido.setNumeroJornada(rs.getInt(2));
                partido.setFecha(rs.getString(3));

                SeleccionesNacionales seleLoc= new SeleccionesNacionales();
                seleLoc.setIdSeleccionesNacionales(rs.getInt(4));
                partido.setSeleccionLocal(seleLoc);

                SeleccionesNacionales selecVis= new SeleccionesNacionales();
                selecVis.setIdSeleccionesNacionales(rs.getInt(5));
                partido.setSeleccionVisitante(selecVis);

                Estadios estadio=new Estadios();
                estadio.setNombre(rs.getString(6));
                partido.setEstadio(estadio);

                Arbitros arbitro= new Arbitros();
                arbitro.setNombre(rs.getString(7));
                partido.setArbitro(arbitro);

                partidos.add(partido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }

    public void crearPartido(Partidos partidos) {

        /*
                Inserte su código aquí
                 */
    }

}
