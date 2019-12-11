/*package banking.users.application.dto.deserializer;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import banking.common.application.enumeration.RequestBodyType;
//import banking.users.application.dto.UserClaimDto;
import banking.users.application.dto.UserDto;

public class UserDtoDeserializer extends JsonDeserializer<UserDto> {
	@Override
	public UserDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		UserDto userDto = null;
		//UserClaimDto userClaimDto = null;
		//Set<UserClaimDto> userClaimDtos = null;
		try {
    		ObjectCodec objectCodec = jsonParser.getCodec();
            JsonNode node = objectCodec.readTree(jsonParser);
            String name = node.get("name").asText();
            String password = node.get("password").asText();
            userDto = new UserDto(name, password);
            /*
            node.findValues("claims").forEach(nodeClaim -> {
                String type = nodeClaim.get("type").asText();
            	String value = nodeClaim.get("value").asText();
            	userClaimDtos.add(new UserClaimDto(type, value));    
            });
*/          
            
            /*
            JsonNode nodeClaim = node.get("claims");
            System.out.println(nodeClaim);
            String type = nodeClaim.get("type").asText();
            String value = nodeClaim.get("value").asText();
            
            System.out.println("type"+ type);
            System.out.println("value" + value);
            
            userClaimDto = new UserClaimDto(type, value);
            System.out.println("ANTES userClaimDtos.add(userClaimDto);");
            userClaimDtos.add(userClaimDto); 
            System.out.println("ANTES userDto = new UserDto(name, password, userClaimDtos);");
            userDto = new UserDto(name, password, userClaimDtos);
            System.out.println("FIN userDto = new UserDto(name, password, userClaimDtos);");*/
  /*  	} catch(Exception ex) {
    		userDto = new UserDto(RequestBodyType.INVALID.toString(), RequestBodyType.INVALID.toString());
    	}
        return userDto;
	}	
}
*/