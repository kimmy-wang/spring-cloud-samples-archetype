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

package ${package}.oss.server.service;

import ${package}.oss.common.ObjectListResult;
import ${package}.oss.common.OosObject;
import ${package}.oss.common.OosObjectSummary;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * @author WANGY
 */
public interface IOosStoreService {
    void createSeqTable() throws IOException;

    void put(String bucket, String key, ByteBuffer content, long length, String mediaType,
             Map<String, String> properties) throws Exception;

    OosObjectSummary getSummary(String bucket, String key) throws IOException;

    List<OosObjectSummary> list(String bucket, String startKey, String endKey)
            throws IOException;

    ObjectListResult listDir(String bucket, String dir,
                             String start, int maxCount) throws IOException;

    ObjectListResult listByPrefix(String bucket, String dir, String keyPrefix, String start,
                                  int maxCount) throws IOException;

    OosObject getObject(String bucket, String key) throws IOException;

    void deleteObject(String bucket, String key) throws Exception;

    void deleteBucketStore(String bucket) throws IOException;

    void createBucketStore(String bucket) throws IOException;
}
