package com.boda.secondhandtransaction.Services;
import com.boda.secondhandtransaction.DAO.BaseDao;
import com.boda.secondhandtransaction.DAO.ResultSetHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    //SHA-256 单向哈希加密(无法反向解密)
    private String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));

            // 转为16进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //用户注册密码确认
    public int confirmPassword(String password, String confirmPassword){
        if (password.equals(confirmPassword)) {
            return 1;
        } else {
            return 0;
        }
    }

    //插入用户信息(哈希加密)
    public int register(String username, String password){
        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        return BaseDao.executeUpdate(sql, username, hashedPassword);
    }

    //根据用户名查询用户id
    public static int getUserId(String username){
        String sql = "SELECT id FROM users WHERE username = ?";
        return BaseDao.query(sql, new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) {
                try {
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return 0;
            }
        }, username);
    }
    // 检验登录(对比哈希值)
    public int login(String username, String password){
        String hashedPassword = hashPassword(password);
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        return BaseDao.query(sql, new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) {
                try {
                    if (rs.next()) {
                        return 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        }, username, hashedPassword);
    }
}
