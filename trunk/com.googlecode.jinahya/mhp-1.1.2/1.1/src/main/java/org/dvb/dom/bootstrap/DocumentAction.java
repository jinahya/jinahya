package org.dvb.dom.bootstrap;

/**
 * DocumentAction is used for an action that modifies a W3C
 * Document. When an application wishes to access a Document,
 * it creates an instance of a class that implements 
 * DocumentAction, and passes this instance to the system.
 * The system then calls back to the user code via the
 * DocumentAction interface.
 *
 * @see MultipleDocumentsAction
 * @see DocumentFactory
 **/

public interface DocumentAction {
            /**
             * Access and/or modify a W3C DOM Document. All modifications
             * must take place during this callback. The results of retaining
             * and using DOM references outside the context of this callback
             * are undefined.
	     *
	     * @param doc the DOM document to modify
             **/
            public void run(org.w3c.dom.Document doc);

        }

