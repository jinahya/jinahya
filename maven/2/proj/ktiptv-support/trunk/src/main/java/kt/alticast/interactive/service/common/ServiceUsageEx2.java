package kt.alticast.interactive.service.common;


import ktiptv.Constants;


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public class ServiceUsageEx2 extends ServiceUsageEx {


    public ServiceUsageEx newInstance(String serviceId) {
        return new ServiceUsageEx2(serviceId);
    }


    protected ServiceUsageEx2(String serviceId) {
        super(serviceId);
    }


    //@Override
    public void start() {
        start(true);
    }


    public synchronized void start(boolean fork) {
        startTime = now();
        if (fork) {
            new Thread() {
                //@Override
                public void run() {
                    startServiceUsage();
                }
            }.start();
        } else {
            startServiceUsage();
        }
    }


    protected final void startServiceUsage() {
        usageId = 0L;
        try {
            usageId = serviceUsage.startServiceUsage(
                Constants.SAID, Constants.PIN_NUMBER, serviceId, startTime,
                null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void finish() {
        finish(true);
    }


    //@Override
    public synchronized void finish(boolean fork) {
        if (fork) {
            new Thread() {
                //@Override
                public void run() {
                    endServiceUsage();
                }
            }.start();
        } else {
            endServiceUsage();
        }
    }


    protected final void endServiceUsage() {
        if (usageId != 0L) {
            try {
                serviceUsage.endServiceUsage(usageId, now(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createServiceUsage();
        }
    }


    private long usageId;
}
