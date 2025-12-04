package com.boda.secondhandtransaction.Services;
import com.boda.secondhandtransaction.DAO.BaseDao;
import com.boda.secondhandtransaction.POJO.Item;
import com.boda.secondhandtransaction.DAO.ResultSetHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsService {
    //插入商品信息
    public int insertItem(String name, String description, String price,Integer sellerId) {
        String sql = "INSERT INTO items (name, description,price, sellerId) VALUES (?, ?, ?, ?)";
        return BaseDao.executeUpdate(sql, name, description,price, sellerId);
    }
    //查询商品信息
    public List<Item> queryItem(String keyWord) {
        String sql = "SELECT * FROM items WHERE name LIKE '%" + keyWord + "%' OR description LIKE '%" + keyWord + "%'";
        String searchPattern = "%" + keyWord + "%";
        return BaseDao.query(sql, new ResultSetHandler<List<Item>>() {
            @Override
            public List<Item> handle(ResultSet rs) {
                List<Item> items = new ArrayList<>();
                try {
                    while (rs.next()) {
                        Item item = new Item();
                        item.setId(rs.getInt("id"));
                        item.setName(rs.getString("name"));
                        item.setDescription(rs.getString("description"));
                        item.setPrice(rs.getString("price"));
                        item.setSellerId(rs.getInt("sellerId"));
                        items.add(item);
                    }
                    return items;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
    //查询对应sellerId的商品
    public List<Item> queryItemBySellerId(Integer sellerId) {
        String sql = "SELECT * FROM items WHERE sellerId = ?";
        return BaseDao.query(sql, new ResultSetHandler<List<Item>>() {
            @Override
            public List<Item> handle(ResultSet rs) throws SQLException {
                List<Item> items = new ArrayList<>();
                try {
                    while (rs.next()) {
                        Item item = new Item();
                        item.setId(rs.getInt("id"));
                        item.setName(rs.getString("name"));
                        item.setDescription(rs.getString("description"));
                        item.setPrice(rs.getString("price"));
                        item.setSellerId(rs.getInt("sellerId"));
                        items.add(item);
                    }
                    return items;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, sellerId);
    }
    //删除商品信息
    public int deleteItemById(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        return BaseDao.executeUpdate(sql, id);
    }
    //更具id查询商品信息
    public Item queryItemById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return BaseDao.query(sql, new ResultSetHandler<Item>() {
            @Override
            public Item handle(ResultSet rs) throws SQLException {
                Item item = new Item();
                if (rs.next()) {
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setPrice(rs.getString("price"));
                    item.setSellerId(rs.getInt("sellerId"));
                    return item;
                }
                return null;
            }
        }, id);
    }
    //更新商品信息
    public int updateItem(int id, String name, String description, String price, Integer sellerId) {
        String sql = "UPDATE items SET name = ?, description = ?, price = ? WHERE id = ? AND sellerId = ?";
        return BaseDao.executeUpdate(sql, name, description, price, id, sellerId);
    }
}
