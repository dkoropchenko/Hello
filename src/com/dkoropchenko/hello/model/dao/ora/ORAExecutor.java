package com.dkoropchenko.hello.model.dao.ora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public abstract class ORAExecutor {
	
	protected static Logger log = Logger.getLogger(ORAExecutor.class); 
	
	protected abstract void getResult(ResultSet result) throws SQLException;
	
	protected void execute(String sql, Map<Integer,Object> params) {
		Connection conn = getConnect();
		PreparedStatement stmn = null;
		ResultSet rs = null;
		try {
		    // Disable auto commit
		    conn.setAutoCommit(false);
		    stmn = conn.prepareStatement(sql);
		    int i = 1;
		    if (params != null) {
		    	for (Map.Entry<Integer,Object> entry: params.entrySet()){
			    	log.info("Add param: " + entry.getKey() + ",  " + entry.getValue());
			    	stmn.setObject(i, entry.getValue(), entry.getKey());
			    	i++;
			    }
		    }
		    else {
		    	log.info("params is null");
		    }
		    // Do SQL updates...
			log.info("Is executed sql: " + stmn.executeUpdate());
			rs = stmn.getResultSet();
			getResult(rs);
			conn.commit();
		}
		catch (NullPointerException e) {
			log.error("Cannot establish connection", e);
		}
		catch (SQLException e) {
			log.error("Error during execution", e);
			if (conn != null) {
	            try {
	                conn.rollback();
	                log.info("Transaction is being rolled back");
	            } catch(SQLException ex) {
	            	log.error("Transaction is not being rolled back", ex);
	            }
	        }
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					log.error("Error during closing result set", ex);
				}
			if (stmn != null)
				try {
					stmn.close();
				} catch (SQLException ex) {
					log.error("Error during closing statement", ex);
				}
			try {
				if (conn != null) conn.close();
			} catch (SQLException ex) {
				log.error("Error during closing connection", ex);
			}
		}
	}
	
	private Connection getConnect() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/hello");
			Locale.setDefault(Locale.ENGLISH);
			return ds.getConnection();
		}
		catch (NamingException e) {
			log.error("", e);
		}
		catch (SQLException e) {
			log.error("", e);
		}
		return null;
	}
}
