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


import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PairTableColumn extends TableColumn {


    public PairTableColumn(final int modelIndex) {
        super(modelIndex);
    }


    public PairTableColumn(final int modelIndex, final int width) {
        super(modelIndex, width);
    }


    public PairTableColumn(final int modelIndex, final int width,
                           final TableCellRenderer cellRenderer,
                           final TableCellEditor cellEditor) {
        super(modelIndex, width, cellRenderer, cellEditor);
    }


}

