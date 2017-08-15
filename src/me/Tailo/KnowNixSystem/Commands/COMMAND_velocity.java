package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_velocity implements CommandExecutor {

	private main plugin;

	public COMMAND_velocity(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("srmod")) {
				
				Player p = (Player) sender;
				
				if(args.length == 0) {
					
					if(plugin.velocity.contains(p.getName())) {
						plugin.velocity.remove(p.getName());
						p.sendMessage(plugin.prefix + "§3Deine Velocity wurde §aaktiviert§3!");	
					} else {
						plugin.velocity.add(p.getName());
						p.sendMessage(plugin.prefix + "§3Deine Velocity wurde §cdeaktiviert§3!");
					}
					
				} else 
					if(args.length == 1) {
						if(p.hasPermission("srdev")) {
							Player target = Bukkit.getPlayer(args[0]);
							
							if(target != null) {
								if(plugin.velocity.contains(target.getName())) {
									plugin.velocity.remove(target.getName());
									target.sendMessage(plugin.prefix + "§3Deine Velocity wurde §aaktiviert§3!");	
									p.sendMessage(plugin.prefix + "§3Die Velocity wurde für §6" + target.getName() + " §aaktiviert§3!");
								} else {
									plugin.velocity.add(target.getName());
									target.sendMessage(plugin.prefix + "§3Deine Velocity wurde §cdeaktiviert§3!");
									p.sendMessage(plugin.prefix + "§3Die Velocity wurde für §6" + target.getName() + " §cdeaktiviert§3!");
								}
							} else {
								p.sendMessage(plugin.prefix + "§3Dieser Spieler ist nicht §6online§3!");
							}
						}
					} else {
						p.sendMessage(plugin.prefix + "§cDu verwendest zu viele Argumente!");
					}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
