package com.neusoft.xlm.entity.lang;

import java.util.List;

public class MovieCountByLangDTO {
    public List<Integer> rows;
    public List<String> cols;

    public List<Integer> getRows() {
        return rows;
    }

    public void setRows(List<Integer> rows) {
        this.rows = rows;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }
}
