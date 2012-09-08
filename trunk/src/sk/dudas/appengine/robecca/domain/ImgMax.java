package sk.dudas.appengine.robecca.domain;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 17.4.2011
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public enum ImgMax {

    // cropped
    s32c("32c"), s48c("45c"), s64c("64c"), s72c("72c"), s104c("104c"), s144c("144c"), s150c("150c"), s160c("160c"),
    // uncropped
    s32u("32u"), s48u("45u"), s64u("64u"), s72u("72u"), s104u("104u"), s144u("144u"), s150u("150u"), s160u("160u"),
    s94("94"), s110("110"), s128("128"), s200("200"), s220("220"), s288("288"), s320("320"), s400("400"), s512("512"), s576("576"),
    s640("640"), s720("720"), s800("800"), s912("912"), s1024("1024"), s1152("1152"), s1280("1280"), s1440("1440"), s1600("1600");

    private String maxSize;

    private ImgMax(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getMaxSize() {
        return maxSize;
    }


}
