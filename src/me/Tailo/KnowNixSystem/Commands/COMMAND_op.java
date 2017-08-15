package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_op implements CommandExecutor {

	private main plugin;

	public COMMAND_op(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("srdev")) {
			
			if(args.length == 0) {
				
				if(sender.isOp()) {
					sender.setOp(false);					
					sender.sendMessage(plugin.prefix + "§9Du wurdest gedeopped!");
				} else {
					sender.setOp(true);					
					sender.sendMessage(plugin.prefix + "§9Du wurdest geopped!");
				}
				
			} else {
				
				if(args.length == 1) {
					
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					
					if(target.isOp()) {
						target.setOp(false);
						
						sender.sendMessage(plugin.prefix + "§6" + target.getName() + " §9wurde gedeopped!");
						
						if(target.isOnline()) {
							((Player) target).sendMessage(plugin.prefix + "§9Du wurdest gedeopped!");
						}
					} else {
						target.setOp(true);
						
						sender.sendMessage(plugin.prefix + "§6" + target.getName() + " §9wurde geopped!");
						
						if(target.isOnline()) {
							((Player) target).sendMessage(plugin.prefix + "§9Du wurdest geopped!");
						}
					}
					
				}
				
			}
			
		}
		
		return true;
	}

}
