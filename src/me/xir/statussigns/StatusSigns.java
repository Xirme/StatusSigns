package me.xir.statussigns;

import me.xir.statussigns.listener.ServerSignListener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class StatusSigns extends JavaPlugin implements Listener {
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ServerSignListener(this), this);
		
		saveDefaultConfig();
		
		/*
		 * TODO: Threaded checking and checks done every 60 seconds.
		 */
		
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
			@Override
			public void run() {
				// update signs every 60 seconds (1200 ticks) for as long as plugin is enabled.
			}
		}, 1200L);
	}
	
	public void onDisable() {
		
	}
}
