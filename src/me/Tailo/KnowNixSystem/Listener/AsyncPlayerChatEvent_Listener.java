package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatEvent_Listener implements Listener {

	private main plugin;

	public AsyncPlayerChatEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		
		if(plugin.globalmute) {
			if(!e.getPlayer().hasPermission("sup") && !e.getPlayer().hasPermission("content") && !e.getPlayer().hasPermission("builder")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(plugin.prefix + "§cGlobalmute ist aktiv!");
			}
		}
		
		if(e.getMessage().contains("@")) {
			for(Player players : Bukkit.getOnlinePlayers()) {
				String newmsg = e.getMessage().replace("@" + players.getName(), "§b@" + players.getName() + "§r");
				e.setMessage(newmsg);
			}
		}
		
	}
	

}
