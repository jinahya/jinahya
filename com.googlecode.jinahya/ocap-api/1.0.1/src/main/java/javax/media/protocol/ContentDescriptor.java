package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/ContentDescriptor.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class ContentDescriptor extends Object
{
    public static final String CONTENT_UNKNOWN = "content_unknown";
    protected String typeName;

    public ContentDescriptor(String cdName)
    {
        super();
    }

    public String getContentType()
    {
        return null;
    }

    public static final String mimeTypeToPackageName(String mimeType)
    {
        return null;
    }
}
