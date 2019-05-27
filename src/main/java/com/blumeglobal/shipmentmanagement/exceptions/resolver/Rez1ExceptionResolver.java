/*
 * package com.blumeglobal.shipmentmanagement.exceptions.resolver;
 * 
 * import java.util.ArrayList; import java.util.Date; import java.util.List;
 * import java.util.Map;
 * 
 * import org.apache.logging.log4j.LogManager; import
 * org.apache.logging.log4j.Logger; import
 * org.apache.logging.log4j.core.lookup.StrSubstitutor; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.Ordered; import
 * org.springframework.core.annotation.Order; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.validation.FieldError; import
 * org.springframework.web.bind.MethodArgumentNotValidException; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.bind.annotation.ResponseStatus; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.context.request.WebRequest; import
 * org.springframework.web.servlet.mvc.method.annotation.
 * ResponseEntityExceptionHandler;
 * 
 * import com.blumeglobal.shipmentmanagement.exceptions.AuthorizationException;
 * import com.blumeglobal.shipmentmanagement.exceptions.BusinessException;
 * import
 * com.blumeglobal.shipmentmanagement.exceptions.DataAlreadyExistException;
 * import com.blumeglobal.shipmentmanagement.exceptions.DataNotFoundException;
 * import com.blumeglobal.shipmentmanagement.exceptions.ExceptionResponse;
 * import com.blumeglobal.shipmentmanagement.exceptions.InValidDataException;
 * import com.blumeglobal.shipmentmanagement.exceptions.MaintainanceException;
 * import com.blumeglobal.shipmentmanagement.exceptions.NoContentException;
 * import com.blumeglobal.shipmentmanagement.exceptions.Rez1BaseException;
 * import com.blumeglobal.shipmentmanagement.model.ExceptionDataKey; import
 * com.blumeglobal.shipmentmanagement.service.helper.ShipmentConstants; import
 * com.blumeglobal.shipmentmanagement.utils.ShipmentUtil;
 * 
 *//**
	 * The Rez1ExceptionResolver is used to handle all the exceptions of the rest
	 * controller
	 *
	 *//*
		 * @Order(Ordered.HIGHEST_PRECEDENCE)
		 * 
		 * @ControllerAdvice
		 * 
		 * @RestController public class Rez1ExceptionResolver extends
		 * ResponseEntityExceptionHandler { private static final Logger logger =
		 * LogManager.getLogger(ResponseEntityExceptionHandler.class);
		 * 
		 * @Autowired Map<String,String> exceptionData;
		 * 
		 * String app ="SCV";
		 * 
		 * String getExceptionMessage(){ return null; }
		 * 
		 * ExceptionResponse getExceptionMessage(Rez1BaseException exception,WebRequest
		 * request){ String language = request.getHeader("language");
		 * if(ShipmentUtil.isNullString(language)) { language = "ENG"; } String
		 * exceptionCode = exception.getExceptionCode(); String application = app;
		 * ExceptionDataKey exceptionDataKey = new ExceptionDataKey();
		 * exceptionDataKey.setAppName(application);
		 * exceptionDataKey.setCode(exceptionCode);
		 * exceptionDataKey.setLanguage(language); String message =
		 * exceptionData.get(exceptionDataKey.toString()); Map<String, String> valuesMap
		 * = exception.getValuesMap();
		 * 
		 * if(valuesMap!=null) { StrSubstitutor sub = new StrSubstitutor(valuesMap);
		 * message = sub.replace(message); } ExceptionResponse exceptionResponse = new
		 * ExceptionResponse(); if(message==null) message=exception.getMessage();
		 * exceptionResponse.setExceptionMessage(message);
		 * exceptionResponse.setTimestamp(new Date());
		 * exceptionResponse.setExceptionCode(exceptionCode); return exceptionResponse;
		 * }
		 * 
		 * @ExceptionHandler(DataAlreadyExistException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.OK) public ExceptionResponse
		 * handleAllException(DataAlreadyExistException
		 * dataAlreadyExistException,WebRequest request) { return
		 * getExceptionMessage(dataAlreadyExistException,request); }
		 * 
		 * @ExceptionHandler(BusinessException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.FORBIDDEN) public ExceptionResponse
		 * handleAllException(BusinessException businessException,WebRequest request) {
		 * 
		 * return getExceptionMessage(businessException,request); }
		 * 
		 * 
		 * @ExceptionHandler(DataNotFoundException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.OK) public ExceptionResponse
		 * handleAllException(DataNotFoundException dataNotFoundException,WebRequest
		 * request) {
		 * 
		 * return getExceptionMessage(dataNotFoundException,request); }
		 * 
		 * @ExceptionHandler(InValidDataException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.OK) public ExceptionResponse
		 * handleBusinessException(InValidDataException inValidDataException,WebRequest
		 * request) {
		 * 
		 * return getExceptionMessage(inValidDataException,request); }
		 * 
		 * @ExceptionHandler(NoContentException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.OK) public ExceptionResponse
		 * handleBusinessException(NoContentException noContentException,WebRequest
		 * request) {
		 * 
		 * return getExceptionMessage(noContentException,request); }
		 * 
		 * @ExceptionHandler(AuthorizationException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) public ExceptionResponse
		 * handleBusinessException(AuthorizationException
		 * authorizationException,WebRequest request) {
		 * 
		 * return getExceptionMessage(authorizationException,request); }
		 * 
		 * @ExceptionHandler(MaintainanceException.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public ExceptionResponse
		 * handleBusinessException(MaintainanceException exception,WebRequest request) {
		 * 
		 * return getExceptionMessage(exception,request); }
		 * 
		 * @ExceptionHandler(Exception.class)
		 * 
		 * @ResponseBody
		 * 
		 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public ExceptionResponse
		 * handleBAllExceptions(Exception exception,WebRequest request) { logger.
		 * error("Unhandled Runtime Exception ResponseEntityExceptionHandler.handleBusinessException."
		 * , exception); Rez1BaseException ex=new
		 * Rez1BaseException(exception.getMessage(), exception);
		 * ex.setExceptionCode(ShipmentConstants.UNHABNDLED_RUNTIME_EXCEPTION); return
		 * getExceptionMessage(ex,request); }
		 * 
		 * @Override protected ResponseEntity<Object>
		 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
		 * headers, HttpStatus status, WebRequest request) { String msg =
		 * processFieldErrors(ex.getBindingResult().getFieldErrors()); Rez1BaseException
		 * rex=new Rez1BaseException(msg);
		 * rex.setExceptionCode(ShipmentConstants.INPUT_VALIDATION_ERROR); return new
		 * ResponseEntity(getExceptionMessage(rex,request), HttpStatus.BAD_REQUEST); }
		 * 
		 * private String processFieldErrors(List<FieldError> fieldErrors) { String
		 * message =""; for (FieldError fieldError: fieldErrors) { message
		 * +=fieldError.getField()+" : " +fieldError.getDefaultMessage()+"; "; } return
		 * message; }
		 * 
		 * }
		 */