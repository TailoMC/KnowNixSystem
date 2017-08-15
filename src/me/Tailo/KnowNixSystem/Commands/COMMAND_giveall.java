package me.Tailo.KnowNixSystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tailo.KnowNixSystem.System.main;

public class COMMAND_giveall implements CommandExecutor {

	private main plugin;

	public COMMAND_giveall(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("srdev")) {
				
				if(args.length == 0) {
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						if(players != p) {
							players.getInventory().addItem(p.getItemInHand());
						}
					}
					
					p.sendMessage(plugin.prefix + "§3Allen Spielern wurde das Item in deiner Hand gegeben!");
					
				} else {
					
					p.sendMessage(plugin.prefix + "§3/giveall §7- §6Gibt allen Spielern das Item in deiner Hand");
					
				}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
