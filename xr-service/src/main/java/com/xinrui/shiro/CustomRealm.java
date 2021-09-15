package com.xinrui.shiro;

/**
 * 自定义realm，需要继承AuthorizingRealm父类
 *     重写父类中的两个方法
 *         doGetAuthorizationInfo     ：授权
 *         doGetAuthenticationInfo     ：认证
 *//*
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    *//**
     * 授权：授权的主要目的就是查询数据库获取用户的所有角色和权限信息
     *//*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从principals获取已认证用户的信息
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户信息
        UserExt userExt = userService.findUserByUsername(username);
        // 获取角色
        List<Role> roleList = userExt.getRoleList();
        List<String> roles = roleList.stream().map(Role::getRoleCode).collect(Collectors.toList());
        //获取权限
        List<Menu> menuList = userExt.getMenuList();
        List<String> permissions = menuList.stream().map(Menu::getMenuCode).collect(Collectors.toList());
        // 构造权限数据
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将查询的角色数据保存到simpleAuthorizationInfo
        simpleAuthorizationInfo.addRoles(roles);
        // 将查询的权限数据保存到simpleAuthorizationInfo
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    *//**
     * 认证：认证的主要目的，比较用户输入的用户名密码是否和数据库中的一致
     *//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录的upToken
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //2.获取输入的用户名密码
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //3.验证用户名密码是否正确
        UserExt userExt = userService.findUserByUsername(username);
        if(!passwordEncoder.matches(password,userExt.getPassword())) {
            return null;//抛出异常表示认证失败
        }else{
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, this.getName());
            return info;
        }
    }
}*/
