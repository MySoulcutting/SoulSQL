package com.whitesoul.soulsql;

import com.whitesoul.soulsql.util.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoulSQL extends JavaPlugin {
    public static Plugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Logger.info("SoulSQL插件已加载-By:WhiteSoul");
        Logger.info("欢迎您的使用!");
        Logger.info("当前版本:1.0.1");

    }

    @Override
    public void onDisable() {
    }
}
