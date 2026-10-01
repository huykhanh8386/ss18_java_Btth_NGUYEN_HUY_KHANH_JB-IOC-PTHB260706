package Btth;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM get_all_products()";
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Product product = new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getDate("created")
                                        .toLocalDate(),
                                rs.getString("catalog"),
                                rs.getBoolean("status")
                        );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addProduct(Product product) {
        String sql = "CALL add_product(?, ?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = ConnectDB.openConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getTitle());
            ps.setDate(4, Date.valueOf(product.getCreated()));
            ps.setString(5, product.getCatalog());
            ps.setBoolean(6, product.isStatus());
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi thêm sản phẩm: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean updateProduct(
            Product product
    ) {
        String sql = "CALL update_product(?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = ConnectDB.openConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getTitle());
            ps.setDate(5, Date.valueOf(product.getCreated()));
            ps.setString(6, product.getCatalog());
            ps.setBoolean(7, product.isStatus());
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi cập nhật: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean deleteProduct(int id) {
        String sql = "CALL delete_product(?)";
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa: " + e.getMessage());
            return false;
        }
    }
    public List<Product> searchProduct(String name
    ) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM search_product(?)";
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Product product =
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getDate("created")
                                        .toLocalDate(),
                                rs.getString("catalog"),
                                rs.getBoolean("status")
                        );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void statistic() {
        String sql = "SELECT * FROM statistic_by_catalog()";
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            System.out.println("===== THỐNG KÊ THEO DANH MỤC =====");
            while (rs.next()) {
                System.out.println(rs.getString("catalog") + ": " + rs.getLong("total") + " sản phẩm");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}