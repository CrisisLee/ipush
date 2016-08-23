package ipush.service;

import java.util.List;

import ipush.model.PushLog;

public interface PushLogService {

	
	int insert(PushLog pushLog);
	
	List<PushLog> selectAll();
}
