package com.boda.secondhandtransaction.DAO;
import com.boda.secondhandtransaction.POJO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {
    // 通用增删改
    public static int executeUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, conn);
        }
        return 0;
    }
    // 通用查询
    public static <T> T query(String sql, ResultSetHandler<T> handler, Object... args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }

            rs = stmt.executeQuery();

            return handler.handle(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
    }
}
