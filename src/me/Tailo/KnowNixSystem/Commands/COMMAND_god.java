package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class COMMAND_god implements CommandExecutor, Listener {

	private main plugin;

	public COMMAND_god(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("srmod")) {
				if(args.length == 0) {
					if(!plugin.god.contains(p.getName())) {
						plugin.god.add(p.getName());
						p.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde §aaktiviert§3!");				
					} else {
						plugin.god.remove(p.getName());
						p.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde §cdeaktiviert§3!");
					}
				} else
					if(args.length == 1) {
						if(p.hasPermission("srdev")) {
							Player target = Bukkit.getPlayer(args[0]);
							
							if(target != null) {
								if(!plugin.god.contains(target.getName())) {
									plugin.god.add(target.getName());
									target.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde für dich §aaktiviert§3!");
									p.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde für §6" + target.getName() + " §aaktiviert§3!");
								} else {
									plugin.god.remove(target.getName());
									target.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde für dich §cdeaktiviert§3!");
									p.sendMessage(plugin.prefix + "§3Die Unsterblichkeit wurde für §6" + target.getName() + " §cdeaktiviert§3!");
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
