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
     * Moves the ant to the next cell in the direction the ant is facing
     * and rotates the ant depending on the color of the cell the ant is now on
     *
     * @return the new position of the ant
     */
    Position move() {
        Position preMovePosition = position;
        board.getCell(preMovePosition).resetAnt();

        Position postMovePosition = new Position(preMovePosition.row() + getDirection().getRowChange()
                                                    , preMovePosition.column() + getDirection().getColumnChange());
        Cell postMoveCell = board.getCell(postMovePosition);

        if (postMoveCell == null) {
            position = null;
        } else {
            rotate(postMoveCell.getColor());

            postMoveCell.enrollAntMove(this);

            position = postMovePosition;
        }
        return position;
    }

    private void rotate(Color color) {
        int rotations = switch (color) {
            case BLACK -> -1;
            case WHITE -> 1;
        };
        direction = direction.rotate(rotations);
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
