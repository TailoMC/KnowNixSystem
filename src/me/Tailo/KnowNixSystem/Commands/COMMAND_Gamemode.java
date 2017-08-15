package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_Gamemode implements CommandExecutor {

	@SuppressWarnings("unused")
	private main plugin;

	public COMMAND_Gamemode(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("srdev"))  {
				if(args.length == 0) {
					if(p.getGameMode() == GameMode.CREATIVE) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Survival' §3Modus!");
						return true;
					}
					
					if(p.getGameMode() == GameMode.SURVIVAL) {
						p.setGameMode(GameMode.CREATIVE);
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Kreativ' §3Modus!");
						return true;
					}
					
					if(p.getGameMode() == GameMode.ADVENTURE) {
						p.setGameMode(GameMode.CREATIVE);
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Kreativ' §3Modus!");
						return true;
					}
				} else
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("s")) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Survival' §3Modus!");
						} else 
							if(args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("c")) {
								p.setGameMode(GameMode.CREATIVE);
								p.setAllowFlight(true);
								p.setFlying(true);
								p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Kreativ' §3Modus!");
							} else
								if(args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("a")) {
									p.setGameMode(GameMode.ADVENTURE);
									p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Adventure' §3Modus!");
								} else
									if(args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spec")) {
										p.setGameMode(GameMode.SPECTATOR);
										p.sendMessage("§3[§6Gamemode§3] §3Du bist jetzt im §e'Zuschauer' §3Modus!");
									} else {
										p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Gamemode §3ist nicht vorhanden!");
									}
					} else {
						if(args.length == 2) {
							if(args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("s")) {
								Player target = Bukkit.getPlayer(args[1]);
								
								if(target != null) {
									target.setGameMode(GameMode.SURVIVAL);
									
									p.sendMessage("§3[§6Gamemode§3] §3Du hast dem Spieler §6" + target.getDisplayName() + " §3in den §e'Survival' §3Modus gesetzt!");
									target.sendMessage("§3[§6Gamemode§3] §3Du wurdest vom Spieler §6" + p.getDisplayName() + " §3in den §e'Survival' §3Modus gesetzt!");
								} else {
									p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Spieler §3ist nicht §6Online§3!");
								}
								
							} else
								if(args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("c")) {
									Player target = Bukkit.getPlayer(args[1]);
									
									if(target != null) {
										target.setGameMode(GameMode.CREATIVE);
										
										target.setAllowFlight(true);
										target.setFlying(true);
										
										p.sendMessage("§3[§6Gamemode§3] §3Du hast dem Spieler §6" + target.getDisplayName() + " §3in den §e'Kreativ' §3Modus gesetzt!");
										target.sendMessage("§3[§6Gamemode§3] §3Du wurdest vom Spieler §6" + p.getDisplayName() + " §3in den §e'Kreativ' §3Modus gesetzt!");
									} else {
										p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Spieler §3ist nicht §6Online§3!");
									}
									
								} else
									if(args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("a")) {
										Player target = Bukkit.getPlayer(args[1]);
										
										if(target != null) {
											target.setGameMode(GameMode.ADVENTURE);
											
											p.sendMessage("§3[§6Gamemode§3] §3Du hast dem Spieler §6" + target.getDisplayName() + " §3in den §e'Adventure' §3Modus gesetzt!");
											target.sendMessage("§3[§6Gamemode§3] §3Du wurdest vom Spieler §6" + p.getDisplayName() + " §3in den §e'Adventure' §3Modus gesetzt!");
										} else {
											p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Spieler §3ist nicht §6Online§3!");
										}										
									} else
										if(args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spec")) {
											Player target = Bukkit.getPlayer(args[1]);
											
											if(target != null) {
												target.setGameMode(GameMode.SPECTATOR);
												
												p.sendMessage("§3[§6Gamemode§3] §3Du hast dem Spieler §6" + target.getDisplayName() + " §3in den §e'Zuschauer' §3Modus gesetzt!");
												target.sendMessage("§3[§6Gamemode§3] §3Du wurdest vom Spieler §6" + p.getDisplayName() + " §3in den §e'Zuschauer' §3Modus gesetzt!");
											} else {
												p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Spieler §3ist nicht §6Online§3!");
											}
										} else {
											p.sendMessage("§3[§6Gamemode§3] §3Dieser §6Gamemode §3ist nicht vorhanden!");
										}
						} else
							if(args.length > 2) {
								p.sendMessage("§3[§6Gamemode§3] §3Du verwendest zu viele §6Argumente§3!");
							}
					}
			}
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
