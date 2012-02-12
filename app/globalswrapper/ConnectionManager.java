package globalswrapper;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.intersys.globals.*;
public class ConnectionManager {
	
   private static ConnectionManager manager;
    public static ConnectionManager Instance()
    {
        if (ConnectionManager.manager == null)
        {
            ConnectionManager.manager = new ConnectionManager();            
        }
        
        return ConnectionManager.manager;
    }
    
    private Connection _connection;

    public Connection getConnection() throws Exception 
    {
        if (_connection == null)
        {
            try
            {
                 _connection =  ConnectionContext.getConnection(); // ConnectionContext.getConnection();
                
                if (!_connection.isConnected())
                {
                    _connection.connect("USER","_SYSTEM","DATA");
                    //_connection.connect("","","");
                }
            }
            catch (GlobalsException ex)
            {
                throw ex;
            }
            catch (Exception ex2)
            {
                throw ex2;
            }
        }
        return _connection;
    }
    
    public void CloseConnection()
    {
        try {
            getConnection().close();
        } catch (Exception ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
