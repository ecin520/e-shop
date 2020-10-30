package com.pytap.order.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.pytap.api.model.vo.MemberVO;
import com.pytap.api.service.api.urp.MemberFeignService;
import com.pytap.common.annotation.Log;
import com.pytap.common.constant.RedisKey;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.generator.entity.EsCartProduct;
import com.pytap.generator.entity.EsMember;
import com.pytap.order.controller.BaseController;
import com.pytap.order.service.CartProductService;
import com.pytap.order.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/27 14:47
 */
@RequestMapping("/web/cart")
@RestController
public class CartProductWebController extends BaseController {

    @Resource
    private CartProductService cartProductService;

    @Log(value = "添加商品到购物车")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertCartProduct(@RequestBody EsCartProduct cartProduct) throws GeneralException {
        cartProduct.setMemberId(getMemberId());
        return 1 != cartProductService.insertCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "删除购物车商品")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResultEntity<Object> deleteCartProduct(@RequestBody EsCartProduct cartProduct) throws GeneralException {
        cartProduct.setMemberId(getMemberId());
        return 1 != cartProductService.deleteCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新购物车商品")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultEntity<Object> updateCartProduct(@RequestBody EsCartProduct cartProduct) throws GeneralException {
        cartProduct.setMemberId(getMemberId());
        return 1 != cartProductService.updateCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取购物车商品")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<List<EsCartProduct>> listCartProducts(@RequestBody EsCartProduct cartProduct) throws GeneralException {
        cartProduct.setMemberId(getMemberId());
        return ResultEntity.success(cartProductService.listCartProducts(cartProduct));
    }

    private Long getMemberId() throws GeneralException {
        EsMember member = getMember();
        if (null == member) {
            throw new GeneralException("用户不存在");
        }
        return member.getId();
    }

}
