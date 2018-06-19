package com.jdy.connecter;

import com.jdy.config.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MysqlConnecter {
   
    private Connection connection = null;
    private boolean connected = false;

    public MysqlConnecter() {
        try {
            Class.forName(Configure.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR AT MysqlConnecter");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(Configure.URL, Configure.USERNAME, Configure.PASSWORD);
            connected = true;
            //String charset = "set names utf8";
            //String sql;
			//try {
			//	sql = new String(charset.getBytes("utf-8"));
			//	PreparedStatement preStmt = connection.prepareStatement(sql);
			//	preStmt.executeUpdate();
			//} catch (UnsupportedEncodingException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int insert(String sql)  
    {
        if (!connected) return 0;
        int autoInckey = -1;
        try{
            PreparedStatement preStmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preStmt.executeUpdate();
            
            ResultSet rs = preStmt.getGeneratedKeys(); //获取结果  
            if (rs.next()) {
            	autoInckey = rs.getInt(1);//取得ID
            } else {
            	autoInckey = -1;
            }
        }
        catch (SQLException e)  
        {
            e.printStackTrace();
        }
        return autoInckey; 
    }

    public int update(String sql)
    {
        int lineNum = 0;
        if (!connected) return 0;
        try{
            PreparedStatement preStmt = connection.prepareStatement(sql); 
            lineNum = preStmt.executeUpdate();
        }
        catch (SQLException e)  
        {
            e.printStackTrace();
        }
        
        return lineNum;
    }
    
    /** 
     * 查询一条数据 
     * @param sql 
     * @return 
     */  
    public Map<String,Object> find(String sql){
        if (!connected) return null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format(sql));
            ResultSetMetaData rsm = rs.getMetaData();
            HashMap<String,Object> data = null;
            
            if(rs.next()){  
                data = new HashMap<String,Object>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {// 数据库里从 1 开始    
                    String c = rsm.getColumnName(i);
                    String v = rs.getString(c);
                    data.put(c, v);
                }    
            }
            
            stmt.close();
            rs.close();
            
            return data;
        }
        catch(Exception e) {
            return null;
        }
    }
    
    public ArrayList<Map<String, String>> select(String sql, String tableName)
    { 
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        
        try  
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String[] frame = getFrame(tableName);
            while (rs.next())  
            {
                Map<String, String> tmp = new HashMap<String, String>();
                for (String key : frame) {
                    if (key == "#") break;
                    tmp.put(key, rs.getString(key));
                }
                result.add(tmp);
            }

            stmt.close();
            rs.close();
            
        }
        catch (SQLException e)  
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public int delete(String sql)  
    { 
        int lineNum = 0;
        try  
        {
            Statement stmt = connection.createStatement();
            lineNum = stmt.executeUpdate(sql);

            stmt.close();
        }
        catch (SQLException e)  
        {
            e.printStackTrace();
        }
        
        return lineNum;
    }
    
    private String[] getFrame(String tableName) {
        String[] result = new String[Configure.TABLELEN];
         try
         {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("show columns from " + tableName);
                int i = 0;
                while (rs.next())
                {
                    result[i++] = rs.getString(1);
                }
                result[i] = "#";

                stmt.close();
                rs.close();
                
        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }
        return result;
    }
    
    public void release() {
    	try
        {
        	if (connected) {
        		connection.close();
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}