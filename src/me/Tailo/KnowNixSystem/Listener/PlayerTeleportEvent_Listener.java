package me.Tailo.KnowNixSystem.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.Tailo.KnowNixSystem.System.main;

public class PlayerTeleportEvent_Listener implements Listener {

	private main plugin;

	public PlayerTeleportEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		
		if(e.getPlayer().hasPermission("mod")) {
			
			plugin.back.put(e.getPlayer().getUniqueId(), e.getFrom());
			
		}
		
		if(plugin.freeze.contains(e.getPlayer().getName())) {
			System.out.println(e.getCause());
			
			if(e.getCause() != TeleportCause.UNKNOWN) {
				e.setCancelled(true);
			}
		}
		
	}

}
