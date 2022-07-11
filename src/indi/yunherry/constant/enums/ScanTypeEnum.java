package indi.yunherry.constant.enums;

/**
 * @author YunHerry
 *
 */

public enum ScanTypeEnum {
    //模式1 只扫描注解 模式2 只扫描继承类 模式3 全部扫描
    SCAN_ONLY_ANNOTATION(1),SCAN_ONLY_FATHER_CLASS(2),SCAN_ALL(3);
    public final int model;
    private ScanTypeEnum(int model) {
        this.model = model;
    }

}
