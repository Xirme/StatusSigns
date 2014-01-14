package me.xir.statussigns.listener;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import me.xir.statussigns.StatusSigns;

import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ServerSignListener implements Listener {
	
	private StatusSigns plugin;
	
	public void onSignChange(SignChangeEvent e) {
		String servername = plugin.getConfig().getString("serversigns.server.name");
		if (e.getLine(0).equalsIgnoreCase("[ServerSigns]")) {
			if (e.getLine(1).equalsIgnoreCase(servername)) {
				
				HashMap JSON = pingServer(server);
				
				if (JSON == null || JSON.get("status").equals("false")) {
					e.setLine(3, "Offline");
				} else {
					e.setLine(3, "Online");
				}	
			}
		}
	}
	
	public static HashMap pingServer(String serverip) {
		try {
			URL url = new URL("http://api.iamphoenix.me/get/?server_ip=" + serverip);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String data = reader.readLine();
				
				Gson gson = new Gson();
				return gson.fromJson(data,  HashMap.class);
			}
		} catch (MalformedURLException ex) {
			ex.printStrackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
