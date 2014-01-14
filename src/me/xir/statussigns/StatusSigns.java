package me.xir.statussigns;

import me.xir.statussigns.listener.ServerSignListener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StatusSigns extends JavaPlugin implements Listener {
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ServerSignListener(this), this);
		
		saveDefaultConfig();
	}
	
	public void onDisable() {
		
	}
}
