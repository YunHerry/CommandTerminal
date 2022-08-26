package indi.yunherry.constant.enums;

/**
 * @author YunHerry
 * 扫描枚举,用于配置扫描模式
 * Scan enum, used to configure scan mode
 */

public enum ScanTypeEnum {
    //模式1 只扫描注解 模式2 只扫描继承类 模式3 全部扫描
    //scan mode1 annotation only | scan mode2 fatherclass only | scan mode3 all
    SCAN_ONLY_ANNOTATION(1),SCAN_ONLY_FATHER_CLASS(2),SCAN_ALL(3);
    public final int model;
    private ScanTypeEnum(int model) {
        this.model = model;
    }

}
