package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.Methoden.Vanish_methoden;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEvent_Listener implements Listener {

	private main plugin;

	public PlayerQuitEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.hasPermission("sup") || p.hasPermission("content")) {	
			if(Vanish_methoden.hasVanishEnabled(p)) {
				Vanish_methoden.unvanish(p);
				e.setQuitMessage("");
			}
		}
		
		plugin.god.remove(p.getName());
		plugin.velocity.remove(p.getName());
		
		if(plugin.saving.contains(p)) {
			plugin.saving.remove(p);
			
			plugin.forcefield.get(p).cancel();
			plugin.forcefield.remove(p);
		}
		
		if(p.isOp()) p.setOp(false);
		
	}

}
