package model;

import java.util.EnumMap;
import java.util.Map;


/**
 * Represents the direction of an ant.
 *
 * @author Mick Kohler
 */
public enum Direction {
    /**
     * Represent the direction 'north'.
     */
    NORTH('N', 'n', -1, 0),

    /**
     * Represent the direction 'east'.
     */
    EAST('E', 'e', 0, 1),

    /**
     * Represent the direction 'south'.
     */
    SOUTH('S', 's', 1, 0),

    /**
     * Represent the direction 'west'.
     */
    WEST('W', 'w', 0, -1);

    private final Map<Color, Character> representations;
    private final int rowChange;
    private final int columnChange;

    Direction(char blackRepresentation, char whiteRepresentation, int rowChange, int columnChange) {
        this.representations = new EnumMap<>(Color.class);
        representations.put(Color.BLACK, blackRepresentation);
        representations.put(Color.WHITE, whiteRepresentation);
        this.rowChange = rowChange;
        this.columnChange = columnChange;
    }

    /**
     * Returns the row change of the direction.
     *
     * @return the row change
     */
    public int getRowChange() {
        return rowChange;
    }


    /**
     * Returns the column change of the direction.
     *
     * @return the column change
     */
    public int getColumnChange() {
        return columnChange;
    }

    /**
     * Returns the corresponding direction based on given representation.
     *
     * @param color the color of the expected direction
     * @return the corresponding direction, {@code null} if no direction uses the representation
     */
    public char getRepresentation(Color color) {
        return representations.get(color);
    }


    /**
     * Returns the corresponding direction after rotating the current direction
     * a given number of times by 90 degrees clockwise.
     *
     * @param times the number of times to rotate the direction
     * @return the corresponding direction after rotating the current direction
     */
    public Direction rotate(int times) {
        return values()[Math.floorMod(ordinal() + times, values().length)];
    }

    /**
     * Returns the corresponding direction based on given representation.
     *
     * @param representation the representation of the expected direction
     * @return the corresponding direction, {@code null} if no direction uses the representation
     */
    public static Direction fromRepresentation(char representation) {
        for (Direction value : values()) {
            for (Color color : Color.values()) {
                if (value.getRepresentation(color) == representation) {
                    return value;
                }
            }
        }
        return null;
    }


}
