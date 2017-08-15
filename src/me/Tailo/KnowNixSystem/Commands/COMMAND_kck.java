package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_kck implements CommandExecutor {

	private main plugin;

	public COMMAND_kck(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("mod")) {
			
			if(args.length == 1) {
				
				if(!(args[0].equalsIgnoreCase("all") && sender.hasPermission("srmod"))) {
					
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null) {
						target.kickPlayer(plugin.prefix + "§6Du wurdest gekickt!");
						sender.sendMessage(plugin.prefix + "§6" + target.getName() + " §3wurde gekickt!");
					} else {
						sender.sendMessage(plugin.prefix + "§cDieser Spieler ist nicht online");
					}
					
				} else {
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						
						if(!players.hasPermission("testmod")) {
							players.kickPlayer(plugin.prefix + "§6Du wurdest gekickt!");
						}
						
					}
					
					sender.sendMessage(plugin.prefix + "§3Alle Spieler wurden gekickt!");
					
				}
				
			} else
				if(args.length > 1) {
					
					String reason = "";
					for(int i = 1; i < args.length; i++) {
						reason = reason + args[i] + " ";
					}
					
					if(!(args[0].equalsIgnoreCase("all") && sender.hasPermission("srmod"))) {
						
						Player target = Bukkit.getPlayer(args[0]);
						
						if(target != null) {
							target.kickPlayer(plugin.prefix + "§6Du wurdest gekickt! §4Grund: §c" + reason);
							sender.sendMessage(plugin.prefix + "§6" + target.getName() + " §3wurde gekickt! §4Grund: §c" + reason);
						} else {
							sender.sendMessage(plugin.prefix + "§cDieser Spieler ist nicht online");
						}
						
					} else {
						
						for(Player players : Bukkit.getOnlinePlayers()) {
							
							if(!players.hasPermission("testmod")) {
								players.kickPlayer(plugin.prefix + "§6Du wurdest gekickt! §4Grund: §c" + reason);
							}
							
						}
						
						sender.sendMessage(plugin.prefix + "§3Alle Spieler wurden gekickt! §4Grund: §c" + reason);
						
					}
					
				} else {
					sender.sendMessage(plugin.prefix + "§c/kck <Spielername>");	
				}
			
		}
		
		return true;
	}

}
