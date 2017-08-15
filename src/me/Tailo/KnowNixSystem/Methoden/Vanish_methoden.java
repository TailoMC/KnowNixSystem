package me.Tailo.KnowNixSystem.Methoden;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.KnowNixSystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Vanish_methoden {
	
	private static main plugin;

	@SuppressWarnings("static-access")
	public Vanish_methoden(main main) {
		this.plugin = main;
	}
	
	private static HashMap<Scoreboard, HashMap<Player, Team>> team = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> run = new HashMap<>();
	private static HashMap<Player, List<Player>> van = new HashMap<>();

	public static void vanish(final Player p) {
		
		List<Player> vanplayers = new ArrayList<Player>();
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			
			if(players.spigot().getHiddenPlayers().contains(p)) {
				vanplayers.add(players);
			}
			
			if(!players.hasPermission("sup") && !players.hasPermission("content")) {
				
				players.hidePlayer(p);
				
			} else {
				
				Scoreboard board = players.getScoreboard();
				
				Team t = board.getTeam(p.getUniqueId().toString().substring(0, 16));
				
				if(t == null) {
					
					t = board.registerNewTeam(p.getUniqueId().toString().substring(0, 16));
					t.setSuffix(" §7[Vanish]");
					
					Team oldteam = p.getScoreboard().getEntryTeam(p.getName());
					
					if(oldteam != null) {
											
						t.setPrefix(oldteam.getPrefix());
						
						HashMap<Player, Team> map = team.get(players);
						
						if(map == null) {
							map = new HashMap<>();
						}
						
						map.put(p, oldteam);
						
						team.put(board, map);
						
					}
					
				}
				
				t.addEntry(p.getName());
				
			}
			
		}
		
		run.put(p, new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.canSee(p) && !players.hasPermission("sup") && !players.hasPermission("content")) {
						players.hidePlayer(p);
						if(van.get(p).contains(players)) {
							van.get(p).remove(players);
						}
					}
					
					Scoreboard board = players.getScoreboard();
					
					if(!board.getTeam(p.getUniqueId().toString().substring(0, 16)).getName().equals(board.getEntryTeam(p.getName()).getName())) {
						
						HashMap<Player, Team> map = team.get(board);
						
						if(map == null) {
							map = new HashMap<>();
						}
						
						map.put(p, board.getEntryTeam(p.getName()));
						
						team.put(board, map);
						
						board.getTeam(p.getUniqueId().toString().substring(0, 16)).setPrefix(board.getEntryTeam(p.getName()).getPrefix());
						board.getTeam(p.getUniqueId().toString().substring(0, 16)).addEntry(p.getName());
						
					}
				}
			}
		});
		run.get(p).runTaskTimer(plugin, 0, 20);
		
		van.put(p, vanplayers);
		
		p.sendMessage(plugin.prefix + "§4§lDu bist jetzt unsichtbar!");
	}
	
	public static void unvanish(Player p) {
		
		run.get(p).cancel();
		
		run.remove(p);
		
		List<Scoreboard> list = new ArrayList<>();
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(!van.get(p).contains(players)) {
				players.showPlayer(p);
			}
			
			if(players.hasPermission("sup") || players.hasPermission("content")) {
				
				Scoreboard board = players.getScoreboard();
				
				if(!list.contains(board)) {
					list.add(board);
				}
				
			}
			
		}
		
		for(Scoreboard boards : list) {
			
			Team t = boards.getTeam(p.getUniqueId().toString().substring(0, 16));
			
			if(t != null) {
				t.removeEntry(p.getName());
				t.unregister();
			}
			
			Team newteam = team.get(boards).get(p);
			
			if(newteam != null) {
				newteam.addEntry(p.getName());
			}
			
			team.get(boards).remove(p);
			
		}
		
		p.sendMessage(plugin.prefix + "§4§lDu bist jetzt sichtbar!");
		
	}
	
	public static boolean hasVanishEnabled(Player p) {
		
		ResultSet rs = MySQL.getResult("SELECT playername FROM vanish WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void setVanish(Player p) {
		
		MySQL.update("INSERT INTO vanish (UUID, playername) VALUES ('" + p.getUniqueId().toString() + "', '" + p.getName() + "')");
		
	}
	
	public static void unsetVanish(Player p) {
		
		MySQL.update("DELETE FROM vanish WHERE UUID = '" + p.getUniqueId() + "'");
		
	}
	
}
