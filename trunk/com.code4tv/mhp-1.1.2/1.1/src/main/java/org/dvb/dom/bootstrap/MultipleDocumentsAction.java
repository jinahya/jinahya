package org.dvb.dom.bootstrap;

/**
 * MultipleDocumentAction is used for an action that modifies 
 * zero or more W3C
 * Document instances. When an application wishes to access a
 * number of Documents simultaneously,
 * it creates an instance of a class that implements 
 * MultipleDocumentAction, and passes this instance to the system.
 * The system then calls back to the user code via the
 * MultipleDocumentAction interface.
 *
 * @see DocumentAction
 * @see DocumentFactory
**/

        public interface MultipleDocumentsAction {

            /**
             * Access and/or modify a set of W3C DOM Documents. All 
             * modifications must take place during this callback. The 
             * results of retaining and using DOM references outside the 
             * context of this callback are undefined.
             *
             * @param docs  The array of documents on which to operate.
             *              If a requested document is not found, the
             *              corresponding entry in this array will be null.
             **/
            public void run(org.w3c.dom.Document[] docs);

        }


