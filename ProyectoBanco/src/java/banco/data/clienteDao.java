/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class clienteDao {

    public static boolean registrar(Cliente cli) throws Exception {
        String SQL = "insert into cliente values (?,?,?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, cli.getIdCliente());
            st.setString(2, cli.getUsuarioIdUsuario().getIdUsuario());
            st.setString(3, cli.getApellidos());
            st.setString(4, cli.getNombre());
            st.setString(5, cli.getTelefono());
            return st.executeUpdate() != 0;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Usuario creaUsuario(ResultSet resultado) throws SQLException {
        Usuario usu = new Usuario();
        
        usu.setIdUsuario(resultado.getString("id_Usuario"));
        usu.setClaveAcceso(resultado.getString("clave_acceso"));
        usu.setRol(resultado.getInt("rol"));

        return usu;
    }

    public static ArrayList<Cliente> listar() throws Exception {
        String SQL = "select*from cliente inner join usuario where usuario_id_usuario = id_usuario;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();
            Cliente cli ;

            while (resultado.next()) {
                cli = new Cliente();
                cli.setIdCliente(resultado.getString("id_cliente"));
                cli.setUsuarioIdUsuario(creaUsuario(resultado));
                cli.setApellidos(resultado.getString("apellidos"));
                cli.setNombre(resultado.getString("nombre"));
                cli.setTelefono(resultado.getString("telefono"));
               
                lista.add(cli);
            }
            con.close();
            st.close();
            resultado.close();
            return lista;

        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
//
//    public static Cliente find(String ced, String clave) throws Exception {
//        String SQL = "select * from usuario where id_usuario=? and clave_acceso=?;";
//        Cliente usu = null;
//
//        try {
//            Connection con = Coneccion.conectar();
//            PreparedStatement st = con.prepareStatement(SQL);
//            st.setString(1, ced);
//            st.setString(2, clave);
//            ResultSet resultado = st.executeQuery();
//
//            while (resultado.next()) {
//                usu = new Usuario();
//                usu.setIdUsuario(resultado.getString("id_Usuario"));
//                usu.setClaveAcceso(resultado.getString("clave_acceso"));
//                usu.setRol(resultado.getInt("rol"));
//            }
//            return usu;
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//            return usu;
//        }
//    }

}
