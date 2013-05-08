

package com.googlecode.jinahya.test.annotation;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Final
public final class AllFinal {


    @Final
    public AllFinal(final String value) {
        super();

        this.value = value;
    }


    @Final
    public AllFinal() {
        this(null);
    }


    @Final
    public void setValue(@Final String value) {
        this.value = value;
    }


    @Final
    private String value;


}

