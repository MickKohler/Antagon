package model;

/**
 * Represents a cell in the grid.
 *
 * @author Mick Kohler
 */
public class Cell {
    private Ant ant;
    private Color color;

    /**
     * Constructs a cell with a given color.
     *
     * @param color the color of the cell
     */
    public Cell(Color color) {
        this.color = color;
    }

    /**
     * Returns the (possibly) ant in the cell.
     *
     * @return the ant
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Sets the ant on the cell.
     *
     * @param ant the ant to set
     */
    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    /**
     * Resets the ant on the cell.
     */
    public void resetAnt() {
        this.ant = null;
    }

    /**
     * Returns the color of the cell.
     *
     * @return the color of the cell
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the cell.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the state representation of the cell.
     *
     * @return the state representation of the cell
     */
    public char getStateRepresentation() {
        return (ant == null) ? color.getRepresentation() : ant.getDirection().getRepresentation(color);
    }

}


