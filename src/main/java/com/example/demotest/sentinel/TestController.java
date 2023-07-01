package com.example.demotest.sentinel;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TestController {

//    @GetMapping("/testSentinelResource")
//    @SentinelResource(value = "getOrderInfo",blockHandler = "blockHandlerFunc",fallback = "fallbackFunc")
//    public String testSentinelResource(@RequestParam(required = false) String orderId){
//        // 模拟接口运行时抛出代码异常
//        if ("000".equals(orderId)) {
//            throw new RuntimeException();
//        }
//
//        System.out.println("获取订单信息:" + orderId);
//        return "return OrderInfo :" + orderId;
//    }

    /**
     * 订单查询接口抛出限流或降级时的处理逻辑
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
//    public String blockHandlerFunc(String orderId, BlockException e) {
//        e.printStackTrace();
//        return "handleFlowQpsException for getOrderInfo: " + orderId;
//    }

    /**
     * 订单查询接口运行时抛出的异常提供fallback处理
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public String fallbackFunc(String orderId, Throwable e) {
        return "fallback queryOrderInfo2: " + orderId;
    }

}
