package com.pytap.product.service.impl;

import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.dao.EsSkuSpecDetailMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.model.dto.ProductSpecDTO;
import com.pytap.product.service.ProductCategorySpecService;
import com.pytap.product.service.ProductService;
import com.pytap.product.service.SkuProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ecin520
 * @date 2020/9/21 11:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategorySpecServiceImplTest {

    @Resource
    private ProductCategorySpecService productCategorySpecService;

    @Resource
    private ProductService productService;

    @Resource
    private EsSkuProductMapper skuProductMapper;

    @Resource
    private EsSkuSpecDetailMapper skuSpecDetailMapper;

    @Test
    void listSpecDTOsByCategoryAndProductId() {
        List<ProductSpecDTO> list = productCategorySpecService.listSpecDTOsByCategoryAndProductId(1001L, 1020L);
        for (ProductSpecDTO dto : list) {
            System.out.println(dto);
            List<EsProductSpecDetail> details = dto.getDetails();
            for (EsProductSpecDetail detail : details) {
                System.out.println(detail);
            }
            System.out.println();
        }
    }

    @Test
    void listSpecDetailsByProductId() {
        List<EsProductSpecDetail> list = productCategorySpecService.listSpecDetailsByProductId(1020L);
        for (EsProductSpecDetail productSpecDetail : list) {
            System.out.println(productSpecDetail);
        }
    }

    @Test
    void test() {

        Long before = System.currentTimeMillis();

        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(1020L);
        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);

        for (EsSkuProduct skuProduct : list) {
            EsSkuSpecDetailExample example1 = new EsSkuSpecDetailExample();
            EsSkuSpecDetailExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSkuIdEqualTo(skuProduct.getId());
            List<EsSkuSpecDetail> list1 = skuSpecDetailMapper.selectByExample(example1);

            StringBuilder specs = new StringBuilder();

            for (EsSkuSpecDetail esSkuSpecDetail : list1) {
                specs.append(esSkuSpecDetail.getSpecDetailId()).append("-");
            }
            System.out.println(skuProduct);
            System.out.println(specs);

        }

        Long after = System.currentTimeMillis();

        System.out.println(after - before);

    }
}