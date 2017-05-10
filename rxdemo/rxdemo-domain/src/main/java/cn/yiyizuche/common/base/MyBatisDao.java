package cn.yiyizuche.common.base;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Mybatis分页查询工具类,为分页查询增加传递: startRow,endRow : 用于oracle分页使用,从1开始 offset,limit :
 * 用于mysql 分页使用,从0开始
 * 
 * @author badqiu
 * 
 */
public class MyBatisDao extends SqlSessionDaoSupport {
    
    /**
     * getSession
     */
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    
    /**
     * 
    * @Title: selectPage
    * @Description: TODO(分页方法)
    * @param @param page 分页对象
    * @param @param statementName 集合名称
    * @param @param countStatementName 集合数量
    * @param @param parameter 参数
    * @param @return    设定文件
    * @return Page<T>    返回类型
    * @throws
     */
    public <T> Page<T> selectPage(Page<T> page, String statementName, String countStatementName, Map<String, Object> parameter) {
        
        Number totalItems = (Number)getSqlSession().selectOne(countStatementName, parameter);
        
        if (totalItems != null && totalItems.longValue() > 0) {
            List<T> list = getSqlSession().selectList(statementName, toParameterMap(parameter, page));
            page.setResult(list);
            page.setTotalItems(totalItems.longValue());
        }
        return page;
    }
    
    /**
     * 
    * @Title: toParameterMap
    * @Description: TODO(参数处理)
    * @param @param parameter
    * @param @param p
    * @param @return    设定文件
    * @return Map<String,Object>    返回类型
    * @throws
     */
    protected static Map<String, Object> toParameterMap(Map<String, Object> parameter, Page<?> p) {
        Map<String, Object> map = toParameterMap(parameter);
        map.put("startRow", p.getStartRow());
        map.put("endRow", p.getEndRow());
        map.put("offset", p.getOffset());
        map.put("limit", p.getPageSize());
        return map;
    }
    
    /**
     * 
    * @Title: toParameterMap
    * @Description: TODO(map叙述)
    * @param @param parameter
    * @param @return    设定文件
    * @return Map<String,Object>    返回类型
    * @throws
     */
    protected static Map<String, Object> toParameterMap(Map<String, Object> parameter) {
        if (parameter instanceof Map) {
            return (Map<String, Object>)parameter;
        }
        else {
            try {
                return PropertyUtils.describe(parameter);
            }
            catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
                return null;
            }
        }
    }
}
