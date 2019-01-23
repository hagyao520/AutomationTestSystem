package Test;

/**
 * 特性实体
 */
public class ApkImpliedFeature {

	   /**
	 * 所需设备特性名称。
	 */
	 private String feature;

	 /**
	 * 表明所需特性的内容。
	 */
	 private String implied;

	 public ApkImpliedFeature() {
	      super();
	 }

	 public ApkImpliedFeature(String feature, String implied) {
	      super();
	      this.feature = feature;
	      this.implied = implied;
	 }

	 public String getFeature() {
	      return feature;
	 }

	 public void setFeature(String feature) {
	      this.feature = feature;
	 }

	 public String getImplied() {
	      return implied;
	 }

	 public void setImplied(String implied) {
	      this.implied = implied;
	 }

	 @Override
	 public String toString() {
	      return "Feature [feature=" + feature + ", implied=" + implied + "]";
	 }
}