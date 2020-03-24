/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class ClienteDao {

    public static boolean registrar(Cliente cli) throws Exception {
        String SQL = "insert into cliente values (?,?,?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, cli.getIdCliente());
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
        String SQL = "select*from cliente inner join usuario on usuario_id_usuario = id_usuario;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();
            Cliente cli;

            while (resultado.next()) {
                cli = new Cliente();
                cli.setIdCliente(resultado.getInt("id_cliente"));
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
            System.out.println(ex);
            return null;
        }

    }

    public static Cliente find(String id) throws Exception {
        String SQL = "select*from cliente inner join usuario on usuario_id_usuario = id_usuario where usuario_id_usuario =?";
        Cliente cli = null;
        ArrayList<Cuenta> fav = new ArrayList<Cuenta>();

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, id);
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                cli = new Cliente();
                cli.setIdCliente(resultado.getInt("id_cliente"));
                cli.setUsuarioIdUsuario(creaUsuario(resultado));
                cli.setApellidos(resultado.getString("apellidos"));
                cli.setNombre(resultado.getString("nombre"));
                cli.setTelefono(resultado.getString("telefono"));
                llenarFavoritas(cli);

            }

            con.close();
            st.close();
            resultado.close();

            return cli;

        } catch (SQLException ex) {
            System.out.println(ex);
            return cli;
        }
    }

    public static void llenarFavoritas(Cliente cliente) {
        String SQL = "select * from favorita where cliente_id=?;";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, cliente.getUsuarioIdUsuario().getIdUsuario());

            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                cliente.getFavoritasCollection().add(CuentaDao.getCuenta(resultado.getInt("cuenta_id")));
            }
            con.close();
            st.close();
            resultado.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void agregarFavorita(int clienteId, int cuentaId) throws Exception {
        String SQL = "insert into favorita (cliente_id, cuenta_id) values (?,?)";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, clienteId);
            st.setInt(1, cuentaId);
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

    public static boolean actualizar(String nombre, String apellidos, String telefono, String idUsuario) throws Exception {
        String SQL = "update cliente set nombre =?, apellidos =?, telefono =? where usuario_id_usuario =?;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, nombre);
            st.setString(2, apellidos);
            st.setString(3, telefono);
            st.setString(4, idUsuario);
            st.executeUpdate();
            return true;
            
        } catch (Exception e) {
            return false;
        }

    }

}
