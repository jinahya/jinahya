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


import java.util.List;
import java.util.Set;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PairSetColumnModel extends DefaultTableColumnModel {


    /**
     *
     * @param pairNames
     * @param excludeNames
     */
    public PairSetColumnModel(final List<String> pairNames,
                              final Set<String> excludeNames) {
        super();

        for (int i = 0; i < pairNames.size(); i++) {
            if (!excludeNames.contains(pairNames.get(i))) {
                final TableColumn tableColumn = new PairTableColumn(i);
                //tableColumn.setHeaderValue(pairNames.get(i));
                addColumn(tableColumn);
            }
        }
    }


}

