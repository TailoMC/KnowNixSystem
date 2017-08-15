package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginEvent_Listener implements Listener {

	private main plugin;

	public PlayerLoginEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent e) {
		
		String ip = e.getHostname().toString().split(":")[0];
		
		if(!ip.toLowerCase().endsWith("knownix.de")) {
			
			e.disallow(null, "§9§lKnowNix\n\n§cBitte joine über '§a§l§nknownix.de§c'!\n\n§7(Port 25565)");
			
			return;
		}
		
		String name = e.getPlayer().getName().toLowerCase();
		
		if(plugin.banned.contains(name)) {
			
			e.disallow(null, "§cDu wurdest für diesen Server gesperrt!");
			
		}
		
	}

}
