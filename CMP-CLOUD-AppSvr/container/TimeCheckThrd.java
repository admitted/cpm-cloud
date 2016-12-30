package container;

import java.util.LinkedList;

import util.CommUtil;
import bean.BaseCmdBean;

/**
 * ʱ��У��
 * �� ʱ�䳬ʱ�Ŀͻ��� LOG��ӡ���� ��ӡ��ʽ [data=CPM_ID]   
 * @author cui
 *
 */
public class TimeCheckThrd extends Thread
{
	private int m_TimeOut = 60; //m_TimeOutʱ��Ĭ��ֵ 60 ��
	public TimeCheckThrd(int timeout)throws Exception
	{
		m_TimeOut = timeout;
	}
	
	/* 
	 * TimeCheckThrd run����ִ�к� ��ӡ [data �Ƿ�ʱ]
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		LinkedList<String> checkList = null; //���������б�,���ڿͻ������ݽ���
		while(true)
		{
			try
			{
				checkList = ActionContainer.GetTimeOutList(m_TimeOut); // m_TimeOut = 30
				while(!checkList.isEmpty())
				{
					System.out.println(checkList);
					String data = checkList.removeFirst();
					System.out.println(data);
					if(null ==  data)
					{
						break;
					}						
					BaseCmdBean bean = ActionContainer.GetAction(data);
					if(null != bean)
						bean.noticeTimeOut(); // �˺���Ϊ�� ʲôҲ����
					CommUtil.LOG(data + " ��Ӧ��ʱ 222");
				}
				sleep(1000*10);  //˯��10 s
			}catch(Exception e)
			{}
		}				
	}	
}