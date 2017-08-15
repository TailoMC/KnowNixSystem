package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_globalmute implements CommandExecutor {

	private main plugin;

	public COMMAND_globalmute(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("sup") | sender.isOp()) {
			
			if(!plugin.globalmute) {
				plugin.globalmute = true;
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.hasPermission("sup")) {
						players.sendMessage(plugin.prefix + "§4Globalmute wurde von §b" + sender.getName() + " §aaktiviert§4!");
					} else {
						players.sendMessage(plugin.prefix + "§4Globalmute wurde §aaktiviert§4!");
					}
				}
			} else {
				plugin.globalmute = false;
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.hasPermission("sup")) {
						players.sendMessage(plugin.prefix + "§4Globalmute wurde von §b" + sender.getName() + " §cdeaktiviert§4!");
					} else {
						players.sendMessage(plugin.prefix + "§4Globalmute wurde §cdeaktiviert§4!");
					}
				}
			}
			
		}
		
		return true;
	}

}
