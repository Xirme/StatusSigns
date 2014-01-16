package me.xir.statussigns.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import me.xir.statussigns.StatusSigns;
import me.xir.statussigns.StatusSignsDatatypes;

import org.bukkit.ChatColor;
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
				StatusSignsDatatypes query = queryServer(servername);
	
				/*TODO: Clear the sign before data is outputted to the sign so the sign looks like:
				 * Example Server
				 * Status
				 * Players: 
				 * As end result.
				 * 
				 * TODO: Threaded checking and checks done every 60 seconds.
				 */
				
				if (query == null || !query.status) {
					//TODO: Handle offline properly.
					e.setLine(1, "Offline");
				} else {
					e.setLine(1, "Online");
				}
				e.setLine(2, "Players: " + query.players);
				
				player.sendMessage(ChatColor.GREEN + "StatusSign registered sign for " + servername);
			} else {
				player.sendMessage(ChatColor.RED + "Server does not exist in config.yml!");
			}
		}
	}
	
	public static StatusSignsDatatypes queryServer(String server) {
		FileConfiguration config = plugin.getConfig();
		try { 
			URL url = new URL("http://api.iamphoenix.me/get/?server_ip=" + config.getString("serversigns.servers." + server + ".ip") + ":" + config.getString("serversigns.servers." + server + ".port"));
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String data = reader.readLine();
				
				Gson gson = new Gson();
				return gson.fromJson(data,  StatusSignsDatatypes.class);
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
