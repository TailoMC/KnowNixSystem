package me.Tailo.KnowNixSystem.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tailo.KnowNixSystem.System.main;

public class COMMAND_serverban implements CommandExecutor {

	private main plugin;

	public COMMAND_serverban(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
			
			sender.sendMessage(plugin.prefix + "§cGesperrte Spieler:");
			for(String name : plugin.banned) {
				sender.sendMessage("§c- §7" + name);
			}
			
		} else if(args.length == 1 && args[0].equalsIgnoreCase("ban")) {
			
			if(!plugin.banned.contains(args[1])) {
				
				plugin.banned.add(args[1].toLowerCase());
				
				sender.sendMessage(plugin.prefix + "§e" + args[1] + " §7wurde gesperrt!");
				
			} else {
				sender.sendMessage(plugin.prefix + "§e" + args[1] + " §cist bereits gesperrt!");
			}
			
		} else if(args.length == 1 && args[0].equalsIgnoreCase("unban")) {
			
			if(!plugin.banned.contains(args[1])) {
				
				plugin.banned.remove(args[1].toLowerCase());
				
				sender.sendMessage(plugin.prefix + "§e" + args[1] + " §7wurde entsperrt!");
				
			} else {
				sender.sendMessage(plugin.prefix + "§e" + args[1] + " §cist nicht gesperrt!");
			}
			
		} else {
			sender.sendMessage(plugin.prefix + "§c/serverban list");
			sender.sendMessage(plugin.prefix + "§c/serverban ban [Spielername]");
			sender.sendMessage(plugin.prefix + "§c/serverban unban [Spielername]");
		}
		
		return true;
	}

}
