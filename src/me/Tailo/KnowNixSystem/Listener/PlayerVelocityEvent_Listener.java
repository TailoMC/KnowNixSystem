package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;

public class PlayerVelocityEvent_Listener implements Listener {

	private main plugin;

	public PlayerVelocityEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerVelocity(PlayerVelocityEvent e) {
		
		Player p = e.getPlayer();
		
		if(plugin.velocity.contains(p.getName())) {
			e.setCancelled(true);
		}
		
	}
	
}
