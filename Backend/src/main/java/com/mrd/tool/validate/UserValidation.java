package com.mrd.tool.validate;

import com.mrd.tool.common.ultils.StringUtil;
import com.mrd.tool.entity.User;

import javax.validation.ValidationException;

public class UserValidation extends BaseValidation{
    public String validateLogin(String username, String password) throws ValidationException {

        if (StringUtil.isNullOrEmpty(username)) {
            getMessageDes().add("Tài khoản không được để trống");
        }

        if (StringUtil.isNullOrEmpty(password)) {
            getMessageDes().add("Mật khẩu không được để trống");
        }

        return !isValid() ? this.buildValidationMessage() : null;
    }

    public String validateInsertUser(User user) {
        if (user == null) {
            return "payLoad không hợp lệ";
        } else if (StringUtil.isNullOrEmpty(user.getUserName())) {
            getMessageDes().add("Tên tài khoản không được để trống ");
        } else if (StringUtil.isNullOrEmpty(user.getEmail()) && StringUtil.isNullOrEmpty(user.getMobile())) {
            getMessageDes().add("email hoặc mobile không được để trống ");
        } else if (!StringUtil.isNullOrEmpty(user.getEmail()) && !StringUtil.validateEmail(user.getEmail())) {
            getMessageDes().add("email không đúng định dạng \n");
        } else if (!StringUtil.isNullOrEmpty(user.getMobile()) && !StringUtil.checkMobilePhoneNumberNew(user.getMobile())) {
            getMessageDes().add("mobile không đúng định dạng ");
        } else if (StringUtil.isNullOrEmpty(user.getFullName())) {
            getMessageDes().add("fullName không được để trống ");
        } else if (user.getPassword().length() < 6 || user.getPassword().length() > 12) {
            getMessageDes().add("Độ dài mật khẩu phải lớn hơn 6 và nhỏ hơn 12 ");
        } else if (!StringUtil.isNullOrEmpty(user.getFullName()) && user.getFullName().length() > 255) {
            getMessageDes().add("fullName chiều dài tối đa 255");
        }
        return !isValid() ? this.buildValidationMessage() : null;
    }
}

