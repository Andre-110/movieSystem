$(function () {
    var form = layui.form;

    // 校验规则
    form.verify({
        pwd: [
            /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
        ],
        samePwd: function (value) {
            if (value === $('#oldPwd').val()) {
                return '新密码不能和旧密码相同！';
            }
        },
        rePwd: function (value) {
            if (value !== $('#newPwd').val()) {
                return '两次密码不一致！';
            }
        }
    })

    $('.layui-form').on('submit', function (e) {
        e.preventDefault();
        $.ajax({
            method: 'POST',
            // 如果这里调用了baseAPI文件，接口就不要写全，但是baseAPI文件中的URL地址也要记得更换
            url: 'user/changePassword',
            data: $(this).serialize(),
            success: function (res) {
                if (res.status !== 0) {
                    return layui.layer.msg('更新密码失败!');
                } else {
                    layui.layer.msg('更新密码成功!');
                    $('.layui-form')[0].reset();
                }
            }
        })
    })
})