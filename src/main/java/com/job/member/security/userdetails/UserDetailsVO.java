/**
 * <PRE>
 *  Project : demo
 *  Package : demo.framework.security.vo
 * </PRE>
 * @file   : UserDetailsVO.java
 * @date   : 2015. 5. 15. 오후 3:29:17
 * @author : jkkim
 * @brief  :
 *  변경이력    :
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        jkkim  : 2015. 5. 15.       :            : 신규 개발.
 */
package com.job.member.security.userdetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsVO {
	private String userId;
	private String password;
	private String userNm;
	private String roleId;
}


