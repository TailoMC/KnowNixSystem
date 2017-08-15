package me.Tailo.KnowNixSystem.Permission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.KnowNixSystem.System.main;

public class Permission_methoden {
	
	private static main plugin;

	@SuppressWarnings("static-access")
	public Permission_methoden(main main) {
		this.plugin = main;
	}
	
	public static void loadPermissions(Player p, Group group) {
		
		loadExtraPermissions(p, group);
		
	}
	
	public static void setGroup(String uuid, Group group) {
		
		if(group != Group.SPIELER) {
			
			if(isUserExists(uuid)) {
				
				MySQL.update("UPDATE permissions SET UUID = '" + uuid + "',playername = '" + UUIDFetcher.getName(UUID.fromString(uuid)) + "',rank = '" + getGroupName(group) + "' WHERE UUID = '" + uuid + "'");
				
			} else {
				
				MySQL.update("INSERT INTO permissions (UUID, playername, rank) VALUES ('" + uuid + "', '" + UUIDFetcher.getName(UUID.fromString(uuid)) + "', '" + getGroupName(group) + "')");
				
			}
			
		} else {
			
			if(isUserExists(uuid)) {
				
				MySQL.update("DELETE FROM permissions WHERE UUID = '" + uuid + "'");
				
			}
			
		}
		
	}
	
	public static Group getGroup(Player p) {
		
		ResultSet rs = MySQL.getResult("SELECT rank FROM permissions WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				String groupname = rs.getString("rank");
				
				Group group = Permission_methoden.getGroup(groupname);
				
				return group;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Group.SPIELER;
		
	}
	
	public static String getGroupName(Group group) {
		
		if(group == Group.ADMIN) return "Admin";
		if(group == Group.SRDEV) return "SrDev";
		if(group == Group.DEV) return "Dev";
		if(group == Group.SRMOD) return "SrMod";
		if(group == Group.MOD) return "Mod";
		if(group == Group.SUP) return "Sup";
		if(group == Group.CONTENT) return "Content";
		if(group == Group.BUILDER) return "Builder";
		if(group == Group.VIP) return "VIP";
		if(group == Group.PREMIUMPLUS) return "PremiumPlus";
		if(group == Group.PREMIUM) return "Premium";
		if(group == Group.SPIELER) return "Spieler";
		
		return "NULL";
		
	}
	
	public static Group getGroup(String string) {
		
		if (string.equalsIgnoreCase("premium")) {
			return Group.PREMIUM;
		} else if (string.equalsIgnoreCase("premiumplus")) {
			return Group.PREMIUMPLUS;
		} else if (string.equalsIgnoreCase("vip")) {
			return Group.VIP;
		} else if (string.equalsIgnoreCase("builder")) {
			return Group.BUILDER;
		} else if (string.equalsIgnoreCase("content")) {
			return Group.CONTENT;
		} else if (string.equalsIgnoreCase("sup")) {
			return Group.SUP;
		} else if (string.equalsIgnoreCase("mod")) {
			return Group.MOD;
		} else if (string.equalsIgnoreCase("srmod")) {
			return Group.SRMOD;
		} else if (string.equalsIgnoreCase("dev")) {
			return Group.DEV;
		} else if (string.equalsIgnoreCase("srdev")) {
			return Group.SRDEV;
		} else if (string.equalsIgnoreCase("admin")) {
			return Group.ADMIN;
		}
		
		return Group.SPIELER;
		
	}
	
	public static void loadExtraPermissions(Player p, Group group) {
		
		if(group == Group.ADMIN) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("mod", true);
			attachment.setPermission("srmod", true);
			attachment.setPermission("dev", true);
			attachment.setPermission("srdev", true);
			attachment.setPermission("admin", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.SRDEV) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("mod", true);
			attachment.setPermission("srmod", true);
			attachment.setPermission("dev", true);
			attachment.setPermission("srdev", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.DEV) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("mod", true);
			attachment.setPermission("srmod", true);
			attachment.setPermission("dev", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.SRMOD) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("mod", true);
			attachment.setPermission("srmod", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.MOD) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("mod", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.SUP) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("sup", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			
		}
		if(group == Group.CONTENT) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("content", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		if(group == Group.BUILDER) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("builder", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		if(group == Group.VIP) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("vip", true);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("spartan.bypass", true);
//			attachment.setPermission("reflex.bypass", true);
			attachment.setPermission("spartan.reconnect", true);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		if(group == Group.PREMIUMPLUS) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("premium", true);
			attachment.setPermission("premiumplus", true);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		if(group == Group.PREMIUM) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("premium", true);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		if(group == Group.SPIELER) {
			
			PermissionAttachment attachment = p.addAttachment(plugin);
			attachment.setPermission("BungeeSigns.use", true);
			
		}
		
	}
	
	public enum Group {
		
		SPIELER, PREMIUM, PREMIUMPLUS, VIP, BUILDER, CONTENT, SUP, MOD, SRMOD, DEV, SRDEV, ADMIN
		
	}
	
	private static boolean isUserExists(String uuid) {		
		try {
			ResultSet rs = MySQL.getResult("SELECT rank FROM permissions WHERE UUID = '" + uuid + "'");
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
}
