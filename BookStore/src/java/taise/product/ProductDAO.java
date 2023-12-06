/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import taise.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void getAllProduct()
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        //1. create Connection
        Connection con = null;
        ResultSet rs = null;
        Statement stm = null;
        boolean result = false;
        try {
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select id, name, quantity, unitPrice, status "
                        + "From tblProduct";
                //3. Create Statement Object
                stm = con.createStatement();
                //4. execute Querry
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    boolean status = rs.getBoolean("status");
                    //5.2 set data into DTO 
                    ProductDTO dto = new ProductDTO(id, name, quantity, unitPrice, status);
                    //5.3 add dto into list
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }//end account is not existed
                    this.productList.add(dto);
                }//end traverse rs to get all row
            }//end connection is available 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
