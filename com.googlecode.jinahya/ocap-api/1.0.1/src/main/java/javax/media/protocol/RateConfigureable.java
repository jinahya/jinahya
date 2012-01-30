package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/RateConfigureable.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface RateConfigureable
{
    public abstract RateConfiguration[] getRateConfigurations();

    public abstract RateConfiguration setRateConfiguration(
        RateConfiguration config);
}
 
