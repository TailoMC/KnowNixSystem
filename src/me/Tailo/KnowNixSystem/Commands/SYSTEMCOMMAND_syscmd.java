package me.Tailo.KnowNixSystem.Commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SYSTEMCOMMAND_syscmd implements CommandExecutor {

	private static main plugin;

	@SuppressWarnings("static-access")
	public SYSTEMCOMMAND_syscmd(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender) {
			
			Player target = Bukkit.getPlayer(args[1]);
			
			if(args[0].equals("punish")) {
				
				String reason = "";
				for(int i = 2; i < args.length; i++) {
					reason = reason + args[i] + " ";
				}
				
				send(target, "punish", reason);
				
			} else
				if(args[0].equals("notify")) {
					
					String msg = "";
					for(int i = 2; i < args.length; i++) {
						msg = msg + args[i] + " ";
					}
					
					send(target, "notify", msg);
					
				}
			
		}
		
		return true;
	}
	
	private static void send(Player p, String channel, String input) {
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF(channel);
			out.writeUTF(input);
		} catch(Exception ex) {
		}
		
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
		
	}

}
