package ipush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.PushLogMapper;
import ipush.model.PushLog;
import ipush.service.PushLogService;

@Service("pushLogService")
public class PushLogServiceImpl implements PushLogService {

	@Autowired
	private PushLogMapper pushLogMapper;
	
	@Override
	public int insert(PushLog pushLog) {
		// TODO Auto-generated method stub
		return pushLogMapper.insert(pushLog);
	}

	@Override
	public List<PushLog> selectAll() {
		// TODO Auto-generated method stub
		return pushLogMapper.selectAll();
	}

}
