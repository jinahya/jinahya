

package com.googlecode.jinahya.test.annotation;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class HelloWorld {


    @Created({
        @By(name = "JIn Kwon", comment = "")
    })
    @Reviewed({
        @By(name = "Steve Jobs", comment = "Awesome!"),
        @By(name = "Linus Torvalds", comment = "*YOU* are full of bullshit."),
        @By(name = "James Gosling",
            comment = "sigh...shoot me now...can you get Josh Bloch fired?")
    })
    protected static void helloworld() {
        System.out.println("hello, world");
    }


    @Source(created = {@By(name = "mskim"),
                       @By(name = "jkwon")},
            reviewed = {@By(name = "ryunseo"),
                        @By(name = "ymchoi"),
                        @By(name = "mwpark")
    })
    public static void main(final String[] args) {
        helloworld();
    }


}

