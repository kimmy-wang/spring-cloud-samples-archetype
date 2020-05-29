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

package ${package}.product.server.service;

import ${package}.product.server.entity.Product;

import java.util.List;

/**
 * 商品服务业务层
 *
 * @author WANGY
 */
public interface ProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> getAllProduct();

    /**
     * 查询商品明细
     * @return
     */
    Product getProductById(String id);

    /**
     * 查询商品明细
     * @return
     */
    List<Product> getProductById(List<String> productIdList);

    /**
     * 新增、更新、删除商品
     * @return
     */
    Product saveOne(Product product);

    /**
     * 批量新增、更新、删除商品
     * @return
     */
    List<Product> saveAll(List<Product> productList);
}
