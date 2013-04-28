package org.ocap.hardware.pod;

/**
 * This class represents an Application that resides in the
 * OpenCable CableCARD device. The CableCARD device has zero or more CableCARD 
 * Applications. PODApplication instances corresponding to those CableCARD 
 * Applications are retrieved by the 
 * {@link org.ocap.hardware.pod.POD#getApplications} method. 
 * This class provides information of the CableCARD Application described in the 
 * Application_info_req() APDU defined in [CCIF 2.0]. 
 **/
public interface PODApplication {
    /**
     * The Conditional Access application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See OpenCable CableCARD Interface 
     * specification. 
     **/
    public static final int TYPE_CA = 0;

    /**
     * The "Copy Protection" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See OpenCable CableCARD Interface 
     * specification. 
     **/
    public static final int TYPE_CP = 1;


    /**
     * The "IP Service" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See OpenCable CableCARD Interface 
     * specification. 
     **/
    public static final int TYPE_IP = 2;

    /**
     * The "Network Interface - DVS/167" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See [CCIF 2.0]. 
     **/
    public static final int TYPE_DVS167 = 3;

    /**
     * The "Network Interface - DVS/178" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See [CCIF 2.0]. 
     **/
    public static final int TYPE_DVS178 = 4;


    /**
     * The "Diagnostic" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See [CCIF 2.0]. 
     **/
    public static final int TYPE_DIAGNOSTIC = 6;


    /**
     * The "Undesignated" application type. 
     * This value is defined for the application_type field in the 
     * Application_info_cnf() APDU. See [CCIF 2.0]. 
     **/
    public static final int TYPE_UNDESIGNATED = 7;


    /**
     * This method returns an application type value of the CableCARD 
     * Application represented by this class. 
     * The application type is described in the application_type field in the 
     * Application_info_cnf() APDU. 
     *
     * @return  an application type value of the CableCARD application  
     *             represented by this class. Known values are defined as  
     *             the field values prefixed with "TYPE_". 
     **/
    public int getType();


    /**
     * This method returns an application version number of the CableCARD 
     * Application represented by this class. 
     * The application version number is described in the 
     * application_version_number field in the Application_info_cnf() APDU. 
     *
     * @return  an application version number value of the CableCARD Application 
     *             represented by this class. 
     **/
    public int getVersionNumber();


    /**
     * This method returns an application name of the CableCARD Application 
     * represented by this class. 
     * The application version number is described in the 
     * application_name_byte field in the Application_info_cnf() APDU. 
     *
     * @return  an application name of the CableCARD Application represented by 
     *             this class. 
     **/
    public String getName();


    /**
     * This method returns a URL of the CableCARD Application represented by 
     * this class. 
     * The URL is described in the application_url_byte field in the 
     * Application_info_cnf() APDU. 
     *
     * @return  a URL of the CableCARD Application represented by 
     *             this class. 
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     **/
    public String getURL();
}
