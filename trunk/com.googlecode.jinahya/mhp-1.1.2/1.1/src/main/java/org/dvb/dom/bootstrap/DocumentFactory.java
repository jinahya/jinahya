package org.dvb.dom.bootstrap;

/**
 * DocumentFactory contains bootstrap methods for applications embedded in a document
 * to access the document object model of the document in which they are contained.
 * @since MHP 1.1
**/

public interface DocumentFactory {
	    /**
	     * The property string to use with <code>XletContext.getXletProperty</code>
	     * in order to obtain the DocumentFactory for this Xlet (if one exists).
	     */
	    public static final String DOM = "dom";

	    /**
             * Perform an action on the document that contains the
             * Xlet controlled by the given XletContext.
             *
             * @param act  The action to perform. It will be called by
             *             the system either synchronously, or on a system
             *             thread.
             **/
            public void performAction(DocumentAction act);

	    /**
             * Perform an action on the document that contains the
             * Xlet controlled by the given XletContext. The action is
	     * called without any ability to modify the DOM.
             *
             * @param act  The action to perform. It will be called by
             *             the system either synchronously, or on a system
             *             thread. Attempts by this action to modify the DOM
	     * 		   shall fail.
             **/
            public void performActionReadOnly(DocumentAction act);

            /**
             * Perform an action on a set of documents, each contained
             * in a frame that is a part of the same application as the
             * Xlet controlled by the given XletContext.
             *
             * @param names The names of the desired frames.
             * @param act  The action to perform. It will be called by
             *             the system either synchronously, or on a system
             *             thread.
             **/
            public void performActionOnFrames(
                                        String[] names,
                                        MultipleDocumentsAction act);
        }

