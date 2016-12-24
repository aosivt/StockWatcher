package aosivt.server.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


/**
 * Created by iskander on 24.12.16.
 */
@Configuration
public class SoapClientConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in pom.xml
        marshaller.setContextPath("structureXML.wsdl");
        return marshaller;
    }

    @Bean
    public ConSoapClient soapClient(Jaxb2Marshaller marshaller) {
        ConSoapClient client = new ConSoapClient();
        client.setDefaultUri("http://localhost:8080/ws/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
