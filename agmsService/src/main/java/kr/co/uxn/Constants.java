package kr.co.uxn;

//Define Global Constants
public class Constants {

    //authority type
    public enum AUTHORITY {
        PATIENT("PATIENT"),
        STAFF("STAFF"),
        ADMIN("ADMIN");

        private final String value;

        AUTHORITY(String value) {
            this.value=value;
        }

        public String getValue() {
            return value;
        }
    }

    public static final long TIME_MIN_30 = 30;

}
