package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the board of the Langton's ant game.
 * For more information see the Readme.md file in the GitHub repository or Wikipedia.
 *
 * @author Mick Kohler
 */
public final class Board {
    private final Map<Position, Cell> board;
    private final Ant ant;
    private final int rowCount;
    private final int columnCount;

    /**
     * Creates a new instance.
     *
     * @param board the cells to initialize the board with
     */
    private Board(Map<Position, Cell> board, int rowCount, int columnCount,
                         Position antPosition, Direction antDirection) {
        this.board = Collections.unmodifiableMap(board);
        this.ant = new Ant(this, antPosition, antDirection);
        board.get(antPosition).setAnt(ant);
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    /**
     * Checks whether a certain position is on the board.
     *
     * @param position the position to check
     * @return {@code true} if the position is on the board, {@code false} otherwise
     */
    public boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < rowCount
                && position.column() >= 0 && position.column() < columnCount;
    }

    /**
     * Returns the board.
     *
     * @return the board
     */
    public Board getBoard(){
        return this;
    }

    /**
     * Returns the number of rows of the board.
     *
     * @return int value of the number of rows
     */
    public int getRowCount(){
        return rowCount;
    }

    /**
     * Returns the number of columns of the board.
     *
     * @return int value of the number of columns
     */
    public int getColumnCount(){
        return columnCount;
    }

    /**
     * Moves the ant according to the rules of the Langton's ant game.
     */
    public void moveAnt() {
        Position startPosition = ant.getPosition();
        Cell startCell = board.get(startPosition);
        Position endPisition = new Position(startPosition.row() + ant.getDirection().getRowChange(),
                startPosition.column() + ant.getDirection().getColumnChange());

        Cell endCell = board.get(endPisition);

        startCell.resetAnt();
        ant.setPosition(endPisition);
        endCell.setAnt(ant);

        if (endCell.getColor() == Color.WHITE) {
            ant.setDirection(ant.getDirection().rotate(1)); // 1 rotation 90 degrees clockwise
            endCell.setColor(Color.BLACK);
        } else if (endCell.getColor() == Color.BLACK) {
            ant.setDirection(ant.getDirection().rotate(3)); // 3 rotations 90 degrees clockwise -> 1 rotation 90 degrees counter-clockwise
            endCell.setColor(Color.WHITE);
        }

    }

    /**
     * Returns the cell on the given position.
     *
     * @param position the position indicating the desired cell
     * @return the cell on the given position, {@code null} if position is off board
     */
    public Cell getCell(Position position) {
        return board.get(position);
    }

    /**
     * Returns the current position of the ant.
     *
     * @return the current position of the ant, {@code null} if the ant is off board
     */
    public Position getAntPosition() {
        return ant.getPosition();
    }

    /**
     * Returns the representation of the current state of the board.
     * Each element of the list represents a row of the board from top to bottom where
     * each row consists of concatenated cell {@link Cell#getStateRepresentation() representations}.
     *
     * @return the representation of the current state of the board
     */
    public List<List<Character>> getRepresentation() {
        List<List<Character>> rows = new ArrayList<>(rowCount);
        for (int row = 0; row < rowCount; row++) {
            List<Character> cellRepresentations = new ArrayList<>(columnCount);
            for (int column = 0; column < columnCount; column++) {
                cellRepresentations.add(board.get(new Position(row, column)).getStateRepresentation());
            }
            rows.add(cellRepresentations);
        }
        return rows;
    }

    /**
     * Returns a new board instance initialized with the provided cell representations.
     * Each element of the list represents a row of the board from top to bottom where
     * each row consists of concatenated cell {@link Cell#getStateRepresentation() representations}.
     *
     * @param rows the cell representations ordered by their rows
     * @return a new board instance in the state of provided representations
     */
    public static Board fromRepresentation(List<List<Character>> rows) {
        Map<Position, Cell> board = new HashMap<>();
        int rowCount = rows.size();
        int columnCount = rows.get(0).size();
        Position antPosition = null;
        Direction antDirection = null;

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            List<Character> rowRepresentation = rows.get(rowIndex);
            for (int columnIndex = 0; columnIndex < rowRepresentation.size(); columnIndex++) {
                Position position = new Position(rowIndex, columnIndex);
                char representation = rowRepresentation.get(columnIndex);
                Color color = Color.fromRepresentation(representation);
                if (color == null) {
                    antPosition = position;
                    antDirection = Direction.fromRepresentation(representation);
                    color = Color.WHITE;
                }

                board.put(position, new Cell(color));
            }
        }
        return new Board(board, rowCount, columnCount, antPosition, antDirection);
    }

}
