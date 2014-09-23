package com.edwise.pocs.swagger.entities;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.LocalDate;

@ApiModel(value = "User entity", description = "Complete info of a entity user")
public class User {

    @ApiModelProperty(value = "The id of the user")
    private Long id;

    @ApiModelProperty(value = "The name of the user", required = true)
    private String name;

    @ApiModelProperty(value = "The type of the user", required = true)
    private Integer type;

    @ApiModelProperty(value = "The phone of the user", required = false)
    private String phone;

    @ApiModelProperty(value = "The birthDay of the user", required = false)
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public User setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public User copyFrom(User user) {
        if (user.name != null) {
            this.name = user.name;
        }
        if (user.type != null) {
            this.type = user.type;
        }
        if (user.phone != null) {
            this.phone = user.phone;
        }
        if (user.birthDate != null) {
            this.birthDate = user.birthDate;
        }
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;

        return !(birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) &&
                !(id != null ? !id.equals(user.id) : user.id != null) &&
                !(name != null ? !name.equals(user.name) : user.name != null) &&
                !(phone != null ? !phone.equals(user.phone) : user.phone != null) &&
                !(type != null ? !type.equals(user.type) : user.type != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
