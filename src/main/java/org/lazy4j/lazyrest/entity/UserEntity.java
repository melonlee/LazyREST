package org.lazy4j.lazyrest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
/**
 * @Author：Melon
 * @Date：2017/10/19
 * @Time：下午10:33
 */
@Data
//@Builder
//@Accessors(chain = true)
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;


    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 20, message = "用户名长度为5-20之间")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "用户名不合法")
    private String username;

    @JsonIgnore
    private String password;

    private String createdate;

    @JsonIgnore
    private String salt;

}
