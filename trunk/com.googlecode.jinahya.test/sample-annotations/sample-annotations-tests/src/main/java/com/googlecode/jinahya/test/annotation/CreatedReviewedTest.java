

package com.googlecode.jinahya.test.annotation;


/**
 * The HelloWorld.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class CreatedReviewedTest {


    /**
     * Says 'hello' to the 'world'.
     */
    @Created({
        @By(name = "JIn Kwon", comment = "Damn, I'm good!")
    })
    @Reviewed({
        @By(name = "Steve Jobs", comment = "Awesome!"),
        @By(name = "Linus Torvalds", comment = "*YOU* are full of bullshit."),
        @By(name = "James Gosling",
            comment = "sigh...shoot me now...can you get Josh Bloch fired?")
    })
    public static void main(final String[] args) {
        System.out.println("hello, world");
    }


}

