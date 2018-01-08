package com.stratio.cct.servicestatus.api.constants;

public class ServiceStatusConstants {

	public static final String DCOS_ACS_AUTH_COOKIE = "dcos-acs-auth-cookie";

	public static final String WS_TOPIC = "/topic/status";

	public static final String WS_ENDOPOINT = "/service-status";

	public static final String WS_MESSAGE_MAPPING = "/status";

	public static final String SERVICE_STATUS_WS_RAW_ENDPOINT = "/service/status/all";

	public static final String SERVICE_STATUS_WS_LAUNCHER_ENDPOINT = "/plataform/service/status";

	public static final String DATA_SOURCE_EXCEPTION_CODE = "500";

	public static final String CONSUL_NOT_AVARIABLE = "Consul not avariable";

	public static final String MARATHON_NOT_AVARIABLE = "Marathon not avariable";

	public static final String DATA_SOURCE_EXCEPTION = "DataSource exception";

	public static final String NOAUTH_EXCEPTION_CODE = "401";

	public static final String NOAUTH_EXCEPTION = "Unauthorized";

	public static final String CLOSE = "CLOSE";

	public static final String BAD_REQUEST_MESSAGE = "Invalid input parameters for %s";

	public static final String QUERY_ERROR_MESSAGE = "Error retrieving data";

	public static final String INSTALL_ERROR_MESSAGE = "Error installing service";

	public static final String LAUNCHER_STATUS_ENDPOINT = "/platform/status";

	public static final String LAUNCHER_STATUS_QUEUE = "/queue/launcher";

	public static final String NRT_STATUS_QUEUE = "/queue/platform";

	public static final String LAUNCHER_INSTALL_SERVICE_ENDPOINT = "/platform/service";

	public static final String LABEL_VERSION = "version";

	public static final String LABEL_STRATIO_KEY = "owner";

	public static final String LABEL_STRATIO_VALUE = "stratio";

	public static final String DEFAULT_ZONE_ID = "Europe/Madrid";

	public static final String DEFAULT_DATASOURCE_PATH = "jdbc:postgresql://%s:%s/%s?ssl=true&sslmode=verify-full&sslcert=/etc/stratio/client.crt&sslkey=/etc/stratio/client.key.pk8&sslrootcert=/etc/stratio/root.cert";

}
