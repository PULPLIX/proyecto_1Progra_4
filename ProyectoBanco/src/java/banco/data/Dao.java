package banco.data;

//package personas.datos;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import personas.logica.Persona;
//
//public class Dao {
//    RelDatabase db;
//    
//    public Dao(){
//        db= new RelDatabase();
//    }
//
//    public Persona PersonaGet(String id) throws Exception{
//        String sql="select * "+
//                "from persona p "+
//                "where p.id='%s'";
//        sql = String.format(sql,id);
//        ResultSet rs =  db.executeQuery(sql);
//        if (rs.next()) {
//            return persona(rs);
//        }
//        else{
//            throw new Exception ("Persona no Existe");
//        }
//    }
//    
//    public void PersonaDelete(Persona p) throws Exception{
//        String sql="delete from persona where id='%s'";
//        sql = String.format(sql,p.getId());
//        int count=db.executeUpdate(sql);
//        if (count==0){
//            throw new Exception("Persona no existe");
//        }
//    }
//
//    public void PersonaAdd(Persona p) throws Exception{
//        String sql="insert into Persona (id, nombre, sexo, estado_codigo) "+
//                "values('%s','%s','%s','%s')";
//        sql=String.format(sql,p.getId(),p.getNombre(),p.getSexo(),"001");
//        int count=db.executeUpdate(sql);
//        if (count==0){
//            throw new Exception("Persona ya existe");
//        }
//    }
//
//    public void PersonaUpdate(Persona p) throws Exception{
//        String sql="update persona set nombre='%s',sexo='%s' "+
//                "where id='%s'";
//        sql=String.format(sql,p.getNombre(),p.getSexo(),p.getId());
//        
//        int count=db.executeUpdate(sql);
//        if (count==0){
//            throw new Exception("Persona no existe");
//        }
//    }
//    
//    public List<Persona> PersonaSearch(String nombre){
//        List<Persona> resultado = new ArrayList<Persona>();
//        try {
//            String sql="select * from "+
//                    "persona p "+
//                    "where p.nombre like '%%%s%%'";
//            sql=String.format(sql,nombre);
//            ResultSet rs =  db.executeQuery(sql);
//            while (rs.next()) {
//                resultado.add(persona(rs));
//            }
//        } catch (SQLException ex) { }
//        return resultado;
//    }
//    private Persona persona(ResultSet rs){
//        try {
//            Persona p= new Persona();
//            p.setId(rs.getString("id"));
//            p.setNombre(rs.getString("nombre"));
//            p.setSexo(rs.getString("sexo").charAt(0));
//            return p;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
//   public  void close(){
//    }
//}
