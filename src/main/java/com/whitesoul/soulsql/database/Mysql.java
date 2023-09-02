package com.whitesoul.soulsql.database;

import com.whitesoul.soulsql.util.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Mysql {
    private static HikariConfig config;
    private static File file;

    // 数据库连接池
    public static void createConfig(String name, Plugin plugin) {

        file = new File(plugin.getDataFolder(), name + ".properties.yml");
        if (!file.exists()) {
            Logger.info("数据库配置文件已创建");
            plugin.saveResource(name + ".properties", false);
        } else {
            Logger.info("数据库配置文件加载成功");
        }
        config = new HikariConfig(plugin.getDataFolder() + "\\" + name + ".properties");
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return new HikariDataSource(config).getConnection();
    }
    // 关闭数据库连接池
    public static void close() {
        new HikariDataSource(config).close();
    }
    // 获取数据库连接池
    public static HikariDataSource getHikariDataSource() {
        return new HikariDataSource(config);
    }
    // 获取数据库连接池配置
    public static HikariConfig getHikariConfig() {
        return config;
    }
}
