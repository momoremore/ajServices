/**
 * Copyright(c) SUPCON 2008-2011. �㽭����п���Ϣ�������޹�˾
 */

package its.webservice.service;

import its.webservice.activemq.MessageSender;
import its.webservice.common.AppInitConstants;
import its.webservice.config.SystemConfig;
import its.webservice.dao.VehPassDao;
import its.webservice.entity.PicInfo;
import its.webservice.entity.WbRecord;
import its.webservice.util.FtpUtil;
import its.webservice.util.HttpPicSaver;
import its.webservice.util.ItsUtility;
import its.webservice.util.PassPostUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.util.Base64;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;


/**
 * ϵͳ���ƣ����ܽ�ͨWebService����(ITSWebService)
 * ����ģ�飺��������д��
 * ������������������д��ӿ�ʵ����
 * �ļ�����com.supconit.its.service.impl.WriteVehInfoServiceImpl.java
 * �汾��Ϣ��1.00
 * 
 * �������ţ��з�����
 * �����ߣ� lzk
 * ����ʱ�䣺Jan 25, 2011 4:16:13 PM
 * �޸��ߣ� lzk
 * �޸�ʱ�䣺Jan 25, 2011 4:16:13 PM
 */

public class VehInfoServiceImpl implements VehInfoService {
	
	/** ��ȡLog4jʵ�� */
    protected Logger log = Logger.getLogger(VehInfoServiceImpl.class.getName());
    
    private VehPassDao vehPassDao;
    
	public VehPassDao getVehPassDao() {
		return vehPassDao;
	}

	public void setVehPassDao(VehPassDao vehPassDao) {
		this.vehPassDao = vehPassDao;
	}
	
	/*
	 * ͼƬ�������洢ͼƬ·��ǰ׺����D:/picserver
	 * ��ֵ���������ļ�������
	 */
	private String imaPath = "";
	
	public String getImaPath() {
		return imaPath;
	}

	public void setImaPath(String imaPath) {
		this.imaPath = imaPath;
	}
	
	/**
	 * ͼƬ������FTPǰ׺������ͳһ�������ͼƬ����ftp://its:its@10.10.76.75:21
	 * ��ֵ���������ļ�������
	 */
	private String ftpPre = "";
	
	public String getFtpPre() {
		return ftpPre;
	}

	public void setFtpPre(String ftpPre) {
		this.ftpPre = ftpPre;
	}
	
	//FTP����·��
	String virtualRoute = "";
	
    //protected Socket socket;
    
	 //PrintWriter pw;
	
    /**
     *  ���캯��
     */
	/*public VehInfoServiceImpl(){
		try {
	        // ���� 
			if(socket == null){
		        socket = new Socket(AppInitConstants.SOCKET_PROXY_SERVER_IP, AppInitConstants.SOCKET_PROXY_SERVER_PORT);
		        log.debug("��SMSS�������ӳɹ���");
			}
		}catch (Exception e) {
			log.error("��ʼ������SMSSʧ�ܣ�"+e.getMessage());
			e.printStackTrace();
        }
	}*/

	/**
	 * ��ʼ����Ϣд��
	 * @param deviceCode
	 * @param deviceKey
	 * @return
	 */
	public String InitWriteVehInfo(String deviceCode,String deviceKey){
		log.debug("WriteVehInfoServiceImpl.InitWriteVehInfo()  Start......");
		String returnStr = "";
		
		/*if("".equals(deviceCode)){
			return "���ڱ�Ų���Ϊ�գ�����д�������";
		}
		if("".equals(deviceKey)){
			return "���ڱ�Ŷ�Ӧ��Կ����Ϊ�գ�����д�������";
		}
		//�жϿ���ƽ̨�Ƿ����Ӹÿ��ڱ��
		if(!vehPassDao.hasAddedCode(deviceCode)){
			return "���ڱ��("+deviceCode+")��δ�ڿ��ڹ���ƽ̨���������Ϣ��������ϵ����Ա���������ٽ�������д�룡";
		}
		try{
			//��֤������Կ�Ƿ�Ϸ�
			if(deviceKey.equals(ItsUtility.getMD5String(deviceCode+"supcon"))){
				//�ж��Ƿ��Ѿ�����ʼ����֤
				if(AppInitConstants.ALL_CODE_KEY_MAP.containsKey(deviceCode)){
					//return "���ڱ��("+deviceCode+")��Ӧ����Կ("+deviceKey+")�Ѿ�����ʼ��ע�ᣬ�����ٴγ�ʼ��ע�ᡣ";
					return "";
				}else{
					AppInitConstants.ALL_CODE_KEY_MAP.put(deviceCode, deviceKey);
				}
			}else{
				return "���ڱ��("+deviceCode+")��Ӧ����Կ("+deviceKey+")��������ϵ����Ա���»����Կ��";
			}
		
			boolean isInsert = vehPassDao.InitWriteVehInfo(deviceCode, deviceKey);
			if(isInsert){
				log.debug("���ڱ��:"+deviceCode+"��ʼ��ע��ɹ���");
			}else{
				returnStr = "���ڱ��"+deviceCode+"��ʼ��ע��ʧ�ܣ�����ϵ����Ա��";
			}
		}catch(Exception e){
			log.error("���ڱ��"+deviceCode+"��ʼ��ע��ʧ�ܡ�" + e.getMessage());
			returnStr = "���ڱ��"+deviceCode+"��ʼ��ע��ʧ�ܣ�����ϵ����Ա��" + e.getMessage();
		}*/
		
		log.debug("WriteVehInfoServiceImpl.InitWriteVehInfo()  End......");
		return returnStr;
	}

	/**
	 * ��������д��ӿڷ���
	 * @param vehInfo
	 * @return String
	 */
	public String WriteVehicleInfo(String SBBH,String FXBH,String HPHM,String HPZL,String JGSJ,String CLSD,String HPYS,
			String CSYS,String CLLX,String TZTP,String QMTP,String HPTP,String CDH,String XS) {
	    long start = System.currentTimeMillis();																					
        long end;																																		
		long total = 0;	
		
		log.debug("WriteVehInfoServiceImpl.WriteVehicleInfo()  Start......");
		String returnStr = "";
		log.debug("HPHM:" + HPHM);
		log.debug("JGSJ:" + JGSJ);
		log.debug("HPYS:" + HPYS);
		log.debug("SBBH:" + SBBH);
		log.debug("CDH:" + CDH);
		log.debug("CLSD:" + CLSD);
		log.debug("XS:" +XS);
		
		//�жϸ�·�ڱ���Ƿ񾭹���ʼ�������Ƿ������ԿУ��
		/*if(!AppInitConstants.ALL_CODE_KEY_MAP.containsKey(vehInfo.getJgdd())){
			return "��·��δ���г�ʼ����֤�����Ƚ���InitWriteVehInfo��ʼ��ע�ᣬ�ٽ�������д�룡";
		}*/
		
		//�ж�һЩ�������Ƿ�д��
		if("".equals(HPHM)){
			return "���ƺ���(HPHM)����Ϊ�գ�����д�������";
		}
		if("".equals(JGSJ)){
			return "����ʱ��(JGSJ)����Ϊ�գ�����д�������";
		}
		//�жϹ���ʱ���ʽ�Ƿ���ȷ
		//if(!ItsUtility.isValidDate(vehInfo.getJgsj())){
		//	return "����ʱ��(JGSJ:"+vehInfo.getJgsj()+")��ʽ��������д�������";
		//}
		Date jgsj = ItsUtility.StringToDate(JGSJ,"yyyy-MM-dd HH:mm:ss");
		log.debug("jgsj:"+jgsj);
		if(jgsj == null){
			return "����ʱ��(JGSJ:"+JGSJ+")��ʽ��������д�������";
		}
		if("".equals(HPYS)){
			return "������ɫ(HPYS)����Ϊ�գ�����д�������";
		}
		if("".equals(SBBH)){
			return "�豸���(SBBH)����Ϊ�գ�����д�������";
		}
		if("".equals(CDH)){
			return "������(CDH)����Ϊ�գ�����д�������";
		}
		try{
			boolean isInsert = vehPassDao.WriteVehicleInfo(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,TZTP,QMTP,HPTP,CDH,XS);
			//boolean isInsert = true;
			if(isInsert){
				log.debug("��������д��ɹ���");
			}else{
				returnStr = "��������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
		}catch(Exception e){
			log.error("д���������ʧ�ܣ�" + e.getMessage());
			return returnStr = "д���������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
			
		}
		
		//����ʵʱ���ݺͲ��ر���  Added by Tony Lin on 2012-5-10
		try{
			//���˵���ʷ���ݱ���,1Сʱ��  Added by Tony Lin on 2012-7-25
			Date now = new Date();
			long n = (now.getTime() - jgsj.getTime())/(60*60*1000);//n < 2 ��ʾʱ���ӳ�С��2Сʱ
			if("1".equals(AppInitConstants.IS_NEED_BKBJ)){//�Ƿ���в��ر������ܣ�1�У�0��  Modified by Tony Lin on 2012-5-14
				//ʵʱ�������ݷ���
				sendLiveMessage(HPHM,SBBH,JGSJ,FXBH,CDH,HPZL,CLSD,TZTP,QMTP,HPTP);
				
				//����У�鱨��
				//У���Ƿ�Ϊ������Ϣ�������������Ͳ�����Ϣ
				if(n < 2){//n < 2 ��ʾʱ����С��2Сʱ
		            Map<String,String> alarmMap = vehPassDao.checkAlarmInfo(JGSJ, SBBH, HPHM, CSYS, HPZL, CLLX);
		            if("true".equals(alarmMap.get("alarm"))){//���Ͳ��ر��������ݸ�ʽ�����붼��ת��������
		            	sendAlarmmessge(alarmMap.get("bkid"),HPHM,HPZL,SBBH,CDH,FXBH,JGSJ,CLSD,TZTP,QMTP,HPTP,HPYS,alarmMap.get("bk_type_no"),alarmMap.get("ifsend"),alarmMap.get("phoneno"));
		            	//д�뱨������  Added by Tony Lin on 2012-6-4
		            	vehPassDao.insertBkAlarmVeh(SBBH, FXBH, HPHM, HPZL, JGSJ, CLSD, HPYS, CSYS, CLLX, TZTP, QMTP, HPTP, CDH, XS, alarmMap.get("bkid"), alarmMap.get("bk_type_no"));
		            }
				}
			}
		}catch(Exception e){
			log.error("�������ݱ���ʧ�ܣ�" + e.getMessage());
			//returnStr = "�������ݱ���ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
		}
		
		//д�볬��Υ��������WFCLB added by Tony Lin on 2011-09-27
		//ȡ��ԭ�����ݿ�job���ô洢���̷�ʽ
		//ȡ���˷�ʽ�жϣ����ڹ���Υ������д�������WriteSurveilInfo()   Modified by Tony Lin on 2011-12-26
		/*if("".equals(AppInitConstants.DEPLOY_PLACE)){
			int clxs = 50;
			int csbl = 0;
			try{
				clxs = Integer.parseInt(XS);
				csbl = Math.round((Integer.parseInt(CLSD) - clxs)*100/clxs);
			}catch(Exception e){
				
			}
			log.debug("csbl=" + csbl);
			if(csbl > 10){//����10%����д��Υ������
				try{
					//��Υ������д�����ݿ�(WFSJB_YSH)
					boolean isInsert = vehPassDao.insertWFCLB(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,TZTP,QMTP,HPTP,CDH,XS);
					if(isInsert){
						log.debug("Υ�����ٹ�������д��ɹ���");
					}else{
						returnStr = "Υ�����ٹ�������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
					}
				}catch(Exception e){
					log.error("д��Υ�����ٹ�������ʧ�ܣ�" + e.getMessage());
					returnStr = "д��Υ�����ٹ�������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
				}
			}
		}*/
		
		log.debug("WriteVehInfoServiceImpl.WriteVehicleInfo()  End......");
		
		end = System.currentTimeMillis();
		total = end - start;
		long sec = total / 1000;		//����ʱ�侫ȷ����
		log.debug("��������д���ܹ���ʱ�� " + sec / 60 + " �� " + sec % 60 + " ��"+total%1000+"���롣 ");
		return returnStr;
	}
	
	/**
	 * ��������(��ͼƬ)д��ӿڷ���
	 * 
	 * @return String
	 */
	public String WriteVehicleInfoWithPhoto(String SBBH,String FXBH,String HPHM,String HPZL,
			String JGSJ,String CLSD,String HPYS,String CSYS,String CLLX,String TZTP,String QMTP,String HPTP,String TPTYPE,String CDH,String XS){
		log.debug("WriteVehInfoServiceImpl.WriteVehicleInfoWithPhoto()  Start......");
		
	    long start = System.currentTimeMillis();																					
        long end;																																		
		long total = 0;	
		 
		String returnStr = "";
		
		
		//�ж�һЩ�������Ƿ�д��
		if("".equals(HPHM)){
			return "���ƺ���(HPHM)����Ϊ�գ�����д�������";
		}
		if("".equals(JGSJ)){
			return "����ʱ��(JGSJ)����Ϊ�գ�����д�������";
		}
		Date jgsj = ItsUtility.StringToDate(JGSJ,"yyyy-MM-dd HH:mm:ss");
		if(jgsj == null){
			return "����ʱ��(JGSJ:"+JGSJ+")��ʽ��������д�������";
		}
		if("".equals(HPYS)){
			return "������ɫ(HPYS)����Ϊ�գ�����д�������";
		}
		if("".equals(SBBH)){
			return "�豸���(SBBH)����Ϊ�գ�����д�������";
		}
		if("".equals(CDH)){
			return "������(CDH)����Ϊ�գ�����д�������";
		}
		if("".equals(TPTYPE)){
			TPTYPE = "jpg";
		}
		
		if ("δʶ��".equals(HPHM)) {
			HPHM="�޷�ʶ��";
		}
		
		
		
		String tztpFtpPath = "";
		String qmtpFtpPath = "";
		String hptpFtpPath = "";
		String fileNewPath = "";
		
		//���������ļ����ѡ��   Added by Tony Lin on 2013-06-14
		//Modified by Tony Lin on 2013-06-14
		Map<String,String> ftpMap = ItsUtility.getRandomImgPath("kk");
		String imaPath = ftpMap.get("ImagePath");
		String virtualRoute = ftpMap.get("VirtualRoute");
		String ftpPre = ftpMap.get("ftpPre");
		if("".equals(imaPath)){
			imaPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH;
		}
		if("".equals(ftpPre)){
			ftpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		if("".equals(virtualRoute)){
			virtualRoute = AppInitConstants.IMG_SERVER_FILE_PATH;
		}
		
		String gaFtpPre = SystemConfig.getConfigInfomation("gaFtpPre");
		if("".equals(gaFtpPre)){
			gaFtpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		String gaTztpFtpPath = "";
		String gaQmtpFtpPath = "";
		String gaHptpFtpPath = "";
		String isWriteGA = SystemConfig.getConfigInfomation("isWriteGA");
		
		 //mowangzhong
		String tztpHttpPath="";
	//	String qmtpHttpPath="";
		String hptpHttpPath="";
		
		try{
			FileOutputStream fos = null;
			File file = null;
			byte[] bytes = null;
			String cltpName = "";
			//++++++
			HttpPicSaver httpPicSaver = new HttpPicSaver(AppInitConstants.HTTP_KK_PIC_IP_ZW, AppInitConstants.HTTP_PIC_PORT);
			String zwurl = "http://"+AppInitConstants.HTTP_KK_PIC_IP_ZW+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
		
				try {
					
					//������ͼƬ����ڱ��ػ���FTP�ϣ���������д�����ݿⲢ��¼����ͼƬ��ַ
					//����ͼƬ����Ŀ¼·��Ϊ������(��)/�豸���/����/������/ͼƬ��
					if(null != TZTP &&  !"".equals(TZTP)){//������һ���ļ�
						//cltpName = SBBH + "_" +  ItsUtility.DateToString(jgsj, "yyyyMMddHHmmss") + "_" + CDH + "_" + "TZTP." + TPTYPE;
						//Modified by Tony Lin on 2012-8-1
						cltpName = String.valueOf(start) + "_" + "TZTP." + TPTYPE;
						fileNewPath =  AppInitConstants.IMG_SERVER_ABS_FILE_PATH_KK + "/"+ ItsUtility.DateToString(jgsj, "yyyyMMdd") + "/" + SBBH + "/" + FXBH + "/" + CDH + "/";
						try {
							file = new File(imaPath  + fileNewPath + cltpName);   
							//������ļ��Ѵ��ڣ���ֱ�ӷ���
							/*if(file.isFile()){
								returnStr = "��������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
								return returnStr;
							}*/
							//����ļ��в����ڣ����ȴ����ļ���
							if(file.isDirectory()){
								log.debug("the directory is exists!");
							}else{
								file.getParentFile().mkdirs();
								//filePath.createNewFile();
								log.debug("�½�Ŀ¼��"+file+" �ɹ�");
							}
							
							bytes = Base64.decode(TZTP);   
							fos = new FileOutputStream(file);   
							fos.write(bytes);   
							fos.flush();   
							fos.close();
						} catch (Exception e) {
							PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,TZTP);
							AppInitConstants.picInfoQueue.put(picInfo);
							log.error("д�����ͼƬʧ�ܣ�" + SBBH + "_" + HPHM + "_" + JGSJ + "; error:" + e.getMessage());
						} 
			        	tztpFtpPath = ftpPre + virtualRoute + fileNewPath +cltpName;
			        	gaTztpFtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
			        	//**********************************************************************************************************************
			        	//HttpPicSaver httpPicSaver = new HttpPicSaver(AppInitConstants.HTTP_KK_PIC_IP_ZW, AppInitConstants.HTTP_PIC_PORT);
						//String zwurl = "http://"+AppInitConstants.HTTP_KK_PIC_IP_ZW+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
					
						//����ͼƬ��ŵ�http ͼƬ������
						//������һ��
						cltpName = SBBH + "_" +  ItsUtility.DateToString(jgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "tztp." + TPTYPE;
						//��ͼƬ������ת��
						bytes = Base64.decode(TZTP);
						//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
						String picKey =httpPicSaver.save(bytes, cltpName);
						tztpHttpPath = zwurl+picKey;
						System.out.println("+tztpHttpPath"+tztpHttpPath);
					}
		        	if(null != QMTP && !"".equals(QMTP)){//�����ڶ����ļ�
		        		//Modified by Tony Lin on 2012-8-1
						cltpName = String.valueOf(start) + "_" + "QMTP." + TPTYPE;
		        		fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_KK  + "/"+  ItsUtility.DateToString(jgsj, "yyyyMMdd") + "/" + SBBH + "/" + FXBH + "/" + CDH + "/";
						try {
							file = new File(imaPath  + fileNewPath + cltpName);   
							//����ļ��в����ڣ����ȴ����ļ���
							if(file.isDirectory()){
								log.debug("the directory is exists!");
							}else{
								file.getParentFile().mkdirs();
								log.debug("�½�Ŀ¼��"+file+" �ɹ�");
							}
							
							bytes = Base64.decode(QMTP);   
							fos = new FileOutputStream(file);   
							fos.write(bytes);   
							fos.flush();   
							fos.close();
						} catch (Exception e) {
							PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,QMTP);
							AppInitConstants.picInfoQueue.put(picInfo);
							log.error("д�����ͼƬʧ�ܣ�" + SBBH + "_" + HPHM + "_" + JGSJ + "; error:" + e.getMessage());
						} 
			        	qmtpFtpPath = ftpPre + virtualRoute+ fileNewPath  +cltpName;
			        	gaQmtpFtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
			        	
		        	}else{
		        		qmtpFtpPath = tztpFtpPath;
		        		gaQmtpFtpPath = gaTztpFtpPath;
		        	}
		        	if(null != HPTP && !"".equals(HPTP)){//�����������ļ�
		        		//Modified by Tony Lin on 2012-8-1
						cltpName = String.valueOf(start) + "_" + "HPTP." + TPTYPE;
		        		fileNewPath =  AppInitConstants.IMG_SERVER_ABS_FILE_PATH_KK  + "/"+  ItsUtility.DateToString(jgsj, "yyyyMMdd") + "/" + SBBH + "/" + FXBH + "/" + CDH + "/";
						try {
							file = new File(imaPath  + fileNewPath + cltpName);   
							//����ļ��в����ڣ����ȴ����ļ���
							if(file.isDirectory()){
								log.debug("the directory is exists!");
							}else{
								file.getParentFile().mkdirs();
								//filePath.createNewFile();
								log.debug("�½�Ŀ¼��"+file+" �ɹ�");
							}
							
							bytes = Base64.decode(HPTP);
							fos = new FileOutputStream(file);   
							fos.write(bytes);   
							fos.flush();   
							fos.close();
						} catch (Exception e) {
							PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,HPTP);
							AppInitConstants.picInfoQueue.put(picInfo);
							log.error("д�����ͼƬʧ�ܣ�" + SBBH + "_" + HPHM + "_" + JGSJ + "; error:" + e.getMessage());
						} 
			        	hptpFtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
			        	gaHptpFtpPath = gaFtpPre + virtualRoute  + fileNewPath +cltpName;
			        	
			        	
			        	//����ͼƬ��ŵ�hptp ͼƬ������
						//����������
						cltpName = SBBH + "_" +  ItsUtility.DateToString(jgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "hptp." + TPTYPE;
						//��ͼƬ������ת��
						bytes = Base64.decode(HPTP);
						//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
						String picKey =httpPicSaver.save(bytes, cltpName);
						hptpHttpPath = zwurl+picKey;
			        	       				        	
		        	}
		        	//fos.close();   
		        } catch (Exception e) {
		        	log.error("д�����ͼƬʧ�ܣ�" + SBBH + "_" + HPHM + "_" + JGSJ + "; error:" + e.getMessage());
		        	//ȡ��ͼƬ�������ƣ���������ͼƬ������д�� Added by Tony Lin on 2012-4-28
		        	returnStr = "��������ͼƬд��ʧ�ܣ����ȷ��ͼƬ�����Ƿ���ȷ��" + e;
		        } finally{
		             if (fos != null){
		                 try{
		                	 fos.flush(); 
		                     fos.close();
		                 }
		                 catch (Exception e){
		                	 log.error(e.getMessage());
		                	 e.printStackTrace();
		                 }
		             }
		         }
	        
			if(!StringUtils.isEmpty(SystemConfig.getBMDConfigInfomation(HPHM+"_"+HPZL))){
				//������
				vehPassDao.WriteBMDVehicleInfoWithPhoto(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,tztpFtpPath,qmtpFtpPath,hptpFtpPath,CDH,XS);
			}else{
				//���͸������ݣ�����oracle start
			//	boolean isInsert = vehPassDao.WriteVehicleInfoWithPhoto(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,tztpFtpPath,qmtpFtpPath,hptpFtpPath,CDH,XS);
				
				if ("�޷�ʶ��".equals(HPHM)) {
					HPHM="-";
				}		
				System.out.println("****************"+FXBH+"*******************");
				String result=PassPostUtil.postClientUploadTgs(AppInitConstants.uploadForBigDate.toString(), "999999", 
						SBBH, SBBH, HPHM, HPZL, HPYS,CLLX,
						JGSJ, FXBH, CDH, CLSD,"1", "1", "2","",
						CSYS, "000,000,000,000", "000,000,000,000", "000,000,000,000",
						"0",tztpHttpPath);
			    String code = result.substring(result.indexOf(":")+1,result.indexOf(","));
			    //���͵�������-end
				if("0".endsWith(code)){ 
					System.out.println("��������д������ݳɹ���+++++++++++++++++");
					if ("δʶ��".equals(HPHM)) {
						HPHM="����";
					}
					//ת��������ɫ�ṩ������
					if(HPYS=="01"){
						HPYS="3";
					}else if(HPYS=="02"){
						HPYS="4";
					}else if(HPYS=="03"||HPYS=="04"){
						HPYS="5";
					}else if(HPYS=="23"){
						HPYS="2";
					}else{
						HPYS="1";
					}
				     String mqMessage="1"+"$"+SBBH+"$"+CDH+"$"+HPHM+"$"+HPYS+"$"+HPZL+"$"+JGSJ+"$"+CLSD+"$"+CLLX+"$"+"1"+"$"+CSYS+"$"+hptpHttpPath+"$"+tztpHttpPath+"$"+"0"+"$"+"1"+"$"+""+"$"+"";
				     boolean offer = AppInitConstants.sendMQQueue.offer(mqMessage);
				     if (offer) {
				    	 log.debug("+++++++++��ͼƬ�������ݷ���MQ���гɹ������г��ȣ�"+AppInitConstants.sendMQQueue.size()+"+++++++++++");
					}
				}else{
					returnStr = "��������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
				}
				if("1".equals(isWriteGA)){ //д�뵽��ʱ��ͬ����������
					boolean isInsertGa = vehPassDao.WriteVehicleInfoWithPhotoTemp(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,gaTztpFtpPath,gaQmtpFtpPath,gaHptpFtpPath,CDH,XS);
					if(isInsertGa){
						log.debug("��������д����ʱ���ɹ���");
						
					}else{
						returnStr = "��������д����ʱ��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
					}
				}
			}
			
		}catch(Exception e){
			log.error("д���������ʧ�ܣ�" + SBBH + "_" + HPHM + "_" + JGSJ + "; error:" + e.getMessage());
			returnStr = "д���������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
			return returnStr;
		}
		
		
		//д�볬��Υ��������WFSJB_YSH added by Tony Lin on 2011-09-27
		boolean isCS = false;
		//��ʽ�羯�������ݹ��˵�, added by Tony Lin on 2012-04-10
		//�жϸ��豸�Ƿ���Ҫ�ɼ�Υ���������� added by Tony Lin on 2012-5-4
		/*if(vehPassDao.isNeedWfcj(SBBH)){
		}*/
		try{
			//if(!"1".equals(SBBH.substring(7, 8))){//�����豸��Ź����ж�,�ų��羯��������
			if("deqing".equals(AppInitConstants.DEPLOY_PLACE)){
				int clxs = 50;
				int csbl = 0;
				try{
					clxs = Integer.parseInt(XS);
					csbl = Math.round((Integer.parseInt(CLSD) - clxs)*100/clxs);
				}catch(Exception e){
				}
				if(csbl > 10){
					isCS = true;
				}
				log.debug("csbl=" + csbl);
			} else {
				if(vehPassDao.isNeedWfcj(SBBH)){//�жϸ��豸�Ƿ���Ҫ�ɼ�Υ���������� added by Tony Lin on 2012-5-4
					if("anji".equals(AppInitConstants.DEPLOY_PLACE)){//�������ٱ�׼Ϊ��ʵ�� - ���� > 10
						if((Integer.parseInt(CLSD) - Integer.parseInt(XS)) > 10){
							isCS = true;
						}
					}else if("shangyu".equals(AppInitConstants.DEPLOY_PLACE)){//���ݳ��ٱ�׼Ϊ��ʵ�� - ���� > 10
						if((Integer.parseInt(CLSD) - Integer.parseInt(XS)) > 10){
							isCS = true;
						}
					}else{//�����ٱ��� > 10%
						int clxs = 50;
						int csbl = 0;
						try{
							clxs = Integer.parseInt(XS);
							csbl = Math.round((Integer.parseInt(CLSD) - clxs)*100/clxs);
						}catch(Exception e){
							
						}
						if(csbl > 10){
							isCS = true;
						}
						log.debug("csbl=" + csbl);
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
		if(isCS){//����д��Υ������
			try{
				//��Υ������д�����ݿ�(WFSJB_YSH)
				boolean isInsert = vehPassDao.insertWFCLB(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,tztpFtpPath,qmtpFtpPath,hptpFtpPath,CDH,XS);
				if(isInsert){
					log.debug("Υ�����ٹ�������д��ɹ���");
				}else{
					//returnStr = "Υ�����ٹ�������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
				}
				if("1".equals(isWriteGA)){
					boolean isInsertGa = vehPassDao.insertWFCLBTemp(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,gaTztpFtpPath,gaQmtpFtpPath,gaHptpFtpPath,CDH,XS);
					if(isInsertGa){
						log.debug("Υ����������д����ʱ���ɹ���");
						
					}else{
						returnStr = "Υ�����ٹ�������д����ʱ��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
					}
				}
			}catch(Exception e){
				log.error("д��Υ�����ٹ�������ʧ�ܣ�" + e.getMessage());
				returnStr = "д��Υ�����ٹ�������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
				//return returnStr;
			}
		 }
		/* ��������Ǩ�� ������
		try{
			//д��ʱ����ʵʱ���ݷ��͡����س����ͺ�����У�飬���������ر�����Ϣ   Added by Tony Lin on 2011-12-12
			//if("haiyan".equals(AppInitConstants.DEPLOY_PLACE)  || "anji".equals(AppInitConstants.DEPLOY_PLACE)  || "common".equals(AppInitConstants.DEPLOY_PLACE)){
			//���˵���ʷ���ݱ���,1Сʱ��  Added by Tony Lin on 2012-7-25
			Date now = new Date();
			long n = (now.getTime() - jgsj.getTime())/(60*60*1000);//n < 2 ��ʾʱ����С��2Сʱ
			if("1".equals(AppInitConstants.IS_NEED_BKBJ)){//�Ƿ���в��ر������ܣ�1�У�0��  Modified by Tony Lin on 2012-5-14
				//ʵʱ�������ݷ���
				sendLiveMessage(HPHM,SBBH,JGSJ,FXBH,CDH,HPZL,CLSD,tztpFtpPath,qmtpFtpPath,hptpFtpPath);
				//����У�鱨��
				//У���Ƿ�Ϊ������Ϣ�������������Ͳ�����Ϣ
				if(n < 2){//n < 2 ��ʾʱ����С��2Сʱ
		            Map<String,String> alarmMap = vehPassDao.checkAlarmInfo(JGSJ, SBBH, HPHM, CSYS, HPZL, CLLX);
		            if("true".equals(alarmMap.get("alarm"))){//���Ͳ��ر��������ݸ�ʽ�����붼��ת��������
		            	sendAlarmmessge(alarmMap.get("bkid"),HPHM,HPZL,SBBH,CDH,FXBH,JGSJ,CLSD,tztpFtpPath,qmtpFtpPath,hptpFtpPath,HPYS,alarmMap.get("bk_type_no"),alarmMap.get("ifsend"),alarmMap.get("phoneno"));
		            	//д�뱨������  Added by Tony Lin on 2012-5-14
		            	vehPassDao.insertBkAlarmVeh(SBBH, FXBH, HPHM, HPZL, JGSJ, CLSD, HPYS, CSYS, CLLX, tztpFtpPath, qmtpFtpPath, hptpFtpPath, CDH, XS, alarmMap.get("bkid"), alarmMap.get("bk_type_no"));
		            }
				}
	            
			}
		}catch(Exception e){
			log.error("�������ݱ���ʧ�ܣ�" + e.getMessage());
			returnStr = "�������ݱ���ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
			//return returnStr;
		}
		*/
		end = System.currentTimeMillis();
		total = end - start;
		long sec = total / 1000;		//����ʱ�侫ȷ����
		log.error("��������д���ܹ���ʱ�� " + sec / 60 + " �� " + sec % 60 + " ��"+total%1000+"���롣 ");
		
		log.debug("WriteVehInfoServiceImpl.WriteVehicleInfoWithPhoto()  End......");
		return returnStr;
	}
	
	/**
	 * ���ر�������д��ӿڷ���
	 * @param vehInfo
	 * @return String
	 */
	/*public String WriteAlarmVehInfo(VehicleInfo vehInfo){
		log.debug("WriteVehInfoServiceImpl.WriteAlarmVehInfo()  Start......");
		String returnStr = "";

		//�ж�һЩ�������Ƿ�д��
		if("".equals(vehInfo.getHphm())){
			return "���ƺ���(HPHM)����Ϊ�գ�����д�������";
		}
		if("".equals(vehInfo.getJgsj())){
			return "����ʱ��(JGSJ)����Ϊ�գ�����д�������";
		}
		//������ڸ�ʽ�Ƿ���ȷ
		Date jgsj = ItsUtility.StringToDate(vehInfo.getJgsj(),"yyyy-MM-dd HH:mm:ss");
		log.debug("jgsj:"+jgsj);
		if(jgsj == null){
			return "����ʱ��(JGSJ:"+vehInfo.getJgsj()+")��ʽ��������д�������";
		}
		if("".equals(vehInfo.getHpys())){
			return "������ɫ(HPYS)����Ϊ�գ�����д�������";
		}
		if("".equals(vehInfo.getJgdd())){
			return "�����ص�(JGDD)����Ϊ�գ�����д�������";
		}
		if("".equals(vehInfo.getCdh())){
			return "������(CDH)����Ϊ�գ�����д�������";
		}
		try{
			boolean isInsert = vehPassDao.WriteAlarmVehInfo(vehInfo);
			if(isInsert){
				log.debug("���ر�������д��ɹ���");
			}else{
				returnStr = "���ر�������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
		}catch(Exception e){
			log.error("д�벼�ر�������ʧ�ܣ�" + e.getMessage());
			returnStr = "д�벼�ر�������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
		}
		
		log.debug("WriteVehInfoServiceImpl.WriteAlarmVehInfo()  End......");
		return returnStr;
	}*/
	
	/**
	 * ����������д��ӿڷ���
	 * @param blackNameInfo
	 * @return
	 */
	/*public String WriteBlackName(BlackNameInfo blackNameInfo){
		log.debug("WriteVehInfoServiceImpl.WriteBlackName()  Start......");
		String returnStr = "";
		
		if("".equals(blackNameInfo.getCph())){
			return "���ƺ�(CPH)����Ϊ�գ�����д�������";
		}
		//���ó����ں����������Ƿ��Ѵ���
		if(vehPassDao.hasThisBlackName(blackNameInfo.getCph())){
			return "�ó��ƺ�("+blackNameInfo.getCph()+")�ں����������Ѵ��ڣ����β�����д�������";
		}
		try{
			boolean isInsert = vehPassDao.WriteBlackName(blackNameInfo);
			if(isInsert){
				log.debug("����������д��ɹ���");
			}else{
				returnStr = "����������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
		}catch(Exception e){
			log.error("д�����������ʧ�ܣ�" + e.getMessage());
			returnStr = "д�����������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
		}
		
		log.debug("WriteVehInfoServiceImpl.WriteBlackName()  End......");
		return returnStr;
	}*/
	
	/**
	 * ��ѯԶ�̷�����ʱ��
	 * @return
	 */
	public String QueryServerTime(){
		log.debug("WriteVehInfoServiceImpl.QueryServerTime()  Start......");
		String strServerDate = vehPassDao.getSysdate();
		if("".equals(strServerDate)){
			Date date = new Date();
			strServerDate = ItsUtility.DateToString(date, "yyyy-MM-dd HH:mm:ss");
		}
		log.debug("strServerDate:" + strServerDate);
		log.debug("WriteVehInfoServiceImpl.QueryServerTime()  End......");
		return strServerDate;
	}
	
	/**
	 * �豸״̬д��ӿڷ���
	 * @return
	 */
	/*public String WriteDeviceStat(DeviceStatInfo deviceStatInfo){
		log.debug("WriteVehInfoServiceImpl.WriteDeviceStat()  Start......");
		String returnStr = "";
		
		if("".equals(deviceStatInfo.getChange_time())){
			return "�豸״̬���ʱ��(change_time)����Ϊ�գ�����д�������";
		}
		if("".equals(deviceStatInfo.getCross_no())){
			return "·�ڱ��(cross_no)����Ϊ�գ�����д�������";
		}
		if("".equals(deviceStatInfo.getDevice_type())){
			return "�豸����(device_type)����Ϊ�գ�����д�������";
		}
		if("".equals(deviceStatInfo.getDevice_status())){
			return "�豸״̬(device_status)����Ϊ�գ�����д�������";
		}
		if(deviceStatInfo.getDevice_status().length() > 1){
			return "�豸״̬(device_status)���ݴ�������д�������";
		}
		
		try{
			boolean isInsert = vehPassDao.WriteDeviceStat(deviceStatInfo);
			if(isInsert){
				log.debug("�豸״̬����д��ɹ���");
			}else{
				returnStr = "�豸״̬����д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
		}catch(Exception e){
			log.error("д���豸״̬����ʧ�ܣ�" + e.getMessage());
			returnStr = "д���豸״̬����ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
		}
		
		log.debug("WriteVehInfoServiceImpl.WriteDeviceStat()  End......");
		return returnStr;
	}*/
	
	/**
	 * test for �ļ��ϴ�
	 */
	public String WriteFileTest(String fileName,String strFileBase64){
		log.debug("fileName" + fileName);
		log.debug("strFileBase64:" + strFileBase64);
		log.debug("strFileBase64 length:" + strFileBase64.length());
		
		FileOutputStream fos = null;   
        File file111 = new File(AppInitConstants.IMG_SERVER_ABS_FILE_PATH + fileName);   
        byte[] bytes = Base64.decode(strFileBase64);   
        try {   
        	fos = new FileOutputStream(file111);   
        	fos.write(bytes);   
        	fos.flush();   
        	fos.close();   
        } catch (Exception e) {   
            return "Wrong!" + e;   
        }   

		return "";
	}
	
	
	/**
	 * Υ������(�������ڡ��羯)д��ӿ�
	 * @param SBBH
	 * @param FXBH
	 * @param HPHM
	 * @param HPZL
	 * @param JGSJ
	 * @param CLSD
	 * @param HPYS
	 * @param CSYS
	 * @param CLLX
	 * @param CDH
	 * @param XS
	 * @param Cltp1
	 * @param Cltp2
	 * @param Cltp3
	 * @param CltpType
	 * @param CJFS
	 * @return
	 */
	public String WriteSurveilInfo(String SBBH,String FXBH,String HPHM,String HPZL,String JGSJ,String CLSD,String HPYS,String CSYS,
			String CLLX,String CDH,String XS,String Cltp1,String Cltp2,String Cltp3,String CltpType,String VideoFile,String VideoType,String CJFS){
		log.debug("WriteVehInfoServiceImpl.WriteSurveilInfo()  Start......");
	    long start = System.currentTimeMillis();																					
        long end;																																		
		long total = 0;	
		
		String returnStr = "";
		
		if("".equals(SBBH)){
			return "�豸���(SBBH)����Ϊ�գ�����д�������";
		}
		if("".equals(HPHM)){
			return "���ƺ���(HPHM)����Ϊ�գ�����д�������";
		}
		if("".equals(JGSJ)){
			return "����ʱ��(JGSJ)����Ϊ�գ�����д�������";
		}
		if("".equals(CDH)){
			return "�������(CDH)����Ϊ�գ�����д�������";
		}
		if("".equals(HPYS)){
			return "������ɫ(HPYS)����Ϊ�գ�����д�������";
		}
		if("".equals(Cltp1)){
			return "Υ��ͼƬ1(Cltp1)����Ϊ�գ�����д�������";
		}
		//����ͼƬĬ�Ϻ�׺��
		if("".equals(CltpType)){
			CltpType = "jpg";
		}
		//����¼��Ĭ�Ϻ�׺��
		if("".equals(VideoType)){
			VideoType = "mp4";
		}
		
		Date checkJgsj = ItsUtility.StringToDate(JGSJ,"yyyy-MM-dd HH:mm:ss");
		log.debug("jgsj:"+checkJgsj);
		if(checkJgsj == null){
			return "����ʱ��(JGSJ:"+JGSJ+")��ʽ��������д�������";
		}
		JGSJ = ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss");
		FileOutputStream fos = null;
		File file = null;
		byte[] bytes = null;
		String cltpName = "";
		String fileNewPath = "";
		String cltp1FtpPath = "";
		String cltp2FtpPath = "";
		String cltp3FtpPath = "";
		String videoFtpPath = "";
		
		if("".equals(imaPath)){
			imaPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH;
		}
		if("".equals(ftpPre)){
			ftpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		//���������ļ����ѡ��   Added by Tony Lin on 2013-06-14
		//Modified by Tony Lin on 2013-06-14
		
		Map<String,String> ftpMap = ItsUtility.getRandomImgPath("wf");
		String imaPath = ftpMap.get("ImagePath");
		String  virtualRoute = ftpMap.get("VirtualRoute");
		String  ftpPre = ftpMap.get("ftpPre");
		if("".equals(imaPath)){
			imaPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH;
		}
		if("".equals(ftpPre)){
			ftpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		if("".equals(virtualRoute)){
			virtualRoute = AppInitConstants.IMG_SERVER_FILE_PATH;
		}
		String gaFtpPre = SystemConfig.getConfigInfomation("gaFtpPre");
		if("".equals(gaFtpPre)){
			gaFtpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		
		
		String gaCltp1FtpPath = "";
		String gaCltp2FtpPath = "";
		String gaCltp3FtpPath = "";
		String gaVideoFtpPath = "";
		String isWriteGA = SystemConfig.getConfigInfomation("isWriteGA");
		try{
			try {
				/*
				HttpPicSaver httpPicSaver = new HttpPicSaver(AppInitConstants.HTTP_WF_PIC_IP_ZW, AppInitConstants.HTTP_PIC_PORT);
				String zwurl = "http://"+AppInitConstants.HTTP_WF_PIC_IP_ZW+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
				String gaurl = "http://"+AppInitConstants.HTTP_WF_PIC_IP_GA+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
				//�羯Υ��ͼƬ��ŵ�http ͼƬ������
				if(null != Cltp1 && !"".equals(Cltp1)){//������һ��
					cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "1." + CltpType;
					//��ͼƬ������ת��
					bytes = Base64.decode(Cltp1);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp1FtpPath = zwurl+picKey;
					gaCltp1FtpPath =gaurl+picKey;
				}
				
				if( null!= Cltp2  && !"".equals(Cltp2)){//�����ڶ����ļ�
					cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "2." + CltpType;
					//��ͼƬ������ת��
					bytes = Base64.decode(Cltp2);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp2FtpPath = zwurl+picKey;
					gaCltp2FtpPath =gaurl+picKey;
				}
				if(null != Cltp3 && !"".equals(Cltp3)){//�����������ļ�
	        		cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "3." + CltpType;
	        		//��ͼƬ������ת��
					bytes = Base64.decode(Cltp3);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp3FtpPath = zwurl+picKey;
					gaCltp3FtpPath =gaurl+picKey;
				}
				*/
	//******************************************************************************************			
		
				//��Υ��ͼƬ����ڱ��ػ���FTP�ϣ�Υ������д�����ݿⲢ��¼Υ��ͼƬ��ַ
				//Υ��ͼƬ¼�񱣴�Ŀ¼·��Ϊ������(��)/�豸���/ͼƬ��¼����
				
				if(null != Cltp1 && !"".equals(Cltp1)){//������һ���ļ�
					cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "1." + CltpType;
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+  ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					try {
						file = new File(imaPath  + fileNewPath + cltpName);  
						//if(file.isFile()){
						//	returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
						//	return returnStr;
						//}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp1);   
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp1);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp1FtpPath = ftpPre + virtualRoute + fileNewPath +cltpName;
		        	gaCltp1FtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
				}
	        	if( null!= Cltp2  && !"".equals(Cltp2)){//�����ڶ����ļ�
	        		cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "2." + CltpType;
					fileNewPath =  AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+  ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
//						if(file.isFile()){
//							returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp2);   
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp2);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp2FtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaCltp2FtpPath = gaFtpPre + virtualRoute  + fileNewPath +cltpName;
	        	}
	        	if(null != Cltp3 && !"".equals(Cltp3)){//�����������ļ�
	        		cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "3." + CltpType;
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
//						if(file.isFile()){
//							returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp3);
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp3);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp3FtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaCltp3FtpPath = gaFtpPre + virtualRoute  + fileNewPath +cltpName;
	        	}
	        	if(null != VideoFile && !"".equals(VideoFile)){//����¼���ļ�
	        		cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "." + VideoType;
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
						//if(file.isFile()){
//							returnStr = "Υ������¼��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(VideoFile);
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
						
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,VideoFile);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	videoFtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaVideoFtpPath = gaFtpPre + virtualRoute  + fileNewPath +cltpName;
	        	}
	        	
	        	
	        } catch (Exception e) {
	        	log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
	        	returnStr = "Υ������ͼƬд��ʧ�ܣ����ȷ��ͼƬ�����Ƿ���ȷ��" + e;
	        	return returnStr;
	        } finally{
	             if (fos != null) {
	                 try {
	                	 fos.flush(); 
	                     fos.close();
	                 }
	                 catch (Exception e){
	                 }
	             }
	         }
	        //������д�����ݿ�(WFSJB_YSH)
			boolean isInsert = vehPassDao.WriteSurveilInfo(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,CDH,XS,cltp1FtpPath,cltp2FtpPath,cltp3FtpPath,CltpType,videoFtpPath,VideoType,CJFS);
			if(isInsert){
				log.debug("Υ������д��ɹ���");
			}else{
				returnStr = "Υ������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
			if("1".equals(isWriteGA)){
				//������д�����ݿ���ʱ��(WFSJB_YSH_TEMP)
				boolean isInsertTemp = vehPassDao.WriteSurveilInfoTemp(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,CDH,XS,gaCltp1FtpPath,gaCltp2FtpPath,gaCltp3FtpPath,CltpType,gaVideoFtpPath,VideoType,CJFS);
				if(isInsertTemp){
					log.debug("Υ������д����ʱ���ɹ���");
				}else{
					returnStr = "Υ������д����ʱ��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
				}
			}
		}catch(Exception e){
			log.error("д��Υ������ʧ�ܣ�" + e.getMessage());
			returnStr = "д��Υ������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
			return returnStr;
		}
		
		log.debug("WriteVehInfoServiceImpl.WriteSurveilInfo()  End......");
		
		end = System.currentTimeMillis();
		total = end - start;
		long sec = total / 1000;		//����ʱ�侫ȷ����
		log.debug("��������д���ܹ���ʱ�� " + sec / 60 + " �� " + sec % 60 + " ��"+total%1000+"���롣 ");
		return returnStr;
	}
	
	
	/**
	  * ��Υ������(�������ڡ��羯)д��ӿڣ�����RedLightTime��RedLightDuration��ViolationType
	 * @param SBBH
	 * @param FXBH
	 * @param HPHM
	 * @param HPZL
	 * @param JGSJ
	 * @param CLSD
	 * @param HPYS
	 * @param CSYS
	 * @param CLLX
	 * @param CDH
	 * @param XS
	 * @param Cltp1
	 * @param Cltp2
	 * @param Cltp3
	 * @param CltpType
	 * @param CJFS
	 * @param RedLightTime
	 * @param RedLightDuration
	 * @param ViolationType
	 * @return
	 */
	public String WriteSurveilInfoExt(String SBBH,String FXBH,String HPHM,String HPZL,String JGSJ,String CLSD,String HPYS,String CSYS,
			String CLLX,String CDH,String XS,String Cltp1,String Cltp2,String Cltp3,String CltpType,String VideoFile,String VideoType,String CJFS,
			String RedLightTime,String RedLightDuration,String ViolationType){
		log.debug("WriteVehInfoServiceImpl.WriteSurveilInfoExt()  Start......");
	    long start = System.currentTimeMillis();																					
        long end;																																		
		long total = 0;	
		
		String returnStr = "";
		String fxbh = "";
		String clsd = "";
		String xsz = "";
		String dwxh = ""; // �豸��λ���
		if("".equals(SBBH)){
			return "�豸���(SBBH)����Ϊ�գ�����д�������";
		}
		if("".equals(HPHM)){
			return "���ƺ���(HPHM)����Ϊ�գ�����д�������";
		}
		if("".equals(JGSJ)){
			return "����ʱ��(JGSJ)����Ϊ�գ�����д�������";
		}
		if("".equals(CDH)){
			return "�������(CDH)����Ϊ�գ�����д�������";
		}
		if("".equals(HPYS)){
			return "������ɫ(HPYS)����Ϊ�գ�����д�������";
		}
		if("".equals(Cltp1)){
			return "Υ��ͼƬ1(Cltp1)����Ϊ�գ�����д�������";
		}
		//����ͼƬĬ�Ϻ�׺��
		if("".equals(CltpType)){
			CltpType = "jpg";
		}
		//����¼��Ĭ�Ϻ�׺��
		if("".equals(VideoType)){
			VideoType = "mp4";
		}
		
		Date checkJgsj = ItsUtility.StringToDate(JGSJ,"yyyy-MM-dd HH:mm:ss");
		log.debug("jgsj:"+checkJgsj);
		if(checkJgsj == null){
			return "����ʱ��(JGSJ:"+JGSJ+")��ʽ��������д�������";
		}
		JGSJ = ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss");
		log.debug("JGSJ-VIEW:"+JGSJ);
		FileOutputStream fos = null;
		File file = null;
		byte[] bytes = null;
		String cltpName = "";
		String fileNewPath = "";
		String cltp1FtpPath = "";
		String cltp2FtpPath = "";
		String cltp3FtpPath = "";
		String videoFtpPath = "";
		//�����ϴ��ŵ�
		InputStream input  = null;
		String fxName = "";
		String lkName = "";
		String xc_path = "";
		
		if("shangyu".equals(AppInitConstants.DEPLOY_PLACE)){
			fxName = ItsUtility.getDictionaryValue(AppInitConstants.DIRECTION_TYPE_ID, FXBH);
			lkName = vehPassDao.getDeviceNameByCode(SBBH);
			xc_path = "/ZKGQ/"+lkName+"/"+fxName+"/";
			log.debug("�ϴ��ŵ�FTP·��-----------------------------"+xc_path);
		}
		
		if("".equals(imaPath)){
			imaPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH;
		}
		if("".equals(ftpPre)){
			ftpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		//���������ļ����ѡ��   Added by Tony Lin on 2013-06-14
		//Modified by Tony Lin on 2013-06-14
		
		Map<String,String> ftpMap = ItsUtility.getRandomImgPath("wf");
		String imaPath = ftpMap.get("ImagePath");
		String virtualRoute = ftpMap.get("VirtualRoute");
		String ftpPre = ftpMap.get("ftpPre");
		if("".equals(imaPath)){
			imaPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH;
		}
		if("".equals(ftpPre)){
			ftpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		if("".equals(virtualRoute)){
			virtualRoute = AppInitConstants.IMG_SERVER_FILE_PATH;
		}
		
		String gaFtpPre = SystemConfig.getConfigInfomation("gaFtpPre");
		if("".equals(gaFtpPre)){
			gaFtpPre = AppInitConstants.IMG_SERVER_FTP_PRE;
		}
		
		String gaCltp1FtpPath = "";
		String gaCltp2FtpPath = "";
		String gaCltp3FtpPath = "";
		String gaVideoFtpPath = "";
		String isWriteGA = SystemConfig.getConfigInfomation("isWriteGA");
		
		
		
		if(AppInitConstants.DEPLOY_PLACE.equals("yuhuan")){  // by lvhua add 2013-05-20
			if(StringUtils.isNotEmpty(FXBH)){
				if(FXBH.equals("01")){
					fxbh = "3";
				}else if(FXBH.equals("02")){
					fxbh = "4";
				}else if(FXBH.equals("03")){
					fxbh = "2";
				}else{
					fxbh = "1";
				}
			}
			if(StringUtils.isNotEmpty(CLSD)){
				if(CLSD.length()==1){
					clsd = "00"+CLSD;
				}else if(CLSD.length()==2){
					clsd = "0"+CLSD;
				}else{
					clsd =  CLSD;
				}
			}
			if(StringUtils.isNotEmpty(XS)){
				if(XS.length()==1){
					xsz = "00"+XS;
				}else if(XS.length()==2){
					xsz = "0"+XS;
				}else{
					xsz =  XS;
				}
			}
			try {
				dwxh = AppInitConstants.resource.getString(SBBH);
			} catch (Exception e) {
				log.error("�����ڸ��豸���:"+SBBH);
			}
		}
		
		try{
			try {
				/*
				HttpPicSaver httpPicSaver = new HttpPicSaver(AppInitConstants.HTTP_WF_PIC_IP_ZW, AppInitConstants.HTTP_PIC_PORT);
				String zwurl = "http://"+AppInitConstants.HTTP_WF_PIC_IP_ZW+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
				String gaurl = "http://"+AppInitConstants.HTTP_WF_PIC_IP_GA+":"+AppInitConstants.HTTP_PIC_PORT + "/images";
				//�羯Υ��ͼƬ��ŵ�http ͼƬ������
				if(null != Cltp1 && !"".equals(Cltp1)){//������һ��
					cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "1." + CltpType;
					//��ͼƬ������ת��
					bytes = Base64.decode(Cltp1);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp1FtpPath = zwurl+picKey;
					gaCltp1FtpPath =gaurl+picKey;
				}
				
				if( null!= Cltp2  && !"".equals(Cltp2)){//�����ڶ����ļ�
					cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "2." + CltpType;
					//��ͼƬ������ת��
					bytes = Base64.decode(Cltp2);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp2FtpPath = zwurl+picKey;
					gaCltp2FtpPath =gaurl+picKey;
				}
				if(null != Cltp3 && !"".equals(Cltp3)){//�����������ļ�
	        		cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "3." + CltpType;
	        		//��ͼƬ������ת��
					bytes = Base64.decode(Cltp3);
					//��ͼƬ���浽ͼƬ������ ������ͼƬ�����ַ
					String picKey =httpPicSaver.save(bytes, cltpName);
					cltp3FtpPath = zwurl+picKey;
					gaCltp3FtpPath =gaurl+picKey;
				}
				*/
				
				//*************************************************************old
				
				//��Υ��ͼƬ����ڱ��ػ���FTP�ϣ�Υ������д�����ݿⲢ��¼Υ��ͼƬ��ַ
				//Υ��ͼƬ¼�񱣴�Ŀ¼·��Ϊ������(��)/�豸���/ͼƬ��¼����
				if(null != Cltp1  && !"".equals(Cltp1)){//������һ���ļ�
					//cltpName = (CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "1." + CltpType);
					//fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+  ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					//fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					 // ��������� Ŀ¼�ṻ  ����ʱ��\·��\�ļ�����  ��֮�෴ by lvhua 2013-05-20
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+(!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/"):(SBBH + "/"+ItsUtility.DateToString(checkJgsj, "yyyyMMddHH")+ "/"));
					cltpName = (!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "1." + CltpType):(AppInitConstants.DEPLOY_PLACE_NO +"1"+dwxh+fxbh+JGSJ+CDH+xsz+clsd+"."+ CltpType));
					try {
						file = new File(imaPath  + fileNewPath + cltpName);  
//						if(file.isFile()){
//							returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp1); 
						fos = new FileOutputStream(file); 
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp1);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp1FtpPath = ftpPre + virtualRoute + fileNewPath +cltpName;
		        	gaCltp1FtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
		        	
		        	
		        	
//		        	if("shangyu".equals(AppInitConstants.DEPLOY_PLACE)){
//		        		log.debug("------------��ʼ�ϴ��ŵ�ͼƬ---------"+bytes.length);
//		        		input = new  ByteArrayInputStream(bytes);
//		        		String filename = JGSJ+"-000A.jpg";
//		        		boolean isUpload = FtpUtil.UploadFileByIp(AppInitConstants.FTP_HOST_NAME_XC, input, xc_path, filename);
//		        		if(isUpload){
//		        			log.debug("�ϴ��ŵ�FTPͼƬ1��"+filename+" �ɹ�!");
//		        		}else{
//		        			log.debug("�ϴ��ŵ�FTPͼƬ1��"+filename+" ʧ��!");
//		        		}
//		        		input.close();
//		        	}
		        	
				}
	        	if(null != Cltp2 &&   !"".equals(Cltp2)){//�����ڶ����ļ�
	        		//cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "2." + CltpType;
	        		//fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					 // ��������� Ŀ¼�ṻ  ����ʱ��\·��\�ļ�����  ��֮�෴ by lvhua 2013-05-20
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+(!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/"):(SBBH + "/"+ItsUtility.DateToString(checkJgsj, "yyyyMMddHH")+ "/"));
					cltpName = (!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "2." + CltpType):(AppInitConstants.DEPLOY_PLACE_NO +"1"+dwxh+fxbh+JGSJ+CDH+xsz+clsd+"_2."+ CltpType));
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
//						if(file.isFile()){
//							returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp2);   
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp2);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp2FtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaCltp2FtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
		        	if("shangyu".equals(AppInitConstants.DEPLOY_PLACE)){
		        		log.debug("byte2_length---------"+bytes.length);
		        		input = new  ByteArrayInputStream(bytes);
		        		String filename = JGSJ+"-000B.jpg";
		        		boolean isUpload = FtpUtil.UploadFileByIp(AppInitConstants.FTP_HOST_NAME_XC, input, xc_path, filename);
		        		if(isUpload){
		        			log.debug("�ϴ��ŵ�FTPͼƬ2��"+filename+" �ɹ�!");
		        		}else{
		        			log.debug("�ϴ��ŵ�FTPͼƬ2��"+filename+" ʧ��!");
		        		}
		        		input.close();
		        	}
	        	}
	        	if(null != Cltp3 && !"".equals(Cltp3)){//�����������ļ�
	        		//cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_"  + CDH + "_" + "3." + CltpType;
	        		//fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					 // ��������� Ŀ¼�ṻ  ����ʱ��\·��\�ļ�����  ��֮�෴ by lvhua 2013-05-20
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+(!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/"):(SBBH + "/"+ItsUtility.DateToString(checkJgsj, "yyyyMMddHH")+ "/"));
					cltpName = (!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "3." + CltpType):(AppInitConstants.DEPLOY_PLACE_NO +"1"+dwxh+fxbh+JGSJ+CDH+xsz+clsd+"_3."+ CltpType));
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
//						if(file.isFile()){
//							returnStr = "Υ������ͼƬ��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(Cltp3);
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,Cltp3);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	cltp3FtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaCltp3FtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
		        	if("shangyu".equals(AppInitConstants.DEPLOY_PLACE)){
		        		log.debug("byte3_length---------"+bytes.length);
		        		input = new  ByteArrayInputStream(bytes);
		        		String filename = JGSJ+"-000C.jpg";
		        		boolean isUpload = FtpUtil.UploadFileByIp(AppInitConstants.FTP_HOST_NAME_XC, input, xc_path, filename);
		        		if(isUpload){
		        			log.debug("�ϴ��ŵ�FTPͼƬ3��"+filename+" �ɹ�!");
		        		}else{
		        			log.debug("�ϴ��ŵ�FTPͼƬ3��"+filename+" ʧ��!");
		        		}
		        		input.close();
		        	}
	        	}
	        	if(null != VideoFile &&  !"".equals(VideoFile)){//����¼���ļ�
	        		//cltpName = CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "." + VideoType;
					//fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF + "/"+   ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/";
					 // ��������� Ŀ¼�ṻ  ����ʱ��\·��\�ļ�����  ��֮�෴ by lvhua 2013-05-20
					fileNewPath = AppInitConstants.IMG_SERVER_ABS_FILE_PATH_WF  + "/"+(!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(ItsUtility.DateToString(checkJgsj, "yyyyMMdd") + "/" + SBBH + "/"):(SBBH + "/"+ItsUtility.DateToString(checkJgsj, "yyyyMMddHH")+ "/"));
					cltpName = (!AppInitConstants.DEPLOY_PLACE.equals("yuhuan")?(CJFS + "_" + SBBH + "_" +  ItsUtility.DateToString(checkJgsj, "yyyyMMddHHmmss") + "_" + FXBH + "_" + CDH + "_" + "." + VideoType):(AppInitConstants.DEPLOY_PLACE_NO +"1"+dwxh+fxbh+JGSJ+CDH+xsz+clsd+"."+ VideoType));
					try {
						file = new File(imaPath  + fileNewPath + cltpName);   
//						if(file.isFile()){
//							returnStr = "Υ������¼��"+imaPath  + fileNewPath + cltpName+"���Ѵ��ڣ��������ظ��ϴ����ݣ�";
//							return returnStr;
//						}
						//����ļ��в����ڣ����ȴ����ļ���
						if(file.isDirectory()){
							log.debug("the directory is exists!");
						}else{
							file.getParentFile().mkdirs();
							//filePath.createNewFile();
							log.debug("�½�Ŀ¼��"+file+" �ɹ�");
						}
						
						bytes = Base64.decode(VideoFile);
						fos = new FileOutputStream(file);   
						fos.write(bytes);   
						fos.flush();   
						fos.close();
						
					} catch (Exception e) {
						PicInfo picInfo = new PicInfo(imaPath  + fileNewPath + cltpName,VideoFile);
						AppInitConstants.picInfoQueue.put(picInfo);
						log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
					} 
		        	videoFtpPath = ftpPre + virtualRoute  + fileNewPath +cltpName;
		        	gaVideoFtpPath = gaFtpPre + virtualRoute + fileNewPath +cltpName;
	        	}
	        	
	        	
	        } catch (Exception e) {
	        	log.error("д��Υ��ͼƬʧ�ܣ�" + e.getMessage());
	        	returnStr = "Υ������ͼƬд��ʧ�ܣ����ȷ��ͼƬ�����Ƿ���ȷ��" + e;
	        	return returnStr;
	        } finally{
	             if (fos != null) {
	                 try {
	                	 fos.flush(); 
	                     fos.close();
	                 }
	                 catch (Exception e){
	                 }
	             }
	         }
	        //������д�����ݿ�(WFSJB_YSH)
			boolean isInsert = vehPassDao.WriteSurveilInfoExt(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,CDH,XS,cltp1FtpPath,cltp2FtpPath,cltp3FtpPath,CltpType,videoFtpPath,VideoType,CJFS,RedLightTime,RedLightDuration,ViolationType);
			if(isInsert){
				log.debug("Υ������д��ɹ���");
			}else{
				returnStr = "Υ������д��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
			}
			if("1".equals(isWriteGA)){
				boolean isInsertTemp = vehPassDao.WriteSurveilInfoExtTemp(SBBH,FXBH,HPHM,HPZL,JGSJ,CLSD,HPYS,CSYS,CLLX,CDH,XS,gaCltp1FtpPath,gaCltp2FtpPath,gaCltp3FtpPath,CltpType,gaVideoFtpPath,VideoType,CJFS,RedLightTime,RedLightDuration,ViolationType);
				if(isInsertTemp){
					log.debug("Υ������д����ʱ���ɹ���");
				}else{
					returnStr = "Υ������д����ʱ��ʧ�ܣ����������ֶ��Ƿ���ȷ��";
				}
			}
			
		}catch(Exception e){
			log.error("д��Υ������ʧ�ܣ�" + e.getMessage());
			returnStr = "д��Υ������ʧ�ܣ�����������������ʽ�Ƿ���ȷ��" + e.getMessage();
			return returnStr;
		}
		
		log.debug("WriteVehInfoServiceImpl.WriteSurveilInfoExt()  End......");
		
		end = System.currentTimeMillis();
		total = end - start;
		long sec = total / 1000;		//����ʱ�侫ȷ����
		log.info("��������д���ܹ���ʱ�� " + sec / 60 + " �� " + sec % 60 + " ��"+total%1000+"���롣 ");
		return returnStr;
	}
	
	
	public String writeWbRecord(String wbjc_code, String cdh, int speed_aver, int speed_lon_veh,int speed_mid_veh,int speed_sht_veh,int volume_veh,int volume_lon_veh,int volume_mid_veh,int volume_sht_veh,int occupy_veh,int occupy_lon_veh,int occupy_mid_veh,int occupy_sht_veh,int headway_time,String gather_time) {
		if(StringUtils.isBlank(wbjc_code)){
			return "΢���豸��Ų���Ϊ��";
		}else if(StringUtils.isBlank(cdh)){
			return "�����Ų���Ϊ��";
		}
		WbRecord wbReco = new WbRecord("1");
		wbReco.setWbjc_code(wbjc_code);
		wbReco.setCdh(cdh);
		wbReco.setGather_time(gather_time);
		wbReco.setSpeed_aver(speed_aver);
		wbReco.setSpeed_lon_veh(speed_lon_veh);
		wbReco.setSpeed_mid_veh(speed_mid_veh);
		wbReco.setSpeed_sht_veh(speed_sht_veh);
	
		wbReco.setVolume_lon_veh(volume_lon_veh);
		wbReco.setVolume_mid_veh(volume_mid_veh);
		wbReco.setVolume_sht_veh(volume_sht_veh);
		wbReco.setVolume_veh(volume_veh);
		wbReco.setOccupy_lon_veh(occupy_lon_veh);
		wbReco.setOccupy_mid_veh(occupy_mid_veh);
		wbReco.setOccupy_sht_veh(occupy_sht_veh);
		wbReco.setOccupy_veh(occupy_veh);
		wbReco.setHeadway_time(headway_time);
		//wbReco.setUpload_time(new Date());
		
		int affectedRows = 0;
		try {
			//д��΢������
			affectedRows = vehPassDao.insertWbRecord(wbReco);
			//seq_wbjc_data_id.nextval
			if(affectedRows == 1){
				
				log.info("΢������д��ɹ�,΢���豸���:  " + wbjc_code);
			}else{
				log.info("΢������д��ʧ��,���������ֶ��Ƿ���ȷ��");
			}
		} catch (Exception e) {
			log.error("΢������д��ʧ��: " + wbjc_code +  ":" + e.getMessage());
			e.printStackTrace();
			return "΢������д��ʧ��";
		}
		return String.valueOf(affectedRows);
	}
	
	
	/**
	 * δʹ��
	 * @param file
	 * @param filename
	 * @return
	 */
//  ʹ��DataHandler���Ͳ����ϴ��ļ�
    public boolean uploadWithDataHandler(DataHandler file, String filename)
    {
        
         FileOutputStream fos = null;
         try
         {            
             fos = new FileOutputStream(filename);   
             //  ��ͨ��DataHandler���getInputStream������ȡ�ϴ�����
             writeInputStreamToFile(file.getInputStream(), fos);
             fos.close();
         }
         catch (Exception e)
         {
             return false;
         }
         finally
         {
             if (fos != null)
             {
                 try
                 {
                     fos.close();
                 }
                 catch (Exception e)
                 {
                 }
             }
         }
         return true;
    }
    
    /**
     *  δʹ��
     */
    private void writeInputStreamToFile(InputStream is, OutputStream os) throws Exception
    {
         int n = 0;
         byte[] buffer = new byte[8192];
         while((n = is.read(buffer)) > 0)
         {
             os.write(buffer, 0, n);
         }
    }
    
    /**
     * ����ʵʱ������Ϣ
     * @throws Exception
     */
    private void sendLiveMessage(String HPHM,String SBBH,String JGSJ,String FXBH,String CDH,String HPZL,String CLSD,String TZTP,String QMTP,String HPTP) throws Exception{
    	//hphm$sbmc$jgsj$fxmc$cdh$hpzlmc$clsd$tztp$qmtp$hptp$sbbh$fxbh$hpzlbh
    	//hphm$sbmc$jgsj$fxmc$cdh$hpzlmc$clsd$tztp$qmtp$hptp$sbbh$fxbh$hpzlbh$hpys$xs �����Ӻ�����ɫ������
    	
    	StringBuffer contentStr = new StringBuffer();
    	 contentStr.append("LIVE:" + ItsUtility.escape(HPHM) + "$");
         contentStr.append(ItsUtility.escape(AppInitConstants.ALL_CROSS_MAP.get(SBBH)) + "$");
         contentStr.append(JGSJ + "$");
         contentStr.append(ItsUtility.escape(ItsUtility.getDictionaryValue(AppInitConstants.DIRECTION_TYPE_ID,FXBH)) + "$");
         contentStr.append(CDH + "$");
         contentStr.append(ItsUtility.escape(ItsUtility.getDictionaryValue(AppInitConstants.CAR_TYPE_ID,HPZL)) + "$");
         contentStr.append(CLSD + "$");
         contentStr.append(TZTP + '$');
         contentStr.append(QMTP + '$');
         contentStr.append(HPTP + '$');
         contentStr.append(SBBH + '$');
         contentStr.append(FXBH + '$');
         contentStr.append(HPZL);
         try{
        	/* if(!AppInitConstants.vehPassInfoQueue.isEmpty() && AppInitConstants.vehPassInfoQueue.size()>(ItsConstants.MAX_STAY_MSG - ItsConstants.MAX_STAY_MSG/3)){
        		 //AppInitConstants.vehPassInfoQueue.take();
        		 log.debug("AppInitConstants.vehPassInfoQueue.size()==========" + AppInitConstants.vehPassInfoQueue.size());
        	 }*/
        	 log.debug("AppInitConstants.vehPassInfoQueue.size():"+AppInitConstants.vehPassInfoQueue.size());
        	 AppInitConstants.vehPassInfoQueue.put(contentStr.toString());
         }catch(Exception e){
        	 log.error("ʵʱ��������д�����ʧ��"+e.getMessage());
     		
     	}
    }

    
    /**
     * ���Ͳ��ر�����Ϣ
     * @param BKID
     * @param HPHM
     * @param HPZL
     * @param SBBH
     * @param CDH
     * @param FXBH
     * @param JGSJ
     * @param CLSD
     * @param TZTP
     * @param QMTP
     * @param HPTP
     * @param HPYS
     * @param BK_TYPE_NO
     * @throws Exception
     */
    private void sendAlarmmessge(String BKID,String HPHM,String HPZL,String SBBH,String CDH,String FXBH,String JGSJ,String 
    		CLSD,String TZTP,String QMTP,String HPTP,String HPYS,String BK_TYPE_NO,String ifsend,String phoneno) throws Exception{
    	//bkid$hpzl$sbbh$cdh$fxbh$jgsj$clsd$tztp$qmtp$hptp$hpys$sbmc$fxmc$hpzlmc$bk_typemc
    	StringBuffer bkInfo = new StringBuffer();
    	bkInfo.append("BK:" + ItsUtility.removeNull(BKID) + "$" + ItsUtility.escape(HPHM) + "$");
    	bkInfo.append(HPZL + "$");
    	bkInfo.append(SBBH + "$");
    	bkInfo.append(CDH + "$");
    	bkInfo.append(FXBH + "$");
    	bkInfo.append(JGSJ + "$");
    	bkInfo.append(CLSD + "$");
    	bkInfo.append(TZTP + "$");
    	bkInfo.append(QMTP + "$");
    	bkInfo.append(HPTP + "$");
    	bkInfo.append(HPYS + "$");
    	bkInfo.append(ItsUtility.escape(AppInitConstants.ALL_CROSS_MAP.get(SBBH)) + "$");
    	bkInfo.append(ItsUtility.escape(ItsUtility.getDictionaryValue(AppInitConstants.DIRECTION_TYPE_ID,FXBH)) + "$");
    	//bkInfo.append(ItsUtility.getDictionaryValue(AppInitConstants.CAR_TYPE_ID,HPZL));
    	//���Ӳ��ر������ͣ�added by Tony Lin on 2012-5-10
    	bkInfo.append(ItsUtility.escape(ItsUtility.getDictionaryValue(AppInitConstants.CAR_TYPE_ID,HPZL)) + "$");
    	bkInfo.append(ItsUtility.escape(ItsUtility.getDictionaryValue(AppInitConstants.BK_TYPE_ID,ItsUtility.removeNull(BK_TYPE_NO))) + "$");
    	 //���Ӷ��ű���������Ϣ  Added by Tony Lin on 2012-10-23 for ����   
    	bkInfo.append(ItsUtility.removeNull(phoneno) + "$");
    	bkInfo.append(ItsUtility.removeNull(ifsend));//�Ƿ���ű�����0:��  1:�ǣ�
    	
    	try{
    		 /*if(!AppInitConstants.vehAlarmInfoQueue.isEmpty() && AppInitConstants.vehAlarmInfoQueue.size()>(ItsConstants.MAX_STAY_MSG - ItsConstants.MAX_STAY_MSG/3)){
    			 AppInitConstants.vehAlarmInfoQueue.take();
    		 }*/
    		 AppInitConstants.vehAlarmInfoQueue.put(bkInfo.toString());
    	}catch(Exception e){
    		log.error("��������д�����ʧ��"+e.getMessage());
    	}
    	
    }
	
}