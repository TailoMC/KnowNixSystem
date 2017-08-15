package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.Methoden.Vanish_methoden;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_vanish implements CommandExecutor {

	@SuppressWarnings("unused")
	private main plugin;

	public COMMAND_vanish(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("sup") || p.hasPermission("content")) {
				if(!Vanish_methoden.hasVanishEnabled(p)) {
					
					Vanish_methoden.vanish(p);
					
					Vanish_methoden.setVanish(p);
					
				} else {
					
					Vanish_methoden.unvanish(p);
					
					Vanish_methoden.unsetVanish(p);
					
				}
			}
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		return true;
	}

}
