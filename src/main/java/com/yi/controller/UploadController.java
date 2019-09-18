package com.yi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yi.util.UploadFileUtils;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
			
	private String innerUploadPath = "resources/upload";
	
	@Resource(name="uploadPath")
	private String outUploadPath;
	
	
	@RequestMapping(value="/innerUpload", method=RequestMethod.GET)
	public String innerUploadForm() {
		logger.info("-----------------------------innerUpload GET");
		return "innerUploadForm";
	}
	
	@RequestMapping(value="/innerUpload", method=RequestMethod.POST)
	public String innerUploadPost(String test, MultipartFile file, HttpServletRequest request, Model model) throws IOException {
		logger.info("-----------------------------innerUpload POST");
		logger.info("test="+test);
		logger.info("file="+file.getOriginalFilename());
		logger.info("file size="+file.getSize());
		
		String root_path = request.getSession().getServletContext().getRealPath("/"); //ex03서버
		// ex/03/resources/upload
		File dirPath = new File(root_path+"/"+innerUploadPath);
		if(dirPath.exists()==false) {//폴더 없음
			dirPath.mkdir();
		}
		//빈 껍데기 파일이 만들어짐
		UUID uid = UUID.randomUUID();
		String savedName = uid+"_"+file.getOriginalFilename();
		File target = new File(root_path+"/"+innerUploadPath,savedName); 
		FileCopyUtils.copy(file.getBytes(), target); //파일 업로드 완료
		
		model.addAttribute("test", test);
		model.addAttribute("file", savedName);
		
		
		
		return "innerUploadResult";
	}
	
	
	@RequestMapping(value="/innerMultiUpload", method=RequestMethod.GET)
	public String innerMultiUploadForm() {
		logger.info("-----------------------------innerMultiUpload GET");
		return "innerMultiUploadForm";
	}
	@RequestMapping(value="/innerMultiUpload", method=RequestMethod.POST)
	public String innerMultiUploadFormPOST(String test, List<MultipartFile> files, HttpServletRequest request, Model model) throws IOException {
		logger.info("-----------------------------innerMultiUpload POST");
		logger.info("test = "+test);
		for(MultipartFile file :files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize()+"");
			logger.info(file.getContentType());
		}
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(root_path+"/"+innerUploadPath);
		if(dir.exists()==false) {
			dir.mkdir();
		}
		
		ArrayList<String> list = new ArrayList<>();
		
		for(MultipartFile file:files) {
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString()+"_"+file.getOriginalFilename();
			
			File target = new File(root_path+"/"+innerUploadPath,savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			list.add(savedName);
		}
		
		model.addAttribute("test",test);
		model.addAttribute("list",list);
		
		return "innerMultiUploadResult";
	}
	
	
	@RequestMapping(value="/outUpload", method=RequestMethod.GET)
	public String outUploadGET() {
		logger.info("-----------------------------outUpload GET");
		return "outUploadForm";
	}
	
	
	
	
	
	@RequestMapping(value="/outUpload", method=RequestMethod.POST)
	public String outUploadPOST(String test,MultipartFile file,Model model) throws IOException {
		logger.info("-----------------------------outUpload POST");
		logger.info("test:"+test);
		logger.info("file:"+file.getOriginalFilename());
		logger.info("file size:"+file.getSize());
		
		UUID uid = UUID.randomUUID();
		String savedName = uid+"_"+file.getOriginalFilename();
		File target = new File(outUploadPath,savedName); //outUploadPath경로가 반드시 존재한다는 가정하게 처리됨
		FileCopyUtils.copy(file.getBytes(), target);
		
		model.addAttribute("test",test);
		model.addAttribute("file",savedName);
		
		return "outUploadResult";
	}
	@RequestMapping(value="/displayFile",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename){
		logger.info("----------displayFile,filename="+filename);
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);//확장자만 뽑아냄
		MediaType mType = null;
		ResponseEntity<byte[]> entity;
		
		if(formatName.equalsIgnoreCase("jpg")) {
			mType=MediaType.IMAGE_JPEG;
		}
		else if(formatName.equalsIgnoreCase("gif")) {
			mType=MediaType.IMAGE_GIF;
		}
		else if(formatName.equalsIgnoreCase("png")) {
			mType=MediaType.IMAGE_PNG;
		}
		InputStream in =null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(outUploadPath+"/"+filename);
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED
					);
		}catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				if(in !=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="/dragUpload", method=RequestMethod.GET)
	public String dragUploadGET() {
		logger.info("-----------------------------dragUpload GET");
		return "dragDropForm";
	}
	
	@RequestMapping(value="/dragUpload", method=RequestMethod.POST)
	public ResponseEntity<List<String>> dragUploadPOST(List<MultipartFile> files, String test) {
		logger.info("-----------------------------dragUploadPOST");
		
		logger.info("test:"+test);
		for(MultipartFile file:files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize()+"");
		}
		
		ResponseEntity<List<String>> entity = null;
		
		List<String> list = new ArrayList<>();
		
		try {
			for(MultipartFile file : files) {
				String savedName = UploadFileUtils.uploadFile(
						outUploadPath, 
						file.getOriginalFilename(), 
						file.getBytes()
						);
				list.add(savedName);
			}
			entity = new ResponseEntity<List<String>>(list,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteFile",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String filename){
		logger.info("-------------deleteFile, filename="+filename);
		ResponseEntity<String> entity = null;
		
		try {
			File sfile = new File(outUploadPath+"/"+filename);
			
//			File file = new File(outUploadPath+"/"+filename.substring(0,filename.indexOf("s_"))+filename.substring(filename.indexOf("s_")+2));
			File file = new File(outUploadPath+"/"+filename.substring(0,12)+filename.substring(14));
			
			if(file.exists()) {
				logger.info("file exist"+sfile.getPath());
				logger.info("file exist"+file.getPath());
				sfile.delete();
				file.delete();
			}else {
				logger.info("file not exist");
			}
			
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="outUpload2",method=RequestMethod.GET)
	public String outUpload2Get() {
		logger.info("--------------- outUpload2 GET");
		return "outUploadForm2";
	}
}



