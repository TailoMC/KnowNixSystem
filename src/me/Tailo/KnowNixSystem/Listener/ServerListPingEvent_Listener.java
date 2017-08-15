package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingEvent_Listener implements Listener {

	private main plugin;

	public ServerListPingEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onServerListPing(ServerListPingEvent e) {
		
		String ip = e.getAddress().getHostName();
		
		if(!ip.equalsIgnoreCase("nevadamc.de")) {
			
			e.setMotd("§9§lNevadaMC\n§cBitte joine über '§a§l§nnevadamc.de§c'! §7(Port 25565)");
			e.setMaxPlayers(0);
			
		}
		
	}

}
