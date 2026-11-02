package space.tictac.datasource.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class BoardConverter implements AttributeConverter<int [][],String> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(int[][] attribute) {
        try {
            return MAPPER.writeValueAsString(attribute);
        }
        catch (JsonProcessingException e){
            throw  new IllegalArgumentException(e);
        }
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        try {
            return MAPPER.readValue(dbData, int [][].class);
        }
        catch (JsonProcessingException e){
            throw  new IllegalArgumentException(e);
        }
    }
}
