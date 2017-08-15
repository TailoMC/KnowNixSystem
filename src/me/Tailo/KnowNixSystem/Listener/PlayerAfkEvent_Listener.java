package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.Methoden.AfkScheduler;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerAfkEvent_Listener implements Listener {

	private main plugin;

	public PlayerAfkEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		if(e.getFrom().distance(e.getTo()) > 0.1) {
			if(plugin.afk.containsKey(e.getPlayer().getName())) {
				plugin.afk.put(e.getPlayer().getName(), 0);
			}
		}
		
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerInteact(PlayerInteractEvent e) {
		
		if(plugin.afk.containsKey(e.getPlayer().getName())) {
			plugin.afk.put(e.getPlayer().getName(), 0);
		}
		
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		if(!e.getPlayer().hasPermission("premiumplus")) {
			if(Bukkit.getOnlinePlayers().size() == 1) {
				plugin.afkrun = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new AfkScheduler(plugin), 0, 20);
			}
			
			plugin.afk.put(e.getPlayer().getName(), 0);
		}
		
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		if(Bukkit.getOnlinePlayers().size() <= 1) {
			Bukkit.getScheduler().cancelTask(plugin.afkrun);
		}
		
		plugin.afk.remove(e.getPlayer().getName());
		
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		
		plugin.afk.remove(e.getPlayer().getName());
		
	}

}
