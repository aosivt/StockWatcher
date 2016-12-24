package aosivt.server.SoapClient;

/**
 * Created by iskander on 24.12.16.
 */


import structureXML.wsdl.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ConSoapClient extends WebServiceGatewaySupport {

private GetListBankResponse listBank;

public ConSoapClient()
{

}
    private static final Logger log = LoggerFactory.getLogger(ConSoapClient.class);

    public GetListBankResponse getListBankResponse() {

        GetListBankRequest request = new GetListBankRequest();

        GetListBankResponse response =  null;
        try {
            response = (GetListBankResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                    "http://localhost:8080/ws",
                    request,
                    new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetListBankRequest"));
} catch (Exception e)
        {
            e.getLocalizedMessage();
        }


        return response;
    }

    public GetListBankResponse getListBank() {
        return listBank;
    }

    public void setListBank(GetListBankResponse listBank) {
        this.listBank = listBank;
    }
}
