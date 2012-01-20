/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.sql.metadata.ui;


import com.googlecode.jinahya.sql.metadata.Pair;
import com.googlecode.jinahya.sql.metadata.PairSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PairSetsTableModel<T extends PairSet> extends AbstractTableModel {


    /**
     * Creates a new instance.
     *
     * @param pairSets a collection of PairSets.
     */
    public PairSetsTableModel(final Collection<T> pairSets) {
        super();

        columnNames = Collections.unmodifiableList(new ArrayList<String>(
            pairSets.iterator().next().getPairs().keySet()));

        values = new String[columnNames.size()][];
        int row = 0;
        for (final Iterator<T> i = pairSets.iterator(); i.hasNext(); row++) {
            final Map<String, Pair> pairs = i.next().getPairs();
            values[row] = new String[pairs.size()];
            for (int j = 0; j < values[row].length; j++) {
                values[row][j] = pairs.get(columnNames.get(j)).getValue();
            }
        }
    }


    @Override
    public String getColumnName(final int column) {
        return columnNames.get(column);
    }


    @Override
    public int getColumnCount() {
        return columnNames.size();
    }


    @Override
    public int getRowCount() {
        return values.length;
    }


    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return values[rowIndex][columnIndex];
    }


    /**
     * column names.
     */
    private final List<String> columnNames;


    /**
     * model values.
     */
    private final String[][] values;


}

