package com.pytap.order.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.order.controller.BaseController;
import com.pytap.order.model.dto.OrderParamDTO;
import com.pytap.order.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ecin520
 * @date 2020/10/27 15:03
 */
@RequestMapping("/web/order")
@RestController
public class OrderWebController extends BaseController {

    @Resource
    private OrderService orderService;

    @Log(value = "生成订单")
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResultEntity<Object> generateOrder(@RequestBody OrderParamDTO orderParamDTO) throws GeneralException {
        orderParamDTO.setMemberId(getMember().getId());
        return 1 != orderService.insertOrderByParam(orderParamDTO) ? ResultEntity.fail() : ResultEntity.success();
    }

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public String pay(String orderNo, String price) throws UnsupportedEncodingException {

        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016103000779036",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDXU7dlNGxKjBhi8zS1+Ai28fk7GvXkVVoVfhHvx8daszmS3e2h2HDnmQQu6H/4wEYxvVQaSee/TdGr8cRNDFyfPJL/W2+iIiwtVl1BI5UtSRyR2q+ijAfSbd14DTXqFkOKsYguQfnH4XLjoPjdyqTplh2i7ysNicbyv4Xii90/edBTvMP1Y1FKiT8aqMxx2lpWfG5oImt9Vm9A1FOmDbq+xyb7yupvSKdhUSlYjdlfnBWKTjYRTWFfPMAzKC82nWoVQ+OmA+sJzxbQzjHVp/tMB0Ega58wwwesrXMKJQwqJzf8KiNtcIkLpa0+mgCc+pVtRP1eLQkMg4TUbSP3AN8TAgMBAAECggEAU25Dg/og722C0Wwsg0k8jy4IhIc4uST9B5DWgFKnApSo/SfyOCB19raKMWaylv5g9ErIeHskSTJsyDOf/L6KcMR2rGyQP4EAHvz0mxWfanGGmVjjbpbVPQqGokp0FmRT/Tw2aL31eyX2bw5i/wTSixM4HnaO9uroZAwjYxkQO9RP14AVAXt3BV4GtN4DvEeY9t6v1lbqvLsBeg9XjSDiUC5LBtUgMsbiLfrevTPAE9QumXEi5J8engXYVa+hGyCGEOUwelX8wH/hjvxF1gVOzCq6jo0Xv7IaXco5zst0N43V6rucOkzDFvamW2qfbdFPjjiL5UsJ7NVTc2KdxkFJ8QKBgQD31/14KlGeij7TM31kQVFM36rAj6OBWiupzcNcTuAC5UezQ/Vrz2Wl5a+VZXKXMX3FewDj/gmZ6aLpbHdd26/rFiGdW0dyVm+4oNg/NRlYYr0E8607iuInMeH1YurM+phg24M44UAvPxNkn7c9BtA7mLb6FPibP7tfZ3s0fqKeGQKBgQDeachibj5sxFU6Z2EI2xS/7rtun8y+Pvl4d2ILsL0IVvyKmhT1HRaaez9gbq5I2Sipgb1xogJEW52/Ov3P/I7wh8vfSKUH9ELJRksB4KdtvJAkobFe+Oa2pHdGHSsnmE3uR3C9D4qEFXF5pXq0Qhu9MDmWyk80Bic43Zr4e/80CwKBgQCvkTC74/aHgVT51dq4n1CRTUwvNlFT+2U810bR6Anl4zeEdNqwsW1HNMrJ4OBo3CVFNFowJ6/zeL7v7ecs9MfMl5PeXV/iTfq8ZixpshqvMtrFXDzWFg8YRszTQGwmIkq5KibtrxsBnQQG3uE1+NKftRHBXAO0LlR0Q+DSEfbuWQKBgQCoOPhgQqQinkkcWI7TJOlH1mib3m/8wSLtk9Zroy0G/swahTouvuaWx3uDE5Ps85tGwiOoq/HtBCKHPjY1NwBiO0PfSVHRd1WAaRNYgzP/0yxvGIFQpQ/Tuwef13YJh7AB9rT1uqh5CMMAUEJDzvCuIqicPb60Sacm4q5RHXBESwKBgH9MdH67yDwP3owNLcJgwwv4VPHE/hPxBOnEq0/31y2TQj4aFnTGuuLMMtb+iQjuXYG4lzjls//yJmV/g7OQ7ax8/W1KliX3yYWccarFE7ssrSg1mdCS25DoK1d1PunED3lRWgH3KC+v/HINOwj7Q/aSBxdaUYJspyRw1dD13g0/",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7g70BC8IUA7acsUaxzO28D4nNQBMdzSY41v4rcOVl4eVIzSFP0KdBRGs18Dq1if3wjHGj0TOlp1F1gk4W0B649dF6ApOi0RTM6AfC6JIaGXuoH6JvQ0udK1qE6qhR4ElLQJC4xh0Sl4XClLmZ1JbXki+iS4JFlO+KNi1LXUlVS9EXgi9xnI+g1y9EbOEZL83dgcdvSpyh5RZlZndaamuu1XtQLMRBzeTVJT6yYBXW3kXpRVLYQkHvR+ES42acIcJPwyq4yKTTazstDeCgoqGIZotCutqfalBG5pnDdulnt8UuLXDgbqjtZdbw3EKrwwkE7kuxBmdr5AKt/FoO8uDMQIDAQAB",
                "RSA2"
        );

        String out_trade_no = orderNo;
        out_trade_no = URLDecoder.decode(out_trade_no, "UTF-8");
        String total_amount = price;
        total_amount = URLDecoder.decode(total_amount, "UTF-8");
        String subject = "iPhone12pro";
        subject = URLDecoder.decode(subject, "UTF-8");
        String body = "description";
        body = URLDecoder.decode(body, "UTF-8");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //同步通知
        alipayRequest.setReturnUrl("http://127.0.0.1:10010/api/order/web/order/alipay/callback/return");
        //异步通知
        alipayRequest.setNotifyUrl("/web/order/notify");

        //配置参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + total_amount + "," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"body\":\"" + body + "\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //form就是一个表单 html 直接给前端 替换 body标签里面的东西
        return form;

    }


    @Log(value = "支付成功回调方法")
    @RequestMapping("/alipay/callback/return")
    public String AlipayReturn(HttpServletRequest request) {
        String trade_no = request.getParameter("trade_no");
        Map<String,Object> receiveMap = new HashMap<String,Object>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = String.valueOf(enu.nextElement());
            String value = request.getParameter(key);
            receiveMap.put(key, value);
        }
        System.out.println("支付宝交易号是:" + trade_no);

        receiveMap.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        return trade_no;
    }

}
