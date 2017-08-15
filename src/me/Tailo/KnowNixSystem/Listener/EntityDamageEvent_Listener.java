package me.Tailo.KnowNixSystem.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.Tailo.KnowNixSystem.System.main;

public class EntityDamageEvent_Listener implements Listener {

	private main plugin;

	public EntityDamageEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(plugin.god.contains(p.getName()) || plugin.freeze.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
		
	}
	
}
