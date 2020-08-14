/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceclientmicro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStreamReader;
import java.io.FileReader;
/**
 *
 * @author KERVIN CORTEZ
 */
public class WebServiceClientMicro {
/*----------------------------------*/
    static String Empresa = "";
    static String servername = "";
    static String dbname = "";
    static String db_user = "";
    static String db_pass = "";
    static String transhec = " ";
    static String transhecpoll = " ";
    static String stockhec = " ";
/*----------------------------------*/    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
         Properties prop = new Properties();
        InputStreamReader input = null;
        input = new FileReader("config.properties");
        prop.load(input);

        Empresa = prop.getProperty("Empresa");
        servername = prop.getProperty("servername");
        dbname = prop.getProperty("dbname");
        db_user = prop.getProperty("db_user");
        db_pass = prop.getProperty("db_pass");
        transhec = prop.getProperty("transhec");
        stockhec = prop.getProperty("stockhec");
        
        System.out.println("*******************************************");
        System.out.println(prop.getProperty("Empresa"));
        System.out.println(prop.getProperty("servername"));
        System.out.println(prop.getProperty("dbname"));
        System.out.println(prop.getProperty("db_user"));
        System.out.println(prop.getProperty("db_pass"));
        System.out.println("*******************************************");
        
        
        System.out.println("*******************VERSION 2.1******************");

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = null;
            Statement st;
            ResultSet rs;
            int init = 0;
            while (true)
            {
                try
                {
                    //con = DriverManager.getConnection("jdbc:sqlserver://"+args[0]+";databaseName="+args[1],args[2],args[3]);
                     con = DriverManager.getConnection("jdbc:sqlserver://" + servername + ";DatabaseName=" + dbname + ";user=" + db_user + ";Password=" + db_pass);

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    if (con!=null)
                        con.close();
                    int c = 0;
                    while (c==0)
                    {
                        try
                        {
                            //con = DriverManager.getConnection("jdbc:sqlserver://"+args[0]+";databaseName="+args[1],args[2],args[3]);
                             con = DriverManager.getConnection("jdbc:sqlserver://" + servername + ";DatabaseName=" + dbname + ";user=" + db_user + ";Password=" + db_pass);
                            c=1;
                        }
                        catch (SQLException s)
                        {
                            System.out.println("Err: "+s.toString());
                            Log.log(" Error en conexion a base de datos  : "+s.toString() );
                        }
                        Thread.sleep(15000);
                    }
                }
                try
                {
                    st = con.createStatement();
                    if (init == 0)
                    {
                    System.out.println(" ENTRAMOS A LA CREACION DE TABLA");
                         creaTablas(st, con);
                    System.out.println(" SALIMOS A LA CREACION DE TABLA");
                    }

                    init = 1;
                    
                    
                   if(transhec.equalsIgnoreCase("true"))
                        {

                    String Sql = "select * from IDTRANSACTIONS where TIPO=1";
                    st = con.createStatement();
                    String id = "";
                    rs = st.executeQuery(Sql);
                    while (rs.next())
                        id = rs.getObject(1).toString();
                    if (id.equalsIgnoreCase(""))
                        id = "0";
                    //Sql = "select t.TransDateTime,e.Description,t.Quantity,isnull(t.ArticleDescription,'N/A'),isnull(p.VehicleLicensePlate,'N/A'),isnull(p.Mileage,0),t.ID_TRANSACTIONS from Transactions t inner join Terminals e on t.TerminalsId=e.ID_TERMINALS inner join Payments p on p.TransactionsID=t.ID_TRANSACTIONS where t.ID_TRANSACTIONS>"+id+" order by t.ID_TRANSACTIONS";
                    Sql = "select  t.TransDateTime,e.Description,t.Quantity,isnull(t.ArticleDescription,'N/A'),isnull(p.VehicleLicensePlate,'N/A') as Patente,isnull(p.Mileage,0) as Odometro,t.ID_TRANSACTIONS,t.DeviceAddress,isnull((select Name from Tanks where StationsID=t.TerminalsID and Number=t.TankNumber),'N/A'),isnull(p.CardSystem,'N/A') from Transactions t inner join Terminals e on t.TerminalsId=e.ID_TERMINALS inner join Payments p on p.TransactionsID=t.ID_TRANSACTIONS where t.ID_TRANSACTIONS>"+id+" order by t.ID_TRANSACTIONS";
                    st = con.createStatement();
                    rs = st.executeQuery(Sql);
                    while (rs.next())
                    {
                        System.out.println("(1)insertTrx:  "+rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString() +", 1 ,"+ Integer.parseInt(Empresa));
                        //int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString(), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                        Log.log("(1)insertTrx:  "+rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString() +", 1 ,"+ Integer.parseInt(Empresa));
                        int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString(), 1 , Integer.parseInt(Empresa));
                        Log.log("respuestas del servidor 1 : "+ r);
                        //int  r = insertarDatos(rs.getObject(1).toString(), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString(), rs.getObject(5).toString(), rs.getObject(6).toString());
                        if (r == 1)
                        {
                            System.out.println("(1u)r " +r);
                            Sql = "update IDTRANSACTIONS set ID_TRANSACTIONS="+rs.getObject(7).toString()+" where TIPO=1";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                        else
                        {
                            System.out.println("(1i)r " +r);
                            Sql = "insert into IDTRXFAILS values ("+rs.getObject(7).toString()+",1)";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                    }
                    Sql = "select t.TransDateTime,e.Description,t.Quantity,isnull(t.ArticleDescription,'N/A'),isnull(p.VehicleLicensePlate,'N/A') as Patente,isnull(p.Mileage,0) as Odometro,t.ID_TRANSACTIONS,t.DeviceAddress,isnull((select Name from Tanks where StationsID=t.TerminalsID and Number=t.TankNumber),'N/A'),isnull(p.CardSystem,'N/A') from Transactions t inner join Terminals e on t.TerminalsId=e.ID_TERMINALS inner join Payments p on p.TransactionsID=t.ID_TRANSACTIONS where t.ID_TRANSACTIONS in (select ID_TRANSACTIONS from IDTRXFAILS where TIPO=1) order by t.ID_TRANSACTIONS";
                    st = con.createStatement();
                    rs = st.executeQuery(Sql);
                    while (rs.next())
                    {
                        //int  r = insertarDatos(rs.getObject(1).toString(), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString(), rs.getObject(5).toString(), rs.getObject(6).toString());
                        //int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString(), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                       System.out.println("(2)insertTrx:  "+rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString()+", 1 ,"+ Integer.parseInt(Empresa));
                       int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+"@"+rs.getObject(9).toString()+"@"+rs.getObject(10).toString(), 1 , Integer.parseInt(Empresa));
                       if (r == 1)
                        {
                             System.out.println("(2d)r " +r);
                             System.out.println("(2)delete from IDTRXFAILS where ID_TRANSACTIONS="+rs.getObject(7).toString()+" and TIPO=1");
                            Sql = "delete from IDTRXFAILS where ID_TRANSACTIONS="+rs.getObject(7).toString()+" and TIPO=1";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                         System.out.println("(2)r " +r);
                    }
                                            }
                    else
                    {
                        System.out.println(" CONFIGURAR LA VARIABLE TRANSC en config. 1");
                    } 
                    
                    if(stockhec.equalsIgnoreCase("true"))
                        {
                    String Sql = "select * from IDTRANSACTIONS where TIPO=0";
                    st = con.createStatement();
                    rs = st.executeQuery(Sql);
                    String id = "";
                    while (rs.next())
                        id = rs.getObject(1).toString();
                    if (id.equalsIgnoreCase(""))
                        id = "0";
                    Sql = "select td.ID_TANKDATAS,t.StationsID, s.StationName, t.Name,td.DateTime,td.Level,td.Volume,td.Water from TankDatas td inner join Tanks t on t.ID_TANKS=td.TanksID inner join stations s on t.StationsID=s.ID_STATIONS where td.ID_TANKDATAS>"+id+" order by td.ID_TANKDATAS";
                    st = con.createStatement();
                    rs = st.executeQuery(Sql);
                    while (rs.next())
                    {
                        System.out.println("(3)insertTrx:  "+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+", 0 ,"+ Integer.parseInt(Empresa));
                        Log.log("(3)insertTrx:  "+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString()+", 0 ,"+ Integer.parseInt(Empresa));
                        //int r = insertTrx(rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString(), 0, Integer.parseInt(args[5]));
                        int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString(), 0, Integer.parseInt(Empresa));
                        Log.log("respuestas del servidor 2 : "+ r);
                        if (r == 1)
                        {
                             System.out.println("(3u)r " +r);
                            Sql = "update IDTRANSACTIONS set ID_TRANSACTIONS="+rs.getObject(1).toString()+" where TIPO=0";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                        else
                        {
                             System.out.println("(3i)r " +r);
                            Sql = "insert into IDTRXFAILS values ("+rs.getObject(1).toString()+",0)";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                    }
                    Sql = "select td.ID_TANKDATAS, t.StationsID, s.StationName, t.Name,td.DateTime,td.Level,td.Volume,td.Water from TankDatas td inner join Tanks t on t.ID_TANKS=td.TanksID inner join stations s on t.StationsID=s.ID_STATIONS where td.ID_TANKDATAS in (select ID_TRANSACTIONS from IDTRXFAILS where TIPO=0) order by td.ID_TANKDATAS";
                    st = con.createStatement();
                    rs = st.executeQuery(Sql);
                    while (rs.next())
                    {
     System.out.println("(4)insertTrx:  "+rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString() +", 0 ,"+ Integer.parseInt(Empresa));
                        //int r = insertTrx(rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString(), 0, Integer.parseInt(args[5]));
                        int r = insertTrx(rs.getObject(1).toString()+"@"+rs.getObject(2).toString()+"@"+rs.getObject(3).toString()+"@"+rs.getObject(4).toString()+"@"+rs.getObject(5).toString()+"@"+rs.getObject(6).toString()+"@"+rs.getObject(7).toString()+"@"+rs.getObject(8).toString(), 0, Integer.parseInt(Empresa));
                        if (r == 1)
                        {
                            System.out.println("(4d)r deberia limpiar IDTRXFAILS  " +r);
                            System.out.println(rs.getObject(1).toString());                            
                            Sql = "delete from IDTRXFAILS where ID_TRANSACTIONS="+rs.getObject(1).toString()+" and TIPO=0";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                         System.out.println("(4)r " +r);
                    }
                                            }
                    else
                    {
                            System.out.println(" CONFIGURAR LA VARIABLE stockhec en config. 1");
                    }

                    con.close();
                    try
                    {
                        Thread.sleep(300000);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Log.log(" Fails in the sleep  e  : "+e );
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.log(" Falla en el contenido e  : "+e );
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
  
        private static int creaTablas(Statement st, Connection con)
    {
        String Sql = "";
        ResultSet rs;
        try
        {
//          REVISION DE LAS TABLAS A VER SI EXISTE
            Sql = "select * from IDTRANSACTIONS";
            st = con.createStatement();
            st.executeQuery(Sql);
        }
        catch(Exception e)
        {
            System.out.println("TABLA IDTRANSACTIONS NO EXISTE");
            Sql = "create table IDTRANSACTIONS (ID_TRANSACTIONS int,TIPO int)";
            try
            {
                st = con.createStatement();
                st.executeUpdate(Sql);        
                
                //Sql = "insert into IDTRANSACTIONS values (0,1)";         
//                Sql = "select max(ID_TRANSACTIONS) from Transactions";
//                st = con.createStatement();
//                rs = st.executeQuery(Sql);
//                System.out.println(Sql);
//                System.out.println("Existe registro :  "+rs.next());          
                if(transhec.equalsIgnoreCase("true"))
                        {
                            System.out.println(" TRANSE 1 ");
                            Sql = "insert into IDTRANSACTIONS values ((select max(ID_TRANSACTIONS) from Transactions),1)";
                            st = con.createStatement();
                            st.executeUpdate(Sql);
                        }
                    else
                    {
                        System.out.println(" CONFIGURAR LA VARIABLE TRANSC en config.");
                    } 
                //Sql = "insert into IDTRANSACTIONS values (0,0)";
               //REVISION DEL TIPO CERO A VER SI EXISTE
//                    Sql = "select max(ID_TANKDATAS) from TANKDATAS";
//                    st = con.createStatement();
//                    rs = st.executeQuery(Sql);
//                    System.out.println(Sql);
//                    System.out.println("Existe registro :  "+rs.next());          
                    if(stockhec.equalsIgnoreCase("true") )
                        {   
                            System.out.println(" STOCK 1");
                             Sql = "insert into IDTRANSACTIONS values ((select max(ID_TANKDATAS) from TankDatas),0)";
                             st = con.createStatement();
                             st.executeUpdate(Sql);
                        }
                    else
                    {
                            System.out.println(" CONFIGURAR LA VARIABLE stockhec en config.");
                    }
                    
            }
            catch(Exception s)
            {
                Log.log(" Error en la tabla IDTRANSACTIONS  exepcion1 s  : "+s );
                return 0;
            }
        }
        try
        {
            Sql = "select * from IDTRXFAILS";
            st = con.createStatement();
            st.executeQuery(Sql);
            System.out.println("TABLA IDTRXFAILS EXISTE");     
        }
        catch(Exception e)
        {
            System.out.println("TABLA IDTRXFAILS NO EXISTE");
            Sql = "create table IDTRXFAILS (ID_TRANSACTIONS int,TIPO INT)";
            try
            {
                st = con.createStatement();
                st.executeUpdate(Sql);
                Log.log(" TABLA IDTRXFAILS EXISTE " );
            }
            catch(Exception s)
            {
                Log.log(" Error en la tabla IDTRXFAILSL  exepcion2 s  : "+s );
                return 0;
            }
        }
        return 1;
    }

    private static int insertTrx(java.lang.String data, int tipo, int empresa) {
        webserviceclientmicro.Servicio_Service service = new webserviceclientmicro.Servicio_Service();
        webserviceclientmicro.Servicio port = service.getServicioPort();
        return port.insertTrx(data, tipo, empresa);
    }
}

 