package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.Methoden.Vanish_methoden;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEvent_Listener implements Listener {

	private main plugin;

	public PlayerJoinEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.hasPermission("sup") || p.hasPermission("content")) {
			
			if(Vanish_methoden.hasVanishEnabled(p)) {
				
				Vanish_methoden.vanish(p);
				
				e.setJoinMessage("");
			}
			
			if(Vanish_methoden.run.size() != 0) {
				
				for(Player players : Vanish_methoden.run.keySet()) {
					if(!p.hasPermission("testmod")) {
						p.hidePlayer(players);
					}
				}
				
			}
			
		}
		
	}
	
}
