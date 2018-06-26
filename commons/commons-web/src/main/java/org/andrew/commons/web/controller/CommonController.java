package org.andrew.commons.web.controller;



import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装管理端公共方法。
 * Created by andrewliu on 2018/3/22.
 */
public class CommonController extends ShiroBaseController {

    /**
     * 修改统一结果出口。
     *
     * @param flag 成功标识
     * @return 结果
     */
    public JSONObject returnResult(boolean flag, String resultFlag,
                                   String successMsg, String failMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate(resultFlag, flag);
        if (flag) {
            jsonObject.put("msg", successMsg);
        } else {
            jsonObject.put("msg", failMsg);
        }
        return jsonObject;
    }

    /**
     * 组装批量删除参数。
     *
     * @param ids 主键集合
     * @return 封装参数
     */
    public Map<String, Object> getDeleteIds(String ids) {
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(ids.split(",")));
        Map map = new HashMap();
        map.put("list", list);
        return map;
    }

}
