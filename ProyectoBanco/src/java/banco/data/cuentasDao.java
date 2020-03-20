/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.TipoCuenta;
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
public class cuentasDao {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @author david
     */
    public static boolean registrar(Cuenta cuenta) throws Exception {
        String SQL = "insert into cuenta (fecha_creacion,limite_transferencia_diaria,activa,saldo_inicial,fecha_ultima_aplicacion,saldo_final,moneda_nombre,cliente_id_cliente, idTipoCuenta)"
                + "values (?,?,?,?,?,?,?,?,?);";
        try {
            //Se crea la coneccion y luego se prepara el query que se ejecutará en la BD
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            //Se sustituyen los valores que existían en el String.

            st.setInt(1, cuenta.getNumCuenta());
            st.setDate(2, new java.sql.Date(cuenta.getFechaCreacion().getDate()));
            st.setDouble(3, cuenta.getLimiteTransferenciaDiaria());
            st.setInt(4, cuenta.getActiva());
            st.setDouble(5, cuenta.getSaldoInicial());
            st.setDate(6, new java.sql.Date(cuenta.getFechaUltimaAplicacion().getDate()));
            st.setDouble(7, cuenta.getSaldoFinal());
//Atributos de objetos
            st.setDouble(8, cuenta.getMonedaNombre().getNombre());
            st.setInt(9, cuenta.getClienteIdCliente().getIdCliente());
            st.setInt(10, cuenta.getIdTipoCuenta().getIdTipoCuenta());

            return st.executeUpdate() != 0;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static ArrayList<Cuenta> listar() throws Exception {
        String SQL = "select * from cuenta c inner join moneda m on "
                 + "c.moneda_nombre = m.nombre "
                 + "inner join cliente cli on "
                 + "c.cliente_id_cliente = cli.id_cliente "
                 + "inner join usuario u on "
                 + "cli.usuario_id_usuario = u.id_usuario "
                 + "inner join tipo_cuenta tp on "
                 + "c.idTipoCuenta = tp.id_tipo_cuenta ";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Cuenta> lista = new ArrayList<>();
            Cuenta cuenta;

            while (resultado.next()) {
                cuenta = new Cuenta();
                cuenta.setNumCuenta(resultado.getInt("num_cuenta"));
                cuenta.setFechaCreacion(resultado.getDate("fecha_creacion"));
                cuenta.setLimiteTransferenciaDiaria(resultado.getDouble("limite_transferencia_diaria"));
                cuenta.setActiva(resultado.getShort("activa"));
                cuenta.setSaldoInicial(resultado.getDouble("saldo_inicial"));
                cuenta.setFechaUltimaAplicacion(resultado.getDate("fecha_ultima_aplicacion"));
                cuenta.setSaldoFinal(resultado.getFloat("saldo_final"));
                cuenta.setMonedaNombre(creaMoneda(resultado));
                cuenta.setClienteIdCliente(creaCliente(resultado));
                cuenta.setIdTipoCuenta(creaIdTipoCuenta(resultado));


                lista.add(cuenta);
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

    public static Moneda creaMoneda(ResultSet resultado) throws SQLException {
        Moneda moneda = new Moneda();
        
        moneda.setNombre(resultado.getInt("nombre"));
        moneda.setSimbolo(resultado.getString("simbolo"));
        moneda.setDescripcion(resultado.getString("descripcion"));
        moneda.setTipoCambioCompra(resultado.getDouble("tipo_cambio_compra"));
        moneda.setTipoCambioVenta(resultado.getDouble("tipo_cambio_venta"));

        return moneda;
    }
       public static Usuario creaUsuario(ResultSet resultado) throws SQLException {
        Usuario usu = new Usuario();
        
        usu.setIdUsuario(resultado.getString("id_Usuario"));
        usu.setClaveAcceso(resultado.getString("clave_acceso"));
        usu.setRol(resultado.getInt("rol"));

        return usu;
    }
    public static Cliente creaCliente(ResultSet resultado) throws SQLException {
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(resultado.getInt("id_cliente"));
        cliente.setUsuarioIdUsuario(creaUsuario(resultado));
        cliente.setApellidos(resultado.getString("apellidos"));
        cliente.setNombre(resultado.getString("nombre"));
        cliente.setTelefono(resultado.getString("telefono"));

        return cliente;
    }
    
        public static TipoCuenta creaIdTipoCuenta(ResultSet resultado) throws SQLException {
        TipoCuenta tp = new TipoCuenta();
        
        tp.setIdTipoCuenta(resultado.getInt("id_cliente"));
        tp.setDescripción("descripción");
        tp.setTasaInterés(resultado.getInt("tasa_interés"));

        return tp;
    }

     public static ArrayList<Cuenta> getCuentasCliente(int id) throws Exception {
         String SQL = "select * from cuenta c inner join moneda m on "
                 + "c.moneda_nombre = m.nombre "
                 + "inner join cliente cli on "
                 + "c.cliente_id_cliente = cli.id_cliente "
                 + "inner join usuario u on "
                 + "cli.usuario_id_usuario = u.id_usuario "
                 + "inner join tipo_cuenta tp on "
                 + "c.idTipoCuenta = tp.id_tipo_cuenta "
                 + "where cliente_id_cliente=?;";

        

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, id);
            
            ResultSet resultado = st.executeQuery();
            
            ArrayList<Cuenta> lista = new ArrayList<>();
            Cuenta cuenta;

            while (resultado.next()) {
                cuenta = new Cuenta();
                cuenta.setNumCuenta(resultado.getInt("num_cuenta"));
                cuenta.setFechaCreacion(resultado.getDate("fecha_creacion"));
                cuenta.setLimiteTransferenciaDiaria(resultado.getDouble("limite_transferencia_diaria"));
                cuenta.setActiva(resultado.getShort("activa"));
                cuenta.setSaldoInicial(resultado.getDouble("saldo_inicial"));
                cuenta.setFechaUltimaAplicacion(resultado.getDate("fecha_ultima_aplicacion"));
                cuenta.setSaldoFinal(resultado.getFloat("saldo_final"));
                cuenta.setMonedaNombre(creaMoneda(resultado));
                cuenta.setClienteIdCliente(creaCliente(resultado));
                cuenta.setIdTipoCuenta(creaIdTipoCuenta(resultado));

                lista.add(cuenta);
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
     
     
}
