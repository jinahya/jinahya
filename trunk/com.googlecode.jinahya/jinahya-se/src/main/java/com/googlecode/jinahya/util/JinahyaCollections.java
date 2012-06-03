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


package com.googlecode.jinahya.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class JinahyaCollections {


    /**
     * 
     * @param <T> value type parameter
     * @param values values ordered descendant
     * @return ranks
     */
    public static <T extends Comparable<? super T>> List<Integer> ranks(
        final List<T> values) {

        if (values == null) {
            throw new NullPointerException("null values");
        }

        if (values.isEmpty()) {
            return Collections.<Integer>emptyList();
        }

        final List<Integer> ranks = new ArrayList<Integer>(values.size());

        final Iterator<T> iterator = values.iterator();
        T previous = iterator.next();
        int rank = 1;
        ranks.add(rank);
        int tie = 0;
        int compare = 0;
        for (T next = null; iterator.hasNext();) {
            if ((next = iterator.next()) == null) {
                throw new NullPointerException("null element");
            }
            compare = previous.compareTo(next);
            if (compare < 0) {
                throw new IllegalArgumentException("not ordered descendant");
            } else if (compare == 0) {
                tie++;
            } else {
                tie = 0;
            }
            ranks.add(++rank - tie);
            previous = next;
        }

        return ranks;
    }


    private JinahyaCollections() {
        super();
    }


}

