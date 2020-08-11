package org.tyrande.jobs.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * bean模式的调度任务执行类
 *
 * @author Tyrande
 */
@Component
@Slf4j
public class TyrandeJobService {

    /**
     * 测试任务1
     */
    @XxlJob("tyrande-job-1")
    public ReturnT<String> tyrandeJob1(String param) throws Exception {
        log.info("[泰兰德系统-定时任务1开始执行...]");
        log.info("[输入参数] {}", param);
        for (int i = 0; i < 5; i++) {
            log.info("任务1-操作步骤: {}", i);
        }
        log.info("[泰兰德系统-定时任务1执行成功...]");
        return ReturnT.SUCCESS;
    }

    /**
     * 测试任务1
     */
    @XxlJob("tyrande-job-2")
    public ReturnT<String> tyrandeJob2(String param) throws Exception {
        log.info("[泰兰德系统-定时任务2开始执行...]");
        log.info("[输入参数] {}", param);
        for (int i = 0; i < 5; i++) {
            log.info("任务2-操作步骤: {}", i);
        }
        log.info("[泰兰德系统-定时任务2执行成功...]");
        return ReturnT.SUCCESS;
    }
}
