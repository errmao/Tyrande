package org.tyrande.system.service.sysdictkey;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyrande.system.model.sysdictkey.SysDictKeyModel;
import org.tyrande.system.vo.sysdictkey.SysDictKeyVo;
import java.util.List;

/**
 * 系统字典项表 service 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
public interface SysDictKeyService extends IService<SysDictKeyModel> {

    /**
     * 分页查询
     */
    IPage<SysDictKeyModel> getPageList(SysDictKeyVo sysDictKeyVo);

    /**
     * 根据字典项编号删除字典值
     */
    void deleteDictValue(String id);

    /**
     * 根据字典项查询是否重复
     */
    SysDictKeyModel checkDictKey(String dictKey);
}