package com.compitation.ticketsystem.dispatchImpl;

import com.comtipation.ticketsystem.model.User;

/**
 * ��¼ע��ĵ��Ȳ�ӿ���
 * @author Administrator
 *
 */
public interface LoginAndRegisterDispatchImpl {
	/**
	 * ��¼
	 * @param userName
	 * @param passWord
	 */
	public void login(String userName,String passWord);
	/**ע��
	 * @param user
	 */
	public void register(User user);
}
