package model;

public class Ant {
    private final Board board;
    private Position position;
    private Direction direction;

    /**
     * Constructor for the Ant class
     *
     * @param board the board the ant is on
     * @param position the position of the ant
     * @param direction the direction the ant is facing
     */
    public Ant(Board board, Position position, Direction direction) {
        this.board = board;
        this.position = position;
        this.direction = direction;
    }

    /**
     * Getter for the board
     *
     * @return the board the ant is on
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for the position of the ant
     *
     * @return the position of the ant
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter for the position of the ant
     *
     * @param position the new position of the ant
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter for the direction the ant is facing
     *
     * @return the direction the ant is facing
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Setter for the direction the ant is facing
     *
     * @param direction the new direction the ant is facing
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
