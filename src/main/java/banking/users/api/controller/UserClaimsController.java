package banking.users.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import banking.common.api.controller.ResponseHandler;
import banking.users.application.UserClaimApplicationService;
import banking.users.application.dto.UserClaimDto;

@RestController
@RequestMapping("api/users/{userId}/claims")
public class UserClaimsController {
	@Autowired
	UserClaimApplicationService userClaimApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.POST, path = "", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@PathVariable("userId") long userId, @RequestBody UserClaimDto userClaimDto) throws Exception {
        try {
        	userClaimDto = userClaimApplicationService.create(userId, userClaimDto);
            return new ResponseEntity<Object>(userClaimDto, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	/*
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> get(@PathVariable("userId") long userId) {
        try {
            List<UserClaimDto> users = userClaimApplicationService.get(userId);
            return this.responseHandler.getDataResponse(users, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }*/
}
