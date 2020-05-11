package videoclubzgui;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class SistemaGUI extends javax.swing.JFrame {

    public static Connection cnx = null;
    public static int iP=0;
    public static int iS=0;
    public static int agregarPel[] = new int[100];
    public static int agregarSer[] = new int[100];
    //Declaracion de datos de conexión a BD
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="12345";
    private static final String url="jdbc:mysql://localhost:3306/videoclubz?useTimezone=true&serverTimezone=UTC";
    
    //Modelo para listas
    DefaultListModel dm = new DefaultListModel();
    DefaultListModel dmPrestamo = new DefaultListModel();
    DefaultListModel dmVenta = new DefaultListModel();
    
    //Variables globales para actualizar campos
    //Clientes
    String tmpRFCClient, tmpRFCEmp;
    
    public void addListVenta(String toAddID, boolean esPelicula){
        listaVenta.setModel(dmVenta);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        if(esPelicula){
            try {
                stmt = cnx.createStatement();
                query = "SELECT * FROM peliculas WHERE id_pelicula = " + toAddID + "";

                if(stmt.execute(query)){
                    rs = stmt.getResultSet();
                }
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "No esta la  pelicula");
                }
                else{
                    dmVenta.addElement(rs.getString("titulo_pel"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        else{
            stmt = null;
            rs = null;

            try {
                stmt = cnx.createStatement();
                query = "SELECT * FROM series WHERE id_serie= " + toAddID + "";

                if(stmt.execute(query)){
                    rs = stmt.getResultSet();
                }
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "No esta la serie");
                }
                else{
                    dmVenta.addElement(rs.getString("titulo_ser"));
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
    }
    
    public void addListPrestamo(String toAddID, boolean esPelicula){
        listaPrestamos.setModel(dmPrestamo);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        if(esPelicula){
            try {
                stmt = cnx.createStatement();
                query = "SELECT * FROM peliculas WHERE id_pelicula = " + toAddID + "";

                if(stmt.execute(query)){
                    rs = stmt.getResultSet();
                }
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "No esta la  pelicula");
                }
                else{
                    dmPrestamo.addElement(rs.getString("titulo_pel"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        else{
            stmt = null;
            rs = null;

            try {
                stmt = cnx.createStatement();
                query = "SELECT * FROM series WHERE id_serie= " + toAddID + "";

                if(stmt.execute(query)){
                    rs = stmt.getResultSet();
                }
                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "No esta la serie");
                }
                else{
                    dmPrestamo.addElement(rs.getString("titulo_ser"));
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
    }
    
    public boolean checkExist(int choiceVP,int choiceSP, String idToSearch){
        //ChoiceSP 1 = peliculas, 2 = series
        //choiceVP 1 = Venta, 2 = Prestamo
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        //Variables para revisar existencia
        int verExistencia = 0;
        
        try {
            stmt = cnx.createStatement();
            //Revisar si existe
            if(choiceSP==1){
                query = "SELECT * FROM peliculas WHERE id_pelicula = '"+idToSearch+"'";
            }
            else{
                query = "SELECT * FROM series WHERE id_series = '"+idToSearch+"'";
            }
                
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                //Avisar si no existe
                if(choiceSP==1){
                    JOptionPane.showMessageDialog(this, "No se encontro la pelicula");
                    return false;
                }
                else{
                    JOptionPane.showMessageDialog(this, "No se encontro la serie");
                    return false;
                }
                    
            }else{
                do{
                    if(choiceVP==1){//Revisar la existencia venta
                        if(choiceSP==1){//Revisar existencia de pelicula
                            verExistencia = Integer.parseInt(rs.getString("existencias_ven_pel"));
                            
                            if(verExistencia>=1)
                                return true;
                            else{
                                JOptionPane.showMessageDialog(this, "No hay existencias de la pelicula");
                                return false;
                            }
                        }
                        else{//Revisar existencia serie
                            
                        }
                    }
                    else{ //Revisar existencia prestamo
                        
                    }
                    
                }while(rs.next());
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        return false;
    }
    
    public void estanteriaPeliculas(){
        dm.clear();
        listaEstanterias.setModel(dm);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        Statement stmtPel = null;
        ResultSet rsPel = null;
        String queryPel;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM estanteria WHERE tipo = '" + "Peliculas" + "'";
            rs = stmt.executeQuery(query);
            
            stmtPel = cnx.createStatement();
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "Sin estanterias");
            }else{
                do{
                    dm.addElement(rs.getString("num_estant") + "-------------------------" + rs.getString("rango_letras"));
                    
                    //Para agregar peliculas
                    queryPel = "SELECT * FROM peliculas WHERE num_estant = " + rs.getString("num_estant") +"";
                    if(stmtPel.execute(queryPel)){
                        rsPel = stmtPel.getResultSet();
                    }
                    while(rsPel.next()){
                        dm.addElement("ID: " + rsPel.getString("id_pelicula"));
                        dm.addElement("Nombre: " + rsPel.getString("titulo_pel"));
                        dm.addElement("Director: " + rsPel.getString("director_pel"));
                        dm.addElement("Año Lanzamiento: " + rsPel.getString("anio_pel"));
                        dm.addElement("Genero: " + rsPel.getString("genero_pel"));
                        dm.addElement("Costo: " + rsPel.getString("costo_pel"));
                        dm.addElement("Existencia para venta: " + rsPel.getString("existencias_ven_pel"));
                        dm.addElement("Existencia para renta: " + rsPel.getString("existencias_pres_pel"));
                        dm.addElement("\n");
                    }
                    
                    dm.addElement("-------------------------------------");
                }while(rs.next());
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
    }
    
    public void estanteriaSeries(){
        dm.clear();
        listaEstanterias.setModel(dm);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        Statement stmtSer = null;
        ResultSet rsSer = null;
        String querySer;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM estanteria WHERE tipo = '" + "Series" + "'";
            rs = stmt.executeQuery(query);
            
            stmtSer = cnx.createStatement();
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "Sin estanterias");
            }else{
                do{
                    dm.addElement(rs.getString("num_estant") + "--------------------------------" + rs.getString("rango_letras"));
                    
                    //Para agregar peliculas
                    querySer = "SELECT * FROM series WHERE num_estant = " + rs.getString("num_estant") +"";
                    if(stmtSer.execute(querySer)){
                        rsSer = stmtSer.getResultSet();
                    }
                    while(rsSer.next()){
                        dm.addElement("ID: " + rsSer.getString("id_serie"));
                        dm.addElement("Nombre: " + rsSer.getString("titulo_ser"));
                        dm.addElement("Genero: " + rsSer.getString("genero_ser"));
                        dm.addElement("Año Lanzamiento: " + rsSer.getString("anio_temp"));
                        dm.addElement("Temporada: " + rsSer.getString("num_temp"));
                        dm.addElement("Capitulos: " + rsSer.getString("num_caps"));
                        dm.addElement("Costo: " + rsSer.getString("costo_ser"));
                        dm.addElement("Existencias para prestamo: " + rsSer.getString("existencias_pres_ser"));
                        dm.addElement("Existencias para ventas: " + rsSer.getString("existencias_ven_ser"));
                        dm.addElement("\n");
                    }
                    
                    dm.addElement("-------------------------------------------");
                }while(rs.next());
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE); 
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
    }
    
    public void checkQueryEmp(String idAux){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM empleados WHERE id_empleado = '"+idAux+"'";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
           while(rs.next()){
                aux = true;
                txtIdEmp.setText(rs.getString("id_empleado"));
                textNomEmp.setText(rs.getString("nombres_emp"));
                txtPrimerApeEmp.setText(rs.getString("apellido1_emp"));
                txtSegundoApeEmp.setText(rs.getString("apellido2_emp"));
                comboEmp.setSelectedItem(rs.getString("puesto"));
                txtTelefonoEmp.setText(rs.getString("telefono_emp"));
                txtSalarioEmp.setText(rs.getString("salario"));
                txtHorarioEmp.setText(rs.getString("horario_emp"));

                //Actualizar botones
                btnActualizarEmp.setVisible(true);
                btnCancelarEmp.setVisible(true);
                btnRegistrarEmp.setVisible(false);
                btnBuscarEmp.setVisible(false);

                //Asignar valor a variable temporal
                tmpRFCEmp = rs.getString("id_empleado");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(!aux){
            JOptionPane.showMessageDialog(this, "No se encontro el empleado.");
        }
    } //CHECK
    
    public void checkQueryCli(String rfc){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM clientes WHERE id_cliente = '"+rfc+"'";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            while(rs.next()){
                aux= true;
                //Recoger datos y poner  en text
                txtRFCClient.setText(rs.getString("id_cliente"));
                txtNomClient.setText(rs.getString("nombres_cliente"));
                txtPrimerApeClient.setText(rs.getString("apellido1_cliente"));
                txtSegundoApeClient.setText(rs.getString("apellido2_cliente"));
                txtDirClient.setText (rs.getString("direccion_cliente"));
                txtTelefonoClient.setText(rs.getString("telefono_cliente"));
                txtSaldoClient.setText(rs.getString("saldo_pendiente"));

                //Asignar valores a variables temp
                tmpRFCClient = rs.getString("id_cliente");

                //Actualizar botones
                btnActualizarClient.setVisible(true);
                btnCancelarClient.setVisible(true);
                btnRegistrarClient.setVisible(false);
                btnBuscarClient.setVisible(false);
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(!aux){
            JOptionPane.showMessageDialog(this, "No se encontro el cliente.");
        }
    } //CHECK
    
    public void checkQueryPeliculas(String titulo){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM peliculas WHERE titulo_pel = '"+titulo+"'";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "No se encontro la pelicula");
            }else{
                do{
                    txtTituloPel.setText(rs.getString("titulo_pel"));
                    txtDirectorPel.setText(rs.getString("director_pel"));
                    txtAnioPel.setText(rs.getString("anio_pel"));
                    txtEstanteriaPel.setText(rs.getString("num_estant"));
                    txtCostoPel.setText(rs.getString("costo_pel"));
                    txtExistenciaPresPel.setText(rs.getString("existencias_pres_pel"));
                    txtExistenciaVenPel.setText(rs.getString("existencias_ven_pel"));
                    comboGenero.setSelectedItem(rs.getString("genero_pel"));
                }while(rs.next());
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
    } //CHECK
    
    public void checkQuerySeries(String titulo){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM series WHERE titulo_ser = '"+titulo+"'";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "No se encontro la serie");
            }else{
                do{
                    txtTituloSer.setText(rs.getString("titulo_ser"));
                    comboGeneroSer.setSelectedItem(rs.getString("genero_ser"));
                    txtTempAnioSer.setText(rs.getString("anio_temp"));
                    txtEpisodiosSer.setText(rs.getString("num_caps"));
                    txtTempSer.setText(rs.getString("num_temp"));
                    txtCostoSer.setText(rs.getString("costo_ser"));
                    txtExisPresSer.setText(rs.getString("existencias_pres_ser"));
                    txtExisVenSer.setText(rs.getString("existencias_ven_ser"));
                    txtEstanteriaSer.setText(rs.getString("num_estant"));
                    
                }while(rs.next());
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
    } //CHECK
    
    public void insertEmpleado(String id,String nom,String ap1,String ap2,String pues,String tel,String sala,String hora) throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;
        
        try {
            stmt = cnx.createStatement();
            query = "INSERT INTO empleados VALUES ('"+id+"','"+nom+"','"+ap1+"','"+ap2+"','"+pues+"','"+tel+"',"+sala+",'"+hora+"')";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Registro Exitoso!");
            
            //Poner textos en blanco
            txtIdEmp.setText("");
            textNomEmp.setText("");
            txtPrimerApeEmp.setText("");
            txtSegundoApeEmp.setText("");
            comboEmp.setSelectedItem("");
            txtTelefonoEmp.setText("");
            txtSalarioEmp.setText("");
            txtHorarioEmp.setText("");
        }
    } //CHECK
    
    public void updateCliente(String rfc, String nom,String ap1,String ap2,String dir,String tel,Integer saldo){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();
            query = "update clientes set nombres_cliente = '"+ nom+"', apellido1_cliente = '" + ap1 + "', apellido2_cliente = '" + ap2 + "', direccion_cliente = '" + dir + "', telefono_cliente = '" + tel + "', saldo_pendiente = " + saldo + " where id_cliente = '" + rfc + "'";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Actualización Exitosa!");
        }
    } //CHECK
    
    public void updateEmp(String id,String nom,String ap1,String ap2,String pues,String tel,int sala,String hora){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();
            query = "update empleados set nombres_emp = '" + nom + "', apellido1_emp  = '" + ap1 + "', apellido2_emp = '" + ap2 + "', puesto = '"+ pues + "', telefono_emp = '" + tel + "', salario = " + sala + ", horario_emp = '" + hora + "' where id_empleado = '" + id + "'";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Actualización Exitosa!");
        }
    } //CHECK
    
    public void insertCliente(String rfc,String nom,String ap1,String ap2,String dir,String tel,Integer saldo){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();
            query = "INSERT INTO clientes VALUES ('"+rfc+"','"+nom+"','"+ap1+"','"+ap2+"','"+dir+"','"+tel+"',"+saldo+")";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Registro Exitoso!");
            
            //Poner textos en blanco
            txtRFCClient.setText("");
            txtNomClient.setText("");
            txtPrimerApeClient.setText("");
            txtSegundoApeClient.setText("");
            txtDirClient.setText ("");
            txtTelefonoClient.setText("");
            txtSaldoClient.setText("");
        }
    } //CHECK
    
    public void insertPelicula(String titulo,String director,String genero,int anio,int costo,int existencia_pres, int existencia_vent, int estante){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();
            query = "INSERT INTO peliculas(anio_pel, titulo_pel, genero_pel, costo_pel, director_pel, existencias_pres_pel, existencias_ven_pel, num_estant) VALUES ("+anio+",'"+titulo+"','"+genero+"',"+costo+",'"+director+"',"+existencia_pres+","+existencia_vent+","+estante+")";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Registro Exitoso!");
            
            //Poner textos en blanco
            txtTituloPel.setText("");
            txtDirectorPel.setText("");
            txtAnioPel.setText("");
            txtEstanteriaPel.setText("");
            txtCostoPel.setText("");
            txtExistenciaPresPel.setText("");
            txtExistenciaVenPel.setText("");
            comboGenero.setSelectedItem("");
        }
    } //CHECK
    
    public void insertSerie(String titulo,String genero,int numTemp,int anioTemp,int numCaps, int costo, int existenciaPresSer, int existenciaVenSer, int estanteria){
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();
            query = "INSERT INTO series(num_temp, anio_temp, num_caps, costo_ser, titulo_ser, genero_ser, existencias_pres_ser, existencias_ven_ser, num_estant) VALUES ("+numTemp+","+anioTemp+","+numCaps+","+costo+",'"+titulo+"','"+genero+"',"+existenciaPresSer+","+existenciaVenSer+ "," + estanteria +")";
            stmt.execute(query);
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Registro Exitoso!");
            
            //Poner textos en blanco
            txtTituloSer.setText("");
            comboGeneroSer.setSelectedItem("");
            txtTempAnioSer.setText("");
            txtEpisodiosSer.setText("");
            txtTempSer.setText("");
            txtCostoSer.setText("");
            txtExisPresSer.setText("");
            txtExisVenSer.setText("");
            txtEstanteriaSer.setText("");
        }
    } //CHECK
    
    public void comprar(String pago, String costo, String id_cli, String id_emp){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        Integer id_venta=0;
        
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = String.valueOf(anio) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia);   
        System.out.println(agregarPel[0]);
        System.out.println(agregarSer[0]);
        if(agregarSer[0]>0 || agregarPel[0]>0){
            try {
                txtFechaVen.setText(fechaActual);
                stmt = cnx.createStatement();
                query = "insert into venta (fecha_ven, metodo_pago_ven, costo_total, id_cliente, id_empleado) values('"+fechaActual+"', '"+pago+"', "+Integer.parseInt(costo)+", '"+id_cli+"', '"+id_emp+"')";
                stmt.execute(query);
                query2 = "select id_venta from venta where  fecha_ven = '"+fechaActual+"' and metodo_pago_ven = '"+pago+"' and costo_total = "+Integer.parseInt(costo)+" and id_cliente = '"+id_cli+"' and id_empleado = '"+id_emp+"'";
                rs = stmt.executeQuery(query2);
                //rs.next(); Si falla, descomentar esta linea.
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }

                if(rs.next()){
                    id_venta = Integer.parseInt(rs.getString("id_venta"));
                }
                if(agregarPel[0]>0){
                    comprarPel(id_venta);
                }
                if(agregarSer[0]>0){
                    comprarSer(id_venta);
                }
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        else{
            System.out.println("No se puede hacer venta porque no se agregaron articulos");
        } 
    }
    
    public void comprarPel(Integer id){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2, query3; //query = Hacer update de existencias. query2 = Seleccionar las existencias para actualizar (--). query3 = Insertar nuevo registro en pelicula_venta.
        Integer existencias = 0;
        System.out.println("Entra a Peliculas");
        int i=0;
        for(i=0; i<iP; i++){
            try {
                stmt = cnx.createStatement();
                query2 = "select existencias_ven_pel from peliculas where id_pelicula = "+agregarPel[i]+"";
                rs = stmt.executeQuery(query2);
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }
                if(rs.next()){
                    existencias = Integer.parseInt(rs.getString("existencias_ven_pel"));
                }
                
                existencias--;
                query = "update peliculas set existencias_ven_pel = "+existencias+" where id_pelicula = "+agregarPel[i]+"";
                stmt.execute(query);
                
                query3 = "insert into pelicula_venta values ("+agregarPel[i]+", "+id+")";
                stmt.execute(query3);
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        if(i==iP){
            for(int x=0; x<100; x++){
                agregarPel[x] = -1;
            }
            iP=0;
        }
    }
    
    public void comprarSer(Integer id){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2, query3; //query = Hacer update de existencias. query2 = Seleccionar las existencias para actualizar (--). query3 = Insertar nuevo registro en serie_venta.
        Integer existencias= 0;
        System.out.println("Entra a series");
        int i=0;
        for(i=0; i<iS; i++){
            try {
                stmt = cnx.createStatement();
                query2 = "select existencias_ven_ser from series where id_serie = "+Long.valueOf(agregarSer[i])+"";
                rs = stmt.executeQuery(query2);
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }
                if(rs.next()){
                    existencias = Integer.parseInt(rs.getString("existencias_ven_ser"));
                }
                
                existencias--;
                query = "update series set existencias_ven_ser = "+existencias+" where id_serie = "+Long.valueOf(agregarSer[i])+"";
                stmt.execute(query);
                
                query3 = "insert into serie_venta values ("+agregarSer[i]+", "+id+")";
                stmt.execute(query3);
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        if(i==iS){
            for(int x=0; x<100; x++){
                agregarSer[x] = -1;
            }
            iS=0;
        }
    }
    
    public void prestamo(String fechaEst, String pago, String id_cli, Integer costo){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        Integer id_prest=0;

        System.out.println(agregarPel[0]);
        System.out.println(agregarSer[0]);
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = String.valueOf(anio) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia);
        if(agregarSer[0]>0 || agregarPel[0]>0){
            try {
                stmt = cnx.createStatement();
                query = "insert into prestamo (fecha_entrega_est, fecha_inicio, metodo_pago_pres,id_cliente,costo_dia) values('"+fechaEst+"', '"+fechaActual+"', '"+pago+"', '"+id_cli+"', "+costo+")";
                stmt.execute(query);
                query2 = "select id_prestamo from prestamo where  fecha_inicio = '"+fechaActual+"' and metodo_pago_pres = '"+pago+"' and fecha_entrega_est = '"+fechaEst+"' and id_cliente = '"+id_cli+"' and costo_dia = "+costo+"";
                rs = stmt.executeQuery(query2);
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }

                if(rs.next()){
                    id_prest = Integer.parseInt(rs.getString("id_prestamo"));
                }
                if(agregarPel[0]>0){
                    prestarPel(id_prest);
                }
                if(agregarSer[0]>0){
                    prestarSer(id_prest);
                }
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        else{
            System.out.println("No se puede hacer venta porque no se agregaron articulos");
        }
    }
    
    public void prestarPel (Integer id){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2, query3; //query = Hacer update de existencias. query2 = Seleccionar las existencias para actualizar (--). query3 = Insertar nuevo registro en serie_prestamo.
        Integer existencias = 0;
        System.out.println("Entra a Peliculas");
        int i=0;
        for(i=0; i<iP; i++){
            try {
                stmt = cnx.createStatement();
                query2 = "select existencias_pres_pel from peliculas where id_pelicula = "+agregarPel[i]+"";
                rs = stmt.executeQuery(query2);
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }
                if(rs.next()){
                    existencias = Integer.parseInt(rs.getString("existencias_pres_pel"));
                }
                
                existencias--;
                query = "update peliculas set existencias_pres_pel = "+existencias+" where id_pelicula = "+agregarPel[i]+"";
                stmt.execute(query);
                
                query3 = "insert into pelicula_prestamo values ("+agregarPel[i]+", "+id+")";
                stmt.execute(query3);
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error en el prestamo de peliculas.", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        if(i==iP){
            for(int x=0; x<100; x++){
                agregarPel[x] = -1;
            }
            iP=0;
        }
    }
    
    public void prestarSer(Integer id){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2, query3; //query = Hacer update de existencias. query2 = Seleccionar las existencias para actualizar (--). query3 = Insertar nuevo registro en serie_prestamo.
        Integer existencias= 0;
        System.out.println("Entra a series");
        int i=0;
        for(i=0; i<iS; i++){
            try {
                stmt = cnx.createStatement();
                query2 = "select existencias_pres_ser from series where id_serie = "+Long.valueOf(agregarSer[i])+"";
                rs = stmt.executeQuery(query2);
                
                if(stmt.execute(query2)){
                    rs = stmt.getResultSet();
                }
                if(rs.next()){
                    existencias = Integer.parseInt(rs.getString("existencias_pres_ser"));
                }
                
                existencias--;
                query = "update series set existencias_pres_ser = "+existencias+" where id_serie = "+Long.valueOf(agregarSer[i])+"";
                stmt.execute(query);
                
                query3 = "insert into serie_prestamo values ("+agregarSer[i]+", "+id+")";
                stmt.execute(query3);
                
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
                /*System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());*/
            }
            finally{
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                    rs = null;
                }
                if(stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {}
                    stmt = null;
                }
            }
        }
        if(i>=iS){
            for(int x=0; x<100; x++){
                agregarSer[x] = -1;
            }
            iS=0;
        }
    }
    
    public void actualizarPel(String titulo,String director,String genero,int anio,int costo,int existencia_pres, int existencia_vent, int estante){  //CHECK
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        int idAux=-1;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();           
            query2 = "SELECT * FROM peliculas WHERE titulo_pel = '"+titulo+"'";
            rs = stmt.executeQuery(query2);
            
            if(stmt.execute(query2)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "No se encontro la pelicula");
            }
            else{
                idAux = Integer.parseInt(rs.getString("id_pelicula"));
            }
            
            query = "update peliculas set id_pelicula = "+ idAux+", anio_pel = " + anio + ", titulo_pel = '" + titulo + "', genero_pel = '" + genero + "', costo_pel = " + costo + ", director_pel = '" + director + "', existencias_pres_pel = "+existencia_pres+", existencias_ven_pel = "+existencia_vent+", num_estant = "+estante+" where id_pelicula = " + idAux + "";
            stmt.execute(query);
            
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Actualización Exitosa!");
        }
    } //CHECK
    
    public void actualizarSer(String titulo,String genero,int numTemp,int anioTemp,int numCaps, int costo, int existenciaPresSer, int existenciaVenSer, int estanteria){
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        int idAux=-1;
        Boolean aux = false;

        try {
            stmt = cnx.createStatement();           
            query2 = "SELECT * FROM series WHERE titulo_ser = '"+titulo+"'";
            rs = stmt.executeQuery(query2);
            
            if(stmt.execute(query2)){
                rs = stmt.getResultSet();
            }
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(this, "No se encontro la pelicula");
            }
            else{
                idAux = Integer.parseInt(rs.getString("id_serie"));
            }
            
            query = "update series set id_serie = "+ idAux+", num_temp = " + numTemp + ", anio_temp = " + anioTemp + ", num_caps = " + numCaps + ", costo_ser = " + costo + ", titulo_ser = '" + titulo + "', genero_ser = '"+genero+"', existencias_pres_ser = "+existenciaPresSer+", existencias_ven_ser = "+existenciaVenSer+", num_estant = "+estanteria+" where id_serie = " + idAux + "";
            stmt.execute(query);
            
            aux = true;
        } catch(SQLException ex) {
            aux = false;
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(aux){
            JOptionPane.showMessageDialog(this, "Actualización Exitosa!");
        }
    }  //CHECK
    
    public void listaPrestamo(int idToCheck){//Revisar que se presto para mostrarlo en la lista
        listaPrestamos.setModel(dmPrestamo);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        Statement stmt2 = null;
        ResultSet rs2 = null;
        String query2;
        
        try {
            //Revisar si en el prestamo se llevaron series
            stmt = cnx.createStatement();
            query = "SELECT * FROM serie_prestamo WHERE id_prestamo = " + idToCheck + ";";
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            
            while(rs.next()){
               stmt2 = cnx.createStatement();
               query2 = "SELECT * FROM series WHERE id_serie = " + rs.getString("id_serie") + ";";
               
               
                if(stmt2.execute(query2)){
                    rs2 = stmt2.getResultSet();
                }
                
                while(rs2.next()){
                    dmPrestamo.addElement(rs2.getString("titulo_ser"));
                }
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        
        
        stmt = null;
        rs = null;
        query = null;
        
        stmt2 = null;
        rs2 = null;
        query2 = null;
        
        try {
            //Revisar si en el prestamo se llevaron peliculas
            stmt = cnx.createStatement();
            query = "SELECT * FROM pelicula_prestamo WHERE id_prestamo = " + idToCheck + ";";
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            
            while(rs.next()){
               stmt2 = cnx.createStatement();
               query2 = "SELECT * FROM peliculas WHERE id_pelicula = " + rs.getString("id_pelicula") + ";";
               
               
                if(stmt2.execute(query2)){
                    rs2 = stmt2.getResultSet();
                }
                
                while(rs2.next()){
                    dmPrestamo.addElement(rs2.getString("titulo_pel"));
                }
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        
    }
    
    public void checkQueryPres(Integer id) throws ParseException{
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        Boolean aux = false;
        
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM prestamo WHERE id_prestamo = "+id+"";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            while(rs.next()){
                aux= true;
                if(rs.getString("fecha_entrega") != null){ // En caso de ya estar pagado 
                    String fechaEntrega = rs.getString("fecha_entrega");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //Creamos una instancia nueva, para convertir una cadena a Date.

                    java.util.Date fechaEst;
                    java.util.Date fechaInicio;
                    java.util.Date fechaEntregaReal=dateFormat.parse(fechaEntrega);  //Se convierte de SimpleDateFormat, a Date
                    
                    //Verificar que no se paso de la fecha de entrega
                    fechaEst=dateFormat.parse(rs.getString("fecha_entrega_est"));
                    fechaInicio=dateFormat.parse(rs.getString("fecha_inicio"));
                    //Con el metodo getTime() conseguimos la fecha en milisegundos, y despues las convertimos a dias.
                    int diasAtrasados=(int) ((fechaEntregaReal.getTime()-fechaEst.getTime())/86400000);  //Obtenemos los días atrasados.
                    int dias = (int) ((fechaEntregaReal.getTime() - fechaInicio.getTime())/86400000); //Obtenemos cuantos días tardo en entregar
                    int costoDia = Integer.parseInt(rs.getString("costo_dia"));
                    int total=0;
                    if(diasAtrasados>0){
                        total = costoDia * (dias-diasAtrasados);
                        total += (costoDia*2) * (diasAtrasados);
                    }
                    else{
                        total = costoDia * dias;
                    }
                    
                    listaPrestamo(id);
                    txtClientePres.setText(rs.getString("id_cliente"));
                    comboPagoPres.setSelectedItem(rs.getString("metodo_pago_pres"));
                    txtFechaEstPres.setText(rs.getString("fecha_entrega_est"));
                    txtFechaInicio.setText(rs.getString("fecha_inicio"));
                    txtCostoDia.setText(rs.getString("costo_dia"));
                    lblFechaPres.setText(rs.getString("fecha_entrega"));
                    
                    lblFechaPres.setText(fechaEntrega);
                    lblCostoTotal.setText(String.valueOf(total));
                }
                else{
                    txtClientePres.setText(rs.getString("id_cliente"));
                    comboPagoPres.setSelectedItem(rs.getString("metodo_pago_pres"));
                    txtFechaEstPres.setText(rs.getString("fecha_entrega_est"));
                    txtFechaInicio.setText(rs.getString("fecha_inicio"));
                    txtCostoDia.setText(rs.getString("costo_dia"));
                    lblFechaPres.setText(rs.getString("fecha_entrega"));
                    
                    listaPrestamo(id);
                
                    lblCostoTotal.setText(String.valueOf(0));
                }
                //Recoger datos y poner  en text
                
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(!aux){
            JOptionPane.showMessageDialog(this, "No se encontro el id del prestamo.");
        }
    }
    
    public void listaVenta(int idToCheck){//Revisar que se vendio para mostrarlo en la lista
        listaVenta.setModel(dmVenta);
        
        Statement stmt = null;
        ResultSet rs = null;
        String query;
        
        Statement stmt2 = null;
        ResultSet rs2 = null;
        String query2;
        
        try {
            //Revisar si en la venta se llevaron series
            stmt = cnx.createStatement();
            query = "SELECT * FROM serie_venta WHERE id_venta = " + idToCheck + ";";
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            
            while(rs.next()){
               stmt2 = cnx.createStatement();
               query2 = "SELECT * FROM series WHERE id_serie = " + rs.getString("id_serie") + ";";
               
               
                if(stmt2.execute(query2)){
                    rs2 = stmt2.getResultSet();
                }
                
                while(rs2.next()){
                    dmVenta.addElement(rs2.getString("titulo_ser"));
                }
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        
        
        stmt = null;
        rs = null;
        query = null;
        
        stmt2 = null;
        rs2 = null;
        query2 = null;
        
        try {
            //Revisar si en la venta se llevaron peliculas
            stmt = cnx.createStatement();
            query = "SELECT * FROM pelicula_venta WHERE id_venta = " + idToCheck + ";";
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            
            while(rs.next()){
               stmt2 = cnx.createStatement();
               query2 = "SELECT * FROM peliculas WHERE id_pelicula = " + rs.getString("id_pelicula") + ";";
               
               
                if(stmt2.execute(query2)){
                    rs2 = stmt2.getResultSet();
                }
                
                while(rs2.next()){
                    dmVenta.addElement(rs2.getString("titulo_pel"));
                }
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        
    }
    
    public void checkQueryVen(Integer id)throws ParseException{
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        Boolean aux = false;
        
        
        try {
            stmt = cnx.createStatement();
            query = "SELECT * FROM venta WHERE id_venta = "+id+"";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            while(rs.next()){
                aux= true;
                txtRFCClientVen.setText(rs.getString("id_cliente"));
                txtRFCEmpVen.setText(rs.getString("id_empleado"));
                txtFechaVen.setText(rs.getString("fecha_ven"));
                comboPagoVen.setSelectedItem(rs.getString("metodo_pago_ven"));
                txtCostoVen.setText (rs.getString("costo_total"));
                    
                listaVenta(id);   
                }
                //Recoger datos y poner  en text
                
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(!aux){
            JOptionPane.showMessageDialog(this, "No se encontro el id de la venta.");
        }
        
    }
    
    public void devolverPrestamo(Integer id) throws ParseException{
        Statement stmt = null;
        ResultSet rs = null;
        String query, query2;
        Boolean aux = false;
        
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaEntrega = String.valueOf(anio) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //Creamos una instancia nueva, para convertir una cadena a Date.
 
	java.util.Date fechaEst;
        java.util.Date fechaInicio;
	java.util.Date fechaEntregaReal=dateFormat.parse(fechaEntrega);  //Se convierte de SimpleDateFormat, a Date
        
        try {
            stmt = cnx.createStatement();
            query2 = "update prestamo set fecha_entrega = '"+fechaEntrega+"' where id_prestamo = "+id+"";
            stmt.execute(query2);
            
            query = "SELECT * FROM prestamo WHERE id_prestamo = "+id+"";
            rs = stmt.executeQuery(query);
            
            if(stmt.execute(query)){
                rs = stmt.getResultSet();
            }
            
            while(rs.next()){
                //Verificar que no se paso de la fecha de entrega
                fechaEst=dateFormat.parse(rs.getString("fecha_entrega_est"));
                fechaInicio=dateFormat.parse(rs.getString("fecha_inicio"));
                //Con el metodo getTime() conseguimos la fecha en milisegundos, y despues las convertimos a dias.
                int diasAtrasados=(int) ((fechaEntregaReal.getTime()-fechaEst.getTime())/86400000);  //Obtenemos los días atrasados.
                int dias = (int) ((fechaEntregaReal.getTime() - fechaInicio.getTime())/86400000); //Obtenemos cuantos días tardo en entregar
                int costoDia = Integer.parseInt(rs.getString("costo_dia"));
                int total=0;
                if(diasAtrasados>0){
                    total = costoDia * (dias-diasAtrasados);
                    total += (costoDia*2) * (diasAtrasados);
                }
                else{
                    total = costoDia * dias;
                }
                aux= true;
                //Recoger datos y poner  en text;
                lblFechaPres.setText(fechaEntrega);
                lblCostoTotal.setText(String.valueOf(total));
            }
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage() + "\n" + "SQLState: " + ex.getSQLState() + "\n" + "VendorError: " + ex.getErrorCode(), "Error al registrar", JOptionPane.WARNING_MESSAGE);
            /*System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());*/
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {}
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
        if(!aux){
            JOptionPane.showMessageDialog(this, "No se encontro el id del prestamo.");
        }
    }

    public SistemaGUI() {
        initComponents();
    }

    public SistemaGUI(String perfil) {
        initComponents();
        
        btnActualizarClient.setVisible(false);
        btnCancelarClient.setVisible(false);
        
        btnActualizarEmp.setVisible(false);
        btnCancelarEmp.setVisible(false);
        
        btnActPel.setVisible(false);
        btnCancelPel.setVisible(false);
        
        switch(perfil){
            case "Jefe":
                panelTabs.setEnabledAt(0, true);
                panelTabs.setEnabledAt(1, true);
                panelTabs.setEnabledAt(2, true);
                panelTabs.setEnabledAt(3, true);
                panelTabs.setEnabledAt(4, true);
                panelTabs.setEnabledAt(5, true);
                break;
                
            case "Empleado":
                panelTabs.setEnabledAt(0, false);
                panelTabs.setEnabledAt(1, true);
                panelTabs.setEnabledAt(2, true);
                panelTabs.setEnabledAt(3, true);
                panelTabs.setEnabledAt(4, true);
                panelTabs.setEnabledAt(5, true);
                panelTabs.setSelectedIndex(1);
                break;
                
            default:
                break;
        }
        
        // Reseteamos a null la conexion a la bd
        cnx=null;
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            cnx= (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (cnx!=null){
                System.out.println("Conexion establecida");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            System.out.println(""+e);
        }
        for(int i=0; i<5; i++){
            agregarPel[i] = -1;
        }
        for(int i=0; i<5; i++){
            agregarSer[i] = -1;
        }
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = String.valueOf(anio) + "-" + String.valueOf(mes+1) + "-" + String.valueOf(dia); 
        txtFechaInicio.setText(fechaActual);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblRegEmpleados = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        textNomEmp = new javax.swing.JTextField();
        lblPrimerApe = new javax.swing.JLabel();
        txtPrimerApeEmp = new javax.swing.JTextField();
        lblSegundoApe = new javax.swing.JLabel();
        txtSegundoApeEmp = new javax.swing.JTextField();
        lbPuesto = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefonoEmp = new javax.swing.JTextField();
        lblSalario = new javax.swing.JLabel();
        comboEmp = new javax.swing.JComboBox<>();
        txtSalarioEmp = new javax.swing.JTextField();
        lblHorario = new javax.swing.JLabel();
        txtHorarioEmp = new javax.swing.JTextField();
        btnRegistrarEmp = new javax.swing.JButton();
        lblRFC = new javax.swing.JLabel();
        txtIdEmp = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtBuscarEmp = new javax.swing.JTextField();
        btnBuscarEmp = new javax.swing.JButton();
        btnActualizarEmp = new javax.swing.JButton();
        btnCancelarEmp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblRegClientes = new javax.swing.JLabel();
        lblNomClient = new javax.swing.JLabel();
        lblPrimerApeClient = new javax.swing.JLabel();
        lblSegundoApeClient = new javax.swing.JLabel();
        txtNomClient = new javax.swing.JTextField();
        txtPrimerApeClient = new javax.swing.JTextField();
        txtSegundoApeClient = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDirClient = new javax.swing.JTextField();
        lblSaldoP = new javax.swing.JLabel();
        lblTelefonoClient = new javax.swing.JLabel();
        txtTelefonoClient = new javax.swing.JTextField();
        txtSaldoClient = new javax.swing.JTextField();
        lblRFCClient = new javax.swing.JLabel();
        txtRFCClient = new javax.swing.JTextField();
        btnRegistrarClient = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtBuscarClient = new javax.swing.JTextField();
        btnBuscarClient = new javax.swing.JButton();
        btnActualizarClient = new javax.swing.JButton();
        btnCancelarClient = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblRegPeliculas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTituloPel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDirectorPel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAnioPel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCostoPel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtExistenciaPresPel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtExistenciaVenPel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstanteriaPel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        btnRegPel = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtBuscarPel = new javax.swing.JTextField();
        btnBuscarPel = new javax.swing.JButton();
        btnActPel = new javax.swing.JButton();
        btnCancelPel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblRegPeliculas1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtClientePres = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblFechaPres = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtFechaEstPres = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBuscarPres = new javax.swing.JTextField();
        btnPrestamo = new javax.swing.JButton();
        btnEntregarPres = new javax.swing.JButton();
        comboPagoPres = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtPelPres = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtSerPres = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JLabel();
        butAgregarPel = new javax.swing.JButton();
        butAgregarSer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPrestamos = new javax.swing.JList<>();
        btnBuscarPres = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        txtCostoDia = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        lblCostoTotal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblRegPeliculas2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTituloSer = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        comboGeneroSer = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtTempAnioSer = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCostoSer = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtEpisodiosSer = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTempSer = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtExisPresSer = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtExisVenSer = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEstanteriaSer = new javax.swing.JTextField();
        btnRegSer = new javax.swing.JButton();
        btnBuscarSer = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtBuscarSer = new javax.swing.JTextField();
        btnActSer = new javax.swing.JButton();
        btnCancelSer = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblRegPeliculas3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtRFCClientVen = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtRFCEmpVen = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPelVen = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtCostoVen = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtFechaVen = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        comboPagoVen = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtBuscarVen = new javax.swing.JTextField();
        btnBuscarVen = new javax.swing.JButton();
        btnComprarVen = new javax.swing.JButton();
        btnAgregarPel = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        txtSerieVen = new javax.swing.JTextField();
        btnAgregarSer = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaVenta = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaEstanterias = new javax.swing.JList<>();
        lblEstanterias = new javax.swing.JLabel();
        btnPelEstanteria = new javax.swing.JButton();
        btnSerEstanteria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRegEmpleados.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegEmpleados.setText("Registro de Empleados");

        lblNom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNom.setText("Nombre:");

        lblPrimerApe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPrimerApe.setText("Primer Apellido:");

        lblSegundoApe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSegundoApe.setText("Segundo Apellido:");

        lbPuesto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPuesto.setText("Puesto:");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono:");

        lblSalario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSalario.setText("Salario:");

        comboEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Gerente" }));

        lblHorario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHorario.setText("Horario:");

        btnRegistrarEmp.setText("Registrar");
        btnRegistrarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarEmpActionPerformed(evt);
            }
        });

        lblRFC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRFC.setText("Id Empleado");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Buscar:");

        btnBuscarEmp.setText("Buscar");
        btnBuscarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpActionPerformed(evt);
            }
        });

        btnActualizarEmp.setText("Actualizar");
        btnActualizarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpActionPerformed(evt);
            }
        });

        btnCancelarEmp.setText("Cancelar");
        btnCancelarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegEmpleados)
                    .addComponent(lblHorario)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancelarEmp)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscarEmp))
                                    .addComponent(txtHorarioEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarEmp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizarEmp))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnRegistrarEmp)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNom)
                                        .addComponent(lblPrimerApe)
                                        .addComponent(txtPrimerApeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSegundoApe)
                                        .addComponent(txtSegundoApeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSalarioEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSalario)
                                        .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTelefono)
                                        .addComponent(lbPuesto)
                                        .addComponent(comboEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblRFC)
                                        .addComponent(txtIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegEmpleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNom)
                    .addComponent(lbPuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrimerApe)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrimerApeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSegundoApe)
                    .addComponent(lblSalario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSegundoApeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalarioEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorario)
                    .addComponent(lblRFC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHorarioEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnRegistrarEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtBuscarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEmp)
                    .addComponent(btnActualizarEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarEmp)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        panelTabs.addTab("Empleados", jPanel1);

        lblRegClientes.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegClientes.setText("Registro de Clientes");

        lblNomClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomClient.setText("Nombre:");

        lblPrimerApeClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPrimerApeClient.setText("Primer Apellido:");

        lblSegundoApeClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSegundoApeClient.setText("Segundo Apellido:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Direccion:");

        lblSaldoP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSaldoP.setText("Saldo Pendiente:");

        lblTelefonoClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelefonoClient.setText("Telefono:");

        lblRFCClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRFCClient.setText("RFC:");

        btnRegistrarClient.setText("Registrar Cliente");
        btnRegistrarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClientActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Buscar:");

        btnBuscarClient.setText("Buscar");
        btnBuscarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientActionPerformed(evt);
            }
        });

        btnActualizarClient.setText("Actualizar");
        btnActualizarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClientActionPerformed(evt);
            }
        });

        btnCancelarClient.setText("Cancelar");
        btnCancelarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelarClient)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblRegClientes)
                                            .addComponent(txtPrimerApeClient, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPrimerApeClient)
                                            .addComponent(lblSegundoApeClient)
                                            .addComponent(jLabel1)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jLabel36)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtBuscarClient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtDirClient, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtSegundoApeClient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarClient)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(lblNomClient)
                                            .addGap(180, 180, 180)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblTelefonoClient)
                                                .addComponent(lblSaldoP))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActualizarClient))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(234, 234, 234)
                                    .addComponent(txtSaldoClient, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(244, 244, 244)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblRFCClient)
                                .addComponent(txtTelefonoClient)
                                .addComponent(txtRFCClient, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnRegistrarClient))))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomClient)
                    .addComponent(lblSaldoP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomClient, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtSaldoClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrimerApeClient)
                    .addComponent(lblTelefonoClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefonoClient)
                    .addComponent(txtPrimerApeClient, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSegundoApeClient)
                    .addComponent(lblRFCClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRFCClient)
                    .addComponent(txtSegundoApeClient, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnRegistrarClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarClient)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtBuscarClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarClient))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        panelTabs.addTab("Clientes", jPanel3);

        lblRegPeliculas.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegPeliculas.setText("Peliculas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Titulo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Director:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Año de lanzamiento:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Costo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Existencias para prestamo:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Existencias para venta:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Genero:");

        jLabel9.setText("Numero de estanteria:");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acción", "Romance", "Comedia", "Terror", "Infantil", "Familiar", "Histórica", "Drama" }));

        btnRegPel.setText("Registrar Pelicula");
        btnRegPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegPelActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Buscar:");

        btnBuscarPel.setText("Buscar");
        btnBuscarPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPelActionPerformed(evt);
            }
        });

        btnActPel.setText("Actualizar");
        btnActPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActPelActionPerformed(evt);
            }
        });

        btnCancelPel.setText("Cancelar");
        btnCancelPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelPelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTituloPel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(txtEstanteriaPel)
                                    .addComponent(comboGenero, 0, 200, Short.MAX_VALUE)))
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRegPeliculas)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtExistenciaPresPel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(txtCostoPel, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtAnioPel, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDirectorPel, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtBuscarPel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel14)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnRegPel)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(btnActPel)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnCancelPel)))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtExistenciaVenPel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarPel)
                                .addGap(102, 102, 102)))
                        .addGap(196, 196, 196))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegPeliculas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTituloPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirectorPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstanteriaPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnioPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegPel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnActPel)
                    .addComponent(btnCancelPel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCostoPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExistenciaPresPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(btnBuscarPel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtExistenciaVenPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        panelTabs.addTab("Peliculas", jPanel2);

        lblRegPeliculas1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegPeliculas1.setText("Prestamos");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("RFC Cliente:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Fecha de prestamo:");

        lblFechaPres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFechaPres.setText("Fecha a mostrar...");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Fecha de entrega estimada:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Buscar:");

        btnPrestamo.setText("Prestamo");
        btnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamoActionPerformed(evt);
            }
        });

        btnEntregarPres.setText("Devolver prestamo");
        btnEntregarPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarPresActionPerformed(evt);
            }
        });

        comboPagoPres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Metodo de pago:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("ID Producto (Peliculas):");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("ID Producto (Series):");

        jLabel38.setText("Fecha de inicio:");

        butAgregarPel.setText("Agregar");
        butAgregarPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarPelActionPerformed(evt);
            }
        });

        butAgregarSer.setText("Agregar");
        butAgregarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarSerActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listaPrestamos);

        btnBuscarPres.setText("Buscar");
        btnBuscarPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPresActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText("Costo (por día)");

        txtCostoDia.setText("15");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText("Costo total");

        lblCostoTotal.setText("Por mostrar...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarPres)
                .addGap(106, 106, 106))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(69, 69, 69)
                                            .addComponent(butAgregarPel)
                                            .addGap(180, 180, 180))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addComponent(btnPrestamo)
                                    .addGap(89, 89, 89))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel33)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblRegPeliculas1)
                                                    .addComponent(jLabel10)
                                                    .addComponent(txtClientePres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel32)
                                                    .addComponent(comboPagoPres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(58, 58, 58)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblFechaPres)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel11)
                                                    .addComponent(txtFechaEstPres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel38)
                                                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtPelPres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtSerPres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(84, 84, 84))
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addGap(58, 58, 58)
                                                        .addComponent(butAgregarSer)
                                                        .addGap(157, 157, 157)))
                                                .addComponent(btnEntregarPres)
                                                .addGap(49, 49, 49))))
                                    .addGap(18, 18, 18)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCostoDia, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(txtBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCostoTotal)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRegPeliculas1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClientePres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaPres)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel13)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboPagoPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtFechaEstPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel38)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtPelPres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(butAgregarPel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSerPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btnPrestamo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butAgregarSer))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(btnEntregarPres))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarPres)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCostoTotal)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        panelTabs.addTab("Prestamo", jPanel4);

        lblRegPeliculas2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegPeliculas2.setText("Series");

        jLabel15.setText("Titulo:");

        jLabel16.setText("Genero:");

        comboGeneroSer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acción", "Romance", "Comedia", "Terror", "Infantil", "Familiar", "Histórica", "Drama" }));
        comboGeneroSer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel17.setText("Año de temporada:");

        jLabel18.setText("Costo:");

        jLabel19.setText("Cantidad de episodios:");

        jLabel20.setText("Numero de temporada:");

        jLabel21.setText("Existencias para prestamo:");

        jLabel22.setText("Existencias para venta:");

        jLabel23.setText("Estanteria:");

        btnRegSer.setText("Registrar");
        btnRegSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegSerActionPerformed(evt);
            }
        });

        btnBuscarSer.setText("Buscar");
        btnBuscarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSerActionPerformed(evt);
            }
        });

        jLabel24.setText("Buscar:");

        btnActSer.setText("Actualizar");
        btnActSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActSerActionPerformed(evt);
            }
        });

        btnCancelSer.setText("Cancelar");
        btnCancelSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtTempSer, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtEpisodiosSer)
                                                .addComponent(lblRegPeliculas2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTituloSer, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(comboGeneroSer, javax.swing.GroupLayout.Alignment.LEADING, 0, 180, Short.MAX_VALUE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTempAnioSer, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel20))
                                        .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel21)
                                                .addComponent(txtExisPresSer, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                .addComponent(jLabel22)
                                                .addComponent(txtExisVenSer)
                                                .addComponent(jLabel23)
                                                .addComponent(txtEstanteriaSer))
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(btnActSer)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnCancelSer))
                                                .addComponent(txtBuscarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(btnRegSer))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(btnBuscarSer)))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegPeliculas2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTituloSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExisPresSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGeneroSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExisVenSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTempAnioSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstanteriaSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEpisodiosSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegSer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(btnActSer)
                    .addComponent(btnCancelSer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTempSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarSer)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        panelTabs.addTab("Series", jPanel5);

        lblRegPeliculas3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblRegPeliculas3.setText("Ventas");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("RFC Cliente:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("RFC Empleado:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("ID Producto (Peliculas):");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Costo:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Fecha de Venta:");

        txtFechaVen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFechaVen.setText("Fecha a mostrar...");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Metodo de pago:");

        comboPagoVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Buscar:");

        btnBuscarVen.setText("Buscar");
        btnBuscarVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVenActionPerformed(evt);
            }
        });

        btnComprarVen.setText("Comprar!");
        btnComprarVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarVenActionPerformed(evt);
            }
        });

        btnAgregarPel.setText("Agregar");
        btnAgregarPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPelActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("ID Producto (Series):");

        btnAgregarSer.setText("Agregar");
        btnAgregarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSerActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(listaVenta);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRFCEmpVen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCostoVen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPagoVen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRFCClientVen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(lblRegPeliculas3)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaVen)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtPelVen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSerieVen)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(btnBuscarVen)
                                            .addGap(55, 55, 55))))
                                .addGap(52, 52, 52))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtBuscarVen, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnAgregarPel)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarSer)
                            .addComponent(btnComprarVen))
                        .addGap(104, 104, 104))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegPeliculas3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPelVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtRFCClientVen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnAgregarPel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRFCEmpVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCostoVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarSer)))
                    .addComponent(txtSerieVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprarVen)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPagoVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBuscarVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(txtFechaVen)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarVen)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        panelTabs.addTab("Venta", jPanel6);

        jScrollPane1.setViewportView(listaEstanterias);

        lblEstanterias.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        lblEstanterias.setText("Estanterias");

        btnPelEstanteria.setText("Peliculas");
        btnPelEstanteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelEstanteriaActionPerformed(evt);
            }
        });

        btnSerEstanteria.setText("Series");
        btnSerEstanteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerEstanteriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPelEstanteria)
                            .addComponent(btnSerEstanteria)))
                    .addComponent(lblEstanterias))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstanterias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnPelEstanteria)
                        .addGap(18, 18, 18)
                        .addComponent(btnSerEstanteria))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        panelTabs.addTab("Estanterias", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnBuscarPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPelActionPerformed
        checkQueryPeliculas(txtBuscarPel.getText());
        
        btnActPel.setVisible(true);
        btnCancelPel.setVisible(true);
        btnAgregarPel.setVisible(false);
        btnBuscarPel.setVisible(false);
        btnRegPel.setVisible(false);
    }//GEN-LAST:event_btnBuscarPelActionPerformed

    private void btnBuscarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpActionPerformed
        checkQueryEmp(txtBuscarEmp.getText());
        
        
    }//GEN-LAST:event_btnBuscarEmpActionPerformed

    private void btnRegistrarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEmpActionPerformed
        try {
            // TODO add your handling code here:
            insertEmpleado(txtIdEmp.getText(),textNomEmp.getText(),txtPrimerApeEmp.getText(),txtSegundoApeEmp.getText(),(String)comboEmp.getSelectedItem(),txtTelefonoEmp.getText(),txtSalarioEmp.getText(),txtHorarioEmp.getText());
        } catch (SQLException ex) {
            Logger.getLogger(SistemaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarEmpActionPerformed

    private void btnRegistrarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClientActionPerformed
        // TODO add your handling code here:
        insertCliente(txtRFCClient.getText(),txtNomClient.getText(),txtPrimerApeClient.getText(),txtSegundoApeClient.getText(),txtDirClient.getText(),txtTelefonoClient.getText(),Integer.parseInt(txtSaldoClient.getText()));
    }//GEN-LAST:event_btnRegistrarClientActionPerformed

    private void btnBuscarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientActionPerformed
        checkQueryCli(txtBuscarClient.getText());
        
    }//GEN-LAST:event_btnBuscarClientActionPerformed

    private void btnRegPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegPelActionPerformed
        insertPelicula(txtTituloPel.getText(), txtDirectorPel.getText(), (String)comboGenero.getSelectedItem(), Integer.parseInt(txtAnioPel.getText()), Integer.parseInt(txtCostoPel.getText()), Integer.parseInt(txtExistenciaPresPel.getText()), Integer.parseInt(txtExistenciaVenPel.getText()), Integer.parseInt(txtEstanteriaPel.getText()));
        
        txtTituloPel.setText("");
        txtDirectorPel.setText("");
        txtAnioPel.setText("");
        txtCostoPel.setText("");
        txtExistenciaPresPel.setText("");
        txtExistenciaVenPel.setText("");
        txtEstanteriaPel.setText("");
    }//GEN-LAST:event_btnRegPelActionPerformed

    private void btnRegSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegSerActionPerformed
        insertSerie(txtTituloSer.getText(), (String)comboGeneroSer.getSelectedItem(), Integer.parseInt(txtTempSer.getText()), Integer.parseInt(txtTempAnioSer.getText()), Integer.parseInt(txtEpisodiosSer.getText()), Integer.parseInt(txtCostoSer.getText()), Integer.parseInt(txtExisPresSer.getText()), Integer.parseInt(txtExisVenSer.getText()), Integer.parseInt(txtEstanteriaSer.getText()));
        
        txtTituloSer.setText("");
        txtTempSer.setText("");
        txtTempAnioSer.setText("");
        txtEpisodiosSer.setText("");
        txtCostoSer.setText("");
        txtExisPresSer.setText("");
        txtExisVenSer.setText("");
        txtEstanteriaSer.setText("");
    }//GEN-LAST:event_btnRegSerActionPerformed

    private void btnBuscarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSerActionPerformed
        checkQuerySeries(txtBuscarSer.getText());
        
        btnActSer.setVisible(true);
        btnCancelSer.setVisible(true);
        btnRegSer.setVisible(false);
        btnBuscarSer.setVisible(false);
    }//GEN-LAST:event_btnBuscarSerActionPerformed

    private void btnAgregarPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPelActionPerformed
        Integer aux = Integer.parseInt(txtPelVen.getText());
        
        if(checkExist(1,1, txtPelVen.getText())){
            addListVenta(txtPelVen.getText(), true);
            agregarPel[iP] = aux;
            iP++;
            for(int i=0; i<iP; i++){
                System.out.println(agregarPel[i]);
            }
        }
            
    }//GEN-LAST:event_btnAgregarPelActionPerformed

    private void btnAgregarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSerActionPerformed
        addListVenta(txtSerieVen.getText(), false);
        Integer aux = Integer.parseInt(txtSerieVen.getText());
        agregarSer[iS] = aux;
        iS++;
        for(int i=0; i<iS; i++){
            System.out.println(agregarSer[i]);
        }
    }//GEN-LAST:event_btnAgregarSerActionPerformed

    private void btnComprarVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarVenActionPerformed
        comprar((String)comboPagoVen.getSelectedItem(), txtCostoVen.getText(), txtRFCClientVen.getText(), txtRFCEmpVen.getText());
        dmVenta.clear();
    }//GEN-LAST:event_btnComprarVenActionPerformed

    private void btnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamoActionPerformed
        prestamo(txtFechaEstPres.getText(), (String)comboPagoPres.getSelectedItem(),txtClientePres.getText(), Integer.parseInt(txtCostoDia.getText()));
        dmPrestamo.clear();
    }//GEN-LAST:event_btnPrestamoActionPerformed

    private void butAgregarPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarPelActionPerformed
        addListPrestamo(txtPelPres.getText(), true);
        Integer aux = Integer.parseInt(txtPelPres.getText());
        agregarPel[iP] = aux;
        iP++;
        for(int i=0; i<iP; i++){
            System.out.println(agregarPel[i]);
        }
    }//GEN-LAST:event_butAgregarPelActionPerformed

    private void butAgregarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarSerActionPerformed
        addListPrestamo(txtSerPres.getText(), false);
        Integer aux = Integer.parseInt(txtSerPres.getText());
        agregarSer[iS] = aux;
        iS++;
        for(int i=0; i<iS; i++){
            System.out.println(agregarSer[i]);
        }
    }//GEN-LAST:event_butAgregarSerActionPerformed

    private void btnBuscarVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVenActionPerformed
        dmVenta.clear();
        try {
            checkQueryVen(Integer.parseInt(txtBuscarVen.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(SistemaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarVenActionPerformed

    private void btnCancelarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClientActionPerformed
        tmpRFCClient = null;

        btnActualizarClient.setVisible(false);
        btnCancelarClient.setVisible(false);
        btnRegistrarClient.setVisible(true);
        btnBuscarClient.setVisible(true);
        
        txtNomClient.setText("");
        txtPrimerApeClient.setText("");
        txtSegundoApeClient.setText("");
        txtDirClient.setText("");
        txtSaldoClient.setText("");
        txtTelefonoClient.setText("");
        txtRFCClient.setText("");
        txtBuscarClient.setText("");
        
    }//GEN-LAST:event_btnCancelarClientActionPerformed

    private void btnActualizarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClientActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de actualizar los siguientes datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(opcion==0){
            updateCliente(tmpRFCClient, txtNomClient.getText(), txtPrimerApeClient.getText(), txtSegundoApeClient.getText(), txtDirClient.getText(), txtTelefonoClient.getText(), Integer.parseInt(txtSaldoClient.getText()));
        
            tmpRFCClient = null;

            btnActualizarClient.setVisible(false);
            btnCancelarClient.setVisible(false);
            btnRegistrarClient.setVisible(true);
            btnBuscarClient.setVisible(true);

            /*txtNomClient.setText("");
            txtPrimerApeClient.setText("");
            txtSegundoApeClient.setText("");
            txtDirClient.setText("");
            txtSaldoClient.setText("");
            txtTelefonoClient.setText("");
            txtRFCClient.setText("");
            txtBuscarClient.setText("");*/
        }
        else{
            
        }
        
    }//GEN-LAST:event_btnActualizarClientActionPerformed

    private void btnCancelarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpActionPerformed
        tmpRFCEmp = null;
        
        btnActualizarEmp.setVisible(false);
        btnCancelarEmp.setVisible(false);
        btnRegistrarEmp.setVisible(true);
        btnBuscarEmp.setVisible(true);
        
        txtIdEmp.setText("");
        textNomEmp.setText("");
        txtPrimerApeEmp.setText("");
        txtSegundoApeEmp.setText("");
        comboEmp.setSelectedItem("");
        txtTelefonoEmp.setText("");
        txtSalarioEmp.setText("");
        txtHorarioEmp.setText("");
        txtBuscarEmp.setText("");
    }//GEN-LAST:event_btnCancelarEmpActionPerformed

    private void btnActualizarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de actualizar los siguientes datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(opcion==0){
            updateEmp(tmpRFCEmp, textNomEmp.getText(), txtPrimerApeEmp.getText(), txtSegundoApeEmp.getText(), (String)comboEmp.getSelectedItem(), txtTelefonoEmp.getText(), Integer.parseInt(txtSalarioEmp.getText()), txtHorarioEmp.getText());

            tmpRFCEmp = null;

            btnActualizarEmp.setVisible(false);
            btnCancelarEmp.setVisible(false);
            btnRegistrarEmp.setVisible(true);
            btnBuscarEmp.setVisible(true);

            /*txtIdEmp.setText("");
            textNomEmp.setText("");
            txtPrimerApeEmp.setText("");
            txtSegundoApeEmp.setText("");
            comboEmp.setSelectedItem("");
            txtTelefonoEmp.setText("");
            txtSalarioEmp.setText("");
            txtHorarioEmp.setText("");
            txtBuscarEmp.setText("");*/
        }
        else{
            
        }
    }//GEN-LAST:event_btnActualizarEmpActionPerformed

    private void btnCancelPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelPelActionPerformed
        // TODO add your handling code here:
        txtTituloPel.setText("");
        txtDirectorPel.setText("");
        txtAnioPel.setText("");
        txtEstanteriaPel.setText("");
        txtCostoPel.setText("");
        txtExistenciaPresPel.setText("");
        txtExistenciaVenPel.setText("");
        comboGenero.setSelectedItem("");
        
        btnActPel.setVisible(false);
        btnCancelPel.setVisible(false);
        btnBuscarPel.setVisible(true);
        btnRegPel.setVisible(true);
    }//GEN-LAST:event_btnCancelPelActionPerformed

    private void btnActPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActPelActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de actualizar los siguientes datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(opcion==0){
            actualizarPel(txtTituloPel.getText(), txtDirectorPel.getText(), (String)comboGenero.getSelectedItem(), Integer.parseInt(txtAnioPel.getText()), Integer.parseInt(txtCostoPel.getText()), Integer.parseInt(txtExistenciaPresPel.getText()), Integer.parseInt(txtExistenciaVenPel.getText()), Integer.parseInt(txtEstanteriaPel.getText()));

            btnActPel.setVisible(false);
            btnCancelPel.setVisible(false);
            btnBuscarPel.setVisible(true);
            btnRegPel.setVisible(true);
        }
    }//GEN-LAST:event_btnActPelActionPerformed

    private void btnPelEstanteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelEstanteriaActionPerformed
        estanteriaPeliculas();
    }//GEN-LAST:event_btnPelEstanteriaActionPerformed

    private void btnSerEstanteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerEstanteriaActionPerformed
        estanteriaSeries();
    }//GEN-LAST:event_btnSerEstanteriaActionPerformed

    private void btnActSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActSerActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de actualizar los siguientes datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(opcion==0){
            actualizarSer(txtTituloSer.getText(), (String)comboGeneroSer.getSelectedItem(), Integer.parseInt(txtTempSer.getText()), Integer.parseInt(txtTempAnioSer.getText()), Integer.parseInt(txtEpisodiosSer.getText()), Integer.parseInt(txtCostoSer.getText()), Integer.parseInt(txtExisPresSer.getText()), Integer.parseInt(txtExisVenSer.getText()), Integer.parseInt(txtEstanteriaSer.getText()));

            btnActSer.setVisible(false);
            btnCancelSer.setVisible(false);
            btnBuscarSer.setVisible(true);
            btnRegSer.setVisible(true);
        }
    }//GEN-LAST:event_btnActSerActionPerformed

    private void btnCancelSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSerActionPerformed
        // TODO add your handling code here:
        btnActSer.setVisible(false);
        btnCancelSer.setVisible(false);
        btnBuscarSer.setVisible(true);
        btnRegSer.setVisible(true);
    }//GEN-LAST:event_btnCancelSerActionPerformed

    private void btnBuscarPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPresActionPerformed
        dmPrestamo.clear();
        try {
            checkQueryPres(Integer.parseInt(txtBuscarPres.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(SistemaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarPresActionPerformed

    private void btnEntregarPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarPresActionPerformed
        try {
            devolverPrestamo(Integer.parseInt(txtBuscarPres.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(SistemaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEntregarPresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActPel;
    private javax.swing.JButton btnActSer;
    private javax.swing.JButton btnActualizarClient;
    private javax.swing.JButton btnActualizarEmp;
    private javax.swing.JButton btnAgregarPel;
    private javax.swing.JButton btnAgregarSer;
    private javax.swing.JButton btnBuscarClient;
    private javax.swing.JButton btnBuscarEmp;
    private javax.swing.JButton btnBuscarPel;
    private javax.swing.JButton btnBuscarPres;
    private javax.swing.JButton btnBuscarSer;
    private javax.swing.JButton btnBuscarVen;
    private javax.swing.JButton btnCancelPel;
    private javax.swing.JButton btnCancelSer;
    private javax.swing.JButton btnCancelarClient;
    private javax.swing.JButton btnCancelarEmp;
    private javax.swing.JButton btnComprarVen;
    private javax.swing.JButton btnEntregarPres;
    private javax.swing.JButton btnPelEstanteria;
    private javax.swing.JButton btnPrestamo;
    private javax.swing.JButton btnRegPel;
    private javax.swing.JButton btnRegSer;
    private javax.swing.JButton btnRegistrarClient;
    private javax.swing.JButton btnRegistrarEmp;
    private javax.swing.JButton btnSerEstanteria;
    private javax.swing.JButton butAgregarPel;
    private javax.swing.JButton butAgregarSer;
    private javax.swing.JComboBox<String> comboEmp;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboGeneroSer;
    private javax.swing.JComboBox<String> comboPagoPres;
    private javax.swing.JComboBox<String> comboPagoVen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbPuesto;
    private javax.swing.JLabel lblCostoTotal;
    private javax.swing.JLabel lblEstanterias;
    private javax.swing.JLabel lblFechaPres;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblNomClient;
    private javax.swing.JLabel lblPrimerApe;
    private javax.swing.JLabel lblPrimerApeClient;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblRFCClient;
    private javax.swing.JLabel lblRegClientes;
    private javax.swing.JLabel lblRegEmpleados;
    private javax.swing.JLabel lblRegPeliculas;
    private javax.swing.JLabel lblRegPeliculas1;
    private javax.swing.JLabel lblRegPeliculas2;
    private javax.swing.JLabel lblRegPeliculas3;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblSaldoP;
    private javax.swing.JLabel lblSegundoApe;
    private javax.swing.JLabel lblSegundoApeClient;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoClient;
    private javax.swing.JList<String> listaEstanterias;
    private javax.swing.JList<String> listaPrestamos;
    private javax.swing.JList<String> listaVenta;
    private javax.swing.JTabbedPane panelTabs;
    private javax.swing.JTextField textNomEmp;
    private javax.swing.JTextField txtAnioPel;
    private javax.swing.JTextField txtBuscarClient;
    private javax.swing.JTextField txtBuscarEmp;
    private javax.swing.JTextField txtBuscarPel;
    private javax.swing.JTextField txtBuscarPres;
    private javax.swing.JTextField txtBuscarSer;
    private javax.swing.JTextField txtBuscarVen;
    private javax.swing.JTextField txtClientePres;
    private javax.swing.JTextField txtCostoDia;
    private javax.swing.JTextField txtCostoPel;
    private javax.swing.JTextField txtCostoSer;
    private javax.swing.JTextField txtCostoVen;
    private javax.swing.JTextField txtDirClient;
    private javax.swing.JTextField txtDirectorPel;
    private javax.swing.JTextField txtEpisodiosSer;
    private javax.swing.JTextField txtEstanteriaPel;
    private javax.swing.JTextField txtEstanteriaSer;
    private javax.swing.JTextField txtExisPresSer;
    private javax.swing.JTextField txtExisVenSer;
    private javax.swing.JTextField txtExistenciaPresPel;
    private javax.swing.JTextField txtExistenciaVenPel;
    private javax.swing.JTextField txtFechaEstPres;
    private javax.swing.JLabel txtFechaInicio;
    private javax.swing.JLabel txtFechaVen;
    private javax.swing.JTextField txtHorarioEmp;
    private javax.swing.JTextField txtIdEmp;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtPelPres;
    private javax.swing.JTextField txtPelVen;
    private javax.swing.JTextField txtPrimerApeClient;
    private javax.swing.JTextField txtPrimerApeEmp;
    private javax.swing.JTextField txtRFCClient;
    private javax.swing.JTextField txtRFCClientVen;
    private javax.swing.JTextField txtRFCEmpVen;
    private javax.swing.JTextField txtSalarioEmp;
    private javax.swing.JTextField txtSaldoClient;
    private javax.swing.JTextField txtSegundoApeClient;
    private javax.swing.JTextField txtSegundoApeEmp;
    private javax.swing.JTextField txtSerPres;
    private javax.swing.JTextField txtSerieVen;
    private javax.swing.JTextField txtTelefonoClient;
    private javax.swing.JTextField txtTelefonoEmp;
    private javax.swing.JTextField txtTempAnioSer;
    private javax.swing.JTextField txtTempSer;
    private javax.swing.JTextField txtTituloPel;
    private javax.swing.JTextField txtTituloSer;
    // End of variables declaration//GEN-END:variables
}
