package me.Tailo.KnowNixSystem.Listener;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class PlayerFreezeEvent_Listener implements Listener {

	private main plugin;

	public PlayerFreezeEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		if(plugin.freeze.contains(e.getPlayer().getName())) {
			e.getPlayer().teleport(e.getFrom(), TeleportCause.UNKNOWN);
		}
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(plugin.freeze.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if(plugin.freeze.contains(e.getDamager().getName())) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if(plugin.freeze.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		if(plugin.freeze.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
		
	}
	
}
