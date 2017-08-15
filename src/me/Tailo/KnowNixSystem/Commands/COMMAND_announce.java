package me.Tailo.KnowNixSystem.Commands;

import me.Tailo.KnowNixSystem.System.main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class COMMAND_announce implements CommandExecutor {
	
	private main plugin;

	public COMMAND_announce(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("mod")) {
			
			if(args.length != 0) {
				
				try {
					
					String msg = "";
					for(String s : args) {
						msg = msg + " " + s;
					}
					String zeile1 = ChatColor.translateAlternateColorCodes('&', msg.split(";")[0]);
					String zeile2 = ChatColor.translateAlternateColorCodes('&', msg.split(";")[1]);
					
					IChatBaseComponent titlecomp = ChatSerializer.a("{'text':'" + zeile1 + "'}");
					IChatBaseComponent subtitlecomp = ChatSerializer.a("{'text':'" + zeile2 + "'}");
					PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlecomp);
					PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitlecomp);
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						PlayerConnection con = ((CraftPlayer) players).getHandle().playerConnection;
						con.sendPacket(titlePacket);
						con.sendPacket(subtitlePacket);
					}
					
				} catch(Exception e) {
					sender.sendMessage(plugin.prefix + "§c/announce [Zeile1]§4;§c[Zeile2]");
				}
				
			} else {
				sender.sendMessage(plugin.prefix + "§c/announce [Zeile1];[Zeile2]");
			}
			
		}
		
		return true;
	}

}
