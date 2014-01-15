package me.xir.statussigns.listener;

import me.xir.statussigns.StatusSigns;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ServerSignListener implements Listener {
	
	private StatusSigns plugin;
	
	public ServerSignListener(StatusSigns p) {
		plugin = p;
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		
		// Rule one: Make a variable for everything you call more than once.
		FileConfiguration config = plugin.getConfig();
		String servername = e.getLine(1));
		
		if (e.getLine(0).equalsIgnoreCase("[ServerSigns]")) {
			// Rule two: Don't make a variable for anything you only call once.
			if (config.getString("serversigns.servers." + servername) != null) {
				e.setLine(3, config.getString("serversigns.servers." + servername + ".ip"));
				System.out.println("A sign was registered.");
			} System.out.println("derp1");
		} System.out.println("derp2");
	}
}
