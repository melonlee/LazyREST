package lazyrest.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Melon on 17/2/15.
 */
@TableName("t_user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    @ApiModelProperty("id")
    private String id;


    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 20, message = "用户名长度为5-20之间")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "用户名不合法")
    @ApiModelProperty("用户名")
    private String username;


    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度为5-20之间")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("密码盐值")
    private String salt;

    @ApiModelProperty("注册时间")
    private String createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

}
