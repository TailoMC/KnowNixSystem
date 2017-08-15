package me.Tailo.KnowNixSystem.Methoden;

import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AfkScheduler implements Runnable {
	
	private main plugin;

	public AfkScheduler(main main) {
		this.plugin = main;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		for(String s : plugin.afk.keySet()) {
			
			plugin.afk.put(s, plugin.afk.get(s) +1);
			
			if(plugin.afk.get(s) >= 120 * 2) {
				
				Player p = Bukkit.getPlayer(s);
				
				int value = (120 * 2 + 10 - plugin.afk.get(s));
				
				p.sendMessage(plugin.prefix + "§eDu wirst in " + value + " Sekunden auf Grund von Inaktivität gekickt!");
				
				p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
				
			}
			
			if(plugin.afk.get(s) >= 120 * 2 + 10) {
				
				Bukkit.getPlayer(s).kickPlayer("Afk");
				
			}
			
		}
		
	}

}
