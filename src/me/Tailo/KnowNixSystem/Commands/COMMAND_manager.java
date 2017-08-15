package me.Tailo.KnowNixSystem.Commands;

import java.io.File;
import java.net.URL;

import me.Tailo.KnowNixSystem.System.main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class COMMAND_manager implements CommandExecutor {

	private static main plugin;

	@SuppressWarnings("static-access")
	public COMMAND_manager(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender | sender.hasPermission("srdev")) {
			
			if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("disableplugin")) {
					
					Plugin pl = plugin.getServer().getPluginManager().getPlugin(args[1]);
					
					if(pl != null) {
						
						if(pl.isEnabled()) {
							
							plugin.getServer().getPluginManager().disablePlugin(pl);
							
							sender.sendMessage(plugin.prefix + "§cDas Plugin §4" + pl.getName() + " §cwurde deaktiviert!");
							
						} else {
							sender.sendMessage(plugin.prefix + "§cDieses Plugin ist bereits deaktiviert!");
						}
						
					} else {
						sender.sendMessage(plugin.prefix + "§cDieses Plugin existiert nicht!");
					}
					
				} else
					if(args[0].equalsIgnoreCase("enableplugin")) {
						
						Plugin pl = plugin.getServer().getPluginManager().getPlugin(args[1]);
						
						if(pl != null) {
							
							if(!pl.isEnabled()) {
								
								plugin.getServer().getPluginManager().enablePlugin(pl);
								
								sender.sendMessage(plugin.prefix + "§cDas Plugin §4" + pl.getName() + " §cwurde aktiviert!");
								
							} else {
								sender.sendMessage(plugin.prefix + "§cDieses Plugin ist bereits aktiviert!");
							}
							
						} else {
							sender.sendMessage(plugin.prefix + "§cDieses Plugin existiert nicht!");
						}
						
					} else
						if(args[0].equalsIgnoreCase("dlplugin")) {
							
							download(sender, args[1]);
							
						}
				
			} else
				if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("plugin")) {
						
						if(sender instanceof Player) {
							
							Player p = (Player) sender;
							
							for(Plugin pl : plugin.getServer().getPluginManager().getPlugins()) {
								
								TextComponent tc = new TextComponent();
								if(pl.isEnabled()) {
									tc.setText("- §4" + pl.getName() + " §7[" + pl.getDescription().getVersion() + "] §a(aktiv)");
									tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/manager disableplugin " + pl.getName()));
									tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§4" + pl.getName() + " §cdeaktivieren!").create()));
								} else {
									tc.setText("- §4" + pl.getName() + " §7[" + pl.getDescription().getVersion() + "] §c(inaktiv)");
									tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/manager enableplugin " + pl.getName()));
									tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§4" + pl.getName() + " §caktivieren!").create()));
								}
								
								p.spigot().sendMessage(tc);
							}
							
						} else {
							sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
						}
						
					}
					
				} else {
					
					sender.sendMessage("§c/manager plugin");
					sender.sendMessage("§c/manager dlplugin (Link)");
					
				}
			
		} else {
			return false;
		}
		
		return true;
	}
	
	private static void download(CommandSender sender, String link) {
		
		String[] split = link.split("/");
		
		String name = split[split.length - 1];
		
		sender.sendMessage(plugin.prefix + "§e" + name + " §awird heruntergeladen...");
		
		try {
				
			URL url = new URL(link);
			
			File dataFolder = new File("plugins/");
			File dataFile = new File(dataFolder.getPath() + File.separator + name);
			
			FileUtils.copyURLToFile(url, dataFile);
			
			Bukkit.getPluginManager().loadPlugin(dataFile);
				
		} catch (Exception e) {
			sender.sendMessage(plugin.prefix + "§e" + name + " §ckonnte nicht heruntergeladen werden!");
			return;
		}
		
		sender.sendMessage(plugin.prefix + "§e" + name + " §awurde heruntergeladen und kann jetzt aktiviert werden!");
		
	}

}
