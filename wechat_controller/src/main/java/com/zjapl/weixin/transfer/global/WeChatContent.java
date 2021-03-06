package com.zjapl.weixin.transfer.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zjapl.weixin.transfer.bean.AppInfo;
import com.zjapl.weixin.transfer.utils.StringUtils;

/**
 * 微信全局资源
 * @author yangb
 *
 */
public class WeChatContent {

	private static ArrayList<AppInfo> apps;

	/**
	 * 公众号和业务系统 的信息.
	 */
	private static Map<String, AppInfo> appMap;
	
	private static HashMap<String,String> appidAndSecret;
	
	public static HashMap<String, String> getAppidAndSecret() {
		return appidAndSecret;
	}
	
	public ArrayList<AppInfo> getApps() {
		return apps;
	}

	public void setApps(ArrayList<AppInfo> apps) {
		WeChatContent.apps = apps;
	}

	/**
	 * 添加一个公众号
	 * @param appinfo
	 */
	public void addAppInfo(AppInfo appinfo){
		apps.add(appinfo);
		init(apps);
	}
	
	/**
	 * 移除一个公众号
	 * @param appinfo
	 */
	public void removeAppInfo(AppInfo appinfo){
		AppInfo app = appMap.get(appinfo.getAppname());
		apps.remove(app);
	}
	
	/**
	 * 初始化
	 * @param apps
	 */
	public void init(ArrayList<AppInfo> apps){
		WeChatContent.apps = apps;
		
		appidAndSecret = new HashMap<>();
		appMap = new HashMap<>();
		for (AppInfo appInfo : apps) {
			appidAndSecret.put(appInfo.getAppid(), appInfo.getSecret());
			
			String appname = appInfo.getAppname();
			if(!StringUtils.isEmpty(appname)){
				appMap.put(appname, appInfo);
			}
		}
	}

	/**
	 * 获取应用信息
	 * @param appname
	 * @return
	 */
	public static AppInfo obtainAppInfo(String appname){
		return appMap.get(appname);
	}
	
	/**
	 * 获取应用密匙
	 * @param appid
	 * @return
	 */
	public static String obtainSecret(String appid){
		return appidAndSecret.get(appid);
	}

}
