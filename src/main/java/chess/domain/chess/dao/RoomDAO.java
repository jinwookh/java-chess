package chess.domain.chess.dao;

import chess.domain.chess.game.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public void add() throws SQLException {
        Connection connection = DBConnection.getConnection();

        String query = "INSERT INTO room (team) VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Team.WHITE.name());

        pstmt.executeUpdate();

        connection.close();
    }

    public void update(int id, Team team) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String query = "UPDATE room SET team = ? WHERE id = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, team.name());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();

        connection.close();
    }

    public Team select(int roomId) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String query = "SELECT team FROM room WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new SQLException();

        Team team = Team.getTeamByName(resultSet.getString("team"));

        connection.close();

        return team;

    }

    public List<Integer> getIds() throws SQLException {
        Connection connection = DBConnection.getConnection();

        String query = "SELECT id FROM room ORDER BY id ASC LIMIT 100 OFFSET 0";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<Integer> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        connection.close();
        return ids;
    }

    public int getRecentId() throws SQLException {
        Connection connection = DBConnection.getConnection();

        String query = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new SQLException();

        int id = resultSet.getInt("id");
        return id;
    }

}
