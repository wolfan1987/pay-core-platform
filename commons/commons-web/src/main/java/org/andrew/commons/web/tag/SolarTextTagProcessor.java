package org.andrew.commons.web.tag;




import java.util.Calendar;
import java.util.List;


/**
 * 数据字典自定义标签--text显示。
 * Created by andrewliu on 2017/6/5.
 */
public final class SolarTextTagProcessor{
        //extends AbstractStandardExpressionAttributeTagProcessor {

//    private ItemDao itemDao;
//
//    private RedisTemplate redisTemplate;
//
//    public static final int PRECEDENCE   = 1300;
//    public static final String ATTR_NAME = "text";
//
//    /**
//     * 构造。
//     */
//    public SolarTextTagProcessor(
//        final String dialectPrefix, RedisTemplate redisTemplate, ItemDao itemDao) {
//        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, true);
//        this.redisTemplate = redisTemplate;
//        this.itemDao = itemDao;
//    }
//
//
//    @Override
//    protected void doProcess(
//        final ITemplateContext context, final IProcessableElementTag tag,
//        final AttributeName attributeName, final String attributeValue,
//        final Object expressionResult, final IElementTagStructureHandler structureHandler) {
//
//        final TemplateMode templateMode = getTemplateMode();
//
//        final CharSequence text;
//        text = TagUtil.getCharSequence(context, expressionResult, templateMode);
//
//        //转换数据字典
//        String type = ThymeleafFacade.getRawValue(tag, "type");//类型
//        String textValue = "";
//        if (type != null && !"".equals(type)) {
//            textValue = getName(type, text.toString());
//        }
//        // Report the result to the engine, whichever the type of process we have applied
//        structureHandler.setBody(textValue, false);
//
//    }
//
//
//
//    /**
//     * 获取数据字典的名称。
//     *
//     * @param type 类型
//     * @param code 代码
//     * @return 名称
//     */
//    public String getName(String type, String code) {
//        String prf = "CATEGORY_";
//        String name = "";
//        ValueOperations<String, ItemVo> valueOps = redisTemplate.opsForValue();
//        ItemVo itemDto = valueOps.get(prf + type);
//        if (itemDto == null) {
//            List<ItemVo> list = itemDao.findByCategory(type);
//            if (list != null && list.size() == 1) {
//                itemDto = list.get(0);
//                valueOps.set(prf + type, itemDto);
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.DATE, 1);
//                calendar.set(Calendar.HOUR_OF_DAY, 0);//24小时制
//                calendar.set(Calendar.MINUTE, 0);
//                calendar.set(Calendar.SECOND, 0);
//                //第二天的00:00:00过期
//                redisTemplate.expireAt(prf + type, calendar.getTime());
//            } else {
//                return "";
//            }
//        }
//        List<ItemDetail> itemDetails = itemDto.getItemDetails();
//        if (itemDetails != null && itemDetails.size() > 0) {
//            for (ItemDetail bean : itemDetails) {
//                if (code.equals(bean.getCode())) {
//                    name = bean.getName();
//                    break;
//                }
//            }
//        }
//        return name;
//    }
}
