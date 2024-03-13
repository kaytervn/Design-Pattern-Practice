package timepasstechies.decorator;

public class PolicyCalibration extends CalibrationDecorator {
	public VendorCalibration vendorCalibration;

	public PolicyCalibration(VendorCalibration vendorCalibration) {
		super();
		this.vendorCalibration = vendorCalibration;
	}

	public PolicyCalibration() {
		super();
// TODO Auto-generated constructor stub
	}

	public VendorCalibration getVendorCalibration() {
		return vendorCalibration;
	}

	public void setVendorCalibration(VendorCalibration vendorCalibration) {
		this.vendorCalibration = vendorCalibration;
	}

	@Override
	public double calibrate(Double rawdata) {
// TODO Auto-generated method stub
		return vendorCalibration.calibrate(rawdata) * 0.998;
	}
}