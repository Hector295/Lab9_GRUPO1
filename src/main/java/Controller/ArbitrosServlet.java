package Controller;

import Bean.Arbitros;
import Bean.Jugadores;
import Dao.DaoArbitros;
import Dao.DaoBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitrosServlet", urlPatterns = {"/ArbitrosServlet"})
public class ArbitrosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");

        switch (action) {

            case "buscar":
                String opcion = request.getParameter("tipo");
                String buscar = request.getParameter("buscar");
                if (opcion.equalsIgnoreCase("pais")) {
                    request.setAttribute("listaArbitros",daoArbitros.busquedaPais(buscar) );
                    request.setAttribute("opciones",opciones);
                }
                if (opcion.equalsIgnoreCase("nombre")) {
                    request.setAttribute("listaArbitros", daoArbitros.busquedaNombre(buscar));
                    request.setAttribute("opciones",opciones);
                }else{
                    request.setAttribute("paises", paises);
                    view = request.getRequestDispatcher("/Arbitros/form.jsp");
                    view.forward(request, response);
                }
                view = request.getRequestDispatcher("Arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "guardar":
                Arbitros arbitro = new Arbitros();
                arbitro.setNombre(request.getParameter("nombre"));
                arbitro.setPais(request.getParameter("pais"));
                ArrayList<Arbitros> listaArbitros= daoArbitros.listarArbitros();
                boolean evaluar = false;
                for(Arbitros i: listaArbitros){
                    if(i.getNombre().equalsIgnoreCase(arbitro.getNombre())){
                        evaluar = true;
                        break;
                    }
                }
                if(!arbitro.getNombre().equalsIgnoreCase("")
                        && !request.getParameter("nombre").equalsIgnoreCase("")
                        && !arbitro.getPais().equalsIgnoreCase("")
                        && evaluar ==false){

                    daoArbitros.crearArbitro(arbitro);
                }else{

                    view = request.getRequestDispatcher("/Jugadores/FormCreate.jsp");
                    view.forward(request, response);
                }
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "lista":
                ArrayList<Arbitros> listaArbitros = daoArbitros.listarArbitros();
                request.setAttribute("listaArbitros", listaArbitros);
                request.setAttribute("opciones",opciones);
                view = request.getRequestDispatcher("/Arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("paises",paises);
                view = request.getRequestDispatcher("/Arbitros/form.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                int id = Integer.parseInt(request.getParameter("id"));
                if(daoArbitros.buscarArbitro(id) != null){
                    daoArbitros.borrarArbitro(id);
                }
                response.sendRedirect(request.getContextPath() + "/ArbitrosServlet");
                break;
        }
    }
}
