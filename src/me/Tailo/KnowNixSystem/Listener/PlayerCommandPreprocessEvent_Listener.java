package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessEvent_Listener implements Listener {

	private main plugin;

	public PlayerCommandPreprocessEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getMessage().toLowerCase().startsWith("/pl ") | e.getMessage().toLowerCase().startsWith("/plugins ") | e.getMessage().equalsIgnoreCase("/pl") | e.getMessage().equalsIgnoreCase("/plugins")) {
			if(p.hasPermission("dev")) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix + "§3Du hast keinen Zugriff auf unsere §6Plugins§3!");
			}
			
			return;
		}
		if(e.getMessage().equalsIgnoreCase("/op")) {
			if(!p.hasPermission("sup")) {
				e.setCancelled(true);
				p.sendMessage("§cSyntax: /op <Spieler>");
			}
			
			return;
		}
		if(e.getMessage().toLowerCase().startsWith("/op ") && !e.getMessage().split(" ")[1].equals("")) {
			if(!p.hasPermission("sup")) {
				e.setCancelled(true);
				p.sendMessage(e.getMessage().split(" ")[1] + " wurde zum Operator ernannt");
			}
		}
	}
}
