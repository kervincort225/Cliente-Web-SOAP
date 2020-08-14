
package webserviceclientmicro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webserviceclientmicro package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InsertTrxResponse_QNAME = new QName("http://servicio.wskc/", "insertTrxResponse");
    private final static QName _InsertTrx_QNAME = new QName("http://servicio.wskc/", "insertTrx");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webserviceclientmicro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertTrx }
     * 
     */
    public InsertTrx createInsertTrx() {
        return new InsertTrx();
    }

    /**
     * Create an instance of {@link InsertTrxResponse }
     * 
     */
    public InsertTrxResponse createInsertTrxResponse() {
        return new InsertTrxResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertTrxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.wskc/", name = "insertTrxResponse")
    public JAXBElement<InsertTrxResponse> createInsertTrxResponse(InsertTrxResponse value) {
        return new JAXBElement<InsertTrxResponse>(_InsertTrxResponse_QNAME, InsertTrxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertTrx }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.wskc/", name = "insertTrx")
    public JAXBElement<InsertTrx> createInsertTrx(InsertTrx value) {
        return new JAXBElement<InsertTrx>(_InsertTrx_QNAME, InsertTrx.class, null, value);
    }

}
