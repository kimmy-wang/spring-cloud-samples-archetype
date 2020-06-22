#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 *
 * MIT License
 *
 * Copyright (c) 2020 cloud.upcwangying.com
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package ${package}.oss.server.dao;

import ${package}.oss.common.BucketModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * @author WANGY
 */
@Mapper
public interface BucketMapper {
    void addBucket(@Param("bucket") BucketModel bucketModel);

    int updateBucket(@Param("bucketName") String bucketName, @Param("detail") String detail);

    int deleteBucket(@Param("bucketName") String bucketName);

    @ResultMap("BucketResultMap")
    BucketModel getBucket(@Param("bucketId") String bucketId);

    @ResultMap("BucketResultMap")
    BucketModel getBucketByName(@Param("bucketName") String bucketName);

    @ResultMap("BucketResultMap")
    List<BucketModel> getBucketByCreator(@Param("creator") String creator);

    @ResultMap("BucketResultMap")
    List<BucketModel> getUserAuthorizedBuckets(@Param("token") String token);
}
