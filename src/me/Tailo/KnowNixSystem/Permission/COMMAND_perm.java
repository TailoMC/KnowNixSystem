package me.Tailo.KnowNixSystem.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import me.Tailo.KnowNixSystem.Permission.UUIDFetcher;
import me.Tailo.KnowNixSystem.Permission.Permission_methoden.Group;
import me.Tailo.KnowNixSystem.System.main;

public class COMMAND_perm implements CommandExecutor {

	@SuppressWarnings("unused")
	private main plugin;

	public COMMAND_perm(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender) {
			
			if(args.length == 3) {
				
				if(args[1].equalsIgnoreCase("setgroup")) {
					
					String uuid = UUIDFetcher.getUUID(args[0]).toString();
					
					Group group = Permission_methoden.getGroup(args[2]);
							
					Permission_methoden.setGroup(uuid, group);
					
				}
				
			}
			
		}
		
		return true;
	}

}
