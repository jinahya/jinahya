package jinahya.misc.clazz.reflector.microedition.xlet;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.microedition.xlet.Xlet;
import javax.microedition.xlet.XletContext;
import javax.microedition.xlet.XletStateChangeException;

import jinahya.misc.clazz.reflector.Reflector;

import org.xml.sax.SAXException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletImpl implements Xlet {


    @Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        // EMPTY
    }


    @Override
    public void startXlet() throws XletStateChangeException {

        try {

            //final OutputStream out = System.out;

            final OutputStream out = new FileOutputStream("out.xml");

            final Reflector reflector = new Reflector(out);

            try {
                reflector.startReflect();

                for (File file : new File("specs").listFiles(new FileFilter() {
                    @Override
                    public boolean accept(final File pathname) {
                        return pathname.isFile();
                    }
                })) {

                    final BufferedReader br =
                        new BufferedReader(new FileReader(file));
                    try {
                        reflector.reflectSpec(br, file.getName());
                    } finally {
                        br.close();
                    }
                }

                reflector.endReflect();

                out.flush();
                out.close();

            } catch (SAXException saxe) {
                saxe.printStackTrace();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @Override
    public void pauseXlet() {
        // EMPTY
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        // EMPTY
    }
}
