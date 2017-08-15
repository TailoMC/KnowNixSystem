package me.Tailo.KnowNixSystem.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tailo.KnowNixSystem.System.main;

public class COMMAND_back implements CommandExecutor {

	private main plugin;

	public COMMAND_back(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("mod")) {
				
				Player p = (Player) sender;
				
				if(plugin.back.containsKey(p.getUniqueId())) {
					
					p.teleport(plugin.back.get(p.getUniqueId()));
					
					p.sendMessage(plugin.prefix + "§3Du hast dich zurückteleportiert!");
					
				} else {
					p.sendMessage(plugin.prefix + "§cDu hast dich noch nicht teleportiert!");
				}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
