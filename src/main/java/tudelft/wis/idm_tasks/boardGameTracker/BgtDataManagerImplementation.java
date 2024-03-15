package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BgtDataManagerImplementation implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager {
    private static Connection connection;

    public Connection getConnection()  {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/boardGames", "postgres", "Sarah1809");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    /**
     * Creates a new player and stores it in the DB.
     *
     * @param name the player name
     * @param nickname the player nickname
     * @return the new player
     * @throws java.sql.SQLException DB trouble
     */
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        String insertQuery = "INSERT INTO players (name, nick_name), VALUES (?, ?)";
        getConnection();
        try {
            PreparedStatement myStmt = connection.prepareStatement(insertQuery);
            myStmt.setString(1, name);
            myStmt.setString(2, nickname);
            myStmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new PlayerImplementation(name, nickname);
    }

    /**
     * Searches for player in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "hris" will find "Christoph"
     * @return collection of all players containing the param substring in their names
     * @throws BgtException the bgt exception
     */
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        String findIDQuery = "SELECT name, nickname FROM players WHERE name LIKE CONCAT(%, ?, %)";
        ResultSet resultSet = null;
        try {
            PreparedStatement myStmt = connection.prepareStatement(findIDQuery);
            myStmt.setString(1, name);
            resultSet = myStmt.executeQuery();
            Collection<Player> players = new ArrayList<>();
            while(resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                players.add(new PlayerImplementation(name, nickname));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Creates a new board game and stores it in the DB.
     * <p>
     * Note: These "create" methods are somewhat unnecessary. However, I put
     * them here to make the task test a bit easier. You can call an appropriate
     * persist method for this.
     *
     * @param name the name of the game
     * @param bggURL the URL of the game at BoardGameGeek.com
     * @return the new game
     * @throws SQLException DB trouble
     */
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        return null;
    }

    /**
     * Searches for game in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "clips" will
     * find "Eclipse: Second Dawn of the Galaxy""
     * @return collection of all boardgames containing the param substring in their names
     */
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        return null;
    }

    /**
     * Creates a new play session and stores it in the DB.
     *
     * @param date the date of the session
     * @param host the session host
     * @param game the game which was played
     * @param playtime the approximate playtime in minutes
     * @param players all players
     * @param winner the one player who won (NULL in case of no winner; multiple
     * winners not supported)
     * @return the new play session
     */
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        return null;
    }

    /**
     * Finds all play sessions from a specific date
     *
     * @param date the date to search from
     * @return collection of all play sessions from the param date
     * @throws BgtException the bgt exception
     */
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        return null;
    }

    /**
     * Persists a given player to the DB. Note that this player might already exist and only needs an update :-)
     * @param player the player
     */
    public void persistPlayer(Player player) {
        String query = "UPSERT INTO player (name, nickname) VALUES (?, ?)";
        try {
            PreparedStatement myStmt = connection.prepareStatement(query);
            myStmt.setString(1, player.getPlayerName());
            myStmt.setString(2, player.getPlayerNickName());
            myStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Persists a given session to the DB. Note that this session might already exist and only needs an update :-)
     * @param session the session
     */
    public void persistPlaySession(PlaySession session) {

    }

    /**
     * Persists a given game to the DB. Note that this game might already exist and only needs an update :-)
     * @param game the game
     */
    public void persistBoardGame(BoardGame game) {

    }
}
