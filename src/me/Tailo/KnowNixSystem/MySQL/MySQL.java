package me.Tailo.KnowNixSystem.MySQL;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class MySQL {
	
	public static Connection con;
	public static String host;
	public static String port;
	public static String database;
	public static String username;
	public static String password;
	
	public static void connect() {
		if(!isConnected()) {
			
			loadConfig();
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
				System.out.println("[System] Verbindung mit MySQL erfolgreich hergestellt!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[System] Verbindung mit MySQL konnte nicht hergestellt werden!");
			}
		}
		
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("[System] Verbindung mit MySQL erfolgreich geschlossen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		if(con != null) {
			return true;
		}		
		return false;
	}
	
	public static void update(String qry) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(qry);
			ps.executeUpdate();
		} catch (SQLException e) {
			if(e instanceof CommunicationsException) {
				System.out.println("[System] Verbindung mit MySQL verloren! Versuche erneut...");
				update(qry);
			}
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResult(String qry) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(qry);
			return ps.executeQuery();
		} catch (SQLException e) {
			if(e instanceof CommunicationsException) {
				System.out.println("[System] Verbindung mit MySQL verloren! Versuche erneut...");
				return getResult(qry);
			}
			e.printStackTrace();
		}
		return null;
	}
	
	private static void loadConfig() {
		
		File file = new File("plugins/KnowNixSystem", "mysql.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", "3306");
		cfg.addDefault("database", "system");
		cfg.addDefault("username", "system");
		cfg.addDefault("password", "password");
		
		cfg.options().copyDefaults(true);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		host = cfg.getString("host");
		port = cfg.getString("port");
		database = cfg.getString("database");
		username = cfg.getString("username");
		password = cfg.getString("password");
		
	}
	
}
