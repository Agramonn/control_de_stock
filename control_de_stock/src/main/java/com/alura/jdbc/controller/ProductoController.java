package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoController {

	private ProductoDAO productoDAO;
	
	public ProductoController() {
		productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			final java.sql.PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET NOMBRE = ?" + ", DESCRIPCION =?"
	    			+",CANTIDAD =?" + " WHERE ID=?");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, id);
				statement.setInt(4, cantidad);
				
				statement.execute();
				
				return statement.getUpdateCount();
			}
		}
	}

	public int eliminar(Integer id) throws SQLException{
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			final java.sql.PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try(statement){
				statement.setInt(1, id);
				
				statement.execute();
				
				return statement.getUpdateCount();
			}
		}
	}

	public List<Producto> listar() {
		return productoDAO.listar();
		
	}

    public void guardar(Producto producto) {    	
    	productoDAO.guardar(producto);
    }

	
    	
  	
 
}
