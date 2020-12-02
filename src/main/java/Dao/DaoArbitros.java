package Dao;

import Bean.Arbitros;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase {
    public ArrayList<Arbitros> listarArbitros() {
        String sql="SELECT * from arbitros";
        ArrayList<Arbitros> arbitros = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                Arbitros arbitro= new Arbitros();
                arbitro.setIdArbitros(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));
                arbitros.add(arbitro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arbitros;
    }

    public void crearArbitro(Arbitros arbitros) {
        String sql ="INSERT INTO arbitros(nombre, pais) VALUES (?,?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, arbitros.getNombre());
            pstmt.setString(2, arbitros.getPais());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Arbitros> busquedaPais(String pais) {

        ArrayList<Arbitros> arbitros = new ArrayList<>();
        String sql="select*from arbitros where nombre like ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1,"%"+pais+"%");

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    Arbitros arbitro = new Arbitros();
                    arbitros.add(arbitro);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arbitros;
    }

    public ArrayList<Arbitros> busquedaNombre(String nombre) {

        ArrayList<Arbitros> arbitros = new ArrayList<>();
        String sql="select*from arbitros where nombre like ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1,"%"+nombre+"%");

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                Arbitros arbitro = new Arbitros();
                arbitros.add(arbitro);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arbitros;
    }

    public Arbitros buscarArbitro(int id){

        Arbitros arbitros = new Arbitros();
        String sql="select*from arbitros where idArbitros=?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1,"%"+id+"%");

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    arbitros.setNombre(rs.getString(1));
                    arbitros.setPais(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arbitros;
    }

    public void borrarArbitro(int id){
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE from arbitros where idArbitros=?;");) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
