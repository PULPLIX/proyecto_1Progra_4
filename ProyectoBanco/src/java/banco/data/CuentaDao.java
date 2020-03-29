/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.Movimiento;
import banco.logica.TipoCuenta;
import banco.logica.Transferencia;
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
public class CuentaDao {

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

            st.setDate(1, new java.sql.Date(cuenta.getFechaCreacion().getDate()));
            st.setDouble(2, cuenta.getLimiteTransferenciaDiaria());
            st.setInt(3, cuenta.getActiva());
            st.setDouble(4, cuenta.getSaldoInicial());
            st.setDate(5, new java.sql.Date(cuenta.getFechaUltimaAplicacion().getDate()));
            st.setDouble(6, cuenta.getSaldoFinal());
//Atributos de objetos
            st.setInt(7, cuenta.getMonedaNombre().getNombre());
            st.setString(8, cuenta.getClienteIdCliente().getUsuarioIdUsuario().getIdUsuario());
            st.setInt(9, cuenta.getIdTipoCuenta().getIdTipoCuenta());

            return st.executeUpdate() != 0;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Cuenta getCuenta(int idCuenta) throws Exception {
        String SQL = "select * from cuenta c "
                + "inner join moneda m on c.moneda_nombre = m.nombre "
                + "inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario "
                + "inner join usuario u on cli.usuario_id_usuario = u.id_usuario "
                + "inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta "
                + "where c.num_cuenta=?;";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, idCuenta);

            ResultSet resultado = st.executeQuery();

            Cuenta cuenta = new Cuenta();
            ;

            if (resultado.next()) {
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
                llenarMovimientosCuenta(cuenta);
                llenarTransferencias(cuenta);

            }
            con.close();
            st.close();
            resultado.close();
            return cuenta;

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
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
        cliente.setNombre(resultado.getString("cli.nombre"));
        cliente.setTelefono(resultado.getString("telefono"));

        return cliente;
    }

    public static TipoCuenta creaIdTipoCuenta(ResultSet resultado) throws SQLException {
        TipoCuenta tp = new TipoCuenta();

        tp.setIdTipoCuenta(resultado.getInt("id_cliente"));
        tp.setDescripción(resultado.getString("descripción"));
        tp.setTasaInterés(resultado.getInt("tasa_interés"));

        return tp;
    }

    public static ArrayList<Cuenta> getCuentasCliente(String id) throws Exception {
        String SQL = "select * from cuenta c "
                + "inner join moneda m on c.moneda_nombre = m.nombre "
                + "inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario "
                + "inner join usuario u on cli.usuario_id_usuario = u.id_usuario "
                + "inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta "
                + "where cliente_id_cliente=?;";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, id);

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
                llenarMovimientosCuenta(cuenta);
                llenarTransferencias(cuenta);

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

    public static Cuenta getCuentaUnica(String idCuenta) throws Exception {
        String SQL = "select * from cuenta c "
                + "inner join moneda m on c.moneda_nombre = m.nombre "
                + "inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario "
                + "inner join usuario u on cli.usuario_id_usuario = u.id_usuario "
                + "inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta "
                + "where num_cuenta=?;";

        try {
            Cuenta cuenta = null;

            PreparedStatement st;
            ResultSet resultado;
            try (Connection con = Coneccion.conectar()) {
                st = con.prepareStatement(SQL);
                st.setInt(1, Integer.parseInt(idCuenta));

                resultado = st.executeQuery();
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
                    llenarMovimientosCuenta(cuenta);
                    llenarTransferencias(cuenta);
                }
            }
            st.close();
            resultado.close();
            return cuenta;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static ArrayList<Cuenta> getCuenta(String idUsuario, String idCuenta) throws Exception {
        String SQL = "select * from cuenta c "
                + "inner join moneda m on c.moneda_nombre = m.nombre "
                + "inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario "
                + "inner join usuario u on cli.usuario_id_usuario = u.id_usuario "
                + "inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta "
                + "where num_cuenta=? and cliente_id_cliente =?;";

        try {
            PreparedStatement st;
            ResultSet resultado;
            ArrayList<Cuenta> lista;
            try (Connection con = Coneccion.conectar()) {
                st = con.prepareStatement(SQL);
                st.setInt(1, Integer.parseInt(idCuenta));
                st.setString(2, idUsuario);

                resultado = st.executeQuery();
                lista = new ArrayList<>();
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
                    llenarMovimientosCuenta(cuenta);
                    llenarTransferencias(cuenta);
                    lista.add(cuenta);
                }
            }
            st.close();
            resultado.close();
            return lista;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    //Toma el id de la cuenta y realiza un select en la tabla movimiento 
    //de unicamente las filas que se relacionan con el id de la cuenta
    public static void llenarMovimientosCuenta(Cuenta cuenta) {
        String SQL = "select *from movimiento where cuenta_num_cuenta = ?;";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, cuenta.getNumCuenta());

            ResultSet resultado = st.executeQuery();

            Movimiento m;
            while (resultado.next()) {
                m = new Movimiento();
                m.setId_movimiento(resultado.getInt("id_movimiento"));
                m.setMonto(resultado.getDouble("monto"));
                m.setFecha(resultado.getDate("fecha"));
                m.setAplicado(resultado.getShort("aplicado"));
                m.setMotivo(resultado.getString("motivo"));
                m.setNombre_depositante(resultado.getString("nombre_depositante"));
                m.setCuenta(cuenta);
                cuenta.getMovimientoCollection().add(m);
            }
            con.close();
            st.close();
            resultado.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void llenarTransferencias(Cuenta cuenta) {
        String SQL = "select * from transferencia where cuenta_destino=?";

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, cuenta.getNumCuenta());

            ResultSet resultado = st.executeQuery();

            Transferencia t;
            while (resultado.next()) {
                Cuenta cuentaDest = new Cuenta();
                t = new Transferencia();
                t.setId_transferencia(resultado.getInt("id_transferencia"));
                t.setMonto(resultado.getString("monto"));
                t.setFecha(resultado.getDate("fecha"));
                t.setAplicado(resultado.getShort("aplicado"));
                t.setCuenta(cuenta);
                cuentaDest.setNumCuenta(resultado.getInt("cuenta_destino"));
                t.setCuenta_Destino(cuentaDest);
                cuenta.getTransferenciaCollection().add(t);
            }
            con.close();
            st.close();
            resultado.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void updateSaldo(Cuenta cuenta) {
        String SQL = "UPDATE cuenta SET saldo_final = ? ,limite_transferencia_diaria = ? WHERE num_cuenta = ?;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setString(1, String.valueOf(cuenta.getSaldoFinal()));
            st.setString(2, String.valueOf(cuenta.getLimiteTransferenciaDiaria()));
            st.setInt(3, cuenta.getNumCuenta());
            st.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
