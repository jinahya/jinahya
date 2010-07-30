/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.util.logging;


import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class VerySimpleFormatter extends Formatter {


    @Override
    public String format(final LogRecord record) {
        final StringBuffer buffer = new StringBuffer();


        if (formatLevel) {
            buffer.append(record.getLevel().getName());
            buffer.append(" ");
        }

        synchronized (this) {
            if (dateFormat != null) {
                date.setTime(record.getMillis());
                buffer.append(dateFormat.format(date));
                buffer.append(" ");
            }
        }

        if (formatSourceClassName) {
            buffer.append(record.getSourceClassName());
            buffer.append(" ");
        }

        if (formatSourceMethodName) {
            buffer.append(record.getSourceMethodName());
            buffer.append(" ");
        }

        buffer.append(record.getMessage());

        buffer.append(System.getProperty("line.separator"));

        return buffer.toString();
    }


    public boolean getFormatLevel() {
        return formatLevel;
    }


    public void setFormatLevel(final boolean formatLevel) {
        this.formatLevel = formatLevel;
    }


    public DateFormat getDateFormat() {
        return dateFormat;
    }


    public void setDateFormat(final DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }


    public boolean getFormatSourceClassName() {
        return formatSourceClassName;
    }


    public void setFormatSourceClassName(final boolean formatSourceClassName) {
        this.formatSourceClassName = formatSourceClassName;
    }


    public boolean getFormatSourceMethodName() {
        return formatSourceMethodName;
    }


    public void setFormatSourceMethodName(
        final boolean formatSourceMethodName) {

        this.formatSourceMethodName = formatSourceMethodName;
    }


    private boolean formatLevel = false;

    private volatile DateFormat dateFormat =
        new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
    private Date date = new Date();

    private boolean formatSourceClassName = false;
    private boolean formatSourceMethodName = false;
}
