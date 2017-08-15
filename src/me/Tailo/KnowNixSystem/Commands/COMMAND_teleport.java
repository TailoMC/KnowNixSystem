package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class COMMAND_teleport implements CommandExecutor, Listener {

	private main plugin;

	public COMMAND_teleport(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("mod")) {
				if(args.length == 1) {
					
					if(p.hasPermission("srmod") && args[0].equalsIgnoreCase("all")) {
						
						for(Player players : Bukkit.getOnlinePlayers()) {
							players.teleport(p);
						}
						
						p.sendMessage(plugin.prefix + "§3Alle Spieler wurden zu dir teleportiert!");
						
					} else {
						
						Player target = Bukkit.getPlayer(args[0]);
						
						if(target != null) {
							
							p.teleport(target);
							
							p.sendMessage(plugin.prefix + "§3Du hast dich zu §6" + target.getDisplayName() + " §3teleportiert!");						
						} else {
							p.sendMessage(plugin.prefix + "§3Der Spieler §6" + args[0] + " §3ist nicht online!");
						}
						
					}
					
				} else 			
					if(args.length == 2) {
						
						if(p.hasPermission("mod")) {
							
							if(args[0].equalsIgnoreCase("here")) {							
								
								Player target = Bukkit.getPlayer(args[1]);
								
								if(target != null) {
									
									target.teleport(p);
									
									p.sendMessage(plugin.prefix + "§6" + target.getDisplayName() + " §3wurde zu dir teleportiert!");						
								} else {
									p.sendMessage(plugin.prefix + "§3Der Spieler §6" + args[1] + "§3 ist nicht online!");
								}
								
							} else {
								
								Player p1 = Bukkit.getPlayer(args[0]);
								
								if(p1 != null) {
									
									Player p2 = Bukkit.getPlayer(args[1]);
									
									if(p2 != null) {
										
										p1.teleport(p2);
										
										p.sendMessage(plugin.prefix + "§6" + p1.getDisplayName() + " §3wurde zu " + p2.getDisplayName() + " §3teleportiert!");	
										
									} else {
										p.sendMessage(plugin.prefix + "§3Der Spieler §6" + args[1] + "§3 ist nicht online!");
									}
									
								} else {
									p.sendMessage(plugin.prefix + "§3Der Spieler §6" + args[1] + "§3 ist nicht online!");
								}
								
							}
							
						}
						
					} else
						if(args.length == 3) {
							
							if(p.hasPermission("srmod")) {
								
								try {
									
									Location loc = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
									
									p.teleport(loc);
									
								} catch(Exception e) {
									p.sendMessage(plugin.prefix + "§cDu hast keine Zahlen angegeben!");
								}
								
							}
							
						} else
							if(args.length == 4) {
								
								if(p.hasPermission("srmod")) {
									
									try {
										
										World world = Bukkit.getWorld(args[3]);
										
										if(world != null) {
											
											Location loc = new Location(world, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
											
											p.teleport(loc);
											
										} else {
											p.sendMessage(plugin.prefix + "§cDiese Welt existiert nicht!");
											p.sendMessage(plugin.prefix + "§3Existierende Welten:");
											
											for(World worlds : Bukkit.getWorlds()) {
												p.sendMessage("§7- §6" + worlds.getName());
											}
										}
										
									} catch(Exception e) {
										p.sendMessage(plugin.prefix + "§cDu hast keine Zahlen angegeben!");
									}
									
								}
								
							} else {
								
								p.sendMessage("§3/tp <Spielername> §r- §6Teleportiere dich zu einem Spieler!");
								if(p.hasPermission("moderator")) {
									p.sendMessage("§3/tp here <Spielername> §r- §6Teleportiere einen Spieler zu dir!");
								}
								if(p.hasPermission("admin")) {
									p.sendMessage("§3/tp <X> <Y> <Z> [Welt] §r- §6Teleportiere dich zu einer Koordinate!");
									p.sendMessage("§3/tp all §r- §6Teleportiere alle Spieler zu dir!");
								}
								
							}
			}
						
		} else {
			sender.sendMessage("Du kannst den teleport Befehl nicht benutzen. Diese Funktion können nur Spieler Ingame nutzen!");
		}
		
		return true;
	}

}
