package banco.data;

import banco.logica.TipoCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván
 */
public class TipoCuentaDao {

    public static boolean registrar(TipoCuenta tipoCuenta) throws Exception {
        String SQL = "insert into tipo_cuenta (id_tipo_cuenta, descripción, tasa_interés) "
                + "values (?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setInt(1, tipoCuenta.getIdTipoCuenta());
            st.setString(2, tipoCuenta.getDescripción());
            st.setDouble(3, tipoCuenta.getTasaInterés());

            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static TipoCuenta find(Integer id_tipo_cuenta) throws Exception {
        String SQL = "select * from tipo_cuenta where id_tipo_cuenta=?;";
        TipoCuenta tipoCuenta = new TipoCuenta();

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setInt(1, id_tipo_cuenta);
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {

                tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
                tipoCuenta.setDescripción(resultado.getString("descripción"));
                tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));

            }
            con.close();
            resultado.close();
            st.close();
            return tipoCuenta;

        } catch (SQLException ex) {
            System.out.println(ex);
            return tipoCuenta;
        }
    }

    public static TipoCuenta creaTipoCuenta(ResultSet resultado) throws SQLException {
        TipoCuenta tipoCuenta = new TipoCuenta();

        tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
        tipoCuenta.setDescripción(resultado.getString("descripción"));
        tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));

        return tipoCuenta;
    }

    public static ArrayList<TipoCuenta> listar() throws Exception {
        String SQL = "select * from tipo_cuenta;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<TipoCuenta> lista = new ArrayList<TipoCuenta>();
            TipoCuenta tipoCuenta;

            while (resultado.next()) {
                tipoCuenta = new TipoCuenta();

                tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
                tipoCuenta.setDescripción(resultado.getString("descripción"));
                tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));

                lista.add(tipoCuenta);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
