

package com.googlecode.jinahya.test.annotation;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Final // TYPE
public final class FinalTest {


    @Final // CONSTRUCTOR
    public FinalTest(@Final String value /* PARAMETER */) {
        super();

        this.value = value;
    }


    @Final // METHOD
    public FinalTest() {
        this(null);
    }


    @Final // METHOD
    public String getValue() {
        return value;
    }


    @Final
    public void setValue(@Final String value /* PARAMETER */) {
        this.value = value;
    }


    @Final // FIELD
    private String value;


}

