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

package ${package}.mdm.common.validation;


import ${package}.mdm.common.validation.annotation.UserPermissionInputAnno;
import ${package}.mdm.common.entity.UserPermissionInput;
import ${package}.mdm.common.enums.UserPermissionType;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by WANGY
 *
 * @author WANGY
 */
public class UserPermissionInputValidator implements ConstraintValidator<UserPermissionInputAnno, UserPermissionInput> {

    @Override
    public void initialize(UserPermissionInputAnno constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserPermissionInput value, ConstraintValidatorContext context) {
        String type = value.getType();
        String roleId = value.getRoleId();
        String permissionId = value.getPermissionId();

        if (StringUtils.isEmpty(type) || !UserPermissionType.isValid(type)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{${package}.user.common.validation.annotation.UserPermissionInput.type.message}").addConstraintViolation();
            return false;
        }

        // 如果type为role, 且roleId为空
        if (UserPermissionType.role.name().equals(type) && StringUtils.isEmpty(roleId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{${package}.user.common.validation.annotation.UserPermissionInput.role.message}").addConstraintViolation();
            return false;
        }

        // 如果type为permission, 且permissionId为空
        if (UserPermissionType.permission.name().equals(type) && StringUtils.isEmpty(permissionId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{${package}.user.common.validation.annotation.UserPermissionInput.permission.message}").addConstraintViolation();
            return false;
        }

        return true;

    }

}
