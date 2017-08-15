package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_fly implements CommandExecutor {

	private main plugin;

	public COMMAND_fly(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("srmod")) {
				if(args.length == 0) {
					if(!p.getAllowFlight()) {
						p.setAllowFlight(true);				
						p.sendMessage(plugin.prefix + "§3Der Flugmodus wurde §aaktiviert§3!");				
					} else {
						p.setAllowFlight(false);
						p.sendMessage(plugin.prefix + "§3Der Flugmodus wurde §cdeaktiviert§3!");
					}
				} else
					if(args.length == 1) {
						if(p.hasPermission("srdev")) {
							Player target = Bukkit.getPlayer(args[0]);
							
							if(target != null) {
								if(!target.getAllowFlight()) {
									target.setAllowFlight(true);
									target.sendMessage(plugin.prefix + "§3Der Flugmodus wurde für dich §aaktiviert§3!");
									p.sendMessage(plugin.prefix + "§3Der Flugmodus wurde für §6" + target.getName() + " §aaktiviert§3!");
								} else {
									target.setAllowFlight(false);
									target.sendMessage(plugin.prefix + "§3Der Flugmodus wurde für dich §cdeaktiviert§3!");
									p.sendMessage(plugin.prefix + "§3Der Flugmodus wurde für §6" + target.getName() + " §cdeaktiviert§3!");
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
