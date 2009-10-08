package kt.alticast.interactive.service.common;


import kt.alticast.interactive.service.common.client.ServiceUsageClientStub;

import ktiptv.Constants;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ServiceUsageEx {

    public static ServiceUsageEx newIntance(String serviceId) {
        return new ServiceUsageEx(serviceId);
    }


    protected ServiceUsageEx(String serviceId) {
        super();

        this.serviceId = serviceId;

        serviceUsage = new ServiceUsageClientStub(ServiceUsage.class);
    }


    //@Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(":)");
    }


    protected String now() {
        return ServiceUsageClientStub.getCurrentTime
            (System.currentTimeMillis());
    }


    public synchronized void start() {
        startTime = now();
    }


    public synchronized void finish(boolean fork) {
        if (startTime == null) {
            startTime = now();
        }
        if (fork) {
            new Thread() {
                public void run() {
                    createServiceUsage();
                }
            }.start();
        } else {
            createServiceUsage();
        }
    }


    protected final void createServiceUsage() {
        try {
            serviceUsage.createServiceUsage(Constants.SAID,
                Constants.PIN_NUMBER, serviceId, startTime, now(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected String serviceId;

    protected ServiceUsage serviceUsage;

    protected String startTime = null;
}
