package me.xir.statussigns;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StatusSigns extends JavaPlugin implements Listener {
	
	public void onEnable() {
		
		saveDefaultConfig();
	}
	
	public void onDisable() {
		
	}
}
