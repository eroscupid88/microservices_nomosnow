package ca.nomosnow.shareresource.common;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Getter
@Setter
public class Address {

  private String street1;
  private String street2;
  private String city;
  private String province;
  private String country;

  private Address() {
  }

  public Address(String street1, String street2, String city, String province, String country) {
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.province = province;
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
