package me.xir.statussigns.listener;

import me.xir.statussigns.StatusSigns;

import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ServerSignListener implements Listener {
	
	private StatusSigns plugin;
	
	public ServerSignListener(StatusSigns p) {
		plugin = p;
	}

	
	public void onSignChange(SignChangeEvent e) {
		String servername = plugin.getConfig().getString("serversigns.servers" + e.getLine(1));
		String serverip = plugin.getConfig().getString(servername + "ip");
		if (e.getLine(0).equalsIgnoreCase("[ServerSigns]")) {
			if (e.getLine(1).equalsIgnoreCase(servername)) {
				e.setLine(3, serverip);
				System.out.println("A sign was registered.");
			}
		}
	}

}
