package dao;

import model.Enums;
import model.Product;
import utils.DatabaseUtility;
import java.util.logging.Logger;
import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object for handling operations related to the Product entity.
 */
public class ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

    /**
     * Inserts a new product into the database.
     *
     * @param product The product to insert.
     * @return The ID of the created product, or -1 if insertion fails.
     */
    public int createProduct(Product product) {
        String sql = "INSERT INTO public.products (name, description, category, status, created_at, updated_at) VALUES (?, ?, ?::product_category, ?::product_status, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getCategory().name());
            stmt.setString(4, product.getStatus().name());
            stmt.setTimestamp(5, product.getCreatedAt());
            stmt.setTimestamp(6, product.getUpdatedAt());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.severe("Error inserting product: " + e.getMessage());
        } finally {
        }
        return -1; 
    }

    /**
     * Updates an existing product.
     *
     * @param product The product with updated details.
     * @return true if the product was updated successfully.
     */
    public boolean updateProduct(Product product) {
        String sql = "UPDATE public.products SET name = ?, description = ?, category = ?::product_category, status = ?::product_status, updated_at = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getCategory().name());
            stmt.setString(4, product.getStatus().name());
            stmt.setTimestamp(5, product.getUpdatedAt());
            stmt.setInt(6, product.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error updating product: " + e.getMessage());
            return false;
        } finally {
        }
    }

    /**
     * Deletes a product based on its ID.
     *
     * @param id The ID of the product to delete.
     * @return true if the product was deleted successfully.
     */
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM public.products WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error deleting product: " + e.getMessage());
            return false;
        } finally {
        }
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product.
     * @return An Optional containing the found Product or an empty Optional if no product is found.
     */
    public Optional<Product> getProductById(int id) {
        String sql = "SELECT * FROM public.products WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(Enums.ProductCategory.valueOf(rs.getString("category")));
                product.setStatus(Enums.ProductStatus.valueOf(rs.getString("status")));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving product: " + e.getMessage());
        } finally {
        }
        return Optional.empty();
    }
}