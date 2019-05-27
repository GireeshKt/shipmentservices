package com.blumeglobal.shipmentmanagement.external.hub.endpoint;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.blumeglobal.shipmentmanagement.external.hub.endpoint.response.OrganizationResponse;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.response.PersonResponse;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.response.UserGroupResponse;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.response.UsersResponse;
import com.ias.hub.core.dto.PasscodeDTO;
import com.ias.hub.core.dto.PersonDTO;
import com.ias.hub.core.dto.UserGroupsDTO;
import com.ias.hub.core.dto.UserProfileDTO;

@WebService(targetNamespace="http://www.interasset.com")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL,parameterStyle=ParameterStyle.BARE)
public interface IDispatchManagerFacade {
	/**
	 * Gets the organization.
	 *
	 * @param code the code
	 * @return the organization
	 */
	@WebMethod(operationName = "getOrganization")
	public OrganizationResponse getOrganization(@WebParam(name = "code") String code);

	/**
	 * Gets the person by id.
	 *
	 * @param dbid the dbid
	 * @return PersonResponse
	 */
	@WebMethod(operationName = "getPersonById")
	public PersonResponse getPersonById(@WebParam(name = "dbid") Long dbid);

	/**
	 * Gets the offices by party id.
	 *
	 * @param dbid the dbid
	 * @return OrganizationResponse
	 */
	@WebMethod(operationName = "getOfficesByPartyId")
	public OrganizationResponse getOfficesByPartyId(@WebParam(name = "dbid") Long dbid);

	/**
	 * Gets the organization by id.
	 *
	 * @param orgDbId the org db id
	 * @return returns OrganizationResponse
	 */
	@WebMethod(operationName = "getOrganizationById")
	public OrganizationResponse getOrganizationById(@WebParam(name = "orgDbId") Long orgDbId);


	/**
	 * Gets the org by person db id.
	 *
	 * @param personDbId the person db id
	 * @return OrganizationResponse
	 */
	@WebMethod(operationName = "getOrgByPersonDbId")
	public OrganizationResponse getOrgByPersonDbId(@WebParam(name = "personDbId") Long personDbId);

	/**
	 * Gets the organizations.
	 *
	 * @param orgIds List<Long>
	 * @return OrganizationResponse
	 */
	@WebMethod(operationName = "getOrganizations")
	public OrganizationResponse getOrganizations(List<Long> orgIds);

	/**
	 * Authenticate.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param appCode the app code
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "authenticate")
	public boolean authenticate(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password,
			@WebParam(name = "appCode") String appCode) throws Exception;


	/**
	 * Gets the user profile by user name.
	 *
	 * @param userName the user name
	 * @param appCode the app code
	 * @return the user profile by user name
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getUserProfileByUserName")
	public UserProfileDTO getUserProfileByUserName(@WebParam(name = "userName") String userName,
			@WebParam(name = "appCode") String appCode) throws Exception;

	/**
	 * Gets the organizations by ids.
	 *
	 * @param DbidList the dbid list
	 * @param orgType the org type
	 * @return the organizations by ids
	 */
	@WebMethod(operationName = "getOrganizationsByIds")
	public OrganizationResponse getOrganizationsByIds(@WebParam(name = "DbidList") List<Long> DbidList, String orgType);


	/**
	 * Gets the organizations by code.
	 *
	 * @param CodeList the code list
	 * @return the organizations by code
	 */
	@WebMethod(operationName = "getOrganizationsByCode")
	public OrganizationResponse getOrganizationsByCode(@WebParam(name = "CodeList") List<String> CodeList);


	/**
	 * Gets the all originator or receivers.
	 *
	 * @param organizationType the organization type
	 * @param appCode the app code
	 * @return the all originator or receivers
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getAllOriginatorOrReceivers")
	public OrganizationResponse getAllOriginatorOrReceivers(@WebParam(name = "organizationType") String organizationType,
			@WebParam(name = "appCode") String appCode) throws Exception;


	/**
	 * Gets the partnered parties.
	 *
	 * @param partyId the party id
	 * @param applicationCode the application code
	 * @return the partnered parties
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getPartneredParties")
	public OrganizationResponse getPartneredParties(@WebParam(name = "partyId") Long partyId,
			@WebParam(name = "applicationCode") String applicationCode) throws Exception;

	/**
	 * Gets the all partnered ep organizations.
	 *
	 * @param organizationDbId the organization db id
	 * @param organizationType the organization type
	 * @param applicationCode the application code
	 * @return the all partnered ep organizations
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getDistinctPartyIdList")
	public OrganizationResponse getDistinctPartyIdList(@WebParam(name = "organizationDbId") Long organizationDbId,
			@WebParam(name = "organizationType") String organizationType,
			@WebParam(name = "applicationCode") String applicationCode) throws Exception;

	/**
	 * Find all person by organization id.
	 *
	 * @param organizationDbid the organization dbid
	 * @return PersonResponse
	 * @throws Exception the exception
	 */
	public PersonResponse findAllPersonByOrganizationId(Long organizationDbid) throws Exception;


	/**
	 * Gets the organization and application member ship data.
	 *
	 * @param orgCode the org code
	 * @return OrganizationResponse
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getOrganizationAndApplicationMemberShipData")
	public OrganizationResponse getOrganizationAndApplicationMemberShipData(@WebParam(name = "orgCode") String orgCode) throws Exception;

	/**
	 * Gets the organization and application member ship data for mobile.
	 *
	 * @param organizationId the organization id
	 * @param applicationCode the application code
	 * @return the organization and application member ship data for mobile
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getOrganizationAndApplicationMemberShipDataForMobile")
	public OrganizationResponse getOrganizationAndApplicationMemberShipDataForMobile(@WebParam(name = "organizationId")Long organizationId,
																					 @WebParam(name = "applicationCode")String applicationCode) throws Exception;

	
	/**
	 * Gets the user by name.
	 *
	 * @param userName the user name
	 * @param isChangePassword the is change password
	 * @return UsersResponse
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getUserByName")
	public UsersResponse getUserByName(
			@WebParam(name = "userName")String userName,
			@WebParam(name = "isChangePassword")Boolean isChangePassword)throws Exception;

	/**
	 * Change password.
	 *
	 * @param userName the user name
	 * @param newPassword the new password
	 * @param changeRequestedAt the change requested at
	 * @return UsersResponse
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "changePassword")
	public UsersResponse changePassword(
			@WebParam(name = "userName")String userName,
			@WebParam(name = "newPassword")String newPassword,
			@WebParam(name = "changeRequestedAt")Long changeRequestedAt)throws Exception;

	/**
	 * Update profile.
	 *
	 * @param userName the user name
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @return UsersResponse
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "updateProfile")
	public UsersResponse updateProfile(
			@WebParam(name = "userName")String userName,
			@WebParam(name = "oldPassword")String oldPassword,
			@WebParam(name = "newPassword")String newPassword)throws Exception;

	/**
	 * Gets the partnership facility list.
	 *
	 * @param facilityIdList the facility id list
	 * @param orgType the org type
	 * @param applicationCode the application code
	 * @return the partnership facilities
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getPartnershipFacilityList")
	public OrganizationResponse getPartnershipFacilityList(@WebParam(name = "facilityIds") List<Long> facilityIdList,
														 @WebParam(name = "orgType") String orgType,
														 @WebParam(name = "applicationCode") String applicationCode) throws Exception;

	/**
	 * Gets the partnership facilities.
	 *
	 * @param facilityIdList the facility id list
	 * @param orgType the org type
	 * @param applicationCode the application code
	 * @return the partnership facilities
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getPartnershipFacilities")
	public OrganizationResponse getPartnershipFacilities(@WebParam(name = "facilityIdList") List<Long> facilityIdList,
			@WebParam(name = "orgType") String orgType,
			@WebParam(name = "applicationCode") String applicationCode) throws Exception;


	/**
	 * Gets the notification document types.
	 *
	 * @param applicationCode the application code
	 * @return the notification document types
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getNotificationDocumentTypes")
	public List<String> getNotificationDocumentTypes(@WebParam(name = "applicationCode")String applicationCode) throws Exception;


	/**
	 * Gets the notification user groups.
	 *
	 * @param organizationCode the organization code
	 * @return the notification document types
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getNotificationUserGroups")
	public UserGroupResponse getNotificationUserGroups(@WebParam(name = "organizationCode")String organizationCode) throws Exception;

	/**
	 * Gets the notification user group.
	 *
	 * @param groupId the group id
	 * @return the notification user group
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getNotificationUserGroup")
	public UserGroupsDTO getNotificationUserGroup(@WebParam(name = "groupId")Long groupId) throws Exception;

	/**
	 * Gets the notification user group by dbids.
	 *
	 * @param groupDbids the group dbids
	 * @return the notification user group by dbids
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "getNotificationUserGroupByDbids")
	public UserGroupResponse getNotificationUserGroupByDbids(@WebParam(name = "groupDbids")List<Long> groupDbids) throws Exception;

	/**
	 * Register new branch offices.
	 *
	 * @param organizationNewDbids the organization new dbids
	 * @param appCode the app code
	 * @return the organization response
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "registerNewBranchOffices")
	public OrganizationResponse registerNewBranchOffices(@WebParam(name = "organizationNewDbids")List<Long> organizationNewDbids,@WebParam(name = "appCode")String appCode) throws Exception;


	/**
	 * De register existing branch offices.
	 *
	 * @param organizationExistingDbids the organization existing dbids
	 * @param appCode the app code
	 * @return the organization response
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "deRegisterExistingBranchOffices")
	public OrganizationResponse deRegisterExistingBranchOffices(@WebParam(name = "organizationExistingDbids")List<Long> organizationExistingDbids,@WebParam(name = "appCode")String appCode) throws Exception;


	/**
	 * Register new user.
	 *
	 * @param personDTO the person dto
	 * @param viewAllJobs the view all jobs
	 * @param mobileAppCode the mobile app code
	 * @return the person response
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "registerNewMobileUser")
	public PersonResponse registerNewMobileUser(@WebParam(name = "personDTO")PersonDTO personDTO,@WebParam(name = "mobileAppCode")String mobileAppCode) throws Exception;


	/**
	 * Update registered user.
	 *
	 * @param mobileAppCode the mobile app code
	 * @param viewAllJobs the view all jobs
	 * @param personDTO the person dto
	 * @return the person response
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "updateRegisteredUser")
	public PersonResponse updateRegisteredUser(@WebParam(name = "mobileAppCode")String mobileAppCode,@WebParam(name = "personDTO")PersonDTO personDTO) throws Exception;


	/**
	 * Signup.
	 *
	 * @param mobileUser the mobile user
	 * @param passcode the passcode
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "verifyCode")
	public PasscodeDTO verifyCode(@WebParam(name = "mobileUser") String mobileUser,@WebParam(name = "passcode") Long passcode) throws Exception;

	/**
	 * Resend passcode.
	 *
	 * @param mobileUserName the mobile user name
	 * @param action the action
	 * @return the long
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "resendPasscode")
	public Long resendPasscode(@WebParam(name = "mobileUserName") String mobileUserName,@WebParam(name = "action") String action) throws Exception;

	/**
	 * Reset password.
	 *
	 * @param mobileUserNameForReset the mobile user name for reset
	 * @param password the password
	 * @return the users response
	 * @throws Exception the exception
	 */
	@WebMethod(operationName = "resetPassword")
	public UsersResponse resetPassword(@WebParam(name = "mobileUserNameForReset") String mobileUserNameForReset,@WebParam(name = "password")String password) throws Exception;
}