package com.svetakvetko.dto;

public class EditPasswordDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
    private long userId;

    public long getUserId() {
        return userId;
    }

    public EditPasswordDto(Long userId) {
        this.userId = userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }


}
