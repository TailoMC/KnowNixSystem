package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class COMMAND_freeze implements CommandExecutor {

	private main plugin;

	public COMMAND_freeze(main main) {
		this.plugin = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("mod")) {
			
			if(args.length == 1) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target != null) {
					
					if(plugin.freeze.contains(target.getName())) {
						plugin.freeze.remove(target.getName());

						target.sendMessage(plugin.prefix + "§9Du bist jetzt nicht mehr eingefroren!");
						sender.sendMessage(plugin.prefix + "§e" + target.getName() + " §9ist jetzt nicht mehr eingefroren!");
					} else {
						
						target.sendMessage(plugin.prefix + "§9Du wurdest eingefroren!");
						target.sendMessage(plugin.prefix + "§4Wenn du den Server verlässt kann das als §eBan §4enden!");
						sender.sendMessage(plugin.prefix + "§e" + target.getName() + " §9wurde eingefroren!");
						
						Vector v = new Vector(0, -1, 0);
						
						BukkitRunnable run = new BukkitRunnable() {
							
							@Override
							public void run() {
								if(!target.isOnGround()) {
									target.setVelocity(v);
								} else {
									cancel();
									plugin.freeze.add(target.getName());
								}
							}
						};
						run.runTaskTimer(plugin, 0, 1);
						
					}
					
				} else {
					sender.sendMessage(plugin.prefix + "§e" + args[0] + " §cist nicht online!");
				}
				
			} else {
				sender.sendMessage(plugin.prefix + "§c/freeze [Spielername]");
			}
			
		}
		
		return true;
	}

}
