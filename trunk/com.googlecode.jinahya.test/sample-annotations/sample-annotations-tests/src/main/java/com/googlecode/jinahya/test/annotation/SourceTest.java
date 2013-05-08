

package com.googlecode.jinahya.test.annotation;


/**
 * The HelloWorld.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class SourceTest {


    /**
     *
     * @param args program arguments
     */
    @Source(created = {@By(name = "mskim"),
                       @By(name = "jkwon")},
            reviewed = {@By(name = "ryunseo"),
                        @By(name = "ymchoi"),
                        @By(name = "mwpark")
    })
    public static void main(final String[] args) {
        System.out.println("hello, world");
    }


}

