

package com.googlecode.jinahya.test.annotation;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Final
public final class AlmostAllFinal {


    @Final
    public AlmostAllFinal(final String value) {
        super();

        this.value = value;
    }


    @Final
    public AlmostAllFinal() {
        this(null);
    }


    @Final
    public String getValue() {
        return value;
    }


    @Final
    public void setValue(@Final String value) {
        this.value = value;
    }


    @Final
    private String value;


}

