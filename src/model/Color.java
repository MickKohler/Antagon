package model;

/**
 * Represents the color of a cell on the board.
 *
 * @author Mick Kohler
 */
public enum Color {

    /**
     * Represents the color black.
     */
    BLACK('1'),

    /**
     * Represents the color white.
     */
    WHITE('0');

    private final char representation;

    Color(char representation) {
        this.representation = representation;
    }

    /**
     * Returns the representation of the color.
     *
     * @return the representation of the color
     */
    public char getRepresentation() {
        return representation;
    }

    /**
     * Returns the corresponding color based on given representation.
     *
     * @param representation the representation of the expected color
     * @return the corresponding color, {@code null} if no color uses the representation
     */
    public static Color fromRepresentation(char representation) {
        for (Color value : values()) {
            if (value.getRepresentation() == representation) {
                return value;
            }
        }
        return null;
    }

}
