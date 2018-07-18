package cn.tentact.nebula.resume;

import cn.tentact.nebula.resume.I_ResumeController;
import cn.tentact.nebula.resume.I_ResumeService;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.web.ResponseBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

@RestController
@RequestMapping("/resume")
@SuppressWarnings("all")
public class ResumeController implements I_ResumeController {
  @Autowired
  private I_ResumeService resumeService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/sendMyResume")
  @Override
  public ResponseBean sendMyResume(@RequestHeader("Authorization") final String token, final int recruitId) {
    String username = JwtUtil.getUsername(token);
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("recruitId", Integer.valueOf(recruitId));
    HashMap<String, Object> map = CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1);
    int i = this.resumeService.sendMyResume(map);
    return new ResponseBean(200, "投递结果", true, Integer.valueOf(i));
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchResume")
  @Override
  public ResponseBean searchResume(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    Map map = this.resumeService.searchResume(username);
    return new ResponseBean(200, "查询结果", true, map);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/updateResume")
  @Override
  public ResponseBean updateResume(@RequestHeader("Authorization") final String token, final String name, final String sex, final String Date, final String height, final String weight, final String marry, final String city, final String education, final String tel, final String email, final String position, final String place, final String money, final String worktime, final String eduexp, final String workexp, final String selfintro, final String tech, final String techexp, final String eduexpnum, final String workexpnum) {
    String username = JwtUtil.getUsername(token);
    Gson gson = new Gson();
    ArrayList<Map> stus = new ArrayList<Map>();
    ArrayList<Map> stus2 = new ArrayList<Map>();
    JsonParser parser = new JsonParser();
    JsonParser parser2 = new JsonParser();
    JsonArray jarray = parser.parse(workexp).getAsJsonArray();
    JsonArray jarray2 = parser2.parse(eduexp).getAsJsonArray();
    for (final JsonElement jsonelement : jarray) {
      stus.add(gson.<Map>fromJson(jsonelement, Map.class));
    }
    final Consumer<Map> _function = (Map one) -> {
      InputOutput.<Map>println(one);
    };
    stus.forEach(_function);
    for (final JsonElement jsonelement_1 : jarray2) {
      stus2.add(gson.<Map>fromJson(jsonelement_1, Map.class));
    }
    final Consumer<Map> _function_1 = (Map one) -> {
      InputOutput.<Map>println(one);
    };
    stus2.forEach(_function_1);
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, ArrayList<Map>> _mappedTo_1 = Pair.<String, ArrayList<Map>>of("workexp", stus);
    Pair<String, ArrayList<Map>> _mappedTo_2 = Pair.<String, ArrayList<Map>>of("eduexp", stus2);
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("name", name);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("sex", sex);
    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("Date", Date);
    Pair<String, String> _mappedTo_6 = Pair.<String, String>of("height", height);
    Pair<String, String> _mappedTo_7 = Pair.<String, String>of("weight", weight);
    Pair<String, String> _mappedTo_8 = Pair.<String, String>of("marry", marry);
    Pair<String, String> _mappedTo_9 = Pair.<String, String>of("city", city);
    Pair<String, String> _mappedTo_10 = Pair.<String, String>of("education", education);
    Pair<String, String> _mappedTo_11 = Pair.<String, String>of("tel", tel);
    Pair<String, String> _mappedTo_12 = Pair.<String, String>of("email", email);
    Pair<String, String> _mappedTo_13 = Pair.<String, String>of("position", position);
    Pair<String, String> _mappedTo_14 = Pair.<String, String>of("place", place);
    Pair<String, String> _mappedTo_15 = Pair.<String, String>of("money", money);
    Pair<String, String> _mappedTo_16 = Pair.<String, String>of("worktime", worktime);
    HashMap<String, Serializable> map = CollectionLiterals.<String, Serializable>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16);
    map.put("selfintro", selfintro);
    map.put("tech", tech);
    map.put("techexp", techexp);
    map.put("eduexpnum", eduexpnum);
    map.put("workexpnum", workexpnum);
    int i = this.resumeService.updateResume(map);
    return new ResponseBean(200, "更新结果", true, Integer.valueOf(1));
  }
  
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  @Override
  public ResponseBean upload(@RequestHeader("Authorization") final String token, final String file) {
    String username = JwtUtil.getUsername(token);
    BASE64Decoder decoder = new BASE64Decoder();
    InputOutput.<String>println(file);
    try {
      byte[] b = decoder.decodeBuffer(file);
      InputOutput.<String>println(file);
      for (int i = 0; (i < b.length); i++) {
        byte _get = b[i];
        boolean _lessThan = (_get < 0);
        if (_lessThan) {
          byte tmp = b[i];
          byte _get_1 = b[i];
          int _plus = (_get_1 + 256);
          tmp = ((byte) _plus);
          b[i] = tmp;
        }
      }
      String path = (("D://image//" + username) + ".jpg");
      FileOutputStream out = new FileOutputStream(path);
      out.write(b);
      out.flush();
      out.close();
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return new ResponseBean(200, "更新结果", true, Integer.valueOf(1));
  }
}
