package me.Tailo.KnowNixSystem.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.Tailo.KnowNixSystem.Commands.COMMAND_Gamemode;
import me.Tailo.KnowNixSystem.Commands.COMMAND_ac;
import me.Tailo.KnowNixSystem.Commands.COMMAND_announce;
import me.Tailo.KnowNixSystem.Commands.COMMAND_back;
import me.Tailo.KnowNixSystem.Commands.COMMAND_clearchat;
import me.Tailo.KnowNixSystem.Commands.COMMAND_fix;
import me.Tailo.KnowNixSystem.Commands.COMMAND_fly;
import me.Tailo.KnowNixSystem.Commands.COMMAND_forcefield;
import me.Tailo.KnowNixSystem.Commands.COMMAND_freeze;
import me.Tailo.KnowNixSystem.Commands.COMMAND_giveall;
import me.Tailo.KnowNixSystem.Commands.COMMAND_globalmute;
import me.Tailo.KnowNixSystem.Commands.COMMAND_god;
import me.Tailo.KnowNixSystem.Commands.COMMAND_kck;
import me.Tailo.KnowNixSystem.Commands.COMMAND_manager;
import me.Tailo.KnowNixSystem.Commands.COMMAND_op;
import me.Tailo.KnowNixSystem.Commands.COMMAND_serverban;
import me.Tailo.KnowNixSystem.Commands.COMMAND_speed;
import me.Tailo.KnowNixSystem.Commands.COMMAND_teleport;
import me.Tailo.KnowNixSystem.Commands.COMMAND_vanish;
import me.Tailo.KnowNixSystem.Commands.COMMAND_velocity;
import me.Tailo.KnowNixSystem.Commands.SYSTEMCOMMAND_syscmd;
import me.Tailo.KnowNixSystem.Listener.AsyncPlayerChatEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.EntityDamageEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerAfkEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerCommandPreprocessEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerFreezeEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerJoinEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerLoginEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerQuitEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerTeleportEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.PlayerVelocityEvent_Listener;
import me.Tailo.KnowNixSystem.Listener.ServerListPingEvent_Listener;
import me.Tailo.KnowNixSystem.Methoden.Vanish_methoden;
import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.KnowNixSystem.Permission.COMMAND_perm;
import me.Tailo.KnowNixSystem.Permission.Permission_methoden;
import me.Tailo.KnowNixSystem.Permission.PlayerRecivePermissionEvent_Listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class main extends JavaPlugin{
	
	//==========[int]==========
	public int afkrun;
	
	//==========[ArrayList]==========
	public ArrayList<String> invisible = new ArrayList<String>();
	
	public ArrayList<String> god = new ArrayList<String>();
	public ArrayList<String> freeze = new ArrayList<String>();
	public ArrayList<String> velocity = new ArrayList<String>();
	public ArrayList<Player> saving = new ArrayList<Player>();
	
	public ArrayList<String> banned = new ArrayList<>();
	
	//==========[String]==========
	public String prefix = "§6System §8» §r";
	
	//==========[HashMap]==========
	public static HashMap<String, Integer> afk = new HashMap<>();	
	public HashMap<UUID, Location> back = new HashMap<>();
	
	public HashMap<Player, BukkitRunnable> forcefield = new HashMap<Player, BukkitRunnable>();	
	
	//==========[Boolean]==========
	public boolean globalmute;
	
	public static main instance;
	
	public void onEnable() {
		
		instance = this;
		
		getCommand("teleport").setExecutor(new COMMAND_teleport(this));
		getCommand("gamemode").setExecutor(new COMMAND_Gamemode(this));
		getCommand("fly").setExecutor(new COMMAND_fly(this));
		getCommand("god").setExecutor(new COMMAND_god(this));
		getCommand("vanish").setExecutor(new COMMAND_vanish(this));
		getCommand("clearchat").setExecutor(new COMMAND_clearchat(this));
		getCommand("globalmute").setExecutor(new COMMAND_globalmute(this));
		getCommand("perm").setExecutor(new COMMAND_perm(this));
		getCommand("manager").setExecutor(new COMMAND_manager(this));
		getCommand("freeze").setExecutor(new COMMAND_freeze(this));
		getCommand("speed").setExecutor(new COMMAND_speed(this));
		getCommand("back").setExecutor(new COMMAND_back(this));
		getCommand("op").setExecutor(new COMMAND_op(this));
		getCommand("velocity").setExecutor(new COMMAND_velocity(this));
		getCommand("announce").setExecutor(new COMMAND_announce(this));
		getCommand("giveall").setExecutor(new COMMAND_giveall(this));
		getCommand("kck").setExecutor(new COMMAND_kck(this));
		getCommand("forcefield").setExecutor(new COMMAND_forcefield(this));
		getCommand("fix").setExecutor(new COMMAND_fix(this));
		getCommand("serverban").setExecutor(new COMMAND_serverban(this));
		getCommand("ac").setExecutor(new COMMAND_ac(this));
		
		getCommand("syscmd").setExecutor(new SYSTEMCOMMAND_syscmd(this));
		
		new PlayerJoinEvent_Listener(this);
		new PlayerQuitEvent_Listener(this);
		new PlayerCommandPreprocessEvent_Listener(this);
		new AsyncPlayerChatEvent_Listener(this);
		new PlayerRecivePermissionEvent_Listener(this);
		new PlayerAfkEvent_Listener(this);
		new PlayerFreezeEvent_Listener(this);
		new EntityDamageEvent_Listener(this);
		new PlayerTeleportEvent_Listener(this);
		new PlayerVelocityEvent_Listener(this);
		new PlayerLoginEvent_Listener(this);
		new ServerListPingEvent_Listener(this);
		
		new Vanish_methoden(this);
		
		new Permission_methoden(this);
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		MySQL.connect();
		
	}
	
	public void onDisable() {
		
		MySQL.disconnect();
		
	}
}
