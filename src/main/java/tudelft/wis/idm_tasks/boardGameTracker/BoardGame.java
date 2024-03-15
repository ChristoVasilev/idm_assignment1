package tudelft.wis.idm_tasks.boardGameTracker;

public class BoardGame implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame {
    /**
     * The Name.
     */
    String name;
    /**
     * The BoardGameGeek.com URL of the boardgame.
     */
    String bggURL;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBGG_URL() {
        return bggURL;
    }

    @Override
    public String toVerboseString() {
        return name + " (" + bggURL + ")";
    }

    /**
     * Instantiates a new Board game POJO.
     *
     * @param name   the name
     * @param bggURL the BoardGameGeek.com URL of the boardgame
     */
    public BoardGame(String name, String bggURL) {
        this.name = name;
        this.bggURL = bggURL;
    }
}