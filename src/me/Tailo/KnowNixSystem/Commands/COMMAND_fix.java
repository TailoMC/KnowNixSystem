package me.Tailo.KnowNixSystem.Commands;

import java.util.ArrayList;
import java.util.List;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_fix implements CommandExecutor {

	private main plugin;

	public COMMAND_fix(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			List<Player> list = new ArrayList<>();
			
			for(Player players : Bukkit.getOnlinePlayers()) {
				
				if(p.canSee(players)) {
					list.add(players);
					p.hidePlayer(players);
				}
				
			}
			
			for(Player players : list) {
				p.showPlayer(players);
			}
			
			p.sendMessage(plugin.prefix + "§3Alle Spieler sollten jetzt sichtbar sein!");
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
