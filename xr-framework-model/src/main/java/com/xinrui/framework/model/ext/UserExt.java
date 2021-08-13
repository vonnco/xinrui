package com.xinrui.framework.model.ext;

import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.Role;
import com.xinrui.framework.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class UserExt extends User implements Serializable {
    List<Role> roleList;
    List<Menu> menuList;
}
