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

package ${package}.user.web.utils;

import ${package}.user.web.entity.Permission;
import ${package}.user.web.entity.Role;
import ${package}.user.web.entity.RolePermission;
import ${package}.user.web.entity.User;
import ${package}.user.web.entity.UserPermission;

import static ${package}.core.constant.Constants.UNDELETED_STATUS;
import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 * Created by WANGY
 *
 * @author WANGY
 */
public final class BeanCreators extends ${package}.user.common.utils.BeanCreators {

    public static User createInitUser() {
        User user = new User();
        user.setUserId(EMPTY);
        user.setDelFlag(UNDELETED_STATUS);
        return user;
    }

    public static Permission createInitPermission() {
        Permission permission = new Permission();
        permission.setPermissionId(EMPTY);
        permission.setDelFlag(UNDELETED_STATUS);
        return permission;
    }

    public static Role createInitRole() {
        Role role = new Role();
        role.setRoleId(EMPTY);
        role.setDelFlag(UNDELETED_STATUS);
        return role;
    }

    public static RolePermission createInitRolePermission() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionId(EMPTY);
        rolePermission.setDelFlag(UNDELETED_STATUS);
        return rolePermission;
    }

    public static UserPermission createInitUserPermission() {
        UserPermission userPermission = new UserPermission();
        userPermission.setUserPermissionId(EMPTY);
        userPermission.setDelFlag(UNDELETED_STATUS);
        return userPermission;
    }
}
