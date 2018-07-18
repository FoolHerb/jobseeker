package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.mongo.dao.I_RecruitMongoDao;
import cn.tentact.nebula.recruit.I_RecruitService;
import com.google.common.base.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class RecruitService implements I_RecruitService {
  @Autowired
  private I_RecruitDao recruitDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Autowired
  private I_RecruitMongoDao recruitMongoDao;
  
  @Override
  public List<Map> searchLimitRecruit(final String username, final int start, final int end) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("start", Integer.valueOf(start));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("count", Integer.valueOf((end - start)));
    HashMap<String, Integer> map = CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2);
    int count = this.recruitDao.recruitCount();
    if ((end > count)) {
      map.put("count", Integer.valueOf((count - start)));
    }
    if ((start > count)) {
      map.put("count", Integer.valueOf(0));
    }
    List<Map> list = this.recruitDao.searchRecruit(map);
    return list;
  }
  
  @Override
  public Map searchOneRecruit(final int recruitId) {
    Map map = this.recruitDao.searchOneRecruit(recruitId);
    String description = this.recruitMongoDao.getDescription(Integer.valueOf(recruitId).toString());
    map.put("description", description);
    InputOutput.<String>println(description);
    return map;
  }
  
  @Override
  public List<Map> searchRecruitByKeyWord(final String username, final int start, final int end, final String key) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("start", Integer.valueOf(start));
    Pair<String, Integer> _mappedTo_2 = Pair.<String, Integer>of("count", Integer.valueOf((end - start)));
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("key", key);
    HashMap<String, Object> map = CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3);
    List<Map> list = this.recruitDao.searchRecruitByKeyWord(map);
    return list;
  }
}
