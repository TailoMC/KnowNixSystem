package me.Tailo.KnowNixSystem.Permission;

import me.Tailo.KnowNixSystem.Permission.Permission_methoden.Group;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerRecivePermissionEvent_Listener implements Listener {

	private main plugin;
	public static boolean recived;

	public PlayerRecivePermissionEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerLogin(PlayerLoginEvent e) {
		
		Player p = e.getPlayer();
		Group group = Permission_methoden.getGroup(p);
		
		Permission_methoden.loadPermissions(p, group);
		
	}

}
