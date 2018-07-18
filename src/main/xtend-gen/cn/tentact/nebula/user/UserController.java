package cn.tentact.nebula.user;

import cn.tentact.nebula.email.I_EmailService;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.user.I_UserController;
import cn.tentact.nebula.user.I_UserService;
import cn.tentact.nebula.web.ResponseBean;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块网络类
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController implements I_UserController {
  @Qualifier("redisTemplate")
  @Autowired
  private RedisTemplate redis;
  
  @Autowired
  private I_UserService userService;
  
  @Autowired
  private I_EmailService emailService;
  
  @RequestMapping("/login")
  @Override
  public ResponseBean login(final String username, final String password) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    boolean bool = this.userService.login(Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1)));
    if (bool) {
      String token = JwtUtil.sign(username, password);
      Pair<String, String> _mappedTo_2 = Pair.<String, String>of("token", token);
      Pair<String, String> _mappedTo_3 = Pair.<String, String>of("password", password);
      this.redis.<Object, Object>opsForHash().putAll(username, Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo_2, _mappedTo_3)));
      this.redis.expire(username, JwtUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
      Pair<String, String> _mappedTo_4 = Pair.<String, String>of("token", token);
      return new ResponseBean(200, "登录成功", bool, Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo_4)));
    } else {
      return new ResponseBean(200, "登录失败", bool, null);
    }
  }
  
  @RequestMapping("/searchSummary")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchSummary(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    Map map = this.userService.searchSummary(username);
    return new ResponseBean(200, "查询成功", true, map);
  }
  
  @RequestMapping("/register")
  @Override
  public ResponseBean register(final String username, final String password) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    HashMap<String, String> map = CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1);
    int i = this.userService.register(map);
    return new ResponseBean(200, "注册结果", true, Integer.valueOf(i));
  }
  
  @RequestMapping("/forgetPassword")
  @Override
  public ResponseBean forgetPassword(final String key) {
    Map map = this.userService.forgetPassword(key);
    if ((Objects.equal(map.get("email"), null) || Objects.equal(map.get("password"), null))) {
      Object _get = map.get("result");
      return new ResponseBean(200, "邮件发送结果", true, ((String) _get));
    }
    Object _get_1 = map.get("password");
    String password = ((String) _get_1);
    Object _get_2 = map.get("email");
    String sendTo = ((String) _get_2);
    this.emailService.sendHtmlEmail(sendTo, "密码", password);
    return new ResponseBean(200, "邮件发送结果", true, "发送成功");
  }
  
  @RequestMapping("/setPassword")
  @Override
  public ResponseBean setpassword(final String password) {
    InputOutput.<String>println(password);
    return new ResponseBean(200, "邮件发送结果", true, "修改成功");
  }
  
  @RequestMapping("/searchEmail")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchEmail(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    int i = this.userService.searchEmail(username);
    return new ResponseBean(200, "查询结果", true, Integer.valueOf(i));
  }
  
  @RequestMapping("/updateEmail")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean updateEmail(@RequestHeader("Authorization") final String token, final String email) {
    String username = JwtUtil.getUsername(token);
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("email", email);
    HashMap<String, String> map = CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1);
    int i = this.userService.updateEmail(map);
    return new ResponseBean(200, "设置结果", true, Integer.valueOf(i));
  }
}
