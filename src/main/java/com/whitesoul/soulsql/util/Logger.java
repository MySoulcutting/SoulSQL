package com.whitesoul.soulsql.util;

import com.whitesoul.soulsql.SoulSQL;
import org.bukkit.ChatColor;

public class Logger {
    // 普通消息
    public static void info(String msg) {
        SoulSQL.INSTANCE.getLogger().info(ChatColor.translateAlternateColorCodes('&',"&a[SoulSQL] &f" + msg));

    }
    // 警告消息
    public static void warn(String msg) {
        SoulSQL.INSTANCE.getLogger().warning(ChatColor.translateAlternateColorCodes('&',"&e[SoulSQL] &f" + msg));
    }
    // 错误消息
    public static void error(String msg) {
        SoulSQL.INSTANCE.getLogger().severe(ChatColor.translateAlternateColorCodes('&',"&c[SoulSQL] &f" + msg));
    }
}
