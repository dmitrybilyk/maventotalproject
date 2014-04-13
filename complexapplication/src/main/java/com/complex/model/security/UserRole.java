package com.complex.model.security;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ROLE_ID")
    private int userRoleId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private List<User> users;

    private String authority;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
