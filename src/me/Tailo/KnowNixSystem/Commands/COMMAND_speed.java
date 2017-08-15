package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_speed implements CommandExecutor {

	private main plugin;

	public COMMAND_speed(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("srmod")) {
				
				Player p = (Player) sender;
				
				if(args.length == 0) {
					
					if(p.isFlying()) {
						p.setFlySpeed(0.1F);
						p.sendMessage(plugin.prefix + "§3Dein Fly-Speed wurde zurückgesetzt!");
					} else {
						p.setWalkSpeed(0.2F);
						p.sendMessage(plugin.prefix + "§3Dein Walk-Speed wurde zurückgesetzt!");
					}
					
				} else 
					if(args.length == 1) {
						
						try {
							float speed = Float.parseFloat(args[0]);
							
							if(p.isFlying()) {
								p.setFlySpeed((float) (speed * 0.1));
								p.sendMessage(plugin.prefix + "§3Dein Fly-Speed wurde auf §6" + speed + " §3gesetzt!");
							} else {
								p.setWalkSpeed((float) (speed * 0.2));
								p.sendMessage(plugin.prefix + "§3Dein Walk-Speed wurde auf §6" + speed + " §3gesetzt!");
							}
						} catch(Exception e) {
							p.sendMessage(plugin.prefix + "§cDu musst eine kleinere §eZahl §cangeben!");
						}
						
					} else {
						p.sendMessage(plugin.prefix + "/speed [Vielfache der Normalgeschwindigkeit]");
					}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
