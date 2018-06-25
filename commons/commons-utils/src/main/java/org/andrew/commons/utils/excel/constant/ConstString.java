package org.andrew.commons.utils.excel.constant;

/**
 * ExcelConstant。
 * Created by maqiang on 2017/8/28.
 */
public interface ConstString {

    /**
     * Excel 2003。
     */
    String EXCEL_SUFFIX_XLS = "xls";

    /**
     * Excel 2007。
     */
    String EXCEL_SUFFIX_XLSX = "xlsx";

    String EXCEL_NO_XLSORXLSX = "暂只支持xls/xlsx文件";

    String SELECT_TEMPLATE = "请选择模板文件";

    String IS_NULL_FILE = "空文件，请下载并使用模板文件";

    /**
     * 员工福利。
     */

    String NO_CONTENT = "无信息，请添加员工福利信息";

    String EMP_NO_CONNOT_NULL = "员工编号不可为空";

    String EMP_NO_CONNOT_REPEAT = "员工编号不可重复";

    String EMP_NAME_CONNOT_NULL = "员工姓名不可为空";

    String PHONE_CONNOT_NULL = "手机号码不可为空";

    String PHONE_CONNOT_REPEAT = "手机号码不可重复";

    String AMT_CONNOT_NULL = "充值金额不可为空";

}
