package cn.tentact.nebula.recruit;

import cn.tentact.nebula.recruit.I_RecruitController;
import cn.tentact.nebula.recruit.I_RecruitService;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.web.ResponseBean;
import com.google.common.base.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruit")
@SuppressWarnings("all")
public class RecruitController implements I_RecruitController {
  @Autowired
  private I_RecruitService recruitService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchCurrentRecruit")
  @Override
  public ResponseBean searchCurrentRecruit(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("start", Integer.valueOf(0));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("end", Integer.valueOf(5));
    HashMap<String, Integer> map = CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1);
    List<Map> list = this.recruitService.searchLimitRecruit(username, 0, 5);
    return new ResponseBean(200, "招聘信息", true, list);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchLimitRecruit")
  @Override
  public ResponseBean searchLimitRecruit(@RequestHeader("Authorization") final String token, final int start, final int end) {
    String username = JwtUtil.getUsername(token);
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("start", Integer.valueOf(start));
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("end", Integer.valueOf(end));
    HashMap<String, Integer> map = CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1);
    List<Map> list = this.recruitService.searchLimitRecruit(username, start, end);
    return new ResponseBean(200, "招聘信息", true, list);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchOneRecruit")
  @Override
  public ResponseBean searchOneRecruit(@RequestHeader("Authorization") final String token, final int recruitId) {
    Map map = this.recruitService.searchOneRecruit(recruitId);
    return new ResponseBean(200, "招聘信息", true, map);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitByKey")
  @Override
  public ResponseBean searchRecruitByWord(@RequestHeader("Authorization") final String token, final int start, final int end, final String key) {
    String username = JwtUtil.getUsername(token);
    InputOutput.<String>println(key);
    boolean _equals = Objects.equal(key, null);
    if (_equals) {
      List<Map> list = this.recruitService.searchLimitRecruit(username, start, end);
      return new ResponseBean(200, "招聘信息", true, list);
    } else {
      List<Map> list_1 = this.recruitService.searchRecruitByKeyWord(username, start, end, key);
      return new ResponseBean(200, "招聘信息", true, list_1);
    }
  }
}
