package dao;

import model.User;
import utils.DatabaseUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    /**
     * Inserts a new user into the database.
     *
     * @param user the user to insert
     * @return the generated user ID
     * @throws SQLException if any database operations fail
     */
    public Integer createUser(User user) throws SQLException {
        String sql = "INSERT INTO public.users (username, password, role_id, department_id, first_name, last_name, email, phone_number, status_id, created_at, updated_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRoleId());
            pstmt.setInt(4, user.getDepartmentId());
            pstmt.setString(5, user.getFirstName());
            pstmt.setString(6, user.getLastName());
            pstmt.setString(7, user.getEmail());
            pstmt.setString(8, user.getPhoneNumber());
            pstmt.setInt(9, user.getStatusId());
            pstmt.setTimestamp(10, user.getCreatedAt());
            pstmt.setTimestamp(11, user.getUpdatedAt());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
            throw e;
        }
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user the user with updates
     * @return true if the update was successful
     * @throws SQLException if any database operations fail
     */
    public Boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE public.users SET username = ?, password = ?, role_id = ?, department_id = ?, first_name = ?, last_name = ?, email = ?, phone_number = ?, status_id = ?, updated_at = ? " +
                     "WHERE id = ?";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRoleId());
            pstmt.setInt(4, user.getDepartmentId());
            pstmt.setString(5, user.getFirstName());
            pstmt.setString(6, user.getLastName());
            pstmt.setString(7, user.getEmail());
            pstmt.setString(8, user.getPhoneNumber());
            pstmt.setInt(9, user.getStatusId());
            pstmt.setTimestamp(10, user.getUpdatedAt());
            pstmt.setInt(11, user.getId());

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
            throw e;
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param userId the ID of the user to delete
     * @return true if the deletion was successful
     * @throws SQLException if any database operations fail
     */
    public Boolean deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM public.users WHERE id = ?";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }

    /**
     * Retrieves a specific user by their user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the found user, or an empty Optional if no user is found
     * @throws SQLException if any database operations fail
     */
    public Optional<User> getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM public.users WHERE id = ?";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setDepartmentId(rs.getInt("department_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setStatusId(rs.getInt("status_id"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    user.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return Optional.of(user);
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving user", e);
            throw e;
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     * @throws SQLException if any database operations fail
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM public.users";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setStatusId(rs.getInt("status_id"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all users", e);
            throw e;
        }
    }
    
    /**
     * Authenticates a user by their username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return an Optional containing the authenticated user, or an empty Optional if authentication fails
     * @throws SQLException if any database access error occurs
     */
    public Optional<User> login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM public.users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password); // Encrypt this if passwords are encrypted in DB
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractUserFromResultSet(rs));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error logging in with username: " + email, e);
            throw e;
        }
    }

    /**
     * Extracts a User object from a ResultSet.
     *
     * @param rs the ResultSet to extract the user from
     * @return the extracted User object
     * @throws SQLException if any database access error occurs
     */
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoleId(rs.getInt("role_id"));
        user.setDepartmentId(rs.getInt("department_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setStatusId(rs.getInt("status_id"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));

        return user;
    }
}