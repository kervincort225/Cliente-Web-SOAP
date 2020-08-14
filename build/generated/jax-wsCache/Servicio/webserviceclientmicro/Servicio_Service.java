
package webserviceclientmicro;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Servicio", targetNamespace = "http://servicio.wskc/", wsdlLocation = "http://200.27.62.76:52324/WSKC/Servicio?wsdl")
public class Servicio_Service
    extends Service
{

    private final static URL SERVICIO_WSDL_LOCATION;
    private final static WebServiceException SERVICIO_EXCEPTION;
    private final static QName SERVICIO_QNAME = new QName("http://servicio.wskc/", "Servicio");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://200.27.62.76:52324/WSKC/Servicio?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICIO_WSDL_LOCATION = url;
        SERVICIO_EXCEPTION = e;
    }

    public Servicio_Service() {
        super(__getWsdlLocation(), SERVICIO_QNAME);
    }

    public Servicio_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICIO_QNAME, features);
    }

    public Servicio_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICIO_QNAME);
    }

    public Servicio_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICIO_QNAME, features);
    }

    public Servicio_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Servicio_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Servicio
     */
    @WebEndpoint(name = "ServicioPort")
    public Servicio getServicioPort() {
        return super.getPort(new QName("http://servicio.wskc/", "ServicioPort"), Servicio.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Servicio
     */
    @WebEndpoint(name = "ServicioPort")
    public Servicio getServicioPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicio.wskc/", "ServicioPort"), Servicio.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICIO_EXCEPTION!= null) {
            throw SERVICIO_EXCEPTION;
        }
        return SERVICIO_WSDL_LOCATION;
    }

}
