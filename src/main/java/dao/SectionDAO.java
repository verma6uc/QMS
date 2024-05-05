package dao;

import model.Section;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SectionDAO {

    private static final Logger LOGGER = Logger.getLogger(SectionDAO.class.getName());

    public Integer createSection(Section section) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String insertQuery = "INSERT INTO public.sections(name, functionality, type_of_section, page_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?) RETURNING section_id;";
        try {
            connection = DatabaseUtility.connect();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, section.getName());
            preparedStatement.setString(2, section.getFunctionality());
            preparedStatement.setString(3, section.getTypeOfSection());
            preparedStatement.setInt(4, section.getPageId());
            preparedStatement.setTimestamp(5, section.getCreatedAt());
            preparedStatement.setTimestamp(6, section.getUpdatedAt());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.severe("Failed to create section: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(connection);
        }
        return null;
    }

    public Boolean updateSection(Section section) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String updateQuery = "UPDATE public.sections SET name = ?, functionality = ?, type_of_section = ?, page_id = ?, updated_at = ? WHERE section_id = ?;";
        try {
            connection = DatabaseUtility.connect();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, section.getName());
            preparedStatement.setString(2, section.getFunctionality());
            preparedStatement.setString(3, section.getTypeOfSection());
            preparedStatement.setInt(4, section.getPageId());
            preparedStatement.setTimestamp(5, section.getUpdatedAt());
            preparedStatement.setInt(6, section.getSectionId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.severe("Failed to update section: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(connection);
        }
        return false;
    }

    public Section getSectionById(int sectionId) {
        Section section = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String selectQuery = "SELECT * FROM public.sections WHERE section_id = ?;";
        try {
            connection = DatabaseUtility.connect();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, sectionId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                section = new Section();
                section.setSectionId(resultSet.getInt("section_id"));
                section.setName(resultSet.getString("name"));
                section.setFunctionality(resultSet.getString("functionality"));
                section.setTypeOfSection(resultSet.getString("type_of_section"));
                section.setPageId(resultSet.getInt("page_id"));
                section.setCreatedAt(resultSet.getTimestamp("created_at"));
                section.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            LOGGER.severe("Failed to retrieve section: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(connection);
        }
        return section;
    }

    public Boolean deleteSection(int sectionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String deleteQuery = "DELETE FROM public.sections WHERE section_id = ?;";
        try {
            connection = DatabaseUtility.connect();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, sectionId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.severe("Failed to delete section: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(connection);
        }
        return false;
    }

    public List<Section> getAllSections() {
        List<Section> sections = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM public.sections;";
        try {
            connection = DatabaseUtility.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Section section = new Section();
                section.setSectionId(resultSet.getInt("section_id"));
                section.setName(resultSet.getString("name"));
                section.setFunctionality(resultSet.getString("functionality"));
                section.setTypeOfSection(resultSet.getString("type_of_section"));
                section.setPageId(resultSet.getInt("page_id"));
                section.setCreatedAt(resultSet.getTimestamp("created_at"));
                section.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                sections.add(section);
            }
        } catch (SQLException e) {
            LOGGER.severe("Failed to retrieve all sections: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(connection);
        }
        return sections;
    }
}