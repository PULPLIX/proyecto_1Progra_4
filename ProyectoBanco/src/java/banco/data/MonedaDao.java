package banco.data;

import banco.logica.Moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván
 */
public class MonedaDao {

    public static boolean registrar(Moneda moneda) throws Exception {
        String SQL = "insert into moneda (nombre, descripcion, simbolo, tipo_cambio_compra, tipo_cambio_venta, tasa_intereses) "
                + "values (?,?,?,?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setInt(1, moneda.getNombre());
            st.setString(2, moneda.getDescripcion());
            st.setString(3, moneda.getSimbolo());
            st.setDouble(4, moneda.getTipoCambioCompra());
            st.setDouble(5, moneda.getTipoCambioVenta());
            st.setDouble(6, moneda.getTasaIntereses());

            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Moneda find(Integer nombre) throws Exception {
        String SQL = "select * from moneda where nombre=?;";
        Moneda moneda = new Moneda();

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setInt(1, nombre);
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {

                moneda.setNombre(resultado.getInt("nombre"));
                moneda.setSimbolo(resultado.getString("simbolo"));
                moneda.setDescripcion(resultado.getString("descripcion"));
                moneda.setTipoCambioCompra(resultado.getDouble("tipo_cambio_compra"));
                moneda.setTipoCambioVenta(resultado.getDouble("tipo_cambio_venta"));
                moneda.setTasaIntereses(resultado.getDouble("tasa_intereses"));

            }
            con.close();
            resultado.close();
            st.close();
            return moneda;

        } catch (SQLException ex) {
            System.out.println(ex);
            return moneda;
        }
    }

    public static Moneda creaMoneda(ResultSet resultado) throws SQLException {
        Moneda moneda = new Moneda();

        moneda.setNombre(resultado.getInt("nombre"));
        moneda.setSimbolo(resultado.getString("simbolo"));
        moneda.setDescripcion(resultado.getString("descripcion"));
        moneda.setTipoCambioCompra(resultado.getDouble("tipo_cambio_compra"));
        moneda.setTipoCambioVenta(resultado.getDouble("tipo_cambio_venta"));
        moneda.setTasaIntereses(resultado.getDouble("tasa_intereses"));

        return moneda;
    }

    public static ArrayList<Moneda> listar() throws Exception {
        String SQL = "select * from moneda;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Moneda> lista = new ArrayList<>();
            Moneda moneda;

            while (resultado.next()) {
                moneda = new Moneda();

                moneda.setNombre(resultado.getInt("nombre"));
                moneda.setSimbolo(resultado.getString("simbolo"));
                moneda.setDescripcion(resultado.getString("descripcion"));
                moneda.setTipoCambioCompra(resultado.getDouble("tipo_cambio_compra"));
                moneda.setTipoCambioVenta(resultado.getDouble("tipo_cambio_venta"));
                moneda.setTasaIntereses(resultado.getDouble("tasa_interes"));

                lista.add(moneda);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
