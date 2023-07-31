package com.whitesoul.soulsql.database;

import com.whitesoul.soulsql.SoulSQL;
import com.whitesoul.soulsql.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
    private static PreparedStatement ps;

    // 创建数据库表
    public static void createTable(String tableName, String[] columns, String[] types, String[] comments){
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i]).append(" ").append(types[i]).append(" ").append(comments[i]).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        try {
            Mysql.getConnection().createStatement().executeUpdate(sql.toString());
        }catch (Exception e){
            e.printStackTrace();
            Logger.error("数据库表创建失败");
        }
    }

    // 删除数据库表
    public static void dropTable(String tableName) throws SQLException {
        Mysql.getConnection().createStatement().executeUpdate("DROP TABLE IF EXISTS " + tableName);
    }

    // 使用PreparedStatement插入数据
    public static void insert(String tableName, String[] columns, String[] values) {
        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
        bukkitScheduler.runTaskAsynchronously(SoulSQL.INSTANCE, () -> {
            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + "(");
            for (String column : columns) {
                sql.append(column).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") VALUES(");
            for (String value : values) {
                sql.append("'").append(value).append("'").append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            try {
                ps = Mysql.getConnection().prepareStatement(sql.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                Logger.error("数据插入失败");
            }
        });
    }

    // 查询数据
    public static ResultSet queruy(String column, String tableName, String where, String whereValue) throws SQLException {
        String sql = "SELECT " + where + ", " + column + " FROM " + tableName + " WHERE " + where + " = ?";
                ps = Mysql.getConnection().prepareStatement(sql);
                ps.setString(1, whereValue);
                ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }
    // 使用PreparedStatement更新数据
    public static void update(String column, String columnValue, String tableName, String where, String whereValue) {
        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
        bukkitScheduler.runTaskAsynchronously(SoulSQL.INSTANCE, () -> {
                try {
                    String sql = "UPDATE " + tableName + " SET " + column + " = ? WHERE "+ where +" = ?";
                    ps = Mysql.getConnection().prepareStatement(sql);
                    ps.setString(1, columnValue);
                    ps.setString(2, whereValue);
                    ps.executeUpdate();
                }catch (Exception e){
                    e.printStackTrace();
                    Logger.error("数据更新失败");
                }
        });
    }
    // 使用PreparedStatement删除数据
    public static void delete(String tableName, String column, String columnValue) {
        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
        bukkitScheduler.runTaskAsynchronously(SoulSQL.INSTANCE, () -> {
            String sql = "DELETE FROM " + tableName + " WHERE " + column + " = ?";
            try {
                ps = Mysql.getConnection().prepareStatement(sql);
                ps.setString(1, columnValue);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                Logger.error("数据删除失败");
            }
        });
    }
}
