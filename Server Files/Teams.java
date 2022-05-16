public class Teams {
    // Class for storing columns
    private int columns;
    private String[] columnsNames;

    public Teams(String[] columnsNames, int columns) {
        this.columnsNames = columnsNames;
        this.columns = columns;
    }

    @Override
    public String toString() {
        // Returns a the array of columns as one string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            sb.append(columnsNames[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
