package ca.nomosnow.eventrequest.domain;

public class SportOrganizationEventCreateDepartmentNotFoundException extends RuntimeException {
  public SportOrganizationEventCreateDepartmentNotFoundException(long sportOrganizationEventCreateDepartmentId) {
    super("sport organization event department not found with id " + sportOrganizationEventCreateDepartmentId);
  }
}
