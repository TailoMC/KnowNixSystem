package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class COMMAND_forcefield implements CommandExecutor {

	private static main plugin;

	@SuppressWarnings("static-access")
	public COMMAND_forcefield(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("srmod")) {
				
				Player p = (Player) sender;
				
				if(args.length == 0) {
					
					if(!plugin.saving.contains(p)) {
						plugin.saving.add(p);
						
						enableForcefield(p, 5);
						
						p.sendMessage(plugin.prefix + "§3Dein Forcefield wurde §aaktiviert§3!");
						
					} else {
						
						plugin.saving.remove(p);
						
						plugin.forcefield.get(p).cancel();
						plugin.forcefield.remove(p);
						
						p.sendMessage(plugin.prefix + "§3Dein Forcefield wurde §cdeaktiviert§3!");
						
					}
					
				} else
					if(args.length == 1) {
						
						if(!plugin.saving.contains(p)) {
							
							try {
								
								plugin.saving.add(p);
								
								enableForcefield(p, Integer.parseInt(args[0]));
								
								p.sendMessage(plugin.prefix + "§3Dein Forcefield wurde mit dem Radius §6" + args[0] + " §aaktiviert§3!");
								
							} catch(Exception e) {
								p.sendMessage(plugin.prefix + "§cDu hast keine Zahlen angegeben!");
							}
							
						} else {
							
							try {
								
								int radius = Integer.parseInt(args[0]);
								
								plugin.forcefield.get(p).cancel();
								plugin.forcefield.remove(p);
								
								enableForcefield(p, radius);
								
								p.sendMessage(plugin.prefix + "§3Dein Forcefield-Radius wurde auf §6" + args[0] + " §3gesetzt!");
								
							} catch(Exception e) {
								p.sendMessage(plugin.prefix + "§cDu hast keine Zahlen angegeben!");
							}
							
						}
						
					}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}
	
	private static void enableForcefield(Player p, int radius) {
		
		plugin.forcefield.put(p, new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Entity en: p.getNearbyEntities(radius, radius, radius)) {
					if(en instanceof Player) {
						Player target = (Player) en;
						
						double ax = p.getLocation().getX();
						double ay = p.getLocation().getY();
						double az = p.getLocation().getZ();
						
						double bx = target.getLocation().getX();
						double by = target.getLocation().getY();
						double bz = target.getLocation().getZ();
						
						double x = bx - ax;
						double y = by - ay;
						double z = bz - az;
						
						Vector v = new Vector(x, y, z).normalize().multiply(2D).setY(1D);
						
						if(target.isOp() | target.hasPermission("builder")| target.hasPermission("content") | target.hasPermission("sup")){
							return;
						} else {
							
							if(target.getVehicle() != null) {
								if(target.getVehicle() instanceof Boat) {
									target.getVehicle().remove();
								}
								target.teleport(target.getLocation().add(0, 1, 0));
							}
							
							target.setVelocity(v);

						}
					}
				}
				
			}
		});
		plugin.forcefield.get(p).runTaskTimer(plugin, 0, (long) (20*0.1));
		
	}

}
