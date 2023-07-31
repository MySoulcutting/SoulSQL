package com.whitesoul.soulsql;

import com.whitesoul.soulsql.database.Mysql;
import com.whitesoul.soulsql.database.SQL;
import com.whitesoul.soulsql.util.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


import java.sql.ResultSet;
import java.sql.SQLException;

public final class SoulSQL extends JavaPlugin {
    public static Plugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Logger.info("SoulSQL插件已加载-By:WhiteSoul");
        Logger.info("欢迎您的使用!");
        // 创建数据库连接池配置
        Mysql.createConfig("mysql",this);// 创建数据库表
        SQL.createTable("test", new String[]{"id", "name", "age"},new String[]{"int","varchar(255)","int"},new String[]{"NOT NULL","NOT NULL","NOT NULL"});
        // 插入数据
        SQL.insert("test",new String[]{"id","name","age"},new String[]{"1","WhiteSoul","18"});
        // 更新数据
        SQL.update("name","SoulSoul","test","id","1");
        // 删除数据
        SQL.delete("test","id","1");
            // 查询数据
        ResultSet resultSet = null;
        try {
            resultSet = SQL.queruy("name, age", "test", "id", "2");
            while (resultSet.next()) {
                Logger.info(resultSet.getString("name"));
                Logger.info(resultSet.getString("age"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
    }
}
