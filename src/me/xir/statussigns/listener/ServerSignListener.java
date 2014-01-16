package me.xir.statussigns.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import me.xir.statussigns.StatusSigns;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ServerSignListener implements Listener {
	
	public static StatusSigns plugin;
	
	public ServerSignListener(StatusSigns p) {
		plugin = p;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		
		// Rule one: Make a variable for everything you call more than once.
		FileConfiguration config = plugin.getConfig();
		String player = e.getPlayer().getName();
		String servername = e.getLine(1);
		
		if (e.getLine(0).equalsIgnoreCase("[ServerSigns]")) {
			// Rule two: Don't make a variable for anything you only call once.
			if (config.getString("serversigns.servers." + servername) != null) {
				
				HashMap JSON = queryServer(e.getLine(1));
				//do to check if offline or online
				if (JSON == null || JSON.get("status").equals("false")) {
					e.setLine(3, "Offline");
				} else {
					e.setLine(3, "Online");
				}
			}
		}
	}
	
	public static HashMap queryServer(String server) {
		FileConfiguration config = plugin.getConfig();
		
		try { 
			URL url = new URL("http://api.iamphoenix.me/get/?server_ip=" + config.getString("serversigns.servers." + server + ".ip") + ":" + config.getString("serversigns.servers." + server + ".port"));
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String data = reader.readLine();
				
				Gson gson = new Gson();
				return gson.fromJson(data,  HashMap.class);
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
