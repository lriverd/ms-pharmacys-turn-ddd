package cl.duamit.pharmacy.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;


public class Base64Formatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Formatter.class);

    public static Base64Formatter build(){return new Base64Formatter();}

    public String format(Object obj){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(obj);
            return DatatypeConverter.printBase64Binary(json.getBytes());
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }
}
