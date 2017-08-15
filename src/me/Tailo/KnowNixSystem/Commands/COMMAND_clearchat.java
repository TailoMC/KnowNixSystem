package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_clearchat implements CommandExecutor {

	private main plugin;

	public COMMAND_clearchat(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("sup") | sender.isOp()) {
			
			for(Player players : Bukkit.getOnlinePlayers()) {
				if(!players.hasPermission("sup")) {
					for(int i = 0; i < 100; i++) {
						players.sendMessage("");
					}
					players.sendMessage(plugin.prefix + "§cDer Chat wurde gelöscht!");
				} else {
					players.sendMessage(plugin.prefix + "§cDer Chat wurde von §4" + sender.getName() + " §cgelöscht!");
				}
			}
			
		}
		
		return true;
	}

}
