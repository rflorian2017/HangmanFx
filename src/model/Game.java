package model;

import java.util.Date;

public class Game {
    private Date startDate;
    private Date endDate;

    private int gamePoints;

    public Game(Date startDate) {
        this.startDate = startDate;
    }

    public void endGame(Date endDate) {
        this.endDate = endDate;
    }
}
