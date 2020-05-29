#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 *
 * MIT License
 *
 * Copyright (c) 2019 cloud.upcwangying.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package ${package}.product.server.controller;

import ${package}.core.common.ValidList;
import ${package}.core.constant.Constants;
import ${package}.core.enums.ResultEnum;
import ${package}.core.utils.ResultVOUtils;
import ${package}.core.vo.ResultVO;
import ${package}.product.common.entity.ProductInput;
import ${package}.product.common.entity.ProductOutput;
import ${package}.product.server.entity.Product;
import ${package}.product.server.service.ProductService;
import ${package}.product.server.utils.BeanCreators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品服务Rest Api
 *
 * @author WANGY
 */
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有商品信息
     * @return
     */
    @GetMapping
    public ResultVO getAllProduct() {
        List<ProductOutput> productOutputList = productService.getAllProduct().stream().map(product -> {
            ProductOutput productOutput = BeanCreators.createProductOutput();
            BeanUtils.copyProperties(product, productOutput);
            return productOutput;
        }).collect(Collectors.toList());
        return ResultVOUtils.success(productOutputList);
    }

    /**
     * 创建商品
     * @param productInput
     * @return
     */
    @PostMapping
    public ResultVO createProduct(@Validated @RequestBody ProductInput productInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        Product product = BeanCreators.createInitProduct();
        BeanUtils.copyProperties(productInput, product);
        return ResultVOUtils.success(productService.saveOne(product));
    }

    /**
     * 查询指定商品明细信息
     * @param id 商品Id
     * @return
     */
    @GetMapping("/{id:${symbol_escape}${symbol_escape}w{8}(?:-${symbol_escape}${symbol_escape}w{4}){3}-${symbol_escape}${symbol_escape}w{12}}")
    public ResultVO getProductByProductId(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(product, productOutput);
        return ResultVOUtils.success(productOutput);
    }

    /**
     * 查询指定商品明细信息
     * @param productIds 商品Id列表
     * @return
     */
    @GetMapping("/list/all")
    public ResultVO getProductListByProductIds(@RequestParam("productIds") List<String> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            log.error("ProductController: getProductListByProductIds(productIds={})", productIds);
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR);
        }
        Map<String, ProductOutput> productOutputMap = productService.getProductById(productIds).stream()
                .map(product -> {
                    ProductOutput output = BeanCreators.createProductOutput();
                    BeanUtils.copyProperties(product, output);
                    return output;
                })
                .collect(Collectors.toMap(ProductOutput::getProductId, productOutput -> productOutput));
        return ResultVOUtils.success(productOutputMap);
    }

    /**
     * 更新某个商品全部信息
     * @param id 商品Id
     * @param productInput
     * @return
     */
    @PutMapping("/{id:${symbol_escape}${symbol_escape}w{8}(?:-${symbol_escape}${symbol_escape}w{4}){3}-${symbol_escape}${symbol_escape}w{12}}")
    public ResultVO updateProduct(@PathVariable("id") String id, @Validated @RequestBody ProductInput productInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }

        BeanUtils.copyProperties(productInput, product);

        Product resultProduct = productService.saveOne(product);
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(resultProduct, productOutput);

        return ResultVOUtils.success(productOutput);
    }

    /**
     * 更新部分商品库存信息
     * @param productInputList
     * @return
     */
    @PutMapping("/save/all")
    public ResultVO updateProductList(@Validated @RequestBody ValidList<ProductInput> productInputList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        List<String> productIds = productInputList.stream()
                .map(ProductInput::getProductId).collect(Collectors.toList());

        Map<String, ProductInput> productInputMap = productInputList.stream()
                .collect(Collectors.toMap(ProductInput::getProductId, productInput -> productInput));

        List<Product> productList = productService.getProductById(productIds).stream()
                .map(product -> {
                    product.setStock(productInputMap.get(product.getProductId()).getStock());
                    return product;
                }).collect(Collectors.toList());
        productService.saveAll(productList);
        return ResultVOUtils.success();
    }

    /**
     * 删除指定商品
     * @param id 商品Id
     * @return
     */
    @DeleteMapping("/{id:${symbol_escape}${symbol_escape}w{8}(?:-${symbol_escape}${symbol_escape}w{4}){3}-${symbol_escape}${symbol_escape}w{12}}")
    public ResultVO deleteProduct(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }

        product.setDelFlag(Constants.DELETED_STATUS);

        Product resultProduct = productService.saveOne(product);
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(resultProduct, productOutput);

        return ResultVOUtils.success(productOutput);
    }

}
