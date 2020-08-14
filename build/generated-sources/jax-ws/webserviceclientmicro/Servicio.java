
package webserviceclientmicro;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Servicio", targetNamespace = "http://servicio.wskc/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Servicio {


    /**
     * 
     * @param tipo
     * @param data
     * @param empresa
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertTrx", targetNamespace = "http://servicio.wskc/", className = "webserviceclientmicro.InsertTrx")
    @ResponseWrapper(localName = "insertTrxResponse", targetNamespace = "http://servicio.wskc/", className = "webserviceclientmicro.InsertTrxResponse")
    @Action(input = "http://servicio.wskc/Servicio/insertTrxRequest", output = "http://servicio.wskc/Servicio/insertTrxResponse")
    public int insertTrx(
        @WebParam(name = "Data", targetNamespace = "")
        String data,
        @WebParam(name = "Tipo", targetNamespace = "")
        int tipo,
        @WebParam(name = "Empresa", targetNamespace = "")
        int empresa);

}