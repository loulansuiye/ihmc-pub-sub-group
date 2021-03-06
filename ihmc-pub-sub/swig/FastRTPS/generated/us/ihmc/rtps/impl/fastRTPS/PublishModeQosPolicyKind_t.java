/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package us.ihmc.rtps.impl.fastRTPS;

public final class PublishModeQosPolicyKind_t {
  public final static PublishModeQosPolicyKind_t SYNCHRONOUS_PUBLISH_MODE = new PublishModeQosPolicyKind_t("SYNCHRONOUS_PUBLISH_MODE");
  public final static PublishModeQosPolicyKind_t ASYNCHRONOUS_PUBLISH_MODE = new PublishModeQosPolicyKind_t("ASYNCHRONOUS_PUBLISH_MODE");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static PublishModeQosPolicyKind_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + PublishModeQosPolicyKind_t.class + " with value " + swigValue);
  }

  private PublishModeQosPolicyKind_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private PublishModeQosPolicyKind_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private PublishModeQosPolicyKind_t(String swigName, PublishModeQosPolicyKind_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static PublishModeQosPolicyKind_t[] swigValues = { SYNCHRONOUS_PUBLISH_MODE, ASYNCHRONOUS_PUBLISH_MODE };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

