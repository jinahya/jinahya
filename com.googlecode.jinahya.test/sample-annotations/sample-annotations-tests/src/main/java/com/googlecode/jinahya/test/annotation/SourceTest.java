

package com.googlecode.jinahya.test.annotation;


/**
 * The HelloWorld.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Source(created = {@By(name = "mskim"),
                   @By(name = "jkwon")},
        reviewed = {@By(name = "ryunseo"),
                    @By(name = "ymchoi", comment = ""),
                    @By(name = "mwpark")
})
public class SourceTest {


    /**
     *
     * @param args program arguments
     */
    public static void main(final String[] args) {
        System.out.println("hello, world");
    }


}

