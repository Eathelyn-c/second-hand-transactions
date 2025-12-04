package com.boda.secondhandtransaction.DAO;

import java.sql.ResultSet;
//让查询结果转为对象，Item 或 User
public interface ResultSetHandler<T>{
    T handle(ResultSet rs) throws Exception;
}
