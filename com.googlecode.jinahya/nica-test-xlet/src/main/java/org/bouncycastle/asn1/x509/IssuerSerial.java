package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

public class IssuerSerial
    extends ASN1Object
{
    GeneralNames            issuer;
    ASN1Integer              serial;
    DERBitString            issuerUID;

    public static IssuerSerial getInstance(
            Object  obj)
    {
        if (obj == null || obj instanceof IssuerSerial)
        {
            return (IssuerSerial)obj;
        }

        if (obj instanceof ASN1Sequence)
        {
            return new IssuerSerial((ASN1Sequence)obj);
        }

        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static IssuerSerial getInstance(
        ASN1TaggedObject obj,
        boolean          explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }
    
    public IssuerSerial(
        ASN1Sequence    seq)
    {
        if (seq.size() != 2 && seq.size() != 3)
        {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        
        issuer = GeneralNames.getInstance(seq.getObjectAt(0));
        serial = ASN1Integer.getInstance(seq.getObjectAt(1));

        if (seq.size() == 3)
        {
            issuerUID = DERBitString.getInstance(seq.getObjectAt(2));
        }
    }
    
    public IssuerSerial(
        GeneralNames    issuer,
        ASN1Integer      serial)
    {
        this.issuer = issuer;
        this.serial = serial;
    }

    public GeneralNames getIssuer()
    {
        return issuer;
    }

    public ASN1Integer getSerial()
    {
        return serial;
    }

    public DERBitString getIssuerUID()
    {
        return issuerUID;
    }

    /**
     * Produce an object suitable for an ASN1OutputStream.
     * <pre>
     *  IssuerSerial  ::=  SEQUENCE {
     *       issuer         GeneralNames,
     *       serial         CertificateSerialNumber,
     *       issuerUID      UniqueIdentifier OPTIONAL
     *  }
     * </pre>
     */
    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector  v = new ASN1EncodableVector();

        v.add(issuer);
        v.add(serial);

        if (issuerUID != null)
        {
            v.add(issuerUID);
        }

        return new DERSequence(v);
    }
}
