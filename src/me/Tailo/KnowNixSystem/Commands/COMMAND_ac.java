package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.util.Vector;

public class COMMAND_ac implements CommandExecutor {

	private main plugin;
	
	private static String prefix = "§c§lAntiCheat §8» §r";

	public COMMAND_ac(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("mod")) {
				
				Player p = (Player) sender;
				
				if(args.length > 0) {
					
					if(args[0].equalsIgnoreCase("info") && args.length == 2) {
						
						PermissionAttachment attachment = p.addAttachment(plugin);
						attachment.setPermission("spartan.info", true);
						
						p.performCommand("spartan info " + args[1]);
						
						attachment.setPermission("spartan.info", false);
						
					} else if(args[0].equalsIgnoreCase("crash") && args.length == 2 && p.hasPermission("srdev")) {
						
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target != null) {
							
							PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, 1000);
							
							((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
							
							p.sendMessage(prefix + "§c" + target.getName() + "'s §aClient wurde gecrasht!");
							
						} else {
							p.sendMessage(prefix + "§cDieser Spieler ist nicht online");
						}
						
					} else if(args[0].equalsIgnoreCase("vel") && args.length == 2) {
						
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target != null) {
							
							Vector v = target.getLocation().getDirection().normalize().multiply(-1D);
							v.setY(1.0D);
							
							target.setVelocity(v);
							
							p.sendMessage(prefix + "§aVelocity wurde ausgeführt!");
							
						} else {
							p.sendMessage(prefix + "§cDieser Spieler ist nicht online");
						}
						
					} else if(args[0].equalsIgnoreCase("check") && args.length == 2 && p.hasPermission("srdev")) {
					
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target != null) {
							
							if(target.hasPermission("spartan.bypass")) {
								
								PermissionAttachment attachment = target.addAttachment(plugin);
								attachment.setPermission("spartan.bypass", false);
								attachment.setPermission("reflex.bypass", false);
								
								p.sendMessage(prefix + target.getDisplayName() + " §awird jetzt vom AntiCheat erfasst!");
								
							} else {
								
								PermissionAttachment attachment = target.addAttachment(plugin);
								attachment.setPermission("spartan.bypass", true);
								attachment.setPermission("reflex.bypass", true);
								
								p.sendMessage(prefix + target.getDisplayName() + " §awird jetzt nicht mehr vom AntiCheat erfasst!");
								
							}
							
						} else {
							p.sendMessage(prefix + "§cDieser Spieler ist nicht online");
						}
						
					} else {
						sendUsage(p);
					}
					
				} else {
					sendUsage(p);
				}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}
	
	private static void sendUsage(Player p) {		

		p.sendMessage(prefix + "§c/ac info <Spielername> §7Hackinformationen");
		p.sendMessage(prefix + "§c/ac vel <Spielername> §7Knockback ausführen");
		if(p.hasPermission("srdev")) {
			p.sendMessage(prefix + "§c/ac crash <Spielername> §7Client crashen");
			p.sendMessage(prefix + "§c/ac check <Spielername> §7Aktiviere/Deaktiviere das erfassen des Anticheats für einen Spieler");
		}
		
	}

}
