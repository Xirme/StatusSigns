package me.xir.statussigns.listener;

import me.xir.statussigns.StatusSigns;

import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ServerSignListener implements Listener {
	
	private StatusSigns plugin;
	
	public void onSignChange(SignChangeEvent e) {
		String servername = plugin.getConfig().getString("serversigns.server.name");
		if (e.getLine(0).equalsIgnoreCase("[ServerSigns]")) {
			if (e.getLine(1).equalsIgnoreCase(servername)) {
				//echo servers players/online or offline to sign
				
			}
		}
	}

}
