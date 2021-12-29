package com.laishishui.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <h1>用户表实体类表定义</h1>
 * Create by tachai on 2021/12/29 8:29 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_ecommerce_user")
public class EcommerceUser  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name= "username",nullable = false)
    private String username;

    /** MD5 密码 */
    @Column(name= "password", nullable = false)
    private String password;

    /** 额外的信息，json */
    @Column(name= "extra_info", nullable = false)
    private String extraInfo;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;


    @LastModifiedDate
    @Column(name= "update_time",nullable = false)
    private Date updateTime;
}
