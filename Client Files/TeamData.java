package PartialExam;

import java.util.ArrayList;
import java.util.List;

public class TeamData {

    private List<String[]> data;
    private int columns = 0;
    private String[] columnsNames;


    public TeamData() {
        data = new ArrayList<>();
    }

    public void addData(String[] parts) {

        data.add(parts);
    }

    public String[][] getData() {
        columns = Integer.parseInt(data.get(0)[0]);

        String[][] toReturn = new String[data.size()][columns];

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < columns; j++) {
                toReturn[i][j] = data.get(i)[j + 1];
            }
        }
        return toReturn;
    }

    public void clearData() {
        data = new ArrayList<>();
    }

    public void addColumnsNames(String[] columnsNames) {
        this.columnsNames = columnsNames;
    }

    public String[] getColumnsData() {
        return columnsNames;
    }

}
